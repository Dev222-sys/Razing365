package com.razinggroups.presentation.ui.vendor.vendorList;

import com.razinggroups.domain.model.vendor.FetchAllVendorResponse;

public interface VendorListNavigator {
    void onDataLoaded(FetchAllVendorResponse fetchAllVendorResponse);

    void onError(String message);
}
