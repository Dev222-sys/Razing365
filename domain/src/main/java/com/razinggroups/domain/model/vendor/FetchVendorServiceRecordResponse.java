package com.razinggroups.domain.model.vendor;

public class FetchVendorServiceRecordResponse {

    public String service;

    public FetchVendorServiceRecordResponse(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
