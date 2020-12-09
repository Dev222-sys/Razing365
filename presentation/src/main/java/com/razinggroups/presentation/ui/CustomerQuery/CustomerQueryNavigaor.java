package com.razinggroups.presentation.ui.CustomerQuery;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.CustomerQuery.FetchAllCustomerQuerryResponse;

public interface CustomerQueryNavigaor {

    void onLoginSuccess(Customer customer);
    void onError(String toString);
    void onDataLoaded(FetchAllCustomerQuerryResponse fetchAllLeavesResponse);

    void onUpdateResponse(String message);

}
