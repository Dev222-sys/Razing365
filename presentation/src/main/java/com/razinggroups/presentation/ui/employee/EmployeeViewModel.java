package com.razinggroups.presentation.ui.employee;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.usecases.FetchAllEmployeesUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeViewModel extends BaseViewModel<EmployeeNavigator> {
    com.razinggroups.domain.usecases.FetchAllEmployeesUseCase fetchAllEmployeesUseCase;

    public EmployeeViewModel(com.razinggroups.domain.usecases.FetchAllEmployeesUseCase fetchAllEmployeesUseCase) {
        this.fetchAllEmployeesUseCase = fetchAllEmployeesUseCase;
    }

    public void fetchAllEmployees(String type) {
        fetchAllEmployeesUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employee.EmployeeList>() {
            @Override
            public void onSuccess(EmployeeList employeeList) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(employeeList);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllEmployeesUseCase.Params.fetchAllEmployessUSeCase(type));
    }
}
