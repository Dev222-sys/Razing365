package com.razinggroups.presentation.ui.CustomerQuery.CustomerSection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.CustomerQueryFragment;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeApplyLeave.EmployeeApplyLeaveFragment;
import com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList.EmployeeLeaveListFragment;
import com.razinggroups.presentation.ui.holiday.HolidayMainFragment;

public class CustomerQueryMainFragment extends Fragment {



    public CustomerQueryMainFragment() {
        // Required empty public constructor
    }

    LinearLayout status, apply;
    String empId,key="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_query_main, container, false);
        status = view.findViewById(R.id.fragment_customer_query_main_status);
        apply = view.findViewById(R.id.fragment_customer_query_main_apply);

            key = getArguments().getString("key");
            Toast.makeText(getActivity(), key+"", Toast.LENGTH_SHORT).show();

//        empId = getArguments().getString("employeeId");

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  /*              FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                EmployeeLeaveListFragment employeeLeaveMainFragment = new EmployeeLeaveListFragment();
                transaction.replace(R.id.activity_employee_home_frame_layout, employeeLeaveMainFragment);

                transaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("employeeId", empId);
                employeeLeaveMainFragment.setArguments(bundle);

                transaction.commit();
  */
                Toast.makeText(getContext(), "status", Toast.LENGTH_SHORT).show();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (key.equals("1"))
                {


                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    CustomerQueryFragment customerQueryFragment=new CustomerQueryFragment();

                    // activity_main_frame
                    transaction.replace(R.id.activity_employee_home_frame_layout, customerQueryFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();



                }
                else {

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    CustomerQueryFragment customerQueryFragment=new CustomerQueryFragment();
                    // activity_main_frame
                    transaction.replace(R.id.activity_main_frame, customerQueryFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }




              //  Toast.makeText(getContext(), "Apply", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}