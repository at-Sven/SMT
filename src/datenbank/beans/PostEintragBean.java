package datenbank.beans;

import model.HashtagsEintrag;
import model.PostEintrag;
import datenbank.Datenbank;
import model.PostEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Bean Class for the Posts in the database
 */
public class PostEintragBean {

    private static PreparedStatement pstmtInsertPost;
    private static PreparedStatement pstmtSelectScheduledPostsWithUid;
    private static PreparedStatement pstmtSelectScheduledPostsWithUidAndPoststatus;
    private static PreparedStatement pstmtGetPosts;
    private static PreparedStatement pstmtDelete;
    private static PreparedStatement pstmtUpdatePostStatusToSuccessfulOrError;

    private static HashMap<PostEintrag, Integer> idListe;

    /**
     * Initialisierungsblock
     * Wird ausgeführt wenn die Klasse erzeugt wird
     */
    static {
        // System.out.println("static-Block ausgeführt");

        // Statements vorbereiten
        pstmtInsertPost = Datenbank.getInstance().prepareStatement("INSERT INTO SocialmediaPosts (sid, uid, platform, fbsite, posttext, mediafile, posttime, poststatus) " +
                " VALUES ( (SELECT sid FROM SocialmediaAccounts WHERE uid = ?) , ?, ?, ?, ?, ?, ?, ?)");
        pstmtSelectScheduledPostsWithUid = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaPosts WHERE uid = ?;");

        // hole nur posts die noch nicht gesendet wurden: (bedeutet poststatus == 0)
        pstmtSelectScheduledPostsWithUidAndPoststatus = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaPosts WHERE uid = ? AND poststatus = 0;");

        pstmtGetPosts = Datenbank.getInstance().prepareStatement("SELECT posttext, posttime, platform FROM SocialmediaPosts;");

        pstmtDelete = Datenbank.getInstance().prepareStatement("DELETE FROM SocialmediaPosts WHERE pid = ?;");

        pstmtUpdatePostStatusToSuccessfulOrError = Datenbank.getInstance().prepareStatement("UPDATE SocialmediaPosts SET poststatus = ? WHERE pid = ?;");

        idListe = new HashMap<>();

        /*INSERT INTO telefonbuch (vorname, nachname, strasse, hausnr, plz, ort, telefonnr) VALUES (?, ?, ?, ?, ?, ?, ?);");
        pstmtSelect = Datenbank.getInstance().prepareStatement("SELECT Vorname, Nachname, Straße, HausNr, PLZ, Ort, TelefonNr FROM telefonbuch;");
        pstmtInsertVorname = Datenbank.getInstance().prepareStatement("INSERT INTO table_vorname (vorname) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM table_vorname WHERE vorname = ?);");
        pstmtInsertNachname = Datenbank.getInstance().prepareStatement("INSERT INTO table_nachname (nachname) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM table_nachname WHERE nachname = ?);");
        pstmtInsertStrasse = Datenbank.getInstance().prepareStatement("INSERT INTO table_strasse (strasse) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM table_strasse WHERE strasse = ?);");
        pstmtInsertHausnr = Datenbank.getInstance().prepareStatement("INSERT INTO table_hausnr (hausnr) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM table_hausnr WHERE hausnr = ?);");
        pstmtInsertPlz = Datenbank.getInstance().prepareStatement("INSERT INTO table_plz (plz) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM table_plz WHERE plz = ?);");
        pstmtInsertOrt = Datenbank.getInstance().prepareStatement("INSERT INTO table_ort (ort) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM table_ort WHERE ort = ?);");
        pstmtInsert = Datenbank.getInstance().prepareStatement("INSERT INTO table_telefonbuch (vorname_id, nachname_id, strasse_id, hausnr_id, plz_id, ort_id, telefonnr)" +
                                                                        "SELECT " +
                                                                        "(SELECT id FROM table_vorname WHERE vorname = ?), " +
                                                                        "(SELECT id FROM table_nachname WHERE nachname = ?), " +
                                                                        "(SELECT id FROM table_strasse WHERE strasse = ?), " +
                                                                        "(SELECT id FROM table_hausnr WHERE hausnr = ?), " +
                                                                        "(SELECT id FROM table_plz WHERE plz = ?), " +
                                                                        "(SELECT id FROM table_ort WHERE ort = ?), " +
                                                                        "?;");
       pstmtUpdate = Datenbank.getInstance().prepareStatement("UPDATE table_telefonbuch SET " +
                                                                        "vorname_id = (SELECT id FROM table_vorname WHERE vorname = ?), " +
                                                                        "nachname_id = (SELECT id FROM table_nachname WHERE nachname = ?), " +
                                                                        "strasse_id = (SELECT id FROM table_strasse WHERE strasse = ?), " +
                                                                        "hausnr_id = (SELECT id FROM table_hausnr WHERE hausnr = ?), " +
                                                                        "plz_id = (SELECT id FROM table_plz WHERE plz = ?), " +
                                                                        "ort_id = (SELECT id FROM table_ort WHERE ort = ?), " +
                                                                        "telefonnr = ? WHERE telefonnr = ?;");
        pstmtDelete = Datenbank.getInstance().prepareStatement("DELETE FROM table_telefonbuch WHERE telefonnr = ?;");*/

    }

