package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
    private SubscriptionController subC = new SubscriptionController();



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
                emptyPane.setVisible(true);
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
    void subscriptionEnteredButtonManagement(MouseEvent event){
        subscriptionButton.setStyle("-fx-background-color: #00B9E5;");
        subscriptionButton.setFont(new Font("SimSun", 26));
    }

    @FXML
    void subscriptionExitedButtonManagement(MouseEvent event){
        subscriptionButton.setStyle("-fx-background-color:  #3AA6E0;");
        subscriptionButton.setFont(new Font("SimSun", 24));
    }
    

   
    public UserRegistered getCurrentUser() {
        return currentUser;
    }

    public void serCurrentUSer(UserRegistered currentUser){
        this.currentUser = currentUser;
    }

    
}
