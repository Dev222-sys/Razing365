package com.razinggroups.presentation.ui.CustomerQuery;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.CustomerQuery.FetchAllCustomerQuerryResponse;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.myTask.createMyTask.ItemData;
import com.razinggroups.presentation.ui.myTask.createMyTask.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class CustomerQueryFragment extends BaseFragment<CustomerQueryViewModel> implements CustomerQueryNavigaor {

    /*@Inject
    @Named("LeaveFragmnet")*/

    ViewModelProvider.Factory viewModelFactory;

    CustomerQueryViewModel customerQueryViewModel;

    ProgressBar progressBar;
    TextView errorTv;
    RecyclerView recyclerView;
    EditText et_name,et_company_email,et_mobile_no,et_landline_no,et_passport_no,et_nationality,et_enquiry,et_address;
    Spinner profession,reference,lead_type_spinner;
    Button Submit_Query;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        customerQueryViewModel = ViewModelProviders.of(this, viewModelFactory).get(LeaveViewModel.class);
        leaveViewModel.setNavigator(this);*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_query, container, false);

        initViews(view);
        Submit_Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=((ItemData) profession.getSelectedItem()).getName();

                Toast.makeText(getActivity(), name+"", Toast.LENGTH_SHORT).show();

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


    @Override
    public CustomerQueryViewModel getViewModel() {
        return customerQueryViewModel;
    }


    @Override
    public void onLoginSuccess(Customer customer) {


    }

    @Override
    public void onError(String toString) {

    }

    @Override
    public void onDataLoaded(FetchAllCustomerQuerryResponse fetchAllLeavesResponse) {

    }

    @Override
    public void onUpdateResponse(String message) {

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
