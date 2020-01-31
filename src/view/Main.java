package view;

import datenbank.Datenbank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Die Main Klasse des Programmes SMT
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxLogin.fxml"));
        primaryStage.setTitle("SMT - Social Media Tool: Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setX(600);
        primaryStage.setY(300);
        primaryStage.show();
    }


    public static void main(String[] args) {

        Datenbank.getInstance();

        launch(args);

    }
}
