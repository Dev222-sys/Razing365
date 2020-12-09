package com.razinggroups.domain.model.vendor;

public class FetchAllVendorBrandRecordResponse {
    public String id;
    public String vendorBrand;

    public FetchAllVendorBrandRecordResponse(String id, String vendorBrand) {
        this.id = id;
        this.vendorBrand = vendorBrand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorBrand() {
        return vendorBrand;
    }

    public void setVendorBrand(String vendorBrand) {
        this.vendorBrand = vendorBrand;
    }
}
