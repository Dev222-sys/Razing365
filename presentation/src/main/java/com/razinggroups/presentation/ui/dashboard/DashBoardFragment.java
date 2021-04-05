package com.razinggroups.presentation.ui.dashboard;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.employee.EmployeeFragment;
import com.razinggroups.presentation.ui.main.MainActivity;
import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.model.employeeTask.FetchEmployeeTask;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;

import javax.inject.Inject;
import javax.inject.Named;

public class DashBoardFragment extends BaseFragment<DashBoardViewModel> implements DashBoardNavigator {

    @Inject
    @Named("DashBoardFragment")
    ViewModelProvider.Factory viewModelFactory;

    DashBoardViewModel dashBoardViewModel;

    TextView employeesCountTv, employessOnlineTv, activeTaskTv, myTasksTv, taskCompletedTv;
    LinearLayout employeesCountLayout, employeesOnlineLayout, activeTaskLayout, myTasksLayout, taskCompletedLayout;

    String userType = "";

    String userName = "";


    TextView welcomeTv;

    ProgressBar progressBar;
    LinearLayout mainLayout;


    @Override
    public DashBoardViewModel getViewModel() {
        return dashBoardViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        dashBoardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashBoardViewModel.class);
        dashBoardViewModel.setNavigator(this);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        userType = getArguments().getString("userType");
        userName = getArguments().getString("userName");
        initViews(view);

        dashBoardViewModel.fetchAllEmployees("all");


        dashBoardViewModel.fetchAllEmployees("online");
        dashBoardViewModel.fetchEmployeeTask("active");
        dashBoardViewModel.fetchEmployeeTask("complete");
        dashBoardViewModel.fetchAllMyTasks(userType);

        MainActivity activity = (MainActivity) getActivity();
        activity.setHeaderTitle("DASHBOARD");


        return view;
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.fragment_dashboard_pb);
        mainLayout = view.findViewById(R.id.fragment_dashboard_main_layout);
//        mainLayout.setVisibility(View.GONE);
//        progressBar.setVisibility(View.VISIBLE);

        welcomeTv = view.findViewById(R.id.fragment_dashboard_welcome_tv);
        welcomeTv.setText("Welcome " + userName);

        employeesCountTv = view.findViewById(R.id.fragment_dashboard_total_employees_count);
        employessOnlineTv = view.findViewById(R.id.fragment_dashboard_employees_online_count);
        activeTaskTv = view.findViewById(R.id.fragment_dashboard_active_task_count);
        myTasksTv = view.findViewById(R.id.fragment_dashboard_my_tasks_count);
        taskCompletedTv = view.findViewById(R.id.fragment_dashboard_task_completed_count);

        employeesCountLayout = view.findViewById(R.id.fragment_dashboard_total_employees_count_layout);
        employeesOnlineLayout = view.findViewById(R.id.fragment_dashboard_employees_online_count_layout);
        activeTaskLayout = view.findViewById(R.id.fragment_dashboard_active_task_count_layout);
        myTasksLayout = view.findViewById(R.id.fragment_dashboard_my_tasks_count_layout);
        taskCompletedLayout = view.findViewById(R.id.fragment_dashboard_task_completed_count_layout);


        employeesCountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeesFragment("all");
            }
        });

        employeesOnlineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeesFragment("online");
            }
        });
        activeTaskLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeTaskFragment("active");
            }
        });
        taskCompletedLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openEmployeeTaskFragment("complete");
            }
        });
        myTasksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonalTask();
            }
        });

    }

    private void openPersonalTask() {
        MainActivity activity = (MainActivity) getActivity();
        activity.openPersonalTaskFragment();
    }

    private void openEmployeesFragment(String str) {
//        MainActivity activity = (MainActivity) getActivity();
//        activity.openEmployeesFragment();


        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        EmployeeFragment holidayListFragment = new EmployeeFragment();
        transaction.replace(R.id.activity_main_frame, holidayListFragment);
        transaction.addToBackStack(null);

        Bundle bundle = new Bundle();
        bundle.putString("type", str);
        bundle.putString("OnlineEmployee", "1");

        holidayListFragment.setArguments(bundle);

        transaction.commit();

    }

    private void openEmployeeTaskFragment(String string) {
        MainActivity activity = (MainActivity) getActivity();
        activity.openEmployeeTaskFragment(string);
    }


    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(), "Error in api " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmployeeListLoaded(EmployeeList employeeList, String type) {
        long size = employeeList.getEmployeeDetailList().size();
        if (type.equalsIgnoreCase("all")) {
            employeesCountTv.setText("" + size);
        } else if (type.equalsIgnoreCase("online")) {
            employessOnlineTv.setText("" + size);
        }
    }

    @Override
    public void onEmployeeTaskLoaded(FetchEmployeeTask fetchEmployeeTask, String type) {
        long size = fetchEmployeeTask.getRecords().size();
        if (type.equalsIgnoreCase("active")) {
            activeTaskTv.setText("" + size);
        } else if (type.equalsIgnoreCase("complete")) {
            taskCompletedTv.setText("" + size);
        }

    }

    @Override
    public void onMyTaskLoaded(FetchAllMyTask fetchAllMyTask) {
        long size = fetchAllMyTask.getRecords().size();
        myTasksTv.setText("" + size);
    }
}
