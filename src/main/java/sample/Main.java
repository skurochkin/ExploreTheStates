package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static sample.JsonReader.readJsonFromUrl;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
            primaryStage.setTitle("Explore The States");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, JSONException {
        launch(args);
        JSONObject json = readJsonFromUrl("http://services.groupkt.com/state/get/USA/all");
        System.out.println(json.toString());
        System.out.println(json.get("result"));

    }
}
