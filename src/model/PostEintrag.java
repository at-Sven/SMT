package model;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Die Modellklasse für das Posten von Nachrichten auf den Social Media Kanälen
 */
public class PostEintrag {

    // TODO: Comments noch machen, korrigieren

    private int pid;
    private int uid;
    private int platform;
    private String fbsite;
    private String posttext;
    private String mediafile;
    private String posttime;
    private int poststatus; // check if the post was a success or not // 0 is new, 1 is sent , > 1 is error

    private int sid;
    private String twConsumerKey;
    private String twConsumerSecret;
    private String twAccessToken;
    private String twAccessTokenSecret;

    private String fbAppID;
    private String fbAppSecret;
    private String fbAccessToken;
    private String fbUserdata;
    private int fbAccessTokenExpireDate;

    /**
     * constructor für Posteintrag volles init
     * @param pid postid
     * @param uid uid userid
     * @param platform platform info
     * @param fbsite fbsite
     * @param posttext posttext with message,hashtags
     * @param mediafile path to image or video file
     * @param posttime when to post
     * @param poststatus poststatus, 0 is newpost not send, 1 is sent, >1 error
     * @param sid socialmediaaccount id
     * @param twConsumerKey twConsumerKey
     * @param twConsumerSecret twConsumerSecret
     * @param twAccessToken twAccessToken
     * @param twAccessTokenSecret twAccessTokenSecret
     * @param fbAppID fbAppID
     * @param fbAppSecret fbAppSecret
     * @param fbAccessToken fbAccessToken
     * @param fbUserdata some additional data if needed
     * @param fbAccessTokenExpireDate fbAccessTokenExpireDate
     */
    public PostEintrag(int pid , int uid, int platform, String fbsite, String posttext,
                       String mediafile, String posttime, int poststatus, int sid, String twConsumerKey ,
                       String twConsumerSecret,String twAccessToken, String twAccessTokenSecret, String fbAppID, String fbAppSecret,
                       String fbAccessToken, String fbUserdata, Integer fbAccessTokenExpireDate) {
        this.pid = pid;
        this.uid = uid;
        this.platform = platform;
        this.fbsite = fbsite;
        this.posttext = posttext;
        this.mediafile = mediafile;
        this.posttime = posttime;
        this.poststatus = poststatus; // check if the post was a success or not // 0 is new, 1 is sent , > 1 is error

        this.sid = sid;
        this.twConsumerKey = twConsumerKey;
        this.twConsumerSecret = twConsumerSecret;
        this.twAccessToken = twAccessToken;
        this.twAccessTokenSecret = twAccessTokenSecret;

        this.fbAppID = fbAppID;
        this.fbAppSecret = fbAppSecret;
        this.fbAccessToken = fbAccessToken;
        this.fbUserdata = fbUserdata;
        this.fbAccessTokenExpireDate = fbAccessTokenExpireDate;

    }

    /**
     * constructor für Eintrag ueber ControllerMain Formular:
     * @param uid userid
     * @param platform platform info
     * @param fbsite  fbsite
     * @param posttext posttext with message,hashtags
     * @param mediafile path to image or video file
     * @param posttime when to post
     * @param poststatus poststatus, 0 is newpost not send, 1 is sent, >1 error
     */
    public PostEintrag(int uid, int platform, String fbsite, String posttext, String mediafile, String posttime, int poststatus ){
        this.uid = uid;
        this.platform = platform;
        this.fbsite = fbsite;
        this.posttext = posttext;
        this.mediafile = mediafile;
        this.posttime = posttime;
        this.poststatus = poststatus; // check if the post was a success or not // 0 is new, 1 is sent , > 1 is error
    }

    public PostEintrag(int pid, int uid, int sid, int platform, String fbsite, String posttext, String mediafile, String posttime, int poststatus ){
        this.pid = pid;
        this.uid = uid;
        this.sid = sid;
        this.platform = platform;
        this.fbsite = fbsite;
        this.posttext = posttext;
        this.mediafile = mediafile;
        this.posttime = posttime;
        this.poststatus = poststatus; // check if the post was a success or not // 0 is new, 1 is sent , > 1 is error
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getFbsite() {
        return fbsite;
    }

    public void setFbsite(String fbsite) {
        this.fbsite = fbsite;
    }

    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }

    public String getMediafile() {
        return mediafile;
    }

