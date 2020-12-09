package com.razinggroups.data.models.employeeTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateEmployeeTaskRequestNw {

    @SerializedName("empid")
    @Expose
    public String empid;
    @SerializedName("task_file")
    @Expose
    public String taskFile;
    @SerializedName("task_detail")
    @Expose
    public String taskDetail;
    @SerializedName("deadline")
    @Expose
    public String deadline;
    @SerializedName("task_title")
    @Expose
    public String taskTitle;

    public CreateEmployeeTaskRequestNw(String empid, String taskFile, String taskDetail, String deadline, String taskTitle) {
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

