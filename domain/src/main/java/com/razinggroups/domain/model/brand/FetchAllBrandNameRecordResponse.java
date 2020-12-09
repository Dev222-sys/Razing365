package com.razinggroups.domain.model.brand;

public class FetchAllBrandNameRecordResponse {

    public String id;
    public String brand;
    public String brandCompany;

    public FetchAllBrandNameRecordResponse(String id, String brand, String brandCompany) {
        this.id = id;
        this.brand = brand;
        this.brandCompany = brandCompany;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandCompany() {
        return brandCompany;
    }

    public void setBrandCompany(String brandCompany) {
        this.brandCompany = brandCompany;
    }
}
