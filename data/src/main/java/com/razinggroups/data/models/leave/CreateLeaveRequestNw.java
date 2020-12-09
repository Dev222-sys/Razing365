package com.razinggroups.data.models.leave;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateLeaveRequestNw {
    @SerializedName("empid")
    @Expose
    public String empid;
    @SerializedName("leave_title")
    @Expose
    public String leaveTitle;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("to_date")
    @Expose
    public String toDate;
    @SerializedName("leave_disc")
    @Expose
    public String leaveDisc;

    public CreateLeaveRequestNw(String empid, String leaveTitle, String fromDate, String toDate, String leaveDisc) {
        this.empid = empid;
        this.leaveTitle = leaveTitle;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.leaveDisc = leaveDisc;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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

    public String getLeaveDisc() {
        return leaveDisc;
    }

    public void setLeaveDisc(String leaveDisc) {
        this.leaveDisc = leaveDisc;
    }
}
