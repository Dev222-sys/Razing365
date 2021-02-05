package com.razinggroups.data.network;

import com.razinggroups.data.models.CustomerQuerry.CustomerNw;
import com.razinggroups.data.models.SingleIdRequestNw;
import com.razinggroups.data.models.Employee.EmployeeDetailNw;
import com.razinggroups.data.models.Employee.EmployeeListNw;
import com.razinggroups.data.models.MessageNw;
import com.razinggroups.data.models.brand.CreateBrandRequestNw;
import com.razinggroups.data.models.brand.FetchAllBrandDetailsRecordResponseNw;
import com.razinggroups.data.models.brand.FetchAllBrandDetailsResponseNw;
import com.razinggroups.data.models.brand.FetchAllBrandNameResponseNw;
import com.razinggroups.data.models.company.CreateCompanyRequestnw;
import com.razinggroups.data.models.company.FetchAllCompanyDetailsRecordResponseNw;
import com.razinggroups.data.models.company.FetchAllCompanyDetailsResponseNw;
import com.razinggroups.data.models.company.FetchAllCompanyNamesResponseNw;
import com.razinggroups.data.models.employeeTask.CreateEmployeeTaskRequestNw;
import com.razinggroups.data.models.employeeTask.FetchEmployeeTaskNw;
import com.razinggroups.data.models.holiday.HolidayResponseNw;
import com.razinggroups.data.models.leave.CreateLeaveRequestNw;
import com.razinggroups.data.models.leave.FetchAllLeavesResponseNw;
import com.razinggroups.data.models.leave.FetchSingleEmployeeLeaveNw;
import com.razinggroups.data.models.leave.UpdateLeaveNw;
import com.razinggroups.data.models.login.LoginNw;
import com.razinggroups.data.models.myTask.CreateMyTaskRequestNw;
import com.razinggroups.data.models.myTask.FetchAllMyTaskNw;
import com.razinggroups.data.models.vendor.FetchAllVendorBrandResponseNw;
import com.razinggroups.data.models.vendor.FetchAllVendorResponseNw;
import com.razinggroups.data.models.vendor.FetchSingleVendorResposneNw;
import com.razinggroups.data.models.vendor.FetchVendorServiceResponseNw;
import com.razinggroups.data.models.vendorTask.CreateVendorTaskNw;
import com.razinggroups.data.models.vendorTask.FetchVendorTaksNw;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    //Employee
    @GET("product/read_employee.php")
    Single<EmployeeListNw> fetchAllEmployees();

    @GET("product/read_one.php")
    Single<EmployeeDetailNw> fetchEmployee(@Query("id") long id);

    @POST("product/delete.php")
    Single<MessageNw> deleteEmployee(@Body SingleIdRequestNw singleIdRequestNw);

    @POST("product/update.php")
    Single<MessageNw> updateEmployee(@Body EmployeeDetailNw employeeDetailNw);

    @POST("product/update.php")
    Single<MessageNw> custoer_query(@Body CustomerNw customerNw);

    @POST("product/employee_online.php")
    Single<EmployeeListNw> fetchOnlineEmployees();


    //Login
    @GET("login/read.php")
    Single<LoginNw> login(@Query("uemail") String userName, @Query("pass") String passsword);


    //Gernal form Queryy
    @FormUrlEncoded
    @POST("customer_query/create.php/")
    Call<ResponseBody> submitQueryGenerate(
            @Field("created_by") String created_by,
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("landline") String landline,
            @Field("passport") String passport,
            @Field("nationality") String nationality,
            @Field("permanent_address") String permanent_address,
            @Field("pa_pincode") String pa_pincode,
            @Field("residency_address") String residency_address,
            @Field("ra_pincode") String ra_pincode);


    // Family Form Queryy
    @FormUrlEncoded
    @POST("customer_query/add_family.php/")
    Call<ResponseBody> submitQueryFamily(
            @Field("kyc_id") String kyc_id,
            @Field("relation") String relation,
            @Field("fullname") String fullname,
            @Field("passport_no") String passport_no,
            @Field("age") String age,
            @Field("passport_copy") String passport_copy);

    // submitQueryFinal
    @FormUrlEncoded
    @POST("customer_query/add_question_details.php/")
    Call<ResponseBody> submitQueryFinal(
            @Field("kyc_id") String kyc_id,
            @Field("single_applicant") String single_applicant,
            @Field("main_applicant_name") String main_applicant_name,
            @Field("apply_family") String apply_family,
            @Field("employeement_type") String employeement_type,
            @Field("name_of_org") String name_of_org,
            @Field("uk_visa_status") String uk_visa_status,
            @Field("uk_year") String uk_year,
            @Field("uk_reasion") String uk_reasion,
            @Field("european_visa_status") String european_visa_status,
            @Field("european_visa_year") String european_visa_year,
            @Field("european_visa_reasion") String european_visa_reasion,
            @Field("usa_visa_status") String usa_visa_status,
            @Field("usa_year") String usa_year,
            @Field("usa_reasion") String usa_reasion,
            @Field("interested_type") String interested_type,

            @Field("migrate_plan") String migrate_plan,

            @Field("investment_budget") String investment_budget,
            @Field("reference_through") String reference_through,
            @Field("platform") String platform,
            @Field("contact_through") String contact_through,
            @Field("subscribe_email") String subscribe_email


    );

    //get Query data
    @GET("customer_query/read.php/")
    Call<ResponseBody> readQuery(
    );

    //delete Query
    @FormUrlEncoded
    @POST("customer_query/delete.php/")
    Call<ResponseBody> deleteQuery(
            @Field("id") String id

    );


    //logout
    @FormUrlEncoded
    @POST("logout.php/")
    Call<ResponseBody> logout(
            @Field("uemail") String email);

    //My Task
    @GET("mytask/read.php")
    Single<FetchAllMyTaskNw> fetchAllMyTask();

    @POST("mytask/create.php")
    Single<MessageNw> createMyTask(@Body CreateMyTaskRequestNw createMyTaskRequestNw);

    @POST("mytask/update.php")
    Single<MessageNw> updateMyTask(@Body SingleIdRequestNw singleIdRequestNw);

    @POST("mytask/delete.php")
    Single<MessageNw> deleteMyTask(@Body SingleIdRequestNw singleIdRequestNw);


    //Employee Task
    @GET("employeetask/read.php")
    Single<FetchEmployeeTaskNw> fetchAllEmployeeTask();

    @POST("employeetask/create.php")
    Single<MessageNw> createEmployeeTask(@Body CreateEmployeeTaskRequestNw createEmployeeTaskRequestNw);

    @POST("employeetask/update.php")
    Single<MessageNw> updateEmployeeTask(@Body SingleIdRequestNw singleIdRequestNw);

    @POST("employeetask/delete.php")
    Single<MessageNw> deleteEmployeeTask(@Body SingleIdRequestNw singleIdRequestNw);

    @GET("employeetask/active_task.php")
    Single<FetchEmployeeTaskNw> fetchActiveEmployeeTask();

    @GET("employeetask/complete_task.php")
    Single<FetchEmployeeTaskNw> fetchCompleteEmployeeTask();

    @GET("employeetask/readone.php")
    Single<FetchEmployeeTaskNw> fetchSingleEmployeeTask(@Query("id") long id);


    //Holidays
    @GET("login/duholiday.php")
    Single<HolidayResponseNw> fetchDuHolidays();

    @GET("login/ivsdelhiholiday.php")
    Single<HolidayResponseNw> fetchDelhiHolidays();

    @GET("login/ivskolkataholiday.php")
    Single<HolidayResponseNw> fetchKolkataHolidays();

    @GET("login/ivsmumbaiholiday.php")
    Single<HolidayResponseNw> fetchMumbaiHolidays();

    @GET("login/ivsbangaloreholiday.php")
    Single<HolidayResponseNw> fetchBangaloreHolidays();

    @GET("login/ivssrilankaholiday.php")
    Single<HolidayResponseNw> fetchSriLankaHolidays();


    //Brand
    @GET("login/readbrand.php")
    Single<FetchAllBrandNameResponseNw> fetchAllBrandNames();

    @GET("brand/read.php")
    Single<FetchAllBrandDetailsResponseNw> fetchAllBrandDetails();

    @POST("brand/create.php")
    Single<MessageNw> createBrand(@Body CreateBrandRequestNw createBrandRequestNw);

    @POST("brand/update.php")
    Single<MessageNw> updateBrand(@Body CreateBrandRequestNw createBrandRequestNw);

    @POST("brand/delete.php")
    Single<MessageNw> deleteBrand(@Body SingleIdRequestNw singleIdRequestNw);

    @GET("brand/read.php")
    Single<FetchAllBrandDetailsRecordResponseNw> fetchSingleBrandDetails(@Query("id") long id);

    //Company
    @GET("login/readbrand.php")
    Single<FetchAllCompanyNamesResponseNw> fetchAllCompanyNames();

    @GET("company/read.php")
    Single<FetchAllCompanyDetailsResponseNw> fetchAllCompanyDetails();

    @POST("company/create.php")
    Single<MessageNw> createCompany(@Body CreateCompanyRequestnw createCompanyRequestnw);

    @POST("company/update.php")
    Single<MessageNw> updateCompany(@Body CreateCompanyRequestnw createBrandRequestNw);

    @POST("company/delete.php")
    Single<MessageNw> deleteCompany(@Body SingleIdRequestNw singleIdRequestNw);

    @GET("company/read.php")
    Single<FetchAllCompanyDetailsRecordResponseNw> fetchSingleCompanyDetails(@Query("id") long id);


    //Vendor
    @GET("vendor/read.php")
    Single<FetchAllVendorResponseNw> fetchAllVendor();

    @GET("vendor/readone.php")
    Single<FetchSingleVendorResposneNw> fetchSingleVendor(@Query("id") long id);

    @POST("vendor/create.php")
    Single<MessageNw> createVendor(@Body FetchSingleVendorResposneNw fetchSingleVendorResposneNw);

    @POST("vendor/update.php")
    Single<MessageNw> updateVendor(@Body FetchSingleVendorResposneNw createBrandRequestNw);

    @POST("vendor/delete.php")
    Single<MessageNw> deleteVendor(@Body SingleIdRequestNw singleIdRequestNw);

    @GET("vendor/read_brand.php")
    Single<FetchAllVendorBrandResponseNw> fetchAllVendorBrands();

    @GET("vendor/read_service.php")
    Single<FetchVendorServiceResponseNw> fetchVendorService(@Query("id") long id);

    //Vendor Task

    @GET("vendor_task/read.php")
    Single<FetchVendorTaksNw> fetchAllVendorTask();

    @POST("vendor_task/create.php")
    Single<MessageNw> createVendorTask(@Body CreateVendorTaskNw createVendorTaskNw);

    @POST("vendor_task/delete.php")
    Single<MessageNw> deleteVendorTask(@Body SingleIdRequestNw singleIdRequestNw);

    //Leave
    @POST("leave/create.php")
    Single<MessageNw> createLeave(@Body CreateLeaveRequestNw createLeaveRequestNw);

    @GET("leave/read.php")
    Single<FetchAllLeavesResponseNw> fetchAllLeaves();

    @GET("leave/readone.php")
    Single<FetchSingleEmployeeLeaveNw> fetchEmployeeLeaves(@Query("id") long id);

    @POST("leave/update.php")
    Single<MessageNw> updateLeave(@Body UpdateLeaveNw updateLeaveNw);


    //Admin My Task
    @GET("admintask/readone.php")
    Single<FetchAllMyTaskNw> fetchAdminAllMyTask(@Query("id") long id);

    @POST("admintask/create.php")
    Single<MessageNw> createAdminMyTask(@Body CreateMyTaskRequestNw createMyTaskRequestNw);

    @POST("admintask/update.php")
    Single<MessageNw> updateAdminMyTask(@Body SingleIdRequestNw singleIdRequestNw);

    @POST("admintask/delete.php")
    Single<MessageNw> deleteAdminMyTask(@Body SingleIdRequestNw singleIdRequestNw);


    //Employee My Task
    @GET("empmytask/readone.php")
    Single<FetchAllMyTaskNw> fetchEmployeeAllMyTask(@Query("id") long id);

    @POST("empmytask/create.php")
    Single<MessageNw> createEmployeeMyTask(@Body CreateMyTaskRequestNw createMyTaskRequestNw);

    @POST("empmytask/update.php")
    Single<MessageNw> updateEmployeeMyTask(@Body SingleIdRequestNw singleIdRequestNw);

    @POST("empmytask/delete.php")
    Single<MessageNw> deleteEmployeeMyTask(@Body SingleIdRequestNw singleIdRequestNw);


}
