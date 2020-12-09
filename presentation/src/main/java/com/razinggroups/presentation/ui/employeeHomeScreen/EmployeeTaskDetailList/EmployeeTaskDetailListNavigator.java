package com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeTaskDetailList;

import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;

public interface EmployeeTaskDetailListNavigator {
    void onError(String toString);

    void onDataLoaded(FetchEmployeeTask fetchEmployeeTask);

    void onUpdate(String toString);
}
