package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.GenreController;
import co.edu.uptc.controller.MovieController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Genre;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.model.UserRegistered;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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



public class AdminView{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane emptyPane;
    //--------------------------------------------------------------------------MOVIES ATRIBUTES--------------------------------------------------------------------------//
    @FXML
    private ScrollPane scrollMovies;
    
    @FXML
    private AnchorPane welcomePane;
    
    @FXML
    private AnchorPane titleAnchorPane;

    @FXML
    private Label titleText;

    @FXML
    private AnchorPane addMoviePane;

    @FXML
    private GridPane multimediaContentGrid;

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
    private ComboBox<String> allGenres;

    @FXML
    private VBox movieOptions;

    private String selectedGenreItem = null;

    private AdminController adminC = new AdminController();
    private UserController userC = new UserController();
    private MovieController movieC = new MovieController();
    private GenreController genreC = new GenreController();

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

    public void handleAllGenresComboBoxSelection(String newValue) {
        if (newValue != null && !newValue.equals(selectedGenreItem)) {
            System.out.println(newValue);
        }
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


    //----------------------------------------------------------------FIRST AIDS-------------------------------------------------//
    @FXML
    void player(MouseEvent event, String videoPath) {
        //ImageView clickedImageView = (ImageView) event.getSource();
        //String id = clickedImageView.getId();
        String path = "file:///" + videoPath;
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
