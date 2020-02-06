package model;

import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Die Modellklasse für das Posten von Nachrichten auf den Social Media Kanälen
 */
public class PostEintrag {

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
    private String fbUserAccessToken;
    private String fbPageAccessToken;
    private int fbAccessTokenExpireDate;

    // variable wird nur in der fxMainfxml Tab AllePosts benötigt, und über einen constructor mitgesetzt:
    private String dateiname; // das ist nicht der Absolute Pfad, für posts mediafile variable benutzen!

    /**
     * constructor für Posteintrag volles init
     *
     * @param pid                     postid
     * @param uid                     uid userid
     * @param platform                platform info
     * @param fbsite                  fbsite
     * @param posttext                posttext with message,hashtags
     * @param mediafile               path to image or video file
     * @param posttime                when to post
     * @param poststatus              poststatus, 0 is newpost not send, 1 is sent, >1 error
     * @param sid                     socialmediaaccount id
     * @param twConsumerKey           twConsumerKey
     * @param twConsumerSecret        twConsumerSecret
     * @param twAccessToken           twAccessToken
     * @param twAccessTokenSecret     twAccessTokenSecret
     * @param fbAppID                 fbAppID
     * @param fbAppSecret             fbAppSecret
     * @param fbUserAccessToken       fbAccessToken
     * @param fbPageAccessToken       some additional data if needed
     * @param fbAccessTokenExpireDate fbAccessTokenExpireDate
     */
    public PostEintrag(int pid, int uid, int platform, String fbsite, String posttext,
                       String mediafile, String posttime, int poststatus, int sid, String twConsumerKey,
                       String twConsumerSecret, String twAccessToken, String twAccessTokenSecret, String fbAppID, String fbAppSecret,
                       String fbUserAccessToken, String fbPageAccessToken, Integer fbAccessTokenExpireDate) {
        this.pid = pid;
        this.uid = uid;
        this.platform = platform;
        this.fbsite = fbsite;
        this.posttext = posttext;
        this.mediafile = mediafile;   // absolute mediafile path with dateiname
        this.posttime = posttime;
        this.poststatus = poststatus; // check if the post was a success or not // 0 is new, 1 is sent , > 1 is error

        this.sid = sid;
        this.twConsumerKey = twConsumerKey;
        this.twConsumerSecret = twConsumerSecret;
        this.twAccessToken = twAccessToken;
        this.twAccessTokenSecret = twAccessTokenSecret;

        this.fbAppID = fbAppID;
        this.fbAppSecret = fbAppSecret;
        this.fbUserAccessToken = fbUserAccessToken;
        this.fbPageAccessToken = fbPageAccessToken;
        this.fbAccessTokenExpireDate = fbAccessTokenExpireDate;

    }

    /**
     * constructor für Eintrag ueber ControllerMain Formular:
     *
     * @param uid        userid
     * @param platform   platform info
     * @param fbsite     fbsite
     * @param posttext   posttext with message,hashtags
     * @param mediafile  path to image or video file
     * @param posttime   when to post
     * @param poststatus poststatus, 0 is newpost not send, 1 is sent, >1 error
     */
    public PostEintrag(int uid, int platform, String fbsite, String posttext, String mediafile, String posttime, int poststatus) {
        this.uid = uid;
        this.platform = platform;
        this.fbsite = fbsite;
        this.posttext = posttext;
        this.mediafile = mediafile; // absolute mediafile path with dateiname
        this.posttime = posttime;
        this.poststatus = poststatus; // check if the post was a success or not // 0 is new, 1 is sent , > 1 is error
    }

    /**
     * constructor für Eintrag with all data
     *
     * @param pid        post ID
     * @param uid        user ID
     * @param sid        socialmedia ID
     * @param platform   social media Platform
     * @param fbsite
     * @param posttext   post message
     * @param mediafile  selected media file
     * @param posttime   date and time
     * @param poststatus sending status
     */
    public PostEintrag(int pid, int uid, int sid, int platform, String fbsite, String posttext, String mediafile, String posttime, int poststatus) {
        this.pid = pid;
        this.uid = uid;
        this.sid = sid;
        this.platform = platform;
        this.fbsite = fbsite;
        this.posttext = posttext;
        this.mediafile = mediafile;  // absolute mediafile path with dateiname
        this.dateiname = mediafile.substring(mediafile.lastIndexOf('\\') + 1); // substring the dateiname from absolute mediafile path
        this.posttime = posttime;
        this.poststatus = poststatus; // check if the post was a success or not // 0 is new, 1 is sent , > 1 is error
    }

    /**
     * default constructor
     */
    public PostEintrag() {
    }

    /**
     * Getter for pid
     *
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * Setter for pid
     *
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * Getter for uid
     *
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * Setter for uid
     *
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * Getter for platform
     *
     * @return platform
     */
    public int getPlatform() {
        return platform;
    }

    /**
     * Setter for platform
     *
     * @param platform
     */
    public void setPlatform(int platform) {
        this.platform = platform;
    }

    /**
     * Getter for fbsite
     *
     * @return fbsite
     */
    public String getFbsite() {
        return fbsite;
    }

