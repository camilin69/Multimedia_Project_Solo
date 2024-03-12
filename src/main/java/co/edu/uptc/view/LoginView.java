package co.edu.uptc.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginView implements Initializable{
    @FXML
    private AnchorPane registerUserPane;

    @FXML
    private MediaView backgroundLogin;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label emailLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    //-------------------------------REGISTER USER ATRIBUTES-------------------------//
    @FXML
    private TextField firstNameField;
    @FXML
    private Label firstNameLabel;
    @FXML
    private TextField lastNameField;
    @FXML
    private Label lastNameLabel;
    @FXML
    private TextField emailRegisterField;
    @FXML
    private Label emailRegisterLabel;
    @FXML
    private TextField passwordRegisterField;
    @FXML
    private Label minCharactersLabel;
    @FXML
    private Label specialCharactersLabel;
    @FXML
    private Label uppercaseLabel;
    @FXML
    private Label lowercaseLabel;
    @FXML
    private Label minNumberLabel;
    @FXML
    private TextField passwordToMatchField;
    @FXML
    private Label passwordToMatchLabel;

    private AdminController adminC = new AdminController();
    private UserController userC = new UserController();

    //--------------------------------------------------LOGIN-------------------------------------------------------------------//

    public void initialize(URL location, ResourceBundle resources) {
        emailField.setText("508a@uptc.edu.co");
        passwordField.setText("Cami1234...");
        // emailField.setText("Elon1@uptc.admin.co");
        // passwordField.setText("1");
        Media media = new Media("file:///" + System.getProperty("user.dir").replace("\\", "/").replace(" ", "%20") + "/src/main/java/co/edu/uptc/persistence/icons/backgroundLoginMenu.mp4");
        MediaPlayer player = new MediaPlayer(media);
        backgroundLogin.setMediaPlayer(player);

        backgroundLogin.setPreserveRatio(false);
        player.setVolume(0);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();

    }

    @FXML
    void logIn(ActionEvent event) throws IOException{
        if(adminC.getAdmin().couldLogIn(emailField.getText())){
            if(passwordField.getText().equals(adminC.getAdmin().getPassword())){
                AdminView av = new AdminView();
                av.adminMenu(event);
            }else{
                passwordLabel.setTextFill(Color.RED);
            }
        }else if(userC.userFound(emailField.getText())){
            if(userC.getCurrentUser().getPassword().equals(passwordField.getText())){
                UserView uv = new UserView();
                userC.saveCurrentUserToJson();
                uv.userMenu(event);
                passwordLabel.setTextFill(Color.web("#006CFF"));
            }else{
                passwordLabel.setTextFill(Color.RED);
            }
        }else{
            emailField.setStyle("-fx-prompt-text-fill: red;");
            emailLabel.setText("User not found");
            emailLabel.setTextFill(Color.RED);
        }
        
    }

    //------------------------------------------------USER REGISTER-------------------------------------------------------//
    @FXML
    void registerUserMenu(ActionEvent event){
        registerUserPane.setVisible(true);
    }

    @FXML
    void registerUser(ActionEvent event){
        int aux = userC.verifyRegisterUserInputs(firstNameField, firstNameLabel, lastNameField, lastNameLabel,
         emailRegisterField, emailRegisterLabel, passwordRegisterField, minCharactersLabel, specialCharactersLabel,
          uppercaseLabel, lowercaseLabel, minNumberLabel, passwordToMatchField, passwordToMatchLabel);
        if(aux == 9){
            int id = userC.assignidUser();
            userC.createUser(firstNameField.getText(), lastNameField.getText(), id, 
            id + emailRegisterField.getText(), passwordRegisterField.getText());
            userC.saveUsersToJson();

            Stage advertiseStage = new Stage();
            advertiseStage.initStyle(StageStyle.UTILITY); 
            advertiseStage.initModality(Modality.APPLICATION_MODAL); 
            Label advertiseText = new Label("Account Created succesfully!\n" + "Email: " + id + emailRegisterField.getText() +
                                            "\nPassword: " + passwordRegisterField.getText());
            Button closeButton = new Button("Got It");
            closeButton.setOnAction(e -> advertiseStage.close());
            VBox advertiseWindow = new VBox(10);
            advertiseWindow.setAlignment(Pos.CENTER);
            advertiseWindow.getChildren().addAll(advertiseText,closeButton);
            Scene scene = new Scene(advertiseWindow, 200, 100);
            advertiseStage.setTitle("User Account");
            advertiseStage.setScene(scene);
            advertiseStage.showAndWait(); 
            registerUserPane.setVisible(false);
        }
    }

    //--------------------------------------------FIRST AIDS---------------------------------------------------------------//
    @FXML
    void getEmail(KeyEvent event) {
        if(!emailField.getText().contains("@uptc.admin.co") && !emailField.getText().contains("@uptc.edu.co")){
            emailLabel.setText("User Incorrect");
            emailLabel.setTextFill(Color.RED);
            passwordLabel.setTextFill(Color.web("#021024"));
        }else{
            passwordLabel.setTextFill(Color.web("##021024"));
            emailLabel.setTextFill(Color.web("##021024"));
        }
    }

    @FXML
    void returnToLogin(ActionEvent event) throws IOException{
        registerUserPane.setVisible(false);
    }

    


}