package com.razinggroups.presentation.ui.employeeTask;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.usecases.FetchAllEmployeeTasksUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeTaskViewModel extends BaseViewModel<EmployeeTaskNavigator> {

    com.razinggroups.domain.usecases.FetchAllEmployeeTasksUseCase fetchAllEmployeeTasksUseCase;

    public EmployeeTaskViewModel(com.razinggroups.domain.usecases.FetchAllEmployeeTasksUseCase fetchAllEmployeeTasksUseCase) {
        this.fetchAllEmployeeTasksUseCase = fetchAllEmployeeTasksUseCase;
    }

    public void fetchAllTasks(String str) {
        fetchAllEmployeeTasksUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public void onSuccess(FetchEmployeeTask fetchEmployeeTask) {
                if (getNavigator() != null)
                    getNavigator().onListLoaded(fetchEmployeeTask);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.toString());
            }

        }, FetchAllEmployeeTasksUseCase.Params.fetchAllEmployeeTasksUseCase(str));
    }
}
