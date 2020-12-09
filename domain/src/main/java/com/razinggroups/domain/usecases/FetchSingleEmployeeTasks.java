package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchSingleEmployeeTasks extends SingleUseCase<FetchEmployeeTask, FetchSingleEmployeeTasks.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchSingleEmployeeTasks(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchEmployeeTask> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchSingleEmployeeTasks(params.id);
    }

    public static final class Params {
        long id;

        public Params(long id) {
            this.id = id;
        }

        public static FetchSingleEmployeeTasks.Params FetchSingleEmployeeTasks(long id) {
            return new FetchSingleEmployeeTasks.Params(id);
        }
    }
}
