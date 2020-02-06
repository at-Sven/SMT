package view;

import datenbank.beans.HashtagsBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.HashtagsEintrag;

/**
 * The Controller Class for the FXML file "fxTableHashtags"
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

    String data;

    /**
     * This method take the selected Hashtags and save them in a String Variable
     */
    @FXML
    void selectedHashtags() {
        Stage stage = (Stage) this.btnChoose.getScene().getWindow();

        TablePosition pos = tvHashtags.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        // Item here is the table view type:
        HashtagsEintrag item = tvHashtags.getItems().get(row);

        TableColumn col = pos.getTableColumn();

        // this gives the value in the selected cell:
        data = col.getCellObservableValue(item).getValue().toString();

        stage.close();

    }

    @FXML
    void initialize() {
        getHashTable();
    }

    /**
     * This method load the Hashtags from the databases
     */
    void getHashTable() {
        ObservableList<HashtagsEintrag> entries = FXCollections.observableArrayList(HashtagsBean.getThemes());
        this.tcTheme.setCellValueFactory(new PropertyValueFactory<HashtagsEintrag, String>("theme"));
        this.tcList.setCellValueFactory(new PropertyValueFactory<HashtagsEintrag, String>("hashtags"));

        this.tvHashtags.setItems(entries);
    }

    /**
     * This method return the selected Hashtags to the caller
     *
     * @return selected Hashtags as String
     */
    public String getData() {
        if (data == null) {
            return data = "";
        }

        return data;

    }

}
