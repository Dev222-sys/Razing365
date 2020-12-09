package com.razinggroups.presentation.ui.employeeTask.employeeTaskDetail;

import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;

public interface EmployeeTaskDetailNavigator {
    void onResponse(String message);

    void onUpdate(String message);

    void onDataLoaded(FetchEmployeeTask fetchEmployeeTask);

    void onError(String message);
}
