package datenbank.connection;

import java.sql.*;
import java.util.ArrayList;

public abstract class AllgemeineDatenbankverbindung {

    /**
     * Connection-Objekt, welches die Datenbankverbindung enthält
     */
    protected Connection con;

    /**
     * Liste aller erzeugten PreparedStatements
     */
    private ArrayList<PreparedStatement> pstmtList;

    /**
     * Konstruktor
     * Initialisiert die interne Liste der PreparedStatements
     */
    public AllgemeineDatenbankverbindung() {
        pstmtList = new ArrayList<>();
    }

    /**
     * Diese Methode dient der Erstellung der Verbindung zur Datenbank. Diese MUSS das
     * Connection-Objekt 'con' korrekt setzen.
     * <p>
     * Empfehlung: AutoCommit auf false setzen
     *
     * @return true bei erfolgreicher Verbindung zur Datenbank, false andernfalls.
     */
    public abstract boolean connect();

    /**
     * Trennt die Verbindung zur Datenbank.
     */
    public void disconnect() {
        if (con != null) {

            // Schließen aller PreparedStatements
            for (PreparedStatement pstmt : pstmtList) {
                try {
                    pstmt.close();

                    /* Sollte das Statement schon geschlossen sein, wird eine
                     * Fehlermeldung geworfen. Diese wird ignoriert und mit dem
                     * nächsten weitergemacht
                     */
                } catch (SQLException ignored) {
                }
            }

            // Löschen aller Referenzen zu den PreparedStatements
            pstmtList.clear();

            // Datenbankverbindung trennen
            try {
                con.close();
            } catch (SQLException ignored) {
            } finally {
                con = null;
            }
        }
    }

    /**
     * Führt ein Commit aus. Alle noch offenen Transaktionen werden in die Datenbank geschrieben.
     *
     * @throws SQLException wird geworfen, wenn ein Fehler beim Commit auftritt oder keine korrekte Verbindung zur Datenbank besteht
     */
    public void commit() throws SQLException {
        if (con != null) {
            con.commit();
        }
    }

    /**
     * Führt ein Rollback aus. Alle noch offenen Transaktionen werden rückgängig gemacht.
     *
     * @throws SQLException wird geworfen, wenn ein Fehler beim Rollback auftritt oder keine korrekte Verbindung zur Datenbank besteht
     */
    public void rollback() throws SQLException {
        if (con != null) {
            con.rollback();
        }
    }

    /**
     * Führt ein beliebiges SQL-Statement aus, und gibt etwaiige Fehler auf der Fehlerausgabe aus
     *
     * @param sql SQL-Statement welches ausgeführt werden soll
     * @return gibt true zurück, wenn der Befehl ohne Fehler ausgeführt wurde, und false im Fehlerfall
     */
    public boolean execute(String sql) {
        if (con == null) {
            System.err.println("Datenbankverbindung geschlossen!");
            return false;
        } else {
            try {
                Statement stmt = con.createStatement();
                stmt.execute(sql);
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Ausführen des folgenden Statements: " + sql + "\nFehlermeldung: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Führt ein beliebiges SELECT-SQL-Statement aus und gibt das ResultSet zurück
     *
     * @param sql SQL-Statement welches ausgeführt werden soll
     * @return ResultSet des Ergebnisses des SELECT-Statements
     * @throws IllegalArgumentException wird geworfen, wenn das Statement nicht korrekt ausgeführt werden konnte
     */
    public ResultSet executeQuery(String sql) {
        if (con == null) {
            throw new IllegalArgumentException("Datenbankverbindung geschlossen!");
        } else {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                return rs;
            } catch (SQLException e) {
                throw new IllegalArgumentException("Fehler beim Ausführen des folgenden Statements: " + sql + "\nFehlermeldung: " + e.getMessage());
            }
        }
    }

    /**
     * Diese Methode bereitet den übergebenen SQL-Code als PreparedStatement vor und gibt das erzeugte Objekt zurück
     *
     * @param sql SQL-Statement
     * @return PreparedStatementzu dem übergebenen SQL-Code
     * @throws IllegalArgumentException wird geworfen, wenn das Statement nicht korrekt ausgeführt werden konnte
     */
    public PreparedStatement prepareStatement(String sql) {
        if (con == null) {
            throw new IllegalArgumentException("Datenbankverbindung geschlossen!");
        } else {
            try {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmtList.add(pstmt);
                return pstmt;
            } catch (SQLException e) {
                throw new IllegalArgumentException("Fehler beim Ausführen des folgenden Statements: " + sql + "\nFehlermeldung: " + e.getMessage());
            }
        }
    }
}