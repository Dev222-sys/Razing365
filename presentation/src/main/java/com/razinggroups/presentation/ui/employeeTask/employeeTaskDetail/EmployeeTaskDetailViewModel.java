package com.razinggroups.presentation.ui.employeeTask.employeeTaskDetail;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchSingleEmployeeTasks;
import com.razinggroups.domain.usecases.UpdateTaskUsecase;

import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeTaskDetailViewModel extends BaseViewModel<EmployeeTaskDetailNavigator> {

    com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase;
    com.razinggroups.domain.usecases.UpdateTaskUsecase updateTaskUsecase;
    com.razinggroups.domain.usecases.FetchSingleEmployeeTasks fetchSingleEmployeeTasks;

    public EmployeeTaskDetailViewModel(com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase, com.razinggroups.domain.usecases.UpdateTaskUsecase updateTaskUsecase, com.razinggroups.domain.usecases.FetchSingleEmployeeTasks fetchSingleEmployeeTasks) {
        this.deleteUseCase = deleteUseCase;
        this.updateTaskUsecase = updateTaskUsecase;
        this.fetchSingleEmployeeTasks = fetchSingleEmployeeTasks;
    }

    public void fetchData(long empId) {
        fetchSingleEmployeeTasks.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public void onSuccess(FetchEmployeeTask fetchEmployeeTask) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(fetchEmployeeTask);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, FetchSingleEmployeeTasks.Params.FetchSingleEmployeeTasks(empId));
    }

    public void deleteTask(String id) {
        deleteUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(com.razinggroups.domain.model.Message message) {
                if(getNavigator()!=null)
                    getNavigator().onResponse(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onResponse(e.getMessage());
            }
        }, DeleteUseCase.Params.DeleteUseCase(id, "employeeTask"));
    }

    public void updateTask(String id) {
        updateTaskUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(e.getMessage());
            }
        }, UpdateTaskUsecase.Params.UpdateTaskUsecase(id, "employee"));
    }

}
