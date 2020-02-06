package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Model Class for the Hashtag Lists of the Program
 */
public class HashtagsEintrag {

    IntegerProperty hid;
    IntegerProperty uid;
    StringProperty theme;
    StringProperty hashtags;

    /**
     * Constructor method to create a new Hashtag list
     *
     * @param hid      hashtag ID
     * @param uid      user ID
     * @param theme    the theme of a List
     * @param hashtags the Hashtags in the new list
     */
    public HashtagsEintrag(Integer hid, Integer uid, String theme, String hashtags) {
        this.hid = null;
        this.uid = new SimpleIntegerProperty(uid);
        this.theme = new SimpleStringProperty(theme);
        this.hashtags = new SimpleStringProperty(hashtags);
    }

    /**
     * Constructor method to create a empty new Hashtag list
     */
    public HashtagsEintrag() {
        this.hid = new SimpleIntegerProperty();
        this.uid = new SimpleIntegerProperty();
        this.theme = new SimpleStringProperty("");
        this.hashtags = new SimpleStringProperty("");
    }

    /**
     * Getter method for the Theme of a Hashtag list
     *
     * @return the Theme as String
     */
    public String getTheme() {
        return theme.get();
    }


    public StringProperty themeProperty() {
        return theme;
    }

    /**
     * Setter method for the Theme of a Hashtag list
     *
     * @param theme the Theme as String
     */
    public void setTheme(String theme) {
        this.theme.set(theme);
    }

    /**
     * Getter method for the Hashtags inside a list
     *
     * @return the hashtags as String
     */
    public String getHashtags() {
        return hashtags.get();
    }

    public StringProperty hashtagsProperty() {
        return hashtags;
    }

    /**
     * Setter method for the Hashtags inside a list
     *
     * @param hashtags the Hashtags as String
     */
    public void setHashtags(String hashtags) {
        this.hashtags.set(hashtags);
    }

    /**
     * Getter method for the ID of a Hashtag list
     *
     * @return the ID as int value
     */
    public int getHid() {
        return hid.get();
    }

    public IntegerProperty hidProperty() {
        return hid;
    }

    /**
     * Setter method for the ID of a Hashtag list
     *
     * @param hid the ID as int value
     */
    public void setHid(int hid) {
        this.hid.set(hid);
    }

    /**
     * Getter method for the ID of the User, which own the Hashtag list
     *
     * @return the ID as int value
     */
    public int getUid() {
        return uid.get();
    }

    public IntegerProperty uidProperty() {
        return uid;
    }

    /**
     * Setter method for the ID of the User, which own the Hashtag list
     *
     * @param uid the ID as int value
     */
    public void setUid(int uid) {
        this.uid.set(uid);
    }
}
