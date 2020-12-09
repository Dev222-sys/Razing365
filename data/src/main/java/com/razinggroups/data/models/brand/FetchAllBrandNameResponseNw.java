package com.razinggroups.data.models.brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllBrandNameResponseNw {

    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("records")
    @Expose
    public List<FetchAllBrandNameRecordResponseNw> records = null;

    public FetchAllBrandNameResponseNw(String count, List<FetchAllBrandNameRecordResponseNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllBrandNameRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllBrandNameRecordResponseNw> records) {
        this.records = records;
    }
}
