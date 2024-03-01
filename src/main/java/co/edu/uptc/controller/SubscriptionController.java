package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.model.UserSubscription;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SubscriptionController {
    ArrayList<Subscription> subscriptions = new ArrayList<>();

    public void createSubscription(String name, String description, int duration, double price, String cover, String path){
        Subscription newSub = new Subscription(name, duration, description, price, cover, path);
        subscriptions.add(newSub);
    }

    public void addSubToUser(Subscription sub, UserRegistered user){
        long startTime = System.currentTimeMillis();
        long endTime = startTime + sub.getDuration();  
        UserSubscription userSub = new UserSubscription(sub.getName(), sub.getDuration(), sub.getDescription(),
                         sub.getPrice(), sub.getCoverURL(), sub.getImageLocalPathURL(),
                         startTime, endTime);
        user.setSub(userSub);
    }

    public void cancelSub(UserRegistered user){
        user.setSub(null);
    }

    public int verifyInputs(TextField subscriptionNameField, Label subscriptionNameLabel,
                            TextArea subscriptionDescriptionField, Label subscriptionDescriptionLabel,
                            TextField subscriptionDurationField, Label subscriptionDurationLabel,
                            TextField subscriptionPriceField, Label subscriptionPriceLabel,
                            TextField subscriptionCoverField, Label subscriptionCoverLabel){

        int aux = 0;

        if(subscriptionNameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            aux++;
            subscriptionNameLabel.setTextFill(Color.web("#26eb44"));
            if(!subscriptionFound(subscriptionNameField.getText())){
                aux++;
                subscriptionNameLabel.setTextFill(Color.web("#26eb44"));
            }else{
                subscriptionNameLabel.setText("Subscriptions can not be repeated");
                subscriptionNameLabel.setTextFill(Color.RED);
            }
        }else{
            subscriptionNameLabel.setText("Names only contains characters");
            subscriptionNameLabel.setTextFill(Color.RED);
        }
        if(subscriptionDescriptionField.getText().matches("^[a-zA-Z0-9\\s\\p{Punct}]+$")){
            aux++;
            subscriptionDescriptionLabel.setTextFill(Color.web("#26eb44"));
        }else{
            subscriptionDescriptionLabel.setTextFill(Color.RED);
        }
        if(subscriptionDurationField.getText().matches("\\d+")){
            aux++;
            subscriptionDurationLabel.setTextFill(Color.web("#26eb44"));
        }else{
            subscriptionDurationLabel.setTextFill(Color.RED);
        }
        try{
            Double.parseDouble(subscriptionPriceField.getText());
            subscriptionPriceLabel.setTextFill(Color.web("#26eb44"));
            aux++;
        }catch(Exception e){
            subscriptionPriceLabel.setTextFill(Color.RED);
        }
        if(subscriptionCoverField.getText().startsWith("https://")){
            subscriptionCoverLabel.setTextFill(Color.web("#26eb44"));
            aux++;
        }else{
            subscriptionCoverLabel.setTextFill(Color.RED);
        }
        return aux;
    }

    public int verifyInputsToEdit(String oldName, TextField subscriptionNameField, Label subscriptionNameLabel,
                            TextArea subscriptionDescriptionField, Label subscriptionDescriptionLabel,
                            TextField subscriptionDurationField, Label subscriptionDurationLabel,
                            TextField subscriptionPriceField, Label subscriptionPriceLabel,
                            TextField subscriptionCoverField, Label subscriptionCoverLabel){

        int aux = 0;

        if(subscriptionNameField.getText().matches("^[a-zA-Z0-9\\s]+$")){
            aux++;
            subscriptionNameLabel.setTextFill(Color.web("#26eb44"));
            if(oldName.equals(subscriptionNameField.getText())){
                aux++;
            }else{
                if(!subscriptionFound(subscriptionNameField.getText())){
                    aux++;
                    subscriptionNameLabel.setTextFill(Color.web("#26eb44"));
                }else{
                    subscriptionNameLabel.setText("Subscriptions can not be repeated");
                    subscriptionNameLabel.setTextFill(Color.RED);
                }
            }
            
        }else{
            subscriptionNameLabel.setText("Names only contains characters");
            subscriptionNameLabel.setTextFill(Color.RED);
        }
        if(subscriptionDescriptionField.getText().matches("^[a-zA-Z0-9\\s\\p{Punct}]+$")){
            aux++;
            subscriptionDescriptionLabel.setTextFill(Color.web("#26eb44"));
        }else{
            subscriptionDescriptionLabel.setTextFill(Color.RED);
        }
        if(subscriptionDurationField.getText().matches("\\d+")){
            aux++;
            subscriptionDurationLabel.setTextFill(Color.web("#26eb44"));
        }else{
            subscriptionDurationLabel.setTextFill(Color.RED);
        }
        try{
            Double.parseDouble(subscriptionPriceField.getText());
            subscriptionPriceLabel.setTextFill(Color.web("#26eb44"));
            aux++;
        }catch(Exception e){
            subscriptionPriceLabel.setTextFill(Color.RED);
        }
        if(subscriptionCoverField.getText().startsWith("https://")){
            subscriptionCoverLabel.setTextFill(Color.web("#26eb44"));
            aux++;
        }else{
            subscriptionCoverLabel.setTextFill(Color.RED);
        }
        return aux;
    }

    public boolean subscriptionFound(String subName){
        for(Subscription sub: subscriptions){
            if(sub.getName().equals(subName)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
    public void removeSubscription(Subscription sub){
        subscriptions.remove(sub);
    }

    
}
