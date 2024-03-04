package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.GenreController;
import co.edu.uptc.controller.MovieController;
import co.edu.uptc.controller.PlayListController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.UserRegistered;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

public class UserView {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane emptyPane;

    @FXML
    private AnchorPane userOptionsPane;

    @FXML
    private ImageView userOptionsButton;

    @FXML
    private Button movieButton;

    @FXML
    private Button serieButton;


    @FXML
    private Button playlistButton;

    @FXML
    private Button subscriptionButton;


    @FXML
    private GridPane multimediaContentGrid;

    private boolean optionsActivated = false;

    //-------------------------------------------------------PLAYLIST ATRIBUTES-----------------------------------------------------//
    @FXML
    private Button seePlaylistButton;
    @FXML
    private Button addPlaylistButton;
    @FXML
    private Button editPlaylistButton;
    @FXML
    private Button deletePlaylistButton;

    @FXML
    private AnchorPane playListPane;

    @FXML
    private TextField playListNameField;

    @FXML
    private Label playListNameLabel;

    @FXML
    private Rectangle playListBackgroundCover;

    @FXML
    private ColorPicker playListBackgroundCoverColor;

    @FXML
    private Label playListTextCover;

    @FXML
    private ColorPicker playListTextCoverColor;

    @FXML
    private ComboBox<String> currentPlaylistMovies;

    @FXML
    private ComboBox<String> currentPlaylistSeries;

    @FXML
    private ComboBox<String> allMoviesAvailable;

    @FXML
    private ImageView movieCover;

    @FXML
    private Label movieNameLabel;

    @FXML
    private Label movieDescriptionLabel;

    @FXML
    private Label movieDirectorLabel;

    @FXML
    private Label movieStudioLabel;

    @FXML
    private Label movieBudgetLabel;

    @FXML
    private Label movieRevenueLabel;
    
    @FXML
    private ComboBox<String> allSeriesAvailable;

    @FXML
    private ImageView serieCover;

    @FXML
    private Label serieNameLabel;

    @FXML
    private Label serieDescriptionLabel;

    @FXML
    private Label serieDirectorLabel;

    @FXML
    private VBox playlistOptions;

    @FXML
    private Button createPlaylistButton;

    
    //--------------------------------------------------SEARCH-----------------------------------------------------------------//
    @FXML
    private AnchorPane searchPane;

    @FXML
    private TextField searchField;
    
    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane newContentSearchPane;

    @FXML
    private HBox newMoviesBox;

    @FXML
    private HBox newSeriesBox;

    @FXML
    private AnchorPane notFoundSearchPane;

    @FXML
    private CheckBox byName;

    @FXML
    private CheckBox byDirector;

    @FXML
    private CheckBox byGenre;

    @FXML
    private ComboBox<String> genres;

    private int column = 0;
    private int row = 0;
    private String selectedGenreItem = null;

    //--------------------------------------------------SUBSCRIPTION ATRIBUTES--------------------------------//
    @FXML
    private AnchorPane subscriptionInfoPane;

    @FXML 
    private AnchorPane paymentPane;

    @FXML
    private Label subNameLabel;

    @FXML
    private ImageView subImage;

    @FXML
    private Label subDescriptionLabel;

    @FXML
    private Label subDurationLabel;

    @FXML
    private Label subPriceLabel;

    @FXML
    private Label subEndTimeLabel;

    //--------------------------------------------------SUBSCRIPTION PAYMENT--------------------------------//

    @FXML
    private CheckBox credit;

    @FXML
    private CheckBox debit;

    @FXML
    private TextField cardNumberField;

    @FXML
    private Label cardNumberLabel;

    @FXML
    private TextField mmddField;

    @FXML
    private Label mmddLabel;

    @FXML
    private TextField cvvField;

    @FXML
    private Label cvvLabel;

    @FXML
    private Button paySubButton;

    @FXML
    private ComboBox<String> banks;

    @FXML
    private TextField paymentFirstNameField;

    @FXML
    private TextField paymentLastNameField;

    @FXML
    private TextField paymentIdField;

    @FXML
    private Label paymentFirstNameLabel;

    @FXML
    private Label paymentLastNameLabel;

    @FXML
    private Label paymentIdLabel;

    @FXML
    private DatePicker bornDate;



    private AdminController adminC = new AdminController();
    private UserController userC = new UserController();
    private UserRegistered currentUser = new UserRegistered();
    private MovieController movieC = new MovieController();
    private SerieController serieC = new SerieController();
    private GenreController genreC = new GenreController();
    private PlayListController playlistC = new PlayListController();
    private SubscriptionController subC = new SubscriptionController();

