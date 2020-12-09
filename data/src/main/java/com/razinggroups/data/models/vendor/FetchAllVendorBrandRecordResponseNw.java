package com.razinggroups.data.models.vendor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllVendorBrandRecordResponseNw {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("vendor_brand")
    @Expose
    public String vendorBrand;

    public FetchAllVendorBrandRecordResponseNw(String id, String vendorBrand) {
        this.id = id;
        this.vendorBrand = vendorBrand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorBrand() {
        return vendorBrand;
    }

    public void setVendorBrand(String vendorBrand) {
        this.vendorBrand = vendorBrand;
    }
}
