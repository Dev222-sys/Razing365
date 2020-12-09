package com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave;
import com.razinggroups.domain.usecases.FetchSingleEmployeeLeaves;

import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeLeaveListViewModel extends BaseViewModel<EmployeeLeaveListNavigator> {

    com.razinggroups.domain.usecases.FetchSingleEmployeeLeaves fetchSingleEmployeeLeaves;

    public EmployeeLeaveListViewModel(com.razinggroups.domain.usecases.FetchSingleEmployeeLeaves fetchSingleEmployeeLeaves) {
        this.fetchSingleEmployeeLeaves = fetchSingleEmployeeLeaves;
    }

    public void fetchData(long id) {
        fetchSingleEmployeeLeaves.execute(new DisposableSingleObserver<com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave>() {
            @Override
            public void onSuccess(FetchSingleEmployeeLeave fetchSingleEmployeeLeave) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(fetchSingleEmployeeLeave);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.toString());
            }
        }, FetchSingleEmployeeLeaves.Params.FetchEmployeeLeaves(id));
    }
}
