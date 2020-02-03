package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import socialmedia.SocialMediaWorker;

/**
 * Controller class for the FXML file 'fxMain'
 */
public class ControllerMain {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private CheckBox cbFacebook;

    @FXML
    private CheckBox cbTwitter;

    @FXML
    private Button btnChooseHashtag;

    @FXML
    private Button btnRandomHashtag;

    @FXML
    private Button btnRandmDateTime;

    @FXML
    private Button btnSavePost;

    @FXML
    private Button btnResetFields;

    @FXML
    private TextArea taMessage;

    @FXML
    private TextArea taHashtags;

    @FXML
    private ListView<?> lvFBGruppen;

    @FXML
    private Label lbRestChar;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfTime;

    @FXML
    private ComboBox<?> cbFBGruppen;

    @FXML
    private ToggleButton tbActivate;

    @FXML
    private Button btnPicture;

    @FXML
    private Label lbMessageStatus;

    @FXML
    private Label lbFilename;

    @FXML
    private TableView<?> tvPosts;

    @FXML
    private TableColumn<?, ?> tcText;

    @FXML
    private TableColumn<?, ?> tcDate1;

    @FXML
    private TableColumn<?, ?> tcPlatform;

    @FXML
    private TableColumn<?, ?> tcReaction;

    @FXML
    private TableColumn<?, ?> tcPostAction;

    @FXML
    private TableView<?> tvHashtags;

    @FXML
    private TableColumn<?, ?> tcTheme;

    @FXML
    private TableColumn<?, ?> tcList;

    @FXML
    private TableColumn<?, ?> tcHashAction;

    @FXML
    private Button btnNewList;

    @FXML
    private Button btnLogSave;

    @FXML
    private TextField btnFBUsername;

    @FXML
    private TextField btnFBPasswort;

    @FXML
    private TextField btnFBToken;

    @FXML
    private TextField btnFBKey;

    @FXML
    private TextField ConsumerKey;

    @FXML
    private TextField ConsumerSecret;

    @FXML
    private TextField AccessToken;

    @FXML
    private TextField AccessTokenSecret;

    @FXML
    private Button btnSaveSettingsTW;

    @FXML
    private Button btnSaveSettingsFB;

    @FXML
    private TextArea taLog;

    @FXML
    private Label lbLogSavedFeedback;

    File selectedFile;
    Timeline socialMediaWorkerTimer; // controls the SocialMediaWorker object
    SocialMediaWorker socialMediaWorker; // checks what/when to post to Social Media
    int workerWaitSeconds = 3; // here use later 60 seconds, 3sec only for testing the loop

