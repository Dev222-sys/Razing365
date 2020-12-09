package com.razinggroups.data.models.leave;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchSingleEmployeeLeaveRecordNw {

    @SerializedName("leave_title")
    @Expose
    public String leaveTitle;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("to_date")
    @Expose
    public String toDate;
    @SerializedName("discription")
    @Expose
    public String discription;
    @SerializedName("status")
    @Expose
    public String status;

    public FetchSingleEmployeeLeaveRecordNw(String leaveTitle, String fromDate, String toDate, String discription, String status) {
        this.leaveTitle = leaveTitle;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.discription = discription;
        this.status = status;
    }

    public String getLeaveTitle() {
        return leaveTitle;
    }

    public void setLeaveTitle(String leaveTitle) {
        this.leaveTitle = leaveTitle;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
