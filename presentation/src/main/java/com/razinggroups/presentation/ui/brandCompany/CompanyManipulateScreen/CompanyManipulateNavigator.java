package com.razinggroups.presentation.ui.brandCompany.CompanyManipulateScreen;

import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;

public interface CompanyManipulateNavigator {
    void onUpdate(Message message);

    void onError(String message);

    void onDataloaded(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse);
}
