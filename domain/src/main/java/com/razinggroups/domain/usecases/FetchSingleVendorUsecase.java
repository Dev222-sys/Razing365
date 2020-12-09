package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.vendor.FetchSingleVendorResposne;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchSingleVendorUsecase extends SingleUseCase<FetchSingleVendorResposne, FetchSingleVendorUsecase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchSingleVendorUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchSingleVendorResposne> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchSingleVendor(params.id);
    }

    public static final class Params {

        long id;

        public Params(long id) {
            this.id = id;
        }

        public static FetchSingleVendorUsecase.Params FetchSingleVendorUsecase(long id) {
            return new FetchSingleVendorUsecase.Params(id);
        }
    }
}
