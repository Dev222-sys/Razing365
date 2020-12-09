package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.company.CreateCompanyRequest;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class ManipulateCompany extends SingleUseCase<Message, ManipulateCompany.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public ManipulateCompany(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        if (params.type.equalsIgnoreCase("create"))
            return sampleRepository.createCompany(params.resposne);
        else if (params.type.equalsIgnoreCase("update"))
            return sampleRepository.updateCompany(params.resposne);

        return null;
    }

    public static final class Params {
        CreateCompanyRequest resposne;
        String type;

        public Params(CreateCompanyRequest resposne, String type) {
            this.resposne = resposne;
            this.type = type;
        }

        public static ManipulateCompany.Params ManipulateCompany(CreateCompanyRequest resposne, String type) {
            return new ManipulateCompany.Params(resposne, type);
        }
    }
}