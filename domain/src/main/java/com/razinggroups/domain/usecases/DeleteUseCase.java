package com.razinggroups.domain.usecases;

import com.razinggroups.domain.base.SingleUseCase;
import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.SingleIdRequest;
import com.razinggroups.domain.repository.SampleRepository;

import io.reactivex.Single;

public class DeleteUseCase extends SingleUseCase<Message, DeleteUseCase.Params> {
    com.razinggroups.domain.repository.SampleRepository sampleRepository;

    public DeleteUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        super(postExecutionThread);
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Single<Message> buildUseCaseObservable(Params params) {
        SingleIdRequest request = new SingleIdRequest(params.id);

        if (params.type.equalsIgnoreCase("employeeTask")) {
            return sampleRepository.deleteEmployeeTask(request);
        } else if (params.type.equalsIgnoreCase("MasterAdmin")) {
            return sampleRepository.deleteMyTask(request);
        } else if (params.type.equalsIgnoreCase("Admin")) {
            return sampleRepository.deleteAdminMyTask(request);
        } else if (params.type.equalsIgnoreCase("employee")) {
            return sampleRepository.deleteEmployee(request);
        } else if (params.type.equalsIgnoreCase("brand")) {
            return sampleRepository.deleteBrand(request);
        } else if (params.type.equalsIgnoreCase("vendor")) {
            return sampleRepository.deleteVendor(request);
        } else if (params.type.equalsIgnoreCase("vendorTask")) {
            return sampleRepository.deleteVendorTask(request);
        } else if (params.type.equalsIgnoreCase("company")) {
            return sampleRepository.deleteCompany(request);
        }else if (params.type.equalsIgnoreCase("EmployeeMyTask")) {
            return sampleRepository.deleteEmployeeMyTask(request);
        }
        return null;
    }

    public static final class Params {

        String id;
        String type;

        public Params(String id, String type) {
            this.id = id;
            this.type = type;
        }

        public static DeleteUseCase.Params DeleteUseCase(String id, String type) {
            return new DeleteUseCase.Params(id, type);
        }
    }
}
