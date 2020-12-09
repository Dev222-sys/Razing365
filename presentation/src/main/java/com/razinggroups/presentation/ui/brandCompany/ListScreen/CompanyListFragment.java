package com.razinggroups.presentation.ui.brandCompany.ListScreen;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.razinggroups.presentation.ui.brandCompany.BrandCompanyActivity;
import com.razinggroups.presentation.ui.brandCompany.BrandManipulateScreen.BrandManipulateActivity;
import com.razinggroups.presentation.ui.brandCompany.CompanyManipulateScreen.CompanyManipulateActivity;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;

import javax.inject.Inject;
import javax.inject.Named;

public class CompanyListFragment extends BaseFragment<CompanyListViewModel> implements CompanyListNavigator, CompanyListAdapter.onItemClick {
    @Inject
    @Named("CompanyListFragment")
    ViewModelProvider.Factory viewModelFactory;

    CompanyListViewModel viewModel;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView title;

    String listType = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CompanyListViewModel.class);
        viewModel.setNavigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_list, container, false);

        progressBar = view.findViewById(R.id.fragment_company_list_pb);
        title = view.findViewById(R.id.fragment_company_list_title);
        recyclerView = view.findViewById(R.id.fragment_company_list_rv);
        RecyclerView.LayoutManager layoutManagerFooter = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerFooter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);

        listType = getArguments().getString("listType");




        return view;
    }


    @Override
    public CompanyListViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onCompanyDataLoaded(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new CompanyListAdapter(fetchAllCompanyDetailsResponse.getRecords(), "company", this));

    }

    @Override
    public void getBrandDataLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new CompanyListAdapter(this, fetchAllBrandDetailsResponse.getRecords(), "brand"));

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
    public void onCompanyItemClick(String id) {
        Intent i = new Intent(getActivity(), CompanyManipulateActivity.class);
        i.putExtra("mode", "edit");
        i.putExtra("companyId", id);

        startActivity(i);
    }

    @Override
    public void onBrandItemClick(String id) {
        Intent i = new Intent(getActivity(), BrandManipulateActivity.class);
        i.putExtra("mode", "edit");
        i.putExtra("brandId", id);

        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        if (listType.equalsIgnoreCase("company")) {
            title.setText("Company List");
            viewModel.fetchCompanyData();
        } else if (listType.equalsIgnoreCase("brand")) {
            title.setText("Brand List");
            viewModel.fetchBrandData();
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        BrandCompanyActivity brandCompanyActivity = (BrandCompanyActivity) getActivity();
        brandCompanyActivity.setLayoutVisibility(true);
    }
}
