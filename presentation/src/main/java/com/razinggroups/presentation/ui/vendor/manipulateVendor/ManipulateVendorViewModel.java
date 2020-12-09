package com.razinggroups.presentation.ui.vendor.manipulateVendor;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.vendor.FetchSingleVendorResposne;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase;
import com.razinggroups.domain.usecases.FetchSingleVendorUsecase;
import com.razinggroups.domain.usecases.FetchVendorServiceUseCase;
import com.razinggroups.domain.usecases.ManipulateVendor;

import io.reactivex.observers.DisposableSingleObserver;

public class ManipulateVendorViewModel extends BaseViewModel<ManipulateVendorNavigator> {

    com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase;
    com.razinggroups.domain.usecases.ManipulateVendor manipulateVendor;
    com.razinggroups.domain.usecases.FetchSingleVendorUsecase fetchSingleVendorUsecase;
    com.razinggroups.domain.usecases.FetchVendorServiceUseCase fetchVendorServiceUseCase;
    com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase;

    public ManipulateVendorViewModel(com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase, com.razinggroups.domain.usecases.ManipulateVendor manipulateVendor, com.razinggroups.domain.usecases.FetchSingleVendorUsecase fetchSingleVendorUsecase, FetchVendorServiceUseCase fetchVendorServiceUseCase, com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {
        this.deleteUseCase = deleteUseCase;
        this.manipulateVendor = manipulateVendor;
        this.fetchSingleVendorUsecase = fetchSingleVendorUsecase;
        this.fetchVendorServiceUseCase = fetchVendorServiceUseCase;
        this.fetchAllBrandDetailsUseCase = fetchAllBrandDetailsUseCase;
    }

    public void fetchVendor(long id) {
        fetchSingleVendorUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.vendor.FetchSingleVendorResposne>() {
            @Override
            public void onSuccess(com.razinggroups.domain.model.vendor.FetchSingleVendorResposne fetchSingleVendorResposne) {
                if (getNavigator() != null)
                    getNavigator().onVendorLoaded(fetchSingleVendorResposne);
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchSingleVendorUsecase.Params.FetchSingleVendorUsecase(id));
    }

    public void manipulate(FetchSingleVendorResposne resposne, String type) {
        manipulateVendor.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(com.razinggroups.domain.model.Message message) {
                if (getNavigator() != null)
                    getNavigator().showDialog(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());

            }
        }, ManipulateVendor.Params.ManipulateVendor(resposne, type));
    }

    public void delete(String vendorId) {
        deleteUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if (getNavigator() != null)
                    getNavigator().showDialog(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());

            }
        }, DeleteUseCase.Params.DeleteUseCase(vendorId, "vendor"));
    }

    public void fetchBrands() {
        fetchAllBrandDetailsUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
                if (getNavigator() != null)
                    getNavigator().onBrandLoaded(fetchAllBrandDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());

            }
        }, FetchAllBrandDetailsUseCase.Params.FetchAllBrandDetailsUseCase());
    }
}