    public void setMediafile(String mediafile) {
        this.mediafile = mediafile;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public Integer getPoststatus() {
        return poststatus;
    }

    public void setPoststatus(Integer poststatus) {
        this.poststatus = poststatus;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTwConsumerKey() {
        return twConsumerKey;
    }

    public void setTwConsumerKey(String twConsumerKey) {
        this.twConsumerKey = twConsumerKey;
    }

    public String getTwConsumerSecret() {
        return twConsumerSecret;
    }

    public void setTwConsumerSecret(String twConsumerSecret) {
        this.twConsumerSecret = twConsumerSecret;
    }

    public String getTwAccessToken() {
        return twAccessToken;
    }

    public void setTwAccessToken(String twAccessToken) {
        this.twAccessToken = twAccessToken;
    }

    public String getTwAccessTokenSecret() {
        return twAccessTokenSecret;
    }

    public void setTwAccessTokenSecret(String twAccessTokenSecret) {
        this.twAccessTokenSecret = twAccessTokenSecret;
    }

    public String getFbAppID() {
        return fbAppID;
    }

    public void setFbAppID(String fbAppID) {
        this.fbAppID = fbAppID;
    }

    public String getFbAppSecret() {
        return fbAppSecret;
    }

    public void setFbAppSecret(String fbAppSecret) {
        this.fbAppSecret = fbAppSecret;
    }

    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    public String getFbUserdata() {
        return fbUserdata;
    }

    public void setFbUserdata(String fbUserdata) {
        this.fbUserdata = fbUserdata;
    }

    public Integer getFbAccessTokenExpireDate() {
        return fbAccessTokenExpireDate;
    }

    public void setFbAccessTokenExpireDate(Integer fbAccessTokenExpireDate) {
        this.fbAccessTokenExpireDate = fbAccessTokenExpireDate;
    }


}

/*
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
     *//*
    public PostEintrag(String token, String appID, String message, String hashtags, String date, String time, File file) {
        setToken(token);
        setAppID(appID);
        setMessage(message);
        setHashtag(hashtags);
        setDate(date);
        setTime(time);
        setFile(file);
    }

    *//**
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
     *//*
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

    *//**
     * Get the message from a Post
     *
     * @return the Message as String
     *//*
    public String getMessage() {
        return message;
    }

    *//**
     * Set the message for a Post
     *
     * @param message the Postmessage
     *//*
    public void setMessage(String message) {
        if (message == null || message.length() <= 2) {
            throw new IllegalArgumentException("Ihre Nachricht muss mindestens 2 Zeichen beinhalten");
        } else {
            this.message = message;
        }
    }

    *//**
     * Get the hashtags
     *
     * @return The Hashtags as String
     *//*
    public String getHashtag() {
        return hashtag;
    }

    *//**
     * Set the hashtags for a Post
     *
     * @param hashtag the Hashtags
     *//*
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    *//**
     * get the date from a Post
     *
     * @return the date as String
     *//*
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

    *//**
     * get the file from a Post
     *
     * @return the file as File
     *//*
    public File getFile() {
        return file;
    }

    *//**
     * Set the File for a post
     *
     * @param file the File Object
     *//*
    public void setFile(File file) {
        this.file = file;
    }

    *//**
     * Get the Platform, where the post belong
     *
     * @return the platform as String
     *//*
    public String getPlatform() {
        return platform;
    }

    *//**
     * Set the Platform, where the post get send
     *
     * @param platform the name of the platform as String
     *//*
    public void setPlatform(String platform) {
        if (platform == null) {
            throw new IllegalArgumentException("Eingabe für 'Platform' fehlt");
        } else {
            this.platform = platform;
        }
    }

    *//**
     * get the Token to post on Facebook
     *
     * @return the Token as String
     *//*
    public String getToken() {
        return token;
    }

    *//**
     * Set the Token to post on a Facebook account
     *
     * @param token the Token from the User account
     *//*
    public void setToken(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Eingabe für 'Token' fehlt");
        } else {
            this.token = token;
        }
    }

    *//**
     * Get the App ID to Post on a Facebook account
     *
     * @return the App ID as String
     *//*
    public String getAppID() {
        return appID;
    }

    *//**
     * Set the App ID to Post on a Facebook account
     *
     * @param appID the the App ID from the User account
     *//*
    public void setAppID(String appID) {
        if (appID == null) {
            throw new IllegalArgumentException("Eingabe für 'App ID' fehlt");
        } else {
            this.appID = appID;
        }
    }

    *//**
     * Get the Consumer Key to Post on a Twitter account
     *
     * @return the Conumer Key as String
     *//*
    public String getConsumerKey() {
        return consumerKey;
    }

    *//**
     * Set the Consumer Key to Post on a Twitter account
     *
     * @param consumerKey the Consumer Key from the User account
     *//*
    public void setConsumerKey(String consumerKey) {
        if (consumerKey == null) {
            throw new IllegalArgumentException("Eingabe für 'Consumer Key' fehlt");
        } else {
            this.consumerKey = consumerKey;
        }
    }

    *//**
     * Get the Consumer Secret to Post on a Twitter account
     *
     * @return the Consumer Secret as String
     *//*
    public String getConsumerSecret() {
        return consumerSecret;
    }

    *//**
     * Set the Consumer Secret to Post on a Twitter account
     *
     * @param consumerSecret the Consumer Secret from the User account
     *//*
    public void setConsumerSecret(String consumerSecret) {
        if (consumerSecret == null) {
            throw new IllegalArgumentException("Eingabe für 'Consumer Secret' fehlt");
        } else {
            this.consumerSecret = consumerSecret;
        }
    }

    *//**
     * Get the Access Token to Post on a Twitter account
     *
     * @return the Access Token as String
     *//*
    public String getAccessToken() {
        return accessToken;
    }

    *//**
     * Set the Access Token to Post on a Twitter account
     *
     * @param accessToken the Access Token from the User account
     *//*
    public void setAccessToken(String accessToken) {
        if (accessToken == null) {
            throw new IllegalArgumentException("Eingabe für 'Access Token' fehlt");
        } else {
            this.accessToken = accessToken;
        }
    }

    *//**
     * Get the Access Token Secret to Post on a Twitter account
     *
     * @return the Access Token Secret as String
     *//*
    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    *//**
     * Set the Access Token Secret to Post on a Twitter account
     *
     * @param accessTokenSecret the Access Token Secret from the User account
     *//*
    public void setAccessTokenSecret(String accessTokenSecret) {
        if (accessTokenSecret == null) {
            throw new IllegalArgumentException("Eingabe für 'Access Token Secret' fehlt");
        } else {
            this.accessTokenSecret = accessTokenSecret;
        }
    }

    *//**
     * Get the success validation
     *
     * @return the success as int
     *//*
    public int getSuccess() {
        return success;
    }

    *//**
     * set the success to a specific value
     *
     * @param success value of success or not
     *//*
    public void setSuccess(int success) {
        if (success < 0 || success > 1) {
            throw new IllegalArgumentException("Der Wert muss zwischen 0 und 1 sein");
        } else {
            this.success = success;
        }
    }
}*/
