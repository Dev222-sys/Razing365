package com.razinggroups.presentation.ui.vendor.vendorTaskList;

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
import com.razinggroups.presentation.ui.vendor.VendorActivity;
import com.razinggroups.domain.model.vendorTask.FetchVendorTaks;

import javax.inject.Inject;
import javax.inject.Named;

public class VendorTaskListFragment extends BaseFragment<VendorTaskListViewModel> implements VendorTaskListNavigator, VendorTaskListAdapter.onItemClick {

    @Inject
    @Named("VendorTaskListFragment")
    ViewModelProvider.Factory viewModelFactory;

    VendorTaskListViewModel viewModel;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView title;

    @Override
    public VendorTaskListViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(VendorTaskListViewModel.class);
        viewModel.setNavigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_list, container, false);

        progressBar = view.findViewById(R.id.fragment_vendor_list_pb);
        title = view.findViewById(R.id.fragment_company_list_title);

        recyclerView = view.findViewById(R.id.fragment_vendor_list_rv);
        RecyclerView.LayoutManager layoutManagerFooter = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerFooter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);


        viewModel.fetchData();


        return view;
    }

    @Override
    public void onDelete(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                viewModel.fetchData();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onDataLoaded(FetchVendorTaks fetchVendorTaks) {
        if (fetchVendorTaks != null && fetchVendorTaks.getRecords() != null) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new VendorTaskListAdapter(fetchVendorTaks.getRecords(), this));
        }
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Try again after some time " + message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
    public void onDeleteItemClick(String id) {
        progressBar.setVisibility(View.VISIBLE);
        viewModel.delete(id);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        VendorActivity vendorActivity = (VendorActivity) getActivity();
        vendorActivity.setLayoutVisibility(true);
    }
}
