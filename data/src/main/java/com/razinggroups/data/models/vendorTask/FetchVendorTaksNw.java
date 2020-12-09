package com.razinggroups.data.models.vendorTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchVendorTaksNw {

    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("records")
    @Expose
    public List<FetchVendorTaksRecordNw> records = null;

    public FetchVendorTaksNw(String count, List<FetchVendorTaksRecordNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchVendorTaksRecordNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchVendorTaksRecordNw> records) {
        this.records = records;
    }
}
