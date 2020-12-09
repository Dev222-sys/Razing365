package com.razinggroups.presentation.ui.myTask.createMyTask;

import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.myTask.CreateMyTaskRequest;
import com.razinggroups.domain.usecases.CreateTaskUseCase;
import com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase;
import com.razinggroups.presentation.base.BaseViewModel;

import io.reactivex.observers.DisposableSingleObserver;

public class CreateMyTaskViewModel extends BaseViewModel<CreateMyTaskNavigator> {

    CreateTaskUseCase createTaskUseCase;
    FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase;

    public CreateMyTaskViewModel(CreateTaskUseCase createTaskUseCase, FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.fetchAllBrandDetailsUseCase = fetchAllBrandDetailsUseCase;
    }

    public void fetchBrand() {
        fetchAllBrandDetailsUseCase.execute(new DisposableSingleObserver<FetchAllBrandDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
                if(getNavigator()!=null)
                    getNavigator().onBrandLoaded(fetchAllBrandDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onErrorInBrand(e.getMessage());
            }
        }, FetchAllBrandDetailsUseCase.Params.FetchAllBrandDetailsUseCase());
    }

    public void create(CreateMyTaskRequest request, String userType) {
        createTaskUseCase.execute(new DisposableSingleObserver<Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onCreateResponse(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onCreateResponse(e.getMessage());
            }
        }, CreateTaskUseCase.Params.CreateTaskUseCase(userType, request, null));
    }
}
