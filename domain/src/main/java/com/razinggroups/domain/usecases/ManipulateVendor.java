package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.vendor.FetchSingleVendorResposne;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class ManipulateVendor extends SingleUseCase<Message, ManipulateVendor.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public ManipulateVendor(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        if (params.type.equalsIgnoreCase("create"))
            return sampleRepository.createVendor(params.resposne);
        else if (params.type.equalsIgnoreCase("update"))
            return sampleRepository.updateVendor(params.resposne);

        return null;
    }

    public static final class Params {
        FetchSingleVendorResposne resposne;
        String type;

        public Params(FetchSingleVendorResposne resposne, String type) {
            this.resposne = resposne;
            this.type = type;
        }

        public static ManipulateVendor.Params ManipulateVendor(FetchSingleVendorResposne resposne, String type) {
            return new ManipulateVendor.Params(resposne, type);
        }
    }
}
