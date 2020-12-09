package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllEmployeeTasksUseCase extends SingleUseCase<FetchEmployeeTask, FetchAllEmployeeTasksUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllEmployeeTasksUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchEmployeeTask> buildUseCaseObservable(Params params) {
        if (params.type.equalsIgnoreCase("all"))
            return sampleRepository.fetchAllEmployeeTasks();
        else if (params.type.equalsIgnoreCase("active"))
            return sampleRepository.fetchActiveEmployeeTask();
        else if (params.type.equalsIgnoreCase("complete"))
            return sampleRepository.fetchCompleteEmployeeTask();

        return null;

    }

    public static final class Params {

        String type;

        public Params(String type) {
            this.type = type;
        }

        public static FetchAllEmployeeTasksUseCase.Params fetchAllEmployeeTasksUseCase(String type) {
            return new FetchAllEmployeeTasksUseCase.Params(type);
        }
    }
}
