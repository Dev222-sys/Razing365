package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Login;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class LoginUseCase extends SingleUseCase<Login, LoginUseCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public LoginUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }


    @Override
    public Single<Login> buildUseCaseObservable(Params params) {
        return sampleRepository.login(params.userName, params.password);
    }

    public static final class Params {

        private String userName;
        private String password;

        public Params(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public static LoginUseCase.Params loginUseCase(String userName, String password) {
            return new LoginUseCase.Params(userName, password);
        }
    }
}
