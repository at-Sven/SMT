package datenbank.beans;

import datenbank.Datenbank;
import model.UserEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Die Bean KLasse für die User Tabelle in der Datenbank
 */
public class UserBean {

    // PreparedStatements
    private static PreparedStatement pstmtSelect;
    private static PreparedStatement pstmtSelectwithUid;
    private static PreparedStatement pstmtSelectEmail;
    private static PreparedStatement pstmtSelectAll;
    private static PreparedStatement pstmtInsert;
    private static PreparedStatement pstmtUpdate;
    private static PreparedStatement pstmtDelete;


    static {
        pstmtSelect = Datenbank.getInstance().prepareStatement("SELECT Email, Passwort FROM Users WHERE Email = ? AND Passwort = ?;");
        pstmtSelectwithUid = Datenbank.getInstance().prepareStatement("SELECT uid, Email, Passwort FROM Users WHERE Email = ? AND Passwort = ?;");
        pstmtSelectEmail = Datenbank.getInstance().prepareStatement("SELECT Email FROM Users WHERE Email = ?;");
        pstmtSelectAll = Datenbank.getInstance().prepareStatement("SELECT * FROM Users;");
        pstmtInsert = Datenbank.getInstance().prepareStatement("INSERT INTO Users (Email, Passwort) VALUES (?, ?);");
        pstmtUpdate = Datenbank.getInstance().prepareStatement("UPDATE Users SET Email = ?, Passwort = ? WHERE email = ? AND Passwort = ?;");
        pstmtDelete = Datenbank.getInstance().prepareStatement("DELETE FROM Users WHERE Email = ? AND Passwort = ?;");
    }

    /**
     * Gibt ein Objekt mit den &uuml;bergebenen Informationen zur&uuml;ck oder null, wenn
     * es das Objekt nicht in der Datenbank gibt
     *
     * @param email    saved E-Mail Address of the User
     * @param passwort saved Passwort of the User
     * @return Objekt mit den Informationen aus der Datenbank oder null
     */
    public static UserEintrag get(String email, String passwort) {
        UserEintrag result = null;

        try {
            pstmtSelectwithUid.setString(1, email);
            pstmtSelectwithUid.setString(2, passwort);

            ResultSet rs = pstmtSelectwithUid.executeQuery();

            if (rs.next()) {
                result.setId(rs.getInt(1));
                result.setEmail(rs.getString(2));
                result.setPasswort(rs.getString(3));
            }

            rs.close();

        } catch (SQLException ignored) {
        }

        return result;
    }

    /**
     * Pr&uuml;ft nach, ob ein User in der Datenbank vorhanden ist
     *
     * @param email    Erhaltene E-Mail Addresse
     * @param passwort Erhaltenes Passwort
     * @return Gibt true zur&uuml;ck, wenn ein User gefunden wurde
     */
    public static Boolean isUser(String email, String passwort) {

        try {
            pstmtSelect.setString(1, email);
            pstmtSelect.setString(2, passwort);

            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                return true;
            }

            rs.close();

        } catch (SQLException ignored) {
        }

        return false;
    }

    /**
     * This method check, if a E-mail Address is already in the database
     *
     * @param email The Email of a User
     * @return true, if the E-Mail is used
     */
    public static Boolean usedEmail(String email) {

        try {
            pstmtSelectEmail.setString(1, email);
            ResultSet rs = pstmtSelectEmail.executeQuery();
            if (rs.next()) {
                return true;
            }

            rs.close();

        } catch (SQLException ignored) {
        }
        return false;
    }


    /**
     * Gibt eine Liste mit allen in der Datenbank vorhandennen Objekten zurück.
     *
     * @return Liste mit allen Objekten aus der Datenbank oder null
     */
    public static ArrayList<UserEintrag> getAll() {
        ArrayList<UserEintrag> result = null;

        try {
            ResultSet rs = pstmtSelectAll.executeQuery();
            result = new ArrayList<>();

            while (rs.next()) {
                UserEintrag eintrag = new UserEintrag();
                eintrag.setEmail(rs.getString(1));
                eintrag.setPasswort(rs.getString(2));

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
    public static boolean insert(UserEintrag zuSpeichern) {
        boolean result = false;

        try {
            pstmtInsert.setString(1, zuSpeichern.getEmail());
            pstmtInsert.setString(2, zuSpeichern.getPasswort());

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
     * Ersetz das zweite übergebene Objekt mit dem ersten übergebenen Objekt in der Datenbank
     * und gibt zurück, ob der Vorgang erfolgreich war.
     *
     * @param neu Das in die Datenbank zu schreibende Objekt
     * @param alt Das in die Datenbank zu ersetzende Objekt
     * @return true im Erfolgsfall, false andernfalls
     */
    public static boolean update(UserEintrag neu, UserEintrag alt) {
        boolean result = false;

        try {
            pstmtUpdate.setString(1, neu.getEmail());
            pstmtUpdate.setString(2, neu.getPasswort());
            pstmtUpdate.setString(3, alt.getEmail());
            pstmtUpdate.setString(4, alt.getPasswort());

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

    /**
     * Löscht das übergebene Objekt aus der Datenbank und gibt zurück, ob der Vorgang erfolgreich war.
     *
     * @param zuLoeschen Das aus der Datenbank zu löschende Objekt
     * @return true im Erfolgsfall, false andernfalls
     */
    public static boolean delete(UserEintrag zuLoeschen) {
        boolean result = false;

        try {
            pstmtDelete.setString(1, zuLoeschen.getEmail());
            pstmtDelete.setString(2, zuLoeschen.getPasswort());

            int rows = pstmtDelete.executeUpdate();

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
