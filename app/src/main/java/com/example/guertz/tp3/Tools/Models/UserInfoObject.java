package com.example.guertz.tp3.Tools.Models;

import java.io.Serializable;

/**
 * Created by guertz on 25/07/17.
 */

public class UserInfoObject implements Serializable {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public UserInfoObject() {
        setEmail("");
        setPassword("");
        setFullName("","");
    }

    //region Getter-Setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFullName(String firstName,String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    // endregion

}
