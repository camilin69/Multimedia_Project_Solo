package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Genre;
import co.edu.uptc.model.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GenreController {
    ArrayList<Genre> genres = new ArrayList<>();

    public void createGenre(String name){
        Genre newGenre = new Genre(name);
        genres.add(newGenre);
    }

    public int verifyInputs(TextField genreNameField, Label genreNameLabel){
        int aux = 0;

        if(genreNameField.getText().matches("^[a-zA-Z\\s]+$")){
            aux++;
            genreNameLabel.setTextFill(Color.web("#021024"));
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
    public Genre getGenre(String genreName){
        return genres.stream().filter(g -> g.getName().equals(genreName)).findFirst().get();
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
