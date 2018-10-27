package source.kevtimov.landlordcommunicationapp.models;

import java.io.Serializable;
import java.util.List;

public class Place implements Serializable{

    private int placeID;
    private int tenantID;
    private int landlordID;
    private String address;
    private String description;
    private List<String> rents;

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

    private void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRents() {
        return rents;
    }

    public int getTenantID() {
        return tenantID;
    }

    private void setTenantID(int tenantID) {
        this.tenantID = tenantID;
    }

    public int getLandlordID() {
        return landlordID;
    }

    private void setLandlordID(int landlordID) {
        this.landlordID = landlordID;
    }

    public void setRents(List<String> rents) {
        this.rents = rents;
    }
}

