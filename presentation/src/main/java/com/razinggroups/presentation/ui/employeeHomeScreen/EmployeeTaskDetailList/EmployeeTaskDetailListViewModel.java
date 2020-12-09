package com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeTaskDetailList;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.usecases.FetchSingleEmployeeTasks;
import com.razinggroups.domain.usecases.UpdateTaskUsecase;

import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeTaskDetailListViewModel extends BaseViewModel<EmployeeTaskDetailListNavigator> {
    com.razinggroups.domain.usecases.FetchSingleEmployeeTasks fetchSingleEmployeeTasks;
    com.razinggroups.domain.usecases.UpdateTaskUsecase taskUsecase;

    public EmployeeTaskDetailListViewModel(com.razinggroups.domain.usecases.FetchSingleEmployeeTasks fetchSingleEmployeeTasks, com.razinggroups.domain.usecases.UpdateTaskUsecase taskUsecase) {
        this.fetchSingleEmployeeTasks = fetchSingleEmployeeTasks;
        this.taskUsecase = taskUsecase;
    }

    public void fetchData(long employeeId) {
        fetchSingleEmployeeTasks.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public void onSuccess(FetchEmployeeTask fetchEmployeeTask) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(fetchEmployeeTask);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.toString());
            }
        }, FetchSingleEmployeeTasks.Params.FetchSingleEmployeeTasks(employeeId));
    }

    public void updateTask(String id) {
        taskUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(e.toString());
            }
        }, UpdateTaskUsecase.Params.UpdateTaskUsecase(id, "employee"));
    }
}
