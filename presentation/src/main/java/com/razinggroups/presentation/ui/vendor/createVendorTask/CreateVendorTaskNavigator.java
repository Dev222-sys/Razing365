package com.razinggroups.presentation.ui.vendor.createVendorTask;

import com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse;
import com.razinggroups.domain.model.vendor.FetchVendorServiceResponse;

public interface CreateVendorTaskNavigator {
    void showDailog(String message);

    void onBrandsLoaded(FetchAllVendorBrandResponse fetchAllVendorBrandResponse);

    void onServiceLoaded(FetchVendorServiceResponse fetchVendorServiceResponse);

}
