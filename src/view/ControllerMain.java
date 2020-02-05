package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import datenbank.beans.HashtagsBean;

import datenbank.beans.PostEintragBean;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.HashtagsEintrag;
import model.PostEintrag;
import model.SocialmediaAccount;
import model.UserEintrag;
import socialmedia.SocialMediaWorker;
import datenbank.beans.SocialmediaAccountBean;
import utils.Helper;

/**
 * Controller class for the FXML file 'fxMain'
 */
public class ControllerMain {

    @FXML
    public Label lblSaveTWAccountStatus;

    @FXML
    public Label lblSaveFBAccountStatus;

    @FXML
    public Tab tabEinstellungen; // Tab name id

    @FXML
    public Button btnRandmDateTime;
    @FXML
    public Tab tabAllePosts;

    @FXML
    public Tab tabHashtags;

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
    private Button btnRandomDateTime;

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
    private TableView<PostEintrag> tvPosts;

    @FXML
    private TableColumn<PostEintrag, String> tcText;

    @FXML
    private TableColumn<PostEintrag, String> tcDate;

    @FXML
    private TableColumn<PostEintrag, String> tcPlatform;

    @FXML
    private TableColumn<PostEintrag, String> tcReaction;

    @FXML
    private TableColumn<PostEintrag, String> tcPostAction;

    @FXML
    private TableView<HashtagsEintrag> tvHashtags;

    @FXML
    private TableColumn<HashtagsEintrag, String> tcTheme;

    @FXML
    private TableColumn<HashtagsEintrag, String> tcHashtags;

    @FXML
    private TableColumn<HashtagsEintrag, String> tcHashAction;

    @FXML
    private Button btnNewList;

    @FXML
    private Button btnLogSave;

    @FXML
    private TextField tfFBAccessToken;

    @FXML
    private TextField tfFBUserData;

    @FXML
    private TextField tfFBAppID;

    @FXML
    private TextField tfFBAppSecret;

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

    @FXML
    private ContextMenu cmPosts;

    @FXML
    private MenuItem miDeletePost;

    @FXML
    private ContextMenu cmHashtags;

    @FXML
    private MenuItem miUpdateHashList;

    @FXML
    private MenuItem miDeleteHashList;

    private UserEintrag user;  // The Main Loggedin User (this is set during successfull Login Phase)
    Helper helper = new Helper();  // Helper object with functions
    private SocialmediaAccount socialmediaAccount; // the users socialmedia account data
    File selectedFile;
    String hashtags = "";
    Timeline socialMediaWorkerTimer; // controls the SocialMediaWorker object
    SocialMediaWorker socialMediaWorker; // checks what/when to post to Social Media
    int workerWaitSeconds = 3; // here use later 60 seconds, 3sec only for testing the loop

    /**
     * This Method sets the userObject here, after login of the main user
     * @param userObject userObject with uid,email,pw
     */
    public void setUser(UserEintrag userObject){
        this.user = userObject;
        System.out.println("Uid: " + this.user.getId());
    }


    /**
     * This method opens the FXML file fxTableHashtags in a small window
     */
    @FXML
    void ShowHashtags() {
        try {
            Stage MainStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("fxTableHashtags.fxml").openStream());
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            //Get controller of fxTableHashtags
            ControllerTableHashtags hashCon = loader.getController();

            Scene scene = new Scene(root);

            MainStage.setTitle("SMT - Social Media Tool: Hashtags");
            MainStage.setScene(scene);
            MainStage.showAndWait();

            hashtags = hashtags + hashCon.getData();
        } catch (IOException ex) {
            ex.getStackTrace();
        }

        System.out.println(hashtags);
        if(hashtags.isEmpty()) {
            hashtags = "";
        } else if(taHashtags.getText().isEmpty()) {
            taHashtags.appendText(hashtags);
            countHashtag();
            hashtags = "";
        } else {
            taHashtags.appendText(" " + hashtags);
            countHashtag();
            hashtags = "";
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
        countHashtag();
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
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            //System.out.println(selectedFile.getAbsolutePath());
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
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            //Get controller of fxAddHashtagListe
            ControllerAddHashtagListe newListCon = loader.getController();

            Scene scene = new Scene(root);

            MainStage.setTitle("SMT - Social Media Tool: Hashtagliste Hinzufügen");
            MainStage.setScene(scene);
            MainStage.showAndWait();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        getHashTable();
    }

