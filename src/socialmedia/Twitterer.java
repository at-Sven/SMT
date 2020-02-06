package socialmedia;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;

public class Twitterer {

    public Twitter twitter;
    /*
    public static void main(String[] args) {

        // Tests here, later will be controlled/invoked with loop/thread object:
        Twitterer twitterer = new Twitterer();
        // Twitter keys,tokens,sectrets will come from Database:
        twitterer.setTwitterAuthCredentials("ck", "cs", "at", "ats");
        // send Tweet: (Text and image file path would also come from Database)
        String tweetString = "Test Tweet using twitter4j at JAVA SE course #alfatraining #germany";
        File file = new File("/images/image.jpg");

        //twitterer.sendTweet(tweetString,file);  // if no file/image upload, send null

    }
    */

    /**
     * This method sets the api key, secrets and tokens on the Twitter object.
     * You have to generate them on: apps.twitter.com
     * @param consumerKey String
     * @param consumerSecret String
     * @param accessToken String
     * @param accessTokenSecret String
     */
    public void setTwitterAuthCredentials(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        //Twitter twitter = tf.getInstance();
        //this.twitter = tf.getSingleton();
        this.twitter = tf.getInstance();
    }

    /**
     * This method tweets a given message.
     * @param tweetText String , a message you wish to Tweet out
     * @param imageFile String , a mediafile you wish to Tweet out
     */
    public String sendTweet(String tweetText, File imageFile) {
        String statusText = "";
        try{
            StatusUpdate statusUpdate = new StatusUpdate(tweetText);
            if(imageFile != null){
                statusUpdate.setMedia(imageFile); // set the image to be uploaded here.
            }
            Status status = twitter.updateStatus(statusUpdate);
            statusText = status.getText();
            System.out.println("Tweet wurde erfolgreich abgesetzt: [" + statusText + "].");
        }catch (TwitterException e) {
            statusText = "Error";
            e.printStackTrace();
        }
        return statusText;
    }


}

