package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.UserRegistered;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PlayListController {

    public void createPlaylist(String name, ArrayList<Movie> moviesToAdd, ArrayList<Serie> seriesToAdd,
                                 String textCover, String backgroundCover, UserRegistered currentUser){
        PlayList newPlaylist = new PlayList(name);
        newPlaylist.setMovies(moviesToAdd);
        newPlaylist.setSeries(seriesToAdd);
        newPlaylist.setBackgroundCover(backgroundCover);
        newPlaylist.setTextCover(textCover);
        currentUser.addplayList(newPlaylist);                    
    }

    public int verifyInputs(TextField playlistNameField, Label playlistNameLabel, UserRegistered currentUser){
        int aux = 0;
        if(!playlistNameField.getText().isEmpty()){
            aux++;
            playlistNameLabel.setTextFill(Color.web("#021024"));
        }else{
            playlistNameLabel.setText("Name can not be null");
            playlistNameLabel.setTextFill(Color.RED);
        }
        if(playlistNameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            aux++;
            playlistNameLabel.setTextFill(Color.web("#021024"));

        }else{
            playlistNameLabel.setText("Name does not contains special characters");
            playlistNameLabel.setTextFill(Color.RED);
        }
        if(!playlistFound(currentUser.getplayList(), playlistNameField.getText())){
            aux++;
            playlistNameLabel.setTextFill(Color.web("#021024"));
        }else{
            playlistNameLabel.setText("Name can not be repeated");
            playlistNameLabel.setTextFill(Color.RED);
        }

        return aux;
    }

    public int verifyInputsToEdit(TextField playlistNameField, Label playlistNameLabel, UserRegistered currentUser, PlayList oldPlaylist){
        int aux = 0;
        if(!playlistNameField.getText().isEmpty()){
            aux++;
            playlistNameLabel.setTextFill(Color.web("#021024"));
        }else{
            playlistNameLabel.setText("Name can not be null");
            playlistNameLabel.setTextFill(Color.RED);
        }
        if(playlistNameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            aux++;
            playlistNameLabel.setTextFill(Color.web("#021024"));

        }else{
            playlistNameLabel.setText("Name does not contains special characters");
            playlistNameLabel.setTextFill(Color.RED);
        }
        if(playlistNameField.getText().equals(oldPlaylist.getName())){
            aux++;
            playlistNameLabel.setTextFill(Color.web("#021024"));
        }else{
            if(!playlistFound(currentUser.getplayList(), playlistNameField.getText())){
                aux++;
                playlistNameLabel.setTextFill(Color.web("#3a61e0"));
            }else{
                playlistNameLabel.setText("Name can not be repeated");
                playlistNameLabel.setTextFill(Color.RED);
            }
        }
        
        return aux;
    }
    
    public ArrayList<Movie> setMovies(ObservableList<String> playlistMovies, ArrayList<Movie> allMovies){
        ArrayList<Movie> movies = new ArrayList<>();
        MovieController mc = new MovieController();

        for(String i : playlistMovies){
            Movie newMovie = mc.currentMovie(allMovies, i);
            movies.add(newMovie);
        }
        return movies;
    }

    public ArrayList<Serie> setSeries(ObservableList<String> playlistSeries, ArrayList<Serie> allSeries){
        ArrayList<Serie> series = new ArrayList<>();
        SerieController sc = new SerieController();

        for(String i : playlistSeries){
            Serie newSerie = sc.currentSerie(allSeries, i);
            series.add(newSerie);
        }
        return series;
    }


    public ObservableList<String> currentPlaylistMovies(ComboBox<String> currentPlaylistMovies, ArrayList<Movie> movies){
        ObservableList<String> aux = FXCollections.observableArrayList();
        for(Movie movie: movies){
            aux.add(movie.getName());
        }
        return aux;
    }

    public ObservableList<String> allMoviesAvailable(ObservableList<String> currentPlaylistMovies, ArrayList<Movie> allMovies){
        ObservableList<String> movies = FXCollections.observableArrayList();
        for(Movie movie: allMovies){movies.add(movie.getName());}

        for(String movie: currentPlaylistMovies){
            movies.remove(movie);
        }
        return movies;
    }

    public ObservableList<String> currentPlaylistSeries(ComboBox<String> currentPlaylistSeries, ArrayList<Serie> series){
        ObservableList<String> aux = FXCollections.observableArrayList();
        for(Serie serie: series){
            aux.add(serie.getName());
        }
        return aux;
    }

    public ObservableList<String> allSeriesAvailable(ObservableList<String> currentPlaylistSeries, ArrayList<Serie> allSeries){
        ObservableList<String> series = FXCollections.observableArrayList();
        for(Serie serie: allSeries){series.add(serie.getName());}

        for(String serie: currentPlaylistSeries){
            series.remove(serie);
        }
        return series;
    }

    public boolean playlistFound(ArrayList<PlayList> playlists, String name){
        for(PlayList playlist: playlists){
            if(playlist.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
