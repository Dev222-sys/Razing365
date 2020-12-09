package com.razinggroups.data.models.brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAllBrandDetailsResponseNw {

    @SerializedName("records")
    @Expose
    public List<FetchAllBrandDetailsRecordResponseNw> records = null;

    public FetchAllBrandDetailsResponseNw(List<FetchAllBrandDetailsRecordResponseNw> records) {
        this.records = records;
    }

    public List<FetchAllBrandDetailsRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllBrandDetailsRecordResponseNw> records) {
        this.records = records;
    }
}
