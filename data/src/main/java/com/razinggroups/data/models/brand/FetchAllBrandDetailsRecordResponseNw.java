package com.razinggroups.data.models.brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllBrandDetailsRecordResponseNw {

    @SerializedName("id")
    public String id;

    @SerializedName("brand_name")
    @Expose
    public String brandName;
    @SerializedName("brand_company")
    @Expose
    public String brandCompany;
    @SerializedName("brand_division")
    @Expose
    public String brandDivision;
    @SerializedName("division_mail")
    @Expose
    public String divisionMail;
    @SerializedName("division_mobile")
    @Expose
    public String divisionMobile;
    @SerializedName("brand_email")
    @Expose
    public String brandEmail;
    @SerializedName("brand_mobile")
    @Expose
    public String brandMobile;
    @SerializedName("brand_street")
    @Expose
    public String brandStreet;
    @SerializedName("brand_city")
    @Expose
    public String brandCity;
    @SerializedName("brand_code")
    @Expose
    public String brandCode;
    @SerializedName("brand_country")
    @Expose
    public String brandCountry;

    public FetchAllBrandDetailsRecordResponseNw(String id, String brandName, String brandCompany, String brandDivision, String divisionMail, String divisionMobile, String brandEmail, String brandMobile, String brandStreet, String brandCity, String brandCode, String brandCountry) {
        this.id = id;
        this.brandName = brandName;
        this.brandCompany = brandCompany;
        this.brandDivision = brandDivision;
        this.divisionMail = divisionMail;
        this.divisionMobile = divisionMobile;
        this.brandEmail = brandEmail;
        this.brandMobile = brandMobile;
        this.brandStreet = brandStreet;
        this.brandCity = brandCity;
        this.brandCode = brandCode;
        this.brandCountry = brandCountry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCompany() {
        return brandCompany;
    }

    public void setBrandCompany(String brandCompany) {
        this.brandCompany = brandCompany;
    }

    public String getBrandDivision() {
        return brandDivision;
    }

    public void setBrandDivision(String brandDivision) {
        this.brandDivision = brandDivision;
    }

    public String getDivisionMail() {
        return divisionMail;
    }

    public void setDivisionMail(String divisionMail) {
        this.divisionMail = divisionMail;
    }

    public String getDivisionMobile() {
        return divisionMobile;
    }

    public void setDivisionMobile(String divisionMobile) {
        this.divisionMobile = divisionMobile;
    }

    public String getBrandEmail() {
        return brandEmail;
    }

    public void setBrandEmail(String brandEmail) {
        this.brandEmail = brandEmail;
    }

    public String getBrandMobile() {
        return brandMobile;
    }

    public void setBrandMobile(String brandMobile) {
        this.brandMobile = brandMobile;
    }

    public String getBrandStreet() {
        return brandStreet;
    }

    public void setBrandStreet(String brandStreet) {
        this.brandStreet = brandStreet;
    }

    public String getBrandCity() {
        return brandCity;
    }

    public void setBrandCity(String brandCity) {
        this.brandCity = brandCity;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandCountry() {
        return brandCountry;
    }

    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry;
    }
}
