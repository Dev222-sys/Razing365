package com.razinggroups.domain.model.myTask;

public class CreateMyTaskRequest {

    public String brand;
    public String taskTitle;
    public String deadline;
    public String taskFile;
    public String taskDetail;

    public CreateMyTaskRequest(String brand, String taskTitle, String deadline, String taskFile, String taskDetail) {
        this.brand = brand;
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.taskFile = taskFile;
        this.taskDetail = taskDetail;
    }

    public CreateMyTaskRequest(String taskTitle, String deadline, String taskFile, String taskDetail) {
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.taskFile = taskFile;
        this.taskDetail = taskDetail;
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
