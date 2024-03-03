package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.model.UserSubscription;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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

    public int verifyInputsPaidMethod(TextField cardField, Label cardLabel,
                            TextField mmddField, Label mmddLabel,
                            TextField cvvField, Label cvvLabel,
                            CheckBox credit, CheckBox debit, ComboBox<String> banks){

        int aux = 0;

        if(!credit.isSelected() && !debit.isSelected()){
            credit.setTextFill(Color.web("#cd1919"));debit.setTextFill(Color.web("#cd1919"));
            Timeline effect = new Timeline(new KeyFrame(Duration.seconds(2), e -> {credit.setTextFill(Color.WHITE);debit.setTextFill(Color.WHITE);}));
            effect.setCycleCount(1);
            effect.play();
        }else{
            aux++;
        }
        
        String[] proveCard = cardField.getText().split("-");
        if(proveCard.length == 4){
            for(String p: proveCard){
                if(p.matches("^[0-9\\s]+$") && p.length() == 4){
                    //aux == 4
                    aux++;
                    cardLabel.setTextFill(Color.web("#021024"));
                }else{  
                    cardLabel.setTextFill(Color.web("#cd1919"));
                    break;
                }
            }
        }else{
            effect(cardField);
            cardLabel.setTextFill(Color.web("#cd1919"));
        }
        
        String[] proveMMDD = mmddField.getText().split("/");
        if(mmddField.getText().contains("/")){
            if(proveMMDD.length == 2){
                for(String p: proveMMDD){
                    if(p.matches("^[0-9\\s]+$") && p.length() == 2){
                        //aux == 2
                        aux++;
                        mmddLabel.setTextFill(Color.web("#021024"));
                    }else{
                        mmddLabel.setTextFill(Color.web("#cd1919"));
                        break;
                    }
                }
            }
        }else{
            effect(mmddField);
            mmddLabel.setTextFill(Color.web("#cd1919"));
        }
        

        if(cvvField.getText().matches("^[0-9\\s]+$")){
            if(cvvField.getText().length() == 3){
                aux++;
                cvvLabel.setTextFill(Color.web("#021024"));
            }else{
                cvvLabel.setTextFill(Color.web("#cd1919"));
            }
        }else{
            effect(cvvField);
            cvvLabel.setTextFill(Color.web("#cd1919"));
        }

        if(banks.getSelectionModel().getSelectedItem() != null){
            aux++;
        }else{
            effect(banks);
        }
        return aux;
    }

    public int verifyPersonInformation(TextField paymentFirstNameField, Label paymentFirstNameLabel,
                                       TextField paymentLastNameField, Label paymentLastNameLabel,
                                       TextField paymentIdField, Label paymentIdLabel, DatePicker date){

        int aux = 0;

        if(paymentFirstNameField.getText().matches("^[a-zA-Z\\s]+$")){
            paymentFirstNameLabel.setTextFill(Color.web("#021024"));
            aux++;
        }else{
            effect(paymentFirstNameField);
            paymentFirstNameLabel.setTextFill(Color.web("#ff4848"));
        }
        if(paymentLastNameField.getText().matches("^[a-zA-Z\\s]+$")){
            paymentLastNameLabel.setTextFill(Color.web("#021024"));
            aux++;
        }else{
            effect(paymentLastNameField);
            paymentFirstNameLabel.setTextFill(Color.web("#ff4848"));
        }
        if(paymentIdField.getText().matches("^[0-9\\s]+$")){
            paymentIdLabel.setTextFill(Color.web("#021024"));
            aux++;
        }else{
            effect(paymentIdField);
            paymentIdLabel.setTextFill(Color.web("#ff4848"));
        }
        if(date.getValue() != null){
            aux++;
        }else{
            date.setOpacity(40);
            date.setStyle("-fx-background-color: #cd1919;");
            Timeline effect = new Timeline(new KeyFrame(Duration.seconds(2), e -> {date.setStyle("-fx-background-color: #ffffff;");date.setOpacity(100);}));
            effect.setCycleCount(1);
            effect.play();
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

    public void effect(TextField field){
        field.setOpacity(40);
        field.setStyle("-fx-background-color: #cd1919;");
        Timeline effect = new Timeline(new KeyFrame(Duration.seconds(2), e -> {field.setStyle("-fx-background-color: #ffffff;");field.setOpacity(100);}));
        effect.setCycleCount(1);
        effect.play();
    }

    public void effect(ComboBox<String> box){
        box.setOpacity(40);
        box.setStyle("-fx-background-color: #cd1919;");
        Timeline effect = new Timeline(new KeyFrame(Duration.seconds(2), e -> {box.setStyle("-fx-background-color: #ffffff;");box.setOpacity(100);}));
        effect.setCycleCount(1);
        effect.play();
    }

    
}
