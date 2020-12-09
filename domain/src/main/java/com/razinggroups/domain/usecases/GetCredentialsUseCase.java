package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Credentials;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class GetCredentialsUseCase extends SingleUseCase<Credentials, GetCredentialsUseCase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public GetCredentialsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Credentials> buildUseCaseObservable(Params params) {
        return sampleRepository.getCredentials();
    }

    public static final class Params {


        public Params() {
        }

        public static GetCredentialsUseCase.Params GetCredentialsUseCase() {
            return new GetCredentialsUseCase.Params();
        }
    }
}