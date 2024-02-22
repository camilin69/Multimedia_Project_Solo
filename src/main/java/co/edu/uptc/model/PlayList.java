package co.edu.uptc.model;

import java.util.ArrayList;

public class PlayList {
    private String name;
    private String backgroundCover;
    private String textCover;
    private ArrayList<Movie> movies;
    private ArrayList<Serie> series;

    
    
    public PlayList(){}
    
    public PlayList(String name) {
        this.name = name;
        movies = new ArrayList<>();
        series = new ArrayList<>();
    }

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public void removeMovie(Movie movie){
        for(Movie m : movies){
            if(m == null){break;}
            if(m.getId() == movie.getId()){
                movies.remove(m);
                break;
            }
        }
    }
    public ArrayList<Serie> getSeries() {
        return series;
    }
    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
    public void addSerie(Serie serie){
        series.add(serie);
    }
    public void removeSerie(Serie serie){
        for(Serie s : series){
            if(s == null){break;}
            if(s.getId() == serie.getId()){
                series.remove(s);
                break;
            }
        }
    }
    
    public String getBackgroundCover() {
        return backgroundCover;
    }

    public void setBackgroundCover(String backgroundCover) {
        this.backgroundCover = backgroundCover;
    }

    public String getTextCover() {
        return textCover;
    }

    public void setTextCover(String textCover) {
        this.textCover = textCover;
    }
    
    
    
}