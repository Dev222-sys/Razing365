package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllBrandDetailsUseCase extends SingleUseCase<FetchAllBrandDetailsResponse, FetchAllBrandDetailsUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllBrandDetailsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchAllBrandDetailsResponse> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchAllBrandDetails();
    }

    public static final class Params {
        public Params() {
        }

        public static FetchAllBrandDetailsUseCase.Params FetchAllBrandDetailsUseCase() {
            return new FetchAllBrandDetailsUseCase.Params();
        }
    }
}