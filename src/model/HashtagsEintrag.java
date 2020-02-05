package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HashtagsEintrag {

    IntegerProperty hid;
    IntegerProperty uid;
    StringProperty theme;
    StringProperty hashtags;

    public HashtagsEintrag(Integer hid, Integer uid, String theme, String hashtags) {
        this.hid = null;
        this.uid = new SimpleIntegerProperty(uid);
        this.theme = new SimpleStringProperty(theme);
        this.hashtags = new SimpleStringProperty(hashtags);
    }

    public HashtagsEintrag() {
        this.hid = new SimpleIntegerProperty();
        this.uid = new SimpleIntegerProperty();
        this.theme = new SimpleStringProperty("");
        this.hashtags = new SimpleStringProperty("");
    }

    public String getTheme() {
        return theme.get();
    }

    public StringProperty themeProperty() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme.set(theme);
    }

    public String getHashtags() {
        return hashtags.get();
    }

    public StringProperty hashtagsProperty() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags.set(hashtags);
    }

    public int getHid() {
        return hid.get();
    }

    public IntegerProperty hidProperty() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid.set(hid);
    }

    public int getUid() {
        return uid.get();
    }

    public IntegerProperty uidProperty() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid.set(uid);
    }
}
