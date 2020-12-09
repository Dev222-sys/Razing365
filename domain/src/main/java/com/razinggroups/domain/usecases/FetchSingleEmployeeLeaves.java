package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchSingleEmployeeLeaves extends SingleUseCase<FetchSingleEmployeeLeave, FetchSingleEmployeeLeaves.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchSingleEmployeeLeaves(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchSingleEmployeeLeave> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchEmployeeLeaves(params.id);
    }

    public static final class Params {
        long id;

        public Params(long id) {
            this.id = id;
        }

        public static FetchSingleEmployeeLeaves.Params FetchEmployeeLeaves(long id) {
            return new FetchSingleEmployeeLeaves.Params(id);
        }
    }
}
