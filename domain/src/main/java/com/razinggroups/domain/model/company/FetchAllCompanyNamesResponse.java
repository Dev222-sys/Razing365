package com.razinggroups.domain.model.company;

import java.util.List;

public class FetchAllCompanyNamesResponse {
    public String count;
    public List<com.razinggroups.domain.model.company.FetchAllCompanyNamesRecordResponse> records = null;

    public FetchAllCompanyNamesResponse(String count, List<com.razinggroups.domain.model.company.FetchAllCompanyNamesRecordResponse> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<com.razinggroups.domain.model.company.FetchAllCompanyNamesRecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<FetchAllCompanyNamesRecordResponse> records) {
        this.records = records;
    }
}
