package co.edu.uptc.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.UserRegistered;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class UserController {
    private ArrayList<UserRegistered> users = new ArrayList<>();
    private UserRegistered currentUser;

    public void createUser(String firstName, String lastName, int id, String email, String password){
        UserRegistered newUser = new UserRegistered(firstName, lastName, id, email, password);
        users.add(newUser);

    }

    public int verifyRegisterUserInputs(TextField firstNameField, Label firstNameLabel,
    TextField lastNameField, Label lastNameLabel, TextField emailRegisterField, Label emailRegisterLabel,
    TextField passwordRegisterField, Label minCharactersLabel, Label specialCharactersLabel, Label uppercaseLabel,
    Label lowercaseLabel, Label minNumberLabel, TextField passwordToMatchField, Label passwordToMatchLabel){
        int aux = 0;
        if(firstNameField.getText().matches("^[a-zA-Z\\s]+$")){
            aux++;
            firstNameLabel.setTextFill(Color.web("#cda6ff"));
        }else{
           firstNameLabel.setTextFill(Color.RED); 
        }
        if(lastNameField.getText().matches("^[a-zA-Z\\s]+$")){
            aux++;
            lastNameLabel.setTextFill(Color.web("#cda6ff"));
        }else{
           lastNameLabel.setTextFill(Color.RED); 
        }
        
        if(emailRegisterField.getText().contains("@uptc.edu.co")){  
            aux++;
            emailRegisterLabel.setTextFill(Color.web("#cda6ff"));
        }else{
            emailRegisterLabel.setTextFill(Color.RED);
        }
        if(passwordRegisterField.getText().matches(".{8,}$")){
            minCharactersLabel.setTextFill(Color.web("#cda6ff"));
            aux++;
        }else{
            minCharactersLabel.setTextFill(Color.RED);
        }
        if(verifyPasswordAux(passwordRegisterField.getText(), "[!@#$%^&*(),.?\":{}|<>]")){
            aux++;
            specialCharactersLabel.setTextFill(Color.web("#cda6ff"));
        }else{
            specialCharactersLabel.setTextFill(Color.RED);
        }
        if(verifyPasswordAux(passwordRegisterField.getText(), "[A-Z]")){
            aux++;
            uppercaseLabel.setTextFill(Color.web("#cda6ff"));
        }else{
            uppercaseLabel.setTextFill(Color.RED);
        }
        if(verifyPasswordAux(passwordRegisterField.getText(), "[a-z]")){
            aux++;
            lowercaseLabel.setTextFill(Color.web("#cda6ff"));
        }else{
            lowercaseLabel.setTextFill(Color.RED);
        }
        if(verifyPasswordAux(passwordRegisterField.getText(), "[0-9]")){
            aux++;
            minNumberLabel.setTextFill(Color.web("#cda6ff"));
        }else{
            minNumberLabel.setTextFill(Color.RED);
        }
        if(passwordRegisterField.getText().equals(passwordToMatchField.getText())){
            passwordToMatchLabel.setTextFill(Color.web("#cda6ff"));
            aux++;
        }else{
            passwordToMatchLabel.setTextFill(Color.RED);
        }

        return aux;
    }

    public int assignidUser() {
        int aux = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        for (UserRegistered u : users) {
            ids.add(u.getId());
        }
        while (true && aux == 0) {
            aux = (int) (Math.random() * 1000);
            if (!ids.contains(aux)) {
                break;
            }
        }
        return aux;
    }

    public void updateUsers(UserRegistered currentUser){
        for(UserRegistered user: users){
            if(user.getId() == currentUser.getId()){
                users.remove(user);
                users.add(currentUser);
                break;
            }
        }
    }
    public boolean verifyPasswordAux(String password, String regex){
        boolean rta = false;
        char[] passwordChar = password.toCharArray();
        for(int i = 0; i < passwordChar.length && true; i++){
            if(String.valueOf(passwordChar[i]).matches(regex)){
                rta = true;
                break;
            }else if(i == passwordChar.length-1){return false;}
        }
        return rta;
    }

    public void saveUsersToJson(){
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/users.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCurrentUserToJson(){
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/currentUser.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(currentUser, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserRegistered> loadUsersFromJson() {
        ArrayList<UserRegistered> users = new ArrayList<>();

        try (Reader reader = new FileReader(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/users.json")) {

            Type listType = new TypeToken<List<UserRegistered>>(){}.getType();
            users = new Gson().fromJson(reader, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public UserRegistered loadCurrentUserFromJson() {
        UserRegistered currentUser = new UserRegistered();

        try (Reader reader = new FileReader(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/currentUser.json")) {

            Type listType = new TypeToken<UserRegistered>(){}.getType();
            currentUser = new Gson().fromJson(reader, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentUser;
    }


    

    public boolean userFound(String email){
        for(UserRegistered user: users){
            if(user.couldLogIn(email)){
                currentUser = user;
                return true;
            }
        }
        return false;
    }
    public ArrayList<UserRegistered> getUsers() {
        return users;
    }

    public UserRegistered getCurrentUser() {
        return currentUser;
    }

    public void setUsers(ArrayList<UserRegistered> users) {
        this.users = users;
    }

    public void setCurrentUser(UserRegistered currentUser) {
        this.currentUser = currentUser;
    }
}

