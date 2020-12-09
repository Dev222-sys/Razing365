package com.razinggroups.domain.model.vendorTask;

public class FetchVendorTaksRecord {
    public String id;
    public String brand;
    public String service;
    public String assignDate;
    public String deadlineDate;
    public String taskDetail;

    public FetchVendorTaksRecord(String id, String brand, String service, String assignDate, String deadlineDate, String taskDetail) {
        this.id = id;
        this.brand = brand;
        this.service = service;
        this.assignDate = assignDate;
        this.deadlineDate = deadlineDate;
        this.taskDetail = taskDetail;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }
}
