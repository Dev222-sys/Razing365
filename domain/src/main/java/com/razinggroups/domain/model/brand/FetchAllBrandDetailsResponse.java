package com.razinggroups.domain.model.brand;

import java.util.List;

public class FetchAllBrandDetailsResponse {

    public List<FetchAllBrandDetailsRecordResponse> records = null;

    public FetchAllBrandDetailsResponse(List<FetchAllBrandDetailsRecordResponse> records) {
        this.records = records;
    }

    public List<FetchAllBrandDetailsRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllBrandDetailsRecordResponse> records) {
        this.records = records;
    }
}
