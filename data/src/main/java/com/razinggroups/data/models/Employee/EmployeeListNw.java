package com.razinggroups.data.models.Employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeListNw {

    @SerializedName("count")
    @Expose
    public String count;

    @SerializedName("records")
    @Expose
    public List<EmployeeDetailNw> employeeDetailNwList = null;

    public EmployeeListNw(String count, List<EmployeeDetailNw> employeeDetailNwList) {
        this.count = count;
        this.employeeDetailNwList = employeeDetailNwList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<EmployeeDetailNw> getEmployeeDetailNwList() {
        return employeeDetailNwList;
    }

    public void setEmployeeDetailNwList(List<EmployeeDetailNw> employeeDetailNwList) {
        this.employeeDetailNwList = employeeDetailNwList;
    }
}
