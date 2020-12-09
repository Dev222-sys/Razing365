package com.razinggroups.presentation.ui.myTask;

import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.domain.usecases.FetchAllMyTaskUseCase;
import com.razinggroups.presentation.base.BaseViewModel;

import io.reactivex.observers.DisposableSingleObserver;

public class MyTaskViewModel extends BaseViewModel<MyTaskNavigator> {

    FetchAllMyTaskUseCase fetchAllMyTaskUseCase;

    public MyTaskViewModel(FetchAllMyTaskUseCase fetchAllMyTaskUseCase) {
        this.fetchAllMyTaskUseCase = fetchAllMyTaskUseCase;
    }

    public void fetchMyTasks(String userType) {
        fetchAllMyTaskUseCase.execute(new DisposableSingleObserver<FetchAllMyTask>() {
            @Override
            public void onSuccess(FetchAllMyTask fetchAllMyTask) {
                if (getNavigator() != null)
                    getNavigator().onDataLoaded(fetchAllMyTask);
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.toString());
            }
        }, FetchAllMyTaskUseCase.Params.fetchAllMyTaskUseCase(userType));

    }
}
