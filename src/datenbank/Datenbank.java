package datenbank;

import datenbank.connection.SQLiteDatenbankverbindung;

import java.sql.SQLException;

/**
 * Klasse zur Steuerung der Datenbankvorgänge. Definiert als Singelton:
 * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
 */
public class Datenbank extends SQLiteDatenbankverbindung {

    private static Datenbank db;
    private final static String DBFILE = "smt_db.sqlite";

    /**
     * private Konstruktor
     */
    private Datenbank() {
        super(DBFILE);
    }

    /**
     * Klassenmethode, die die Instanz der Datenbankklasse zurückgibt (Fabrikmethode genannt)
     *
     * @return Instanz der Datenbank-Klasse
     */
    public static Datenbank getInstance() {
        if (db == null) {
            // Neue Instanz von Datenbank erzeugen
            db = new Datenbank();
            db.init();
        }

        return db;
    }

    /**
     * Diese Methode soll die erforderliche Datenstruktur der Datenbank herstellen
     */
    private void init() {
        try {
            execute("CREATE TABLE IF NOT EXISTS \"Users\" (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "Email VARCHAR(100) NOT NULL, " +
                    "Passwort VARCHAR(100) NOT NULL" +
                    ");");

            // execute("CREATE TABLE IF NOT EXISTS \"TwitterAccount\" ();");

            // execute("CREATE TABLE IF NOT EXISTS \"FacebookAccount\" ();");

            // execute("CREATE TABLE IF NOT EXISTS \"SocialmediaAccounts\" ();");

            // execute("CREATE TABLE IF NOT EXISTS \"SocialmediaPosts\" ();");

            // execute("CREATE TABLE IF NOT EXISTS \"Hashtags\" ();");


            this.commit();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Diese Methode pr&uuml;ft, ob eine Datenbankverbindung da ist
     *
     * @return Status der Datenbank
     */
    public static Boolean isConnected() {
        if (db != null) {
            return true;
        }
        return false;
    }
}
