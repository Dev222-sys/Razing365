package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.vendor.FetchVendorServiceResponse;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchVendorServiceUseCase extends SingleUseCase<FetchVendorServiceResponse, FetchVendorServiceUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchVendorServiceUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchVendorServiceResponse> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchVendorService(params.id);
    }

    public static final class Params {

        long id;

        public Params(long id) {
            this.id = id;
        }

        public static FetchVendorServiceUseCase.Params FetchVendorServiceUseCase(long id) {
            return new FetchVendorServiceUseCase.Params(id);
        }
    }
}

