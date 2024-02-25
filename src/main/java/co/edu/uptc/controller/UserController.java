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

public class UserController {
    private ArrayList<UserRegistered> users = new ArrayList<>();
    private UserRegistered currentUser;

    
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
