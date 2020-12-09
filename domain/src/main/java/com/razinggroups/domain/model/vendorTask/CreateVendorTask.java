package com.razinggroups.domain.model.vendorTask;

public class CreateVendorTask {

    public String brand;
    public String service;
    public String deadline;
    public String taskDetail;
    public String taskFile;

    public CreateVendorTask(String brand, String service, String deadline, String taskDetail, String taskFile) {
        this.brand = brand;
        this.service = service;
        this.deadline = deadline;
        this.taskDetail = taskDetail;
        this.taskFile = taskFile;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(String taskFile) {
        this.taskFile = taskFile;
    }
}
