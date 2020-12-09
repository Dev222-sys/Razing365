package com.razinggroups.presentation.ui.vendor.vendorTaskList;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.vendorTask.FetchVendorTaks;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchAllVendorTaskUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class VendorTaskListViewModel extends BaseViewModel<VendorTaskListNavigator> {

    com.razinggroups.domain.usecases.FetchAllVendorTaskUseCase fetchAllVendorTaskUseCase;

    com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase;

    public VendorTaskListViewModel(com.razinggroups.domain.usecases.FetchAllVendorTaskUseCase fetchAllVendorTaskUseCase, com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase) {
        this.fetchAllVendorTaskUseCase = fetchAllVendorTaskUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    public void fetchData() {
        fetchAllVendorTaskUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.vendorTask.FetchVendorTaks>() {
            @Override
            public void onSuccess(FetchVendorTaks fetchVendorTaks) {
                if (getNavigator() != null)
                    getNavigator().onDataLoaded(fetchVendorTaks);
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllVendorTaskUseCase.Params.FetchAllVendorTaskUseCase());
    }

    public void delete(String id) {
        deleteUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if (getNavigator() != null)
                    getNavigator().onDelete(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if (getNavigator() != null)
                    getNavigator().onError(e.getMessage());

            }
        }, DeleteUseCase.Params.DeleteUseCase(id, "vendorTask"));
    }
}
