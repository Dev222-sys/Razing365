package com.razinggroups.data.models.holiday;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HolidayResponseNw {

    @SerializedName("records")
    @Expose
    public List<HolidayRecordResponseNw> records = null;

    public HolidayResponseNw(List<HolidayRecordResponseNw> records) {
        this.records = records;
    }

    public List<HolidayRecordResponseNw> getRecords() {
        return records;
    }

    public void setRecords(List<HolidayRecordResponseNw> records) {
        this.records = records;
    }
}
