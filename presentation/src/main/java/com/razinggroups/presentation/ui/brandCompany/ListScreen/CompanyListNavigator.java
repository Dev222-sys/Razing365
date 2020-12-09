package com.razinggroups.presentation.ui.brandCompany.ListScreen;

import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;

public interface CompanyListNavigator {
    void onCompanyDataLoaded(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse);

    void onError(String message);

    void getBrandDataLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse);
}
