package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllVendorBrandsUseCase extends SingleUseCase<FetchAllVendorBrandResponse, FetchAllVendorBrandsUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllVendorBrandsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchAllVendorBrandResponse> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchAllVendorBrands();
    }

    public static final class Params {
        public Params() {
        }

        public static FetchAllVendorBrandsUseCase.Params FetchAllVendorBrandsUseCase() {
            return new FetchAllVendorBrandsUseCase.Params();
        }
    }
}
