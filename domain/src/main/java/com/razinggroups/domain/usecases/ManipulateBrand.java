package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.brand.CreateBrandRequest;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class ManipulateBrand extends SingleUseCase<Message, ManipulateBrand.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public ManipulateBrand(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        if (params.type.equalsIgnoreCase("create"))
            return sampleRepository.createBrand(params.resposne);
        else if (params.type.equalsIgnoreCase("update"))
            return sampleRepository.updateBrand(params.resposne);

        return null;
    }

    public static final class Params {
        CreateBrandRequest resposne;
        String type;

        public Params(CreateBrandRequest resposne, String type) {
            this.resposne = resposne;
            this.type = type;
        }

        public static ManipulateBrand.Params ManipulateBrand(CreateBrandRequest resposne, String type) {
            return new ManipulateBrand.Params(resposne, type);
        }
    }
}