package com.razinggroups.domain.model.company;

public class FetchAllCompanyDetailsRecordResponse {

    public String id;
    public String companyName;
    public String gstNumber;
    public String gstLocation;
    public String gstFile;
    public String panNumber;
    public String tanNumber;
    public String mca;
    public String billingAddress;
    public String billingAddressLocation;
    public String emailAddress;
    public String mobile;
    public String headOfficeAddress;
    public String street;
    public String city;
    public String postalCode;
    public String country;
    public String websiteLink;

    public FetchAllCompanyDetailsRecordResponse(String id, String companyName, String gstNumber, String gstLocation, String gstFile, String panNumber, String tanNumber, String mca, String billingAddress, String billingAddressLocation, String emailAddress, String mobile, String headOfficeAddress, String street, String city, String postalCode, String country, String websiteLink) {
        this.id = id;
        this.companyName = companyName;
        this.gstNumber = gstNumber;
        this.gstLocation = gstLocation;
        this.gstFile = gstFile;
        this.panNumber = panNumber;
        this.tanNumber = tanNumber;
        this.mca = mca;
        this.billingAddress = billingAddress;
        this.billingAddressLocation = billingAddressLocation;
        this.emailAddress = emailAddress;
        this.mobile = mobile;
        this.headOfficeAddress = headOfficeAddress;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.websiteLink = websiteLink;
    }

    public FetchAllCompanyDetailsRecordResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getGstLocation() {
        return gstLocation;
    }

    public void setGstLocation(String gstLocation) {
        this.gstLocation = gstLocation;
    }

    public String getGstFile() {
        return gstFile;
    }

    public void setGstFile(String gstFile) {
        this.gstFile = gstFile;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getTanNumber() {
        return tanNumber;
    }

    public void setTanNumber(String tanNumber) {
        this.tanNumber = tanNumber;
    }

    public String getMca() {
        return mca;
    }

    public void setMca(String mca) {
        this.mca = mca;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingAddressLocation() {
        return billingAddressLocation;
    }

    public void setBillingAddressLocation(String billingAddressLocation) {
        this.billingAddressLocation = billingAddressLocation;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadOfficeAddress() {
        return headOfficeAddress;
    }

    public void setHeadOfficeAddress(String headOfficeAddress) {
        this.headOfficeAddress = headOfficeAddress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }
}
