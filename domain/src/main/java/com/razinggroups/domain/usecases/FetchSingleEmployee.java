package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class FetchSingleEmployee extends SingleUseCase<EmployeeDetail, FetchSingleEmployee.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public FetchSingleEmployee(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<EmployeeDetail> buildUseCaseObservable(Params params) {
        return sampleRepository.fetchEmployee(params.id);
    }

    public static final class Params {

        long id;

        public Params(long id) {
            this.id = id;
        }

        public static FetchSingleEmployee.Params FetchSingleEmployee(long id) {
            return new FetchSingleEmployee.Params(id);
        }
    }
}