    /**
     * This method enable or disable the automatic posting of Messages
     */
    @FXML
    void postAutomatic() {
        if (this.tbActivate.isSelected()) {
            this.socialMediaWorker.init();// here we need to init() socialmediaworker with the posts from DB
            this.socialMediaWorkerTimer.play();
            this.tbActivate.setStyle("-fx-background-color:cyan");
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
     * This method add generated Social Media Posts in the Database
     *
     * @param event
     */
    @FXML
    void postMessage(ActionEvent event) {
        // Speichert einen neuen Social Media Post in die Datenbank
        // check ob Postzeit ausgewählt und in der Zukunft liegt:
        String postTime = this.helper.checkIfLocalDateTimeIsNotNullAndInFuture(dpDate.getValue(),tfTime.getText());
        if( postTime != null && !postTime.isEmpty()){
            // message,hashtags und selectedfile können nicht alle empty sein, da sonst kein Post content:
           if( !(taMessage.getText().isEmpty() && taHashtags.getText().isEmpty() && selectedFile == null)){
               // check if any socialmedia platform is selected:
               if((cbFacebook.isSelected() && cbFBGruppen.getValue() != null) || cbTwitter.isSelected()){
                   // Speichere Post hier in die SocialmediaPosts table
                   String posttext = taMessage.getText() + " " + taHashtags.getText();
                   String mediafile = "";
                   if(selectedFile != null) {  // check if a mediafile like image or video is selected
                       mediafile = selectedFile.getAbsolutePath();
                   }

                   /*
                    * ToDo: here count and check platforms, where to post and set new PostEinträge with for Schleife and platform / fbsite info:
                    */
                   PostEintrag newPostEintrag = new PostEintrag(this.user.getId(), 1, "fbsite" , posttext, mediafile, postTime, 0 );  // 0  bedeutet neuer post

                   boolean postInsertOK= PostEintragBean.insertNewPost(newPostEintrag);
                   if(postInsertOK) {
                       lbMessageStatus.setText("Post wurde in DB gespeichert!");
                   }else{
                       lbMessageStatus.setText("DB Insert Fehler, Post konnte nicht gespeichert werden!");
                   }
               }else{
                   lbMessageStatus.setText("Es ist keine Social Media Platform ausgewählt! Min. 1 auswählen!");
               }
           }else{
                lbMessageStatus.setText("Kein Text/Content zum Posten eingegeben oder ausgewählt!");
           }
        }else{
            lbMessageStatus.setText("Datum und Zeit muss in der Zukunft liegen!");
        }
        /*
        System.out.println(dpDate.getValue());
        System.out.println(tfTime.getText());
        */
        resetText(lbMessageStatus);
    }

    /**
     * Method to count the Content of the Hashtag TextArea + Message TextArea for the Label lbRestChar, while writing/entering Hashtags
     */
    @FXML
    void countHashtag() {
        countTotalChar();
    }

    /**
     * Method to count the Content of the Message TextArea + Hashtag TextArea for the Label lbRestChar,while writing the post
     */
    @FXML
    void countPost() {
        countTotalChar();
    }

    /**
     * This method counts the chars of the Hashtag TextArea + Message TextArea
     */
    private void countTotalChar() {
        String post = taMessage.getText();
        String tag = taHashtags.getText();
        String tlen = post + tag;

        String msg = (post.length() + tag.length()) + " / 480 Zeichen";
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


    /**
     * This method generates and sets a random date in DatePicker and generate and sets a random time in TextField Time
     * @param event fired from btnRandmDateTime button
     */
    @FXML
    void randomDateTime(ActionEvent event) {
        //When clicked ,must generate Random Date and Time
        Instant jetzt = Instant.now();
        Instant einWoche = Instant.now().plus(Duration.ofDays(7));
        Instant randomInstant = zwischen(jetzt, einWoche);
        Date randomDate = Date.from(randomInstant);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String randomTime = timeFormat.format(randomDate);
        dpDate.setValue(randomInstant.atZone(ZoneId.systemDefault()).toLocalDate());
        tfTime.setText(randomTime);
    }

    /**
     * Method to generate Instant of the random date time in next 7 days
     *
     * @param jetzt    Instant current date time.
     * @param einWoche Instant date time after 7days
     * @return Instant of random date time in next 7 days
     */
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
     * This method save the Facebook Account Details from the tab 'Einstellungen' in the Database and to socialmediaAccount object
     *
     * @param event clickevent
     */
    @FXML
    void saveSettingsFB(ActionEvent event) {
        // Speichert die Facebook Accountdaten des users, abhaengig von seiner uid
        Integer uid = this.user.getId();
        String ai = this.tfFBAppID.getText();
        String as = this.tfFBAppSecret.getText();
        String at = this.tfFBAccessToken.getText();
        String ud = this.tfFBUserData.getText();
        if( uid != null && !ai.trim().equals("") && !as.trim().equals("") && !at.trim().equals("") && !ud.trim().equals("") ){
            if(SocialmediaAccountBean.insertOrUpdateFacebookAccount(uid,ai,as,at,ud)) {  // insertOrUpdate db true
                this.socialmediaAccount.setUid(uid);
                this.socialmediaAccount.setFbAppID(ai);
                this.socialmediaAccount.setFbAppSecret(as);
                this.socialmediaAccount.setFbAccessToken(at);
                this.socialmediaAccount.setFbUserdata(ud);
                this.lblSaveFBAccountStatus.setText("Facebook Accountdaten gespeichert/aktualisiert.");
            }else{
                System.out.println("An Error occured while insert or update FBAccountdata into SocialMediaAccounts Table!");
                this.lblSaveFBAccountStatus.setText("Konnte nicht gespeichert werden, Eingaben ueberpruefen!");
            }
        }else{
            this.lblSaveFBAccountStatus.setText("Konnte nicht gespeichert werden, Felder duerfen nicht leer sein!");
        }
        resetText(this.lblSaveFBAccountStatus);
    }

    /**
     * This method inserts or updates the Twitter Account Details from the tab 'Einstellungen' in the Database and to socialmediaAccount object
     *
     * @param event clickevent
     */
    @FXML
    void saveSettingsTW(ActionEvent event) {
        // Speichert die Twitter Accountdaten des users, abhaengig von seiner uid
        Integer uid = this.user.getId();
        String ck = this.ConsumerKey.getText();
        String cs = this.ConsumerSecret.getText();
        String at = this.AccessToken.getText();
        String ats = this.AccessTokenSecret.getText();
        if( uid != null && !ck.trim().equals("") && !cs.trim().equals("") && !at.trim().equals("") && !at.trim().equals("") ){
            //SocialmediaAccountBean socialmediaAccountBean = new SocialmediaAccountBean();  // used static
            if(SocialmediaAccountBean.insertOrUpdateTwitterAccount(uid,ck,cs,at,ats)){  // insertOrUpdate db true
                this.socialmediaAccount.setUid(uid);
                this.socialmediaAccount.setTwConsumerKey(ck);
                this.socialmediaAccount.setTwConsumerSecret(cs);
                this.socialmediaAccount.setTwAccessToken(at);
                this.socialmediaAccount.setTwAccessTokenSecret(ats);
                this.lblSaveTWAccountStatus.setText("Twitter Accountdaten gespeichert/aktualisiert.");
            }else{
                System.out.println("An Error occured while insert or update TwitterAccountdata into SocialMediaAccounts Table!");
                this.lblSaveTWAccountStatus.setText("Konnte nicht gespeichert werden, Eingaben ueberpruefen!");
            }
        }else{
            this.lblSaveTWAccountStatus.setText("Konnte nicht gespeichert werden, Felder duerfen nicht leer sein!");
        }
        resetText(this.lblSaveTWAccountStatus);
    }

    /**
     * loads the SocialmediaAccount Data of the active user with uid from user object (=UserEintrag object)
     */
    private void loadSocialMediaAccountDataIntoTwitterAndFacebookFields(){

            if (this.user.getId() != null) {

                this.socialmediaAccount = SocialmediaAccountBean.getSocialMediaAccountsByUid(this.user.getId());
                if (this.socialmediaAccount == null) {
                    // user has not yet set any Socialmedia Account informations , no row in table
                    // only set the available uid from loggedin userobject in new socialmediaAccount Object
                    this.socialmediaAccount = new SocialmediaAccount();
                    this.socialmediaAccount.setUid(this.user.getId());

                } else {
                    // show socialmedia account data  in Einstellungs textfields, so user can update and see what is actually set
                    String fbAppID = this.socialmediaAccount.getFbAppID();
                    String fbAppSecret = this.socialmediaAccount.getFbAppSecret();
                    String fbAccessToken = this.socialmediaAccount.getFbAccessToken();
                    String fbUserExtraData = this.socialmediaAccount.getFbUserdata();


                    if( fbAppID != null && fbAppSecret != null && fbAccessToken != null && fbUserExtraData != null ) {
                        this.tfFBAppID.setText(fbAppID);
                        this.tfFBAppSecret.setText(fbAppSecret);
                        this.tfFBAccessToken.setText(fbAccessToken);
                        this.tfFBUserData.setText(fbUserExtraData);
                    }

                    String twck = this.socialmediaAccount.getTwConsumerKey();
                    String twcs = this.socialmediaAccount.getTwConsumerSecret();
                    String twat = this.socialmediaAccount.getTwAccessToken();
                    String twats = this.socialmediaAccount.getTwAccessTokenSecret();

                    if ( twck != null && twcs != null && twat != null && twats != null ){
                        this.ConsumerKey.setText(twck);
                        this.ConsumerSecret.setText(twcs);
                        this.AccessToken.setText(twat);
                        this.AccessTokenSecret.setText(twats);
                    }
               }
            }else{
                System.out.println("nixzuladen");
            }
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

    public void loadAllPostsFromUserWithUid(int uid){
        PostEintragBean.selectAllPostsWithUid(uid);

    }


    /**
     * This method initialalizes the processes like the SocialMediaWorkerTimer etc.
     */
    @FXML
    void initialize() {

        /* -------- Automate Posts Section ------------------------------- */
        this.tbActivate.setSelected(false);  // on start set automated posting to false.
        this.tbActivate.setStyle("-fx-background-color:lightgrey");
        this.socialMediaWorker = new SocialMediaWorker();
        this.socialMediaWorkerTimer = new Timeline(new KeyFrame(javafx.util.Duration.seconds(workerWaitSeconds), this.socialMediaWorker));
        this.socialMediaWorkerTimer.setCycleCount(Timeline.INDEFINITE);

        /* --------- Twitter and Facebook Account Einstellungen ----------- */
        this.socialmediaAccount = new SocialmediaAccount();

        // if we select the Einstellungen tab, it loads newest SocialMediaAccounts Data from DB
        tabEinstellungen.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tabEinstellungen.isSelected()) {
                    loadSocialMediaAccountDataIntoTwitterAndFacebookFields();
                    System.out.println("Einstellungen Tab clicked.");
                }
            }
        });


        /* --------- Tab AllePosts Section loaders ----------- */
        tabAllePosts.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tabAllePosts.isSelected()) {
                    loadAllPostsFromUserWithUid(user.getId());
                    System.out.println("Tab AllePosts clicked.");
                    getPostTable();
                }
            }
        });

        /* --------- Tab Hashtags Section loaders ----------- */
        tabHashtags.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tabHashtags.isSelected()) {
                    getHashTable();
                }
            }
        });



        // wait 3 sec till uid etc. loaded and set by ContollerLogin, put everything in here, if it needs to start short time after fxinits
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(3));
        pause.setOnFinished(event ->
              loadSocialMediaAccountDataIntoTwitterAndFacebookFields()
        );
        pause.play();


        // if not need , set not needed FB Account Fields to invisible:
        // this.tfFBUsername.setVisible(false);
        // this.tfFBPassword.setVisible(false);
    }

    void getHashTable() {
        ObservableList<HashtagsEintrag> entries = FXCollections.observableArrayList(HashtagsBean.getThemes());
        this.tcTheme.setCellValueFactory(new PropertyValueFactory<HashtagsEintrag, String>("theme"));
        this.tcHashtags.setCellValueFactory(new PropertyValueFactory<HashtagsEintrag, String>("hashtags"));

        this.tvHashtags.setItems(entries);
    }

    /**
     * This method load the saved post entries from the Database in the Post Tableview
     */
    void getPostTable() {
        ObservableList<PostEintrag> entries = FXCollections.observableArrayList(PostEintragBean.getPosts());
        this.tcText.setCellValueFactory(new PropertyValueFactory<PostEintrag, String>("posttext"));
        this.tcDate.setCellValueFactory(new PropertyValueFactory<PostEintrag, String>("posttime"));
        this.tcPlatform.setCellValueFactory(new PropertyValueFactory<PostEintrag, String>("platform"));

        this.tvPosts.setItems(entries);
    }

    String getHashtags(String content) {
        return hashtags = content;
    }

    /**
     * This method delete a Post entry in the database
     */
    @FXML
    void deletePost() {

            System.out.println("Post gelöscht");
            // TODO:

            getPostTable();

    }

    @FXML
    void updateHashEntry() {
        System.out.println("Liste aktualisiert");
        //TODO

        getHashTable();
    }

    @FXML
    void deleteHashList() {
        System.out.println("Liste entfernt");
        //TODO

        getPostTable();
    }

}
