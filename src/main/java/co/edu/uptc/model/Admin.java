package co.edu.uptc.model;

import java.util.ArrayList;

public class Admin extends Person{


    private ArrayList<Movie> movies;
    private ArrayList<Serie> series;
    
    public Admin(String firstName, String lastName, int id, String email, String password) {
        super(firstName, lastName, id, email, password);
        movies = new ArrayList<>();
        series = new ArrayList<>();
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
        movies.remove(movie);
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
        series.remove(serie);
    }

    public boolean couldLogIn(String email) {
        if(email.contains("@")){
            String[] emailArray = email.split("@");
            if(emailArray.length == 2){
                if(emailArray[0].equals(this.getFirstName() + this.getId())){
                    if(emailArray[1].equals("uptc.admin.co")){
                        if(this.getEmail().equals(email)){
                            return true;
                        }
                    }
                }
            }
        }
        
        
        return false;
    }
   
    

}
