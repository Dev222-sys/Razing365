package com.razinggroups.data.models.leave;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllLeavesRecordResponseNw {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("empid")
    @Expose
    public String empid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("leave_title")
    @Expose
    public String leaveTitle;
    @SerializedName("leave_status")
    @Expose
    public String leaveStatus;
    @SerializedName("remaining_leave")
    @Expose
    public String remainingLeave;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("to_date")
    @Expose
    public String toDate;
    @SerializedName("discription")
    @Expose
    public String discription;

    public FetchAllLeavesRecordResponseNw(String id, String empid, String name, String brand, String leaveTitle, String leaveStatus, String remainingLeave, String fromDate, String toDate, String discription) {
        this.id = id;
        this.empid = empid;
        this.name = name;
        this.brand = brand;
        this.leaveTitle = leaveTitle;
        this.leaveStatus = leaveStatus;
        this.remainingLeave = remainingLeave;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.discription = discription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLeaveTitle() {
        return leaveTitle;
    }

    public void setLeaveTitle(String leaveTitle) {
        this.leaveTitle = leaveTitle;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getRemainingLeave() {
        return remainingLeave;
    }

    public void setRemainingLeave(String remainingLeave) {
        this.remainingLeave = remainingLeave;
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
}
