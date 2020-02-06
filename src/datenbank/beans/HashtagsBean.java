package datenbank.beans;

import datenbank.Datenbank;
import model.HashtagsEintrag;
import model.PostEintrag;
import model.UserEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Bean Class for the Hashtag Lists in the database
 */
public class HashtagsBean {

    // PreparedStatements
    private static PreparedStatement pstmtSelect;
    private static PreparedStatement pstmtSelectAll;
    private static PreparedStatement pstmtSelectThemes;
    private static PreparedStatement pstmtInsert;
    private static PreparedStatement pstmtUpdate;
    private static PreparedStatement pstmtDelete;


    static {
        pstmtSelect = Datenbank.getInstance().prepareStatement("SELECT theme, hashtags FROM Hashtags WHERE theme = ? AND hashtags = ?;");
        pstmtSelectAll = Datenbank.getInstance().prepareStatement("SELECT * FROM Hashtags;");
        pstmtSelectThemes = Datenbank.getInstance().prepareStatement("SELECT hid, theme, hashtags FROM Hashtags;");
        pstmtInsert = Datenbank.getInstance().prepareStatement("INSERT INTO Hashtags (uid, theme, hashtags) VALUES (?, ?, ?);");
        pstmtUpdate = Datenbank.getInstance().prepareStatement("UPDATE Hashtags SET theme = ?, hashtags = ? WHERE theme = ? AND hashtags = ?;");
        pstmtDelete = Datenbank.getInstance().prepareStatement("DELETE FROM Hashtags WHERE hid = ?;");
    }

    /**
     * Gibt eine Liste mit allen in der Datenbank vorhandennen Objekten zurück.
     *
     * @return Liste mit allen Objekten aus der Datenbank oder null
     */
    public static ArrayList<HashtagsEintrag> getThemes() {
        ArrayList<HashtagsEintrag> result = null;

        try {
            ResultSet rs = pstmtSelectThemes.executeQuery();
            result = new ArrayList<>();

            while (rs.next()) {
                HashtagsEintrag eintrag = new HashtagsEintrag();
                eintrag.setHid(rs.getInt(1));
                eintrag.setTheme(rs.getString(2));
                eintrag.setHashtags(rs.getString(3));

                result.add(eintrag);
            }

            rs.close();

        } catch (SQLException ignored) {
        }

        return result;
    }


    /**
     * Fügt das übergebene Objekt in die Datenbank ein und gibt zurück, ob der Vorgang erfolgreich war.
     *
     * @param zuSpeichern Das in die Datenbank zu schreibende Objekt
     * @return true im Erfolgsfall, false andernfalls
     */
    public static boolean insert(HashtagsEintrag zuSpeichern) {
        boolean result = false;

        try {
            pstmtInsert.setInt(1, zuSpeichern.getUid());
            pstmtInsert.setString(2, zuSpeichern.getTheme());
            pstmtInsert.setString(3, zuSpeichern.getHashtags());

            int rows = pstmtInsert.executeUpdate();

            // Prüfen ob der Eintrag erfolgreich war. Wenn ja, dann werden die Informationen in die Datenbank
            // übertragen (commit). Wenn nicht, werden sie verworfen (rollback)
            if (rows == 1) {
                Datenbank.getInstance().commit();
                result = true;
            } else {
                Datenbank.getInstance().rollback();
            }

        } catch (SQLException ignored) {
        }

        return result;
    }

    /**
     * This method delete a selected Hashtag list from the database
     *
     * @param selectedHashList selected tableview entry
     * @return true, if the delete was a success
     */
    public static boolean delete(HashtagsEintrag selectedHashList) {
        boolean result = false;

        try {
            pstmtDelete.setInt(1, selectedHashList.getHid());
            pstmtDelete.executeUpdate();
            result = true;

            Datenbank.getInstance().commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen des TelefonbuchEintrags aus der Datenbank: " + e.getLocalizedMessage());
        }

        return result;
    }

    /**
     * Ersetz das zweite übergebene Objekt mit dem ersten übergebenen Objekt in der Datenbank
     * und gibt zurück, ob der Vorgang erfolgreich war.
     *
     * @param neu Das in die Datenbank zu schreibende Objekt
     * @param alt Das in die Datenbank zu ersetzende Objekt
     * @return true im Erfolgsfall, false andernfalls
     */
    public static boolean update(HashtagsEintrag neu, HashtagsEintrag alt) {
        boolean result = false;

        try {
            pstmtUpdate.setString(1, neu.getTheme());
            pstmtUpdate.setString(2, neu.getHashtags());
            pstmtUpdate.setString(3, alt.getTheme());
            pstmtUpdate.setString(4, alt.getHashtags());

            int rows = pstmtUpdate.executeUpdate();

            // Prüfen ob der Eintrag erfolgreich war. Wenn ja, dann werden die Informationen in die Datenbank
            // übertragen (commit). Wenn nicht, werden sie verworfen (rollback)
            if (rows == 1) {
                Datenbank.getInstance().commit();
                result = true;
            } else {
                Datenbank.getInstance().rollback();
            }

        } catch (SQLException ignored) {
        }

        return result;
    }

}
