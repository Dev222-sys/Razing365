package com.razinggroups.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageNw {

    @SerializedName("message")
    @Expose
    public String message;

    public MessageNw(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
