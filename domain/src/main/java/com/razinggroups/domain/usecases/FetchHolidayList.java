package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.holiday.HolidayRecordResponse;
import com.razinggroups.domain.repository.SampleRepository;

import java.util.List;

import io.reactivex.Single;

public class FetchHolidayList extends SingleUseCase<List<HolidayRecordResponse>, FetchHolidayList.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchHolidayList(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<List<HolidayRecordResponse>> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchHolidayList(params.city);
    }

    public static final class Params {
        String city;

        public Params(String city) {
            this.city = city;
        }

        public static FetchHolidayList.Params fetchHolidayList(String city) {
            return new FetchHolidayList.Params(city);
        }
    }
}
