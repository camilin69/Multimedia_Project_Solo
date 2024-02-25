package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Genre;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GenreController {
    ArrayList<Genre> genres = new ArrayList<>();

    public void createGenre(String name, ArrayList<Movie> moviesToAdd, ArrayList<Serie> seriesToAdd, String textCover, String backgroundCover){
        Genre newGenre = new Genre(name);
        newGenre.setMovies(moviesToAdd);
        newGenre.setSeries(seriesToAdd);
        genres.add(newGenre);
    }

    public int verifyInputs(TextField genreNameField, Label genreNameLabel){
        int aux = 0;

        if(genreNameField.getText().matches("^[a-zA-Z\\s]+$")){
            aux++;
            genreNameLabel.setTextFill(Color.web("#807170"));
            if(!genreFound(genreNameField.getText())){
                aux++;
            }else{
                genreNameLabel.setText("Genres can not be repeated");
                genreNameLabel.setTextFill(Color.RED);
            }
        }else{
            genreNameLabel.setText("Genre name only contains characters");
            genreNameLabel.setTextFill(Color.RED);
        }
        return aux;
    }

    public int verifyInputsToEdit(TextField genreNameField, Label genreNameLabel, String oldName){
        int aux = 0;

        if(genreNameField.getText().matches("^[a-zA-Z\\s]+$")){
            aux++;
            genreNameLabel.setTextFill(Color.web("#807170"));
            if(oldName.equals(genreNameField.getText())){
                aux++;
            }else{
                if(!genreFound(genreNameField.getText())){
                    aux++;
                }else{
                    genreNameLabel.setText("Genres can not be repeated");
                    genreNameLabel.setTextFill(Color.RED);
                }
            }
            
        }else{
            genreNameLabel.setText("Genre name only contains characters");
            genreNameLabel.setTextFill(Color.RED);
        }
        return aux;
    }

    public boolean genreFound(String genreName){
        for(Genre genre : genres){
            if(genre.getName().equals(genreName)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Movie> setMovies(ObservableList<String> genreMovies, ArrayList<Movie> allMovies){
        ArrayList<Movie> movies = new ArrayList<>();
        MovieController mc = new MovieController();

        for(String i : genreMovies){
            Movie newMovie = mc.currentMovie(allMovies, i);
            movies.add(newMovie);
        }
        return movies;
    }

    


    public ObservableList<String> currentGenreMovies(ComboBox<String> currentGenreMovies, ArrayList<Movie> movies){
        ObservableList<String> aux = FXCollections.observableArrayList();
        for(Movie movie: movies){
            aux.add(movie.getName());
        }
        return aux;
    }

    public ObservableList<String> allMoviesAvailable(ObservableList<String> currentGenreMovies, ArrayList<Movie> allMovies){
        ObservableList<String> movies = FXCollections.observableArrayList();
        for(Movie movie: allMovies){movies.add(movie.getName());}

        for(String movie: currentGenreMovies){
            movies.remove(movie);
        }
        return movies;
    }

    public void updateGenreComboBox(ComboBox<String> allGenres, ArrayList<Genre> genres){
        ObservableList<String> aux = FXCollections.observableArrayList();

        genres.forEach(e -> {
            aux.add(e.getName());
        });

        allGenres.setItems(aux);
    }





    public ArrayList<Genre> getGenres() {
        return genres;
    }
    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
    public void addGenre(Genre genre){
        genres.add(genre);
    }
    public void removeGenre(Genre genre){
        genres.remove(genre);
    }

    
}

