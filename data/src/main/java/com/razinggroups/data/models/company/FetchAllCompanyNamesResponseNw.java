package com.razinggroups.data.models.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllCompanyNamesResponseNw {
    @SerializedName("count")
    @Expose
    public String count;
    @SerializedName("records")
    @Expose
    public List<FetchAllCompanyNamesRecordResponseNw> records = null;

    public FetchAllCompanyNamesResponseNw(String count, List<FetchAllCompanyNamesRecordResponseNw> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllCompanyNamesRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllCompanyNamesRecordResponseNw> records) {
        this.records = records;
    }
}
