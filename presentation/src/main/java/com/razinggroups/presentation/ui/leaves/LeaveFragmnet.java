package com.razinggroups.presentation.ui.leaves;

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

import com.razinggroups.domain.model.leave.FetchAllLeavesResponse;
import com.razinggroups.domain.model.leave.UpdateLeave;
import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;

import javax.inject.Inject;
import javax.inject.Named;

public class LeaveFragmnet extends BaseFragment<LeaveViewModel> implements LeaveNavigaor, LeaveListAdapter.onItemClick {

    @Inject
    @Named("LeaveFragmnet")
    ViewModelProvider.Factory viewModelFactory;

    LeaveViewModel leaveViewModel;

    ProgressBar progressBar;
    TextView errorTv;
    RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        leaveViewModel = ViewModelProviders.of(this, viewModelFactory).get(LeaveViewModel.class);
        leaveViewModel.setNavigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave, container, false);
        initViews(view);
        leaveViewModel.fetchData();
        return view;
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.fragment_leave_progress_bar);
        errorTv = view.findViewById(R.id.fragment_leave_error_tv);
        recyclerView = view.findViewById(R.id.fragment_leave_list_rv);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);

        errorTv.setVisibility(View.GONE);

    }


    @Override
    public LeaveViewModel getViewModel() {
        return leaveViewModel;
    }

    @Override
    public void onError(String toString) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(toString);
    }

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


    @Override
    public void onBtnClick(String taskId, String status) {
        leaveViewModel.updateLeave(new UpdateLeave(taskId, status));
        progressBar.setVisibility(View.VISIBLE);
    }
}
