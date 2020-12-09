package com.razinggroups.domain.model.vendorTask;

import java.util.List;

public class FetchVendorTaks {

    public String count;
    public List<FetchVendorTaksRecord> records = null;

    public FetchVendorTaks(String count, List<FetchVendorTaksRecord> records) {
        this.count = count;
        this.records = records;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FetchVendorTaksRecord> getRecords() {
        return records;
    }

    public void setRecords(List<FetchVendorTaksRecord> records) {
        this.records = records;
    }
}
