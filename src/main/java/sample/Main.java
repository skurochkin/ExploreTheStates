package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;

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
    }
}
