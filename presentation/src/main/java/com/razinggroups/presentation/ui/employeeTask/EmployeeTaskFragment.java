package com.razinggroups.presentation.ui.employeeTask;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.employeeTask.employeeTaskDetail.EmployeeTaskDetailFragment;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;


import javax.inject.Inject;
import javax.inject.Named;

public class EmployeeTaskFragment extends BaseFragment<EmployeeTaskViewModel> implements EmployeeTaskNavigator, EmployeeTaskAdapter.onItemClick {

    @Inject
    @Named("EmployeeTaskFragment")
    ViewModelProvider.Factory viewModelFactory;

    EmployeeTaskViewModel employeeTaskViewModel;

    FrameLayout progressBarLayout;
    private RecyclerView recyclerView;
    TextView errorTv;
    EmployeeTaskAdapter adapter;

    String taskType = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        employeeTaskViewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeeTaskViewModel.class);
        employeeTaskViewModel.setNavigator(this);
//        employeeTaskViewModel.fetchAllTasks();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_task, container, false);
        recyclerView = view.findViewById(R.id.fragment_employee_task_rv);
        RecyclerView.LayoutManager layoutManagerFooter = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerFooter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        errorTv = view.findViewById(R.id.fragment_employee_task_error_tv);
        errorTv.setVisibility(View.GONE);
        progressBarLayout = view.findViewById(R.id.fragment_employee_task_progress_bar_layout);

        taskType = getArguments().getString("taskType");
        employeeTaskViewModel.fetchAllTasks(taskType);
        return view;

    }


    @Override
    public EmployeeTaskViewModel getViewModel() {
        return employeeTaskViewModel;
    }

    @Override
    public void onListLoaded(FetchEmployeeTask fetchEmployeeTask) {
        progressBarLayout.setVisibility(View.GONE);

        if (fetchEmployeeTask != null && fetchEmployeeTask.getRecords() != null && !fetchEmployeeTask.getRecords().isEmpty()) {
            if (fetchEmployeeTask.getRecords().get(0).getId() != null) {
                recyclerView.setVisibility(View.VISIBLE);
                adapter = new EmployeeTaskAdapter(fetchEmployeeTask.getRecords(), this);
                recyclerView.setAdapter(adapter);
            } else {

                errorTv.setVisibility(View.VISIBLE);
                errorTv.setText("There is some error in server. Please try again after some time.");
            }
        } else {
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText("No data to display");
        }
    }

    @Override
    public void onError(String toString) {
        progressBarLayout.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText("Error in loading api - " + toString);
    }

    @Override
    public void onItemClick(String taskId, String empId) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        EmployeeTaskDetailFragment employeeLeaveMainFragment = new EmployeeTaskDetailFragment();
        transaction.replace(R.id.activity_main_frame, employeeLeaveMainFragment);

        transaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putString("employeeId", empId);
        bundle.putString("taskId", taskId);
        employeeLeaveMainFragment.setArguments(bundle);

        transaction.commit();
    }
}
