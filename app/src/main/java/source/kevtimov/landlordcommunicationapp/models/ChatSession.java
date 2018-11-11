package source.kevtimov.landlordcommunicationapp.models;

import java.io.Serializable;
import java.util.List;

public class ChatSession implements Serializable {

    private int chatsessionId;
    private int tenantID;
    private int landlordID;
    private String createDate;
    private List<Message> messages;


    public ChatSession() {
        //default
    }

    public ChatSession(String createDate, int tenantID, int landlordID) {
        setTenantID(tenantID);
        setLandlordID(landlordID);
        setCreateDate(createDate);
    }



    @Override
    public String toString() {
        return String.format("Date of creation: %s", getCreateDate());
    }

    public int getChatsessionId() {
        return chatsessionId;
    }

    public void setChatsessionId(int chatsessionId) {
        this.chatsessionId = chatsessionId;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
