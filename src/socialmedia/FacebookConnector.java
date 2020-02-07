package socialmedia;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import java.io.IOException;
import java.util.HashMap;

/**
 * abstract class FacebookConnector
 * This class handles all methods you need for a connection to Facebook
 * and for posting messages on your Page and in Groups you are in.
 */

public abstract class FacebookConnector {
    public String userAccessToken;
    public String pageAccessToken;
    public int accessTokenExpireDate;
    public String appID;
    public String appSecret;
    public FacebookClient fbUserClient;
    public FacebookClient fbPageClient;

    /**
     * Constructor of the abstract class FacebookConnector
     *
     * @param appID
     * @param appSecret
     * @param accessToken
     * @param option
     */
    public FacebookConnector(String appID, String appSecret, String accessToken, String option) {
        setAppID(appID);
        setAppSecret(appSecret);
        setAccessToken(option, accessToken);
        connectToFb(option);
        checkAccessToken(option);
    }

    /**
     * The class connectToFb manage the connections to Facebook.
     * The connection to Facebook depends on what you want to do.
     * If you want to post on your Page Feed you need a connection with the option "page".
     * If you want to post in a Group where you are in you need a connection with the option "user".
     *
     * @param option
     */
    private void connectToFb(String option) {
        if(option == "user") {
            FacebookClient fbUserClient = new DefaultFacebookClient(userAccessToken, Version.LATEST);
            setFbClient("user", fbUserClient);
        }
        if(option == "page") {
            FacebookClient fbPageClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
            setFbClient("page", fbPageClient);
        }
    }

    /**
     * The class checkAccessToken checks your AccessToken and will expend and save the new Token in the Database
     *
     * @param option
     */
    private void checkAccessToken(String option){
        //System.out.println("Neue Token:");

        if(option == "page") {
            FacebookClient.AccessToken exAccessToken = fbPageClient.obtainExtendedAccessToken(appID, appSecret);
            if (exAccessToken.getAccessToken() != pageAccessToken) {
                setAccessToken("page", exAccessToken.getAccessToken());
                FacebookClient fbPageClient = new DefaultFacebookClient(exAccessToken.getAccessToken(), Version.LATEST);
                setFbClient("page", fbPageClient);
                // TODO: ************* Store new Token into Database *******************
                //System.out.println("Page: " + exAccessToken.getAccessToken());
            }
        }
        if(option == "user") {
            FacebookClient.AccessToken exAccessToken = fbUserClient.obtainExtendedAccessToken(appID, appSecret);
            if (exAccessToken.getAccessToken() != userAccessToken) {
                setAccessToken("user", exAccessToken.getAccessToken());
                FacebookClient fbUserClient = new DefaultFacebookClient(exAccessToken.getAccessToken(), Version.LATEST);
                setFbClient("user", fbUserClient);
                // TODO: ************* Store new Token into Database *******************
                //System.out.println("User: " + exAccessToken.getAccessToken());
            }
        }
    };

    /**
     * Setter class for the connection Object fbUserClient and fbPageClient
     *
     * @param option
     * @param fbClient
     */
    public void setFbClient(String option, FacebookClient fbClient) {
        if(option == "user") {
            this.fbUserClient = fbClient;
        }
        if(option == "page") {
            this.fbPageClient = fbClient;
        }
    }

    /**
     * Setter class for the String userAccessToken and pageAccessToken
     *
     * @param option
     * @param accessToken
     */
    public void setAccessToken(String option, String accessToken) {
        if(option == "user") {
            this.userAccessToken = accessToken;
        }
        if(option == "page") {
            this.pageAccessToken = accessToken;
        }

    }

    /**
     * Setter class the timestamp accessTokenExpireDate.
     * Not implemented yet
     *
     * @param accessTokenExpireDate
     */
    public void setAccessTokenExpireDate(int accessTokenExpireDate) {
        this.accessTokenExpireDate = accessTokenExpireDate;
    }

    /**
     * Setter class for the String appID.
     *
     * @param appID
     */
    public void setAppID(String appID) {
        this.appID = appID;
    }

    /**
     * Setter classe for the String appSecret
     *
     * @param appSecret
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    //public abstract String PostToFacebookTimeline(String myMessage, String myLink, String pathToImage, String pathToVideo);

    /**
     * abstract class buildFullFbURL
     *
     * @param postID
     * @return
     */
    public abstract String buildFullFbURL(String postID);

    /**
     * abstract class getPageName
     *
     * @param pageID
     * @return
     */
    public abstract String getPageName(String pageID);

    /**
     * abstract class postToFacebookPage
     *
     * @param pageID
     * @param myMessage
     * @param myLink
     * @param pathToImage
     * @param pathToVideo
     * @return
     * @throws IOException
     */
    public abstract String postToFacebookPage(String pageID, String myMessage, String myLink, String pathToImage, String pathToVideo) throws IOException;

    /**
     * abstract class postToFacebookGroups
     *
     * @param groupID
     * @param myMessage
     * @param myLink
     * @param pathToImage
     * @param pathToVideo
     * @return
     * @throws IOException
     */
    public abstract String postToFacebookGroups(String groupID, String myMessage, String myLink, String pathToImage, String pathToVideo) throws IOException;

    /**
     * abstract class getUserJoinedGroups
     *
     * @return
     */
    public abstract HashMap<String, String> getUserJoinedGroups();

    /**
     * abstract class getUserAdminPages
     *
     * @return
     */
    public abstract HashMap<String, String> getUserAdminPages();
}
