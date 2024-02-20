package co.edu.uptc.model;

import java.util.ArrayList;


public class Season {

    private String seasonName;
    private ArrayList<MultimediaContent> episodes;
    private String backgroundCover;
    private String textCover;

    public Season(String seasonName) {
        this.seasonName = seasonName;
        episodes = new ArrayList<>();
    }

    public ArrayList<MultimediaContent> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<MultimediaContent> episodes) {
        this.episodes = episodes;
    }

    public void addEpisode(MultimediaContent episodes) {
        this.episodes.add(episodes);
    }

    public void removeEpisode(MultimediaContent episodes) {
        this.episodes.remove(episodes);
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
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
