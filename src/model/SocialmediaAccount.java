package model;

import javafx.beans.property.StringProperty;

public class SocialmediaAccount {

    private Integer uid;
    private StringProperty twConsumerKey;
    private StringProperty twConsumerSecret;
    private StringProperty twAccessToken;
    private StringProperty twAccessTokenSecret;
    private StringProperty fbAppID;
    private StringProperty fbAppSecret;

    /**
     * getter method for uid
     * @return uid loggedin user id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * setter method for uid
     * @param uid sets the userid of loggedin user
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * getter for twitter ConsumerKey
     * @return twConsumerKey String
     */
    public String getTwConsumerKey() {
        return twConsumerKey.get();
    }


    /**
     * setter for twitter ConsumerKey
     * @param twConsumerKey String
     */
    public void setTwConsumerKey(String twConsumerKey) {
        this.twConsumerKey.set(twConsumerKey);
    }

    /**
     * getter for twitter ConsumerSecret
     * @return twConsumerSecret String
     */
    public String getTwConsumerSecret() {
        return twConsumerSecret.get();
    }

    /**
     * setter for twitter ConsumerSecret
     * @param twConsumerSecret String
     */
    public void setTwConsumerSecret(String twConsumerSecret) {
        this.twConsumerSecret.set(twConsumerSecret);
    }

    /**
     * getter for twitter AccessToken
     * @return twAccessToken String
     */
    public String getTwAccessToken() {
        return twAccessToken.get();
    }

    /**
     * setter for twitter AccessToken
     * @param twAccessToken String
     */
    public void setTwAccessToken(String twAccessToken) {
        this.twAccessToken.set(twAccessToken);
    }

    /**
     * getter for twitter AccessTokenSecret
     * @return twAccessTokenSecret String
     */
    public String getTwAccessTokenSecret() {
        return twAccessTokenSecret.get();
    }

    /**
     * setter for twitter AccessTokenSecret
     * @param twAccessTokenSecret String
     */
    public void setTwAccessTokenSecret(String twAccessTokenSecret) {
        this.twAccessTokenSecret.set(twAccessTokenSecret);
    }

    /**
     * getter for facebook AppID
     * @return fbAppID String
     */
    public String getFbAppID() {
        return fbAppID.get();
    }

    /**
     * setter for facebook AppID
     * @param fbAppID String
     */
    public void setFbAppID(String fbAppID) {
        this.fbAppID.set(fbAppID);
    }

    /**
     * getter for facebook AppSecret
     * @return fbAppSecret String
     */
    public String getFbAppSecret() {
        return fbAppSecret.get();
    }

    /**
     * setter for facebook AppSecret
     * @param fbAppSecret String
     */
    public void setFbAppSecret(String fbAppSecret) {
        this.fbAppSecret.set(fbAppSecret);
    }

     /* // uncommented, no need, maybe for later use...
    public StringProperty twConsumerKeyProperty() {
        return twConsumerKey;
    }

    public StringProperty twConsumerSecretProperty() {
        return twConsumerSecret;
    }

    public StringProperty twAccessTokenProperty() {
        return twAccessToken;
    }

    public StringProperty twAccessTokenSecretProperty() {
        return twAccessTokenSecret;
    }

    public StringProperty fbAppIDProperty() {
        return fbAppID;
    }
    public StringProperty fbAppSecretProperty() {
        return fbAppSecret;
    }
    */
}
