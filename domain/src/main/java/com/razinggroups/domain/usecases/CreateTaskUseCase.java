package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.employeeTask.CreateEmployeeTaskRequest;
import com.razinggroups.domain.model.myTask.CreateMyTaskRequest;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class CreateTaskUseCase extends SingleUseCase<Message, CreateTaskUseCase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public CreateTaskUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        if (params.type.equalsIgnoreCase("employee")) {
            return sampleRepository.createEmployeeTask(params.employeeTaskRequest);
        } else if (params.type.equalsIgnoreCase("Admin")) {
            return sampleRepository.createAdminMyTask(params.request);
        } else if (params.type.equalsIgnoreCase("MasterAdmin")) {
            return sampleRepository.createMyTask(params.request);
        }else if (params.type.equalsIgnoreCase("EmployeeMyTask")) {
            return sampleRepository.createEmployeeMyTask(params.request);
        }

        return null;
    }

    public static final class Params {
        String type;
        CreateMyTaskRequest request;
        CreateEmployeeTaskRequest employeeTaskRequest;

        public Params(String type, CreateMyTaskRequest request, CreateEmployeeTaskRequest employeeTaskRequest) {
            this.type = type;
            this.request = request;
            this.employeeTaskRequest = employeeTaskRequest;
        }

        public static CreateTaskUseCase.Params CreateTaskUseCase(String type, CreateMyTaskRequest request, CreateEmployeeTaskRequest employeeTaskRequest) {
            return new CreateTaskUseCase.Params(type, request, employeeTaskRequest);
        }
    }
}
