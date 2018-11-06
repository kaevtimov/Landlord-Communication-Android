package source.kevtimov.landlordcommunicationapp.models;

import java.util.Date;

public class Message {
    private String receiverToken;
    private String messageBody;
    private boolean isSendByMe;
    private String senderName;
    private Date timestamp;

    public Message() {
        //default
    }

    public Message(String from, String body, boolean isSendByMe, String senderName){
        this.receiverToken = from;
        this.messageBody = body;
        this.isSendByMe = isSendByMe;
        this.senderName = senderName;
    }

    public String getReceiverToken() {
        return receiverToken;
    }

    public void setReceiverToken(String receiverToken) {
        this.receiverToken = receiverToken;
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
