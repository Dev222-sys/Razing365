package com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeApplyLeave;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.leave.CreateLeaveRequest;
import com.razinggroups.domain.usecases.CreateLeaveUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeApplyLeaveViewModel extends BaseViewModel<EmployeeApplyLeaveNavigator> {

    com.razinggroups.domain.usecases.CreateLeaveUseCase createLeaveUseCase;

    public EmployeeApplyLeaveViewModel(com.razinggroups.domain.usecases.CreateLeaveUseCase createLeaveUseCase) {
        this.createLeaveUseCase = createLeaveUseCase;
    }

    public void postData(CreateLeaveRequest request) {
        createLeaveUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onDataPosted(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onDataPosted(e.getMessage());
            }
        }, CreateLeaveUseCase.Params.CreateLeaveUseCase(request));
    }

}
