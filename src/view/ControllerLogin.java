package view;

import java.io.IOException;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Control class for the FXML file fxLogin
 */
public class ControllerLogin {

    @FXML
    private Label lbFeedback;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistration;

    /**
     * This method close the Login window and opens the main program window
     */
    @FXML
    void showMain() {
        Stage stage = (Stage) this.btnLogin.getScene().getWindow();
        stage.close();
        showNewPanel();
    }


    /**
     * This method opens the fmxl file fxRegistration
     *
     * @param event click on the Button "Registieren"
     * @throws IOException if the fxml file can't be opened
     */
    @FXML
    void showRegistration(ActionEvent event) throws IOException {
        showPanel(event, "fxRegistration.fxml");
    }

    /**
     * This method opens a fmxl file in the same window, where it will called
     *
     * @param event click on the Button "Anmelden"
     * @param route Path of the FXML file
     * @throws IOException if the fxml file can't be opened
     */
    private void showPanel(ActionEvent event, String route) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(route));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * This method opens a FXML file in a new window
     */
    private void showNewPanel() {
        try {
            Stage MainStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("fxMain.fxml").openStream());
            Scene scene = new Scene(root);
            MainStage.setTitle("SMT - Social Media Tool");
            MainStage.setScene(scene);
            MainStage.setX(300);
            MainStage.setY(150);
            MainStage.show();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    void initialize() {
        // DB starten?
    }
}
