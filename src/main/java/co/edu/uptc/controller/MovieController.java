package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MovieController {

    
    public int verifyMovieInputs(TextField nameField, Label nameLabel,
                            TextField descriptionField, Label descriptionLabel,
                            TextField directorField, Label directorLabel,
                            TextField movieLinkField, Label movieLinkLabel,
                            TextField movieCoverField, Label movieCoverLabel,
                            TextField studioField, Label studioLabel,
                            TextField budgetField, Label budgetLabel,
                            TextField revenueField, Label revenueLabel, 
                            ComboBox<String> allGenres){
        int aux = 0;
            
        if(nameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            
            nameLabel.setTextFill(Color.web("#052659"));
            aux++;

        }else{
            nameField.setOpacity(40);
            nameField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {nameField.setStyle("-fx-background-color: #ffffff;");nameField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            nameLabel.setTextFill(Color.RED);
        }
        if(descriptionField.getText().length() > 10){
            descriptionLabel.setTextFill(Color.web("#052659"));
            aux++;
        }else{
            descriptionField.setOpacity(40);
            descriptionField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {descriptionField.setStyle("-fx-background-color: #ffffff;");descriptionField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            descriptionLabel.setTextFill(Color.RED);
        }
        if(directorField.getText().matches("^[a-zA-Z\\s]+$")){
            directorLabel.setTextFill(Color.web("#052659"));
            aux++;
        }else{
            directorField.setOpacity(40);
            directorField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {directorField.setStyle("-fx-background-color: #ffffff;");directorField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            directorLabel.setTextFill(Color.RED);
        }
        if(studioField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            studioLabel.setTextFill(Color.web("#052659"));
            aux++;

        }else{
            studioField.setOpacity(40);
            studioField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {studioField.setStyle("-fx-background-color: #ffffff;");studioField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            studioLabel.setTextFill(Color.RED);
        }
        try{
            Double.parseDouble(budgetField.getText());
            budgetLabel.setTextFill(Color.web("#052659"));
            aux++;
        }catch(Exception e){
            budgetField.setOpacity(40);
            budgetField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {budgetField.setStyle("-fx-background-color: #ffffff;");budgetField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            budgetLabel.setTextFill(Color.RED);
        }
        try{
            Double.parseDouble(revenueField.getText());
            revenueLabel.setTextFill(Color.web("#052659"));
            aux++;
        }catch(Exception e){
            revenueField.setOpacity(40);
            revenueField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {revenueField.setStyle("-fx-background-color: #ffffff;");revenueField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            revenueLabel.setTextFill(Color.RED);
        }
        if(movieLinkField.getText().startsWith("https://www.youtube.com/watch?v=")){
            movieLinkLabel.setTextFill(Color.web("#052659"));
            aux++;
        }else{
            movieLinkField.setOpacity(40);
            movieLinkField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {movieLinkField.setStyle("-fx-background-color: #ffffff;");movieLinkField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            movieLinkLabel.setTextFill(Color.RED);
        }
        if(movieCoverField.getText().startsWith("https://")){
            movieCoverLabel.setTextFill(Color.web("#052659"));
            aux++;
        }else{
            movieCoverField.setOpacity(40);
            movieCoverField.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {movieCoverField.setStyle("-fx-background-color: #ffffff;");movieCoverField.setOpacity(100);})
            );
            effect.setCycleCount(1);
            effect.play();
            movieCoverLabel.setTextFill(Color.RED);
        }
        if(allGenres.getSelectionModel().getSelectedItem() != null){
            allGenres.setBackground(Background.fill(Color.WHITE));
            aux++;
        }else{
            allGenres.setStyle("-fx-background-color: #ff7a7a;");
            Timeline effect = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {allGenres.setStyle("-fx-background-color: #ffffff;");})
            );
            effect.setCycleCount(1);
            effect.play();
            
        }
        return aux;
    }

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