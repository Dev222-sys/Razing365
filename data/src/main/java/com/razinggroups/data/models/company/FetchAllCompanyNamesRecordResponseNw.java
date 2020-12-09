package com.razinggroups.data.models.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAllCompanyNamesRecordResponseNw {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("company")
    @Expose
    public String company;

    public FetchAllCompanyNamesRecordResponseNw(String id, String company) {
        this.id = id;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
