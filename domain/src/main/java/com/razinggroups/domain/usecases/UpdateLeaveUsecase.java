package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.leave.UpdateLeave;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class UpdateLeaveUsecase extends SingleUseCase<Message, UpdateLeaveUsecase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public UpdateLeaveUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        return sampleRepository.updateLeave(params.request);
    }

    public static final class Params {
        UpdateLeave request;

        public Params(UpdateLeave request) {
            this.request = request;
        }

        public static UpdateLeaveUsecase.Params UpdateLeaveUsecase(UpdateLeave request) {
            return new UpdateLeaveUsecase.Params(request);
        }
    }
}