package com.razinggroups.data.models.vendor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllVendorBrandResponseNw {
    @SerializedName("records")
    @Expose
    public List<FetchAllVendorBrandRecordResponseNw> records = null;

    public FetchAllVendorBrandResponseNw(List<FetchAllVendorBrandRecordResponseNw> records) {
        this.records = records;
    }

    public List<FetchAllVendorBrandRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllVendorBrandRecordResponseNw> records) {
        this.records = records;
    }
}
