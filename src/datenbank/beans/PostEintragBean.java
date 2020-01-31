package datenbank.beans;

import model.PostEintrag;
import datenbank.Datenbank;
import model.PostEintrag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PostEintragBean {

    private static PreparedStatement pstmtSelect;
    private static PreparedStatement pstmtInsertUser;
    private static PreparedStatement pstmtInsertVorname;
    private static PreparedStatement pstmtInsertNachname;
    private static PreparedStatement pstmtInsertStrasse;
    private static PreparedStatement pstmtInsertHausnr;
    private static PreparedStatement pstmtInsertPlz;
    private static PreparedStatement pstmtInsertOrt;
    private static PreparedStatement pstmtInsert;
    private static PreparedStatement pstmtUpdate;
    private static PreparedStatement pstmtDelete;

    private static HashMap<PostEintrag, String> idListe;

    /**
     * Initialisierungsblock
     * Wird ausgeführt wenn die Klasse erzeugt wird
     */
    static {
        System.out.println("static-Block ausgeführt");

        // Statements vorbereiten
        pstmtSelect = Datenbank.getInstance().prepareStatement("SELECT email, passwort, FROM table_users;");
        pstmtInsertUser = Datenbank.getInstance().prepareStatement("INSERT INTO table_users (uid, email, passwort) VALUE(?,?,?)");


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
/*        pstmtUpdate = Datenbank.getInstance().prepareStatement("UPDATE table_telefonbuch SET " +
                                                                        "vorname_id = (SELECT id FROM table_vorname WHERE vorname = ?), " +
                                                                        "nachname_id = (SELECT id FROM table_nachname WHERE nachname = ?), " +
                                                                        "strasse_id = (SELECT id FROM table_strasse WHERE strasse = ?), " +
                                                                        "hausnr_id = (SELECT id FROM table_hausnr WHERE hausnr = ?), " +
                                                                        "plz_id = (SELECT id FROM table_plz WHERE plz = ?), " +
                                                                        "ort_id = (SELECT id FROM table_ort WHERE ort = ?), " +
                                                                        "telefonnr = ? WHERE telefonnr = ?;");
        pstmtDelete = Datenbank.getInstance().prepareStatement("DELETE FROM table_telefonbuch WHERE telefonnr = ?;");*/

        idListe = new HashMap<>();
    }

    /**
     * Lädt das gesamte Telefonbuch aus der Datenbank und gibt es alls Liste von TelefonbuchEinträgen zurück
     *
     * @return Liste mit allen TelefonbuchEinträgen
     * @throws IllegalArgumentException wird geworfen, wenn intern eine SQL- oder ClassNotFoundException aufgetreten ist.
     */
    public static ArrayList<PostEintrag> getPosts() {
        return null;
    }

    /**
     * Speichert einen übergebenen TelefonbuchEintrag in der Datenbank. Ob der Eintrag
     * im Telefonbuch schon vorhanden ist oder nicht, also ob ein update oder ein
     * insert-Befehl für die Datenbank ausgeführt werden muss, ist für den Aufruf von der GUI
     * irrelevant. Dies findet diese Methode heraus.
     *
     * @param zuSpeichern TelefonbuchEintrag, der gespeichert werden soll
     * @return true, wenn die Speicherung erfolgreich war, false andernfalls
     */
    public static boolean save(PostEintrag zuSpeichern) {
        return true;
    }

    /**
     * Löscht einen übergebenen TelefonbuchEintrag aus der Datenbank
     *
     * @param zuLoeschen TelefonbuchEintrag, der gelöscht werden soll
     * @return true, wenn das Löschen erfolgreich war, false andernfalls
     */
    public static boolean delete(PostEintrag zuLoeschen) {
        return true;
    }
}
