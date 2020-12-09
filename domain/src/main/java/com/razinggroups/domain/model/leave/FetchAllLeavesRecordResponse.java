package com.razinggroups.domain.model.leave;

public class FetchAllLeavesRecordResponse {

    public String id;
    public String empid;
    public String name;
    public String brand;
    public String leaveTitle;
    public String leaveStatus;
    public String remainingLeave;
    public String fromDate;
    public String toDate;
    public String discription;

    public FetchAllLeavesRecordResponse(String id, String empid, String name, String brand, String leaveTitle, String leaveStatus, String remainingLeave, String fromDate, String toDate, String discription) {
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
