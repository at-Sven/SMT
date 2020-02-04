package datenbank.beans;

import datenbank.Datenbank;
import model.SocialmediaAccount;
import model.UserEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Die Bean KLasse für die SocialmediaAccounts Eintraege in der Datenbank
 */
public class SocialmediaAccountBean {

    // PreparedStatements
    private static PreparedStatement pstmtInsertTwitterAccount;
    private static PreparedStatement pstmtInsertFacebookAccount;

    private static PreparedStatement pstmtUpdateTwitterAccount;
    private static PreparedStatement pstmtUpdateFacebookAccount;

    private static PreparedStatement pstmtSelectWithUid;
    private static PreparedStatement pstmtSelectAll;


    static {

        pstmtInsertTwitterAccount = Datenbank.getInstance().prepareStatement("INSERT INTO SocialmediaAccounts ( " +
                "uid, " +
                "twConsumerKey, " +
                "twConsumerSecret, " +
                "twAccessToken, " +
                "twAccessTokenSecret)" +
                "VALUES (?, ?, ?, ?, ?);");

        pstmtInsertFacebookAccount = Datenbank.getInstance().prepareStatement("INSERT INTO SocialmediaAccounts ( " +
                "uid, " +
                "fbAppID, " +
                "fbAppSecret," +
                "fbAccessToken, " +
                "fbUserdata, " +
                "fbAccessTokenExpireDate) " +
                "VALUES (?, ?, ?, ?, ?, ? );");

        pstmtUpdateTwitterAccount = Datenbank.getInstance().prepareStatement("UPDATE SocialmediaAccounts SET " +
                "twConsumerKey = ?, " +
                "twConsumerSecret = ?, " +
                "twAccessToken = ?, " +
                "twAccessTokenSecret = ? " +
                "WHERE uid = ?;");

        pstmtUpdateFacebookAccount = Datenbank.getInstance().prepareStatement("UPDATE SocialmediaAccounts SET " +
                "fbAppID = ?, " +
                "fbAppSecret = ?, " +
                "fbAccessToken = ?, " +
                "fbUserdata = ?, " +
                "fbAccessTokenExpireDate = ? " +
                "WHERE uid = ?;");

        pstmtSelectWithUid = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaAccounts WHERE uid = ?;");
        pstmtSelectAll = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaAccounts;");

    }


    /**
     * Fügt das übergebene Objekt in die Datenbank ein und gibt zurück, ob der Vorgang erfolgreich war.
     *
     * @param uid userid
     * @param ck twitter consumerkey
     * @param cs twitter consumersecret
     * @param at twitter accesstoken
     * @param ats  twitter accesstokensecret
     * @return true im Erfolgsfall, false andernfalls
     */

    public static boolean insertOrUpdateTwitterAccount(int uid, String ck, String cs, String at, String ats ) {
    // public static boolean insertOrUpdateTwitterAccount(int uid, SocialmediaAccount zuSpeichern) {
        boolean result = false;
        int rows;
        try {

            // check if a row for uid already exists:
            if (getSocialMediaAccountsByUid(uid) == null) {  // insert if no row found in table

                pstmtInsertTwitterAccount.setInt(1, uid);
                pstmtInsertTwitterAccount.setString(2, ck);
                pstmtInsertTwitterAccount.setString(3, cs);
                pstmtInsertTwitterAccount.setString(4, at);
                pstmtInsertTwitterAccount.setString(5, ats);

                rows = pstmtInsertTwitterAccount.executeUpdate();

            }else{ // update if row with uid already exists

                pstmtUpdateTwitterAccount.setString(1, ck);
                pstmtUpdateTwitterAccount.setString(2, cs);
                pstmtUpdateTwitterAccount.setString(3, at);
                pstmtUpdateTwitterAccount.setString(4, ats);
                pstmtUpdateTwitterAccount.setInt(5, uid);

                rows = pstmtUpdateTwitterAccount.executeUpdate();
            }
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
     * Gibt ein Objekt mit den &uuml;bergebenen Informationen zur&uuml;ck oder null, wenn
     * es das Objekt nicht in der Datenbank gibt
     *
     * @param uid    userid from logged in user
     * @return Objekt mit den Informationen aus der Datenbank oder null
     */
    public static SocialmediaAccount getSocialMediaAccountsByUid(int uid) {
        SocialmediaAccount result = null;

        try {
            pstmtSelectWithUid.setInt(1, uid);

            ResultSet rs = pstmtSelectWithUid.executeQuery();

            if (rs.next()) {
                result = new SocialmediaAccount();
                result.setUid(rs.getInt(2));  // 2. field is uid!
                result.setTwConsumerKey(rs.getString(3));
                result.setTwConsumerSecret(rs.getString(4));
                result.setTwAccessToken(rs.getString(5));
                result.setTwAccessTokenSecret(rs.getString(6));
                result.setFbAppID(rs.getString(7));
                result.setFbAppSecret(rs.getString(8));
                result.setFbAccessToken(rs.getString(9));
                result.setFbUserdata(rs.getString(10));
                result.setFbAccessTokenExpireDate(rs.getInt(11));
            }

            rs.close();

        } catch (SQLException ignored) {
        }

        return result;
    }

}



/* socialmediaaccoutns table Structur zur Info, später hier rauslöschen:
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

 */