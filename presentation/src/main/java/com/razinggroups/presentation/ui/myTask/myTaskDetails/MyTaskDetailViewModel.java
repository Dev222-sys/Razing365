package com.razinggroups.presentation.ui.myTask.myTaskDetails;

import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchAllMyTaskUseCase;
import com.razinggroups.domain.usecases.UpdateTaskUsecase;
import com.razinggroups.presentation.base.BaseViewModel;

import io.reactivex.observers.DisposableSingleObserver;

public class MyTaskDetailViewModel extends BaseViewModel<MyTaskDetailNavigator> {

    DeleteUseCase deleteUseCase;
    UpdateTaskUsecase updateTaskUsecase;
    FetchAllMyTaskUseCase fetchAllMyTask;

    public MyTaskDetailViewModel(DeleteUseCase deleteUseCase, UpdateTaskUsecase updateTaskUsecase, FetchAllMyTaskUseCase fetchSingleEmployeeTasks) {
        this.deleteUseCase = deleteUseCase;
        this.updateTaskUsecase = updateTaskUsecase;
        this.fetchAllMyTask = fetchSingleEmployeeTasks;
    }

    public void fetchData(String userType) {
        fetchAllMyTask.execute(new DisposableSingleObserver<FetchAllMyTask>() {
            @Override
            public void onSuccess(FetchAllMyTask fetchAllEmployeeTask) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(fetchAllEmployeeTask);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, FetchAllMyTaskUseCase.Params.fetchAllMyTaskUseCase(userType));
    }

    public void deleteTask(String id, String userType) {
        deleteUseCase.execute(new DisposableSingleObserver<Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onResponse(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onResponse(e.getMessage());
            }
        }, DeleteUseCase.Params.DeleteUseCase(id, userType));
    }

    public void updateTask(String id, String userType) {
        updateTaskUsecase.execute(new DisposableSingleObserver<Message>() {
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
        }, UpdateTaskUsecase.Params.UpdateTaskUsecase(id, userType));
    }
}
