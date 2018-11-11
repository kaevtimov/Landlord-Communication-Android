package source.kevtimov.landlordcommunicationapp.models;

import java.io.Serializable;

public class Message implements Serializable {

    private int messageID;
    private String timestamp;
    private int senderID;
    private int receiverID;
    private int chatsessionId;
    private String text;



    public Message() {
        //default
    }

    public Message(String timestamp, String text, int senderID, int receiverID, int chatSessionID) {
        setChatsessionId(chatSessionID);
        setSenderID(senderID);
        setReceiverID(receiverID);
        setTimestamp(timestamp);
        setText(text);
    }


    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getChatsessionId() {
        return chatsessionId;
    }

    public void setChatsessionId(int chatsessionId) {
        this.chatsessionId = chatsessionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
