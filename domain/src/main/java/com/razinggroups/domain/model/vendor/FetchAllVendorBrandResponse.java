package com.razinggroups.domain.model.vendor;

import java.util.List;

public class FetchAllVendorBrandResponse {
    public List<com.razinggroups.domain.model.vendor.FetchAllVendorBrandRecordResponse> records = null;

    public FetchAllVendorBrandResponse(List<com.razinggroups.domain.model.vendor.FetchAllVendorBrandRecordResponse> records) {
        this.records = records;
    }

    public List<com.razinggroups.domain.model.vendor.FetchAllVendorBrandRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllVendorBrandRecordResponse> records) {
        this.records = records;
    }
}
