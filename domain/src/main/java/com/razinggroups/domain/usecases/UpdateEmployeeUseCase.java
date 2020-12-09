package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class UpdateEmployeeUseCase  extends SingleUseCase<Message, UpdateEmployeeUseCase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public UpdateEmployeeUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        return sampleRepository.updateEmployee(params.request);
    }

    public static final class Params {
        EmployeeDetail request;

        public Params(EmployeeDetail request) {
            this.request = request;
        }

        public static UpdateEmployeeUseCase.Params UpdateEmployeeUseCase(EmployeeDetail request) {
            return new UpdateEmployeeUseCase.Params(request);
        }
    }
}
