package datenbank.beans;

import datenbank.Datenbank;
import model.SocialmediaAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Die Bean KLasse für die SocialmediaAccounts Tabelle in der Datenbank
 */
public class SocialmediaAccountBean {

    // PreparedStatements
    private static PreparedStatement pstmtSelect;
    private static PreparedStatement pstmtSelectAll;
    private static PreparedStatement pstmtInsert;
    private static PreparedStatement pstmtUpdate;
    private static PreparedStatement pstmtDelete;

    static {
        pstmtSelect = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaAccounts WHERE uid = ?;");
        pstmtSelectAll = Datenbank.getInstance().prepareStatement("SELECT * FROM SocialmediaAccounts;");
        pstmtInsert = Datenbank.getInstance().prepareStatement("INSERT INTO SocialmediaAccounts (Email, Passwort) VALUES (?, ?);");
        pstmtUpdate = Datenbank.getInstance().prepareStatement("UPDATE Users SET Email = ?, Passwort = ? WHERE email = ? AND Passwort = ?;");
        pstmtDelete = Datenbank.getInstance().prepareStatement("DELETE FROM Users WHERE Email = ? AND Passwort = ?;");
    }

}
