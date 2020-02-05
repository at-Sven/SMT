package view;

import datenbank.beans.HashtagsBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HashtagsEintrag;

/**
 * The Controller class of the fxml File fxUpdateHashtagListe
 */
public class ControllerUpdateHashtagListe {

    @FXML
    private TextField tfNewList;

    @FXML
    private TextArea taNewHashtags;

    @FXML
    private Button btnUpdateList;

    public HashtagsBean hashListInsert = new HashtagsBean();
    public HashtagsEintrag oldList;
    public HashtagsEintrag newList;

    /**
     * This method update a existing Hashtag list with updated content
     */
    @FXML
    void updateList() {
        Stage stage = (Stage) this.btnUpdateList.getScene().getWindow();

        newList = new HashtagsEintrag(oldList.getHid(), 1, this.tfNewList.getText(), taNewHashtags.getText());
        hashListInsert.update(newList, oldList);

        stage.close();

    }

    @FXML
    void initialize() {
    }

    /**
     * This method rece the Hashtag list, which geht a update
     *
     * @param oldEntry old Hashtag List from ControllerMain class
     */
    public void setOldEntry(HashtagsEintrag oldEntry) {
        oldList = oldEntry;

        tfNewList.setText(oldEntry.getTheme());
        taNewHashtags.setText(oldEntry.getHashtags());
    }
}

