package socialmedia;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

import java.io.IOException;

/**
 *
 * @author Kilian Spohr
 * @version 0.1
 */

public abstract class FacebookConnector {
    public String userAccessToken;
    public String pageAccessToken;
    public int accessTokenExpireDate;
    public String appID;
    public String appSecret;
    public FacebookClient fbUserClient;
    public FacebookClient fbPageClient;

    public FacebookConnector(String appID, String appSecret, String accessToken, String option) {
        setAppID(appID);
        setAppSecret(appSecret);
        setAccessToken(option, accessToken);
        connectToFb(option);
        checkAccessToken(option);
    }

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

    private void checkAccessToken(String option){
        System.out.println("Neue Token:");

        if(option == "page") {
            FacebookClient.AccessToken exAccessToken = fbPageClient.obtainExtendedAccessToken(appID, appSecret);
            if (exAccessToken.getAccessToken() != pageAccessToken) {
                setAccessToken("page", exAccessToken.getAccessToken());
                FacebookClient fbPageClient = new DefaultFacebookClient(exAccessToken.getAccessToken(), Version.LATEST);
                setFbClient("page", fbPageClient);

                System.out.println("Page: " + exAccessToken.getAccessToken());
            }
        }
        if(option == "user") {
            FacebookClient.AccessToken exAccessToken = fbUserClient.obtainExtendedAccessToken(appID, appSecret);
            if (exAccessToken.getAccessToken() != userAccessToken) {
                setAccessToken("user", exAccessToken.getAccessToken());
                FacebookClient fbUserClient = new DefaultFacebookClient(exAccessToken.getAccessToken(), Version.LATEST);
                setFbClient("user", fbUserClient);

                System.out.println("User: " + exAccessToken.getAccessToken());
            }
        }
    };
    
    public void setFbClient(String option, FacebookClient fbClient) {
        if(option == "user") {
            this.fbUserClient = fbClient;
        }
        if(option == "page") {
            this.fbPageClient = fbClient;
        }
    }

    public void setAccessToken(String option, String accessToken) {
        if(option == "user") {
            this.userAccessToken = accessToken;
        }
        if(option == "page") {
            this.pageAccessToken = accessToken;
        }

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

    public abstract String buildFullFbURL(String postID);

    public abstract String GetPageName(String pageID);

    public abstract String PostToFacebookPage(String pageID, String myMessage, String myLink, String pathToImage, String pathToVideo) throws IOException;

    public abstract String PostToFacebookGroups(String groupID, String myMessage, String myLink, String pathToImage, String pathToVideo) throws IOException;

    public abstract String[][] getUserJoinedGroups();

    public abstract String[][] getUserAdminPages();
}
