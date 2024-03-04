package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.GenreController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.UserRegistered;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserView {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane emptyPane;

    @FXML
    private Button subscriptionButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane multimediaContentGrid;

    @FXML
    private AnchorPane userOptionsPane;

    @FXML
    private ImageView userOptionsButton;

    private boolean optionsActivated = false;

    //--------------------------------------------------SEARCH-----------------------------------------------------------------//
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

    

    



    private AdminController adminC = new AdminController();
    private UserController userC = new UserController();
    private UserRegistered currentUser = new UserRegistered();
    private GenreController genreC = new GenreController();

    


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
        stage.setResizable(false);
        
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
    void seeAvailableMoviesMouse(MouseEvent event){
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());
        seeAvailableMovies(adminC.getAdmin().getMovies());
    }

    
    void seeAvailableMovies(ArrayList<Movie> movies){
        //subscriptionInfoPane.setVisible(false);
        
        emptyPane.setVisible(false);
        adminC.getAdmin().setMovies(adminC.loadMoviesFromJson());

        if(movies.isEmpty()){
            emptyPane.setVisible(true);
            emptyPane.setPrefHeight(510);emptyPane.setLayoutY(90);
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
    void seeAvailableSeriesMouse(MouseEvent event){
        adminC.getAdmin().setSeries(adminC.loadSeriesFromJson());
        seeAvailableSeries(adminC.getAdmin().getSeries());
    }
    
    void seeAvailableSeries(ArrayList<Serie> series){
        emptyPane.setVisible(false);
        //subscriptionInfoPane.setVisible(false);
        if(series.isEmpty()){
            emptyPane.setVisible(true);
            emptyPane.setPrefHeight(510);emptyPane.setLayoutY(90);
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
            emptyPane.setVisible(true);
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
            emptyPane.setVisible(true);
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
        //ALl PANES == visible(false)
    }

    @FXML
    void search(KeyEvent event){
        if(searchField.getText().isEmpty()){
            scrollPane.setVisible(false);
            scrollPane.setPrefHeight(600);
            scrollPane.setLayoutY(0);
            newContentSearchPane.setVisible(true);
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

    //-------------------------------------------------------FIRST AIDS-----------------------------------------------------------//

    @FXML
    void returnToUserMenu(ActionEvent event){
        
        //playListPane.setVisible(false);
        try {
            userMenu(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void playerAdd(MouseEvent event, String videoPath, String addPath) {
        String pathadd = "file:///" + addPath;
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
    

   
    public UserRegistered getCurrentUser() {
        return currentUser;
    }

    public void serCurrentUSer(UserRegistered currentUser){
        this.currentUser = currentUser;
    }

    
}
