package socialmedia;

import com.restfb.*;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import com.restfb.types.Group;
import com.restfb.types.Page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class PostToFacebook extends FacebookConnector {

    public int uid;
    public String fbPost;
    public String hashTags;
    public String pathToImage;
    public String pathToVideo;
    public String postOption;
    public int groupID;
    public int pageID;
    public int meID;

    public PostToFacebook(String aID, String aSecret, String aToken, String option) {
        super(aID, aSecret, aToken, option);
    }

    @Override
    public String PostToFacebookTimeline (String myMessage , String myLink, String pathToImage, String pathToVideo) {
        GraphResponse publishMessageResult = fbUserClient.publish("me/feed", GraphResponse.class, Parameter.with("message", myMessage));
        return publishMessageResult.getId();
    }

    @Override
    public String buildFullFbURL (String postID) {
        //   107778870786744_109804447250853
        // https://www.facebook.com/JavaProjekt/posts/109825213915443
        // https://www.facebook.com/watch/?v=110337977197500
        String postURL = "https://www.facebook.com/" + postID.substring(0, postID.lastIndexOf("_")) + "/posts/" + postID.substring(postID.lastIndexOf("_")+1);

        GetPageName (postID.substring(0, postID.lastIndexOf("_")));

        return postURL;
    }

    @Override
    public String GetPageName (String pageID) {
        Page myPage = fbPageClient.fetchObject(pageID, Page.class);

        System.out.println(myPage);
        return myPage.getName();
    }

    @Override
    public String PostToFacebookPage (String pageID, String myMessage , String myLink, String pathToImage, String pathToVideo) throws IOException {
        // Seite 107778870786744

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



    @Override
    public String PostToFacebookGroups (String groupID, String myMessage, String myLink, String pathToImage, String pathToVideo) throws IOException {
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

    @Override
    public HashMap getUserJoinedGroups () {
        Connection<Group> groups = fbUserClient.fetchConnection("me/groups", Group.class);

        HashMap joinedGroups = new HashMap();
        for (List<Group> groupPage : groups) {
            if(groupPage.size() > 0) {
                for (Group aGroup : groupPage) {
                    joinedGroups.put(aGroup.getName(), aGroup.getId());
                }
            }
        }

        return joinedGroups;
    }

    @Override
    public HashMap getUserAdminPages () {
        Connection<Page> pages = fbUserClient.fetchConnection("me/accounts", Page.class);

        HashMap adminPages = new HashMap();
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
