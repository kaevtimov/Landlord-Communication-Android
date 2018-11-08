package source.kevtimov.landlordcommunicationapp.models;

public class Message {
    private int messageId;
    private String picture;
    private int receiverId;
    private int senderId;
    private long timestamp;
    private String messageBody;

    private boolean isSendByMe;
    private String receiverToken;

    public Message() {
        //default
    }

    public Message(String picture, int receiverId, int senderId, long timestamp, String body, boolean isSendByMe, String receiverToken){
        this.receiverToken = receiverToken;
        setPicture(picture);
        setReceiverId(receiverId);
        setSenderId(senderId);
        setTimestamp(timestamp);
        setMessageBody(body);
        setSendByMe(isSendByMe);
        setReceiverToken(receiverToken);
    }


    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public boolean isSendByMe() {
        return isSendByMe;
    }

    public void setSendByMe(boolean sendByMe) {
        isSendByMe = sendByMe;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getReceiverToken() {
        return receiverToken;
    }

    public void setReceiverToken(String receiverToken) {
        this.receiverToken = receiverToken;
    }
}