    /**
     * This method opens the FXML file fxTableHashtags in a small window
     */
    @FXML
    void ShowHashtags() {
        try {
            Stage MainStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("fxTableHashtags.fxml").openStream());
            Scene scene = new Scene(root);
            MainStage.setTitle("SMT - Social Media Tool: Hashtags");
            MainStage.setScene(scene);
            MainStage.setX(400);
            MainStage.setY(300);
            MainStage.show();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    /**
     * This method clear all fields and the selected file on the FXML file fxMain
     */
    @FXML
    void clearFields() {
        this.taMessage.setText("");
        this.taHashtags.setText("");
        this.dpDate.getEditor().clear();
        this.tfTime.setText("");

        selectedFile = null;
        lbFilename.setText("Keine Bild oder Film ausgewählt");
    }

    /**
     * This method opens a File Chooser for Pictures and Video Files
     */
    @FXML
    void MediaChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Bild oder Video anhängen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Bild Formate", "*.jpg", "*.jpeg", "*.gif", "*.png"),
                new FileChooser.ExtensionFilter("Video Formate", "*.avi", "*.mov", "*.mp4", "*.mpeg", "*.wmv", "*.ogg")
        );
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            lbFilename.setText("Datei: " + selectedFile.getName());
        } else {
            lbFilename.setText("Keine Bild oder Film ausgewählt");
        }

    }

    /**
     * This method opens the FXML file fxAddHashtagListe in a new Window
     */
    @FXML
    void createNewHashList() {
        try {
            Stage MainStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("fxAddHashtagListe.fxml").openStream());
            Scene scene = new Scene(root);
            MainStage.setTitle("SMT - Social Media Tool: Hashtags");
            MainStage.setScene(scene);
            MainStage.setX(400);
            MainStage.setY(300);
            MainStage.show();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    /**
     * This method enable or disable the automatic posting of Messages
     */
    @FXML
    void postAutomatic() {
        if (this.tbActivate.isSelected()) {
            this.socialMediaWorker.init();// here we need to init() socialmediaworker with the posts from DB
            this.socialMediaWorkerTimer.play();
            this.tbActivate.setStyle("-fx-background-color:green");
            //System.out.println("Selected: " + tbActivate.isSelected());
            System.out.println("Automatisierung ist aktiviert");
        } else {
            this.socialMediaWorkerTimer.stop();
            this.tbActivate.setStyle("-fx-background-color:lightgrey");
            //System.out.println("Selected: " + tbActivate.isSelected());
            System.out.println("Automatisierung ist deaktiviert");
        }

    }

    /**
     * This method add generated Posts in the Database
     * @param event
     */
    @FXML
    void postMessage(ActionEvent event) {
        // Speichert einen neuen Post in die Datenbank

        lbMessageStatus.setText("Nachricht wurde gespeichert!");
        resetText(lbMessageStatus);

    }

    /**
     * This method counts the Content of the Hahshtag TextArea for the Label lbRestChar
     */
    @FXML
    void countHashtag() {
        //While writing/entering the Tags, it counts the total character length of post and tags
        String post = taMessage.getText();
        String tag = taHashtags.getText();
        String tlen = taMessage.getText() + taHashtags.getText();

        int len = post.length() + tag.length();
        String msg = len + " / 480 Zeichen";
        lbRestChar.setText(msg);

        //The text in post and hashtag must turn Red when the total characters limit exceeds
        if (tlen.length() > 63206) {
            taMessage.setStyle("-fx-text-inner-color: red;");
            taHashtags.setStyle("-fx-text-inner-color: red;");

            //The check box for twitter must be unchecked and disabled when the total characters limit exceeds 280 characters
        } else if (tlen.length() > 480) {
            taMessage.setStyle("-fx-text-inner-color: black;");
            taHashtags.setStyle("-fx-text-inner-color: black;");
            cbTwitter.setSelected(false);
            cbTwitter.setDisable(true);

            //The text must set to default color(black) when the total characters is reduced to the limit-
        } else {
            taMessage.setStyle("-fx-text-inner-color: black;");
            taHashtags.setStyle("-fx-text-inner-color: black;");
            cbTwitter.setDisable(false);
            //Syamala
        }
    }

    /**
     * This method counts the Content of the Message TextArea for the Label lbRestChar
     */
    @FXML
    void countPost() {
        //While writing the Post, it counts the total character length of post and tags
        String post = taMessage.getText();
        String tag = taHashtags.getText();
        String tlen = taMessage.getText() + taHashtags.getText();

        int len = post.length() + tag.length();
        String msg = len + " / 480 Zeichen";
        lbRestChar.setText(msg);

        //The text in post and hashtag must turn Red when the total characters limit exceeds
        if (tlen.length() > 63206) {
            taMessage.setStyle("-fx-text-inner-color: red;");
            taHashtags.setStyle("-fx-text-inner-color: red;");

            //The check box for twitter must be unchecked and disabled when the total characters limit exceeds 280 characters
        } else if (tlen.length() > 480) {
            taMessage.setStyle("-fx-text-inner-color: black;");
            taHashtags.setStyle("-fx-text-inner-color: black;");
            cbTwitter.setSelected(false);
            cbTwitter.setDisable(true);
            //The text must set to default color(black) when the total characters is reduced to the limit 255 charcaters
        } else {
            taMessage.setStyle("-fx-text-inner-color: black;");
            taHashtags.setStyle("-fx-text-inner-color: black;");
            cbTwitter.setDisable(false);
        }
    }

    @FXML
    void randomDateTime(ActionEvent event) {
        //When clicked ,must generate Random Date and Time
        Instant jetzt = Instant.now();
        Instant einWoche = Instant.now().plus(Duration.ofDays(7));
        Instant randomInstant = zwischen(jetzt, einWoche);
        Date randonDate = Date.from(randomInstant);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String randomTime = timeFormat.format(randonDate);
        dpDate.setValue(randomInstant.atZone(ZoneId.systemDefault()).toLocalDate());
        tfTime.setText(randomTime);
    }


    private Instant zwischen(Instant jetzt, Instant einWoche) {
        long anfang = jetzt.getEpochSecond();
        long ende = einWoche.getEpochSecond();
        long randomSeconds = ThreadLocalRandom.current().nextLong(anfang, ende);
        return Instant.ofEpochSecond(randomSeconds);
    }

    /**
     * This method saves the Content of the TextArea 'taLog' in a txt File
     */
    @FXML
    void saveLog() {
        try {
            ObservableList<CharSequence> paragraph = taLog.getParagraphs();
            Iterator<CharSequence> iter = paragraph.iterator();
            BufferedWriter bf;
            bf = new BufferedWriter(new FileWriter(new File("SMT_Log_Report.txt")));
            while (iter.hasNext()) {
                CharSequence seq = iter.next();
                bf.append(seq);
                bf.newLine();
            }
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLogSavedFeedback.setText("Log als Datei gespeichert!");
        resetText(lbLogSavedFeedback);
    }

    /**
     * This method save the Account Details from the tab 'Einstellungen' in the Database
     * @param event description
     */
    @FXML
    void saveSettings(ActionEvent event) {
        // Speichert die Accountdaten der Profilen
    }

    /**
     * This method initialalizes the processes like the SocialMediaWorkerTimer etc.
     */
    @FXML
    void initialize() {
        this.tbActivate.setSelected(false);  // on start set automated posting to false.
        this.tbActivate.setStyle("-fx-background-color:lightgrey");
        this.socialMediaWorker = new SocialMediaWorker();
        this.socialMediaWorkerTimer = new Timeline(new KeyFrame(javafx.util.Duration.seconds(workerWaitSeconds), this.socialMediaWorker));
        this.socialMediaWorkerTimer.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * This method reset the content of a Label Component after 2,5 seconds
     *
     * @param label Label in which the text should be reset
     */
    void resetText(Label label) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                label.setText("");
            }
        });
        new Thread(sleeper).start();
    }

}
