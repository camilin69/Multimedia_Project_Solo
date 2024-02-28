package co.edu.uptc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Admin;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class AdminController {
    private Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");


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

    public Admin getAdmin() {
        return admin;
    }
}
