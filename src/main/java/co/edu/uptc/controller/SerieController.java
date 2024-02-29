package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SerieController {
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
}