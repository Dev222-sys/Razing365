package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.Login;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class CustomerQueryUserCase extends SingleUseCase<Customer, CustomerQueryUserCase.Params> {

    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public CustomerQueryUserCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {

        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Customer> buildUseCaseObservable(Params params) {

        return sampleRepository.customer(params.userName, params.password);
    }

    public static final class Params {

        private String userName;
        private String password;

        public Params(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public static CustomerQueryUserCase.Params customerQueryUserCase(String userName, String password) {
            return new CustomerQueryUserCase.Params(userName, password);
        }
    }
}
