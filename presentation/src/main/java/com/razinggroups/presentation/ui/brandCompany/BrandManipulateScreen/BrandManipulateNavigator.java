package com.razinggroups.presentation.ui.brandCompany.BrandManipulateScreen;

import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;

public interface BrandManipulateNavigator {
    void onDataloaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse);

    void onError(String message);

    void onUpdate(Message message);

    void onCompanyloaded(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse);
}
