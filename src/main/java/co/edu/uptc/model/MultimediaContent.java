package co.edu.uptc.model;

public class MultimediaContent {
    private int id;
    private String name;
    private String director;
    private String description;
    private String imageLocalPathURL;
    private String videoURL;
    private String coverURL;
    private String genre;
    
    //Serie
    public MultimediaContent(int id, String name, String director, String description,String coverURL, String imageLocalPathURL) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.description = description;
        this.coverURL = coverURL;
        this.imageLocalPathURL = imageLocalPathURL;
    }

    //Episodes
    public MultimediaContent(String name, String description, String videoURL, String coverURL, String imageLocalPathURL) {
        this.name = name;
        this.description = description;
        this.videoURL = videoURL;
        this.coverURL = coverURL;
        this.imageLocalPathURL = imageLocalPathURL;
    }

    //Movie
    public MultimediaContent(int id, String name, String director, String description,
                                    String imageLocalPathURL, String videoURL,String coverURL) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.description = description;
        this.imageLocalPathURL = imageLocalPathURL;
        this.videoURL = videoURL;
        this.coverURL = coverURL;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageLocalPathURL() {
        return imageLocalPathURL;
    }

    public void setImageLocalPathURL(String imageLocalPathURL) {
        this.imageLocalPathURL = imageLocalPathURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    
}
