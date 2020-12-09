package com.razinggroups.presentation.ui.myTask;

import com.razinggroups.domain.model.myTask.FetchAllMyTask;

public interface MyTaskNavigator {
    void onDataLoaded(FetchAllMyTask fetchAllMyTask);

    void onError(String e);
}
