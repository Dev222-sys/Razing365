package com.razinggroups.data.models.employeeTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchEmployeeTaskNw {

    @SerializedName("count")
    @Expose
    public String count;

    @SerializedName("records")
    @Expose
    public List<FetchEmployeeTaskRecordNw> records = null;

    public FetchEmployeeTaskNw(String count, List<FetchEmployeeTaskRecordNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchEmployeeTaskRecordNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchEmployeeTaskRecordNw> records) {
        this.records = records;
    }
}
