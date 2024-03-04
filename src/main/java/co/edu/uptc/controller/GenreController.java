package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Genre;

public class GenreController {
    ArrayList<Genre> genres = new ArrayList<>();

    public Genre getGenre(String name){
        for(Genre g: genres){
            if(g.getName().equals(name)){
                return g;
            }
        }
        return null;
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