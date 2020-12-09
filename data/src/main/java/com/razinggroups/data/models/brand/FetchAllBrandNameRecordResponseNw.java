package com.razinggroups.data.models.brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllBrandNameRecordResponseNw {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("brand_company")
    @Expose
    public String brandCompany;

    public FetchAllBrandNameRecordResponseNw(String id, String brand, String brandCompany) {
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
