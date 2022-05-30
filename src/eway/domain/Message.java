package eway.domain;

public class Message {
    private String phoneNumber;
    private String content;
    private String time;

    public Message() {
    }

    public Message(String phoneNumber, String content, String time) {
        this.phoneNumber = phoneNumber;
        this.content = content;
        this.time = time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return getTime() + "|" + getPhoneNumber() + "|" + getContent();
    }

}
