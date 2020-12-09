package com.razinggroups.presentation.ui.employee;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.employee.EditEmployee.EditEmployeeFragment;
import com.razinggroups.presentation.ui.main.MainActivity;
import com.razinggroups.domain.model.employee.EmployeeList;

import javax.inject.Inject;
import javax.inject.Named;

public class EmployeeFragment extends BaseFragment<EmployeeViewModel> implements EmployeeNavigator, EmployeeListAdapter.onItemClick {

    @Inject
    @Named("EmployeeFragment")
    ViewModelProvider.Factory viewModelFactory;

    EmployeeViewModel employeeViewModel;

    EditText nameEt, brandEt;
    Button searchBtn, viewAllBtn;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    String type = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        employeeViewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeeViewModel.class);
        employeeViewModel.setNavigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);
        initViews(view);
        type = getArguments().getString("type");
        employeeViewModel.fetchAllEmployees(type);
        MainActivity activity = (MainActivity) getActivity();
        activity.setHeaderTitle("EMPLOYEE");

        return view;
    }

    private void initViews(View view) {
        nameEt = view.findViewById(R.id.fragment_employee_search_et);
        brandEt = view.findViewById(R.id.fragment_employee_brand_et);
        searchBtn = view.findViewById(R.id.fragment_employee_search_btn);
        viewAllBtn = view.findViewById(R.id.fragment_employee_view_all_btn);
        recyclerView = view.findViewById(R.id.fragment_employee_rv);
        RecyclerView.LayoutManager layoutManagerFooter = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerFooter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressBar = view.findViewById(R.id.fragment_employee_progress_bar);

        nameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
//                      brandEt.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        brandEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    public EmployeeViewModel getViewModel() {
        return employeeViewModel;
    }

    @Override
    public void onDataLoaded(EmployeeList employeeList) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new EmployeeListAdapter(employeeList.getEmployeeDetailList(), this));
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Some Error occured. Try again after some time !!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
    public void onItemClick(String empId) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        EditEmployeeFragment employeeLeaveMainFragment = new EditEmployeeFragment();
        transaction.replace(R.id.activity_main_frame, employeeLeaveMainFragment);

        transaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putString("employeeId", empId);
        employeeLeaveMainFragment.setArguments(bundle);

        transaction.commit();
    }



    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity) getActivity()).setHeaderTitle("DASHBOARD");

    }
}
