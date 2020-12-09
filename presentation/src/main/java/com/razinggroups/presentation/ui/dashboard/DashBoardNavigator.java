package com.razinggroups.presentation.ui.dashboard;

import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;

public interface DashBoardNavigator {
    void onError(String message);

    void onEmployeeListLoaded(EmployeeList employeeList, String type);

    void onEmployeeTaskLoaded(FetchEmployeeTask fetchEmployeeTask, String type);

    void onMyTaskLoaded(FetchAllMyTask fetchAllMyTask);
}
