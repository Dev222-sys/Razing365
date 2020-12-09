package com.razinggroups.data.models.vendor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllVendorRecordResponseNw {

    @SerializedName("id")
    @Expose
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
    @SerializedName("vendor_brand")
    @Expose
    public String vendorBrand;

    public FetchAllVendorRecordResponseNw(String id, String firstName, String middleName, String lastName, String vendorBrand) {
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
