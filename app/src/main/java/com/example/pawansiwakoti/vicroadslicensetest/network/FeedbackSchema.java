package com.example.pawansiwakoti.vicroadslicensetest.network;

public class FeedbackSchema {
    private String Name;
    private String Email;
    private String Phone;
    private String Message;

    public FeedbackSchema() {
    }

    public FeedbackSchema(String name, String email, String phone, String message) {
        Name = name;
        Email = email;
        Phone = phone;
        Message = message;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
