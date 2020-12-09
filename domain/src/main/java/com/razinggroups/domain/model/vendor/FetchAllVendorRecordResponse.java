package com.razinggroups.domain.model.vendor;

public class FetchAllVendorRecordResponse {

    public String id;
    public String firstName;
    public String middleName;
    public String lastName;
    public String vendorBrand;

    public FetchAllVendorRecordResponse(String id, String firstName, String middleName, String lastName, String vendorBrand) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.vendorBrand = vendorBrand;
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

    public String getVendorBrand() {
        return vendorBrand;
    }

    public void setVendorBrand(String vendorBrand) {
        this.vendorBrand = vendorBrand;
    }
}
