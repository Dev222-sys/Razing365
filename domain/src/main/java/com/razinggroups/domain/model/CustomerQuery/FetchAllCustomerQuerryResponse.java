package com.razinggroups.domain.model.CustomerQuery;
import java.util.List;

public class FetchAllCustomerQuerryResponse {

    public List<FetchAllCustomerQuerryRecordResponse> records = null;

    public FetchAllCustomerQuerryResponse(List<FetchAllCustomerQuerryRecordResponse> records) {
        this.records = records;
    }

    public List<FetchAllCustomerQuerryRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllCustomerQuerryRecordResponse> records) {
        this.records = records;
    }
}