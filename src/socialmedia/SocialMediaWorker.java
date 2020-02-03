package socialmedia;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;

/**
 * this class will make the scheduled posts to socialmedia accounts
 */
public class SocialMediaWorker implements EventHandler<ActionEvent> {

    private Twitterer twitterer;

    /**
     *  constructor
     */
    public SocialMediaWorker(){
    }

    /**
     * init method gives this timertask other needed objects
     * @param
     */
    public void init(){
        // set vars here: ...
        this.twitterer = new Twitterer();
    }

    /*
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
        //System.out.println("this is called every x seconds on UI thread");

        // Todo: use data from DataModel Object (or DB)


        // ************* Twitter handling part: *******************
        ArrayList<String> messageData = new ArrayList<>(); // will be filled from DB
        sendPostToTwitter(messageData);  // send params and infos to make the tweet



        // ************* Facebook handling part: *******************


    }


    public void sendPostToTwitter(ArrayList<String> messageData) {
        System.out.println("twitter post test");
        /*
        this.twitterer = new Twitterer();
        this.twitterer.setTwitterAuthCredentials(a,b,c,d);
        System.out.println("twitter post test");

        // send Tweet: (Text and image file path would also come from Database)
        String tweetString = "Test Tweet using twitter4j at JAVA SE course #alfatraining #germany";
        File file = new File("/images/image.jpg");

        twitterer.sendTweet(tweetString,file);  // if no file/image upload, send null
        */

    }

}

