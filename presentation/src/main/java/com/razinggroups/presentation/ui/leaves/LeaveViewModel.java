package com.razinggroups.presentation.ui.leaves;

import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.leave.FetchAllLeavesResponse;
import com.razinggroups.domain.model.leave.UpdateLeave;
import com.razinggroups.domain.usecases.FetchAllLeavesUseCase;
import com.razinggroups.domain.usecases.UpdateLeaveUsecase;
import com.razinggroups.presentation.base.BaseViewModel;

import io.reactivex.observers.DisposableSingleObserver;

public class LeaveViewModel extends BaseViewModel<LeaveNavigaor> {

    FetchAllLeavesUseCase fetchAllLeavesUseCase;
    UpdateLeaveUsecase updateLeaveUsecase;

    public LeaveViewModel(FetchAllLeavesUseCase fetchAllLeavesUseCase, UpdateLeaveUsecase updateLeaveUsecase) {
        this.fetchAllLeavesUseCase = fetchAllLeavesUseCase;
        this.updateLeaveUsecase = updateLeaveUsecase;
    }

    public void fetchData() {
        fetchAllLeavesUseCase.execute(new DisposableSingleObserver<FetchAllLeavesResponse>() {
            @Override
            public void onSuccess(FetchAllLeavesResponse fetchAllLeavesResponse) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(fetchAllLeavesResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.toString());
            }
        }, FetchAllLeavesUseCase.Params.FetchAllLeavesUseCase());
    }

    public void updateLeave(UpdateLeave request) {
        updateLeaveUsecase.execute(new DisposableSingleObserver<Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onUpdateResponse(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onUpdateResponse(e.getMessage());
            }
        }, UpdateLeaveUsecase.Params.UpdateLeaveUsecase(request));
    }
}
