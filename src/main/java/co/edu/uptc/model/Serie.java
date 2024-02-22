package co.edu.uptc.model;


import java.util.ArrayList;

public class Serie extends MultimediaContent {
    private ArrayList<Season> seasons;
    
    public Serie(int id, String name, String director, String description,String genre, String imageLocalPathURL, String coverURL,
            ArrayList<Season> seasons) {
        super(id, name, director, description, genre, coverURL, imageLocalPathURL);
        this.seasons = seasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public void removeSeasons(Season season) {
        this.seasons.remove(season);
    }

}
