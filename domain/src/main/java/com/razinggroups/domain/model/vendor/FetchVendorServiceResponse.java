package com.razinggroups.domain.model.vendor;

import java.util.List;

public class FetchVendorServiceResponse {
    public List<FetchVendorServiceRecordResponse> records = null;

    public FetchVendorServiceResponse(List<FetchVendorServiceRecordResponse> records) {
        this.records = records;
    }

    public List<FetchVendorServiceRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchVendorServiceRecordResponse> records) {
        this.records = records;
    }
}
