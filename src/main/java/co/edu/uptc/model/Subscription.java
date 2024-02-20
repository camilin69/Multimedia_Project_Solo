package co.edu.uptc.model;

public class Subscription {
    private String name;
    private int duration;
    private String description;
    private double price;
    private String coverURL;
    private String imageLocalPathURL;

    
    //General 
    public Subscription(String name, int duration, String description, double price, String coverURL,
            String imageLocalPathURL) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.price = price;
        this.coverURL = coverURL;
        this.imageLocalPathURL = imageLocalPathURL;
    }
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCoverURL() {
        return coverURL;
    }
    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
    public String getImageLocalPathURL() {
        return imageLocalPathURL;
    }
    public void setImageLocalPathURL(String imageLocalPathURL) {
        this.imageLocalPathURL = imageLocalPathURL;
    }

    
    
}