package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class MovieController {
    public void moviesAvailablesNamesComboBox(ComboBox<String> allMoviesAvailables, ArrayList<Movie> movies){
        ObservableList<String> aux = FXCollections.observableArrayList();

        for(Movie movie: movies){
            aux.add(movie.getName());
        }

        allMoviesAvailables.setItems(aux);
    }

    public void addMoviesAvailablesNamesComboBox(ComboBox<String> allMoviesAvailables, String toAdd){
        ObservableList<String> aux = allMoviesAvailables.getItems();
        aux.add(toAdd);
        allMoviesAvailables.setItems(aux);
    }

    public void removeMoviesAvailablesNamesComboBox(ComboBox<String> allMoviesAvailables, String toRemove){
        ObservableList<String> aux = allMoviesAvailables.getItems();
        aux.remove(toRemove);
        allMoviesAvailables.setItems(aux);
    }

    public void addCurrentMoviesComboBox(ComboBox<String> currentMovies, String toAdd){
        ObservableList<String> aux = currentMovies.getItems();
        aux.add(toAdd);
        currentMovies.setItems(aux);
    }

    public void removeCurrentMoviesComboBox(ComboBox<String> currentMovies, String toRemove){
        ObservableList<String> aux = currentMovies.getItems();
        aux.remove(toRemove);
        currentMovies.setItems(aux);
    }
    
    public Movie currentMovie(ArrayList<Movie> movies, String movieName){
        for(Movie movie: movies){
            if(movie.getName().equals(movieName)){
                return movie;
            }
        }
        return null;
    }
}