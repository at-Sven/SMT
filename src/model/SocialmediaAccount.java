package model;

import javafx.beans.property.StringProperty;

public class SocialmediaAccount {

    private Integer id;
    private StringProperty twConsumerKey;
    private StringProperty twConsumerSecret;
    private StringProperty twAccessToken;
    private StringProperty twAccessTokenSecret;
    private StringProperty fbAppID;
    private StringProperty fbAppSecret;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTwConsumerKey() {
        return twConsumerKey.get();
    }

    public StringProperty twConsumerKeyProperty() {
        return twConsumerKey;
    }

    public void setTwConsumerKey(String twConsumerKey) {
        this.twConsumerKey.set(twConsumerKey);
    }

    public String getTwConsumerSecret() {
        return twConsumerSecret.get();
    }

    public StringProperty twConsumerSecretProperty() {
        return twConsumerSecret;
    }

    public void setTwConsumerSecret(String twConsumerSecret) {
        this.twConsumerSecret.set(twConsumerSecret);
    }

    public String getTwAccessToken() {
        return twAccessToken.get();
    }

    public StringProperty twAccessTokenProperty() {
        return twAccessToken;
    }

    public void setTwAccessToken(String twAccessToken) {
        this.twAccessToken.set(twAccessToken);
    }

    public String getTwAccessTokenSecret() {
        return twAccessTokenSecret.get();
    }

    public StringProperty twAccessTokenSecretProperty() {
        return twAccessTokenSecret;
    }

    public void setTwAccessTokenSecret(String twAccessTokenSecret) {
        this.twAccessTokenSecret.set(twAccessTokenSecret);
    }

    public String getFbAppID() {
        return fbAppID.get();
    }

    public StringProperty fbAppIDProperty() {
        return fbAppID;
    }

    public void setFbAppID(String fbAppID) {
        this.fbAppID.set(fbAppID);
    }

    public String getFbAppSecret() {
        return fbAppSecret.get();
    }

    public StringProperty fbAppSecretProperty() {
        return fbAppSecret;
    }

    public void setFbAppSecret(String fbAppSecret) {
        this.fbAppSecret.set(fbAppSecret);
    }





}
