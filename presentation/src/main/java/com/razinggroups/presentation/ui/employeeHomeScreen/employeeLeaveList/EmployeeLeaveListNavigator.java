package com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList;

import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave;

public interface EmployeeLeaveListNavigator {
    void onDataLoaded(FetchSingleEmployeeLeave fetchSingleEmployeeLeave);

    void onError(String toString);
}
