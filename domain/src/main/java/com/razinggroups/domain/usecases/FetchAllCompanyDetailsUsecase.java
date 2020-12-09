package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllCompanyDetailsUsecase extends SingleUseCase<FetchAllCompanyDetailsResponse, FetchAllCompanyDetailsUsecase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllCompanyDetailsUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchAllCompanyDetailsResponse> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchAllCompanyDetails();
    }

    public static final class Params {
        public Params() {
        }

        public static FetchAllCompanyDetailsUsecase.Params FetchAllCompanyDetailsUsecase() {
            return new FetchAllCompanyDetailsUsecase.Params();
        }
    }
}