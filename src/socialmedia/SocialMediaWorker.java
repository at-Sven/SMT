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
    private ArrayList<PostEintrag> scheduledPostsArrayList;


    /**
     *  constructor
     */
    public SocialMediaWorker(){
    }

    /**
     * init method
     * @param
     */
    public void init(int uid,String fbAppID, String fbAppSecret, String fbPageAccessToken, String fbUserAccessToken,
                    String twConsumerKey, String twConsumerSecret, String twAccessToken, String twAccesTokenSecret){
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


    }

    /* example for Timeline:
    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("this is called every 5 seconds on UI thread");
        }
        }));
    fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
    fiveSecondsWonder.play();
     */

    @Override
    public void handle(ActionEvent actionEvent) {
        //System.out.println("this is called every x seconds");

        // Todo: use data from DB Table

        //  hole nur die posts die noch nicht versendet wurden: (bedeutet poststatus == 0)
        scheduledPostsArrayList = PostEintragBean.selectScheduledPostsWithUidAndPoststatus(this.uid);

        for( int i = 0 ; i < scheduledPostsArrayList.size() ; i++ ){

            // ************* Twitter posting part: *******************

            // überprüfe ob der Post für Twitter ist:
            if(scheduledPostsArrayList.get(i).getPlatform() == 1){  // 1 = Twitter

                // check if postdate is in the past here!




                sendPostToTwitter(scheduledPostsArrayList.get(i));  // sends PostEintrags Object to function

            }









            // ************* Facebook posting part: *******************






        }
    }


    public void sendPostToTwitter(PostEintrag post) {
        /*
        this.twitterer = new Twitterer();
        this.twitterer.setTwitterAuthCredentials(this.twConsumerKey, this.twConsumerSecret, this.twAccessToken, this.twAccesTokenSecret);
        if(post.getMediafile() != null && !post.getMediafile().isEmpty()) {
            File file = new File(post.getMediafile());
            twitterer.sendTweet(post.getPosttext(),file);
        }else{
            twitterer.sendTweet(post.getPosttext(),null);  // if no file/image upload, send null
        }
        */
        System.out.println("twitter post test: " + post.getPosttext() + " Poststatus: " + post.getPoststatus());





        /* for test:
        // send Tweet: (Text and image file path would also come from Database)
        String tweetString = "Test Tweet using twitter4j at JAVA SE course #alfatraining #germany";
        File file = new File("/images/image.jpg");

        twitterer.sendTweet(tweetString,file);  // if no file/image upload, send null
        */

    }

}

