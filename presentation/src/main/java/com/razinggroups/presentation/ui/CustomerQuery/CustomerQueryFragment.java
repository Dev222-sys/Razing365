package com.razinggroups.presentation.ui.CustomerQuery;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.CustomerQuery.FetchAllCustomerQuerryResponse;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.employee.EditEmployee.EditEmployeeViewModel;
import com.razinggroups.presentation.ui.myTask.createMyTask.ItemData;
import com.razinggroups.presentation.ui.myTask.createMyTask.SpinnerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerQueryFragment extends Fragment{

    /*@Inject
    @Named("LeaveFragmnet")*/

    ViewModelProvider.Factory viewModelFactory;

    CustomerQueryViewModel viewModel;

    ProgressBar progressBar;
    TextView errorTv;
    RecyclerView recyclerView;
    EditText et_name,et_company_email,et_mobile_no,et_landline_no,et_passport_no,et_nationality,et_enquiry,et_address;
    Spinner profession,reference,lead_type_spinner;
    Button Submit_Query;
    Customer customer=new Customer();
    TextView lead_type_tv;


/*

    @Override
    public CustomerQueryViewModel getViewModel() {

        return viewModel;
    }
*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CustomerQueryViewModel.class);
        viewModel.setNavigator(this);*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_query, container, false);
        initViews(view);



        String username = SharedPrefManager.getInstans(getActivity()).getUsername();

        Submit_Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

              ///  String name=((ItemData) profession.getSelectedItem()).getName();
                if (et_name.getText().toString().isEmpty())
                {
                    et_name.setError("Please Enter Name");
                    et_name.requestFocus();

                    progressBar.setVisibility(View.INVISIBLE);
                }else if (et_company_email.getText().toString().isEmpty())
                {
                    et_company_email.setError("Please Enter Company Email");
                    et_company_email.requestFocus();

                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if( et_mobile_no.getText().toString().isEmpty())
                {
                    et_mobile_no.setError("Please Enter Mobile Number");
                    et_mobile_no.requestFocus();

                    progressBar.setVisibility(View.INVISIBLE);
                }else  if (et_nationality.getText().toString().isEmpty())
                {
                    et_nationality.setError("Please Enter your Nationality");
                    et_nationality.requestFocus();

                    progressBar.setVisibility(View.INVISIBLE);
                }else if (lead_type_spinner.getSelectedItemPosition()==0)
                {

                    Toast.makeText(getActivity(), "Please Select Lead Type", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);

                }else if (reference.getSelectedItemPosition()==0)
                {

                    Toast.makeText(getActivity(), "Please Select Reference Through", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);

                }else if (profession.getSelectedItemPosition()==0)
                {

                    Toast.makeText(getActivity(), "Please Select Profession", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);

                }else {
                    SubmitQuery();


                }



            }
        });

        //leaveViewModel.fetchData();
        return view;
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.CustomerQueryFragment_progress_bar);

        et_name=view.findViewById(R.id.fragment_customer_query_name_et);
        et_company_email=view.findViewById(R.id.fragment_customer_query_officelandet);
        et_mobile_no=view.findViewById(R.id.fragment_customer_query_mobileetno);
        et_landline_no=view.findViewById(R.id.fragment_employee_detail_officialemailidet);
        et_passport_no=view.findViewById(R.id.fragment_customer_query_pasportnoet);
        et_nationality=view.findViewById(R.id.fragment_customer_query_nationalityet);
        et_enquiry=view.findViewById(R.id.fragment_employee_apply_leave_desc);
        et_address=view.findViewById(R.id.fragment_employee_detail_officeAddresset);
        profession=view.findViewById(R.id.fragment_Brand_spinner);
        reference=view.findViewById(R.id.fragment_status_spinner);
        lead_type_spinner=view.findViewById(R.id.lead_type_spinner);
        Submit_Query=view.findViewById(R.id.fragment_customerquery_submit_btn);
        lead_type_tv=view.findViewById(R.id.lead_type_tv);



        loadStatusSpinner();
        referenceSpinner();
        lead_type_spinner();

        /*

        ((ItemData) profession.getSelectedItem()).getName();
*/





    }

    private void lead_type_spinner() {
        List<ItemData> statusData = new ArrayList<>();
        statusData.add(new ItemData("Select  Type", "0"));
        statusData.add(new ItemData("Cold Lead", "1"));
        statusData.add(new ItemData("Hot Lead", "2"));
        statusData.add(new ItemData("Warm Lead", "3"));



        //statusData.add(new ItemData("Ex Employee", "2"));

        lead_type_spinner.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, statusData));


    }


    private void referenceSpinner() {

        List<ItemData> statusData = new ArrayList<>();
        statusData.add(new ItemData("Select Reference ", "0"));
        statusData.add(new ItemData("Friends", "1"));
        statusData.add(new ItemData("Social Media", "2"));
        statusData.add(new ItemData("Channel  Partner", "3"));



        //statusData.add(new ItemData("Ex Employee", "2"));

        reference.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, statusData));
    }
    private void loadStatusSpinner() {
        List<ItemData> statusData = new ArrayList<>();
        statusData.add(new ItemData("Select Profession", "0"));
        statusData.add(new ItemData("Bussniess", "1"));

        //statusData.add(new ItemData("Ex Employee", "2"));

        profession.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, statusData));
    }


    public void SubmitQuery() {


            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi().submitquery(SharedPrefManager.getInstans(getActivity()).getUsername(),
                            ((ItemData) lead_type_spinner.getSelectedItem()).getName(),
                            et_name.getText().toString(),
                            et_company_email.getText().toString()
                            ,et_mobile_no.getText().toString(),
                            et_nationality.getText().toString()
                            ,et_landline_no.getText().toString(),
                            ((ItemData) reference.getSelectedItem()).getName(),

                            ((ItemData) profession.getSelectedItem()).getName(),
                            et_address.getText().toString(),
                            et_passport_no.getText().toString(),
                            et_enquiry.getText().toString() );

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    String s = null;
                    if (response.code()==200)
                    {
                        try {

                            s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            String msg = jsonObject.getString("message");

//                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            onCreatealert(msg);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }




                    }
                    else
                    {

                        progressBar.setVisibility(View.INVISIBLE);



                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();


                }
            });

        }



    public void onCreatealert(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                getActivity().onBackPressed();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private Customer createRequest() {
        return new Customer(
                ((ItemData) lead_type_spinner.getSelectedItem()).getName(),
                et_name.getText().toString(),
                et_company_email.getText().toString()
                ,et_mobile_no.getText().toString()
                ,et_landline_no.getText().toString(),
                et_passport_no.getText().toString(),
                et_nationality.getText().toString(),
                et_enquiry.getText().toString(),
                ((ItemData) reference.getSelectedItem()).getName(),
                et_address.getText().toString(),
                ((ItemData) profession.getSelectedItem()).getName()

        );


    }
/*
    @Override
    public void onError(String toString) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(toString);
    }*/
/*

    @Override
    public void onDataLoaded(FetchAllLeavesResponse fetchAllLeavesResponse) {
        progressBar.setVisibility(View.GONE);
        if (fetchAllLeavesResponse.getCount().equalsIgnoreCase("0")) {
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText("No Data To Display");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new LeaveListAdapter(fetchAllLeavesResponse.getRecords(), this));
        }

    }

    @Override
    public void onUpdateResponse(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                leaveViewModel.fetchData();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

*/

/*    @Override
    public void onBtnClick(String taskId, String status) {
        leaveViewModel.updateLeave(new UpdateLeave(taskId, status));
        progressBar.setVisibility(View.VISIBLE);
    }*/
}
