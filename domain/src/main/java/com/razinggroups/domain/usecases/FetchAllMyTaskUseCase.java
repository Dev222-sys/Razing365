package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllMyTaskUseCase extends SingleUseCase<FetchAllMyTask, FetchAllMyTaskUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllMyTaskUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<FetchAllMyTask> buildUseCaseObservable(Params params) {
        if (params.type.equalsIgnoreCase("MasterAdmin"))
            return sampleRepository.fetchAllMyTask();
        else if (params.type.equalsIgnoreCase("Admin"))
            return sampleRepository.fetchAdminAllMyTask();
        else if (params.type.equalsIgnoreCase("Employee"))
            return sampleRepository.fetchAllMyTask();
        else if(params.type.equalsIgnoreCase("EmployeeMyTask"))
            return sampleRepository.fetchEmployeeAllMyTask();
        return null;
    }

    public static final class Params {

        String type;

        public Params(String type) {
            this.type = type;
        }

        public static FetchAllMyTaskUseCase.Params fetchAllMyTaskUseCase(String type) {
            return new FetchAllMyTaskUseCase.Params(type);
        }
    }
}