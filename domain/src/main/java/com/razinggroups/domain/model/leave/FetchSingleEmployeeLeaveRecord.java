package com.razinggroups.domain.model.leave;

public class FetchSingleEmployeeLeaveRecord {

    public String leaveTitle;
    public String fromDate;
    public String toDate;
    public String discription;
    public String status;

    public FetchSingleEmployeeLeaveRecord(String leaveTitle, String fromDate, String toDate, String discription, String status) {
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
