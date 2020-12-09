package com.razinggroups.presentation.ui.employeeHomeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeApplyLeave.EmployeeApplyLeaveFragment;
import com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList.EmployeeLeaveListFragment;

public class EmployeeLeaveMainFragment extends Fragment {
    public EmployeeLeaveMainFragment() {
    }

    LinearLayout status, apply;
    String empId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_leave_main, container, false);
        status = view.findViewById(R.id.fragment_employee_leave_main_status);
        apply = view.findViewById(R.id.fragment_employee_leave_main_apply);
        empId = getArguments().getString("employeeId");

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                EmployeeLeaveListFragment employeeLeaveMainFragment = new EmployeeLeaveListFragment();
                transaction.replace(R.id.activity_employee_home_frame_layout, employeeLeaveMainFragment);

                transaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("employeeId", empId);
                employeeLeaveMainFragment.setArguments(bundle);

                transaction.commit();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                EmployeeApplyLeaveFragment employeeLeaveMainFragment = new EmployeeApplyLeaveFragment();
                transaction.replace(R.id.activity_employee_home_frame_layout, employeeLeaveMainFragment);

                transaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("employeeId", empId);
                employeeLeaveMainFragment.setArguments(bundle);

                transaction.commit();
            }
        });

        return view;
    }
}
