package com.razinggroups.presentation.ui.myTask.createMyTask;

import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;

public interface CreateMyTaskNavigator {
    void onCreateResponse(String message);

    void onBrandLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse);

    void onErrorInBrand(String message);
}
