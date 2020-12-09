package com.razinggroups.presentation.ui.employeeTask;

import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;

public interface EmployeeTaskNavigator {
    void onListLoaded(FetchEmployeeTask fetchEmployeeTask);

    void onError(String toString);
}
