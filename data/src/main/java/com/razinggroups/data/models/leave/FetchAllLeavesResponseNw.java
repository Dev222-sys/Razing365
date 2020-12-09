package com.razinggroups.data.models.leave;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllLeavesResponseNw {
    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("records")
    @Expose
    public List<FetchAllLeavesRecordResponseNw> records = null;

    public FetchAllLeavesResponseNw(String count, List<FetchAllLeavesRecordResponseNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllLeavesRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllLeavesRecordResponseNw> records) {
        this.records = records;
    }
}
