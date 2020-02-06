package socialmedia;

import datenbank.beans.PostEintragBean;
import datenbank.beans.SocialmediaAccountBean;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.PostEintrag;
import model.SocialmediaAccount;
import utils.Helper;

import javafx.scene.control.TextArea;
import java.io.File;
import java.util.ArrayList;

/**
 * this class makes the scheduled posts to socialmedia accounts
 */
public class SocialMediaWorker implements EventHandler<ActionEvent> {


    public int uid;
    // Facebook:
    public String fbAppID;
    public String fbAppSecret;
    public String fbUserAccessToken;
    public String fbPageAccessToken;

    // Twitter:
    public String twConsumerKey;
    public String twConsumerSecret;
    public String twAccessToken;
    public String twAccesTokenSecret;

    private Twitterer twitterer;
    //private ArrayList<PostEintrag> scheduledPostsArrayList;

    public TextArea taLog;

    Helper helper = new Helper();

    /**
     *  constructor
     */
    public SocialMediaWorker(){
    }

    /**
     * init methode
     * @param uid
     * @param fbAppID
     * @param fbAppSecret
     * @param fbPageAccessToken
     * @param fbUserAccessToken
     * @param twConsumerKey
     * @param twConsumerSecret
     * @param twAccessToken
     * @param twAccesTokenSecret
     * @param taLog ControllerMain Übersicht Textarea zum loggen
     */
    public void init(int uid,String fbAppID, String fbAppSecret, String fbPageAccessToken, String fbUserAccessToken,
                    String twConsumerKey, String twConsumerSecret, String twAccessToken, String twAccesTokenSecret, TextArea taLog){
        // init vars here:
        this.uid = uid;

        // Facebook:
        this.fbAppID = fbAppID;
        this.fbAppSecret = fbAppSecret;
        this.fbUserAccessToken = fbUserAccessToken;
        this.fbPageAccessToken = fbPageAccessToken;

        // Twitter:
        this.twConsumerKey = twConsumerKey;
        this.twConsumerSecret = twConsumerSecret;
        this.twAccessToken = twAccessToken;
        this.twAccesTokenSecret = twAccesTokenSecret;

        this.twitterer = new Twitterer();

        this.taLog = taLog; // ContollerMains Textarea taLog für loggin in die textarea

    }

    /**
     * Überschriebene methode wird von Timeline in gewissen Zeitabständen aufgerufen, um
     * die Posts liste des Users zu überprüfen, ob Posts anstehen, die auf Social Media
     * gepostet werden sollen.
     * Nach einem Post oder Fehlerhaften Post wird das PostEintrag Object aus der Arraylist scheduledPostsArrayList
     * entfenrt und die Postdaten des Posts in der Datenbanktable SocialmediaPosts wird auf poststatus grösser 0 gesetzt,
     * damit die schon bearbeiteten Posts nicht wieder abgearbeitet und gepostet werden!
     * function wird in der ControllerMain in der postAutomatic() über this.socialMediaWorkerTimer.play(); gestartet
     * bzw. gestoppt!
     * @param actionEvent event über Timeline scheduling
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        //System.out.println("this is called every x seconds");

        //  hole nur die posts die noch nicht versendet wurden: (bedeutet poststatus == 0)
        ArrayList<PostEintrag> scheduledPostsArrayList = PostEintragBean.selectScheduledPostsWithUidAndPoststatus(this.uid);

        for( int i = 0 ; i < scheduledPostsArrayList.size() ; i++ ){

            // ************* Twitter posting part: *******************

            // überprüfe ob der Post für Twitter ist:
            if(scheduledPostsArrayList.get(i).getPlatform() == 1){  // 1 = Twitter
                File file = null; // var for Media file
                // check if postdate is in the past here!
                if(helper.timeNowIsAfterPosttime(scheduledPostsArrayList.get(i).getPosttime())){ // wenn postzeit in Vergangenheit ist true

                    this.twitterer = new Twitterer();  //  connector zur Twitter API
                    this.twitterer.setTwitterAuthCredentials(this.twConsumerKey, this.twConsumerSecret, this.twAccessToken, this.twAccesTokenSecret);
                    String mediafilepath = scheduledPostsArrayList.get(i).getMediafile();
                    if(mediafilepath != null && !mediafilepath.isEmpty()) {
                        file = new File(mediafilepath);
                    }

                    int statusInteger = 2; // default Wert erst auf fehler setzen
                    String statustext = twitterer.sendTweet(scheduledPostsArrayList.get(i).getPosttext(),file);  // Send Tweet with Postttext and Media File

                    if(statustext != "Error" && !statustext.isEmpty()){ // Falls kein Fehler
                        statusInteger = 1; // Post war Erfolgreich!!!
                        this.taLog.appendText("Geplanter Tweet mit folgendem Text wurde erfolgreich verschickt:" + scheduledPostsArrayList.get(i).getPosttext() + "\n"); // loggt in Mainfxml
                    } else {
                        this.taLog.appendText("Geplanter Tweet mit folgendem Text wurde wegen einem Fehler nicht verschickt:" + scheduledPostsArrayList.get(i).getPosttext() + "\n"); // loggt in Mainfxml
                    }
                    // Update SocialmediaPosts Table Row mit der pid des posts entweder auf Erfolgreich = 1 oder Error = 2
                    PostEintragBean.updatePostStatusToSuccessfulOrError(scheduledPostsArrayList.get(i).getPid(), statusInteger);

                    scheduledPostsArrayList.remove(i); // nach erfolgreichem Tweet oder einem Fehler, den Post aus der Array Liste löschen, damit in der For Schleife nicht erneut retweetet wird.
                    //System.out.println(statustext);

                }
            }



            // TODO: ************* Facebook posting part: *******************












        }
    }

}

