package com.razinggroups.presentation.ui.employee.EditEmployee;

import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.employee.EmployeeDetail;

public interface EditEmployeeNavigator {
    void onError(String message);

    void onDataLoaded(EmployeeDetail employeeList);

    void onUpdate(String message);

    void onBrandLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse);

}
