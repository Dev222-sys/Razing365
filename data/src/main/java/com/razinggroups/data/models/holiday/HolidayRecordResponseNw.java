package com.razinggroups.data.models.holiday;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolidayRecordResponseNw {

    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("holiday name")
    @Expose
    public String holidayName;

    public HolidayRecordResponseNw(String date, String holidayName) {
        this.date = date;
        this.holidayName = holidayName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }
}
