package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.vendor.FetchAllVendorResponse;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllVendorUsecase extends SingleUseCase<FetchAllVendorResponse, FetchAllVendorUsecase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllVendorUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchAllVendorResponse> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchAllVendor();
    }

    public static final class Params {
        public Params() {
        }

        public static FetchAllVendorUsecase.Params FetchAllVendorUsecase() {
            return new FetchAllVendorUsecase.Params();
        }
    }
}
