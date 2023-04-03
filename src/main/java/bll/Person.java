package bll;

import presentation.who;

/**
 * Person is the main entity we'll be using to do the login into the app
 *
 * @author Szekely Maria-Robertha
 */

public class Person {

    private String username;
    private String password;
    private who role;

    public Person(String username, String password, who role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public who getRole() {
        return role;
    }

    @Override
    public String toString() {
        return this.username + " " + this.password + " " + this.role;
    }
}
