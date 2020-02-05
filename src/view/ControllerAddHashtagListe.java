package view;

import java.net.URL;
import java.util.ResourceBundle;

import datenbank.beans.HashtagsBean;
import datenbank.beans.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HashtagsEintrag;
import model.UserEintrag;

import static utils.Codify.PwConverter;

public class ControllerAddHashtagListe {

    @FXML
    private TextField tfNewList;

    @FXML
    private TextArea taNewHashtags;

    @FXML
    private Button btnAddNewList;

    UserBean user;
    public HashtagsBean hashListInsert = new HashtagsBean();
    public HashtagsEintrag newList;

    @FXML
    void addNewList(ActionEvent event) {
        Stage stage = (Stage) this.btnAddNewList.getScene().getWindow();

        newList = new HashtagsEintrag(null, 1, this.tfNewList.getText(), taNewHashtags.getText());
        hashListInsert.insert(newList);

        stage.close();
    }

    @FXML
    void initialize() {

    }
}
