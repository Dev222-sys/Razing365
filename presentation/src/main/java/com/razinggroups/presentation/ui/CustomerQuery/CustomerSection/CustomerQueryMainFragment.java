package com.razinggroups.presentation.ui.CustomerQuery.CustomerSection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.razinggroups.presentation.R;

import com.razinggroups.presentation.ui.CustomerQuery.QuerySubmit.Query_Submit;
import com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.ViewQueryFragment;




public class CustomerQueryMainFragment extends Fragment {


    public CustomerQueryMainFragment() {
        // Required empty public constructor
    }

    LinearLayout status, apply;
    String empId, key = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_query_main, container, false);
        status = view.findViewById(R.id.fragment_customer_query_main_status);
        apply = view.findViewById(R.id.fragment_customer_query_main_apply);

        key = getArguments().getString("key");


        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (key.equals("1")) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    ViewQueryFragment viewQueryFragment = new ViewQueryFragment();
                    // activity_main_frame
                    transaction.replace(R.id.activity_employee_home_frame_layout, viewQueryFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    ViewQueryFragment viewQueryFragment = new ViewQueryFragment();
                    // activity_main_frame
                    transaction.replace(R.id.activity_main_frame, viewQueryFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }

            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (key.equals("1")) {


                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();

                    Query_Submit customerQuerySubmit = new Query_Submit();

                    // activity_main_frame
                    transaction.replace(R.id.activity_employee_home_frame_layout, customerQuerySubmit);
                    transaction.addToBackStack(null);
                    transaction.commit();


                } else {

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    Query_Submit customerQuerySubmit = new Query_Submit();


                    // activity_main_frame
                    transaction.replace(R.id.activity_main_frame, customerQuerySubmit);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }


                //  Toast.makeText(getContext(), "Apply", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}