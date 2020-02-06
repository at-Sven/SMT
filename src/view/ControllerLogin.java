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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UserEintrag;
import utils.Codify;

import java.io.IOException;

/**
 * The Controller Class for the FXML file fxLogin
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
    private String userEmail;


    /**
     * Key event, if the enter key get pressed
     *
     * @param event pressed enter key
     */
    @FXML
    void enter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            btnLogin.fire();
        }
    }

    /**
     * This method check get the login information and check, if a user exist.
     * If the entered information match with the database, it opens the Dashboard
     *
     * @param event click on the "Anmelden" Button
     */
    @FXML
    void login(ActionEvent event) {

        if (tfEmail.getText().trim().equals("") || pwfPassword.getText().trim().equals("")) {
            lbStatusLogin.setText("Ihre E-Mail Addresse und Passwort eingeben!");
            tfEmail.requestFocus();
        } else if (user.isUser(this.tfEmail.getText(), Codify.PwConverter(this.pwfPassword.getText()))) {
            this.userObject = this.user.get(this.tfEmail.getText(), Codify.PwConverter(this.pwfPassword.getText())); // get the user uid,email,pw from db.#
            userEmail = this.tfEmail.getText();
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
     * This method opens the registration Window
     *
     * @param event click on the  "Registieren" Button
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
     * This method open a FXML file as new Window
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
            MainStage.setTitle("SMT - Social Media Tool - User: " + userEmail);
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
        // TODO: später auskommentieren !!!! ist nur TEST USER!!!
        tfEmail.setText("a@a.aa");
        pwfPassword.setText("aaa");
    }
}
