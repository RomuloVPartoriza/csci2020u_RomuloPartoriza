package csci2020u.lab10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ServerUI.fxml"));
        primaryStage.setTitle("SimpleBBS Server v1.0 Lab10");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
