package com.razinggroups.presentation.ui.employee;

import com.razinggroups.domain.model.employee.EmployeeList;

public interface EmployeeNavigator {
    void onError(String message);

    void onDataLoaded(EmployeeList employeeList);
}
