package com.razinggroups.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleIdRequestNw {

    @SerializedName("id")
    @Expose
    private String id;

    public SingleIdRequestNw(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
