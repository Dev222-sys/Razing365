package com.razinggroups.domain.model.employeeTask;

import java.util.List;

public class FetchEmployeeTask {

    public String count;

    public List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> records = null;

    public FetchEmployeeTask(String count, List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllEmployeeTaskRecord> records) {
        this.records = records;
    }
}
