package com.example.mychatbot;

public class ChatModel {
    private String message;
    private String sender;

    public ChatModel(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
