package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.HashtagsEintrag;

/**
 * Controllerklasse für die FXML Datei fxTableHashtags
 */
public class ControllerTableHashtags {

    @FXML
    private TableView<HashtagsEintrag> tvHashtags;

    @FXML
    private TableColumn<HashtagsEintrag, String> tcTheme;

    @FXML
    private TableColumn<HashtagsEintrag, String> tcList;

    @FXML
    private TableColumn<HashtagsEintrag, String> tcChoise;

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
