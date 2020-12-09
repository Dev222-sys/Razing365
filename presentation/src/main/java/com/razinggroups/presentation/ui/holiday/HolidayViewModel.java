package com.razinggroups.presentation.ui.holiday;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.holiday.HolidayRecordResponse;
import com.razinggroups.domain.usecases.FetchHolidayList;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class HolidayViewModel extends BaseViewModel<HolidayNavigator> {

    com.razinggroups.domain.usecases.FetchHolidayList fetchHolidayList;

    public HolidayViewModel(com.razinggroups.domain.usecases.FetchHolidayList fetchHolidayList) {
        this.fetchHolidayList = fetchHolidayList;
    }

    public void fetchList(String city) {
        fetchHolidayList.execute(new DisposableSingleObserver<List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>>() {
            @Override
            public void onSuccess(List<HolidayRecordResponse> holidayRecordResponses) {
                if(getNavigator()!=null)
                    getNavigator().onListLoaded(holidayRecordResponses);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.toString());

            }
        }, FetchHolidayList.Params.fetchHolidayList(city));
    }
}
