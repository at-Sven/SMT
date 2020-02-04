package view;

import datenbank.beans.UserBean;
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
import model.UserEintrag;
import utils.Codify;

import java.io.IOException;

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

    private UserBean user = new UserBean();
    private UserEintrag userObject = new UserEintrag();


    @FXML
    void login(ActionEvent event) {

        if (tfEmail.getText().trim().equals("") || pwfPassword.getText().trim().equals("")) {
            lbStatusLogin.setText("Ihre E-Mail Addresse und Passwort eingeben!");
            tfEmail.requestFocus();
        } else if (user.isUser(this.tfEmail.getText(), Codify.PwConverter(this.pwfPassword.getText()))) {
            this.userObject = this.user.get(this.tfEmail.getText(), Codify.PwConverter(this.pwfPassword.getText())); // get the user uid,email,pw from db.
            showMain();
        } else {
            lbStatusLogin.setText("Account nicht vorhanden");
        }

    }

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
        Parent root = FXMLLoader.load(getClass().getResource("fxRegistration.fxml"));
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            ControllerMain controllerMain = loader.getController();  // get the controller of fxMain
            controllerMain.setUser(this.userObject); // sets the MainUser in ControllerMain Object for further usage

            Scene scene = new Scene(root);
            MainStage.setTitle("SMT - Social Media Tool");
            MainStage.setScene(scene);
            MainStage.setX(300);
            MainStage.setY(150);
            MainStage.setResizable(false);
            MainStage.show();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
