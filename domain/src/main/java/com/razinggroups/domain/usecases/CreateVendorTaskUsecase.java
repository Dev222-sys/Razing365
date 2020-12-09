package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.vendorTask.CreateVendorTask;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class CreateVendorTaskUsecase extends SingleUseCase<Message, CreateVendorTaskUsecase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public CreateVendorTaskUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        return sampleRepository.createVendorTask(params.request);
    }

    public static final class Params {
        CreateVendorTask request;

        public Params(CreateVendorTask request) {
            this.request = request;
        }

        public static CreateVendorTaskUsecase.Params CreateVendorTaskUsecase(CreateVendorTask request) {
            return new CreateVendorTaskUsecase.Params(request);
        }
    }
}
