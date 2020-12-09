package com.razinggroups.presentation.ui.vendor.vendorList;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.vendor.FetchAllVendorResponse;
import com.razinggroups.domain.usecases.FetchAllVendorUsecase;

import io.reactivex.observers.DisposableSingleObserver;

public class VendorListViewModel  extends BaseViewModel<VendorListNavigator> {

    com.razinggroups.domain.usecases.FetchAllVendorUsecase fetchAllVendorUsecase;

    public VendorListViewModel(com.razinggroups.domain.usecases.FetchAllVendorUsecase fetchAllVendorUsecase) {
        this.fetchAllVendorUsecase = fetchAllVendorUsecase;
    }
    public void fetchData()
    {
        fetchAllVendorUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.vendor.FetchAllVendorResponse>() {
            @Override
            public void onSuccess(FetchAllVendorResponse fetchAllVendorResponse) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(fetchAllVendorResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, FetchAllVendorUsecase.Params.FetchAllVendorUsecase());
    }
}
