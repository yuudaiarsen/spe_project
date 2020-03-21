package ru.ikbo1018.models;

import java.sql.Date;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String midName;
    private String email;
    private String password;
    private int secLevel;
    private Date regDate;

    public Account(int id, String firstName, String lastName, String midName, String email, String password, int secLevel, Date regDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.email = email;
        this.password = password;
        this.secLevel = secLevel;
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

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

    public int getSecLevel() {
        return secLevel;
    }

    public void setSecLevel(int secLevel) {
        this.secLevel = secLevel;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
