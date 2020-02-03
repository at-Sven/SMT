package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class for the User of the Program
 */
public class UserEintrag {

    private Integer id;
    private final StringProperty email;
    private final StringProperty passwort;

    /**
     * Constructor to create a new User
     *
     * @param email    The E-Mail Address of the User
     * @param passwort The Password of the User
     */
    public UserEintrag(Integer id, String email, String passwort) {
        this.id = null;
        this.email = new SimpleStringProperty(email);
        this.passwort = new SimpleStringProperty(passwort);
    }

    /**
     * Constructor to create a User in the Database
     */
    public UserEintrag() {
        this.id = null;
        this.email = new SimpleStringProperty("");
        this.passwort = new SimpleStringProperty("");
    }

    /**
     * Getter method for the User E-Mail Address
     *
     * @return The E-Mail Address of the User
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * TODO
     *
     * @return The E-Mail Address of the User
     */
    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Getter method for the User Password
     *
     * @return The Password of the User
     */
    public String getPasswort() {
        return passwort.get();
    }

    /**
     * TODO
     *
     * @return the Password of the User
     */
    public StringProperty passwortProperty() {
        return passwort;
    }

    /**
     * Setter method for the User Password
     *
     * @param passwort The Password of the User
     */
    public void setPasswort(String passwort) {
        this.passwort.set(passwort);
    }

    /**
     * Getter method for the user id in the database
     * @return the uid in the database
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
