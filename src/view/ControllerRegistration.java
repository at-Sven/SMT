package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the FXML file fxRegistration
 */
public class ControllerRegistration {

    @FXML
    private Label lbFeedback;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private Button btnRegistration;

    @FXML
    void showLogin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("fxLogin.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void initialize() {
        // DB starten
    }
}