    public void initialize(){
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());
        for(int i = 0; i < 4; i++){
            VBox imageView = createMovieImageView(adminC.getAdmin().getMovies().get(i));
            newMoviesBox.getChildren().add(imageView);
        }
        genreC.setGenres(adminC.loadGenresFromJson());
        genreC.getGenres().forEach(g -> {
            genres.getItems().add(g.getName());
        });
        genres.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleSearchGenres(newValue);
            }
        });
        genres.getSelectionModel().select(0);
    }


    @FXML
    void userMenu(ActionEvent event) throws IOException{
        if(event.getSource() instanceof MenuItem){
            stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        }else{
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        }
        root = FXMLLoader.load(getClass().getResource("/co/edu/uptc/persistence/fxmlFiles/userView.fxml"));
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    @FXML
    void returnToLogin(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/persistence/fxmlFiles/loginView.fxml"));
        root= loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();
    }

    @FXML
    void seeUserOptions(MouseEvent event){
        if(optionsActivated){
            for(int i = 0; i < 2; i++){
                TranslateTransition effect = new TranslateTransition();
                if(i==0){effect.setNode(userOptionsPane);}
                if(i==1){effect.setNode(userOptionsButton);}
                effect.setByX(-190);
                effect.setCycleCount(1);
                effect.play();
            }
            optionsActivated = false;
        }else{
            for(int i = 0; i < 2; i++){
                TranslateTransition effect = new TranslateTransition();
                if(i==0){effect.setNode(userOptionsPane);}
                if(i==1){effect.setNode(userOptionsButton);}
                effect.setByX(190);
                effect.setCycleCount(1);
                effect.play();
            }
            optionsActivated = true;
        }
        
        
    }

    //----------------------------------------------MOVIES------------------------------------------------------------------//
    @FXML
    void seeAvailableMoviesMouse(ActionEvent event){
        searchPane.setVisible(false);
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());
        seeAvailableMovies(adminC.getAdmin().getMovies());
    }

    
    void seeAvailableMovies(ArrayList<Movie> movies){
        subscriptionInfoPane.setVisible(false);
        scrollPane.setVisible(true);
        emptyPane.setVisible(false);
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());

        if(movies.isEmpty()){
            emptyPaneOnly();
        }
        column = 0;
        row = 0;
        multimediaContentGrid.getChildren().clear();

        for (Movie movie : movies) {
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
        newMovie.setFitWidth(240);
        newMovie.setCursor(Cursor.HAND);
        newMovie.setOnMouseClicked(e -> {
            if (currentUser.getSub() != null) {
                if (System.currentTimeMillis() > currentUser.getSub().getEndTime()) {
                    userC.setCurrentUser(currentUser);
                    userC.setUsers(userC.loadUsersFromJson());
                    
                    currentUser.setSub(null);
                    userC.saveUsersToJson();
                    userC.setCurrentUser(currentUser);
                    userC.saveCurrentUserToJson();
                }
            }
            if(currentUser.getSub() == null){
                int rand = (int) (Math.random() * 5) + 1;
                String pathadd = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/adds/add"+ rand + ".mp4";
                playerAdd(e, movie.getImageLocalPathURL().replace(".png", ".mp4"), pathadd);
            }else{
                player(e, movie.getImageLocalPathURL().replace(".png", ".mp4"));
            }
            
            
        });
        Label movieLabel = new Label(movie.getName());
        movieLabel.setTextFill(Color.WHITE);
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(newMovie, movieLabel);
        return vbox;
    }

    //-------------------------------------------------------------SERIES-------------------------------------------------------------//
    @FXML
    void seeAvailableSeriesMouse(ActionEvent event){
        if(!multimediaContentGrid.getChildren().isEmpty()){
            multimediaContentGrid.getChildren().clear();
        }
        column = 0;
        row = 0;
        searchPane.setVisible(false);
        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson());
        seeAvailableSeries(adminC.getAdmin().getSeries());
    }
    
    void seeAvailableSeries(ArrayList<Serie> series){
        scrollPane.setVisible(true);
        emptyPane.setVisible(false);
        subscriptionInfoPane.setVisible(false);
        if(series.isEmpty()){
            emptyPaneOnly();
        }

        for (Serie serie : series) {
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
        newSerie.setFitHeight(240);
        newSerie.setFitWidth(240);
        newSerie.setCursor(Cursor.HAND);
        newSerie.setOnMouseClicked(e -> {
            seeAvailableSeasons(e, serie);
        });
        Label serieName = new Label(serie.getName());
        serieName.setTextFill(Color.WHITE);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newSerie, serieName);
        return vbox;
    }

    void seeAvailableSeasons(MouseEvent event, Serie serie){
        emptyPane.setVisible(false);
        multimediaContentGrid.getChildren().clear();
        column = 0;
        row = 0;
        if(serie.getSeasons().isEmpty()){
            emptyPaneOnly();
        }
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
        Rectangle newSeasonBackground = new Rectangle(240,240);
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
        emptyPane.setVisible(false);
        multimediaContentGrid.getChildren().clear();
        column = 0;
        row = 0;
        if(season.getEpisodes().isEmpty()){
            emptyPaneOnly();
        }
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
        newEpisode.setFitHeight(240);
        newEpisode.setFitWidth(240);
        newEpisode.setCursor(Cursor.HAND);
        newEpisode.setOnMouseClicked(e -> {
            if (currentUser.getSub() != null) {
                if (System.currentTimeMillis() > currentUser.getSub().getEndTime()) {
                    userC.setCurrentUser(currentUser);
                    userC.setUsers(userC.loadUsersFromJson());

                    currentUser.setSub(null);
                    userC.saveUsersToJson();
                    userC.setCurrentUser(currentUser);
                    userC.saveCurrentUserToJson();
                }
            }
            if(currentUser.getSub() == null){
                int rand = (int) (Math.random() * 5) + 1;
                String pathadd = System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/adds/add"+ rand + ".mp4";
                playerAdd(e, episode.getImageLocalPathURL().replace(".png", ".mp4"), pathadd);
            }else{
                player(e, episode.getImageLocalPathURL().replace(".png", ".mp4"));
            }
            
            
            
        });
        Label episodeName = new Label(episode.getName());
        episodeName.setTextFill(Color.WHITE);

        VBox vbox = new VBox(5); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.getChildren().addAll(newEpisode, episodeName);
        return vbox;
        
    }

    //-------------------------------------------------------SEARCH---------------------------------------------------------------//

    @FXML
    void searchMenu(ActionEvent event){
        emptyPane.setVisible(false);
        notFoundSearchPane.setVisible(false);
        newContentSearchPane.setVisible(true);
        searchPane.setVisible(true);
        scrollPane.setVisible(false);
    }

    @FXML
    void search(KeyEvent event){
        if(searchField.getText().isEmpty()){
            scrollPane.setVisible(false);
            scrollPane.setPrefHeight(600);
            scrollPane.setLayoutY(0);
            newContentSearchPane.setVisible(true);
            notFoundSearchPane.setVisible(false);
            emptyPane.setVisible(false);
        }else{
            scrollPane.setVisible(true);
            scrollPane.setPrefHeight(510);
            scrollPane.setLayoutY(90);
            newContentSearchPane.setVisible(false);
            contentManagement();

        }
    }

    public void contentManagement(){
        scrollPane.setVisible(true);
        scrollPane.setPrefHeight(510);
        scrollPane.setLayoutY(90);
        notFoundSearchPane.setVisible(false);
        ArrayList<Movie> moviesFound = new ArrayList<>();
        ArrayList<Serie> seriesFound = new ArrayList<>();
        if(byName.isSelected() || (!byName.isSelected() && !byDirector.isSelected() && !byGenre.isSelected())){
            moviesFound = moviesFound("name");
            seriesFound = seriesFound("name");
        }else if(byDirector.isSelected()){
            moviesFound = moviesFound("director");
            seriesFound = seriesFound("director");
        }else if(byGenre.isSelected()){
            moviesFound = genreC.getGenre(genres.getSelectionModel().getSelectedItem()).getMovies();
            seriesFound = genreC.getGenre(genres.getSelectionModel().getSelectedItem()).getSeries();
        }
        if(!moviesFound.isEmpty()){
            seeAvailableMovies(moviesFound);
        }
        if(!seriesFound.isEmpty()){
            if(moviesFound.isEmpty()){
                multimediaContentGrid.getChildren().clear();
                column = 0;
                row = 0;
            }
            seeAvailableSeries(seriesFound);
        }
        if(moviesFound.isEmpty() && seriesFound.isEmpty()){
            notFoundSearchPane.setVisible(true);
        }
    }

    public ArrayList<Movie> moviesFound(String by){
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());
        ArrayList<Movie> allContent = new ArrayList<>();
        adminC.getAdmin().getMovies().forEach(m -> {
            if(by.equals("name")){
                if(m.getName().toLowerCase().contains(searchField.getText().toLowerCase())){
                    allContent.add(m);
                }
            }else if(by.equals("director")){
                if(m.getDirector().toLowerCase().contains(searchField.getText().toLowerCase())){
                    allContent.add(m);
                }
            }else if(by.equals("genre")){
                if(m.getGenre().toLowerCase().contains(searchField.getText().toLowerCase())){
                    allContent.add(m);
                }
            }
            
        });
        return allContent;
    }
    public ArrayList<Serie> seriesFound(String by){
        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson());

        
        ArrayList<Serie> allContent = new ArrayList<>();
        adminC.getAdmin().getSeries().forEach(s -> {
            if(by.equals("name")){
                if(s.getName().toLowerCase().contains(searchField.getText().toLowerCase())){
                    allContent.add(s);
                }
            }else if(by.equals("director")){
                if(s.getDirector().toLowerCase().contains(searchField.getText().toLowerCase())){
                    allContent.add(s);
                }
            }else if(by.equals("genre")){
                if(s.getGenre().toLowerCase().contains(searchField.getText().toLowerCase())){
                    allContent.add(s);
                }
            }
            
        });
        return allContent;
    }


    @FXML
    void byNameSelected(ActionEvent event){
        scrollPane.setVisible(false);
        scrollPane.setPrefHeight(600);
        scrollPane.setLayoutY(0);
        byDirector.setSelected(false);
        byGenre.setSelected(false);
        if(!searchField.getText().isEmpty()){
            contentManagement();
        }
    }

    @FXML
    void byDirectorSelected(ActionEvent event){
        scrollPane.setVisible(false);
        scrollPane.setPrefHeight(600);
        scrollPane.setLayoutY(0);
        byName.setSelected(false);
        byGenre.setSelected(false);
        if(!searchField.getText().isEmpty()){
            contentManagement();
        }
    }

    @FXML
    void byGenreSelected(ActionEvent event){
        byName.setSelected(false);
        byDirector.setSelected(false);
        contentManagement();
        
    }

    public void handleSearchGenres(String newValue){
        if (newValue != null && !newValue.equals(selectedGenreItem)) {
            if(byGenre.isSelected()){
                contentManagement();
            }
        }
    }

    //------------------------------------------------------------PLAYLIST----------------------------------------------------------------//
    @FXML
    void seeAvailablePlaylists(ActionEvent event){
        
        currentUser = userC.loadCurrentUserFromJson();
        searchPane.setVisible(false);
        newContentSearchPane.setVisible(false);
        emptyPane.setVisible(false);
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;
        if(currentUser.getplayList().isEmpty()){
            emptyPaneOnly();
        }else{
            scrollPane.setVisible(true);
        }
        for(PlayList playlist : currentUser.getplayList()){
            Parent imageView = createPlaylistImageView(playlist);
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

    public Parent createPlaylistImageView(PlayList playlist){
        Rectangle newPlaylistBackground = new Rectangle(240,240);
        newPlaylistBackground.setFill(Color.web(playlist.getBackgroundCover()));
        newPlaylistBackground.setCursor(Cursor.HAND);
        Label newPlaylistText = new Label(playlist.getName());
        newPlaylistText.setTextFill(Color.web(playlist.getTextCover()));
        StackPane imageView = new StackPane();
        imageView.getChildren().addAll(newPlaylistBackground, newPlaylistText);
        imageView.setOnMouseClicked(e -> {
            seePlaylistAvailableMoviesAndSeries(e, playlist);
        });
        return imageView;
    }

    public void seePlaylistAvailableMoviesAndSeries(MouseEvent event, PlayList playlist){
        emptyPane.setVisible(false);

        if(playlist.getMovies().isEmpty() && playlist.getSeries().isEmpty()){
            emptyPaneOnly();
        }
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Serie serie : playlist.getSeries()) {
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

        for(Movie movie : playlist.getMovies()){
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

    @FXML
    void createPlaylist(ActionEvent event){
        currentUser = userC.loadCurrentUserFromJson();
        userC.setCurrentUser(currentUser);
        userC.setUsers(userC.loadUsersFromJson());
        int aux = playlistC.verifyInputs(playListNameField, playListNameLabel, currentUser);
        if(aux == 3){
            ArrayList<Movie> moviesToAdd = playlistC.setMovies(currentPlaylistMovies.getItems(), adminC.getAdmin().getMovies());
            ArrayList<Serie> seriesToAdd = playlistC.setSeries(currentPlaylistSeries.getItems(), adminC.getAdmin().getSeries());

            playlistC.createPlaylist(playListNameField.getText(), moviesToAdd, seriesToAdd,
            adminC.toWebColor(playListTextCoverColor.getValue()), adminC.toWebColor(playListBackgroundCoverColor.getValue()), currentUser);
            userC.updateUsers(currentUser);
            userC.saveUsersToJson();
            userC.setCurrentUser(currentUser);
            userC.saveCurrentUserToJson();
            try {
                userMenu(event);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void addPlaylist(ActionEvent event){
        emptyPane.setVisible(false);
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());
        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson());
        movieC.moviesAvailablesNamesComboBox(allMoviesAvailable, adminC.getAdmin().getMovies());
        serieC.seriesAvailablesNamesComboBox(allSeriesAvailable, adminC.getAdmin().getSeries());
        playListPane.setVisible(true);
        allMoviesAvailable.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleAllMoviesAvailableComboBox(newValue);
            }
        });
        allSeriesAvailable.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleAllSeriesAvailableComboBox(newValue);
            }
        });
    }

    public void handleAllMoviesAvailableComboBox(String newValue){
        if(allMoviesAvailable.getSelectionModel().getSelectedItem() != null){
            Movie currentMovie = movieC.currentMovie(adminC.getAdmin().getMovies(), newValue);
            movieNameLabel.setText("Name: " + currentMovie.getName());
            movieDescriptionLabel.setText("Description: " + currentMovie.getDescription());
            movieDirectorLabel.setText("Director: " + currentMovie.getDirector());
            movieStudioLabel.setText("Studio: " + currentMovie.getStudio());
            movieBudgetLabel.setText("Budget: " + String.valueOf(currentMovie.getBudget()));
            movieRevenueLabel.setText("Revenue: " + String.valueOf(currentMovie.getRevenue()));
            movieCover.setImage(new Image(new File(currentMovie.getImageLocalPathURL()).toURI().toString()));
        }
    }

    @FXML
    void addMovieToPlaylist(MouseEvent event){
        if(allMoviesAvailable.getSelectionModel().getSelectedItem() != null){
            movieC.addCurrentMoviesComboBox(currentPlaylistMovies, allMoviesAvailable.getSelectionModel().getSelectedItem());
            movieC.removeMoviesAvailablesNamesComboBox(allMoviesAvailable, allMoviesAvailable.getSelectionModel().getSelectedItem());
            
            movieNameLabel.setText("Name:" );
            movieDescriptionLabel.setText("Description: ");
            movieDirectorLabel.setText("Director: ");
            movieStudioLabel.setText("Studio: ");
            movieBudgetLabel.setText("Budget: ");
            movieRevenueLabel.setText("Revenue: ");
            movieCover.setImage(null);
            allMoviesAvailable.getSelectionModel().clearSelection();
            allMoviesAvailable.setPromptText("Movies Available");
        }
        
    }

    @FXML
    void deletePlayListMovie(MouseEvent event){
        if(currentPlaylistMovies.getSelectionModel().getSelectedItem() != null){
            movieC.addMoviesAvailablesNamesComboBox(allMoviesAvailable, currentPlaylistMovies.getSelectionModel().getSelectedItem());
            movieC.removeCurrentMoviesComboBox(currentPlaylistMovies, currentPlaylistMovies.getSelectionModel().getSelectedItem());
            currentPlaylistMovies.getSelectionModel().clearSelection();
            currentPlaylistMovies.setPromptText("Movies Added");
        }
    }

    public void handleAllSeriesAvailableComboBox(String newValue){
        if(allSeriesAvailable.getSelectionModel().getSelectedItem() != null){
            Serie currentSerie = serieC.currentSerie(adminC.getAdmin().getSeries(), newValue);
            serieNameLabel.setText("Name: " + currentSerie.getName());
            serieDescriptionLabel.setText("Description: " + currentSerie.getDescription());
            serieDirectorLabel.setText("Director: " + currentSerie.getDirector());
            serieCover.setImage(new Image(new File(currentSerie.getImageLocalPathURL()).toURI().toString()));
        }
    }

    @FXML
    void addSerieToPlaylist(MouseEvent event){
        if(allSeriesAvailable.getSelectionModel().getSelectedItem() != null){
            serieC.addCurrentSeriesComboBox(currentPlaylistSeries, allSeriesAvailable.getSelectionModel().getSelectedItem());
            serieC.removeSeriesAvailablesNamesComboBox(allSeriesAvailable, allSeriesAvailable.getSelectionModel().getSelectedItem());
            serieNameLabel.setText("Name: ");
            serieDescriptionLabel.setText("Description:");
            serieDirectorLabel.setText("Director: " );
            serieCover.setImage(null);
            allSeriesAvailable.getSelectionModel().clearSelection();
            allSeriesAvailable.setPromptText("Series Available");
        }
        
    }

    @FXML
    void deletePlaylistSerie(MouseEvent event){
        if(currentPlaylistSeries.getSelectionModel().getSelectedItem() != null){
            serieC.addSeriesAvailablesNamesComboBox(allSeriesAvailable, currentPlaylistSeries.getSelectionModel().getSelectedItem());
            serieC.removeCurrentSeriesComboBox(currentPlaylistSeries, currentPlaylistSeries.getSelectionModel().getSelectedItem());
            currentPlaylistSeries.getSelectionModel().clearSelection();
            currentPlaylistSeries.setPromptText("Series Added");
        }
    }

    @FXML
    void playlistNameCoverManagement(KeyEvent event){
        playListTextCover.setText(playListNameField.getText());
    }
    
    @FXML
    void playlistTextColorManagement(ActionEvent event){
        playListTextCover.setTextFill(playListTextCoverColor.getValue());
    }

    @FXML
    void playlistBackgroundColorManagement(ActionEvent event){
        playListBackgroundCover.setFill(playListBackgroundCoverColor.getValue());
    }

    @FXML
    void playlistsToEdit(ActionEvent event){
        searchPane.setVisible(false);
        currentUser = userC.loadCurrentUserFromJson();
        emptyPane.setVisible(false);
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;
        if(currentUser.getplayList().isEmpty()){
            emptyPaneOnly();
        }else{
            scrollPane.setVisible(true);
        }
        for (PlayList playlist : currentUser.getplayList()) {
            Parent imageView = createPlaylistToEditImageView(playlist);
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

    public Parent createPlaylistToEditImageView(PlayList playlist){
        Rectangle newPlaylistBackground = new Rectangle(240,240);
        newPlaylistBackground.setFill(Color.web(playlist.getBackgroundCover()));
        newPlaylistBackground.setCursor(Cursor.HAND);
        Label newPlaylistText = new Label(playlist.getName());
        newPlaylistText.setTextFill(Color.web(playlist.getTextCover()));
        StackPane imageView = new StackPane();
        imageView.getChildren().addAll(newPlaylistBackground, newPlaylistText);
        imageView.setOnMouseClicked(e -> {
            editPlaylist(e, playlist);
        });
        return imageView;
    }

    public void editPlaylist(MouseEvent event, PlayList playlist){
        playListPane.setVisible(true);
        emptyPane.setVisible(false);
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());
        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson());
        
        playListNameField.setText(playlist.getName());
        currentPlaylistMovies.setItems(playlistC.currentPlaylistMovies(currentPlaylistMovies, playlist.getMovies()));
        allMoviesAvailable.setItems(playlistC.allMoviesAvailable(currentPlaylistMovies.getItems(), adminC.getAdmin().getMovies()));
        currentPlaylistSeries.setItems(playlistC.currentPlaylistSeries(currentPlaylistSeries, playlist.getSeries()));
        allSeriesAvailable.setItems(playlistC.allSeriesAvailable(currentPlaylistSeries.getItems(), adminC.getAdmin().getSeries()));

        allMoviesAvailable.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    handleAllMoviesAvailableComboBox(newValue);
                }
        });
        allSeriesAvailable.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        handleAllSeriesAvailableComboBox(newValue);
                    }
        });

        playListBackgroundCoverColor.setValue(Color.web(playlist.getBackgroundCover()));
        playListBackgroundCover.setFill(Color.web(playlist.getBackgroundCover()));
        playListTextCoverColor.setValue(Color.web(playlist.getTextCover()));
        playListTextCover.setTextFill(Color.web(playlist.getTextCover()));
        playListTextCover.setText(playlist.getName());

        createPlaylistButton.setText("Save Changes");
        createPlaylistButton.setOnAction(e -> {
            userC.setCurrentUser(currentUser);
            userC.setUsers(userC.loadUsersFromJson());
            int aux = playlistC.verifyInputsToEdit(playListNameField, playListNameLabel, currentUser, playlist);
            if(aux == 3){
                ArrayList<Movie> moviesToAdd = playlistC.setMovies(currentPlaylistMovies.getItems(), adminC.getAdmin().getMovies());
                ArrayList<Serie> seriesToAdd = playlistC.setSeries(currentPlaylistSeries.getItems(), adminC.getAdmin().getSeries());
                playlist.setName(playListNameField.getText());
                playlist.setTextCover(adminC.toWebColor(playListTextCoverColor.getValue()));
                playlist.setBackgroundCover(adminC.toWebColor(playListBackgroundCoverColor.getValue()));
                playlist.setMovies(moviesToAdd);
                playlist.setSeries(seriesToAdd);

                userC.updateUsers(currentUser);
                userC.saveUsersToJson();
                userC.setCurrentUser(currentUser);
                userC.saveCurrentUserToJson();

                try {
                    userMenu(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @FXML
    void playlistsToDelete(ActionEvent event){
        searchPane.setVisible(false);
        currentUser = userC.loadCurrentUserFromJson();
        emptyPane.setVisible(false);
        multimediaContentGrid.getChildren().clear();
        int column = 0;
        int row = 0;
        if(currentUser.getplayList().isEmpty()){
            emptyPaneOnly();
        }else{
            scrollPane.setVisible(true);
        }

        for (PlayList playlist : currentUser.getplayList()) {
            Parent imageView = createPlaylistToDeleteImageView(playlist);
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

    public Parent createPlaylistToDeleteImageView(PlayList playlist){
        Rectangle newPlaylistBackground = new Rectangle(240,240);
        newPlaylistBackground.setFill(Color.web(playlist.getBackgroundCover()));
        newPlaylistBackground.setCursor(Cursor.HAND);
        Label newPlaylistText = new Label(playlist.getName());
        newPlaylistText.setTextFill(Color.web(playlist.getTextCover()));
        StackPane imageView = new StackPane();
        imageView.getChildren().addAll(newPlaylistBackground, newPlaylistText);
        imageView.setOnMouseClicked(e -> {
            userC.setCurrentUser(currentUser);
            userC.setUsers(userC.loadUsersFromJson());
            currentUser.removeplayList(playlist);

            userC.updateUsers(currentUser);
            userC.saveUsersToJson();
            userC.setCurrentUser(currentUser);
            userC.saveCurrentUserToJson();
            playlistsToDelete(new ActionEvent());
        });
        return imageView;
    }

    //----------------------------------------------SUBSCRIPTION---------------------------------------------------------//
    @FXML
    void subscriptionMouse(MouseEvent event){
        subscription(new ActionEvent());
    }
    @FXML
    void subscription(ActionEvent event){
        currentUser = userC.loadCurrentUserFromJson();
        userC.setCurrentUser(userC.loadCurrentUserFromJson());
        emptyPane.setVisible(false);
        if(currentUser.getSub() == null){
            
            subC.setSubscriptions(adminC.loadSubscriptionsFromJson(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/subscriptions.json"));
            if(subC.getSubscriptions().isEmpty()){
                emptyPaneOnly();
            }else{
                scrollPane.setVisible(true);
            }
            multimediaContentGrid.getChildren().clear();
            int column = 0;
            int row = 0;

            for (Subscription sub : subC.getSubscriptions()) {
                VBox imageView = createSubscriptionImageView(sub);
                imageView.setOnMouseClicked(e -> {
                    subInformation(sub, e);
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
        }else{
            
            if (System.currentTimeMillis() > currentUser.getSub().getEndTime()) {
                userC.setCurrentUser(currentUser);
                userC.setUsers(userC.loadUsersFromJson());
                currentUser.setSub(null);
                userC.updateUsers(currentUser);
                userC.saveUsersToJson();
                userC.setCurrentUser(currentUser);
                userC.saveCurrentUserToJson();
                
                subscription(event);

            }else{
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy H:mm:ss");

                Date endTimeDate = new Date(currentUser.getSub().getEndTime());

                String formattedEndTime = dateFormat.format(endTimeDate);

                subNameLabel.setText("Subscribed to: " + currentUser.getSub().getName());
                subDescriptionLabel.setText("Description: " + currentUser.getSub().getDescription());
                subDurationLabel.setText("Duration (Millis): " + currentUser.getSub().getDuration());
                subPriceLabel.setText("Price: " + currentUser.getSub().getPrice());
                subEndTimeLabel.setText("End Time: " + formattedEndTime);
                subImage.setImage(new Image(new File(currentUser.getSub().getImageLocalPathURL()).toURI().toString()));
                subscriptionInfoPane.setVisible(true);
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

    public void subInformation(Subscription sub, MouseEvent event){
        Stage informationSub = new Stage();
        informationSub.initStyle(StageStyle.DECORATED); 
        informationSub.initModality(Modality.APPLICATION_MODAL); 
        informationSub.setResizable(false);

        Label name = new Label("Name: " + sub.getName());
        Label description = new Label("Description: " + sub.getDescription());
        Label duration = new Label("Label: " + sub.getDuration());
        Label price = new Label("Price: " + sub.getPrice());
        name.setTextFill(Color.WHITE);
        description.setTextFill(Color.WHITE);
        duration.setTextFill(Color.WHITE);
        price.setTextFill(Color.WHITE);
        ImageView cover = new ImageView();
        cover.setImage(new Image(new File(sub.getImageLocalPathURL()).toURI().toString()));
        cover.setFitWidth(200);
        cover.setFitHeight(200);
        Button subscribeButton = new Button("Subscribe!");
        subscribeButton.setStyle("-fx-background-color: #5483B3;");
        subscribeButton.setCursor(Cursor.HAND);
        subscribeButton.setOnAction(e2 -> {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            paymentPane.setVisible(true);
            ObservableList<String> aux = banks.getItems();
            aux.addAll("NEQUI", "DAVIPLATA", "BANCOLOMBIA","CAJA SOCIAL", "DAVIVIENDA", "EL BRAYAN");
            
            paySubButton.setOnAction(e -> paySub(e, sub));
            informationSub.close();
            
        });
        VBox informationBox = new VBox(10);
        informationBox.setStyle("-fx-background-color: #021024;");
        informationBox.setAlignment(Pos.CENTER);
        informationBox.getChildren().addAll(name, description, duration, price, cover, subscribeButton);
        Scene scene = new Scene(informationBox, 210, 350);
        informationSub.setTitle("Subscription Information");
        informationSub.setScene(scene);
        informationSub.showAndWait(); 
    }

    
    void paySub(ActionEvent event, Subscription sub){
        int aux = subC.verifyInputsPaidMethod(cardNumberField, cardNumberLabel, mmddField, mmddLabel, cvvField, cvvLabel, credit, debit, banks);
        aux = aux + subC.verifyPersonInformation(paymentFirstNameField, paymentFirstNameLabel, paymentLastNameField, paymentLastNameLabel, paymentIdField, paymentIdLabel, bornDate);
        if(aux == 13){
            currentUser = userC.loadCurrentUserFromJson();
            userC.setCurrentUser(userC.loadCurrentUserFromJson());
            userC.setUsers(userC.loadUsersFromJson());
            subC.addSubToUser(sub, currentUser);
            userC.updateUsers(currentUser);
            userC.saveUsersToJson();
            userC.setCurrentUser(currentUser);
            userC.saveCurrentUserToJson();
            try {
                userMenu(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void creditCheckBox(ActionEvent event){
        if(debit.isSelected()){debit.setSelected(false);}
    }

    @FXML
    void debitCheckBox(ActionEvent event){
        if(credit.isSelected()){credit.setSelected(false);}
    }

    @FXML
    void unsuscribe(ActionEvent event){
        subscriptionInfoPane.setVisible(false);
        userC.setCurrentUser(currentUser);
        userC.setUsers(userC.loadUsersFromJson());
        subC.cancelSub(currentUser);
        userC.updateUsers(currentUser);
        userC.saveUsersToJson();
        userC.setCurrentUser(currentUser);
        userC.saveCurrentUserToJson();
        subscription(event);
    }
    
    //-------------------------------------------------------FIRST AIDS-----------------------------------------------------------//

    @FXML
    void returnToUserMenu(ActionEvent event){
        
        //subscriptionInfoPane.setVisible(false);
        //playListPane.setVisible(false);
        try {
            userMenu(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void playerAdd(MouseEvent event, String videoPath, String addPath) {
        String pathadd = "file:///" + addPath.replace(" ", "%20");
        Media mediaadd = new Media(pathadd);
        MediaPlayer playeradd = new MediaPlayer(mediaadd);
        MediaView mediaViewadd = new MediaView(playeradd);
        mediaViewadd.setMediaPlayer(playeradd);
        playeradd.setVolume(6);

        mediaViewadd.setOnMouseClicked(e -> {
            if (playeradd.getStatus() == MediaPlayer.Status.PLAYING) {
                playeradd.pause();
            } else {
                playeradd.play();
            }
        });
        
        Slider slideradd = new Slider();
        slideradd.setMin(0);
        slideradd.setMax(100);
        slideradd.setLayoutX(30);
        slideradd.setLayoutY(585);
        slideradd.setMinWidth(770);
        slideradd.setStyle("-fx-background-color: #FFF000; -fx-background-radius: 100;");

        Button backadd = new Button("<-");
        backadd.setLayoutX(0);
        backadd.setLayoutY(580);
        backadd.setMinSize(10, 10);
        backadd.setOnAction(arg0 -> {
            try {
                playeradd.stop();
                userMenu(arg0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        
        Rectangle sliderCoveradd = new Rectangle(0, 575, 800, 25);
        sliderCoveradd.setFill(Color.TRANSPARENT); 
        sliderCoveradd.setVisible(true); 
        
        Pane rootadd = new Pane();
        
        rootadd.getChildren().add(mediaViewadd);
        rootadd.getChildren().add(sliderCoveradd);
        rootadd.getChildren().add(slideradd);
        rootadd.getChildren().add(backadd);
        
        Timeline updateSliderTimelineadd = new Timeline(
            new KeyFrame(Duration.seconds(5), event1 -> {
                if (playeradd.getStatus() == MediaPlayer.Status.PLAYING) {
                    double progress = (playeradd.getCurrentTime().toMillis() / playeradd.getTotalDuration().toMillis()) * 100.0;
                    slideradd.setValue(progress);
                }
            })
        );

        updateSliderTimelineadd.setCycleCount(Timeline.INDEFINITE);
        updateSliderTimelineadd.play();
        
        sliderCoveradd.setOnMouseEntered(e -> {
            slideradd.setVisible(true);
            backadd.setVisible(true);
        });
    
        sliderCoveradd.setOnMouseExited(e -> {
            if (!sliderCoveradd.getBoundsInParent().contains(e.getX(), e.getY())) {
                slideradd.setVisible(false);
                backadd.setVisible(false);
            }
        });

        slideradd.valueProperty().addListener((observable, oldValue, newValue) ->
            playeradd.seek(playeradd.getTotalDuration().multiply(newValue.doubleValue() / 100.0)));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Scene sceneadd = new Scene(rootadd, 810, 600);
        mediaViewadd.fitWidthProperty().bind(sceneadd.widthProperty());
        mediaViewadd.fitHeightProperty().bind(sceneadd.heightProperty());

        mediaViewadd.setPreserveRatio(false);
        stage.setScene(sceneadd);
        stage.show();
        playeradd.play();
        
        playeradd.setOnEndOfMedia(() ->{
            if(videoPath != null){
                String path = "file:///" + videoPath;
                Media media = new Media(path);
                MediaPlayer player = new MediaPlayer(media);
                MediaView mediaView = new MediaView(player);
                mediaView.setMediaPlayer(player);
                player.setVolume(6);

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
                slider.setMinWidth(770);

                Button back = new Button("<-");
                back.setLayoutX(0);
                back.setLayoutY(580);
                back.setMinSize(10, 10);
                back.setOnAction(arg0 -> {
                    try {
                        userMenu(arg0);
                        player.stop();
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

                    
                Scene scene = new Scene(root, 800, 600);
                mediaView.fitWidthProperty().bind(scene.widthProperty());
                mediaView.fitHeightProperty().bind(scene.heightProperty());
                mediaView.setPreserveRatio(false);

                stage.setScene(scene);
                stage.show();
                player.play();

            }
        });
    }

    @FXML
    void player(MouseEvent event, String videoPath) {
        String path = "file:///" + videoPath;
        path = path.replace(" ", "%20");
        Media media = new Media(path);
        MediaPlayer player = new MediaPlayer(media);
        MediaView mediaView = new MediaView(player);
        mediaView.setMediaPlayer(player);
        player.setVolume(6);
        
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
        slider.setMinWidth(770);

        Button back = new Button("<-");
        back.setLayoutX(0);
        back.setLayoutY(580);
        back.setMinSize(10, 10);
        back.setOnAction(arg0 -> {
            try {
                player.stop();
                userMenu(arg0);
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
        stage.setResizable(false);
        Scene scene = new Scene(root, 800, 600);
        mediaView.fitWidthProperty().bind(scene.widthProperty());
        mediaView.fitHeightProperty().bind(scene.heightProperty());

        mediaView.setPreserveRatio(false);
        stage.setScene(scene);
        stage.show();
        player.play();
    }
    public void emptyPaneOnly(){
        emptyPane.setVisible(true);
        searchPane.setVisible(false);
        notFoundSearchPane.setVisible(false);
        newContentSearchPane.setVisible(false);
    }

    @FXML
    void searchEnteredButtonManagement(MouseEvent event){
        searchButton.setStyle("-fx-background-color: #7DA0CA;");
        searchButton.setFont(new Font("SimSun", 26));
    }

    @FXML
    void searchExitedButtonManagement(MouseEvent event){
        searchButton.setStyle("-fx-background-color:  #5483B3;");
        searchButton.setFont(new Font("SimSun", 24));
    }


    @FXML
    void movieEnteredButtonManagement(MouseEvent event){
        movieButton.setStyle("-fx-background-color: #7DA0CA;");
        movieButton.setFont(new Font("SimSun", 26));
    }

    @FXML
    void movieExitedButtonManagement(MouseEvent event){
        movieButton.setStyle("-fx-background-color:  #5483B3;");
        movieButton.setFont(new Font("SimSun", 24));
    }

    @FXML
    void serieEnteredButtonManagement(MouseEvent event){
        serieButton.setStyle("-fx-background-color: #7DA0CA;");
        serieButton.setFont(new Font("SimSun", 26));
    }

    @FXML
    void serieExitedButtonManagement(MouseEvent event){
        serieButton.setStyle("-fx-background-color:  #5483B3;");
        serieButton.setFont(new Font("SimSun", 24));
    }

    @FXML
    void playlistEnteredButtonManagement(MouseEvent event){
        playlistButton.setStyle("-fx-background-color: #7DA0CA;");
        playlistButton.setFont(new Font("SimSun", 26));
        playlistOptions.setVisible(true);

    }

    @FXML
    void playlistExitedButtonManagement(MouseEvent event){
        playlistButton.setStyle("-fx-background-color: #5483B3;");
        playlistButton.setFont(new Font("SimSun", 24));
        playlistOptions.setVisible(false);
        
    }

    @FXML
    void seePlaylistMouseEntered(MouseEvent event){
        seePlaylistButton.setFont(new Font("SimSun", 20));
    }
    @FXML
    void seePlaylistMouseExited(MouseEvent event){
        seePlaylistButton.setFont(new Font("SimSun", 18));
    }
    @FXML
    void addPlaylistMouseEntered(MouseEvent event){
        addPlaylistButton.setFont(new Font("SimSun", 20));
    }
    @FXML
    void addPlaylistMouseExited(MouseEvent event){
        addPlaylistButton.setFont(new Font("SimSun", 18));
    }
    @FXML
    void editPlaylistMouseEntered(MouseEvent event){
        editPlaylistButton.setFont(new Font("SimSun", 20));
    }
    @FXML
    void editPlaylistMouseExited(MouseEvent event){
        editPlaylistButton.setFont(new Font("SimSun", 18));
    }
    @FXML
    void deletePlaylistMouseEntered(MouseEvent event){
        deletePlaylistButton.setFont(new Font("SimSun", 20));
    }
    @FXML
    void deletePlaylistMouseExited(MouseEvent event){
        deletePlaylistButton.setFont(new Font("SimSun", 18));
    }

    @FXML
    void subscriptionEnteredButtonManagement(MouseEvent event){
        subscriptionButton.setStyle("-fx-background-color: #7DA0CA;");
        subscriptionButton.setFont(new Font("SimSun", 26));
    }

    @FXML
    void subscriptionExitedButtonManagement(MouseEvent event){
        subscriptionButton.setStyle("-fx-background-color:  #5483B3;");
        subscriptionButton.setFont(new Font("SimSun", 24));
    }
    


    

    

   
    public UserRegistered getCurrentUser() {
        return currentUser;
    }

    public void serCurrentUSer(UserRegistered currentUser){
        this.currentUser = currentUser;
    }

    
}

