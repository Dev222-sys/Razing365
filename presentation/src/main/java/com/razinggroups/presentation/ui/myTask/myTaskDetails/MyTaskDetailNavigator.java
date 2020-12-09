package com.razinggroups.presentation.ui.myTask.myTaskDetails;

import com.razinggroups.domain.model.myTask.FetchAllMyTask;

public interface MyTaskDetailNavigator {
    void onDataLoaded(FetchAllMyTask fetchAllEmployeeTask);

    void onError(String message);

    void onResponse(String message);

    void onUpdate(String message);
}
