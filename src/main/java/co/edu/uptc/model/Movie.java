package co.edu.uptc.model;

public class Movie extends MultimediaContent {
    private String studio;
    private double budget;
    private double revenue;

    //Edit soon
    public Movie(int id, String name, String director, String description, String genre, String imageLocalPathURL, String videoURL,
            String coverURL, String studio, double budget, double revenue) {
        super(id, name, director, description, genre, imageLocalPathURL, videoURL, coverURL);
        this.studio = studio;
        this.budget = budget;
        this.revenue = revenue;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

}
