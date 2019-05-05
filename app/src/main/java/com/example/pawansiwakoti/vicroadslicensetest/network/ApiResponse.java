package com.example.pawansiwakoti.vicroadslicensetest.network;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;



public class ApiResponse {
    @SerializedName("Error")
    private int error;

    @SerializedName("Message")
    private String message;

    @SerializedName("Identity")
    private String identity;

    public ApiResponse(int error, String message, String identity) {
        this.error = error;
        this.message = message;
        this.identity = identity;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault() , "Error: %d, Message: %s, Identity: %s", this.error, this.message, this.identity);
    }
}
