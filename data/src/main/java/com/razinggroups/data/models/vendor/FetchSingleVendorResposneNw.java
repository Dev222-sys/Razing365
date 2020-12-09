package com.razinggroups.data.models.vendor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchSingleVendorResposneNw {


    @SerializedName("id")
    public String id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("middle_name")
    @Expose
    public String middleName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("contact")
    @Expose
    public String contact;
    @SerializedName("vendor_brand")
    @Expose
    public String vendorBrand;
    @SerializedName("vendor_company")
    @Expose
    public String vendorCompany;
    @SerializedName("vendor_service")
    @Expose
    public String vendorService;
    @SerializedName("gstin")
    @Expose
    public String gstin;
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

    public FetchSingleVendorResposneNw(String firstName, String middleName, String lastName, String email, String contact, String vendorBrand, String vendorCompany, String vendorService, String gstin, String street, String city, String postalCode, String country) {
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

    public FetchSingleVendorResposneNw(String id, String firstName, String middleName, String lastName, String email, String contact, String vendorBrand, String vendorCompany, String vendorService, String gstin, String street, String city, String postalCode, String country) {
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
