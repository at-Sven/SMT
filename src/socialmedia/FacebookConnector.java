package socialmedia;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 *
 * @author Kilian Spohr
 * @version 0.1
 */

public abstract class FacebookConnector {
    public String accessToken;
    public int accessTokenExpireDate;
    public String appID;
    public String appSecret;
    public FacebookClient fbClient;

    public FacebookConnector(String appID, String appSecret, String accessToken) {
        setAppID(appID);
        setAppSecret(appSecret);
        setAccessToken(accessToken);
        connectToFb();
        checkAccessToken();
    }

    private void connectToFb() {
        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
        setFbClient(fbClient);
    }

    private void checkAccessToken(){
        FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken(appID, appSecret);
        if(exAccessToken.getAccessToken() != accessToken) {
            setAccessToken(exAccessToken.getAccessToken());
            FacebookClient fbClient = new DefaultFacebookClient(exAccessToken.getAccessToken(), Version.LATEST);
            setFbClient(fbClient);
        }
    };
    
    public void setFbClient(FacebookClient fbClient) {
        this.fbClient = fbClient;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessTokenExpireDate(int accessTokenExpireDate) {
        this.accessTokenExpireDate = accessTokenExpireDate;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public abstract String PostToFacebookTimeline(String myMessage, String myLink, String pathToImage, String pathToVideo);

    public abstract String GetPageName(String pageID);

    public abstract String PostToFacebookPage(String pageID, String myMessage, String myLink, String pathToImage, String pathToVideo) throws IOException;

    public abstract void PostToFacebookGroups(Array groupID, String myMessage, String myLink, String pathToImage, String pathToVideo);
}
