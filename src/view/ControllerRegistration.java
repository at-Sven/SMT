package view;

import datenbank.Datenbank;
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
import javafx.stage.Stage;
import model.UserEintrag;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controllerklasse für die FXML Datei fxRegistration
 */
public class ControllerRegistration {

    @FXML
    private Label lbStatusRegistration;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private Button btnRegistration;

    @FXML
    private Button btnAbortRegistration;

    UserBean userInsert = new UserBean();

    @FXML
    void createLoginAccount(ActionEvent event) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tfEmail.getText());

        if (tfEmail.getText().trim().equals("") || pwfPassword.getText().trim().equals("")) { // check if the fields are empty
            lbStatusRegistration.setText("Eine E-Mail Addresse und ein Passwort eingegeben!");
            tfEmail.requestFocus();
        } else if (matcher.matches()) { // If conditions are correct

            UserEintrag newUser = new UserEintrag(this.tfEmail.getText(), this.pwfPassword.getText());
            userInsert.insert(newUser);

            showLogin(event);

        } else {
            lbStatusRegistration.setText("Es muss eine gültige E-Mail Addresse sein");
            tfEmail.requestFocus();
        }

    }

    @FXML
    void cancelRegistration(ActionEvent event) throws IOException {
        showLogin(event);

    }

    @FXML
    void initialize() {
        // DB starten
    }

    void showLogin(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxLogin.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
