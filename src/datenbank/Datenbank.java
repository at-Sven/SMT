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

            execute("CREATE TABLE IF NOT EXISTS \"SocialmediaPosts\" (" +
                    "pid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "uid INTEGER NOT NULL, " +             // uid from Users
                    "tid INTEGER DEFAULT NULL, " +         // tid from TwitterAccounts
                    "fid INTEGER DEFAULT NULL, " +         // fid from FacebookAccounts
                    "platform INTEGER NOT NULL, " +        // 1 = twitter, 2 = facebook (where to post helper var)
                    "fbsite VARCHAR(300) DEFAULT NULL, " + // z.B.: me/feed, me/photos, pageId/feed, groupId/feed...
                    "posttext VARCHAR(2000), " +           // posttag+hashtags
                    "mediafile VARCHAR(500), " +           // image or video file
                    "posttime DATETIME NOT NULL, " +       // datetime when post will be send
                    "FOREIGN KEY (uid) REFERENCES Users(uid), " +
                    "FOREIGN KEY (tid) REFERENCES TwitterAccounts(tid), " +
                    "FOREIGN KEY (fid) REFERENCES FacebookAccounts(fid) " +
                    ");");

            execute("CREATE TABLE IF NOT EXISTS \"Hashtags\" (" +
                    "hid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "uid INTEGER NOT NULL, " +
                    "theme VARCHAR(100) NOT NULL, " +
                    "hashtags VARCHAR(500) NOT NULL, " +
                    "FOREIGN KEY (uid) REFERENCES Users(uid)" +
                    ");");

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
