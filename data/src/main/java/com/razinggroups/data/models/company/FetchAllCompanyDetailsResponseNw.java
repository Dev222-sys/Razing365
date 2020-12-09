package com.razinggroups.data.models.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllCompanyDetailsResponseNw {

    @SerializedName("records")
    @Expose
    public List<FetchAllCompanyDetailsRecordResponseNw> records = null;

    public FetchAllCompanyDetailsResponseNw(List<FetchAllCompanyDetailsRecordResponseNw> records) {
        this.records = records;
    }

    public List<FetchAllCompanyDetailsRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllCompanyDetailsRecordResponseNw> records) {
        this.records = records;
    }
}
