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
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import javafx.scene.paint.Color;

public class AdminController {
    private Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");


    public void saveMoviesToJson(){
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/movies.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(admin.getMovies(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> loadMoviesFromJson(String jsonFilePath) {
        ArrayList<Movie> movies = new ArrayList<>();

        try (Reader reader = new FileReader(jsonFilePath)) {

            Type listType = new TypeToken<List<Movie>>(){}.getType();
            movies = new Gson().fromJson(reader, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public void saveSeriesToJson(){
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/series.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(admin.getSeries(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Serie> loadSeriesFromJson(String jsonFilePath) {
        ArrayList<Serie> series = new ArrayList<>();
        try (Reader reader = new FileReader(jsonFilePath)) {
            Type listType = new TypeToken<List<Serie>>(){}.getType();
            series = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return series;
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
