package com.razinggroups.presentation.ui.holiday;

import com.razinggroups.domain.model.holiday.HolidayRecordResponse;

import java.util.List;

public interface HolidayNavigator {
    void onError(String toString);

    void onListLoaded(List<HolidayRecordResponse> holidayRecordResponses);
}
