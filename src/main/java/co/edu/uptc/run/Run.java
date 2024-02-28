package co.edu.uptc.run;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application {

    public void start(Stage stage) throws IOException{
    
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uptc/persistence/fxmlFiles/userView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
 
    }
    public static void main(String[] args) {
        System.out.println("JavaFX Runtime Version: " + System.getProperties().getProperty("javafx.runtime.version"));
        launch(args);
    }
}

