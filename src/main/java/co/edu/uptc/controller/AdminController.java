package co.edu.uptc.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Admin;
import co.edu.uptc.model.Genre;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.Subscription;
import javafx.scene.paint.Color;

public class AdminController {
    private Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");


    public ArrayList<Movie> loadMoviesFromJson() {
        ArrayList<Movie> movies = new ArrayList<>();

        try (Reader reader = new FileReader(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/movies.json")) {

            Type listType = new TypeToken<List<Movie>>(){}.getType();
            movies = new Gson().fromJson(reader, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public ArrayList<Serie> loadSeriesFromJson() {
        ArrayList<Serie> series = new ArrayList<>();
        try (Reader reader = new FileReader(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/series.json")) {
            Type listType = new TypeToken<List<Serie>>(){}.getType();
            series = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return series;
    }

    public ArrayList<Genre> loadGenresFromJson() {
        ArrayList<Genre> genres = new ArrayList<>();
        try (Reader reader = new FileReader(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/genres.json")) {
            Type listType = new TypeToken<List<Genre>>(){}.getType();
            genres = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public void downloadCover(String path, String coverURL){
        try{
            String[] command = {"curl", "-o", 
                path, coverURL};

            ProcessBuilder processBuilder = new ProcessBuilder(command);

            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Cover Downloaded Successfully");
            } else {
                System.err.println("Error, output code: " + exitCode);
            }
        } catch (IOException | InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public String toWebColor(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        int a = (int) (color.getOpacity() * 255);

        return String.format("#%02X%02X%02X%02X", r, g, b, a);
    }

    public Admin getAdmin() {
        return admin;
    }

    
}
