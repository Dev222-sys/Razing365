package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchAllEmployeesUseCase extends SingleUseCase<EmployeeList, FetchAllEmployeesUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchAllEmployeesUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<EmployeeList> buildUseCaseObservable(Params params) {

        if (params.type.equalsIgnoreCase("all"))
            return sampleRepository.fetchAllEmployess();
        else if (params.type.equalsIgnoreCase("online"))
            return sampleRepository.fetchOnlineEmployees();

        return null;
    }

    public static final class Params {

        String type;

        public Params(String type) {
            this.type = type;
        }

        public static FetchAllEmployeesUseCase.Params fetchAllEmployessUSeCase(String type) {
            return new FetchAllEmployeesUseCase.Params(type);
        }
    }
}