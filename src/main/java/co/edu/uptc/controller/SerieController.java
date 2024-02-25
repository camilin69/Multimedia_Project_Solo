package co.edu.uptc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SerieController {
    

    public int verifySerieInputs(TextField serieNameField, Label serieNameLabel,
                                  TextField serieDescriptionField, Label serieDescriptionLabel,
                                  TextField serieDirectorField, Label serieDirectorLabel,
                                  TextField serieCoverField, Label serieCoverLabel,
                                  ComboBox<String> allGenres){

        int aux = 0;

        if(serieNameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            serieNameLabel.setTextFill(Color.web("#052659"));
            aux++;
        }else{
            failAnimation(serieNameField);
            serieNameLabel.setTextFill(Color.web("#ff4848"));
        }
        if(serieDescriptionField.getText().length() < 10){
            serieDescriptionLabel.setText("u think thats descriptive?");
            failAnimation(serieDescriptionField);
            serieDescriptionLabel.setTextFill(Color.web("#ff4848"));

        }else{
            aux++;
            serieDescriptionLabel.setTextFill(Color.web("#052659"));
        }
        if(serieDirectorField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            serieDirectorLabel.setTextFill(Color.web("#052659"));
            aux++;
        }else{
            failAnimation(serieDirectorField);
            serieDirectorLabel.setTextFill(Color.web("#ff4848"));
        }
        if(serieCoverField.getText().startsWith("https://")){
            serieCoverLabel.setTextFill(Color.web("#052659"));
            aux++;
        }else{
            failAnimation(serieCoverField);
            serieCoverLabel.setTextFill(Color.web("#ff4848"));
        }
        if(allGenres.getSelectionModel().getSelectedItem() != null){
            aux++;
        }else{
            failAnimation(allGenres);
            allGenres.setStyle("-fx-background-color: red;");
        }
        return aux;
    }

    public int verifySeasonInput(TextField seasonNameField, Label seasonNameLabel, ArrayList<Season> currentSeasons){
        int aux = 0;
        if(seasonNameField.getText().matches("^[0-9\\s]+$")){
            aux++;
            seasonNameLabel.setTextFill(Color.web("#021024"));
            if(!seasonFound(currentSeasons, seasonNameField.getText())){
                aux++;
            }else{
                seasonNameLabel.setText("Seasons can not be repeated");
                failAnimation(seasonNameField);

                seasonNameLabel.setTextFill(Color.web("#ff4848"));
            }
        }else{
            seasonNameLabel.setText("Only digit a number");
            failAnimation(seasonNameField);
            seasonNameLabel.setTextFill(Color.web("#ff4848"));
        }
        return aux;
    }

    

    public void seriesAvailablesNamesComboBox(ComboBox<String> allSeriesAvailables, ArrayList<Serie> series){
        ObservableList<String> aux = FXCollections.observableArrayList();
    
        for(Serie serie: series){
            aux.add(serie.getName());
        }

        allSeriesAvailables.setItems(aux);
    }

    public void addSeriesAvailablesNamesComboBox(ComboBox<String> allSeriesAvailables, String toAdd){
        ObservableList<String> aux = allSeriesAvailables.getItems();
        aux.add(toAdd);
        allSeriesAvailables.setItems(aux);
    }

    public void removeSeriesAvailablesNamesComboBox(ComboBox<String> allSeriesAvailables, String toRemove){
        ObservableList<String> aux = allSeriesAvailables.getItems();
        aux.remove(toRemove);
        allSeriesAvailables.setItems(aux);
    }

    public void addCurrentSeriesComboBox(ComboBox<String> currentSeries, String toAdd){
        ObservableList<String> aux = currentSeries.getItems();
        aux.add(toAdd);
        currentSeries.setItems(aux);
    }

    public void removeCurrentSeriesComboBox(ComboBox<String> currentSeries, String toRemove){
        ObservableList<String> aux = currentSeries.getItems();
        aux.remove(toRemove);
        currentSeries.setItems(aux);
    }

    public Serie currentSerie(ArrayList<Serie> series, String serieName){
        for(Serie serie: series){
            if(serie.getName().equals(serieName)){
                return serie;
            }
        }
        return null;
    }


    public void updateSeasonComboBox(ComboBox<String> allSeasons, ArrayList<Season> currentSeasons){
        ObservableList<String> aux = FXCollections.observableArrayList();

        for(Season season: currentSeasons){
            aux.add(season.getSeasonName());
        }

        allSeasons.setItems(aux);
    }

    public Season currentSeason(ArrayList<Season> currentSeasons, String seasonName){
        for(Season season: currentSeasons){
            if(season.getSeasonName().equals(seasonName)){
                return season;
            }
        }
        return null;
    }

    public boolean seasonFound(ArrayList<Season> currentSeasons, String seasonName){
        for(Season season: currentSeasons){
            if(season.getSeasonName().equals(seasonName.trim())){
                return true;
            }
        }
        return false;
    }

    public int verifyEpisodeInputs(TextField episodeNameField, Label episodeNameLabel,
                                  TextField episodeDescriptionField,Label episodeDescriptionLabel,
                                  TextField episodeVideoField, Label episodeVideoLabel,
                                  TextField episodeCoverField, Label episodeCoverLabel,
                                  Season currentSeason){

        int aux = 0;

        if(episodeNameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            episodeNameLabel.setTextFill(Color.web("#021024"));
            aux++;
            if(!episodeFound(currentSeason, episodeNameField.getText())){
                aux++;
            }else{
                episodeNameLabel.setText("Episode Name can not be repeated");
                failAnimation(episodeNameField);
                episodeNameLabel.setTextFill(Color.web("#ff4848"));
            }
        }else{
            failAnimation(episodeNameField);
            episodeNameLabel.setText("Episode Name only contains characters and numbers");
            episodeNameLabel.setTextFill(Color.web("#ff4848"));
        }
        
        if(episodeDescriptionField.getText().length() < 10){
            failAnimation(episodeDescriptionField);
            episodeDescriptionLabel.setText("u think thats descriptive?");
            episodeDescriptionLabel.setTextFill(Color.web("#ff4848"));
        }else{
            aux++;
            episodeDescriptionLabel.setTextFill(Color.web("#021024"));
        }
        if(episodeVideoField.getText().startsWith("https://www.youtube.com/")){
            episodeVideoLabel.setTextFill(Color.web("#021024"));
            aux++;
        }else{
            failAnimation(episodeVideoField);
            episodeVideoLabel.setTextFill(Color.web("#ff4848"));
        }
        if(episodeCoverField.getText().startsWith("https://")){
            episodeCoverLabel.setTextFill(Color.web("#021024"));
            aux++;
        }else{
            failAnimation(episodeCoverField);
            episodeCoverLabel.setTextFill(Color.web("#ff4848"));
        }
        return aux;
    }

    public int verifyEpisodeInputsToEdit(TextField episodeNameField, Label episodeNameLabel,
                                  TextField episodeDescriptionField, Label episodeDescriptionLabel,
                                  TextField episodeVideoField, Label episodeVideoLabel,
                                  TextField episodeCoverField, Label episodeCoverLabel,
                                  Season currentSeason, String oldName){

        int aux = 0;

        if(episodeNameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            episodeNameLabel.setTextFill(Color.web("#021024"));
            aux++;
            if(oldName.equals(episodeNameField.getText())){
                aux++;
            }else{
                if(!episodeFound(currentSeason, episodeNameField.getText())){
                    aux++;
                }else{
                    failAnimation(episodeNameField);
                    episodeNameLabel.setText("Episode Name can not be repeated");
                    episodeNameLabel.setTextFill(Color.web("#ff4848"));
                }
            }
            
        }else{
            failAnimation(episodeNameField);
            episodeNameLabel.setText("Episode Name only contains characters and numbers");
            episodeNameLabel.setTextFill(Color.web("#ff4848"));
        }
        if(episodeDescriptionField.getText().length() < 10){
            failAnimation(episodeDescriptionField);
            episodeDescriptionLabel.setText("u think thats descriptive?");
            episodeDescriptionLabel.setTextFill(Color.web("#ff4848"));
        }else{
            aux++;
            episodeDescriptionLabel.setTextFill(Color.web("#021024"));
        }
        if(episodeVideoField.getText().startsWith("https://www.youtube.com/")){
            episodeVideoLabel.setTextFill(Color.web("#021024"));
            aux++;
        }else{
            failAnimation(episodeVideoField);
            episodeVideoLabel.setTextFill(Color.web("#ff4848"));
        }
        if(episodeCoverField.getText().startsWith("https://")){
            episodeCoverLabel.setTextFill(Color.web("#021024"));
            aux++;
        }else{
            failAnimation(episodeCoverField);
            episodeCoverLabel.setTextFill(Color.web("#ff4848"));
        }
        return aux;
    }

    public void failAnimation(TextField txt){
        txt.setOpacity(40);
        txt.setStyle("-fx-background-color: #ff4848");
        Timeline effect = new Timeline(
            new KeyFrame(Duration.seconds(2), e -> {txt.setStyle("-fx-background-color: #ffffff");txt.setOpacity(100);})
        );
        effect.setCycleCount(1);
        effect.play();
    }

    public void failAnimation(ComboBox<String> box){
        box.setOpacity(40);
        box.setStyle("-fx-background-color: #ff4848");
        Timeline effect = new Timeline(
            new KeyFrame(Duration.seconds(2), e -> {box.setStyle("-fx-background-color: #ffffff");box.setOpacity(100);})
        );
        effect.setCycleCount(1);
        effect.play();
    }

    public boolean episodeFound(Season currentSeason, String episodeName){
        for(MultimediaContent episode: currentSeason.getEpisodes()){
            if(episode.getName().equals(episodeName)){
                return true;
            }
        }
        return false;
    }

    public MultimediaContent currentEpisode(Season currentSeason, String episodeName){
        for(MultimediaContent episode: currentSeason.getEpisodes()){
            if(episode.getName().equals(episodeName)){
                return episode;
            }

        }
        return null;
    }

    public void updateEpisodeComboBox(ComboBox<String> allEpisodes, Season currentSeason){
        ObservableList<String> aux = FXCollections.observableArrayList();

        for(MultimediaContent episode : currentSeason.getEpisodes()){
            aux.add(episode.getName());
        }

        allEpisodes.setItems(aux);
    }

    public List<String> episodeItemsComboBox(ComboBox<String> allEpisodes, Season currentSeason){
        List<String> aux = FXCollections.observableArrayList();

        for(MultimediaContent episode : currentSeason.getEpisodes()){
            aux.add(episode.getName());
        }

        return aux;
    }

    public void removeEpisodeData(MultimediaContent episode){
        File oldVideo = new File(episode.getImageLocalPathURL().replace(".png", ".mp4"));
        oldVideo.delete();
        File oldImage = new File(episode.getImageLocalPathURL());
        oldImage.delete();
    }

    public void saveOldSeasons(ArrayList<Season> oldSeasons, ArrayList<Season> currentSeasons){
        for(Season season : currentSeasons){
            Season newSeason = new Season(season.getSeasonName());
            for(MultimediaContent episode : season.getEpisodes()){
                MultimediaContent newEpisode = new MultimediaContent(
                    episode.getName(), episode.getDescription(), episode.getVideoURL(),
                     episode.getCoverURL(), episode.getImageLocalPathURL());
                newSeason.addEpisode(newEpisode);
            }
            oldSeasons.add(newSeason);
        }

    }
}
