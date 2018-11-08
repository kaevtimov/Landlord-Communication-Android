package source.kevtimov.landlordcommunicationapp.models;


import java.io.Serializable;
import java.util.List;

public class Place implements Serializable {

    private int placeID;
    private int tenantID;
    private int landlordID;
    private String address;
    private String description;

    public Place() {
        //default
    }

    public Place(String address, String description, int tenantID, int landlordID) {
        setTenantID(tenantID);
        setLandlordID(landlordID);
        setAddress(address);
        setDescription(description);
    }


    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeid){this.placeID = placeid;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTenantID() {
        return tenantID;
    }

    public void setTenantID(int tenantID) {
        this.tenantID = tenantID;
    }

    public int getLandlordID() {
        return landlordID;
    }

    public void setLandlordID(int landlordID) {
        this.landlordID = landlordID;
    }


    @Override
    public String toString() {
        return String.format("Address: %s\n\n", getAddress());
    }

}