    /**
     * Setzt den poststatus eines postes zu entweder Erfolgreich mit 1 oder Error mit 2
     * damit es keine duplizierten posts gibt bzw. fehlerhafte posts nicht erneut Fehler über die APIs verursachen.
     * @param pid int ist postid des bearbeiteteten / geposteten Posts
     * @param status wenn Erfolgreich = 1, Fehler = 2 oder grösser 2
     * @return true im Erfolgsfall, false andernfalls
     */
    public static boolean updatePostStatusToSuccessfulOrError(int pid, int status) {
        boolean result = false;

        try {
            pstmtUpdatePostStatusToSuccessfulOrError.setInt(1, status);
            pstmtUpdatePostStatusToSuccessfulOrError.setInt(2, pid);

            pstmtUpdatePostStatusToSuccessfulOrError.executeUpdate();

            // Prüfen ob der Eintrag erfolgreich war. Wenn ja, dann werden die Informationen in die Datenbank
            // übertragen (commit). Wenn nicht, werden sie verworfen (rollback)

            Datenbank.getInstance().commit();
            result = true;

        } catch (SQLException ignored) {}

        return result;
    }

    /**
     * Fügt das übergebene Objekt in die Datenbank ein und gibt zurück, ob der Vorgang erfolgreich war.
     *
     * @param uid userid
     * @param platform
     * @param fbsite
     * @param posttext
     * @param mediafile
     * @param posttime
     * @param poststatus
     * @return true im Erfolgsfall, false andernfalls
     */
    public static boolean insertNewPost(int uid, int platform, String fbsite, String posttext, String mediafile, String posttime, int poststatus ) {
        boolean result = false;
        int rows;
        try {

            pstmtInsertPost.setInt(1,uid);
            pstmtInsertPost.setInt(2,platform);
            pstmtInsertPost.setString(3,fbsite);
            pstmtInsertPost.setString(4,posttext);
            pstmtInsertPost.setString(5,mediafile);
            pstmtInsertPost.setString(6,posttime);
            pstmtInsertPost.setInt(7,poststatus); // send 0 for new post, not send yet

            rows = pstmtInsertPost.executeUpdate();

            // Prüfen ob der Eintrag erfolgreich war. Wenn ja, dann werden die Informationen in die Datenbank
            // übertragen (commit). Wenn nicht, werden sie verworfen (rollback)
            if (rows == 1) {
                Datenbank.getInstance().commit();
                result = true;
            } else {
                Datenbank.getInstance().rollback();
            }

        } catch(SQLException ignored){
        }

        return result;


    }

    /**
     * Fügt das übergebene Objekt in die Datenbank ein und gibt zurück, ob der Vorgang erfolgreich war.
     * @param pe new PostEintrag
     * @return result true if insert ok
     */
    public static boolean insertNewPost(PostEintrag pe) {
        boolean result = false;
        int rows;
        try {

            pstmtInsertPost.setInt(1,pe.getUid());
            pstmtInsertPost.setInt(2,pe.getUid());
            pstmtInsertPost.setInt(3,pe.getPlatform());
            pstmtInsertPost.setString(4,pe.getFbsite());
            pstmtInsertPost.setString(5,pe.getPosttext());
            pstmtInsertPost.setString(6,pe.getMediafile());
            pstmtInsertPost.setString(7,pe.getPosttime());
            pstmtInsertPost.setInt(8,pe.getPoststatus()); // send 0 for new post, not send yet

            rows = pstmtInsertPost.executeUpdate();

            // Prüfen ob der Eintrag erfolgreich war. Wenn ja, dann werden die Informationen in die Datenbank
            // übertragen (commit). Wenn nicht, werden sie verworfen (rollback)
            if (rows == 1) {
                Datenbank.getInstance().commit();
                result = true;
            } else {
                Datenbank.getInstance().rollback();
            }

        } catch(SQLException ignored){
        }

        return result;


    }

