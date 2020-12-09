package com.razinggroups.domain.model.leave;

import java.util.List;

public class FetchSingleEmployeeLeave {
    public String count;
    public List<FetchSingleEmployeeLeaveRecord> records = null;

    public FetchSingleEmployeeLeave(String count, List<FetchSingleEmployeeLeaveRecord> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchSingleEmployeeLeaveRecord> getRecords() {
        return records;
    }

    public void setRecords(List<FetchSingleEmployeeLeaveRecord> records) {
        this.records = records;
    }
}
