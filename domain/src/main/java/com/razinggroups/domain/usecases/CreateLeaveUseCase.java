package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.leave.CreateLeaveRequest;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class CreateLeaveUseCase extends SingleUseCase<Message, CreateLeaveUseCase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public CreateLeaveUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        return sampleRepository.createLeave(params.request);
    }

    public static final class Params {
        CreateLeaveRequest request;

        public Params(CreateLeaveRequest request) {
            this.request = request;
        }

        public static CreateLeaveUseCase.Params CreateLeaveUseCase(CreateLeaveRequest request) {
            return new CreateLeaveUseCase.Params(request);
        }
    }
}