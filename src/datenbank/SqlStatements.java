package datenbank;

public class SqlStatements {

    // Aktuelle Version
    public final static int VERSION = 1;

    // Befehle zum Setzen und Auslesen der Version
    public final static String GET_VERSION = "PRAGMA USER_VERSION;";
    public final static String SET_VERSION = "PRAGMA USER_VERSION = ";

    // +-------------------------------------------------------------------------------------------------------------------------+
    // |                                                  VERSION 1																 |
    // +-------------------------------------------------------------------------------------------------------------------------+

    // Erstellen der Tabellen

    public static final String CREATE_TABLE_USERS = "CREATE TABLE \"table_users\" ( "
            + "uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + "email VARCHAR(100) NOT NULL, "
            + "passwort VARCHAR(100) NOT NULL, "
            + ");";

    public static final String CREATE_TABLE_SOCIALMEDIAACCOUNTS = "CREATE TABLE \"table_Socialmediaaccounts\" ( "
            + "sid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + "username VARCHAR(100) NOT NULL, "
            + "passwort VARCHAR(100) NOT NULL, "
            + "passwort VARCHAR(100) NOT NULL, "
            // TODO: Was kommt noch rein?
            + ");";

    public static final String CREATE_TABLE_TWITTERACCOUNTS = "CREATE TABLE \"table_users\" ( "
            + "tid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + "user INTEGER NOT NULL, "
            + "consumerKey VARCHAR(200) NOT NULL, "
            + "consumerSecret VARCHAR(200) NOT NULL, "
            + "accessToken VARCHAR(200) NOT NULL, "
            + "accessTokenSecret VARCHAR(200) NOT NULL, "
            + ");";

    //TODO: Hier weiter dann

    /*      public static final String CREATE_TABLE_POSTS = "CREATE TABLE \"table_telefonbuch\" ( "
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + "vorname_id INTEGER NOT NULL, "
            + "nachname_id INTEGER NOT NULL, "
            + "strasse_id INTEGER NOT NULL, "
            + "hausnr_id INTEGER NOT NULL, "
            + "plz_id INTEGER NOT NULL, "
            + "ort_id INTEGER NOT NULL, "
            + "telefonnr VARCHAR(30) NOT NULL UNIQUE,"
            + "FOREIGN KEY (vorname_id) REFERENCES vorname(id), "
            + "FOREIGN KEY (nachname_id) REFERENCES nachname(id), "
            + "FOREIGN KEY (strasse_id) REFERENCES strasse(id), "
            + "FOREIGN KEY (hausnr_id) REFERENCES hausnr(id), "
            + "FOREIGN KEY (plz_id) REFERENCES plz(id), "
            + "FOREIGN KEY (ort_id) REFERENCES ort(id)"
            + ");";

 */
  //  public static final String ACTIVATE_FOREIGN_KEYS = "PRAGMA foreign_keys = ON;";

    // Umkopieren der in der Tabelle telefonbuch vorhandenen Datensätze in die Basistabellen
/*
    public static final String INSERT_VORNAME = "INSERT INTO table_vorname (vorname) SELECT DISTINCT vorname FROM telefonbuch;";
    public static final String INSERT_NACHNAME = "INSERT INTO table_nachname (nachname) SELECT DISTINCT nachname FROM telefonbuch;";
    public static final String INSERT_STRASSE = "INSERT INTO table_strasse (strasse) SELECT DISTINCT strasse FROM telefonbuch;";
    public static final String INSERT_HAUSNR = "INSERT INTO table_hausnr (hausnr) SELECT DISTINCT hausnr FROM telefonbuch;";
    public static final String INSERT_PLZ = "INSERT INTO table_plz (plz) SELECT DISTINCT plz FROM telefonbuch;";
    public static final String INSERT_ORT = "INSERT INTO table_ort (ort) SELECT DISTINCT ort FROM telefonbuch;";
    */

    // Umkopieren der in der Tabelle telefonbuch vorhandenen Datensätze in die Tabelle table_telefonbuch unter
    // Berücksichtigung der ids aus den Basistabellen
/*
 public static final String INSERT_TELFONBUCH = "INSERT INTO table_telefonbuch (vorname_id, nachname_id, strasse_id, hausnr_id, plz_id, ort_id, telefonnr) " +
            "SELECT v.id, n.id, s.id, h.id, p.id, o.id, t.telefonnr " +
            "FROM telefonbuch t " +
            "INNER JOIN table_vorname v ON t.vorname = v.vorname " +
            "INNER JOIN table_nachname n ON t.nachname = n.nachname " +
            "INNER JOIN table_strasse s ON t.strasse = s.strasse " +
            "INNER JOIN table_hausnr h ON t.hausnr = h.hausnr " +
            "INNER JOIN table_plz p ON t.plz = p.plz " +
            "INNER JOIN table_ort o ON t.ort = o.ort;";
            */

    // Löschen der alten Telefonbuchtabelle
   // static final String DROP_TELEFONBUCH = "DROP TABLE telefonbuch;";

    // Erstellen der Sicht
    /*
    static final String CREATE_VIEW_TELEFONBUCH = "CREATE VIEW telefonbuch AS "
            + "SELECT v.vorname AS Vorname, "
            + "n.nachname AS Nachname, "
            + "s.strasse AS Straße, "
            + "h.hausnr AS HausNr, "
            + "p.plz AS PLZ, "
            + "o.ort AS Ort, "
            + "t.telefonnr AS TelefonNr "
            + " FROM table_telefonbuch t "
            + "INNER JOIN table_vorname v ON t.vorname_id = v.id "
            + "INNER JOIN table_nachname n ON t.nachname_id = n.id "
            + "INNER JOIN table_strasse s ON t.strasse_id = s.id "
            + "INNER JOIN table_hausnr h ON t.hausnr_id = h.id "
            + "INNER JOIN table_plz p ON t.plz_id = p.id "
            + "INNER JOIN table_ort o ON t.ort_id = o.id;";
     */
}
