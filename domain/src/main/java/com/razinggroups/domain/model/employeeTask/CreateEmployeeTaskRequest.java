package com.razinggroups.domain.model.employeeTask;

public class CreateEmployeeTaskRequest {

    public String empid;
    public String taskFile;
    public String taskDetail;
    public String deadline;
    public String taskTitle;

    public CreateEmployeeTaskRequest(String empid, String taskFile, String taskDetail, String deadline, String taskTitle) {
        this.empid = empid;
        this.taskFile = taskFile;
        this.taskDetail = taskDetail;
        this.deadline = deadline;
        this.taskTitle = taskTitle;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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
}

