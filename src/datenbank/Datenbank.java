package datenbank;

import datenbank.connection.SQLiteDatenbankverbindung;

import java.sql.SQLException;

/**
 * Klasse zur Steuerung der Datenbankvorgänge. Definiert als Singelton:
 * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
 */
public class Datenbank extends SQLiteDatenbankverbindung {

    private static Datenbank db;
    private final static String DBFILE = "smt_db.sqlite";

    /**
     * private Konstruktor
     */
    private Datenbank() {
        super(DBFILE);
    }

    /**
     * Klassenmethode, die die Instanz der Datenbankklasse zurückgibt (Fabrikmethode genannt)
     *
     * @return Instanz der Datenbank-Klasse
     */
    public static Datenbank getInstance() {
        if (db == null) {
            // Neue Instanz von Datenbank erzeugen
            db = new Datenbank();
            db.init();
        }

        return db;
    }

    /**
     * Diese Methode soll die erforderliche Datenstruktur der Datenbank herstellen
     */
    private void init() {
        try {
            execute("CREATE TABLE IF NOT EXISTS \"Users\" (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "Email VARCHAR(100) NOT NULL, " +
                    "Passwort VARCHAR(100) NOT NULL" +
                    ");");

            execute("CREATE TABLE IF NOT EXISTS \"SocialmediaAccounts\" (" +
                    "sid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "uid INTEGER UNIQUE NOT NULL, " +
                    "twConsumerKey VARCHAR(200), " +
                    "twConsumerSecret VARCHAR(200), " +
                    "twAccessToken VARCHAR(200), " +
                    "twAccessTokenSecret VARCHAR(200)," +
                    "fbAppID VARCHAR(500), " +            // facebook AppID
                    "fbAppSecret VARCHAR(500), " +        // facebook AppSecret
                    "fbAccessToken VARCHAR(500), " +      // fb accesstoken field, if needed
                    "fbUserdata VARCHAR(500), " +         // fbuserdata extra field , if needed
                    "fbAccessTokenExpireDate INTEGER, " +  // temp feld, falls andere noch benoetigt werden sollten
                    "soc1 VARCHAR(300), " +               // temp feld, falls andere noch benoetigt werden sollten
                    "soc2 VARCHAR(300), " +               // temp feld, falls andere noch benoetigt werden sollten
                    "soc3 VARCHAR(300), " +               // temp feld, falls andere noch benoetigt werden sollten
                    "soc4 VARCHAR(300), " +               // temp feld, falls andere noch benoetigt werden sollten
                    "soc5 VARCHAR(300), " +               // temp feld, falls andere noch benoetigt werden sollten
                    "soc6 VARCHAR(300), " +               // temp feld, falls andere noch benoetigt werden sollten
                    "FOREIGN KEY (uid) REFERENCES Users(uid)" +
                    ");");


            execute("CREATE TABLE IF NOT EXISTS \"SocialmediaPosts\" (" +
                    "pid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "uid INTEGER NOT NULL, " +             // uid from Users
                    "sid INTEGER NOT NULL, " +         // sid from SocialMediaAccounts
                    "platform INTEGER NOT NULL, " +        // 1 = twitter, 2 = facebook (where to post helper var)
                    "fbsite VARCHAR(300) DEFAULT NULL, " + // z.B.: me/feed, me/photos, pageId/feed, groupId/feed...
                    "posttext VARCHAR(2000), " +           // posttag+hashtags
                    "mediafile VARCHAR(500), " +           // image or video file
                    "posttime DATETIME NOT NULL, " +       // datetime when post will be send
                    "FOREIGN KEY (uid) REFERENCES Users(uid), " +
                    "FOREIGN KEY (sid) REFERENCES SocialmediaAccounts(sid) " +
                    ");");

            execute("CREATE TABLE IF NOT EXISTS \"Hashtags\" (" +
                    "hid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "uid INTEGER NOT NULL, " +
                    "theme VARCHAR(100) NOT NULL, " +
                    "hashtags VARCHAR(500) NOT NULL, " +
                    "FOREIGN KEY (uid) REFERENCES Users(uid)" +
                    ");");

            /* // TwitterAccounts und FacebookAccounts in einem Table SocialmediaAccounts verfügbar
               // hier als Kommentar, falls es doch extra benötigt werden sollte:
            execute("CREATE TABLE IF NOT EXISTS \"TwitterAccounts\" (" +
                    "tid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "uid INTEGER NOT NULL, " +
                    "consumerKey VARCHAR(200) NOT NULL," +
                    "consumerSecret VARCHAR(200) NOT NULL," +
                    "accessToken VARCHAR(200) NOT NULL," +
                    "accessTokenSecret VARCHAR(200) NOT NULL," +
                    "FOREIGN KEY (uid) REFERENCES Users(uid)" +
                    ");");

            execute("CREATE TABLE IF NOT EXISTS \"FacebookAccounts\" (" +
                    "fid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "uid INTEGER NOT NULL, " +
                    "username VARCHAR(200) NOT NULL," +
                    "passwort VARCHAR(200) NOT NULL," +
                    "appID VARCHAR(200) NOT NULL," +
                    "appSecret VARCHAR(200) NOT NULL," +
                    "FOREIGN KEY (uid) REFERENCES Users(uid)" +
                    ");");

            */

            this.commit();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Diese Methode pr&uuml;ft, ob eine Datenbankverbindung da ist
     *
     * @return Status der Datenbank
     */
    public static Boolean isConnected() {
        if (db != null) {
            return true;
        }
        return false;
    }
}
