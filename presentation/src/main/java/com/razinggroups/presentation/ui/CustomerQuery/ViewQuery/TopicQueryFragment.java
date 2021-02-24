package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.FamliyView.ViewFamily_list;
import com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.KYCInformation.KycInfoFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicQueryFragment extends Fragment {
    LinearLayout GeneralInformation, FamilyInformation,KYCInformation  ;


    public TopicQueryFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_query, container, false);

        GeneralInformation = view.findViewById(R.id.topicQueryFragment_generalInformation);
        FamilyInformation = view.findViewById(R.id.topicQueryFragment_family_information);
        KYCInformation = view.findViewById(R.id.topicQueryFragment_KYCinfo);

        GeneralInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), "GeneralInformation", Toast.LENGTH_SHORT).show();


                String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();


                if (usertype.trim().equalsIgnoreCase("Employee")) {

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    EditQueryFragment editQueryFragment = new EditQueryFragment();
                    // activity_main_frame
                    transaction.replace(R.id.activity_employee_home_frame_layout, editQueryFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();



                }
                else
                {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    EditQueryFragment editQueryFragment = new EditQueryFragment();
                    // activity_main_frame
                    transaction.replace(R.id.activity_main_frame, editQueryFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }



            }
        });
        FamilyInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "FamilyInformation", Toast.LENGTH_SHORT).show();





                String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();


                if (usertype.trim().equalsIgnoreCase("Employee")) {


                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    ViewFamily_list viewFamily_list = new ViewFamily_list();
                    // activity_main_frame
                    transaction.replace(R.id.activity_employee_home_frame_layout, viewFamily_list);
                    transaction.addToBackStack(null);
                    transaction.commit();



                }
                else
                {


                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    ViewFamily_list viewFamily_list = new ViewFamily_list();
                    // activity_main_frame
                    transaction.replace(R.id.activity_main_frame, viewFamily_list);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }

            }
        });
        KYCInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), "KYCInformation", Toast.LENGTH_SHORT).show();






                String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();


                if (usertype.trim().equalsIgnoreCase("Employee")) {




                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    KycInfoFragment kycInfoFragment = new KycInfoFragment();
                    // activity_main_frame
                    transaction.replace(R.id.activity_employee_home_frame_layout, kycInfoFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }
                else
                {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    KycInfoFragment kycInfoFragment = new KycInfoFragment();
                    // activity_main_frame
                    transaction.replace(R.id.activity_main_frame, kycInfoFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }

            }
        });

        return  view;

    }


}