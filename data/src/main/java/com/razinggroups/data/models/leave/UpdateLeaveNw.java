package com.razinggroups.data.models.leave;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateLeaveNw {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("status")
    @Expose
    public String status;

    public UpdateLeaveNw(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
