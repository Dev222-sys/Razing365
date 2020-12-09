package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.vendorTask.FetchVendorTaks;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllVendorTaskUseCase extends SingleUseCase<FetchVendorTaks, FetchAllVendorTaskUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllVendorTaskUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchVendorTaks> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchAllVendorTask();
    }

    public static final class Params {
        public Params() {
        }

        public static FetchAllVendorTaskUseCase.Params FetchAllVendorTaskUseCase() {
            return new FetchAllVendorTaskUseCase.Params();
        }
    }
}

