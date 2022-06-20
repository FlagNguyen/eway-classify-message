package eway.domain

class Message {
    private String phoneNumber
    private String content
    private String time

    Message(String phoneNumber, String content, String time) {
        this.phoneNumber = phoneNumber
        this.content = content
        this.time = time
    }

    String getPhoneNumber() {
        return phoneNumber
    }

    String getContent() {
        return content
    }

    String getTime() {
        return time
    }

    @Override
    String toString() {
        return getTime() + "|" + getPhoneNumber() + "|" + getContent()
    }

}
