package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.UserRegistered;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Node;

public class AdminView {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane emptyPane;

    @FXML
    private ScrollPane scrollMovies;
    
    @FXML
    private AnchorPane welcomePane;
    
    @FXML
    private AnchorPane titleAnchorPane;

    @FXML
    private Label titleText;


    @FXML
    private GridPane multimediaContentGrid;

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

    private AdminController adminC = new AdminController();
    private UserController userC = new UserController();
    private SubscriptionController subC = new SubscriptionController();

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
