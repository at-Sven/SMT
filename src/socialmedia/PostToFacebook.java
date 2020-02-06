package socialmedia;

import com.restfb.*;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import com.restfb.types.Page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public PostToFacebook(String aID, String aSecret, String aToken) {
        super(aID, aSecret, aToken);
    }

    @Override
    public String PostToFacebookTimeline (String myMessage , String myLink, String pathToImage, String pathToVideo) {
        GraphResponse publishMessageResult = fbClient.publish("me/feed", GraphResponse.class, Parameter.with("message", myMessage));
        return publishMessageResult.getId();
    }

    @Override
    public String GetPageName (String pageID) {
        Page myPage = fbClient.fetchObject(pageID, Page.class);
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

            FacebookType result = fbClient.publish(pageID + "/photos", FacebookType.class,
                    BinaryAttachment.with(imageName, data),
                    Parameter.with("message", myMessage),
                    Parameter.with("Link", myLink));

            return result.getId();
        } else if(pathToVideo != "") {
            String videoName = pathToVideo.substring(pathToImage.lastIndexOf("\\")+1);
            byte[] data = new byte[0];

            data = Files.readAllBytes(Paths.get(pathToVideo));

            FacebookType result = fbClient.publish(pageID + "/videos", FacebookType.class,
                    BinaryAttachment.with(videoName, data, Files.probeContentType(Paths.get(pathToVideo))),
                    Parameter.with("description", myMessage),
                    Parameter.with("Link", myLink));

            return result.getId();
        } else {
            FacebookType result = fbClient.publish(pageID + "/feed", FacebookType.class,
                    Parameter.with("message", myMessage),
                    Parameter.with("link", myLink));

            return result.getId();
        }
    }



    @Override
    public void PostToFacebookGroups (Array groupID, String myMessage, String myLink, String pathToImage, String pathToVideo) {
        // Gruppe 187479062657054
    }

}