    /**
     * Setter for fbsite
     *
     * @param fbsite
     */
    public void setFbsite(String fbsite) {
        this.fbsite = fbsite;
    }

    /**
     * Getter for posttext
     *
     * @return posttext
     */
    public String getPosttext() {
        return posttext;
    }

    /**
     * Setter for posttext
     *
     * @param posttext
     */
    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }

    /**
     * Getter for mediafile
     *
     * @return mediafile
     */
    public String getMediafile() {
        return mediafile;
    }

    /**
     * Setter for mediafile
     *
     * @param mediafile
     */
    public void setMediafile(String mediafile) {
        //if (mediafile != null && !mediafile.isEmpty()) {
        this.mediafile = mediafile;
        //}
    }

    /**
     * Getter for posttime
     *
     * @return posttime
     */
    public String getPosttime() {
        return posttime;
    }

    /**
     * Setter for posttime
     *
     * @param posttime
     */
    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    /**
     * Getter for poststatus
     *
     * @return poststatus
     */
    public Integer getPoststatus() {
        return poststatus;
    }

    /**
     * Setter for poststatus
     *
     * @param poststatus
     */
    public void setPoststatus(Integer poststatus) {
        this.poststatus = poststatus;
    }

    /**
     * Getter for sid
     *
     * @return sid
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * Setter for sid
     *
     * @param sid
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * Getter for twConsumerKey
     *
     * @return twConsumerKey
     */
    public String getTwConsumerKey() {
        return twConsumerKey;
    }

    /**
     * Setter for twConsumerKey
     *
     * @param twConsumerKey
     */
    public void setTwConsumerKey(String twConsumerKey) {
        this.twConsumerKey = twConsumerKey;
    }

    /**
     * Getter for twConsumerSecret
     *
     * @return twConsumerSecret
     */
    public String getTwConsumerSecret() {
        return twConsumerSecret;
    }

    /**
     * Setter for twConsumerSecret
     *
     * @param twConsumerSecret
     */
    public void setTwConsumerSecret(String twConsumerSecret) {
        this.twConsumerSecret = twConsumerSecret;
    }

    /**
     * Getter for twAccessToken
     *
     * @return twAccessToken
     */
    public String getTwAccessToken() {
        return twAccessToken;
    }

    /**
     * Setter for twAccessToken
     *
     * @param twAccessToken
     */
    public void setTwAccessToken(String twAccessToken) {
        this.twAccessToken = twAccessToken;
    }

    /**
     * Getter for twAccessTokenSecret
     *
     * @return twAccessTokenSecret
     */
    public String getTwAccessTokenSecret() {
        return twAccessTokenSecret;
    }

    /**
     * Setter for twAccessTokenSecret
     *
     * @param twAccessTokenSecret
     */
    public void setTwAccessTokenSecret(String twAccessTokenSecret) {
        this.twAccessTokenSecret = twAccessTokenSecret;
    }

    /**
     * Getter for fbAppID
     *
     * @return fbAppID
     */
    public String getFbAppID() {
        return fbAppID;
    }

    /**
     * Setter for fbAppID
     *
     * @param fbAppID
     */
    public void setFbAppID(String fbAppID) {
        this.fbAppID = fbAppID;
    }

    /**
     * Getter for fbAppSecret
     *
     * @return fbAppSecret
     */
    public String getFbAppSecret() {
        return fbAppSecret;
    }

    /**
     * Setter for fbAppSecret
     *
     * @param fbAppSecret
     */
    public void setFbAppSecret(String fbAppSecret) {
        this.fbAppSecret = fbAppSecret;
    }

    /**
     * Getter for fbAccessToken
     *
     * @return fbUserAccessToken
     */
    public String getFbUserAccessToken() {
        return fbUserAccessToken;
    }

    /**
     * Setter for fbAccessToken
     *
     * @param fbUserAccessToken
     */
    public void setFbUserAccessToken(String fbUserAccessToken) {
        this.fbUserAccessToken = fbUserAccessToken;
    }

    /**
     * Getter for fbUserdata
     *
     * @return fbUserdata
     */
    public String getFbPageAccessToken() {
        return fbPageAccessToken;
    }

    /**
     * Setter for fbUserdata
     *
     * @param fbPageAccessToken
     */
    public void setFbPageAccessToken(String fbPageAccessToken) {
        this.fbPageAccessToken = fbPageAccessToken;
    }

    /**
     * Getter for fbAccessTokenExpireDate
     *
     * @return fbAccessTokenExpireDate
     */
    public Integer getFbAccessTokenExpireDate() {
        return fbAccessTokenExpireDate;
    }

    /**
     * Setter for fbAccessTokenExpireDate
     *
     * @param fbAccessTokenExpireDate
     */
    public void setFbAccessTokenExpireDate(Integer fbAccessTokenExpireDate) {
        this.fbAccessTokenExpireDate = fbAccessTokenExpireDate;
    }

    /**
     * Getter for dateiname
     *
     * @return dateiname
     */
    public String getDateiname() {
        return dateiname;
    }

}

