package com.razinggroups.domain.model.employeeTask;

public class FetchAllEmployeeTaskRecord {
    public String id;
    public String empid;
    public String empname;
    public String taskFile;
    public String taskDetail;
    public String assignDate;
    public String deadline;
    public String taskTitle;
    public String status;

    public FetchAllEmployeeTaskRecord(String id, String empid, String empname, String taskFile, String taskDetail, String assignDate, String deadline, String taskTitle, String status) {
        this.id = id;
        this.empid = empid;
        this.empname = empname;
        this.taskFile = taskFile;
        this.taskDetail = taskDetail;
        this.assignDate = assignDate;
        this.deadline = deadline;
        this.taskTitle = taskTitle;
        this.status = status;
    }

    public FetchAllEmployeeTaskRecord() {

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

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(String taskFile) {
        this.taskFile = taskFile;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
