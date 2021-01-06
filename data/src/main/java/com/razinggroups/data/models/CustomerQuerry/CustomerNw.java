package com.razinggroups.data.models.CustomerQuerry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerNw {


    @SerializedName("lead_type")
    @Expose
    public String lead_type;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("company_email")
    @Expose
    public String company_email;


    @SerializedName("enquiry")
    @Expose
    public String enquiry;


    @SerializedName("mobile")
    @Expose
    public String mobile;

    @SerializedName("landline")
    @Expose
    public String landline;

    @SerializedName("passport_no")
    @Expose
    public String passport_no;

    @SerializedName("nationality")
    @Expose
    public String nationality;



    @SerializedName("reference")
    @Expose
    public String reference;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("profession")
    @Expose
    public String profession;


    public CustomerNw(String lead_type, String name, String company_email, String enquiry, String mobile, String landline, String passport_no, String nationality, String reference, String address, String profession) {
        this.lead_type = lead_type;
        this.name = name;
        this.company_email = company_email;
        this.enquiry = enquiry;
        this.mobile = mobile;
        this.landline = landline;
        this.passport_no = passport_no;
        this.nationality = nationality;
        this.reference = reference;
        this.address = address;
        this.profession = profession;
    }


    public String getLead_type() {
        return lead_type;
    }

    public void setLead_type(String lead_type) {
        this.lead_type = lead_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
