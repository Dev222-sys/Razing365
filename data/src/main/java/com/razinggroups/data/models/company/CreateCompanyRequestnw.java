package com.razinggroups.data.models.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateCompanyRequestnw {

    @SerializedName("id")
    public String id;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("gst_number")
    @Expose
    public String gstNumber;
    @SerializedName("gst_location")
    @Expose
    public String gstLocation;
    @SerializedName("gst_file")
    @Expose
    public String gstFile;
    @SerializedName("pan_number")
    @Expose
    public String panNumber;
    @SerializedName("tan_number")
    @Expose
    public String tanNumber;
    @SerializedName("mca")
    @Expose
    public String mca;
    @SerializedName("billing_address")
    @Expose
    public String billingAddress;
    @SerializedName("billing_address_location")
    @Expose
    public String billingAddressLocation;
    @SerializedName("email_address")
    @Expose
    public String emailAddress;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("head_office_address")
    @Expose
    public String headOfficeAddress;
    @SerializedName("street")
    @Expose
    public String street;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("postal_code")
    @Expose
    public String postalCode;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("website_link")
    @Expose
    public String websiteLink;

    public CreateCompanyRequestnw(String companyName, String gstNumber, String gstLocation, String gstFile, String panNumber, String tanNumber, String mca, String billingAddress, String billingAddressLocation, String emailAddress, String mobile, String headOfficeAddress, String street, String city, String postalCode, String country, String websiteLink) {
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

    public CreateCompanyRequestnw(String id, String companyName, String gstNumber, String gstLocation, String gstFile, String panNumber, String tanNumber, String mca, String billingAddress, String billingAddressLocation, String emailAddress, String mobile, String headOfficeAddress, String street, String city, String postalCode, String country, String websiteLink) {
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

    @Override
    public String toString() {
        return "CreateCompanyRequestnw{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", gstNumber='" + gstNumber + '\'' +
                ", gstLocation='" + gstLocation + '\'' +
                ", gstFile='" + gstFile + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", tanNumber='" + tanNumber + '\'' +
                ", mca='" + mca + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", billingAddressLocation='" + billingAddressLocation + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobile='" + mobile + '\'' +
                ", headOfficeAddress='" + headOfficeAddress + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", websiteLink='" + websiteLink + '\'' +
                '}';
    }
}
