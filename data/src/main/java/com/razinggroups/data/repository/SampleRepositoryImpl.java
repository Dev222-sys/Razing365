package com.razinggroups.data.repository;

import android.content.Context;

import com.razinggroups.data.database.SampleRoomDatabase;
import com.razinggroups.data.database.dao.SampleDao;
import com.razinggroups.data.models.CustomerQuerry.CustomerNw;
import com.razinggroups.data.models.Employee.EmployeeDetailNw;
import com.razinggroups.data.models.Employee.EmployeeListNw;
import com.razinggroups.data.models.MessageNw;
import com.razinggroups.data.models.SingleIdRequestNw;
import com.razinggroups.data.models.brand.CreateBrandRequestNw;
import com.razinggroups.data.models.brand.FetchAllBrandDetailsRecordResponseNw;
import com.razinggroups.data.models.brand.FetchAllBrandDetailsResponseNw;
import com.razinggroups.data.models.company.CreateCompanyRequestnw;
import com.razinggroups.data.models.company.FetchAllCompanyDetailsRecordResponseNw;
import com.razinggroups.data.models.company.FetchAllCompanyDetailsResponseNw;
import com.razinggroups.data.models.employeeTask.CreateEmployeeTaskRequestNw;
import com.razinggroups.data.models.employeeTask.FetchEmployeeTaskNw;
import com.razinggroups.data.models.employeeTask.FetchEmployeeTaskRecordNw;
import com.razinggroups.data.models.holiday.HolidayRecordResponseNw;
import com.razinggroups.data.models.holiday.HolidayResponseNw;
import com.razinggroups.data.models.leave.CreateLeaveRequestNw;
import com.razinggroups.data.models.leave.FetchAllLeavesRecordResponseNw;
import com.razinggroups.data.models.leave.FetchAllLeavesResponseNw;
import com.razinggroups.data.models.leave.FetchSingleEmployeeLeaveNw;
import com.razinggroups.data.models.leave.FetchSingleEmployeeLeaveRecordNw;
import com.razinggroups.data.models.leave.UpdateLeaveNw;
import com.razinggroups.data.models.login.LoginNw;
import com.razinggroups.data.models.myTask.CreateMyTaskRequestNw;
import com.razinggroups.data.models.myTask.FetchAllMyTaskNw;
import com.razinggroups.data.models.myTask.FetchAllMyTaskRecordsNw;
import com.razinggroups.data.models.vendor.FetchAllVendorBrandRecordResponseNw;
import com.razinggroups.data.models.vendor.FetchAllVendorBrandResponseNw;
import com.razinggroups.data.models.vendor.FetchAllVendorRecordResponseNw;
import com.razinggroups.data.models.vendor.FetchAllVendorResponseNw;
import com.razinggroups.data.models.vendor.FetchSingleVendorResposneNw;
import com.razinggroups.data.models.vendor.FetchVendorServiceRecordResponseNw;
import com.razinggroups.data.models.vendor.FetchVendorServiceResponseNw;
import com.razinggroups.data.models.vendorTask.CreateVendorTaskNw;
import com.razinggroups.data.models.vendorTask.FetchVendorTaksNw;
import com.razinggroups.data.models.vendorTask.FetchVendorTaksRecordNw;
import com.razinggroups.data.network.ApiInterface;
import com.razinggroups.data.sharedpreference.SharedPreferenceHelper;
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
import com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.model.holiday.HolidayRecordResponse;
import com.razinggroups.domain.model.leave.CreateLeaveRequest;
import com.razinggroups.domain.model.leave.FetchAllLeavesRecordResponse;
import com.razinggroups.domain.model.leave.FetchAllLeavesResponse;
import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave;
import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeaveRecord;
import com.razinggroups.domain.model.leave.UpdateLeave;
import com.razinggroups.domain.model.myTask.CreateMyTaskRequest;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords;
import com.razinggroups.domain.model.vendor.FetchAllVendorBrandRecordResponse;
import com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse;
import com.razinggroups.domain.model.vendor.FetchAllVendorRecordResponse;
import com.razinggroups.domain.model.vendor.FetchAllVendorResponse;
import com.razinggroups.domain.model.vendor.FetchSingleVendorResposne;
import com.razinggroups.domain.model.vendor.FetchVendorServiceRecordResponse;
import com.razinggroups.domain.model.vendor.FetchVendorServiceResponse;
import com.razinggroups.domain.model.vendorTask.CreateVendorTask;
import com.razinggroups.domain.model.vendorTask.FetchVendorTaks;
import com.razinggroups.domain.model.vendorTask.FetchVendorTaksRecord;
import com.razinggroups.domain.repository.SampleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class SampleRepositoryImpl implements SampleRepository {

    private ApiInterface apiInterface;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private Context context;

    private SampleDao sampleDao;
    private SampleRoomDatabase db;

    public SampleRepositoryImpl(ApiInterface apiInterface, SharedPreferenceHelper sharedPreferenceHelper, SampleRoomDatabase db, Context context) {
        this.apiInterface = apiInterface;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.context = context;
        this.db = db;
        sampleDao = db.wordDao();
    }


    @Override
    public Single<Integer> sum(final int a, final int b) {
        return Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return (a + b);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Login> login(String userName, String password) {

        return apiInterface.login(userName, password).map(new Function<LoginNw, com.razinggroups.domain.model.Login>() {
            @Override
            public com.razinggroups.domain.model.Login apply(LoginNw loginNw) throws Exception {
                com.razinggroups.domain.model.Login login = new
                        Login(loginNw.getMsg(), loginNw.getType(), loginNw.getEmployeeId(), loginNw.getName(),loginNw.getEmail()
                );

                if (loginNw.getEmployeeId() != null)
                    sharedPreferenceHelper.getSamplePreference()
                            .edit()
                            .putEmployeeId(Long.parseLong(loginNw.getEmployeeId()))
                            .apply();
                return login;
            }
        });
    }



    @Override
    public Single<Message> updateEmployee(EmployeeDetail employeeDetailNw) {

        EmployeeDetailNw employeeDetail = new EmployeeDetailNw(
                employeeDetailNw.getId(),
                employeeDetailNw.getLocation(),
                employeeDetailNw.getName(),
                employeeDetailNw.getDesignation(),
                employeeDetailNw.getLandline(),
                employeeDetailNw.getOfficialMail(),
                employeeDetailNw.getPersonalMail(),
                employeeDetailNw.getMobile(),
                employeeDetailNw.getDol(),
                employeeDetailNw.getAppointmentLetter(),
                employeeDetailNw.getOfficeAddress(),
                employeeDetailNw.getResidenceAddress(),
                employeeDetailNw.getSalary(),
                employeeDetailNw.getEmergencyName(),
                employeeDetailNw.getEmergencyNo(),
                employeeDetailNw.getDob(),
                employeeDetailNw.getDoj(),
                employeeDetailNw.getAadharNo(),
                employeeDetailNw.getAadharCard(),
                employeeDetailNw.getPanNo(),
                employeeDetailNw.getPanCard(),
                employeeDetailNw.getEducation(),
                employeeDetailNw.getProfileImg(),
                employeeDetailNw.getEmpStatus(),
                employeeDetailNw.getBrandName(),
                employeeDetailNw.getBrandId()
        );
        return apiInterface.updateEmployee(employeeDetail).map(new Function<MessageNw, Message>() {
            @Override
            public Message apply(MessageNw messageNw) throws Exception {
                return new Message(messageNw.getMessage());

            }
        });
    }



    @Override
    public Single<Message> customer(Customer customer) {
        CustomerNw customerNw = new CustomerNw(

                customer.getLead_type(),
                customer.getName(),
                customer.getCompany_email(),
                customer.getEnquiry_details(),
                customer.getMobile(),
                customer.getLandline(),
                customer.getPassport_no(),
                customer.getNationality(),
                customer.getReference(),
                customer.getAddress(),
                customer.getProfession());


        return apiInterface.custoer_query(customerNw).map(new Function<MessageNw, Message>() {
            @Override
            public Message apply(MessageNw messageNw) throws Exception {
                return new Message(messageNw.getMessage());

            }
        });
    }

    /*@Override
    public Single<Customer> customer(String userName, String password) {
        return null;
    }
*/
    @Override
    public Single<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask> fetchAllEmployeeTasks() {
        return apiInterface.fetchAllEmployeeTask().map(new Function<FetchEmployeeTaskNw, com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public com.razinggroups.domain.model.employeeTask.FetchEmployeeTask apply(FetchEmployeeTaskNw fetchEmployeeTaskNw) throws Exception {
                List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> fetchAllEmployeeTaskRecordList = new ArrayList<>();
                for (FetchEmployeeTaskRecordNw fetchEmployeeTaskRecordNw : fetchEmployeeTaskNw.getRecords()) {
                    com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord fetchAllEmployeeTaskRecord =
                            new com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord(fetchEmployeeTaskRecordNw.getId(),
                                    fetchEmployeeTaskRecordNw.getEmpid(),
                                    fetchEmployeeTaskRecordNw.getEmpname(),
                                    fetchEmployeeTaskRecordNw.getTaskFile(),
                                    fetchEmployeeTaskRecordNw.getTaskDetail(),
                                    fetchEmployeeTaskRecordNw.getAssignDate(),
                                    fetchEmployeeTaskRecordNw.getDeadline(),
                                    fetchEmployeeTaskRecordNw.getTaskTitle(),
                                    fetchEmployeeTaskRecordNw.getStatus()
                            );
                    fetchAllEmployeeTaskRecordList.add(fetchAllEmployeeTaskRecord);
                }
                return new com.razinggroups.domain.model.employeeTask.FetchEmployeeTask(fetchEmployeeTaskNw.getCount(), fetchAllEmployeeTaskRecordList);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask> fetchSingleEmployeeTasks(long id) {
        return apiInterface.fetchSingleEmployeeTask(id).map(new Function<FetchEmployeeTaskNw, com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public com.razinggroups.domain.model.employeeTask.FetchEmployeeTask apply(FetchEmployeeTaskNw fetchEmployeeTaskNw) throws Exception {
                List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> fetchAllEmployeeTaskRecordList = new ArrayList<>();
                for (FetchEmployeeTaskRecordNw fetchEmployeeTaskRecordNw : fetchEmployeeTaskNw.getRecords()) {
                    com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord fetchAllEmployeeTaskRecord =
                            new com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord(fetchEmployeeTaskRecordNw.getId(),
                                    fetchEmployeeTaskRecordNw.getEmpid(),
                                    fetchEmployeeTaskRecordNw.getEmpname(),
                                    fetchEmployeeTaskRecordNw.getTaskFile(),
                                    fetchEmployeeTaskRecordNw.getTaskDetail(),
                                    fetchEmployeeTaskRecordNw.getAssignDate(),
                                    fetchEmployeeTaskRecordNw.getDeadline(),
                                    fetchEmployeeTaskRecordNw.getTaskTitle(),
                                    fetchEmployeeTaskRecordNw.getStatus()
                            );
                    fetchAllEmployeeTaskRecordList.add(fetchAllEmployeeTaskRecord);
                }
                return new com.razinggroups.domain.model.employeeTask.FetchEmployeeTask(fetchEmployeeTaskNw.getCount(), fetchAllEmployeeTaskRecordList);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave> fetchEmployeeLeaves(long id) {
        return apiInterface.fetchEmployeeLeaves(id).map(new Function<FetchSingleEmployeeLeaveNw, com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave>() {
            @Override
            public com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave apply(FetchSingleEmployeeLeaveNw fetchSingleEmployeeLeaveNw) throws Exception {

                List<com.razinggroups.domain.model.leave.FetchSingleEmployeeLeaveRecord> fetchAllEmployeeTaskRecordList = new ArrayList<>();

                for (FetchSingleEmployeeLeaveRecordNw fetchEmployeeLeaveRecordNw : fetchSingleEmployeeLeaveNw.getRecords()) {
                    com.razinggroups.domain.model.leave.FetchSingleEmployeeLeaveRecord fetchAllEmployeeleaveRecord =
                            new FetchSingleEmployeeLeaveRecord(fetchEmployeeLeaveRecordNw.getLeaveTitle(),
                                    fetchEmployeeLeaveRecordNw.getFromDate(),
                                    fetchEmployeeLeaveRecordNw.getToDate(),
                                    fetchEmployeeLeaveRecordNw.getDiscription(),
                                    fetchEmployeeLeaveRecordNw.getStatus()
                            );
                    fetchAllEmployeeTaskRecordList.add(fetchAllEmployeeleaveRecord);
                }

                com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave fetchSingleEmployeeLeave = new FetchSingleEmployeeLeave(fetchSingleEmployeeLeaveNw.getCount(), fetchAllEmployeeTaskRecordList);

                return fetchSingleEmployeeLeave;
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createLeave(CreateLeaveRequest request) {
        CreateLeaveRequestNw requestNw = new CreateLeaveRequestNw(request.getEmpid(),
                request.getLeaveTitle(),
                request.getFromDate(),
                request.getToDate(),
                request.getLeaveDisc());
        return apiInterface.createLeave(requestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.leave.FetchAllLeavesResponse> fetchAllLeaves() {
        return apiInterface.fetchAllLeaves().map(new Function<FetchAllLeavesResponseNw, com.razinggroups.domain.model.leave.FetchAllLeavesResponse>() {
            @Override
            public com.razinggroups.domain.model.leave.FetchAllLeavesResponse apply(FetchAllLeavesResponseNw fetchAllLeavesResponseNw) throws Exception {

                List<com.razinggroups.domain.model.leave.FetchAllLeavesRecordResponse> allLeavesRecordResponses = new ArrayList<>();

                for (FetchAllLeavesRecordResponseNw fetchAllLeavesRecordResponseNw : fetchAllLeavesResponseNw.getRecords()) {
                    com.razinggroups.domain.model.leave.FetchAllLeavesRecordResponse fetchAllLeavesRecordResponse =
                            new FetchAllLeavesRecordResponse(fetchAllLeavesRecordResponseNw.getId(),
                                    fetchAllLeavesRecordResponseNw.getEmpid(),
                                    fetchAllLeavesRecordResponseNw.getName(),
                                    fetchAllLeavesRecordResponseNw.getBrand(),
                                    fetchAllLeavesRecordResponseNw.getLeaveTitle(),
                                    fetchAllLeavesRecordResponseNw.getLeaveStatus(),
                                    fetchAllLeavesRecordResponseNw.getRemainingLeave(),
                                    fetchAllLeavesRecordResponseNw.getFromDate(),
                                    fetchAllLeavesRecordResponseNw.getToDate(),
                                    fetchAllLeavesRecordResponseNw.getDiscription()
                            );
                    allLeavesRecordResponses.add(fetchAllLeavesRecordResponse);
                }

                com.razinggroups.domain.model.leave.FetchAllLeavesResponse fetchAllLeavesResponse = new FetchAllLeavesResponse(fetchAllLeavesResponseNw.getCount(), allLeavesRecordResponses);

                return fetchAllLeavesResponse;
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateLeave(UpdateLeave updateLeave) {
        UpdateLeaveNw updateLeaveNw = new UpdateLeaveNw(updateLeave.getId(), updateLeave.getStatus());
        return apiInterface.updateLeave(updateLeaveNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteEmployeeTask(com.razinggroups.domain.model.SingleIdRequest singleIdRequestNw) {
        return apiInterface.deleteEmployeeTask(new SingleIdRequestNw(singleIdRequestNw.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateEmployeeTask(com.razinggroups.domain.model.SingleIdRequest request) {
        SingleIdRequestNw singleIdRequestNw = new SingleIdRequestNw(request.getId());
        return apiInterface.updateEmployeeTask(singleIdRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateMyTask(com.razinggroups.domain.model.SingleIdRequest request) {
        SingleIdRequestNw singleIdRequestNw = new SingleIdRequestNw(request.getId());
        return apiInterface.updateMyTask(singleIdRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateAdminMyTask(com.razinggroups.domain.model.SingleIdRequest request) {
        SingleIdRequestNw singleIdRequestNw = new SingleIdRequestNw(request.getId());
        return apiInterface.updateAdminMyTask(singleIdRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteMyTask(com.razinggroups.domain.model.SingleIdRequest singleIdRequestNw) {
        return apiInterface.deleteMyTask(new SingleIdRequestNw(singleIdRequestNw.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteAdminMyTask(com.razinggroups.domain.model.SingleIdRequest singleIdRequest) {

        return apiInterface.deleteAdminMyTask(new SingleIdRequestNw(singleIdRequest.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createEmployeeTask(CreateEmployeeTaskRequest createEmployeeTaskRequest) {
        CreateEmployeeTaskRequestNw createEmployeeTaskRequestNw = new CreateEmployeeTaskRequestNw(
                createEmployeeTaskRequest.getEmpid(),
                createEmployeeTaskRequest.getTaskFile(),
                createEmployeeTaskRequest.getTaskDetail(),
                createEmployeeTaskRequest.getDeadline(),
                createEmployeeTaskRequest.getTaskTitle());
        return apiInterface.createEmployeeTask(createEmployeeTaskRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createAdminMyTask(com.razinggroups.domain.model.myTask.CreateMyTaskRequest createMyTaskRequest) {
        CreateMyTaskRequestNw createMyTaskRequestNw = new CreateMyTaskRequestNw(createMyTaskRequest.getBrand(),
                createMyTaskRequest.getTaskTitle(),
                createMyTaskRequest.getDeadline(),
                createMyTaskRequest.getTaskFile(),
                createMyTaskRequest.getTaskDetail());
        createMyTaskRequestNw.setId(sharedPreferenceHelper.getSamplePreference().getEmployeeId());
        return apiInterface.createAdminMyTask(createMyTaskRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createMyTask(com.razinggroups.domain.model.myTask.CreateMyTaskRequest createMyTaskRequest) {
        CreateMyTaskRequestNw createMyTaskRequestNw = new CreateMyTaskRequestNw(createMyTaskRequest.getBrand(),
                createMyTaskRequest.getTaskTitle(),
                createMyTaskRequest.getDeadline(),
                createMyTaskRequest.getTaskFile(),
                createMyTaskRequest.getTaskDetail());
        return apiInterface.createMyTask(createMyTaskRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.myTask.FetchAllMyTask> fetchAdminAllMyTask() {
        return apiInterface.fetchAdminAllMyTask(sharedPreferenceHelper.getSamplePreference().getEmployeeId()).map(new Function<FetchAllMyTaskNw, com.razinggroups.domain.model.myTask.FetchAllMyTask>() {
            @Override
            public com.razinggroups.domain.model.myTask.FetchAllMyTask apply(FetchAllMyTaskNw fetchAllMyTaskNw) throws Exception {
                List<com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords> fetchAllMyTaskRecordList = new ArrayList<>();
                for (FetchAllMyTaskRecordsNw fetchAllMyTaskRecordNw : fetchAllMyTaskNw.getRecords()) {
                    com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords fetchAllEmployeeTaskRecord =
                            new com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords(fetchAllMyTaskRecordNw.getId(),
                                    fetchAllMyTaskRecordNw.getBrand(),
                                    fetchAllMyTaskRecordNw.getTaskTitle(),
                                    fetchAllMyTaskRecordNw.getDeadline(),
                                    fetchAllMyTaskRecordNw.getTaskFile(),
                                    fetchAllMyTaskRecordNw.getTaskDetail(),
                                    fetchAllMyTaskRecordNw.getAssignDate(),
                                    fetchAllMyTaskRecordNw.getStatus()
                            );
                    fetchAllMyTaskRecordList.add(fetchAllEmployeeTaskRecord);
                }
                return new com.razinggroups.domain.model.myTask.FetchAllMyTask(fetchAllMyTaskRecordList);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.myTask.FetchAllMyTask> fetchAllMyTask() {
        return apiInterface.fetchAllMyTask().map(new Function<FetchAllMyTaskNw, com.razinggroups.domain.model.myTask.FetchAllMyTask>() {
            @Override
            public com.razinggroups.domain.model.myTask.FetchAllMyTask apply(FetchAllMyTaskNw fetchAllMyTaskNw) throws Exception {
                List<com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords> fetchAllMyTaskRecordList = new ArrayList<>();
                for (FetchAllMyTaskRecordsNw fetchAllMyTaskRecordNw : fetchAllMyTaskNw.getRecords()) {
                    com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords fetchAllEmployeeTaskRecord =
                            new com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords(fetchAllMyTaskRecordNw.getId(),
                                    fetchAllMyTaskRecordNw.getBrand(),
                                    fetchAllMyTaskRecordNw.getTaskTitle(),
                                    fetchAllMyTaskRecordNw.getDeadline(),
                                    fetchAllMyTaskRecordNw.getTaskFile(),
                                    fetchAllMyTaskRecordNw.getTaskDetail(),
                                    fetchAllMyTaskRecordNw.getAssignDate(),
                                    fetchAllMyTaskRecordNw.getStatus()
                            );
                    fetchAllMyTaskRecordList.add(fetchAllEmployeeTaskRecord);
                }
                return new com.razinggroups.domain.model.myTask.FetchAllMyTask(fetchAllMyTaskRecordList);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.employee.EmployeeList> fetchAllEmployess() {
        return apiInterface.fetchAllEmployees().map(new Function<EmployeeListNw, com.razinggroups.domain.model.employee.EmployeeList>() {
            @Override
            public com.razinggroups.domain.model.employee.EmployeeList apply(EmployeeListNw employeeListNw) throws Exception {
                List<com.razinggroups.domain.model.employee.EmployeeDetail> employeeDetails = new ArrayList<>();
                for (EmployeeDetailNw employeeDetailNw : employeeListNw.getEmployeeDetailNwList()) {
                    com.razinggroups.domain.model.employee.EmployeeDetail employeeDetail =
                            new com.razinggroups.domain.model.employee.EmployeeDetail(employeeDetailNw.getId(),
                                    employeeDetailNw.getLocation(),
                                    employeeDetailNw.getName(),
                                    employeeDetailNw.getDesignation(),
                                    employeeDetailNw.getLandline(),
                                    employeeDetailNw.getOfficialMail(),
                                    employeeDetailNw.getPersonalMail(),
                                    employeeDetailNw.getMobile(),
                                    employeeDetailNw.getDol(),
                                    employeeDetailNw.getAppointmentLetter(),
                                    employeeDetailNw.getOfficeAddress(),
                                    employeeDetailNw.getResidenceAddress(),
                                    employeeDetailNw.getSalary(),
                                    employeeDetailNw.getEmergencyName(),
                                    employeeDetailNw.getEmergencyNo(),
                                    employeeDetailNw.getDob(),
                                    employeeDetailNw.getDoj(),
                                    employeeDetailNw.getAadharNo(),
                                    employeeDetailNw.getAadharCard(),
                                    employeeDetailNw.getPanNo(),
                                    employeeDetailNw.getPanCard(),
                                    employeeDetailNw.getEducation(),
                                    employeeDetailNw.getProfileImg(),
                                    employeeDetailNw.getEmpStatus(),
                                    employeeDetailNw.getBrandName(),
                                    employeeDetailNw.getBrandId()
                            );
                    employeeDetails.add(employeeDetail);
                }
                return new com.razinggroups.domain.model.employee.EmployeeList(employeeDetails);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.employee.EmployeeDetail> fetchEmployee(long id) {
        return apiInterface.fetchEmployee(id).map(new Function<EmployeeDetailNw, com.razinggroups.domain.model.employee.EmployeeDetail>() {
            @Override
            public com.razinggroups.domain.model.employee.EmployeeDetail apply(EmployeeDetailNw employeeDetailNw) throws Exception {
                com.razinggroups.domain.model.employee.EmployeeDetail employeeDetail = new com.razinggroups.domain.model.employee.EmployeeDetail(
                        employeeDetailNw.getId(),
                        employeeDetailNw.getLocation(),
                        employeeDetailNw.getName(),
                        employeeDetailNw.getDesignation(),
                        employeeDetailNw.getLandline(),
                        employeeDetailNw.getOfficialMail(),
                        employeeDetailNw.getPersonalMail(),
                        employeeDetailNw.getMobile(),
                        employeeDetailNw.getDol(),
                        employeeDetailNw.getAppointmentLetter(),
                        employeeDetailNw.getOfficeAddress(),
                        employeeDetailNw.getResidenceAddress(),
                        employeeDetailNw.getSalary(),
                        employeeDetailNw.getEmergencyName(),
                        employeeDetailNw.getEmergencyNo(),
                        employeeDetailNw.getDob(),
                        employeeDetailNw.getDoj(),
                        employeeDetailNw.getAadharNo(),
                        employeeDetailNw.getAadharCard(),
                        employeeDetailNw.getPanNo(),
                        employeeDetailNw.getPanCard(),
                        employeeDetailNw.getEducation(),
                        employeeDetailNw.getProfileImg(),
                        employeeDetailNw.getEmpStatus(),
                        employeeDetailNw.getBrandName(),
                        employeeDetailNw.getBrandId()
                );

                return employeeDetail;
            }
        });
    }

    @Override
    public Single<List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>> fetchHolidayList(String city) {
        switch (city) {
            case "DU":
                return apiInterface.fetchDuHolidays().map(new Function<HolidayResponseNw, List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>>() {
                    @Override
                    public List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> apply(HolidayResponseNw holidayResponseNw) throws Exception {
                        List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> list = new ArrayList<>();
                        for (HolidayRecordResponseNw holidayRecordResponseNw : holidayResponseNw.getRecords()) {
                            com.razinggroups.domain.model.holiday.HolidayRecordResponse holidayRecordResponse = new com.razinggroups.domain.model.holiday.HolidayRecordResponse(holidayRecordResponseNw.getDate(), holidayRecordResponseNw.getHolidayName());
                            list.add(holidayRecordResponse);
                        }
                        return list;
                    }
                });

            case "delhi":
                return apiInterface.fetchDelhiHolidays().map(new Function<HolidayResponseNw, List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>>() {
                    @Override
                    public List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> apply(HolidayResponseNw holidayResponseNw) throws Exception {
                        List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> list = new ArrayList<>();
                        for (HolidayRecordResponseNw holidayRecordResponseNw : holidayResponseNw.getRecords()) {
                            com.razinggroups.domain.model.holiday.HolidayRecordResponse holidayRecordResponse = new com.razinggroups.domain.model.holiday.HolidayRecordResponse(holidayRecordResponseNw.getDate(), holidayRecordResponseNw.getHolidayName());
                            list.add(holidayRecordResponse);
                        }
                        return list;
                    }
                });
            case "sriLanka":
                return apiInterface.fetchSriLankaHolidays().map(new Function<HolidayResponseNw, List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>>() {
                    @Override
                    public List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> apply(HolidayResponseNw holidayResponseNw) throws Exception {
                        List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> list = new ArrayList<>();
                        for (HolidayRecordResponseNw holidayRecordResponseNw : holidayResponseNw.getRecords()) {
                            com.razinggroups.domain.model.holiday.HolidayRecordResponse holidayRecordResponse = new com.razinggroups.domain.model.holiday.HolidayRecordResponse(holidayRecordResponseNw.getDate(), holidayRecordResponseNw.getHolidayName());
                            list.add(holidayRecordResponse);
                        }
                        return list;
                    }
                });
            case "mumbai":
                return apiInterface.fetchMumbaiHolidays().map(new Function<HolidayResponseNw, List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>>() {
                    @Override
                    public List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> apply(HolidayResponseNw holidayResponseNw) throws Exception {

                        List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> list = new ArrayList<>();
                        for (HolidayRecordResponseNw holidayRecordResponseNw : holidayResponseNw.getRecords()) {
                            com.razinggroups.domain.model.holiday.HolidayRecordResponse holidayRecordResponse = new com.razinggroups.domain.model.holiday.HolidayRecordResponse(holidayRecordResponseNw.getDate(), holidayRecordResponseNw.getHolidayName());
                            list.add(holidayRecordResponse);
                        }
                        return list;
                    }
                });
            case "bangalore":
                return apiInterface.fetchBangaloreHolidays().map(new Function<HolidayResponseNw, List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>>() {
                    @Override
                    public List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> apply(HolidayResponseNw holidayResponseNw) throws Exception {

                        List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> list = new ArrayList<>();
                        for (HolidayRecordResponseNw holidayRecordResponseNw : holidayResponseNw.getRecords()) {
                            com.razinggroups.domain.model.holiday.HolidayRecordResponse holidayRecordResponse = new com.razinggroups.domain.model.holiday.HolidayRecordResponse(holidayRecordResponseNw.getDate(), holidayRecordResponseNw.getHolidayName());
                            list.add(holidayRecordResponse);
                        }
                        return list;
                    }
                });
            case "kolkata":
                return apiInterface.fetchKolkataHolidays().map(new Function<HolidayResponseNw, List<com.razinggroups.domain.model.holiday.HolidayRecordResponse>>() {
                    @Override
                    public List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> apply(HolidayResponseNw holidayResponseNw) throws Exception {

                        List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> list = new ArrayList<>();
                        for (HolidayRecordResponseNw holidayRecordResponseNw : holidayResponseNw.getRecords()) {
                            com.razinggroups.domain.model.holiday.HolidayRecordResponse holidayRecordResponse = new HolidayRecordResponse(holidayRecordResponseNw.getDate(), holidayRecordResponseNw.getHolidayName());
                            list.add(holidayRecordResponse);
                        }
                        return list;
                    }
                });


        }
        return null;
    }

    @Override
    public Single<com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse> fetchAllBrandDetails() {
        return apiInterface.fetchAllBrandDetails().map(new Function<FetchAllBrandDetailsResponseNw, com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse>() {
            @Override
            public com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse apply(FetchAllBrandDetailsResponseNw fetchAllBrandDetailsResponseNw) throws Exception {
                List<com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse> records = new ArrayList<>();
                for (FetchAllBrandDetailsRecordResponseNw responseNw : fetchAllBrandDetailsResponseNw.getRecords()) {
                    com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse recordResponse = new
                            com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse(
                            responseNw.getId(),
                            responseNw.getBrandName(),
                            responseNw.getBrandCompany(),
                            responseNw.getBrandDivision(),
                            responseNw.getDivisionMail(),
                            responseNw.getDivisionMobile(),
                            responseNw.getBrandEmail(),
                            responseNw.getBrandMobile(),
                            responseNw.getBrandStreet(),
                            responseNw.getBrandCity(),
                            responseNw.getBrandCode(),
                            responseNw.getBrandCountry()
                    );
                    records.add(recordResponse);
                }
                com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse = new FetchAllBrandDetailsResponse(records);
                return fetchAllBrandDetailsResponse;
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteEmployee(com.razinggroups.domain.model.SingleIdRequest singleIdRequest) {
        return apiInterface.deleteEmployee(new SingleIdRequestNw(singleIdRequest.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteBrand(com.razinggroups.domain.model.SingleIdRequest singleIdRequest) {
        return apiInterface.deleteBrand(new SingleIdRequestNw(singleIdRequest.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteCompany(com.razinggroups.domain.model.SingleIdRequest singleIdRequest) {
        return apiInterface.deleteCompany(new SingleIdRequestNw(singleIdRequest.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteVendor(com.razinggroups.domain.model.SingleIdRequest singleIdRequest) {
        return apiInterface.deleteVendor(new SingleIdRequestNw(singleIdRequest.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteVendorTask(com.razinggroups.domain.model.SingleIdRequest singleIdRequest) {
        return apiInterface.deleteVendorTask(new SingleIdRequestNw(singleIdRequest.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask> fetchCompleteEmployeeTask() {
        return apiInterface.fetchCompleteEmployeeTask().map(new Function<FetchEmployeeTaskNw, com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public com.razinggroups.domain.model.employeeTask.FetchEmployeeTask apply(FetchEmployeeTaskNw fetchEmployeeTaskNw) throws Exception {
                List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> fetchAllEmployeeTaskRecordList = new ArrayList<>();
                for (FetchEmployeeTaskRecordNw fetchEmployeeTaskRecordNw : fetchEmployeeTaskNw.getRecords()) {
                    com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord fetchAllEmployeeTaskRecord =
                            new com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord(fetchEmployeeTaskRecordNw.getId(),
                                    fetchEmployeeTaskRecordNw.getEmpid(),
                                    fetchEmployeeTaskRecordNw.getEmpname(),
                                    fetchEmployeeTaskRecordNw.getTaskFile(),
                                    fetchEmployeeTaskRecordNw.getTaskDetail(),
                                    fetchEmployeeTaskRecordNw.getAssignDate(),
                                    fetchEmployeeTaskRecordNw.getDeadline(),
                                    fetchEmployeeTaskRecordNw.getTaskTitle(),
                                    fetchEmployeeTaskRecordNw.getStatus()
                            );
                    fetchAllEmployeeTaskRecordList.add(fetchAllEmployeeTaskRecord);
                }
                return new com.razinggroups.domain.model.employeeTask.FetchEmployeeTask(fetchEmployeeTaskNw.getCount(), fetchAllEmployeeTaskRecordList);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.employeeTask.FetchEmployeeTask> fetchActiveEmployeeTask() {
        return apiInterface.fetchActiveEmployeeTask().map(new Function<FetchEmployeeTaskNw, com.razinggroups.domain.model.employeeTask.FetchEmployeeTask>() {
            @Override
            public com.razinggroups.domain.model.employeeTask.FetchEmployeeTask apply(FetchEmployeeTaskNw fetchEmployeeTaskNw) throws Exception {
                List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> fetchAllEmployeeTaskRecordList = new ArrayList<>();
                for (FetchEmployeeTaskRecordNw fetchEmployeeTaskRecordNw : fetchEmployeeTaskNw.getRecords()) {
                    com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord fetchAllEmployeeTaskRecord =
                            new FetchAllEmployeeTaskRecord(fetchEmployeeTaskRecordNw.getId(),
                                    fetchEmployeeTaskRecordNw.getEmpid(),
                                    fetchEmployeeTaskRecordNw.getEmpname(),
                                    fetchEmployeeTaskRecordNw.getTaskFile(),
                                    fetchEmployeeTaskRecordNw.getTaskDetail(),
                                    fetchEmployeeTaskRecordNw.getAssignDate(),
                                    fetchEmployeeTaskRecordNw.getDeadline(),
                                    fetchEmployeeTaskRecordNw.getTaskTitle(),
                                    fetchEmployeeTaskRecordNw.getStatus()
                            );
                    fetchAllEmployeeTaskRecordList.add(fetchAllEmployeeTaskRecord);
                }
                return new FetchEmployeeTask(fetchEmployeeTaskNw.getCount(), fetchAllEmployeeTaskRecordList);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.employee.EmployeeList> fetchOnlineEmployees() {
        return apiInterface.fetchOnlineEmployees().map(new Function<EmployeeListNw, com.razinggroups.domain.model.employee.EmployeeList>() {
            @Override
            public com.razinggroups.domain.model.employee.EmployeeList apply(EmployeeListNw employeeListNw) throws Exception {
                List<com.razinggroups.domain.model.employee.EmployeeDetail> employeeDetails = new ArrayList<>();
                for (EmployeeDetailNw employeeDetailNw : employeeListNw.getEmployeeDetailNwList()) {
                    com.razinggroups.domain.model.employee.EmployeeDetail employeeDetail =
                            new EmployeeDetail(employeeDetailNw.getId(),
                                    employeeDetailNw.getLocation(),
                                    employeeDetailNw.getName(),
                                    employeeDetailNw.getDesignation(),
                                    employeeDetailNw.getLandline(),
                                    employeeDetailNw.getOfficialMail(),
                                    employeeDetailNw.getPersonalMail(),
                                    employeeDetailNw.getMobile(),
                                    employeeDetailNw.getDol(),
                                    employeeDetailNw.getAppointmentLetter(),
                                    employeeDetailNw.getOfficeAddress(),
                                    employeeDetailNw.getResidenceAddress(),
                                    employeeDetailNw.getSalary(),
                                    employeeDetailNw.getEmergencyName(),
                                    employeeDetailNw.getEmergencyNo(),
                                    employeeDetailNw.getDob(),
                                    employeeDetailNw.getDoj(),
                                    employeeDetailNw.getAadharNo(),
                                    employeeDetailNw.getAadharCard(),
                                    employeeDetailNw.getPanNo(),
                                    employeeDetailNw.getPanCard(),
                                    employeeDetailNw.getEducation(),
                                    employeeDetailNw.getProfileImg(),
                                    employeeDetailNw.getEmpStatus(),
                                    employeeDetailNw.getBrandName(),
                                    employeeDetailNw.getBrandId()
                            );
                    employeeDetails.add(employeeDetail);
                }
                return new EmployeeList(employeeDetails);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createVendor(com.razinggroups.domain.model.vendor.FetchSingleVendorResposne fetchSingleVendorResposne) {
        FetchSingleVendorResposneNw fetchSingleVendorResposneNw = new FetchSingleVendorResposneNw(
                fetchSingleVendorResposne.getFirstName(),
                fetchSingleVendorResposne.getMiddleName(),
                fetchSingleVendorResposne.getLastName(),
                fetchSingleVendorResposne.getEmail(),
                fetchSingleVendorResposne.getContact(),
                fetchSingleVendorResposne.getVendorBrand(),
                fetchSingleVendorResposne.getVendorCompany(),
                fetchSingleVendorResposne.getVendorService(),
                fetchSingleVendorResposne.getGstin(),
                fetchSingleVendorResposne.getStreet(),
                fetchSingleVendorResposne.getCity(),
                fetchSingleVendorResposne.getPostalCode(),
                fetchSingleVendorResposne.getCountry()
        );

        return apiInterface.createVendor(fetchSingleVendorResposneNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateVendor(com.razinggroups.domain.model.vendor.FetchSingleVendorResposne fetchSingleVendorResposne) {
        FetchSingleVendorResposneNw fetchSingleVendorResposneNw = new FetchSingleVendorResposneNw(
                fetchSingleVendorResposne.getId(),
                fetchSingleVendorResposne.getFirstName(),
                fetchSingleVendorResposne.getMiddleName(),
                fetchSingleVendorResposne.getLastName(),
                fetchSingleVendorResposne.getEmail(),
                fetchSingleVendorResposne.getContact(),
                fetchSingleVendorResposne.getVendorBrand(),
                fetchSingleVendorResposne.getVendorCompany(),
                fetchSingleVendorResposne.getVendorService(),
                fetchSingleVendorResposne.getGstin(),
                fetchSingleVendorResposne.getStreet(),
                fetchSingleVendorResposne.getCity(),
                fetchSingleVendorResposne.getPostalCode(),
                fetchSingleVendorResposne.getCountry()
        );

        return apiInterface.updateVendor(fetchSingleVendorResposneNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createCompany(com.razinggroups.domain.model.company.CreateCompanyRequest createCompanyRequest) {
        CreateCompanyRequestnw createCompanyRequestnw = new CreateCompanyRequestnw(
                createCompanyRequest.getCompanyName(),
                createCompanyRequest.getGstNumber(),
                createCompanyRequest.getGstLocation(),
                createCompanyRequest.getGstFile(),
                createCompanyRequest.getPanNumber(),
                createCompanyRequest.getTanNumber(),
                createCompanyRequest.getMca(),
                createCompanyRequest.getBillingAddress(),
                createCompanyRequest.getBillingAddressLocation(),
                createCompanyRequest.getEmailAddress(),
                createCompanyRequest.getMobile(),
                createCompanyRequest.getHeadOfficeAddress(),
                createCompanyRequest.getStreet(),
                createCompanyRequest.getCity(),
                createCompanyRequest.getPostalCode(),
                createCompanyRequest.getCountry(),
                createCompanyRequest.getWebsiteLink()
        );
        System.out.println("create company" + createCompanyRequestnw.toString());

        return apiInterface.createCompany(createCompanyRequestnw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateCompany(CreateCompanyRequest createCompanyRequest) {

        CreateCompanyRequestnw createCompanyRequestnw = new CreateCompanyRequestnw(
                createCompanyRequest.getId(),
                createCompanyRequest.getCompanyName(),
                createCompanyRequest.getGstNumber(),
                createCompanyRequest.getGstLocation(),
                createCompanyRequest.getGstFile(),
                createCompanyRequest.getPanNumber(),
                createCompanyRequest.getTanNumber(),
                createCompanyRequest.getMca(),
                createCompanyRequest.getBillingAddress(),
                createCompanyRequest.getBillingAddressLocation(),
                createCompanyRequest.getEmailAddress(),
                createCompanyRequest.getMobile(),
                createCompanyRequest.getHeadOfficeAddress(),
                createCompanyRequest.getStreet(),
                createCompanyRequest.getCity(),
                createCompanyRequest.getPostalCode(),
                createCompanyRequest.getCountry(),
                createCompanyRequest.getWebsiteLink()
        );
        System.out.println("request company" + createCompanyRequestnw.toString());
        return apiInterface.updateCompany(createCompanyRequestnw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createBrand(com.razinggroups.domain.model.brand.CreateBrandRequest createBrandRequest) {
        CreateBrandRequestNw requestNw = new CreateBrandRequestNw(
                createBrandRequest.getBrandName(),
                createBrandRequest.getBrandCompany(),
                createBrandRequest.getBrandDivision(),
                createBrandRequest.getDivisionMail(),
                createBrandRequest.getDivisionMobile(),
                createBrandRequest.getBrandEmail(),
                createBrandRequest.getBrandMobile(),
                createBrandRequest.getBrandStreet(),
                createBrandRequest.getBrandCity(),
                createBrandRequest.getBrandCode(),
                createBrandRequest.getBrandCountry()
        );
        System.out.println("create brand " + requestNw.toString());

        return apiInterface.createBrand(requestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateBrand(CreateBrandRequest createBrandRequest) {

        CreateBrandRequestNw requestNw = new CreateBrandRequestNw(
                createBrandRequest.getId(),
                createBrandRequest.getBrandName(),
                createBrandRequest.getBrandCompany(),
                createBrandRequest.getBrandDivision(),
                createBrandRequest.getDivisionMail(),
                createBrandRequest.getDivisionMobile(),
                createBrandRequest.getBrandEmail(),
                createBrandRequest.getBrandMobile(),
                createBrandRequest.getBrandStreet(),
                createBrandRequest.getBrandCity(),
                createBrandRequest.getBrandCode(),
                createBrandRequest.getBrandCountry()
        );
        System.out.println("request brand " + requestNw.toString());
        return apiInterface.updateBrand(requestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });

    }

    @Override
    public Single<com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse> fetchSingleBrandDetails(long id) {
        return apiInterface.fetchSingleBrandDetails(id).map(new Function<FetchAllBrandDetailsRecordResponseNw, com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse>() {
            @Override
            public com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse apply(FetchAllBrandDetailsRecordResponseNw fetchAllBrandDetailsRecordResponseNw) throws Exception {

                com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse recordResponse = new FetchAllBrandDetailsRecordResponse(
                        fetchAllBrandDetailsRecordResponseNw.getId(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandName(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandCompany(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandDivision(),
                        fetchAllBrandDetailsRecordResponseNw.getDivisionMail(),
                        fetchAllBrandDetailsRecordResponseNw.getDivisionMobile(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandEmail(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandMobile(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandStreet(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandCity(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandCode(),
                        fetchAllBrandDetailsRecordResponseNw.getBrandCountry()
                );

                return recordResponse;
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse> fetchAllCompanyDetails() {
        return apiInterface.fetchAllCompanyDetails().map(new Function<FetchAllCompanyDetailsResponseNw, com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse>() {
            @Override
            public com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse apply(FetchAllCompanyDetailsResponseNw fetchAllCompanyDetailsResponseNw) throws Exception {
                List<com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse> records = new ArrayList<>();
                for (FetchAllCompanyDetailsRecordResponseNw response : fetchAllCompanyDetailsResponseNw.getRecords()) {
                    com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse recordResponse = new com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse(
                            response.getId(),
                            response.getCompanyName(),
                            response.getGstNumber(),
                            response.getGstLocation(),
                            response.getGstFile(),
                            response.getPanNumber(),
                            response.getTanNumber(),
                            response.getMca(),
                            response.getBillingAddress(),
                            response.getBillingAddressLocation(),
                            response.getEmailAddress(),
                            response.getMobile(),
                            response.getHeadOfficeAddress(),
                            response.getStreet(),
                            response.getCity(),
                            response.getPostalCode(),
                            response.getCountry(),
                            response.getWebsiteLink()
                    );
                    records.add(recordResponse);
                }
                return new FetchAllCompanyDetailsResponse(records);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse> fetchSingleCompanyDetails(long id) {
        return apiInterface.fetchSingleCompanyDetails(id).map(new Function<FetchAllCompanyDetailsRecordResponseNw, com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse>() {
            @Override
            public com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse apply(FetchAllCompanyDetailsRecordResponseNw response) throws Exception {
                com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse recordResponse = new FetchAllCompanyDetailsRecordResponse(
                        response.getId(),
                        response.getCompanyName(),
                        response.getGstNumber(),
                        response.getGstLocation(),
                        response.getGstFile(),
                        response.getPanNumber(),
                        response.getTanNumber(),
                        response.getMca(),
                        response.getBillingAddress(),
                        response.getBillingAddressLocation(),
                        response.getEmailAddress(),
                        response.getMobile(),
                        response.getHeadOfficeAddress(),
                        response.getStreet(),
                        response.getCity(),
                        response.getPostalCode(),
                        response.getCountry(),
                        response.getWebsiteLink()
                );
                return recordResponse;
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.vendor.FetchAllVendorResponse> fetchAllVendor() {
        return apiInterface.fetchAllVendor().map(new Function<FetchAllVendorResponseNw, com.razinggroups.domain.model.vendor.FetchAllVendorResponse>() {
            @Override
            public com.razinggroups.domain.model.vendor.FetchAllVendorResponse apply(FetchAllVendorResponseNw fetchAllVendorResponseNw) throws Exception {
                List<com.razinggroups.domain.model.vendor.FetchAllVendorRecordResponse> records = new ArrayList<>();
                for (FetchAllVendorRecordResponseNw recordResponseNw : fetchAllVendorResponseNw.getRecords()) {
                    com.razinggroups.domain.model.vendor.FetchAllVendorRecordResponse recordResponse = new FetchAllVendorRecordResponse(
                            recordResponseNw.getId(),
                            recordResponseNw.getFirstName(),
                            recordResponseNw.getMiddleName(),
                            recordResponseNw.getLastName(),
                            recordResponseNw.getVendorBrand()
                    );
                    records.add(recordResponse);
                }

                return new FetchAllVendorResponse(fetchAllVendorResponseNw.getCount(), records);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.vendor.FetchSingleVendorResposne> fetchSingleVendor(long id) {
        return apiInterface.fetchSingleVendor(id).map(new Function<FetchSingleVendorResposneNw, com.razinggroups.domain.model.vendor.FetchSingleVendorResposne>() {
            @Override
            public com.razinggroups.domain.model.vendor.FetchSingleVendorResposne apply(FetchSingleVendorResposneNw fetchSingleVendorResposneNw) throws Exception {
                return new FetchSingleVendorResposne(
                        fetchSingleVendorResposneNw.getId(),
                        fetchSingleVendorResposneNw.getFirstName(),
                        fetchSingleVendorResposneNw.getMiddleName(),
                        fetchSingleVendorResposneNw.getLastName(),
                        fetchSingleVendorResposneNw.getEmail(),
                        fetchSingleVendorResposneNw.getContact(),
                        fetchSingleVendorResposneNw.getVendorBrand(),
                        fetchSingleVendorResposneNw.getVendorCompany(),
                        fetchSingleVendorResposneNw.getVendorService(),
                        fetchSingleVendorResposneNw.getGstin(),
                        fetchSingleVendorResposneNw.getStreet(),
                        fetchSingleVendorResposneNw.getCity(),
                        fetchSingleVendorResposneNw.getPostalCode(),
                        fetchSingleVendorResposneNw.getCountry()
                );
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse> fetchAllVendorBrands() {
        return apiInterface.fetchAllVendorBrands().map(new Function<FetchAllVendorBrandResponseNw, com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse>() {
            @Override
            public com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse apply(FetchAllVendorBrandResponseNw fetchAllVendorBrandResponseNw) throws Exception {
                List<com.razinggroups.domain.model.vendor.FetchAllVendorBrandRecordResponse> records = new ArrayList<>();
                for (FetchAllVendorBrandRecordResponseNw fetchAllVendorBrandRecordResponse : fetchAllVendorBrandResponseNw.getRecords()) {
                    com.razinggroups.domain.model.vendor.FetchAllVendorBrandRecordResponse recordResponse = new FetchAllVendorBrandRecordResponse(
                            fetchAllVendorBrandRecordResponse.getId(),
                            fetchAllVendorBrandRecordResponse.getVendorBrand()
                    );
                    records.add(recordResponse);
                }
                return new FetchAllVendorBrandResponse(records);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.vendor.FetchVendorServiceResponse> fetchVendorService(long id) {
        return apiInterface.fetchVendorService(id).map(new Function<FetchVendorServiceResponseNw, com.razinggroups.domain.model.vendor.FetchVendorServiceResponse>() {
            @Override
            public com.razinggroups.domain.model.vendor.FetchVendorServiceResponse apply(FetchVendorServiceResponseNw fetchVendorServiceResponseNw) throws Exception {
                List<com.razinggroups.domain.model.vendor.FetchVendorServiceRecordResponse> records = new ArrayList<>();
                for (FetchVendorServiceRecordResponseNw responseNw : fetchVendorServiceResponseNw.getRecords()) {
                    com.razinggroups.domain.model.vendor.FetchVendorServiceRecordResponse fetchVendorServiceRecordResponse = new FetchVendorServiceRecordResponse(
                            responseNw.getService()
                    );
                    records.add(fetchVendorServiceRecordResponse);
                }

                return new FetchVendorServiceResponse(records);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.vendorTask.FetchVendorTaks> fetchAllVendorTask() {
        return apiInterface.fetchAllVendorTask().map(new Function<FetchVendorTaksNw, com.razinggroups.domain.model.vendorTask.FetchVendorTaks>() {
            @Override
            public com.razinggroups.domain.model.vendorTask.FetchVendorTaks apply(FetchVendorTaksNw fetchVendorTaksNw) throws Exception {
                List<com.razinggroups.domain.model.vendorTask.FetchVendorTaksRecord> records = new ArrayList<>();

                for (FetchVendorTaksRecordNw recordNw : fetchVendorTaksNw.getRecords()) {
                    com.razinggroups.domain.model.vendorTask.FetchVendorTaksRecord record = new FetchVendorTaksRecord(
                            recordNw.getId(),
                            recordNw.getBrand(),
                            recordNw.getService(),
                            recordNw.getAssignDate(),
                            recordNw.getDeadlineDate(),
                            recordNw.getTaskDetail()
                    );
                    records.add(record);
                }

                return new FetchVendorTaks(fetchVendorTaksNw.getCount(), records);
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createVendorTask(CreateVendorTask createVendorTaskNw) {
        CreateVendorTaskNw vendorTaskNw = new CreateVendorTaskNw(
                createVendorTaskNw.getBrand(),
                createVendorTaskNw.getService(),
                createVendorTaskNw.getDeadline(),
                createVendorTaskNw.getTaskDetail(),
                createVendorTaskNw.getTaskFile()
        );

        return apiInterface.createVendorTask(vendorTaskNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<Boolean> setCredentials(String userName, String password) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                sharedPreferenceHelper.getSamplePreference()
                        .edit()
                        .putUserName(userName)
                        .putPassword(password)
                        .apply();

                return true;
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Credentials> getCredentials() {
        return Single.fromCallable(new Callable<com.razinggroups.domain.model.Credentials>() {
            @Override
            public com.razinggroups.domain.model.Credentials call() throws Exception {
                return new Credentials(sharedPreferenceHelper.getSamplePreference().getUserName(), sharedPreferenceHelper.getSamplePreference().getPassword());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.myTask.FetchAllMyTask> fetchEmployeeAllMyTask() {
        return apiInterface.fetchEmployeeAllMyTask(sharedPreferenceHelper.getSamplePreference().getEmployeeId()).map(new Function<FetchAllMyTaskNw, com.razinggroups.domain.model.myTask.FetchAllMyTask>() {
            @Override
            public com.razinggroups.domain.model.myTask.FetchAllMyTask apply(FetchAllMyTaskNw fetchAllMyTaskNw) throws Exception {
                List<com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords> fetchAllMyTaskRecordList = new ArrayList<>();
                for (FetchAllMyTaskRecordsNw fetchAllMyTaskRecordNw : fetchAllMyTaskNw.getRecords()) {
                    com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords fetchAllEmployeeTaskRecord =
                            new FetchAllMyTaskRecords(fetchAllMyTaskRecordNw.getId(),
                                    fetchAllMyTaskRecordNw.getTaskTitle(),
                                    fetchAllMyTaskRecordNw.getDeadline(),
                                    fetchAllMyTaskRecordNw.getTaskFile(),
                                    fetchAllMyTaskRecordNw.getTaskDetail(),
                                    fetchAllMyTaskRecordNw.getAssignDate(),
                                    fetchAllMyTaskRecordNw.getStatus()
                            );
                    fetchAllMyTaskRecordList.add(fetchAllEmployeeTaskRecord);
                }
                return new FetchAllMyTask(fetchAllMyTaskRecordList);
            }
        });    }

    @Override
    public Single<com.razinggroups.domain.model.Message> updateEmployeeMyTask(com.razinggroups.domain.model.SingleIdRequest singleIdRequest) {
        SingleIdRequestNw singleIdRequestNw = new SingleIdRequestNw(singleIdRequest.getId());
        return apiInterface.updateEmployeeMyTask(singleIdRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });
    }

    @Override
    public Single<com.razinggroups.domain.model.Message> deleteEmployeeMyTask(SingleIdRequest singleIdRequest) {
        return apiInterface.deleteEmployeeMyTask(new SingleIdRequestNw(singleIdRequest.getId())).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new com.razinggroups.domain.model.Message(messageNw.getMessage());
            }
        });    }

    @Override
    public Single<com.razinggroups.domain.model.Message> createEmployeeMyTask(CreateMyTaskRequest createMyTaskRequest) {
        CreateMyTaskRequestNw createMyTaskRequestNw = new CreateMyTaskRequestNw(
                createMyTaskRequest.getTaskTitle(),
                createMyTaskRequest.getDeadline(),
                createMyTaskRequest.getTaskFile(),
                createMyTaskRequest.getTaskDetail());
        createMyTaskRequestNw.setId(sharedPreferenceHelper.getSamplePreference().getEmployeeId());
        return apiInterface.createEmployeeMyTask(createMyTaskRequestNw).map(new Function<MessageNw, com.razinggroups.domain.model.Message>() {
            @Override
            public com.razinggroups.domain.model.Message apply(MessageNw messageNw) throws Exception {
                return new Message(messageNw.getMessage());
            }
        });    }

}
