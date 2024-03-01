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

import co.edu.uptc.model.Admin;
import co.edu.uptc.model.Subscription;
import javafx.scene.paint.Color;

public class AdminController {
    private Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");

    public void saveSubscriptionsToJson(ArrayList<Subscription> subs){
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir").replace("\\", "/") + "/src/main/java/co/edu/uptc/persistence/admin-user/subscriptions.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(subs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Subscription> loadSubscriptionsFromJson(String jsonFilePath) {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try (Reader reader = new FileReader(jsonFilePath)) {
            Type listType = new TypeToken<List<Subscription>>(){}.getType();
            subscriptions = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    public void downloadCover(String path, String coverURL){
        try{
            String[] command = {"curl", "-o", 
                path, coverURL};

            ProcessBuilder processBuilder = new ProcessBuilder(command);

            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Cover Downloaded Successfully");
            } else {
                System.err.println("Error, output code: " + exitCode);
            }
        } catch (IOException | InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public String toWebColor(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        int a = (int) (color.getOpacity() * 255);

        return String.format("#%02X%02X%02X%02X", r, g, b, a);
    }

    public Admin getAdmin() {
        return admin;
    }

    
}
