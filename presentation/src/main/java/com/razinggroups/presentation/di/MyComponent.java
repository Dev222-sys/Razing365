package com.razinggroups.presentation.di;


import com.razinggroups.presentation.ui.brandCompany.BrandManipulateScreen.BrandManipulateActivity;
import com.razinggroups.presentation.ui.brandCompany.CompanyManipulateScreen.CompanyManipulateActivity;
import com.razinggroups.presentation.ui.brandCompany.ListScreen.CompanyListFragment;
import com.razinggroups.presentation.ui.createTask.CreateTaskActivity;
import com.razinggroups.presentation.ui.dashboard.DashBoardFragment;
import com.razinggroups.presentation.ui.di.MainModule;
import com.razinggroups.presentation.ui.employee.EditEmployee.EditEmployeeFragment;
import com.razinggroups.presentation.ui.employee.EmployeeFragment;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeApplyLeave.EmployeeApplyLeaveFragment;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeTaskDetailList.EmployeeTaskDetailListFragment;
import com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList.EmployeeLeaveListFragment;
import com.razinggroups.presentation.ui.employeeTask.EmployeeTaskFragment;
import com.razinggroups.presentation.ui.employeeTask.employeeTaskDetail.EmployeeTaskDetailFragment;
import com.razinggroups.presentation.ui.holiday.HolidayListFragment;
import com.razinggroups.presentation.ui.leaves.LeaveFragmnet;
import com.razinggroups.presentation.ui.login.LoginActivity;
import com.razinggroups.presentation.ui.main.MainActivity;
import com.razinggroups.presentation.ui.myTask.MyTaskFragment;
import com.razinggroups.presentation.ui.myTask.createMyTask.CreateMyTaskFragment;
import com.razinggroups.presentation.ui.myTask.myTaskDetails.MyTaskDetailFragment;
import com.razinggroups.presentation.ui.vendor.createVendorTask.CreateVendorTaskActivity;
import com.razinggroups.presentation.ui.vendor.manipulateVendor.ManipulateVendorActivity;
import com.razinggroups.presentation.ui.vendor.vendorList.VendorListFragment;
import com.razinggroups.presentation.ui.vendor.vendorTaskList.VendorTaskListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UseCaseModule.class, RepositoryModule.class, MainModule.class})
public interface MyComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);


    void inject(DashBoardFragment dashBoardFragment);

    void inject(EmployeeFragment employeeFragment);

    void inject(EmployeeTaskFragment employeeTaskFragment);

    void inject(LeaveFragmnet leaveFragmnet);

    void inject(MyTaskFragment myTaskFragment);

    void inject(HolidayListFragment holidayListFragment);

    void inject(EmployeeTaskDetailListFragment employeeTaskDetailListFragment);

    void inject(EmployeeLeaveListFragment employeeLeaveListFragment);

    void inject(EmployeeApplyLeaveFragment employeeApplyLeaveFragment);

    void inject(EmployeeTaskDetailFragment employeeTaskDetailFragment);

    void inject(MyTaskDetailFragment myTaskDetailFragment);

    void inject(CreateMyTaskFragment createMyTaskFragment);

    void inject(CreateTaskActivity createTaskActivity);

    void inject(CompanyListFragment companyListFragment);

    void inject(BrandManipulateActivity brandManipulateActivity);

    void inject(CompanyManipulateActivity companyManipulateActivity);

    void inject(EditEmployeeFragment editEmployeeFragment);
   // void inject(CustomerQueryFragment customerQueryFragment);


    void inject(ManipulateVendorActivity manipulateVendorActivity);

    void inject(CreateVendorTaskActivity createVendorTaskActivity);

    void inject(VendorListFragment vendorListFragment);

    void inject(VendorTaskListFragment vendorTaskListFragment);

}
