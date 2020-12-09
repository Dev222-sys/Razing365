package com.razinggroups.presentation.ui.createTask;

import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.employee.EmployeeList;

public interface CreateTaskNavigator {
    void onError(String message);

    void onCreateResponse(String message);

    void onEmployeesLoaded(EmployeeList employeeList);

    void onBrandLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse);
}
