package source.kevtimov.landlordcommunicationapp.models;

import java.io.Serializable;

public class Payment implements Serializable {

    private int paymentID;
    private int userID;
    private int cardID;
    private int rentID;
    private double amount;
    private String date;
    private Card card;
    private Rent rent;


    public Payment() {
        //default
    }

    public Payment(double amount, String date, int userID, int cardID, int rentID) {
        setRentID(rentID);
        setUserID(userID);
        setCardID(cardID);
        setAmount(amount);
        setDate(date);
    }

    public int getPaymentID() {
        return paymentID;
    }

    private void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getUserID() {
        return userID;
    }

    private void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCardID() {
        return cardID;
    }

    private void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getRentID() {
        return rentID;
    }

    private void setRentID(int rentID) {
        this.rentID = rentID;
    }
}

