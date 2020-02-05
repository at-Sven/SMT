package datenbank.beans;

import datenbank.Datenbank;
import model.HashtagsEintrag;
import model.UserEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        pstmtSelectThemes = Datenbank.getInstance().prepareStatement("SELECT theme, hashtags FROM Hashtags;");
        pstmtInsert = Datenbank.getInstance().prepareStatement("INSERT INTO Hashtags (theme, hashtags) VALUES (?, ?);");
        pstmtUpdate = Datenbank.getInstance().prepareStatement("UPDATE Hashtags SET theme = ?, hashtags = ? WHERE theme = ? AND hashtags = ?;");
        pstmtDelete = Datenbank.getInstance().prepareStatement("DELETE FROM Hashtags WHERE theme = ? AND hashtags = ?;");
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
                eintrag.setTheme(rs.getString(1));
                eintrag.setHashtags(rs.getString(2));

                result.add(eintrag);
            }

            rs.close();

        } catch (SQLException ignored) {}

        return result;
    }

    // TODO: Restliche Methoden einfügen

}