    public static ArrayList<PostEintrag> selectScheduledPostsWithUidAndPoststatus(int uid) {
        ArrayList<PostEintrag> result = null;

        try {
            // Datenbankabfrage ausführen
            pstmtSelectScheduledPostsWithUidAndPoststatus.setInt(1,uid);

            ResultSet rs = pstmtSelectScheduledPostsWithUidAndPoststatus.executeQuery();

            // Result initialisieren
            result = new ArrayList<>();

            // Zurücksetzen der idListe
            idListe.clear();

            // Alle Datensätze abfragen und passend dazu neue Einträge generieren
            while (rs.next()) {
                PostEintrag eintrag = new PostEintrag(
                        rs.getInt("pid"),
                        rs.getInt("uid"),
                        rs.getInt("sid"),
                        rs.getInt("platform"),
                        rs.getString("fbsite"),
                        rs.getString("posttext"),
                        rs.getString("mediafile"),
                        rs.getString("posttime"),
                        rs.getInt("poststatus")
                );
                result.add(eintrag);

                // Objekt der idListe hinzufügen
                idListe.put(eintrag, eintrag.getPid());
            }

        } catch (SQLException ignored) {}

        return result;
    }

    public static ArrayList<PostEintrag> selectAllPostsWithUid(int uid) {
        ArrayList<PostEintrag> result = null;

        try {
            // Datenbankabfrage ausführen
            pstmtSelectScheduledPostsWithUid.setInt(1,uid);

            ResultSet rs = pstmtSelectScheduledPostsWithUid.executeQuery();

            // Result initialisieren
            result = new ArrayList<>();

            // Zurücksetzen der idListe
            idListe.clear();

            // Alle Datensätze abfragen und passend dazu neue Einträge generieren
            while (rs.next()) {
                PostEintrag eintrag = new PostEintrag(
                        rs.getInt("pid"),
                        rs.getInt("uid"),
                        rs.getInt("sid"),
                        rs.getInt("platform"),
                        rs.getString("fbsite"),
                        rs.getString("posttext"),
                        rs.getString("mediafile"),
                        rs.getString("posttime"),
                        rs.getInt("poststatus")
                );
                result.add(eintrag);

                // Objekt der idListe hinzufügen
                idListe.put(eintrag, eintrag.getPid());
            }

        } catch (SQLException ignored) {}

        return result;
    }



    /**
     * This method take all saved Posts from the Database
     *
     * @return List of Posts
     */
    public static ArrayList<PostEintrag> getPosts() {
        ArrayList<PostEintrag> result = null;

        try {
            ResultSet rs = pstmtGetPosts.executeQuery();
            result = new ArrayList<>();

            while (rs.next()) {
                PostEintrag eintrag = new PostEintrag();
                eintrag.setPosttext(rs.getString(1));
                eintrag.setPosttime(rs.getString(2));
                eintrag.setPlatform(rs.getInt(3));

                result.add(eintrag);
            }

            rs.close();

        } catch (SQLException ignored) {}

        return result;
    }

    /**
     * Löscht einen übergebenen PostEintrag aus der Datenbank
     *
     * @param selecedPost PostEintrag, der gelöscht werden soll
     * @return true, wenn das Löschen erfolgreich war, false andernfalls
     */
    public static boolean delete(PostEintrag selecedPost) {
        boolean result = false;

        try {
            pstmtDelete.setInt(1, selecedPost.getPid());
            pstmtDelete.executeUpdate();
            result = true;

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen des TelefonbuchEintrags aus der Datenbank: " + e.getLocalizedMessage());
        }

        return result;
    }
}
