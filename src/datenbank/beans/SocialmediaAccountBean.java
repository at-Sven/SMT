package datenbank.beans;

import datenbank.Datenbank;
import model.SocialmediaAccount;
import model.UserEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Die Bean KLasse für die SocialmediaAccounts Tabelle in der Datenbank
 */
public class SocialmediaAccountBean {

    // PreparedStatements
    private static PreparedStatement pstmtInsertTwitterAccount;
    private static PreparedStatement pstmtInsertFacebookAccount;

    private static PreparedStatement pstmtUpdateTwitterAccount;
    private static PreparedStatement pstmtUpdateFacebookAccount;

    private static PreparedStatement pstmtSelectWithUid;
    private static PreparedStatement pstmtSelectAccountWithUidAndPlatform;
    private static PreparedStatement pstmtSelectAll;


    static {

        pstmtInsertTwitterAccount = Datenbank.getInstance().prepareStatement("INSERT INTO SocialmediaAccounts ( uid, " +
                "platform, " + // platform must be 1 for twitter
                "twConsumerKey, " +
                "twConsumerSecret, " +
                "twAccessToken, " +
                "twAccessTokenSecret)" +
                "VALUES (?, ?, ?, ?, ?, ?);");

        pstmtInsertFacebookAccount = Datenbank.getInstance().prepareStatement("INSERT INTO SocialmediaAccounts ( uid, " +
                "platform, " + // platform must be 2 for Facebook
                "fbAppID, " +
                "fbAppSecret) " +
                "VALUES (?, ?, ?, ?);");

        pstmtUpdateTwitterAccount = Datenbank.getInstance().prepareStatement("UPDATE SocialmediaAccounts SET twConsumerKey = ?, " +
                "twConsumerSecret = ?, " +
                "twAccessToken = ?, " +
                "twAccessTokenSecret = ? " +
                "WHERE uid = ? AND platform = ?;"); // platform must be 1 for twitter

        pstmtUpdateFacebookAccount = Datenbank.getInstance().prepareStatement("UPDATE SocialmediaAccounts SET fbAppID = ?, " +
                "fbAppSecret = ? " +
                "WHERE uid = ? AND platform = ?;"); // platform must be 2 for Facebook

        pstmtSelectWithUid = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaAccounts WHERE uid = ?;");
        pstmtSelectAccountWithUidAndPlatform = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaAccounts WHERE uid = ? AND platform = ?;"); // platform 1 = twitter, 2 = facebook
        pstmtSelectAll = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaAccounts;");

    }
}

    /**
     * Fügt das übergebene Objekt in die Datenbank ein und gibt zurück, ob der Vorgang erfolgreich war.
     *
     * @param zuSpeichern Das in die Datenbank zu schreibende Objekt
     * @return true im Erfolgsfall, false andernfalls
     */

    /*
    public static boolean insertTwitterAccount(int uid, SocialmediaAccount zuSpeichern) {
        boolean result = false;

        try {

            // check if a row for uid already exists:
            if(getSocialMediaAccountsByUid(uid) == null){

            pstmtInsertTwitterAccount.setInt(1, uid);
            pstmtInsertTwitterAccount.setInt(2, 0 ); // not needed, saves 0
            pstmtInsertTwitterAccount.setString(3, zuSpeichern.getTwConsumerKey() );
            pstmtInsertTwitterAccount.setString(4, zuSpeichern.getTwConsumerSecret() );
            pstmtInsertTwitterAccount.setString(5, zuSpeichern.getTwAccessToken() );
            pstmtInsertTwitterAccount.setString(6, zuSpeichern.getTwAccessTokenSecret() );

            int rows = pstmtInsertTwitterAccount.executeUpdate();

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

*/
    /**
     * Gibt ein Objekt mit den &uuml;bergebenen Informationen zur&uuml;ck oder null, wenn
     * es das Objekt nicht in der Datenbank gibt
     *
     * @param uid    userid from logged in user
     * @return Objekt mit den Informationen aus der Datenbank oder null
     */

  /*
    public static SocialmediaAccount getSocialMediaAccountsByUid(int uid) {
        SocialmediaAccount result = null;

        try {
            pstmtSelectWithUid.setInt(1, uid);

            ResultSet rs = pstmtSelectWithUid.executeQuery();

            if (rs.next()) {
                result = new SocialmediaAccount();
                result.setId(rs.getInt(1));
                // result.setPlatform(rs.getInt(2)); // no need, not used
                result.setTwConsumerKey(rs.getString(3));
                result.setTwConsumerSecret(rs.getString(4));
                result.setTwAccessToken(rs.getString(5));
                result.setTwAccessTokenSecret(rs.getString(6));
                result.setFbAppID(rs.getString(7));
                result.setFbAppSecret(rs.getString(8));
            }

            rs.close();

        } catch (SQLException ignored) {
        }

        return result;
    }

}
*/

/* table Structur:
"sid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
        "uid INTEGER NOT NULL, " +
        "platform INTEGER NOT NULL, " +        // 1 = twitter, 2 = facebook (where to post helper var)
        "twConsumerKey VARCHAR(200), " +
        "twConsumerSecret VARCHAR(200), " +
        "twAccessToken VARCHAR(200), " +
        "twAccessTokenSecret VARCHAR(200)," +
        "fbAppID VARCHAR(200), " +            // facebook AppID
        "fbAppSecret VARCHAR(200), " +        // facebook AppSecret
        "soc0 VARCHAR(200), " +               // temp feld, falls andere noch benoetigt werden sollten
        "soc1 VARCHAR(200), " +               // temp feld, falls andere noch benoetigt werden sollten
        "soc2 VARCHAR(200), " +               // temp feld, falls andere noch benoetigt werden sollten
        "soc3 VARCHAR(200), " +               // temp feld, falls andere noch benoetigt werden sollten

 */