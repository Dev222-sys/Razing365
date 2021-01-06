package com.razinggroups.domain.repository;

import com.razinggroups.domain.model.Credentials;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.Login;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.SingleIdRequest;
import com.razinggroups.domain.model.brand.CreateBrandRequest;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.company.CreateCompanyRequest;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.model.employeeTask.CreateEmployeeTaskRequest;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.model.holiday.HolidayRecordResponse;
import com.razinggroups.domain.model.leave.CreateLeaveRequest;
import com.razinggroups.domain.model.leave.FetchAllLeavesResponse;
import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave;
import com.razinggroups.domain.model.leave.UpdateLeave;
import com.razinggroups.domain.model.myTask.CreateMyTaskRequest;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse;
import com.razinggroups.domain.model.vendor.FetchAllVendorResponse;
import com.razinggroups.domain.model.vendor.FetchSingleVendorResposne;
import com.razinggroups.domain.model.vendor.FetchVendorServiceResponse;
import com.razinggroups.domain.model.vendorTask.CreateVendorTask;
import com.razinggroups.domain.model.vendorTask.FetchVendorTaks;

import java.util.List;

import io.reactivex.Single;

public interface SampleRepository {

    Single<Integer> sum(int a, int b);

    Single<Login> login(String userName, String password);
    //Single<Message> updateEmployee(EmployeeDetail employeeDetail);

    Single<Message>customer(Customer customer);


    Single<FetchAllMyTask> fetchAllMyTask();

    Single<EmployeeList> fetchAllEmployess();


    Single<EmployeeDetail> fetchEmployee(long id);

    Single<Message> updateEmployee(EmployeeDetail employeeDetail);


    Single<FetchEmployeeTask> fetchCompleteEmployeeTask();

    Single<FetchEmployeeTask> fetchActiveEmployeeTask();

    Single<List<HolidayRecordResponse>> fetchHolidayList(String city);

    Single<FetchEmployeeTask> fetchAllEmployeeTasks();

    Single<FetchEmployeeTask> fetchSingleEmployeeTasks(long id);

    Single<Message> updateEmployeeTask(SingleIdRequest request);

    Single<FetchSingleEmployeeLeave> fetchEmployeeLeaves(long id);

    Single<Message> createLeave(CreateLeaveRequest request);

    Single<FetchAllLeavesResponse> fetchAllLeaves();

    Single<Message> updateLeave(UpdateLeave updateLeave);


    Single<Message> deleteEmployeeTask(SingleIdRequest singleIdRequestNw);


    Single<Message> updateMyTask(SingleIdRequest singleIdRequestNw);

    Single<Message> deleteMyTask(SingleIdRequest singleIdRequestNw);


    Single<FetchAllMyTask> fetchAdminAllMyTask();

    Single<Message> updateAdminMyTask(SingleIdRequest singleIdRequest);

    Single<Message> deleteAdminMyTask(SingleIdRequest singleIdRequest);

    Single<Message> createEmployeeTask(CreateEmployeeTaskRequest createEmployeeTaskRequest);

    Single<Message> createAdminMyTask(CreateMyTaskRequest createMyTaskRequest);

    Single<Message> createMyTask(CreateMyTaskRequest createMyTaskRequest);


    Single<FetchAllBrandDetailsResponse> fetchAllBrandDetails();

    Single<Message> deleteEmployee(SingleIdRequest singleIdRequest);

    Single<Message> deleteBrand(SingleIdRequest singleIdRequest);

    Single<Message> deleteCompany(SingleIdRequest singleIdRequest);

    Single<Message> deleteVendor(SingleIdRequest singleIdRequest);

    Single<Message> deleteVendorTask(SingleIdRequest singleIdRequest);

    Single<EmployeeList> fetchOnlineEmployees();


    Single<Message> createVendor(FetchSingleVendorResposne fetchSingleVendorResposne);

    Single<Message> updateVendor(FetchSingleVendorResposne createBrandRequest);

    Single<Message> createCompany(CreateCompanyRequest createCompanyRequest);

    Single<Message> updateCompany(CreateCompanyRequest createBrandRequestNw);

    Single<Message> createBrand(CreateBrandRequest createBrandRequest);

    Single<Message> updateBrand(CreateBrandRequest createBrandRequestNw);

    Single<FetchAllBrandDetailsRecordResponse> fetchSingleBrandDetails(long id);


    Single<FetchAllCompanyDetailsResponse> fetchAllCompanyDetails();

    Single<FetchAllCompanyDetailsRecordResponse> fetchSingleCompanyDetails(long id);


    Single<FetchAllVendorResponse> fetchAllVendor();

    Single<FetchSingleVendorResposne> fetchSingleVendor(long id);


    Single<FetchAllVendorBrandResponse> fetchAllVendorBrands();

    Single<FetchVendorServiceResponse> fetchVendorService(long id);


    Single<FetchVendorTaks> fetchAllVendorTask();

    Single<Message> createVendorTask(CreateVendorTask createVendorTaskNw);

    Single<Boolean> setCredentials(String userName, String password);

    Single<Credentials> getCredentials();



    Single<FetchAllMyTask> fetchEmployeeAllMyTask();

    Single<Message> updateEmployeeMyTask(SingleIdRequest singleIdRequest);

    Single<Message> deleteEmployeeMyTask(SingleIdRequest singleIdRequest);

    Single<Message> createEmployeeMyTask(CreateMyTaskRequest createMyTaskRequest);



}
