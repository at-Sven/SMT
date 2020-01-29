package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Controllerklasse für die FXML Datei fxTableHashtags
 */
public class ControllerTableHashtags {

    @FXML
    private TableView<?> tvHashtags;

    @FXML
    private TableColumn<?, ?> tcTheme;

    @FXML
    private TableColumn<?, ?> tcList;

    @FXML
    private TableColumn<?, ?> tcChoise;

    @FXML
    private Button btnChoose;

    @FXML
    void selectedHashtags(ActionEvent event) {
        Stage stage = (Stage) this.btnChoose.getScene().getWindow();
        System.out.println("Hashtags ausgewählt");
        stage.close();
    }

    @FXML
    void initialize() {
        // DB starten?
    }
}
