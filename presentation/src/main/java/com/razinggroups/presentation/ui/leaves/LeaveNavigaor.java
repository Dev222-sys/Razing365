package com.razinggroups.presentation.ui.leaves;

import com.razinggroups.domain.model.leave.FetchAllLeavesResponse;

public interface LeaveNavigaor {

    void onError(String toString);

    void onDataLoaded(FetchAllLeavesResponse fetchAllLeavesResponse);

    void onUpdateResponse(String message);
}
