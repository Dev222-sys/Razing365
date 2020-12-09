package com.razinggroups.data.models.vendor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllVendorResponseNw {

    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("records")
    @Expose
    public List<FetchAllVendorRecordResponseNw> records = null;

    public FetchAllVendorResponseNw(String count, List<FetchAllVendorRecordResponseNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllVendorRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllVendorRecordResponseNw> records) {
        this.records = records;
    }
}
