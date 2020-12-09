package com.razinggroups.domain.model.leave;

public class CreateLeaveRequest {
    public String empid;
    public String leaveTitle;
    public String fromDate;
    public String toDate;
    public String leaveDisc;

    public CreateLeaveRequest(String empid, String leaveTitle, String fromDate, String toDate, String leaveDisc) {
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
