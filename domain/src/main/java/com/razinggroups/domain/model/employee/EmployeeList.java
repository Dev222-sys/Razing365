package com.razinggroups.domain.model.employee;

import java.util.List;

public class EmployeeList {

    public List<EmployeeDetail> employeeDetailList = null;

    public EmployeeList(List<EmployeeDetail> employeeDetailList) {
        this.employeeDetailList = employeeDetailList;
    }

    public List<EmployeeDetail> getEmployeeDetailList() {
        return employeeDetailList;
    }

    public void setEmployeeDetailList(List<EmployeeDetail> employeeDetailList) {
        this.employeeDetailList = employeeDetailList;
    }
}
