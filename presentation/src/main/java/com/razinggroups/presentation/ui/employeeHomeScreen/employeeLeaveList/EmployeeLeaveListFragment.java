package com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeave;

import javax.inject.Inject;
import javax.inject.Named;

public class EmployeeLeaveListFragment extends BaseFragment<EmployeeLeaveListViewModel> implements EmployeeLeaveListNavigator {
    @Inject
    @Named("EmployeeLeaveListFragment")
    ViewModelProvider.Factory viewModelFactory;

    EmployeeLeaveListViewModel viewModel;
    long empId;


    ProgressBar progressBar;
    TextView errorTv;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeeLeaveListViewModel.class);
        viewModel.setNavigator(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_leave_list, container, false);
        progressBar = view.findViewById(R.id.fragment_employee_leave_list_progress_bar);
        errorTv = view.findViewById(R.id.fragment_employee_leave_list_error_tv);
        recyclerView = view.findViewById(R.id.fragment_employee_leave_list_rv);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);

        errorTv.setVisibility(View.GONE);

        empId = Long.parseLong(getArguments().getString("employeeId"));
        viewModel.fetchData(empId);


        return view;
    }

    @Override
    public EmployeeLeaveListViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onDataLoaded(FetchSingleEmployeeLeave fetchSingleEmployeeLeave) {
        progressBar.setVisibility(View.GONE);
        if (fetchSingleEmployeeLeave.getCount().equalsIgnoreCase("0")) {
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText("No Data To Display");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new EmployeeLeaveListAdapter(fetchSingleEmployeeLeave.getRecords()));
        }
    }

    @Override
    public void onError(String toString) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(toString);
    }
}
