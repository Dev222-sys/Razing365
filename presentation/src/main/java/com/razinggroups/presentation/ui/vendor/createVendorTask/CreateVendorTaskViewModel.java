package com.razinggroups.presentation.ui.vendor.createVendorTask;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse;
import com.razinggroups.domain.model.vendor.FetchVendorServiceResponse;
import com.razinggroups.domain.model.vendorTask.CreateVendorTask;
import com.razinggroups.domain.usecases.CreateVendorTaskUsecase;
import com.razinggroups.domain.usecases.FetchAllVendorBrandsUseCase;
import com.razinggroups.domain.usecases.FetchVendorServiceUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class CreateVendorTaskViewModel extends BaseViewModel<CreateVendorTaskNavigator> {

    com.razinggroups.domain.usecases.CreateVendorTaskUsecase createVendorTaskUsecase;
    com.razinggroups.domain.usecases.FetchVendorServiceUseCase fetchVendorServiceUseCase;
    com.razinggroups.domain.usecases.FetchAllVendorBrandsUseCase fetchAllVendorBrandsUseCase;

    public CreateVendorTaskViewModel(com.razinggroups.domain.usecases.CreateVendorTaskUsecase createVendorTaskUsecase, com.razinggroups.domain.usecases.FetchVendorServiceUseCase fetchVendorServiceUseCase, com.razinggroups.domain.usecases.FetchAllVendorBrandsUseCase fetchAllVendorBrandsUseCase) {
        this.createVendorTaskUsecase = createVendorTaskUsecase;
        this.fetchVendorServiceUseCase = fetchVendorServiceUseCase;
        this.fetchAllVendorBrandsUseCase = fetchAllVendorBrandsUseCase;
    }


    public void fetchData() {
        fetchAllVendorBrandsUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse>() {
            @Override
            public void onSuccess(FetchAllVendorBrandResponse fetchAllVendorBrandResponse) {
                if(getNavigator()!=null)
                    getNavigator().onBrandsLoaded(fetchAllVendorBrandResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().showDailog(e.getMessage());

            }
        }, FetchAllVendorBrandsUseCase.Params.FetchAllVendorBrandsUseCase());
    }

    public void fetchVendorService(long id) {
        fetchVendorServiceUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.vendor.FetchVendorServiceResponse>() {
            @Override
            public void onSuccess(FetchVendorServiceResponse fetchVendorServiceResponse) {
                if(getNavigator()!=null)
                    getNavigator().onServiceLoaded(fetchVendorServiceResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().showDailog(e.getMessage());

            }
        }, FetchVendorServiceUseCase.Params.FetchVendorServiceUseCase(id));
    }

    public void createTask(CreateVendorTask request) {
        createVendorTaskUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().showDailog(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().showDailog(e.getMessage());

            }
        }, CreateVendorTaskUsecase.Params.CreateVendorTaskUsecase(request));
    }
}
