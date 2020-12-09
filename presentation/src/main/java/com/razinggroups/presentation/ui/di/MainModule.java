package com.razinggroups.presentation.ui.di;

import android.arch.lifecycle.ViewModelProvider;

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
import com.razinggroups.domain.usecases.FetchHolidayList;
import com.razinggroups.domain.usecases.FetchSingleEmployee;
import com.razinggroups.domain.usecases.FetchSingleEmployeeLeaves;
import com.razinggroups.domain.usecases.FetchSingleEmployeeTasks;
import com.razinggroups.domain.usecases.FetchSingleVendorUsecase;
import com.razinggroups.domain.usecases.FetchVendorServiceUseCase;
import com.razinggroups.domain.usecases.GetCredentialsUseCase;
import com.razinggroups.domain.usecases.LoginUseCase;
import com.razinggroups.domain.usecases.ManipulateBrand;
import com.razinggroups.domain.usecases.ManipulateCompany;
import com.razinggroups.domain.usecases.ManipulateVendor;
import com.razinggroups.domain.usecases.SetCredentialsUseCase;
import com.razinggroups.domain.usecases.UpdateEmployeeUseCase;
import com.razinggroups.domain.usecases.UpdateLeaveUsecase;
import com.razinggroups.domain.usecases.UpdateTaskUsecase;
import com.razinggroups.presentation.base.ViewModelProviderFactory;
import com.razinggroups.presentation.ui.brandCompany.BrandManipulateScreen.BrandManipulateViewModel;
import com.razinggroups.presentation.ui.brandCompany.CompanyManipulateScreen.CompanyManipulateViewModel;
import com.razinggroups.presentation.ui.brandCompany.ListScreen.CompanyListViewModel;
import com.razinggroups.presentation.ui.createTask.CreateTaskViewModel;
import com.razinggroups.presentation.ui.dashboard.DashBoardViewModel;
import com.razinggroups.presentation.ui.employee.EditEmployee.EditEmployeeViewModel;
import com.razinggroups.presentation.ui.employee.EmployeeViewModel;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeApplyLeave.EmployeeApplyLeaveViewModel;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeTaskDetailList.EmployeeTaskDetailListViewModel;
import com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList.EmployeeLeaveListViewModel;
import com.razinggroups.presentation.ui.employeeTask.EmployeeTaskViewModel;
import com.razinggroups.presentation.ui.employeeTask.employeeTaskDetail.EmployeeTaskDetailViewModel;
import com.razinggroups.presentation.ui.holiday.HolidayViewModel;
import com.razinggroups.presentation.ui.leaves.LeaveViewModel;
import com.razinggroups.presentation.ui.login.LoginViewModel;
import com.razinggroups.presentation.ui.main.MainViewModel;
import com.razinggroups.presentation.ui.myTask.MyTaskViewModel;
import com.razinggroups.presentation.ui.myTask.createMyTask.CreateMyTaskViewModel;
import com.razinggroups.presentation.ui.myTask.myTaskDetails.MyTaskDetailViewModel;
import com.razinggroups.presentation.ui.vendor.createVendorTask.CreateVendorTaskViewModel;
import com.razinggroups.presentation.ui.vendor.manipulateVendor.ManipulateVendorViewModel;
import com.razinggroups.presentation.ui.vendor.vendorList.VendorListViewModel;
import com.razinggroups.presentation.ui.vendor.vendorTaskList.VendorTaskListViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainViewModel provideMainViewModel() {
        return new MainViewModel();
    }

    @Provides
    @Named("MainActivity")
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    LoginViewModel provideLoginViewModel(LoginUseCase loginUseCase, SetCredentialsUseCase setCredentialsUseCase, GetCredentialsUseCase getCredentialsUseCase) {
        return new LoginViewModel(loginUseCase, setCredentialsUseCase, getCredentialsUseCase);
    }

    @Provides
    @Named("LoginActivity")
    ViewModelProvider.Factory loginViewModelProvider(LoginViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    DashBoardViewModel provideDashBoardViewModel(FetchAllEmployeesUseCase fetchAllEmployeesUseCase, FetchAllMyTaskUseCase fetchAllMyTaskUseCase, FetchAllEmployeeTasksUseCase fetchAllEmployeeTasksUseCase) {
        return new DashBoardViewModel(fetchAllEmployeesUseCase, fetchAllMyTaskUseCase, fetchAllEmployeeTasksUseCase);
    }

    @Provides
    @Named("DashBoardFragment")
    ViewModelProvider.Factory dashboardViewModelProvider(DashBoardViewModel dashBoardViewModel) {
        return new ViewModelProviderFactory<>(dashBoardViewModel);
    }

    @Provides
    EmployeeViewModel provideEmployeeViewModel(FetchAllEmployeesUseCase fetchAllEmployeesUseCase) {
        return new EmployeeViewModel(fetchAllEmployeesUseCase);
    }

    @Provides
    @Named("EmployeeFragment")
    ViewModelProvider.Factory employeeViewModelProvider(EmployeeViewModel employeeViewModel) {
        return new ViewModelProviderFactory<>(employeeViewModel);
    }

    @Provides
    @Named("EmployeeTaskFragment")
    ViewModelProvider.Factory employeeTaskViewModelProvider(EmployeeTaskViewModel employeeViewModel) {
        return new ViewModelProviderFactory<>(employeeViewModel);
    }

    @Provides
    EmployeeTaskViewModel provideEmployeeTaskViewModel(FetchAllEmployeeTasksUseCase fetchAllEmployeeTasksUseCase) {
        return new EmployeeTaskViewModel(fetchAllEmployeeTasksUseCase);
    }

    @Provides
    LeaveViewModel provideLeaveViewModel(FetchAllLeavesUseCase fetchAllLeavesUseCase, UpdateLeaveUsecase updateLeaveUsecase) {
        return new LeaveViewModel(fetchAllLeavesUseCase, updateLeaveUsecase);
    }

    @Provides
    @Named("LeaveFragmnet")
    ViewModelProvider.Factory LeaveViewModelProvider(LeaveViewModel leaveViewModel) {
        return new ViewModelProviderFactory<>(leaveViewModel);
    }

    @Provides
    @Named("MyTaskFragment")
    ViewModelProvider.Factory MyTaskViewModelProvider(MyTaskViewModel myTaskViewModel) {
        return new ViewModelProviderFactory<>(myTaskViewModel);
    }

    @Provides
    MyTaskViewModel provideMyTaskViewModel(FetchAllMyTaskUseCase fetchAllMyTaskUseCase) {
        return new MyTaskViewModel(fetchAllMyTaskUseCase);
    }

    @Provides
    @Named("HolidayListFragment")
    ViewModelProvider.Factory HolidayViewModelProvider(HolidayViewModel holidayViewModel) {
        return new ViewModelProviderFactory<>(holidayViewModel);
    }

    @Provides
    HolidayViewModel provideHolidayViewModel(FetchHolidayList fetchHolidayList) {
        return new HolidayViewModel(fetchHolidayList);
    }

    @Provides
    @Named("EmployeeTaskDetailListFragment")
    ViewModelProvider.Factory EmployeeTaskDetailsViewModellProvider(EmployeeTaskDetailListViewModel employeeTaskDetailListViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailListViewModel);
    }

    @Provides
    EmployeeTaskDetailListViewModel provideEmployeeTaskDetailsViewModel(FetchSingleEmployeeTasks fetchSingleEmployeeTasks, UpdateTaskUsecase taskUsecase) {
        return new EmployeeTaskDetailListViewModel(fetchSingleEmployeeTasks, taskUsecase);
    }


    @Provides
    @Named("EmployeeLeaveListFragment")
    ViewModelProvider.Factory EmployeeLeaveListViewModelProvider(EmployeeLeaveListViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    EmployeeLeaveListViewModel provideEmployeeLeaveListViewModel(FetchSingleEmployeeLeaves fetchSingleEmployeeLeaves) {
        return new EmployeeLeaveListViewModel(fetchSingleEmployeeLeaves);
    }

    @Provides
    @Named("EmployeeApplyLeaveFragment")
    ViewModelProvider.Factory EmployeeApplyLeaveViewModelProvider(EmployeeApplyLeaveViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    EmployeeApplyLeaveViewModel provideEmployeeApplyLeaveViewModel(CreateLeaveUseCase createLeaveUseCase) {
        return new EmployeeApplyLeaveViewModel(createLeaveUseCase);
    }


    @Provides
    @Named("EmployeeTaskDetailFragment")
    ViewModelProvider.Factory EmployeeTaskDetailViewModelProvider(EmployeeTaskDetailViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    EmployeeTaskDetailViewModel provideEmployeeTaskDetailViewModel(DeleteUseCase deleteUseCase, UpdateTaskUsecase updateTaskUsecase, FetchSingleEmployeeTasks fetchSingleEmployeeTasks) {
        return new EmployeeTaskDetailViewModel(deleteUseCase, updateTaskUsecase, fetchSingleEmployeeTasks);
    }


    @Provides
    @Named("MyTaskDetailFragment")
    ViewModelProvider.Factory MyTaskDetailViewModelProvider(MyTaskDetailViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    MyTaskDetailViewModel provideMyTaskDetailViewModel(DeleteUseCase deleteUseCase, UpdateTaskUsecase updateTaskUsecase, FetchAllMyTaskUseCase fetchSingleEmployeeTasks) {
        return new MyTaskDetailViewModel(deleteUseCase, updateTaskUsecase, fetchSingleEmployeeTasks);
    }


    @Provides
    @Named("CreateMyTaskFragment")
    ViewModelProvider.Factory CreateMyTaskViewModelProvider(CreateMyTaskViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    CreateMyTaskViewModel provideCreateMyTaskViewModel(CreateTaskUseCase createTaskUseCase, FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {
        return new CreateMyTaskViewModel(createTaskUseCase, fetchAllBrandDetailsUseCase);
    }


    @Provides
    @Named("CreateTaskActivity")
    ViewModelProvider.Factory CreateTaskViewModelProvider(CreateTaskViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    CreateTaskViewModel provideCreateTaskViewModel(FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase, FetchAllEmployeesUseCase fetchAllEmployeesUseCase, CreateTaskUseCase createTaskUseCase) {
        return new CreateTaskViewModel(fetchAllBrandDetailsUseCase, fetchAllEmployeesUseCase, createTaskUseCase);
    }


    @Provides
    @Named("CompanyListFragment")
    ViewModelProvider.Factory CompanyListViewModelProvider(CompanyListViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    CompanyListViewModel provideCompanyListViewModel(FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase, FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {
        return new CompanyListViewModel(fetchAllCompanyDetailsUsecase, fetchAllBrandDetailsUseCase);
    }


    @Provides
    @Named("BrandManipulateActivity")
    ViewModelProvider.Factory BrandManipulateViewModelProvider(BrandManipulateViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    BrandManipulateViewModel provideBrandManipulateViewModel(ManipulateBrand manipulateBrand, FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase, DeleteUseCase deleteUseCase, FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase) {
        return new BrandManipulateViewModel(manipulateBrand, fetchAllBrandDetailsUseCase, deleteUseCase, fetchAllCompanyDetailsUsecase);
    }


    @Provides
    @Named("CompanyManipulateActivity")
    ViewModelProvider.Factory CompanyManipulateViewModelProvider(CompanyManipulateViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    CompanyManipulateViewModel provideCompanyManipulateViewModel(ManipulateCompany manipulateBrand, FetchAllCompanyDetailsUsecase fetchAllBrandDetailsUseCase, DeleteUseCase deleteUseCase) {
        return new CompanyManipulateViewModel(manipulateBrand, fetchAllBrandDetailsUseCase, deleteUseCase);
    }


    @Provides
    @Named("EditEmployeeFragment")
    ViewModelProvider.Factory EditEmployeeViewModelProvider(EditEmployeeViewModel employeeTaskDetailsViewModel) {
        return new ViewModelProviderFactory<>(employeeTaskDetailsViewModel);
    }

    @Provides
    EditEmployeeViewModel provideEditEmployeeViewModel(FetchSingleEmployee fetchAllEmployeesUseCase, UpdateEmployeeUseCase updateEmployeeUseCase, DeleteUseCase deleteUseCase, FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {
        return new EditEmployeeViewModel(fetchAllEmployeesUseCase, updateEmployeeUseCase, deleteUseCase, fetchAllBrandDetailsUseCase);
    }

    @Provides
    @Named("ManipulateVendorActivity")
    ViewModelProvider.Factory ManipulateVendorViewModelProvider(ManipulateVendorViewModel manipulateVendorViewModel) {
        return new ViewModelProviderFactory<>(manipulateVendorViewModel);
    }

    @Provides
    ManipulateVendorViewModel provideManipulateVendorViewModel(DeleteUseCase deleteUseCase, ManipulateVendor manipulateVendor, FetchSingleVendorUsecase fetchSingleVendorUsecase, FetchVendorServiceUseCase fetchVendorServiceUseCase, FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {
        return new ManipulateVendorViewModel(deleteUseCase, manipulateVendor, fetchSingleVendorUsecase, fetchVendorServiceUseCase, fetchAllBrandDetailsUseCase);
    }

    @Provides
    @Named("CreateVendorTaskActivity")
    ViewModelProvider.Factory CreateVendorTaskViewModelProvider(CreateVendorTaskViewModel manipulateVendorViewModel) {
        return new ViewModelProviderFactory<>(manipulateVendorViewModel);
    }

    @Provides
    CreateVendorTaskViewModel provideCreateVendorTaskViewModel(CreateVendorTaskUsecase createVendorTaskUsecase, FetchVendorServiceUseCase fetchVendorServiceUseCase, FetchAllVendorBrandsUseCase fetchAllVendorBrandsUseCase) {
        return new CreateVendorTaskViewModel(createVendorTaskUsecase, fetchVendorServiceUseCase, fetchAllVendorBrandsUseCase);
    }

    @Provides
    @Named("VendorListFragment")
    ViewModelProvider.Factory VendorListViewModelProvider(VendorListViewModel manipulateVendorViewModel) {
        return new ViewModelProviderFactory<>(manipulateVendorViewModel);
    }

    @Provides
    VendorListViewModel provideVendorListViewModel(FetchAllVendorUsecase fetchAllVendorUsecase) {
        return new VendorListViewModel(fetchAllVendorUsecase);
    }

    @Provides
    @Named("VendorTaskListFragment")
    ViewModelProvider.Factory VendorTaskListViewModelProvider(VendorTaskListViewModel manipulateVendorViewModel) {
        return new ViewModelProviderFactory<>(manipulateVendorViewModel);
    }

    @Provides
    VendorTaskListViewModel provideVendorTaskListViewModel(FetchAllVendorTaskUseCase fetchAllVendorTaskUseCase, DeleteUseCase deleteUseCase) {
        return new VendorTaskListViewModel(fetchAllVendorTaskUseCase, deleteUseCase);
    }


}

