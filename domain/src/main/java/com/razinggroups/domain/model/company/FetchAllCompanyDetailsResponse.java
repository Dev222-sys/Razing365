package com.razinggroups.domain.model.company;

import java.util.List;

public class FetchAllCompanyDetailsResponse {

    public List<com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse> records = null;

    public FetchAllCompanyDetailsResponse(List<com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse> records) {
        this.records = records;
    }

    public List<com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllCompanyDetailsRecordResponse> records) {
        this.records = records;
    }
}
