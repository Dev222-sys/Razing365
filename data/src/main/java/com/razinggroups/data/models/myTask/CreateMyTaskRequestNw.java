package com.razinggroups.data.models.myTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateMyTaskRequestNw {



    @SerializedName("emp_id")
    public long id;

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

    public CreateMyTaskRequestNw(String brand, String taskTitle, String deadline, String taskFile, String taskDetail) {
        this.brand = brand;
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.taskFile = taskFile;
        this.taskDetail = taskDetail;
    }

    public CreateMyTaskRequestNw(String taskTitle, String deadline, String taskFile, String taskDetail) {
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.taskFile = taskFile;
        this.taskDetail = taskDetail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
