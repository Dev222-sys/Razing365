package com.razinggroups.presentation.ui.vendor.manipulateVendor;

import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.vendor.FetchSingleVendorResposne;

public interface ManipulateVendorNavigator {
    void onVendorLoaded(FetchSingleVendorResposne fetchSingleVendorResposne);

    void onError(String message);

    void showDialog(String message);

    void onBrandLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse);

}
