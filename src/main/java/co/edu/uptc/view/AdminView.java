package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.GenreController;
import co.edu.uptc.controller.MovieController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Genre;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.UserRegistered;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Node;

public class AdminView {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private AnchorPane emptyPane;

    @FXML
    private AnchorPane welcomePane;
   
    
    @FXML
    private ScrollPane scrollMovies;
    

    
    @FXML
    private AnchorPane titleAnchorPane;

    @FXML
    private Label titleText;


    @FXML
    private GridPane multimediaContentGrid;

    

    @FXML
    private ComboBox<String> allGenres;

    @FXML
    private ComboBox<String> allGenresSeries;

    //--------------------------------------------------------------------------MOVIES ATRIBUTES--------------------------------------------------------------------------//
    @FXML
    private AnchorPane addMoviePane;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField descriptionField;

    @FXML
    private Label descriptionLabel;

    @FXML
    private TextField directorField;

    @FXML
    private Label directorLabel;

    @FXML
    private TextField studioField;

    @FXML
    private Label studioLabel;

    @FXML
    private TextField budgetField;

    @FXML
    private Label budgetLabel;

    @FXML
    private TextField revenueField;

    @FXML
    private Label revenueLabel;

    @FXML
    private TextField movieLinkField;

    @FXML
    private Label movieLinkLabel;

    @FXML
    private TextField movieCoverField;

    @FXML
    private Label movieCoverLabel;

    @FXML
    private Button createMovieButton;


    @FXML
    private VBox movieOptions;



    //---------------------------------------------------------------------SERIES ATRIBUTES-------------------------------------------------------------//
    @FXML
    private AnchorPane addSeriePane;
    
    //Inputs and labels
    @FXML
    private TextField serieNameField;

    @FXML
    private Label serieNameLabel;

    @FXML
    private TextField serieDescriptionField;

    @FXML
    private Label serieDescriptionLabel;

    @FXML
    private TextField serieDirectorField;

    @FXML
    private Label serieDirectorLabel;

    @FXML
    private TextField serieCoverField;

    @FXML
    private Label serieCoverLabel;

    @FXML
    private TextField seasonNameField;

    @FXML
    private Label seasonNameLabel;

    @FXML
    private TextField episodeNameField;

    @FXML
    private Label episodeNameLabel;

    @FXML
    private TextField episodeDescriptionField;

    @FXML
    private Label episodeDescriptionLabel;

    @FXML
    private TextField episodeVideoField;

    @FXML
    private Label episodeVideoLabel;

    @FXML
    private TextField episodeCoverField;

    @FXML
    private Label episodeCoverLabel;

    //Season cover objects

    @FXML
    private AnchorPane seasonCoverPane;

    @FXML
    private Label seasonTextCover;

    @FXML
    private Rectangle seasonCover;

    @FXML
    private ColorPicker textColor;

    @FXML 
    private ColorPicker backgroundColor;

    //Images and Combobox
    @FXML
    private ComboBox<String> allEpisodes;

    @FXML
    private ComboBox<String> allSeasons;

    @FXML
    private VBox addEpisode;

    @FXML
    private VBox addSeason;

    @FXML
    private ImageView activateEditSeason;

    @FXML
    private ImageView activateEditEpisode;

    @FXML
    private VBox editSeason;

    @FXML
    private VBox editEpisode;

    @FXML
    private VBox deleteEpisode;

    @FXML
    private VBox deleteSeason;

    @FXML
    private ImageView cancelEditSeason;

    @FXML
    private ImageView cancelEditEpisode;

    @FXML
    private Button createSerie;

    @FXML
    private Button returnButton;

    @FXML
    private VBox serieOptions;

    //---------------------------------------------------------------------SUBCRIPTIONS ATRIBUTES-------------------------------------------------------------//
    @FXML
    private AnchorPane addSubscriptionPane;

    @FXML
    private VBox subsOptions;

    @FXML
    private TextField subscriptionNameField;

    @FXML
    private Label subscriptionNameLabel;

    @FXML
    private TextField subscriptionDescriptionField;

    @FXML
    private Label subscriptionDescriptionLabel;

    @FXML
    private TextField subscriptionDurationField;

    @FXML
    private Label subscriptionDurationLabel;

    @FXML
    private TextField subscriptionPriceField;

    @FXML
    private Label subscriptionPriceLabel;

    @FXML
    private TextField subscriptionCoverField;

    @FXML
    private Label subscriptionCoverLabel;

    @FXML
    private Button createSubscriptionButton;

    private String selectedSeasonItem = null;

    private String selectedEpisodeItem = null;

    private String selectedGenreItem = null;

    private int serieId;

    private ArrayList<Season> currentSeasons = new ArrayList<>();

    ArrayList<Season> oldSeasons = new ArrayList<>();

    private Season currentSeason;

    private MultimediaContent currentEpisode;

    

    private AdminController adminC = new AdminController();
    private MovieController movieC = new MovieController();
    private UserController userC = new UserController();
    private SerieController serieC = new SerieController();
    private GenreController genreC = new GenreController();
    private SubscriptionController subC = new SubscriptionController();

    //------------------------------------------------------------MOVIES------------------------------------------------------//

