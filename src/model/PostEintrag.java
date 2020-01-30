package model;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Die Modellklasse für das Posten von Nachrichten auf de Social Media Kanälen
 */
public class PostEintrag {
    String message;
    String hashtag;
    String date;
    String time;
    File file;
    String platform;

    // necessary for Facebook
    String token;
    String appID;

    // necessary for Twitter
    String consumerKey;
    String consumerSecret;
    String accessToken;
    String accessTokenSecret;

    // check if the post was a success or not
    int success;
    // String errorText; (optional)

    /**
     * Constructor for a Post to a Facebook account
     *
     * @param token    The Token from Facebook account
     * @param appID    The App ID from the facebook account
     * @param message  The message to post
     * @param hashtags the used hashtags
     * @param date     the defined date for the Post
     * @param time     the defined time for the Post
     * @param file     the uploaded file for the Post
     */
    public PostEintrag(String token, String appID, String message, String hashtags, String date, String time, File file) {
        setToken(token);
        setAppID(appID);
        setMessage(message);
        setHashtag(hashtags);
        setDate(date);
        setTime(time);
        setFile(file);
    }

    /**
     * Constructor for a Post to a Twitter account
     *
     * @param consumerKey       The Consumer Key from the Twitter Account
     * @param consumerSecret    The Consumer Secret from the Twitter Account
     * @param accessToken       The Access Token from the Twitter Account
     * @param accessTokenSecret The Access Token Secret from the Twitter Account
     * @param message           The message to post
     * @param hashtags          the used hashtags
     * @param date              the defined date for the Post
     * @param time              the defined time for the Post
     * @param file              the uploaded file for the Post
     */
    public PostEintrag(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret,
                       String message, String hashtags, String date, String time, File file) {
        setToken(token);
        setAppID(appID);
        setMessage(message);
        setHashtag(hashtags);
        setDate(date);
        setTime(time);
        setFile(file);
    }

    /**
     * Get the message from a Post
     *
     * @return the Message as String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message for a Post
     *
     * @param message the Postmessage
     */
    public void setMessage(String message) {
        if (message == null || message.length() <= 2) {
            throw new IllegalArgumentException("Ihre Nachricht muss mindestens 2 Zeichen beinhalten");
        } else {
            this.message = message;
        }
    }

    /**
     * Get the hashtags
     *
     * @return The Hashtags as String
     */
    public String getHashtag() {
        return hashtag;
    }

    /**
     * Set the hashtags for a Post
     *
     * @param hashtag the Hashtags
     */
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    /**
     * get the date from a Post
     *
     * @return the date as String
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date == null || date.equals("")) {
            throw new IllegalArgumentException("Eingabe für 'Datum' fehlt");
        } else {
            this.date = date;
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (time == null || time.equals("")) {
            throw new IllegalArgumentException("Eingabe für 'Uhrzeit' fehlt");
        } else {
            this.time = time;
        }
    }

    /**
     * get the file from a Post
     *
     * @return the file as File
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the File for a post
     *
     * @param file the File Object
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Get the Platform, where the post belong
     *
     * @return the platform as String
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Set the Platform, where the post get send
     *
     * @param platform the name of the platform as String
     */
    public void setPlatform(String platform) {
        if (platform == null) {
            throw new IllegalArgumentException("Eingabe für 'Platform' fehlt");
        } else {
            this.platform = platform;
        }
    }

    /**
     * get the Token to post on Facebook
     *
     * @return the Token as String
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the Token to post on a Facebook account
     *
     * @param token the Token from the User account
     */
    public void setToken(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Eingabe für 'Token' fehlt");
        } else {
            this.token = token;
        }
    }

    /**
     * Get the App ID to Post on a Facebook account
     *
     * @return the App ID as String
     */
    public String getAppID() {
        return appID;
    }

    /**
     * Set the App ID to Post on a Facebook account
     *
     * @param appID the the App ID from the User account
     */
    public void setAppID(String appID) {
        if (appID == null) {
            throw new IllegalArgumentException("Eingabe für 'App ID' fehlt");
        } else {
            this.appID = appID;
        }
    }

    /**
     * Get the Consumer Key to Post on a Twitter account
     *
     * @return the Conumer Key as String
     */
    public String getConsumerKey() {
        return consumerKey;
    }

    /**
     * Set the Consumer Key to Post on a Twitter account
     *
     * @param consumerKey the Consumer Key from the User account
     */
    public void setConsumerKey(String consumerKey) {
        if (consumerKey == null) {
            throw new IllegalArgumentException("Eingabe für 'Consumer Key' fehlt");
        } else {
            this.consumerKey = consumerKey;
        }
    }

    /**
     * Get the Consumer Secret to Post on a Twitter account
     *
     * @return the Consumer Secret as String
     */
    public String getConsumerSecret() {
        return consumerSecret;
    }

    /**
     * Set the Consumer Secret to Post on a Twitter account
     *
     * @param consumerSecret the Consumer Secret from the User account
     */
    public void setConsumerSecret(String consumerSecret) {
        if (consumerSecret == null) {
            throw new IllegalArgumentException("Eingabe für 'Consumer Secret' fehlt");
        } else {
            this.consumerSecret = consumerSecret;
        }
    }

    /**
     * Get the Access Token to Post on a Twitter account
     *
     * @return the Access Token as String
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Set the Access Token to Post on a Twitter account
     *
     * @param accessToken the Access Token from the User account
     */
    public void setAccessToken(String accessToken) {
        if (accessToken == null) {
            throw new IllegalArgumentException("Eingabe für 'Access Token' fehlt");
        } else {
            this.accessToken = accessToken;
        }
    }

    /**
     * Get the Access Token Secret to Post on a Twitter account
     *
     * @return the Access Token Secret as String
     */
    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    /**
     * Set the Access Token Secret to Post on a Twitter account
     *
     * @param accessTokenSecret the Access Token Secret from the User account
     */
    public void setAccessTokenSecret(String accessTokenSecret) {
        if (accessTokenSecret == null) {
            throw new IllegalArgumentException("Eingabe für 'Access Token Secret' fehlt");
        } else {
            this.accessTokenSecret = accessTokenSecret;
        }
    }

    /**
     * Get the success validation
     *
     * @return the success as int
     */
    public int getSuccess() {
        return success;
    }

    /**
     * set the success to a specific value
     *
     * @param success value of success or not
     */
    public void setSuccess(int success) {
        if (success < 0 || success > 1) {
            throw new IllegalArgumentException("Der Wert muss zwischen 0 und 1 sein");
        } else {
            this.success = success;
        }
    }
}