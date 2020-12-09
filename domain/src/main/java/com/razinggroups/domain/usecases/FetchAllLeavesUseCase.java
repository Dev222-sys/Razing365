package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.leave.FetchAllLeavesResponse;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllLeavesUseCase extends SingleUseCase<FetchAllLeavesResponse, FetchAllLeavesUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllLeavesUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchAllLeavesResponse> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchAllLeaves();
    }

    public static final class Params {
        public Params() {
        }

        public static FetchAllLeavesUseCase.Params FetchAllLeavesUseCase() {
            return new FetchAllLeavesUseCase.Params();
        }
    }
}