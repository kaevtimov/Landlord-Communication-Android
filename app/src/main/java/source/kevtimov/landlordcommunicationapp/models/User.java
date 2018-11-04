package source.kevtimov.landlordcommunicationapp.models;

import java.io.Serializable;

public class User implements Serializable {

    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private boolean isLandlord;
    private boolean isOnline;
    private String passwordHash;
    private String passwordSalt;

    public User(){
        //default
    }


    public User(boolean isLandlord, String username, String picture, String firstName, String lastName, String email,
                boolean isOnline, String passwordHash, String passwordSalt){
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setLandlord(isLandlord);
        setOnline(isOnline);
        setPicture(picture);
        setPasswordHash(passwordHash);
        setPasswordSalt(passwordSalt);
    }


    public int getUserId() {
        return userID;
    }

    public void setUserId(int userId){
        this.userID = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isLandlord() {
        return isLandlord;
    }

    public void setLandlord(boolean landlord) {
        isLandlord = landlord;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, %s", getLastName(), getFirstName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User us = (User)obj;

        return us.username.equals(username);
    }
}
