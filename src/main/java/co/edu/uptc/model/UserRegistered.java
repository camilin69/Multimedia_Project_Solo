package co.edu.uptc.model;

import java.util.ArrayList;

public class UserRegistered extends Person{
    private ArrayList<PlayList> playList;
    private UserSubscription sub;
    
    
    public UserRegistered(){}
    public UserRegistered(String firstName, String lastName, int id, String email, String password) {
        super(firstName, lastName, id, email, password);
        sub = null;
        playList = new ArrayList<>();
        
    }
    public ArrayList<PlayList> getplayList() {
        return playList;
    }
    public void setplayList(ArrayList<PlayList> playList) {
        this.playList = playList;
    }
    public void addplayList(PlayList playList) {
        this.playList.add(playList);
    }
    public void removeplayList(PlayList playList) {
        this.playList.remove(playList);
    }
    public UserSubscription getSub() {
        return sub;
    }
    public void setSub(UserSubscription sub) {
        this.sub = sub;
    }
    public boolean couldLogIn(String email) {
        if(email.contains("@")){
            String[] emailArray = email.split("@");
            if(emailArray.length == 2){
                if(emailArray[1].equals("uptc.edu.co")){
                    if(this.getEmail().equals(email)){
                        return true;
                    }
                }
            }
            
        }
        
        
        return false;
    }
}