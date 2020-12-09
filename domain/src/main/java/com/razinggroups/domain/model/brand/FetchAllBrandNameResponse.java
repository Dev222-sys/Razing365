package com.razinggroups.domain.model.brand;

import java.util.List;

public class FetchAllBrandNameResponse {

    public String count;
    public List<FetchAllBrandNameRecordResponse> records = null;

    public FetchAllBrandNameResponse(String count, List<FetchAllBrandNameRecordResponse> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllBrandNameRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllBrandNameRecordResponse> records) {
        this.records = records;
    }
}
