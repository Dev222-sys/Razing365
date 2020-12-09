package com.razinggroups.presentation.ui.dashboard;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.domain.usecases.FetchAllEmployeeTasksUseCase;
import com.razinggroups.domain.usecases.FetchAllEmployeesUseCase;
import com.razinggroups.domain.usecases.FetchAllMyTaskUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class DashBoardViewModel extends BaseViewModel<DashBoardNavigator> {

    com.razinggroups.domain.usecases.FetchAllEmployeesUseCase fetchAllEmployeesUseCase;
    com.razinggroups.domain.usecases.FetchAllMyTaskUseCase fetchAllMyTaskUseCase;
    com.razinggroups.domain.usecases.FetchAllEmployeeTasksUseCase fetchAllEmployeeTasksUseCase;

    public DashBoardViewModel(com.razinggroups.domain.usecases.FetchAllEmployeesUseCase fetchAllEmployeesUseCase, com.razinggroups.domain.usecases.FetchAllMyTaskUseCase fetchAllMyTaskUseCase, com.razinggroups.domain.usecases.FetchAllEmployeeTasksUseCase fetchAllEmployeeTasksUseCase) {
        this.fetchAllEmployeesUseCase = fetchAllEmployeesUseCase;
        this.fetchAllMyTaskUseCase = fetchAllMyTaskUseCase;
        this.fetchAllEmployeeTasksUseCase = fetchAllEmployeeTasksUseCase;
    }

    public void fetchAllEmployees(String type) {
        fetchAllEmployeesUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employee.EmployeeList>() {
            @Override
            public void onSuccess(EmployeeList employeeList) {
                if (getNavigator() != null)
                    getNavigator().onEmployeeListLoaded(employeeList, type);

            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllEmployeesUseCase.Params.fetchAllEmployessUSeCase(type));
    }

    public void fetchEmployeeTask(String type) {
        fetchAllEmployeeTasksUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public void onSuccess(FetchEmployeeTask fetchEmployeeTask) {
                if (getNavigator() != null)
                    getNavigator().onEmployeeTaskLoaded(fetchEmployeeTask, type);
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllEmployeeTasksUseCase.Params.fetchAllEmployeeTasksUseCase(type));
    }


    public void fetchAllMyTasks(String userType) {
        fetchAllMyTaskUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.myTask.FetchAllMyTask>() {
            @Override
            public void onSuccess(FetchAllMyTask fetchAllMyTask) {
                if (getNavigator() != null)
                    getNavigator().onMyTaskLoaded(fetchAllMyTask);

            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllMyTaskUseCase.Params.fetchAllMyTaskUseCase(userType));
    }

}
