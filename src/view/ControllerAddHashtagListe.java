package view;

import datenbank.beans.HashtagsBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HashtagsEintrag;

/**
 * The Controller class of the fxml File fxAddHashtagListe
 */
public class ControllerAddHashtagListe {

    @FXML
    private TextField tfNewList;

    @FXML
    private TextArea taNewHashtags;

    @FXML
    private Button btnAddNewList;

    public HashtagsBean hashListInsert = new HashtagsBean();
    public HashtagsEintrag newList;

    /**
     * This method add a new Hashtag list in the database
     */
    @FXML
    void addNewList() {
        Stage stage = (Stage) this.btnAddNewList.getScene().getWindow();

        newList = new HashtagsEintrag(null, 1, this.tfNewList.getText(), taNewHashtags.getText());
        hashListInsert.insert(newList);

        stage.close();
    }

    @FXML
    void initialize() {

    }

}
