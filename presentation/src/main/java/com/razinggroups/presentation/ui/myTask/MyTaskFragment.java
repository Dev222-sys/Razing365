package com.razinggroups.presentation.ui.myTask;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.myTask.createMyTask.CreateMyTaskFragment;
import com.razinggroups.presentation.ui.myTask.myTaskDetails.MyTaskDetailFragment;

import javax.inject.Inject;
import javax.inject.Named;

public class MyTaskFragment extends BaseFragment<MyTaskViewModel> implements MyTaskNavigator, MyTaskAdapter.onItemClick {
    @Inject
    @Named("MyTaskFragment")
    ViewModelProvider.Factory viewModelFactory;

    MyTaskViewModel myTaskViewModel;

    TextView addTaskTv, errorTv;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MyTaskAdapter adapter;

    String userType = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        myTaskViewModel = ViewModelProviders.of(this, viewModelFactory).get(MyTaskViewModel.class);
        myTaskViewModel.setNavigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_task, container, false);
        userType = getArguments().getString("userType");
        myTaskViewModel.fetchMyTasks(userType);
        initViews(view);


        return view;
    }

    private void initViews(View view) {
        addTaskTv = view.findViewById(R.id.fragment_my_task_tv);
        progressBar = view.findViewById(R.id.fragment_my_task_pb);
        errorTv = view.findViewById(R.id.fragment_my_task_error_tv);
        errorTv.setVisibility(View.GONE);

        recyclerView = view.findViewById(R.id.fragment_my_task_rv);
        RecyclerView.LayoutManager layoutManagerFooter = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerFooter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);

        addTaskTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                CreateMyTaskFragment createMyTaskFragment = new CreateMyTaskFragment();

                if (userType.equalsIgnoreCase("EmployeeMyTask")) {
                    transaction.replace(R.id.activity_employee_home_frame_layout, createMyTaskFragment);

                } else {
                    transaction.replace(R.id.activity_main_frame, createMyTaskFragment);
                }

                transaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("userType", userType);
                createMyTaskFragment.setArguments(bundle);

                transaction.commit();

            }
        });
    }

    @Override
    public MyTaskViewModel getViewModel() {
        return myTaskViewModel;
    }

    @Override
    public void onDataLoaded(FetchAllMyTask fetchAllMyTask) {
        if (fetchAllMyTask != null && fetchAllMyTask.getRecords() != null && !fetchAllMyTask.getRecords().isEmpty()) {
            if (fetchAllMyTask.getRecords().get(0).getId() != null) {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                errorTv.setVisibility(View.GONE);
                adapter = new MyTaskAdapter(fetchAllMyTask.getRecords(), this);
                recyclerView.setAdapter(adapter);
            } else {

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                errorTv.setVisibility(View.VISIBLE);
                errorTv.setText("There is some error in server. Please try again after some time.");

            }
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText("No Data to Display");
        }
    }

    @Override
    public void onError(String e) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(e);

    }

    @Override
    public void onItemClick(String taskId) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MyTaskDetailFragment myTaskDetailFragment = new MyTaskDetailFragment();


        if (userType.equalsIgnoreCase("EmployeeMyTask")) {
            transaction.replace(R.id.activity_employee_home_frame_layout, myTaskDetailFragment);

        } else {
            transaction.replace(R.id.activity_main_frame, myTaskDetailFragment);
        }

        transaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putString("taskId", taskId);
        bundle.putString("userType", userType);
        myTaskDetailFragment.setArguments(bundle);

        transaction.commit();

    }
}
