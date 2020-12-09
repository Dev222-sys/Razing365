package com.razinggroups.domain.model.holiday;

public class HolidayRecordResponse {

    public String date;
    public String holidayName;

    public HolidayRecordResponse(String date, String holidayName) {
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
