package com.razinggroups.domain.model.vendor;

import java.util.List;

public class FetchAllVendorResponse {

    public String count;
    public List<FetchAllVendorRecordResponse> records = null;

    public FetchAllVendorResponse(String count, List<FetchAllVendorRecordResponse> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchAllVendorRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllVendorRecordResponse> records) {
        this.records = records;
    }
}
