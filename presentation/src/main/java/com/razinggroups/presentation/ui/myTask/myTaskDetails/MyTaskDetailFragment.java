package com.razinggroups.presentation.ui.myTask.myTaskDetails;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.domain.model.myTask.FetchAllMyTask;
import com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords;

import javax.inject.Inject;
import javax.inject.Named;

public class MyTaskDetailFragment extends BaseFragment<MyTaskDetailViewModel> implements MyTaskDetailNavigator {

    @Inject
    @Named("MyTaskDetailFragment")
    ViewModelProvider.Factory viewModelFactory;

    MyTaskDetailViewModel viewModel;


    TextView errorTv;
    ProgressBar progressBar;

    RelativeLayout mainLayout;

    TextView title, taskTitle, taskDesc, dueDate, createdDate;
    Button deleteBtn, completeBtn, labelBtn;

    String taskId = "";
    private String userType="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyTaskDetailViewModel.class);
        viewModel.setNavigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        errorTv = view.findViewById(R.id.fragment_task_detail_error_tv);
        progressBar = view.findViewById(R.id.fragment_task_detail_progress_bar);
        errorTv.setVisibility(View.GONE);

        title = view.findViewById(R.id.fragment_task_detail_titile_tv);
        taskTitle = view.findViewById(R.id.fragment_task_detail_task_title);
        taskDesc = view.findViewById(R.id.fragment_task_detail_task_desc);
        dueDate = view.findViewById(R.id.fragment_task_detail_due_date);
        createdDate = view.findViewById(R.id.fragment_task_detail_created_date);
        deleteBtn = view.findViewById(R.id.fragment_task_detail_delete_btn);
        completeBtn = view.findViewById(R.id.fragment_task_detail_complete_btn);
        labelBtn = view.findViewById(R.id.fragment_task_detail_status_btn);
        mainLayout = view.findViewById(R.id.fragment_task_detail_layout);


        mainLayout.setVisibility(View.GONE);

        taskId = getArguments().getString("taskId");
        userType = getArguments().getString("userType");
        viewModel.fetchData(userType);

        return view;
    }

    @Override
    public void onDataLoaded(FetchAllMyTask fetchAllMyTask) {

        progressBar.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
        com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords record = new com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords();
        for (com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords fetchAllEmployeeTaskRecord : fetchAllMyTask.getRecords()) {
            if (fetchAllEmployeeTaskRecord.getId().equalsIgnoreCase(taskId)) {
                record = fetchAllEmployeeTaskRecord;
            }
        }

        title.setText("This task is private to you.");
        taskTitle.setText(record.getTaskTitle());
        taskDesc.setText(record.getTaskDetail());
        dueDate.setText(record.getDeadline());
        createdDate.setText(record.getAssignDate());
        if (record.getStatus().equalsIgnoreCase("pending")) {
            labelBtn.setText("Pending");
            completeBtn.setVisibility(View.VISIBLE);
            labelBtn.setBackgroundColor(labelBtn.getContext().getResources().getColor(R.color.colorYellow));
        } else {

            labelBtn.setText("Completed");
            completeBtn.setVisibility(View.GONE);
            labelBtn.setBackgroundColor(labelBtn.getContext().getResources().getColor(R.color.colorGreen));
        }
        FetchAllMyTaskRecords finalRecord = record;
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.deleteTask(finalRecord.getId(),userType);
            }
        });
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.updateTask(finalRecord.getId(),userType);
            }
        });


    }

    @Override
    public void onResponse(String message) {
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


    @Override
    public void onUpdate(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                viewModel.fetchData(userType);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(message);
    }

    @Override
    public MyTaskDetailViewModel getViewModel() {
        return viewModel;
    }
}
