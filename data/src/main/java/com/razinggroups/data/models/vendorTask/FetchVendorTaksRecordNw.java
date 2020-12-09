package com.razinggroups.data.models.vendorTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchVendorTaksRecordNw {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("service")
    @Expose
    public String service;
    @SerializedName("assign_date")
    @Expose
    public String assignDate;
    @SerializedName("deadline_date")
    @Expose
    public String deadlineDate;
    @SerializedName("task_detail")
    @Expose
    public String taskDetail;

    public FetchVendorTaksRecordNw(String id, String brand, String service, String assignDate, String deadlineDate, String taskDetail) {
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
