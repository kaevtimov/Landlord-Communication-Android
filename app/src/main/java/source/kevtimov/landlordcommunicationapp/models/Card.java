package source.kevtimov.landlordcommunicationapp.models;

import android.annotation.SuppressLint;

import java.io.Serializable;

public class Card implements Serializable {

    private int cardID;
    private String brand;
    private String type;
    private String cardNumber;
    private String cvvNumber;
    private double balance;
    private int userID;
    private User user;

    public Card() {
        //default
    }

    public Card(String brand, String type, String cardNumber, String cvvNumber, double balance, int userID) {
        setUserID(userID);
        setBrand(brand);
        setType(type);
        setCardNumber(cardNumber);
        setCvvNumber(cvvNumber);
        setBalance(balance);
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("%s, %s, %.2f", getBrand(), getType(), getBalance());
    }
}
