package com.razinggroups.domain.model.CustomerQuery;

public class Customer {
    public String id;

    public String lead_type;
    public String name;
    public String company_email;
    public String mobile;
    public String Landline;
    public String passport_no;
    public String nationality;
    public String enquiry_details;
    public String reference;
    public String address;
    public String profession;

    public Customer(String id, String lead_type, String name, String company_email, String mobile, String landline, String passport_no, String nationality, String enquiry_details, String reference, String address, String profession) {
        this.id = id;
        this.lead_type = lead_type;
        this.name = name;
        this.company_email = company_email;
        this.mobile = mobile;
        Landline = landline;
        this.passport_no = passport_no;
        this.nationality = nationality;
        this.enquiry_details = enquiry_details;
        this.reference = reference;
        this.address = address;
        this.profession = profession;
    }


    public Customer() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLandline() {
        return Landline;
    }

    public void setLandline(String landline) {
        Landline = landline;
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

    public String getEnquiry_details() {
        return enquiry_details;
    }

    public void setEnquiry_details(String enquiry_details) {
        this.enquiry_details = enquiry_details;
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
