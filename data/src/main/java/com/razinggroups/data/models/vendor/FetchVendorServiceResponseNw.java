package com.razinggroups.data.models.vendor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchVendorServiceResponseNw {
    @SerializedName("records")
    @Expose
    public List<FetchVendorServiceRecordResponseNw> records = null;

    public FetchVendorServiceResponseNw(List<FetchVendorServiceRecordResponseNw> records) {
        this.records = records;
    }

    public List<FetchVendorServiceRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchVendorServiceRecordResponseNw> records) {
        this.records = records;
    }
}
