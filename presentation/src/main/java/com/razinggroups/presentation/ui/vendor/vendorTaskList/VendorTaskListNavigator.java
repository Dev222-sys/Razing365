package com.razinggroups.presentation.ui.vendor.vendorTaskList;

import com.razinggroups.domain.model.vendorTask.FetchVendorTaks;

public interface VendorTaskListNavigator {
    void onDelete(String message);

    void onDataLoaded(FetchVendorTaks fetchVendorTaks);

    void onError(String message);
}
