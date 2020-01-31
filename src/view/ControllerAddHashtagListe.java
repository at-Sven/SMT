package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerAddHashtagListe {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfNewList;

    @FXML
    private TextArea taNewHashtags;

    @FXML
    private Button btnAddNewList;

    @FXML
    void addNewList(ActionEvent event) {
        Stage stage = (Stage) this.btnAddNewList.getScene().getWindow();
        System.out.println("Hashtags ausgewählt");
        stage.close();
    }

    @FXML
    void initialize() {

    }
}
