package com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeTaskDetailList;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;

import javax.inject.Inject;
import javax.inject.Named;

public class EmployeeTaskDetailListFragment extends BaseFragment<EmployeeTaskDetailListViewModel> implements EmployeeTaskDetailListNavigator, EmployeeTaskDetailListAdapter.onItemClick {
    @Inject
    @Named("EmployeeTaskDetailListFragment")
    ViewModelProvider.Factory viewModelFactory;

    EmployeeTaskDetailListViewModel viewModel;

    TextView errorTv, welcomeTv;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    long empId;
    String userName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeeTaskDetailListViewModel.class);
        viewModel.setNavigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_task_details, container, false);
        errorTv = view.findViewById(R.id.fragment_employee_task_details_error_tv);
        progressBar = view.findViewById(R.id.fragment_employee_task_details_progress_bar);
        errorTv.setVisibility(View.GONE);

        welcomeTv = view.findViewById(R.id.fragment_employee_task_details_welcome_tv);

        recyclerView = view.findViewById(R.id.fragment_employee_task_details_rv);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);

        empId = Long.parseLong(getArguments().getString("employeeId"));
        userName = getArguments().getString("userName");
        welcomeTv.setText("Welcome " + userName + " !");
        viewModel.fetchData(empId);

        return view;
    }

    @Override
    public EmployeeTaskDetailListViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onError(String toString) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(toString);
    }

    @Override
    public void onDataLoaded(FetchEmployeeTask fetchEmployeeTask) {
        progressBar.setVisibility(View.GONE);
        if (fetchEmployeeTask.getCount().equalsIgnoreCase("0")) {
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText("No Data To Display");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new EmployeeTaskDetailListAdapter(fetchEmployeeTask.getRecords(), this));
        }
    }


    @Override
    public void onBtnClick(String taskId) {
        viewModel.updateTask(taskId);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUpdate(String toString) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(toString).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                viewModel.fetchData(empId);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
