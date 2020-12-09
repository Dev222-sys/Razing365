package com.razinggroups.domain.model.vendor;

public class FetchSingleVendorResposne {


    public String id;
    public String firstName;
    public String middleName;
    public String lastName;
    public String email;
    public String contact;
    public String vendorBrand;
    public String vendorCompany;
    public String vendorService;
    public String gstin;
    public String street;
    public String city;
    public String postalCode;
    public String country;

    public FetchSingleVendorResposne(String firstName, String middleName, String lastName, String email, String contact, String vendorBrand, String vendorCompany, String vendorService, String gstin, String street, String city, String postalCode, String country) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.vendorBrand = vendorBrand;
        this.vendorCompany = vendorCompany;
        this.vendorService = vendorService;
        this.gstin = gstin;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public FetchSingleVendorResposne(String id, String firstName, String middleName, String lastName, String email, String contact, String vendorBrand, String vendorCompany, String vendorService, String gstin, String street, String city, String postalCode, String country) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.vendorBrand = vendorBrand;
        this.vendorCompany = vendorCompany;
        this.vendorService = vendorService;
        this.gstin = gstin;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVendorBrand() {
        return vendorBrand;
    }

    public void setVendorBrand(String vendorBrand) {
        this.vendorBrand = vendorBrand;
    }

    public String getVendorCompany() {
        return vendorCompany;
    }

    public void setVendorCompany(String vendorCompany) {
        this.vendorCompany = vendorCompany;
    }

    public String getVendorService() {
        return vendorService;
    }

    public void setVendorService(String vendorService) {
        this.vendorService = vendorService;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
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
}
