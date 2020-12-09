package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.SingleIdRequest;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class UpdateTaskUsecase extends SingleUseCase<Message, UpdateTaskUsecase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public UpdateTaskUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        SingleIdRequest request = new SingleIdRequest(params.id);
        if (params.type.equalsIgnoreCase("employee"))
            return sampleRepository.updateEmployeeTask(request);
        else if (params.type.equalsIgnoreCase("MasterAdmin"))
            return sampleRepository.updateMyTask(request);
        else if (params.type.equalsIgnoreCase("Admin"))
            return sampleRepository.updateAdminMyTask(request);
        else if (params.type.equalsIgnoreCase("EmployeeMyTask"))
            return sampleRepository.updateEmployeeMyTask(request);

        return null;
    }

    public static final class Params {
        String id;
        String type;

        public Params(String id, String type) {
            this.id = id;
            this.type = type;
        }

        public static UpdateTaskUsecase.Params UpdateTaskUsecase(String id, String type) {
            return new UpdateTaskUsecase.Params(id, type);
        }
    }
}
