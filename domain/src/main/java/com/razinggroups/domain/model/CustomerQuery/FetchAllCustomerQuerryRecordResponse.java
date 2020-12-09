package com.razinggroups.domain.model.CustomerQuery;

public class
FetchAllCustomerQuerryRecordResponse {
    public String id;
    public String brandName;
    public String brandCompany;
    public String brandDivision;
    public String divisionMail;
    public String divisionMobile;
    public String brandEmail;
    public String brandMobile;
    public String brandStreet;
    public String brandCity;
    public String brandCode;
    public String brandCountry;

    public FetchAllCustomerQuerryRecordResponse(String id, String brandName, String brandCompany, String brandDivision, String divisionMail, String divisionMobile, String brandEmail, String brandMobile, String brandStreet, String brandCity, String brandCode, String brandCountry) {
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

    public FetchAllCustomerQuerryRecordResponse() {

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