    @FXML
    void seeAvailableMovies(ActionEvent event){
        welcomePane.setVisible(false);
        emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a movie to see");
        addMoviePane.setVisible(false);

        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/movies.json"));
        if(adminC.getAdmin().getMovies().isEmpty()){
            emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Movie movie : adminC.getAdmin().getMovies()) {
            VBox imageView = createMovieImageView(movie);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    

    public VBox createMovieImageView(Movie movie){
        ImageView newMovie = new ImageView();
        newMovie.setImage(new Image(new File(movie.getImageLocalPathURL()).toURI().toString()));
        newMovie.setId(String.valueOf(movie.getId()));
        newMovie.setFitHeight(250);
        newMovie.setFitWidth(250);
        newMovie.setCursor(Cursor.HAND);
        newMovie.setOnMouseClicked(e -> {
            player(e, movie.getImageLocalPathURL().replace(".png", ".mp4"));
        });
        Label movieLabel = new Label(movie.getName());
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(newMovie, movieLabel);
        return vbox;
    }

    



    @FXML
    void addMovie(ActionEvent event){
        welcomePane.setVisible(false);
        scrollMovies.setVisible(false);
        titleAnchorPane.setVisible(false);
        emptyPane.setVisible(false);
        addMoviePane.setVisible(true);
        genreC.setGenres(adminC.loadGenresFromJson());
        genreC.updateGenreComboBox(allGenres, genreC.getGenres());
        allGenres.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleAllGenresComboBoxSelection(newValue);
        });
        //Create Movie Button
        //createMovieButton.setText("Create Movie");
        createMovieButton.setOnAction(e -> {
            int aux = movieC.verifyMovieInputs(nameField, nameLabel, descriptionField, descriptionLabel,
             directorField, directorLabel, movieLinkField, movieLinkLabel, movieCoverField, movieCoverLabel,
             studioField, studioLabel, budgetField, budgetLabel, revenueField, revenueLabel, allGenres);
            

            if(aux == 9){
                int id = adminC.assignidMovie();
            
                try {
                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            String videoPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/" + id + ".mp4";
                            adminC.downloadVideo(videoPath, movieLinkField.getText());
                            return null;
                        }
                    };
                    new Thread(task).start();
                    showProgressDialog(task);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                String coverPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/"+ id + ".png";
                try {
                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                           
                            adminC.downloadCover(coverPath, movieCoverField.getText());
                            return null;
                        }
                    };
                    new Thread(task).start();
                    showProgressDialog(task);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                
                //Create new Movie
                Movie movie = new Movie(id, nameField.getText(), descriptionField.getText(), directorField.getText(),
                allGenres.getSelectionModel().getSelectedItem(), coverPath, movieLinkField.getText(),movieCoverField.getText(), studioField.getText(),
                Double.parseDouble(budgetField.getText()), Double.parseDouble(revenueField.getText()));
                adminC.getAdmin().setMovies(adminC.loadMoviesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/movies.json"));
                adminC.getAdmin().addMovie(movie);
                adminC.saveMoviesToJson();

                genreC.getGenres().forEach(g -> {
                    if(g.getName().equals(allGenres.getSelectionModel().getSelectedItem())){
                        g.addMovie(movie);
                    }
                });        

                adminC.saveGenresToJson(genreC.getGenres());
            
                try {
                    adminMenu(event);
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        });
    }



    @FXML
    void moviesToEdit(ActionEvent event){
        welcomePane.setVisible(false);
        emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        
        titleText.setText("Select a movie to edit");

        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/movies.json"));
        if(adminC.getAdmin().getMovies().isEmpty()){
            emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Movie movie : adminC.getAdmin().getMovies()) {
            VBox imageView = createMovieImageViewToEdit(movie);
            imageView.setOnMouseClicked(e -> {
                editMovie(e, movie);
            });
            imageView.setCursor(Cursor.HAND);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        

    }

    @FXML
    void editMovie(MouseEvent event, Movie movie){
        genreC.setGenres(adminC.loadGenresFromJson());
        allGenres.getSelectionModel().select(movie.getGenre());
        genreC.updateGenreComboBox(allGenres, genreC.getGenres());
        allGenres.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleAllGenresComboBoxSelection(newValue);
        });
        welcomePane.setVisible(false);
        scrollMovies.setVisible(false);
        titleAnchorPane.setVisible(false);
        emptyPane.setVisible(false);
        addMoviePane.setVisible(true);
        String oldName = movie.getName();
        String oldDescription = movie.getDescription();
        String oldDirector = movie.getDirector();
        String oldStudio = movie.getStudio();
        double oldBudget = movie.getBudget();
        double oldRevenue = movie.getRevenue();
        String oldVideoURL = movie.getVideoURL();
        String oldCoverURL = movie.getCoverURL();

        nameField.setText(oldName);
        descriptionField.setText(oldDescription);
        directorField.setText(oldDirector);
        studioField.setText(oldStudio);
        budgetField.setText(String.valueOf(oldBudget));
        revenueField.setText(String.valueOf(oldRevenue));
        movieLinkField.setText(oldVideoURL);
        movieCoverField.setText(oldCoverURL);

        createMovieButton.setText("Save changes");
        createMovieButton.setOnAction(e -> {

            int aux = movieC.verifyMovieInputs(nameField, nameLabel, descriptionField, descriptionLabel, 
            directorField, directorLabel, movieLinkField, movieLinkLabel, movieCoverField, movieCoverLabel,
             studioField, studioLabel, budgetField, budgetLabel, revenueField, revenueLabel, allGenres);
            if(aux == 9){
                genreC.setGenres(adminC.loadGenresFromJson());
                String oldGenre = movie.getGenre();

                movie.setName(nameField.getText());
                movie.setDescription(descriptionField.getText());
                movie.setDirector(directorField.getText());
                movie.setGenre(allGenres.getSelectionModel().getSelectedItem());
                movie.setStudio(studioField.getText());
                movie.setBudget(Double.parseDouble(budgetField.getText()));
                movie.setRevenue(Double.parseDouble(revenueField.getText()));
                if(!movieLinkField.getText().equals(oldVideoURL)){
                    File oldFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/", movie.getId() + ".mp4");
                    oldFile.delete();
                    try {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                String videoPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/" + movie.getId() + ".mp4";
                                adminC.downloadVideo(videoPath, movieLinkField.getText());
                                return null;
                            }
                        };
                        new Thread(task).start();
                        showProgressDialog(task);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    
                    movie.setVideoURL(movieLinkField.getText());
                }
                if(!movieCoverField.getText().equals(oldCoverURL)){
                    File oldFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/" + movie.getId() + ".png");
                    oldFile.delete();
                    try {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                String coverPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/" + movie.getId() + ".png";
                                adminC.downloadCover(coverPath, movieCoverField.getText());
                                return null;
                            }
                        };
                        new Thread(task).start();
                        showProgressDialog(task);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    
                    movie.setCoverURL(movieCoverField.getText());
                }
                
                if(!oldGenre.equals(allGenres.getSelectionModel().getSelectedItem())){
                    genreC.getGenres().forEach(g -> {
                        if(g.getName().equals(oldGenre)){
                            g.removeMovie(movie);
                        }else if(g.getName().equals(movie.getGenre())){
                            g.addMovie(movie);
                        }
                    });
                }
                genreC.getGenres().forEach(g -> {
                    if(g.getName().equals(movie.getGenre())){
                        g.getMovies().forEach(m -> {
                            if(m.getId() == movie.getId()){
                                m.setName(movie.getName());
                                m.setDirector(movie.getDirector());
                                m.setDescription(movie.getDescription());
                                m.setGenre(movie.getGenre());
                                m.setImageLocalPathURL(movie.getImageLocalPathURL());
                                m.setVideoURL(movie.getVideoURL());
                                m.setCoverURL(movie.getCoverURL());
                                m.setBudget(movie.getBudget());
                                m.setRevenue(movie.getRevenue());
                                m.setStudio(movie.getStudio());
                            }
                        });
                    }
                });
                adminC.saveGenresToJson(genreC.getGenres());
                adminC.saveMoviesToJson();
                try {
                    adminMenu(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    
    public VBox createMovieImageViewToEdit(Movie movie){
        ImageView newMovie = new ImageView();
        newMovie.setImage(new Image(new File(movie.getImageLocalPathURL()).toURI().toString()));
        newMovie.setId(String.valueOf(movie.getId()));
        newMovie.setFitHeight(250);
        newMovie.setFitWidth(250);
        newMovie.setCursor(Cursor.HAND);
        Label movieLabel = new Label(movie.getName());
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(newMovie, movieLabel);
        return vbox;
    }



    @FXML
    void moviesToDelete(ActionEvent event){
        welcomePane.setVisible(false);
        emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a movie to delete");

        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/movies.json"));
        if(adminC.getAdmin().getMovies().isEmpty()){
            emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Movie movie : adminC.getAdmin().getMovies()) {
            VBox imageView = createMovieImageViewToDelete(movie);
            imageView.setOnMouseClicked(e -> {
                deleteMovieFunction(movie);
            });
            imageView.setCursor(Cursor.HAND);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createMovieImageViewToDelete(Movie movie){
        ImageView newMovie = new ImageView();
        newMovie.setImage(new Image(new File(movie.getImageLocalPathURL()).toURI().toString()));
        newMovie.setId(String.valueOf(movie.getId()));
        newMovie.setFitHeight(250);
        newMovie.setFitWidth(250);
        
        Label movieLabel = new Label(movie.getName());
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(newMovie, movieLabel);
        return vbox;
    }

    public void deleteMovieFunction(Movie movie){
        File videoFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/" + movie.getId() + ".mp4");
            videoFile.delete();
            File coverFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/movies/" + movie.getId() + ".png");
            coverFile.delete();
            adminC.getAdmin().removeMovie(movie);
            adminC.saveMoviesToJson();
            
            userC.setUsers(userC.loadUsersFromJson());
            genreC.setGenres(adminC.loadGenresFromJson());
            for(UserRegistered user: userC.getUsers()){
                for(PlayList playlist: user.getplayList()){
                    playlist.removeMovie(movie);
                }
            }
            for(Genre genre: genreC.getGenres()){
                genre.removeMovie(movie);
            }
            userC.saveUsersToJson();
            adminC.saveGenresToJson(genreC.getGenres());

            moviesToDelete(new ActionEvent());
    }

    @FXML
    void movieOptionsIn(MouseEvent event){
        movieOptions.setVisible(true);
    }

    @FXML
    void movieOptionsOut(MouseEvent event){
        movieOptions.setVisible(false);
    }


    @FXML
    void adminMenu(ActionEvent event) throws IOException{
        if(event.getSource() instanceof MenuItem){
            stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        }else{
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        }
        root = FXMLLoader.load(getClass().getResource("/co/edu/uptc/persistence/fxmlFiles/adminView.fxml"));
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void returnToLogin(ActionEvent event) throws IOException{
        stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/persistence/fxmlFiles/loginView.fxml"));
        root= loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();
    }
    /*-------------------------------------------------------------------------SERIES-------------------------------------------------------------------------*/

    @FXML
    void seeAvailableSeries(ActionEvent event){
        //welcomePane.setVisible(false);
        //emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a serie to see");
        //addMoviePane.setVisible(false);

        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/series.json"));
        if(adminC.getAdmin().getSeries().isEmpty()){
            //emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Serie serie : adminC.getAdmin().getSeries()) {
            VBox imageView = createSerieImageView(serie);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createSerieImageView(Serie serie){
        ImageView newSerie = new ImageView();
        newSerie.setImage(new Image(new File(serie.getImageLocalPathURL()).toURI().toString()));
        newSerie.setId(String.valueOf(serie.getId()));
        newSerie.setFitHeight(250);
        newSerie.setFitWidth(250);
        newSerie.setCursor(Cursor.HAND);
        newSerie.setOnMouseClicked(e -> {
            seeAvailableSeasons(e, serie);
        });
        Label serieName = new Label(serie.getName());
        serieName.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newSerie, serieName);
        return vbox;
    }

    void seeAvailableSeasons(MouseEvent event, Serie serie){
        
        titleText.setText("Select a season to see");
        if(serie.getSeasons().isEmpty()){
            //emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Season season : serie.getSeasons()) {
            Parent imageView = createSeasonImageView(season);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        } 
    }

    public Parent createSeasonImageView(Season season){
        Rectangle newSeasonBackground = new Rectangle(250,250);
        newSeasonBackground.setFill(Color.web(season.getBackgroundCover()));
        newSeasonBackground.setCursor(Cursor.HAND);
        Label newSeasonText = new Label(season.getSeasonName());
        newSeasonText.setTextFill(Color.web(season.getTextCover()));
        StackPane imageView = new StackPane();
        imageView.getChildren().addAll(newSeasonBackground, newSeasonText);
        imageView.setOnMouseClicked(e -> {
            seeAvailableEpisodes(e, season);
        });
        return imageView;
    }

    void seeAvailableEpisodes(MouseEvent event, Season season){
        titleText.setText("Select a episode to see");
        if(season.getEpisodes().isEmpty()){
            //emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (MultimediaContent episode : season.getEpisodes()) {
            VBox imageView = createEpisodeImageView(episode);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createEpisodeImageView(MultimediaContent episode){
        ImageView newEpisode = new ImageView();
        newEpisode.setImage(new Image(new File(episode.getImageLocalPathURL()).toURI().toString()));
        newEpisode.setId(String.valueOf(episode.getId()));
        newEpisode.setFitHeight(250);
        newEpisode.setFitWidth(250);
        newEpisode.setCursor(Cursor.HAND);
        newEpisode.setOnMouseClicked(e -> {
            player(e, episode.getImageLocalPathURL().replace(".png", ".mp4"));
        });
        Label episodeName = new Label(episode.getName());
        episodeName.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newEpisode, episodeName);
        return vbox;
        
    }

    @FXML
    void addSerie(ActionEvent e){
        //welcomePane.setVisible(false);
        scrollMovies.setVisible(false);
        titleAnchorPane.setVisible(false);
        //addMoviePane.setVisible(false);
        //emptyPane.setVisible(false);
        addSeriePane.setVisible(true);
        genreC.setGenres(adminC.loadGenresFromJson());
        genreC.updateGenreComboBox(allGenresSeries, genreC.getGenres());
        allGenresSeries.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleAllGenresComboBoxSelection(newValue);
        });
    }

    @FXML
    void addGenre(MouseEvent event){
        Stage addGenreStage = new Stage();
        addGenreStage.setTitle("Add Genre");
        addGenreStage.initStyle(StageStyle.DECORATED);
        addGenreStage.initModality(Modality.APPLICATION_MODAL);

        TextField genreNameField = new TextField();
        genreNameField.setPromptText("Genre Name");
        genreNameField.setPrefSize(200, 15);

        Label genreNameLabel = new Label("Genre only contains characteres");
        genreNameLabel.setTextFill(Color.web("#021024"));

        Button addGenre = new Button("Add");
        addGenre.setPrefSize(40, 10);
        addGenre.setCursor(Cursor.HAND);
        addGenre.setOnAction(e -> {
            if(genreC.verifyInputs(genreNameField, genreNameLabel) == 2){
                genreC.createGenre(genreNameField.getText());
                genreC.updateGenreComboBox(allGenres, genreC.getGenres());
                genreC.updateGenreComboBox(allGenresSeries, genreC.getGenres());
                adminC.saveGenresToJson(genreC.getGenres());
                addGenreStage.close();
            }
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5, 20, 5, 20));;
        root.setStyle("-fx-background-color: #021024;");
        root.getChildren().addAll(genreNameField, genreNameLabel, addGenre);
        Scene addGenreScene = new Scene(root, 380, 100);
        addGenreStage.setScene(addGenreScene);
        addGenreStage.setResizable(false);
        addGenreStage.showAndWait();
    }

    public void handleAllGenresComboBoxSelection(String newValue) {
        if (newValue != null && !newValue.equals(selectedGenreItem)) {
            System.out.println(newValue);
        }
    }

    @FXML
    void createSerie(ActionEvent event) {
        adminC.loadGenresFromJson();
        int serieVerifies = serieC.verifySerieInputs(serieNameField, serieNameLabel, serieDescriptionField, serieDescriptionLabel,
                                              serieDirectorField, serieDirectorLabel, serieCoverField, serieCoverLabel, allGenresSeries);
        if(serieVerifies == 5){
            if(serieId == 0){serieId = adminC.assignidSerie();}
            String coverPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/series/"+ serieId + serieNameField.getText() + ".png";
            try {
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        
                        adminC.downloadCover(coverPath, serieCoverField.getText());
                        return null;
                    }
                };
                new Thread(task).start();
                showProgressDialog(task);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            Serie newSerie = new Serie(serieId, serieNameField.getText(), serieDirectorField.getText(),
                                     serieDirectorField.getText(), allGenresSeries.getSelectionModel().getSelectedItem(), coverPath, serieCoverField.getText(), currentSeasons);
            adminC.getAdmin().setSeries(adminC.loadSeriesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/series.json"));
            adminC.getAdmin().addSerie(newSerie);
            
            genreC.getGenre(allGenresSeries.getSelectionModel().getSelectedItem()).addSerie(newSerie);
            adminC.saveGenresToJson(genreC.getGenres());
            adminC.saveSeriesToJson();

            for(Season season: currentSeasons){
                for(MultimediaContent episode : season.getEpisodes()){
                    try {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                adminC.downloadVideo(episode.getImageLocalPathURL().replace(".png", ".mp4"), episode.getVideoURL());
                                return null;
                            }
                        };
                        new Thread(task).start();
                        showProgressDialog(task);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    try {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                adminC.downloadCover(episode.getImageLocalPathURL(), episode.getCoverURL());
                                return null;
                            }
                        };
                        new Thread(task).start();
                        showProgressDialog(task);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    
                    
                }
            }
            try {
                adminMenu(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void seasonMenu(String action){
        Stage addSeasonStage = new Stage();
        addSeasonStage.initStyle(StageStyle.DECORATED);
        addSeasonStage.initModality(Modality.APPLICATION_MODAL);
        addSeasonStage.setTitle(action + " Season Menu");

        Label seasonAuxLabel = new Label("S:");
        seasonAuxLabel.setLayoutX(10);
        seasonAuxLabel.setLayoutY(10);
        seasonAuxLabel.setTextFill(Color.WHITE);
        seasonAuxLabel.setFont(new Font(24));
        if(action.equals("edit")){
            seasonNameField = new TextField(selectedSeasonItem);
        }else{
            seasonNameField = new TextField();
        }
        seasonNameField.setPromptText("Season Number");
        seasonNameField.setPrefSize(220, 10);
        seasonNameField.setLayoutX(30);
        seasonNameField.setLayoutY(15);

        seasonNameLabel = new Label("Season number invalid");
        seasonNameLabel.setLayoutX(30);
        seasonNameLabel.setLayoutY(40);
        seasonNameLabel.setTextFill(Color.web("#021024"));

        Button addSeason = new Button(action);
        addSeason.setPrefSize(80, 10);
        addSeason.setLayoutX(90);
        addSeason.setLayoutY(70);
        addSeason.setOnAction(e -> {
            if(seasonHandler(action)){
                addSeasonStage.close();
            }
        });

        AnchorPane addSeasonRoot = new AnchorPane();
        addSeasonRoot.setStyle("-fx-background-color: #021024;");
        addSeasonRoot.getChildren().addAll(seasonAuxLabel,seasonNameField, seasonNameLabel, addSeason);


        Scene addSeasonScene = new Scene(addSeasonRoot, 260, 110);
        addSeasonStage.setScene(addSeasonScene);
        addSeasonStage.setResizable(false);
        addSeasonStage.showAndWait();
    }

    public boolean seasonHandler(String action){
        if(action.equals("add")){
            allSeasons.setStyle(null);
            int aux = serieC.verifySeasonInput(seasonNameField, seasonNameLabel, currentSeasons);
    
            if(aux == 2){
                Season newSeason = new Season(seasonNameField.getText());
                Rectangle backgroundCover = new Rectangle(200, 200);
                backgroundCover.setFill(Color.BLACK);
                newSeason.setBackgroundCover("#000000");
                Label textCover = new Label(seasonNameField.getText());
                textCover.setTextFill(Color.WHITE);
                newSeason.setTextCover("#FFFFFF");
    
                currentSeasons.add(newSeason);
                serieC.updateSeasonComboBox(allSeasons, currentSeasons);
                allSeasons.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        handleAllSeasonsComboBoxSelection(newValue);
                    }
                });
                seasonNameField.setText("");
                return true;
            }
            return false;
        }else if(action.equals("edit")){
            int aux = serieC.verifySeasonInput(seasonNameField, seasonNameLabel, currentSeasons);
            if(aux == 2){
                currentSeason.setSeasonName(seasonNameField.getText());
                serieC.updateSeasonComboBox(allSeasons, currentSeasons);
                currentSeason.getEpisodes().forEach(e -> {
                    e.setImageLocalPathURL(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/series/"+ serieId + currentSeason.getSeasonName().replace(" ", "") + e.getName().replace(" ", "") + ".png");
                });
                editSeason.setVisible(false);
                deleteSeason.setVisible(false);
                allEpisodes.setVisible(false);
                addEpisode.setVisible(false);
                editEpisode.setVisible(false);
                deleteEpisode.setVisible(false);
                return true;
            }
            return false;
        }
        return false;
    }

    @FXML
    void addSeason(MouseEvent event) {
        seasonMenu("add");
    }

    @FXML
    void editSeason(MouseEvent event){
        seasonMenu("edit");
    }

    @FXML
    void removeSeason(MouseEvent event){
        if(allSeasons.getSelectionModel().getSelectedItem() != null){
            currentSeason = serieC.currentSeason(currentSeasons, allSeasons.getSelectionModel().getSelectedItem());
            editSeason.setVisible(false);
            deleteSeason.setVisible(false);
            allEpisodes.setVisible(false);
            addEpisode.setVisible(false);
            editEpisode.setVisible(false);
            deleteEpisode.setVisible(false);
            currentSeasons.remove(currentSeason);
            serieC.updateSeasonComboBox(allSeasons, currentSeasons);
            allEpisodes.setPromptText("Episodes");
            allEpisodes.setItems(null);
            selectedSeasonItem = null;
        }
    }

    

    public void handleAllSeasonsComboBoxSelection(String newValue) {
        if (newValue != null && !newValue.equals(selectedSeasonItem)) {
            allSeasons.setStyle(null);
            editSeason.setVisible(true);
            deleteSeason.setVisible(true);
            allEpisodes.setVisible(true);
            addEpisode.setVisible(true);
            editEpisode.setVisible(false);
            deleteEpisode.setVisible(false);
            

            currentSeason = serieC.currentSeason(currentSeasons, newValue);
            if(!currentSeason.getEpisodes().isEmpty()){
                serieC.updateEpisodeComboBox(allEpisodes, currentSeason);
            }else{
                allEpisodes.setPromptText("Episodes");
                allEpisodes.setItems(null);
            }

            selectedSeasonItem = newValue;
        }
    }

    
    
    public void episodeMenu(String action){
        Stage addEpisodeStage = new Stage();
        addEpisodeStage.initStyle(StageStyle.DECORATED);
        addEpisodeStage.initModality(Modality.APPLICATION_MODAL);
        addEpisodeStage.setResizable(false);
        addEpisodeStage.setTitle("Add Episode to Season " + selectedSeasonItem);

        episodeNameField = new TextField();
        episodeNameField.setPromptText("Episode Name");
        episodeNameField.setPrefSize(100, 10);
        episodeNameLabel = new Label("Episode Name Invalid");
        episodeNameLabel.setTextFill(Color.web("#021024"));

        episodeDescriptionField = new TextField();
        episodeDescriptionField.setPromptText("Episode Description");
        episodeDescriptionField.setPrefSize(100, 200);
        episodeDescriptionLabel = new Label("Episode Description Invalid");
        episodeDescriptionLabel.setTextFill(Color.web("#021024"));

        episodeVideoField = new TextField();
        episodeVideoField.setPromptText("Episode Video Link");
        episodeVideoField.setPrefSize(100, 10);
        episodeVideoLabel = new Label("Episode Video Link Invalid");
        episodeVideoLabel.setTextFill(Color.web("#021024"));

        episodeCoverField = new TextField();
        episodeCoverField.setPromptText("Episode Cover");
        episodeCoverField.setPrefSize(100, 10);
        episodeCoverLabel = new Label("Episode Cover Invalid");
        episodeCoverLabel.setTextFill(Color.web("#021024"));
        
        if(action.equals("edit")){
            episodeNameField.setText(currentEpisode.getName());
            episodeDescriptionField.setText(currentEpisode.getDescription());
            episodeVideoField.setText(currentEpisode.getVideoURL());
            episodeCoverField.setText(currentEpisode.getCoverURL());
        }

        Button addEpisodeButton = new Button(action);
        addEpisodeButton.setOnAction(e -> {
            if(episodeHandler(action)){
                addEpisodeStage.close();
            }
        });

        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: #021024;");
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20, 10, 20, 10));
        root.getChildren().addAll(episodeNameField,episodeNameLabel,
                                  episodeDescriptionField,episodeDescriptionLabel,
                                  episodeVideoField,episodeVideoLabel,
                                  episodeCoverField, episodeCoverLabel,
                                  addEpisodeButton);

        Scene addEpisodeScene = new Scene(root, 300, 400);

        addEpisodeStage.setScene(addEpisodeScene);
        addEpisodeStage.showAndWait();
    }

    public boolean episodeHandler(String action){
        if(action.equals("add")){
            if(allSeasons.getSelectionModel().getSelectedItem() != null){
                allSeasons.setStyle(null);
                int aux = serieC.verifyEpisodeInputs(episodeNameField, episodeNameLabel, episodeDescriptionField, episodeDescriptionLabel,
                                         episodeVideoField, episodeVideoLabel, episodeCoverField, episodeCoverLabel, currentSeason);
                if(aux == 5){
                    if(serieId == 0){serieId = adminC.assignidSerie();}
                    
    
                    String coverPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/series/"+ serieId + currentSeason.getSeasonName().replace(" ", "") + episodeNameField.getText().replace(" ", "") + ".png";
                    
                    
                    //Create new Episode
                    MultimediaContent newEpisode = new MultimediaContent(episodeNameField.getText(),
                                    episodeDescriptionField.getText(), episodeVideoField.getText().replace("\\", "/"), 
                                    episodeCoverField.getText().replace("\\", "/"), coverPath);
                    newEpisode.setId(serieId);
                    currentSeason.addEpisode(newEpisode);
    
                    serieC.updateEpisodeComboBox(allEpisodes, currentSeason);
                    allEpisodes.valueProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue != null) {
                            handleAllEpisodesComboBoxSelection(newValue);
                        }
                    });
    
                    allEpisodes.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
                        List<String> dataList = serieC.episodeItemsComboBox(allEpisodes, currentSeason);
                        if (newValue != null) {
                            allEpisodes.getItems().setAll(dataList.stream().filter(s -> !s.trim().isEmpty()).collect(Collectors.toList()));
                        }
                    });
                    return true;
                }
                return false;
            }
        }else if(action.equals("edit")){
            int aux = serieC.verifyEpisodeInputsToEdit(episodeNameField, episodeNameLabel,
                                        episodeDescriptionField, episodeDescriptionLabel,
                                        episodeVideoField, episodeVideoLabel, episodeCoverField, episodeCoverLabel, 
                                        currentSeason, currentEpisode.getName());
            if(aux == 5){
                episodeNameLabel.setTextFill(Color.web("#f5cf7a"));
                currentEpisode.setName(episodeNameField.getText());
                currentEpisode.setDescription(episodeDescriptionField.getText());
                currentEpisode.setImageLocalPathURL(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/series/"+ serieId + currentSeason.getSeasonName().replace(" ", "") + episodeNameField.getText().replace(" ", "") + ".png");
                currentEpisode.setCoverURL(episodeCoverField.getText());
                currentEpisode.setVideoURL(episodeVideoField.getText());

                serieC.updateEpisodeComboBox(allEpisodes, currentSeason);
                return true;
            }
            return false;
        }
        return false;
    }

    @FXML
    void addEpisode(MouseEvent event) {
        episodeMenu("add");
        editEpisode.setVisible(false);
        deleteEpisode.setVisible(false);
    }

    

    @FXML
    void editEpisode(MouseEvent event){
        episodeMenu("edit");
        allEpisodes.getSelectionModel().clearSelection();
        
        editEpisode.setVisible(false);
        deleteEpisode.setVisible(false);
        
    }

   
    
    @FXML
    void removeEpisode(MouseEvent event){
        if(allEpisodes.getSelectionModel().getSelectedItem() != null){
            currentSeason = serieC.currentSeason(currentSeasons, allSeasons.getSelectionModel().getSelectedItem());
            if(!currentSeason.getEpisodes().isEmpty()){
                //adminC.removeEpisodeData(adminC.currentEpisode(currentSeason, allEpisodes.getSelectionModel().getSelectedItem()));
                currentSeason.removeEpisode(serieC.currentEpisode(currentSeason, allEpisodes.getSelectionModel().getSelectedItem()));
                serieC.updateEpisodeComboBox(allEpisodes, currentSeason);
            }
        }
        editEpisode.setVisible(false);
        deleteEpisode.setVisible(false);
    }

    public void handleAllEpisodesComboBoxSelection(String newValue) {
        if (newValue != null && !newValue.equals(selectedEpisodeItem)) {
            editEpisode.setVisible(true);
            deleteEpisode.setVisible(true);
            currentEpisode = serieC.currentEpisode(currentSeason, newValue);
            selectedEpisodeItem = newValue;
        }else if(newValue != null){
            editEpisode.setVisible(true);
            deleteEpisode.setVisible(true);
        }
        
    }

    @FXML
    void seriesToEdit(ActionEvent event){
        //welcomePane.setVisible(false);
        //emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a serie to edit");
        //addMoviePane.setVisible(false);

        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/series.json"));
        if(adminC.getAdmin().getSeries().isEmpty()){
            //emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Serie serie : adminC.getAdmin().getSeries()) {
            VBox imageView = createSerieToEditImageView(serie);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createSerieToEditImageView(Serie serie){
        ImageView newSerie = new ImageView();
        newSerie.setImage(new Image(new File(serie.getImageLocalPathURL()).toURI().toString()));
        newSerie.setId(String.valueOf(serie.getId()));
        newSerie.setFitHeight(250);
        newSerie.setFitWidth(250);
        newSerie.setCursor(Cursor.HAND);
        newSerie.setOnMouseClicked(e -> {
            editSerie(e, serie);
        });
        Label serieName = new Label(serie.getName());
        serieName.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newSerie, serieName);
        return vbox;
    }

    public void editSerie(MouseEvent event, Serie serie){
        serieId = serie.getId();
        //welcomePane.setVisible(false);
        scrollMovies.setVisible(false);
        titleAnchorPane.setVisible(false);
        addSeriePane.setVisible(true);

        serieNameField.setText(serie.getName());
        serieDescriptionField.setText(serie.getDescription());
        serieDirectorField.setText(serie.getDirector());
        serieCoverField.setText(serie.getCoverURL());
        currentSeasons.addAll(serie.getSeasons());
        serieC.saveOldSeasons(oldSeasons, currentSeasons);
        serieC.updateSeasonComboBox(allSeasons, currentSeasons);

        genreC.setGenres(adminC.loadGenresFromJson());
        genreC.updateGenreComboBox(allGenresSeries, genreC.getGenres());
        allGenresSeries.getSelectionModel().select(serie.getGenre());
        allGenresSeries.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleAllGenresComboBoxSelection(newValue);
        });
        

        allSeasons.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    handleAllSeasonsComboBoxSelection(newValue);
                }
        });
        allEpisodes.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        handleAllEpisodesComboBoxSelection(newValue);
                    }
        });
        createSerie.setText("Save changes");
        createSerie.setOnAction(e -> {
            adminC.loadGenresFromJson();
            serie.setName(serieNameField.getText());
            serie.setDescription(serieDescriptionField.getText());
            serie.setDirector(serieDirectorField.getText());

            File oldCover = new File(serie.getImageLocalPathURL());
            oldCover.delete();
            String coverPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/series/"+ serie.getId() + serieNameField.getText() + ".png";
            try {
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        adminC.downloadCover(coverPath, serieCoverField.getText());
                        return null;
                    }
                };
                new Thread(task).start();
                showProgressDialog(task);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            serie.setImageLocalPathURL(coverPath);
            serie.setSeasons(currentSeasons);
            serie.setCoverURL(serieCoverField.getText());
            String oldGenre = serie.getGenre();
            String newGenre = allGenresSeries.getSelectionModel().getSelectedItem();
            serie.setGenre(newGenre);
            if(!oldGenre.equals(serie.getGenre())){
                genreC.getGenres().forEach(g -> {
                    if(g.getName().equals(oldGenre)){
                        g.removeSerie(serie);
                    }else if(g.getName().equals(serie.getGenre())){
                        g.addSerie(serie);
                    }
                });
            }
            adminC.saveGenresToJson(genreC.getGenres());
            adminC.saveSeriesToJson();
            
            for(Season season: oldSeasons){
                for(MultimediaContent episode : season.getEpisodes()){
                    serieC.removeEpisodeData(episode);
                }
            }

            for(Season season : serie.getSeasons()){
                for(MultimediaContent episode : season.getEpisodes()){
                    try {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                adminC.downloadVideo(episode.getImageLocalPathURL().replace(".png", ".mp4"), episode.getVideoURL());
                                return null;
                            }
                        };
                        new Thread(task).start();
                        showProgressDialog(task);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    try {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                adminC.downloadCover(episode.getImageLocalPathURL(), episode.getCoverURL());
                                return null;
                            }
                        };
                        new Thread(task).start();
                        showProgressDialog(task);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            try {
                adminMenu(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @FXML
    void seriesToDelete(ActionEvent event){
        //welcomePane.setVisible(false);
        //emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a serie to delete");
        //addMoviePane.setVisible(false);

        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/series.json"));
        if(adminC.getAdmin().getSeries().isEmpty()){
            //emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Serie serie : adminC.getAdmin().getSeries()) {
            VBox imageView = createSerieToDeleteImageView(serie);
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createSerieToDeleteImageView(Serie serie){
        ImageView newSerie = new ImageView();
        newSerie.setImage(new Image(new File(serie.getImageLocalPathURL()).toURI().toString()));
        newSerie.setId(String.valueOf(serie.getId()));
        newSerie.setFitHeight(250);
        newSerie.setFitWidth(250);
        newSerie.setCursor(Cursor.HAND);
        newSerie.setOnMouseClicked(e -> {
            for(Season season: serie.getSeasons()){
                for(MultimediaContent episode : season.getEpisodes()){
                    serieC.removeEpisodeData(episode);
                }
            }
            File serieCover = new File(serie.getImageLocalPathURL());
            serieCover.delete();
            adminC.getAdmin().removeSerie(serie);
            adminC.saveSeriesToJson();

            userC.setUsers(userC.loadUsersFromJson());
            genreC.setGenres(adminC.loadGenresFromJson());
            for(UserRegistered user: userC.getUsers()){
                for(PlayList playlist: user.getplayList()){
                    playlist.removeSerie(serie);
                }
            }
            for(Genre genre: genreC.getGenres()){
                genre.removeSerie(serie);
            }
            userC.saveUsersToJson();
            adminC.saveGenresToJson(genreC.getGenres());

            seriesToDelete(new ActionEvent());
        });
        Label serieName = new Label(serie.getName());
        serieName.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newSerie, serieName);
        return vbox;
    }

    @FXML
    void serieOptionsIn(MouseEvent event){
        serieOptions.setVisible(true);
    }

    @FXML
    void serieOptionsOut(MouseEvent event){
        serieOptions.setVisible(false);
    }

    //---------------------------------------------------------------SUBSCRIPTIONS-------------------------------------------------------------------------------------//
    @FXML
    void seeAvailableSubscriptions(ActionEvent event){
        welcomePane.setVisible(false);
        emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a Subscription to see");
        //addMoviePane.setVisible(false);

        subC.setSubscriptions(adminC.loadSubscriptionsFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/subscriptions.json"));
        if(subC.getSubscriptions().isEmpty()){
            emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Subscription sub : subC.getSubscriptions()) {
            VBox imageView = createSubscriptionImageView(sub);
            imageView.setCursor(Cursor.HAND);
            imageView.setOnMouseClicked(e -> {
                seeSubscription(e, sub);
            });
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createSubscriptionImageView(Subscription sub){
        ImageView newSub = new ImageView();
        newSub.setImage(new Image(new File(sub.getImageLocalPathURL()).toURI().toString()));
        newSub.setId(String.valueOf(sub.getName()));
        newSub.setFitHeight(250);
        newSub.setFitWidth(250);
        
        Label subName = new Label(sub.getName());
        subName.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newSub, subName);
        return vbox;
    }

    public void seeSubscription(MouseEvent event, Subscription sub){
        Stage informationSub = new Stage();
        informationSub.initStyle(StageStyle.UTILITY); 
        informationSub.initModality(Modality.APPLICATION_MODAL); 

        Label name = new Label("Name: " + sub.getName());
        Label description = new Label("Description: " + sub.getDescription());
        Label duration = new Label("Label: " + sub.getDuration());
        Label price = new Label("Price: " + sub.getPrice());
        ImageView cover = new ImageView();
        cover.setImage(new Image(new File(sub.getImageLocalPathURL()).toURI().toString()));
        cover.setFitWidth(200);
        cover.setFitHeight(200);
        VBox advertiseWindow = new VBox(10);
        advertiseWindow.setAlignment(Pos.CENTER);
        advertiseWindow.getChildren().addAll(name, description, duration, price, cover);
        Scene scene = new Scene(advertiseWindow, 210, 350);
        informationSub.setTitle("Subscription Information");
        informationSub.setScene(scene);
        informationSub.showAndWait(); 
    }

    @FXML
    void addSubscription(ActionEvent event){
        welcomePane.setVisible(false);
        scrollMovies.setVisible(false);
        titleAnchorPane.setVisible(false);
        //addMoviePane.setVisible(false);
        //addSeriePane.setVisible(false);
        //addGenrePane.setVisible(false);
        emptyPane.setVisible(false);
        addSubscriptionPane.setVisible(true);
        

    }

    @FXML
    void createSubscription(ActionEvent event){
        int aux = subC.verifyInputs(subscriptionNameField, subscriptionNameLabel,
                                 subscriptionDescriptionField, subscriptionDescriptionLabel, 
                                 subscriptionDurationField, subscriptionDurationLabel, 
                                 subscriptionPriceField, subscriptionPriceLabel, 
                                 subscriptionCoverField, subscriptionCoverLabel);
        if(aux == 6){
            String path = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/subscriptions/" + subscriptionNameField.getText().replace(" ", "") + ".png";
            try {
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        adminC.downloadCover(path, subscriptionCoverField.getText());
                        return null;
                    }
                };
                new Thread(task).start();
                showProgressDialog(task);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            
            subC.createSubscription(subscriptionNameField.getText(), subscriptionDescriptionField.getText(),
                 Integer.parseInt(subscriptionDurationField.getText()), Double.parseDouble(subscriptionPriceField.getText()),
                  subscriptionCoverField.getText(), path);
            adminC.saveSubscriptionsToJson(subC.getSubscriptions());
            try {
                adminMenu(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void subscriptionsToEdit(ActionEvent event){
        welcomePane.setVisible(false);
        emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a Subscription to edit");
        //addMoviePane.setVisible(false);

        subC.setSubscriptions(adminC.loadSubscriptionsFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/subscriptions.json"));
        if(subC.getSubscriptions().isEmpty()){
            emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Subscription sub : subC.getSubscriptions()) {
            VBox imageView = createSubscriptionToEditImageView(sub);

            imageView.setCursor(Cursor.HAND);
            imageView.setOnMouseClicked(e -> {
                editSubscription(e, sub);
            });
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createSubscriptionToEditImageView(Subscription sub){
        ImageView newSub = new ImageView();
        newSub.setImage(new Image(new File(sub.getImageLocalPathURL()).toURI().toString()));
        newSub.setId(String.valueOf(sub.getName()));
        newSub.setFitHeight(250);
        newSub.setFitWidth(250);
        
        Label subName = new Label(sub.getName());
        subName.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newSub, subName);
        return vbox;
    }

    public void editSubscription(MouseEvent event, Subscription sub){
        welcomePane.setVisible(false);
        scrollMovies.setVisible(false);
        emptyPane.setVisible(false);
        titleAnchorPane.setVisible(false);
        //addMoviePane.setVisible(false);
        //addSeriePane.setVisible(false);
        //addGenrePane.setVisible(false);
        addSubscriptionPane.setVisible(true);

        subscriptionNameField.setText(sub.getName());
        subscriptionDescriptionField.setText(sub.getDescription());
        subscriptionDurationField.setText(String.valueOf(sub.getDuration()));
        subscriptionPriceField.setText(String.valueOf(sub.getPrice()));
        subscriptionCoverField.setText(sub.getCoverURL());

        createSubscriptionButton.setText("Save changes");
        createSubscriptionButton.setOnAction(e -> {
            int aux = subC.verifyInputsToEdit(sub.getName(), subscriptionNameField, subscriptionNameLabel, 
                        subscriptionDescriptionField, subscriptionDescriptionLabel, subscriptionDurationField,
                        subscriptionDurationLabel, subscriptionPriceField, subscriptionPriceLabel, subscriptionCoverField, subscriptionCoverLabel);
            if(aux == 6){
                sub.setName(subscriptionNameField.getText());
                sub.setDescription(subscriptionDescriptionField.getText());
                sub.setDuration(Integer.parseInt(subscriptionDurationField.getText()));
                sub.setPrice(Double.parseDouble(subscriptionPriceField.getText()));
                sub.setCoverURL(subscriptionCoverField.getText());
                String path = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/subscriptions/" + subscriptionNameField.getText().replace(" ", "") + ".png";
                File oldCover = new File(sub.getImageLocalPathURL());
                oldCover.delete();
                try {
                Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            adminC.downloadCover(path, subscriptionCoverField.getText());
                            return null;
                        }
                    };
                    new Thread(task).start();
                    showProgressDialog(task);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                
                sub.setImageLocalPathURL(path);

                adminC.saveSubscriptionsToJson(subC.getSubscriptions());

                returnToAdminMenu(e);
            }
        });
    }

    @FXML
    void subscriptionsToDelete(ActionEvent event){
        welcomePane.setVisible(false);
        emptyPane.setVisible(false);
        scrollMovies.setVisible(true);
        titleAnchorPane.setVisible(true);
        titleText.setText("Select a Subscription to delete");
        //addMoviePane.setVisible(false);

        subC.setSubscriptions(adminC.loadSubscriptionsFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/subscriptions.json"));
        if(subC.getSubscriptions().isEmpty()){
            emptyPane.setVisible(true);
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Subscription sub : subC.getSubscriptions()) {
            VBox imageView = createSubscriptionToDeleteImageView(sub);
            imageView.setCursor(Cursor.HAND);
            imageView.setOnMouseClicked(e -> {
                deleteSubscriptionFunction(sub);
            });
            multimediaContentGrid.add(imageView, column, row);

            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            column++;
            if (column > 3) {
                column = 0;
                row++;
            }
        }
        
        
    }

    public VBox createSubscriptionToDeleteImageView(Subscription sub){
        ImageView newSub = new ImageView();
        newSub.setImage(new Image(new File(sub.getImageLocalPathURL()).toURI().toString()));
        newSub.setId(String.valueOf(sub.getName()));
        newSub.setFitHeight(250);
        newSub.setFitWidth(250);
        
        Label subName = new Label(sub.getName());
        subName.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newSub, subName);
        return vbox;
    }

    public void deleteSubscriptionFunction(Subscription sub){
        File oldCover = new File(sub.getImageLocalPathURL());
            oldCover.delete();
            subC.removeSubscription(sub);
            adminC.saveSubscriptionsToJson(subC.getSubscriptions());

            userC.setUsers(userC.loadUsersFromJson());
            for(UserRegistered user: userC.getUsers()){
                if(user.getSub() != null && user.getSub().getName().equals(sub.getName())){
                    user.setSub(null);
                }
            }
            
            userC.saveUsersToJson();
            subscriptionsToDelete(new ActionEvent());
    }

    @FXML
    void showSubOptions(MouseEvent event){
        subsOptions.setVisible(true);
    }

    @FXML
    void hideSubOptions(MouseEvent event){
        subsOptions.setVisible(false);
    }


    //----------------------------------------------------------------FIRST AIDS-------------------------------------------------//
    @FXML
    void player(MouseEvent event, String videoPath) {
        //ImageView clickedImageView = (ImageView) event.getSource();
        //String id = clickedImageView.getId();
        String path = "file:///" + videoPath;
        path = path.replace(" ", "%20");
        Media media = new Media(path);
        MediaPlayer player = new MediaPlayer(media);
        MediaView mediaView = new MediaView(player);
        mediaView.setMediaPlayer(player);
        player.setVolume(40);
        
        mediaView.setOnMouseClicked(e -> {
            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.pause();
            } else {
                player.play();
            }
        });
        
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setLayoutX(30);
        slider.setLayoutY(585);
        slider.setMinWidth(800);

        Button back = new Button("<-");
        back.setLayoutX(0);
        back.setLayoutY(580);
        back.setMinSize(10, 10);
        back.setOnAction(arg0 -> {
            try {
                player.stop();
                adminMenu(arg0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        
        Rectangle sliderCover = new Rectangle(0, 575, 800, 25);
        sliderCover.setFill(Color.TRANSPARENT); 
        sliderCover.setVisible(true); 
        
        Pane root = new Pane();
        
        root.getChildren().add(mediaView);
        root.getChildren().add(sliderCover);
        root.getChildren().add(slider);
        root.getChildren().add(back);
        
        Timeline updateSliderTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), event1 -> {
                    if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                        double progress = (player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis()) * 100.0;
                        slider.setValue(progress);
                    }
                })
        );

        updateSliderTimeline.setCycleCount(Timeline.INDEFINITE);
        updateSliderTimeline.play();
        
        sliderCover.setOnMouseEntered(e -> {
            slider.setVisible(true);
            back.setVisible(true);
        });
    
        sliderCover.setOnMouseExited(e -> {
            if (!sliderCover.getBoundsInParent().contains(e.getX(), e.getY())) {
                slider.setVisible(false);
                back.setVisible(false);
            }
        });

        slider.valueProperty().addListener((observable, oldValue, newValue) ->
            player.seek(player.getTotalDuration().multiply(newValue.doubleValue() / 100.0)));

        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        mediaView.fitWidthProperty().bind(scene.widthProperty());
        mediaView.fitHeightProperty().bind(scene.heightProperty());

        mediaView.setPreserveRatio(false);
        stage.setScene(scene);
        stage.show();
        player.play();
    }

    public void showProgressDialog(Task<Void> task) {
        VBox root = new VBox(10);
        ProgressBar progressBar = new ProgressBar();
        Label progressLabel = new Label("Downloading content.\nPlease wait.");
        progressLabel.setFont(new Font("SimSun", 22));
        progressLabel.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(progressLabel, progressBar);

        Stage progressStage = new Stage();
        progressStage.initStyle(StageStyle.UNDECORATED);
        progressStage.initModality(Modality.APPLICATION_MODAL);
        progressStage.setScene(new Scene(root));

        progressBar.progressProperty().bind(task.progressProperty());

        task.setOnSucceeded(event -> {
            progressStage.close();
        });

        progressStage.showAndWait();
    }


    public void advertiseMessage(String text){
        Stage advertiseStage = new Stage();
        advertiseStage.initStyle(StageStyle.UTILITY); 
        advertiseStage.initModality(Modality.APPLICATION_MODAL); 
        Label advertiseText = new Label(text);
        Button closeButton = new Button("Got It");
        closeButton.setOnAction(e -> advertiseStage.close());
        VBox advertiseWindow = new VBox(10);
        advertiseWindow.setAlignment(Pos.CENTER);
        advertiseWindow.getChildren().addAll(advertiseText,closeButton);
        Scene scene = new Scene(advertiseWindow, 200, 100);
        advertiseStage.setTitle("Advertise Message");
        advertiseStage.setScene(scene);
        advertiseStage.showAndWait(); 
    }
    
    @FXML
    void returnToAdminMenu(ActionEvent event) {
        try {
            adminMenu(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
