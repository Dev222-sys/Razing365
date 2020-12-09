package com.razinggroups.domain.model.leave;

import java.util.List;

public class FetchAllLeavesResponse {
    public String count;
    public List<FetchAllLeavesRecordResponse> records = null;

    public FetchAllLeavesResponse(String count, List<FetchAllLeavesRecordResponse> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllLeavesRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllLeavesRecordResponse> records) {
        this.records = records;
    }
}
