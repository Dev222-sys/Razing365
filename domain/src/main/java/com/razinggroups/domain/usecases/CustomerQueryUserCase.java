package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class CustomerQueryUserCase extends SingleUseCase<Message, CustomerQueryUserCase.Params> {

    SampleRepository sampleRepository;

    public CustomerQueryUserCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {

        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {

        return sampleRepository.customer(params.request);
    }

    public static final class Params {
        Customer request;

        public Params(Customer request) {
            this.request = request;
        }

        public static CustomerQueryUserCase.Params CustomerQueryUseCase(Customer request) {
            return new CustomerQueryUserCase.Params(request);
        }
    }
}
