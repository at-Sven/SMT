package datenbank.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatenbankverbindung extends AllgemeineDatenbankverbindung {

    private final String DBFILE;

    /**
     * Konstruktor, der die übergebene Datenbankdatei auf einen gültigen Wert überprüft.
     * Sollte dies nicht der Fall sein, wird eine IllegalArgumentException geworfen
     *
     * @param datenbankdatei SQLite-Datenbankdatei zu der eine Verbindung aufgebaut werden soll. Die Datenbank muss NICHT existieren - kann aber.
     * @throws IllegalArgumentException wird geworfen, wenn der übergebene Parameter null oder leer ist.
     */
    public SQLiteDatenbankverbindung(String datenbankdatei) {
        if (datenbankdatei == null || datenbankdatei.isEmpty()) {
            throw new IllegalArgumentException("Keine gültige Datenbankdatei angegeben!");
        }

        this.DBFILE = datenbankdatei;

        connect();
    }

    /**
     * Diese Methode dient der Erstellung der Verbindung zur Datenbank. Diese MUSS das
     * Connection-Objekt 'con' korrekt setzen.
     * <p>
     * Empfehlung: AutoCommit auf false setzen
     *
     * @return true bei erfolgreicher Verbindung zur Datenbank, false andernfalls.
     */
    @Override
    public boolean connect() {
        if (con == null) {
            // Es existiert noch keine Verbindung. Diese muss nun aufgebaut werden.

            try {
                // Treiber laden
                Class.forName("org.sqlite.JDBC");

                // Datenbankverbindung aufbauen
                con = DriverManager.getConnection("jdbc:sqlite:" + DBFILE);

                // AutoCommit auf false setzen
                con.setAutoCommit(false);

            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Der JDBC-Treiber für SQLite konnte nicht gefunden werden!");
            } catch (SQLException e) {
                throw new IllegalArgumentException("Problem beim Zugriff auf die Datenbankdatei '" + DBFILE + "'. Bitte Zugriffsrechte oder gleichzeitige Benutzung durch anderes Programm prüfen!");
            }
        }
        return true;
    }
}
