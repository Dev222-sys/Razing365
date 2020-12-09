package com.razinggroups.presentation.di;

import com.razinggroups.domain.executor.PostExecutionThread;
import com.razinggroups.domain.repository.SampleRepository;
import com.razinggroups.domain.usecases.CreateLeaveUseCase;
import com.razinggroups.domain.usecases.CreateTaskUseCase;
import com.razinggroups.domain.usecases.CreateVendorTaskUsecase;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase;
import com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase;
import com.razinggroups.domain.usecases.FetchAllEmployeeTasksUseCase;
import com.razinggroups.domain.usecases.FetchAllEmployeesUseCase;
import com.razinggroups.domain.usecases.FetchAllLeavesUseCase;
import com.razinggroups.domain.usecases.FetchAllMyTaskUseCase;
import com.razinggroups.domain.usecases.FetchAllVendorBrandsUseCase;
import com.razinggroups.domain.usecases.FetchAllVendorTaskUseCase;
import com.razinggroups.domain.usecases.FetchAllVendorUsecase;
import com.razinggroups.domain.usecases.FetchSingleEmployee;
import com.razinggroups.domain.usecases.FetchSingleEmployeeLeaves;
import com.razinggroups.domain.usecases.FetchHolidayList;
import com.razinggroups.domain.usecases.FetchSingleEmployeeTasks;
import com.razinggroups.domain.usecases.FetchSingleVendorUsecase;
import com.razinggroups.domain.usecases.FetchVendorServiceUseCase;
import com.razinggroups.domain.usecases.GetCredentialsUseCase;
import com.razinggroups.domain.usecases.GetSum;
import com.razinggroups.domain.usecases.LoginUseCase;
import com.razinggroups.domain.usecases.ManipulateBrand;
import com.razinggroups.domain.usecases.ManipulateCompany;
import com.razinggroups.domain.usecases.ManipulateVendor;
import com.razinggroups.domain.usecases.SetCredentialsUseCase;
import com.razinggroups.domain.usecases.UpdateEmployeeUseCase;
import com.razinggroups.domain.usecases.UpdateLeaveUsecase;
import com.razinggroups.domain.usecases.UpdateTaskUsecase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    GetSum provideGetSum(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new GetSum(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllVendorUsecase provideFetchAllVendorUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllVendorUsecase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchSingleVendorUsecase provideFetchSingleVendorUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchSingleVendorUsecase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllVendorBrandsUseCase provideFetchAllVendorBrandsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllVendorBrandsUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchVendorServiceUseCase provideFetchVendorServiceUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchVendorServiceUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllVendorTaskUseCase provideFetchAllVendorTaskUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllVendorTaskUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    CreateVendorTaskUsecase provideCreateVendorTaskUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new CreateVendorTaskUsecase(postExecutionThread, sampleRepository);
    }


    @Provides
    LoginUseCase provideLoginUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new LoginUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllEmployeeTasksUseCase provideFetchAllEmployeeTasksUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllEmployeeTasksUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllMyTaskUseCase provideFetchAllMyTaskUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllMyTaskUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllEmployeesUseCase provideFetchAllEmployeesUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllEmployeesUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchHolidayList provideFetchHolidayList(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchHolidayList(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchSingleEmployeeTasks provideFetchSingleEmployeeTasks(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchSingleEmployeeTasks(postExecutionThread, sampleRepository);
    }

    @Provides
    UpdateTaskUsecase provideUpdateTaskUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new UpdateTaskUsecase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchSingleEmployeeLeaves provideFetchEmployeeLeaves(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchSingleEmployeeLeaves(postExecutionThread, sampleRepository);
    }

    @Provides
    CreateLeaveUseCase provideCreateLeaveUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new CreateLeaveUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllLeavesUseCase provideFetchAllLeavesUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllLeavesUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    UpdateLeaveUsecase provideUpdateLeaveUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new UpdateLeaveUsecase(postExecutionThread, sampleRepository);
    }

    @Provides
    DeleteUseCase provideDeleteUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new DeleteUseCase(postExecutionThread, sampleRepository);
    }


    @Provides
    CreateTaskUseCase provideCreateTaskUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new CreateTaskUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchAllBrandDetailsUseCase provideFetchAllBrandDetailsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllBrandDetailsUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    ManipulateVendor provideManipulateVendor(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new ManipulateVendor(postExecutionThread, sampleRepository);
    }

    @Provides
    ManipulateBrand provideManipulateBrand(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new ManipulateBrand(postExecutionThread, sampleRepository);
    }

    @Provides
    ManipulateCompany provideManipulateCompany(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new ManipulateCompany(postExecutionThread, sampleRepository);
    }

    @Provides
    UpdateEmployeeUseCase provideUpdateEmployeeUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new UpdateEmployeeUseCase(postExecutionThread, sampleRepository);
    }


    @Provides
    FetchAllCompanyDetailsUsecase provideFetchAllCompanyDetailsUsecase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchAllCompanyDetailsUsecase(postExecutionThread, sampleRepository);
    }

    @Provides
    FetchSingleEmployee provideFetchSingleEmployee(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new FetchSingleEmployee(postExecutionThread, sampleRepository);
    }

    @Provides
    SetCredentialsUseCase provideSetCredentialsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new SetCredentialsUseCase(postExecutionThread, sampleRepository);
    }

    @Provides
    GetCredentialsUseCase provideGetCredentialsUseCase(PostExecutionThread postExecutionThread, SampleRepository sampleRepository) {
        return new GetCredentialsUseCase(postExecutionThread, sampleRepository);
    }

}
