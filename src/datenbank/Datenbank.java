package datenbank;


import com.sun.media.jfxmediaimpl.platform.Platform;
import datenbank.connection.SQLiteDatenbankverbindung;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasse zur Steuerung der Datenbankvorgänge. Definiert als Singelton:
 * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
 */
public class Datenbank extends SQLiteDatenbankverbindung {

    private static Datenbank db;
    private final static String DBFILE = "sml-db.sqlite";

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
            ResultSet rs = executeQuery(SqlStatements.GET_VERSION);

            int aktuelleDBVersion;
            if (rs.next()) {
                aktuelleDBVersion = rs.getInt(1);
            } else {
                aktuelleDBVersion = 0;
            }
            rs.close();

            upgrade(aktuelleDBVersion);

        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Fehler aufgetreten: " + e.getLocalizedMessage());
            System.err.println("Programm wird geschlossen!");
            System.exit(0);
        }
    }

    /**
     * Upgrade-Funktion der Datenbank, um bei einer neuen Programmversion gleich auch die Datenbank
     * zu migrieren.
     * @throws SQLException wird geworfen bei einem Fehler im Commit oder Rollback
     */
    private void upgrade(int oldDBVersion) throws SQLException {
        if (oldDBVersion == SqlStatements.VERSION) {
            System.out.println("Die Datenbank ist bereits aktuell.");
            return;
        } else if (oldDBVersion > SqlStatements.VERSION) {
            throw new IllegalArgumentException("Die Datenbank ist neuer als die Version des Programms.\nProgramm wird beendet.");
        } else {
            System.out.println("Die Datenbank wird von Version " + oldDBVersion + " auf die Version " + SqlStatements.VERSION + " hochgestuft.");
        }

        boolean result = true;

        switch(oldDBVersion) {
            case 0: {
                // Befehle für Upgrade auf Version 1...
                result = execute(SqlStatements.CREATE_TABLE_USERS);

                System.out.println("Upgrade auf DB-Version 1 durchgeführt.");
                // KEIN break!!!! Wir wollen, dass die Datenbank immer auf die aktuelle Version upgedatet wird.
            }
            case 1: { //TODO: HIER WEITER
                // Befehle für Upgrade auf Version 1
/*                result = result && execute(SqlStatements.CREATE_TABLE_VORNAME);
                result = result && execute(SqlStatements.CREATE_TABLE_NACHNAME);
                result = result && execute(SqlStatements.CREATE_TABLE_STRASSE);
                result = result && execute(SqlStatements.CREATE_TABLE_HAUSNR);
                result = result && execute(SqlStatements.CREATE_TABLE_PLZ);
                result = result && execute(SqlStatements.CREATE_TABLE_ORT);
                result = result && execute(SqlStatements.CREATE_TABLE_TELEFONBUCH);
                result = result && execute(SqlStatements.ACTIVATE_FOREIGN_KEYS);

                result = result && execute(SqlStatements.INSERT_VORNAME);
                result = result && execute(SqlStatements.INSERT_NACHNAME);
                result = result && execute(SqlStatements.INSERT_STRASSE);
                result = result && execute(SqlStatements.INSERT_HAUSNR);
                result = result && execute(SqlStatements.INSERT_PLZ);
                result = result && execute(SqlStatements.INSERT_ORT);
                result = result && execute(SqlStatements.INSERT_TELFONBUCH);

                result = result && execute(SqlStatements.DROP_TELEFONBUCH);

                result = result && execute(SqlStatements.CREATE_VIEW_POSTS);
                */

                System.out.println("Upgrade auf DB-Version 2 durchgeführt.");
                // KEIN break!!!! Wir wollen, dass die Datenbank immer auf die aktuelle Version upgedatet wird.
            }
            case 2: {
                // Befehle für Upgrade auf Version 3...

                // System.out.println("Upgrade auf DB-Version 3 durchgeführt.");
                // KEIN break!!!! Wir wollen, dass die Datenbank immer auf die aktuelle Version upgedatet wird.
            }
        }

        // Neue Datenbankversion setzen
        result = result && execute(SqlStatements.SET_VERSION + SqlStatements.VERSION + ";");

        // Wenn alles erfolgreich war und es keine Exception oder ein false gab,
        // wird ein commit ausgeführt
        if (result) {
            commit();
        } else {
            rollback();
        }
    }
}
