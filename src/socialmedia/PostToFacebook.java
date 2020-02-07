package socialmedia;

import com.restfb.*;
import com.restfb.types.FacebookType;
import com.restfb.types.Group;
import com.restfb.types.Page;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * class PostToFacebook.
 * This class extends the abstract class FacebookConnector
 */
public class PostToFacebook extends FacebookConnector {
    public int uid;

    /**
     * Constructor of the class PostToFacebook
     *
     * @param aID
     * @param aSecret
     * @param aToken
     * @param option
     */
    public PostToFacebook(String aID, String aSecret, String aToken, String option) {
        super(aID, aSecret, aToken, option);
    }

    /*
    @Override
    public String PostToFacebookTimeline (String myMessage , String myLink, String pathToImage, String pathToVideo) {
        GraphResponse publishMessageResult = fbUserClient.publish("me/feed", GraphResponse.class, Parameter.with("message", myMessage));
        return publishMessageResult.getId();
    }
    */

    /**
     * This class builds a complete Facebook-URL from the returned postID of a posted message on your Page
     *
     * @param postID
     * @return
     */
    @Override
    public String buildFullFbURL (String postID) {
        // 107778870786744_109804447250853
        // https://www.facebook.com/JavaProjekt/posts/109825213915443
        // https://www.facebook.com/watch/?v=110337977197500
        String postURL = "https://www.facebook.com/" + postID.substring(0, postID.lastIndexOf("_")) + "/posts/" + postID.substring(postID.lastIndexOf("_")+1);

        getPageName (postID.substring(0, postID.lastIndexOf("_")));
        return postURL;
    }

    /**
     * This class gets the Name of a Page by given pageID. Needed for the method buildFullFbURL
     *
     * @param pageID
     * @return
     */
    @Override
    public String getPageName (String pageID) {
        Page myPage = fbPageClient.fetchObject(pageID, Page.class);
        return myPage.getName();
    }

    /**
     * This class handles the message, picture or movie you want to post on your Page.
     *
     * @param pageID
     * @param myMessage
     * @param myLink
     * @param pathToImage
     * @param pathToVideo
     * @return
     * @throws IOException
     */
    @Override
    public String postToFacebookPage (String pageID, String myMessage , String myLink, String pathToImage, String pathToVideo) throws IOException {
        if(pathToImage != "") {
            String imageName = pathToImage.substring(pathToImage.lastIndexOf("\\")+1);
            byte[] data = new byte[0];

            try {
                data = Files.readAllBytes(Paths.get(pathToImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

            FacebookType result = fbPageClient.publish(pageID + "/photos", FacebookType.class,
                    BinaryAttachment.with(imageName, data),
                    Parameter.with("message", myMessage),
                    Parameter.with("Link", myLink));

            return result.getId();
        } else if(pathToVideo != "") {
            String videoName = pathToVideo.substring(pathToImage.lastIndexOf("\\")+1);
            byte[] data = new byte[0];

            data = Files.readAllBytes(Paths.get(pathToVideo));

            FacebookType result = fbPageClient.publish(pageID + "/videos", FacebookType.class,
                    BinaryAttachment.with(videoName, data, Files.probeContentType(Paths.get(pathToVideo))),
                    Parameter.with("description", myMessage),
                    Parameter.with("Link", myLink));

            return result.getId();
        } else {
            FacebookType result = fbPageClient.publish(pageID + "/feed", FacebookType.class,
                    Parameter.with("message", myMessage),
                    Parameter.with("link", myLink));

            return result.getId();
        }
    }

    /**
     * This class handles the message, picture or movie you want to post in a Group.
     *
     * @param groupID
     * @param myMessage
     * @param myLink
     * @param pathToImage
     * @param pathToVideo
     * @return
     * @throws IOException
     */
    @Override
    public String postToFacebookGroups (String groupID, String myMessage, String myLink, String pathToImage, String pathToVideo) throws IOException {
        // Gruppe 187479062657054
        if(pathToImage != "") {
            String imageName = pathToImage.substring(pathToImage.lastIndexOf("\\")+1);
            byte[] data = new byte[0];

            try {
                data = Files.readAllBytes(Paths.get(pathToImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

            FacebookType result = fbUserClient.publish(groupID + "/photos", FacebookType.class,
                    BinaryAttachment.with(imageName, data),
                    Parameter.with("message", myMessage),
                    Parameter.with("Link", myLink));

            return result.getId();
        } else if(pathToVideo != "") {
            String videoName = pathToVideo.substring(pathToImage.lastIndexOf("\\")+1);
            byte[] data = new byte[0];

            data = Files.readAllBytes(Paths.get(pathToVideo));

            FacebookType result = fbUserClient.publish(groupID + "/videos", FacebookType.class,
                    BinaryAttachment.with(videoName, data, Files.probeContentType(Paths.get(pathToVideo))),
                    Parameter.with("description", myMessage),
                    Parameter.with("Link", myLink));

            return result.getId();
        } else {
            FacebookType result = fbUserClient.publish(groupID + "/feed", FacebookType.class,
                    Parameter.with("message", myMessage),
                    Parameter.with("link", myLink));

            return result.getId();
        }

    }

    /**
     * This class gets all Facebook Groups you have joined.
     *
     * @return
     */
    @Override
    public HashMap<String, String> getUserJoinedGroups () {
        Connection<Group> groups = fbUserClient.fetchConnection("me/groups", Group.class);

        HashMap<String, String> joinedGroups = new HashMap<String, String>();
        for (List<Group> groupPage : groups) {
            if(groupPage.size() > 0) {
                for (Group aGroup : groupPage) {
                    joinedGroups.put(aGroup.getName(), aGroup.getId());
                }
            }
        }

        return joinedGroups;
    }

    /**
     * This class gets all Facebook Pages where you are Admin.
     *
     * @return
     */
    @Override
    public HashMap<String, String> getUserAdminPages () {
        Connection<Page> pages = fbUserClient.fetchConnection("me/accounts", Page.class);

        HashMap<String, String> adminPages = new HashMap<String, String>();
        for (List<Page> feedPage : pages) {
            if(feedPage.size() > 0) {
                for (Page aPage : feedPage) {
                    adminPages.put(aPage.getName(), aPage.getId());
                }
            }
        }

        return adminPages;
    }
}
