package com.razinggroups.data.models.myTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllMyTaskRecordsNw {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("brand")
    public String brand;

    @SerializedName("task_title")
    @Expose
    public String taskTitle;
    @SerializedName("deadline")
    @Expose
    public String deadline;
    @SerializedName("task_file")
    @Expose
    public String taskFile;
    @SerializedName("task_detail")
    @Expose
    public String taskDetail;
    @SerializedName("assign_date")
    @Expose
    public String assignDate;
    @SerializedName("status")
    @Expose
    public String status;

    public FetchAllMyTaskRecordsNw(String id, String brand, String taskTitle, String deadline, String taskFile, String taskDetail, String assignDate, String status) {
        this.id = id;
        this.brand = brand;
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.taskFile = taskFile;
        this.taskDetail = taskDetail;
        this.assignDate = assignDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
