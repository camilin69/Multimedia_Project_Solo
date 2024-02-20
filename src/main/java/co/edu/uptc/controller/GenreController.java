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
        newGenre.setTextCover(textCover);
        newGenre.setBackgroundCover(backgroundCover);
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

    

    public boolean genreFound(String genreName){
        for(Genre genre : genres){
            if(genre.getName().equals(genreName)){
                return true;
            }
        }
        return false;
    }

    

    public ArrayList<Serie> setSeries(ObservableList<String> genreSeries, ArrayList<Serie> allSeries){
        ArrayList<Serie> series = new ArrayList<>();
        SerieController sc = new SerieController();

        for(String i : genreSeries){
            Serie newSerie = sc.currentSerie(allSeries, i);
            series.add(newSerie);
        }
        return series;
    }


    

    public ObservableList<String> currentGenreSeries(ComboBox<String> currentGenreSeries, ArrayList<Serie> series){
        ObservableList<String> aux = FXCollections.observableArrayList();
        for(Serie serie: series){
            aux.add(serie.getName());
        }
        return aux;
    }

    public ObservableList<String> allSeriesAvailable(ObservableList<String> currentGenreSeries, ArrayList<Serie> allSeries){
        ObservableList<String> series = FXCollections.observableArrayList();
        for(Serie serie: allSeries){series.add(serie.getName());}

        for(String serie: currentGenreSeries){
            series.remove(serie);
        }
        return series;
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
