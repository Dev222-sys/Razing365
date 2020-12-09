package com.razinggroups.data.models.myTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllMyTaskNw {

    @SerializedName("count")
    public String count;

    @SerializedName("records")
    @Expose
    public List<FetchAllMyTaskRecordsNw> records = null;


    public FetchAllMyTaskNw(String count, List<FetchAllMyTaskRecordsNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllMyTaskRecordsNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllMyTaskRecordsNw> records) {
        this.records = records;
    }
}
