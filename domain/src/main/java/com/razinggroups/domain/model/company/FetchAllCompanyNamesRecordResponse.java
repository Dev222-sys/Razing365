package com.razinggroups.domain.model.company;

public class FetchAllCompanyNamesRecordResponse {
    public String id;
    public String company;

    public FetchAllCompanyNamesRecordResponse(String id, String company) {
        this.id = id;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
