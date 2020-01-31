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
 * Controllerklasse für die FXML Datei fxLogin
 */
public class ControllerLogin {

    @FXML
    private Label lbStatusLogin;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistration;

    /**
     * Diese Methode schließt nach erfolg das Login Fenster und öffnet das Main Fenster des Programmes.
     */
    @FXML
    void showMain() {
        Stage stage = (Stage) this.btnLogin.getScene().getWindow();
        stage.close();
        showNewPanel();
    }


    /**
     * Diese Methode öffnet das Registerfenster
     *
     * @param event Blick des Buttons "Registieren"
     * @throws IOException Falls die FXML Datei nicht geöffnet werden kann
     */
    @FXML
    void showRegistration(ActionEvent event) throws IOException {
        showPanel(event, "fxRegistration.fxml");
    }

    /**
     * Diese Methode öffnet eine FXML Datei im gleichen Fenster, wo der Auslöser ist.
     *
     * @param event Actionevent
     * @param route Pfad von der FXML Datei
     * @throws IOException Wenn die FXML Datei nicht geöffnet werden kann
     */
    private void showPanel(ActionEvent event, String route) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(route));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Diese Methode öffnet eine FXML Datei als komplett neues Fenster
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
