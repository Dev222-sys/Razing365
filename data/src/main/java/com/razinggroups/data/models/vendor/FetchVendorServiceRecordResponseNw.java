package com.razinggroups.data.models.vendor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchVendorServiceRecordResponseNw {

    @SerializedName("service")
    @Expose
    public String service;

    public FetchVendorServiceRecordResponseNw(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
