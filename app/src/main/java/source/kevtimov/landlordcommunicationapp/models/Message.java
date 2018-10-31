package source.kevtimov.landlordcommunicationapp.models;

public class Message {
    private String from;
    private String messageBody;
    private boolean isSendByMe;
    private String senderName;

    public Message() {
        //default
    }

    public Message(String from, String body, boolean isSendByMe, String senderName){
        this.from = from;
        this.messageBody = body;
        this.isSendByMe = isSendByMe;
        this.senderName = senderName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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
