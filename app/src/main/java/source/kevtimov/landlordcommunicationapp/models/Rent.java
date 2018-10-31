package source.kevtimov.landlordcommunicationapp.models;

import java.io.Serializable;

public class Rent implements Serializable {

    private int rentID;
    private int placeID;
    private double totalAmount;
    private double remaining;
    private String dueDate;
    private boolean isPaid;

    public Rent(){
        //default
    }

    public Rent(int placeId, double totalAmount, double remainingAmount, String dueDate, boolean isPaid){
        setPlaceID(placeId);
        setDueDate(dueDate);
        setPaid(isPaid);
        setRemainingAmount(remainingAmount);
        setTotalAmount(totalAmount);
    }

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getRemainingAmount() {
        return remaining;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remaining = remainingAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

}
