package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class SetCredentialsUseCase extends SingleUseCase<Boolean, SetCredentialsUseCase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public SetCredentialsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Boolean> buildUseCaseObservable(Params params) {
        return sampleRepository.setCredentials(params.userName, params.password);
    }

    public static final class Params {

        String userName, password;

        public Params(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public static SetCredentialsUseCase.Params SetCredentialsUseCase(String userName, String password) {
            return new SetCredentialsUseCase.Params(userName, password);
        }
    }
}
