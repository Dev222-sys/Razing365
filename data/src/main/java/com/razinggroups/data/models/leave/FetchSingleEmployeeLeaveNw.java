package com.razinggroups.data.models.leave;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchSingleEmployeeLeaveNw {
    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("records")
    @Expose
    public List<FetchSingleEmployeeLeaveRecordNw> records = null;

    public FetchSingleEmployeeLeaveNw(String count, List<FetchSingleEmployeeLeaveRecordNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchSingleEmployeeLeaveRecordNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchSingleEmployeeLeaveRecordNw> records) {
        this.records = records;
    }
}
