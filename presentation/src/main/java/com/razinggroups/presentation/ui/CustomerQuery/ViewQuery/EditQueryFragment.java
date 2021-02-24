package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.CustomerSection.CustomerQueryMainFragment;
import com.razinggroups.presentation.ui.login.LoginActivity;
import com.razinggroups.presentation.ui.main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQueryFragment extends Fragment {
    EditText GI_customer_query_name_et, GK_country_no_code_tv, GK__customer_query_mobileetno, Gk_country_tv, Gk_country_landline_code_et,
            GK_customer_laindline_et, Gk_customer_passport_et, Gk_customer_emailadress_et, GK_customer_permanentadress_et,
            Gk_customer_permanentadress_postal_et, Gk_customer_resiencyadress_et, Gk_customer_resiencyadress_postal_et;


    Button generalQuerysubmit, generalQuerydelete;
    LinearLayout generalQueryLl;

    String name, mobile_no, country_name, landline, passport, email, permanent_add, p_code, resiency_add, r_code;

    public EditQueryFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_query, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {

        GI_customer_query_name_et = view.findViewById(R.id.GI_customer_query_name_et);
        GK__customer_query_mobileetno = view.findViewById(R.id.GK__customer_query_mobileetno);
        Gk_country_tv = view.findViewById(R.id.Gk_country_tv);
        GK_customer_laindline_et = view.findViewById(R.id.GK_customer_laindline_et);
        Gk_customer_passport_et = view.findViewById(R.id.Gk_customer_passport_et);
        Gk_customer_emailadress_et = view.findViewById(R.id.Gk_customer_emailadress_et);
        GK_customer_permanentadress_et = view.findViewById(R.id.GK_customer_permanentadress_et);
        Gk_customer_permanentadress_postal_et = view.findViewById(R.id.Gk_customer_permanentadress_postal_et);
        Gk_customer_resiencyadress_et = view.findViewById(R.id.Gk_customer_resiencyadress_et);
        Gk_customer_resiencyadress_postal_et = view.findViewById(R.id.Gk_customer_resiencyadress_postal_et);
        generalQuerysubmit = view.findViewById(R.id.generalQuerysubmit);
        generalQuerydelete = view.findViewById(R.id.generalQuerydelete);


        generalQuerysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updategernalformdata();
            }
        });
        generalQuerydelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT).show();
                deletedata();
            }
        });

        String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();
        generalQueryLl = view.findViewById(R.id.generalQueryLl);

        //Toast.makeText(getContext(), usertype+"", Toast.LENGTH_SHORT).show();
        if (usertype.trim().equalsIgnoreCase("MasterAdmin")) {
            // Toast.makeText(getContext(), "masterAdmin", Toast.LENGTH_SHORT).show();
            generalQueryLl.setVisibility(View.VISIBLE);

        }
        getgernalinfromation();
    }

    private void deletedata() {





        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().delete_userdetails(SharedPrefManager.getInstans(getActivity()).getdatakyc_id());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;

                // Toast.makeText(getActivity(), response.code()+"", Toast.LENGTH_SHORT).show();
                if (response.code()==200)
                {
                    try {
                        s = response.body().string();
                        JSONObject jsonObject = new JSONObject(s);
                        String msg = jsonObject.getString("message");
                        alertfamilySubmit(msg);

                        if (SharedPrefManager.getInstans(getActivity()).getLogintype().trim().equalsIgnoreCase("Employee")) {



                            FragmentManager manager = getFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            CustomerQueryMainFragment customerQueryMainFragment = new CustomerQueryMainFragment();
                            // activity_main_frame
                            transaction.replace(R.id.activity_employee_home_frame_layout, customerQueryMainFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        }
                        else
                        {
                            FragmentManager manager = getFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            CustomerQueryMainFragment customerQueryMainFragment = new CustomerQueryMainFragment();
                            // activity_main_frame
                            transaction.replace(R.id.activity_main_frame, customerQueryMainFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();



                        }

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void updategernalformdata() {


        name = GI_customer_query_name_et.getText().toString();
        mobile_no = GK__customer_query_mobileetno.getText().toString();
        country_name = Gk_country_tv.getText().toString();
        landline = GK_customer_laindline_et.getText().toString();
        passport = Gk_customer_passport_et.getText().toString();
        email = Gk_customer_emailadress_et.getText().toString();
        permanent_add = GK_customer_permanentadress_et.getText().toString();
        p_code = Gk_customer_permanentadress_postal_et.getText().toString();
        resiency_add = Gk_customer_resiencyadress_et.getText().toString();
        r_code = Gk_customer_resiencyadress_postal_et.getText().toString();


        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().Update_Gernal_infromation(SharedPrefManager.getInstans(getActivity()).getdatakyc_id(),
                        SharedPrefManager.getInstans(getActivity()).getUsername(),
                        name, email, mobile_no, landline, passport, country_name, permanent_add,
                        p_code, resiency_add, r_code);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;

               // Toast.makeText(getActivity(), response.code()+"", Toast.LENGTH_SHORT).show();
                if (response.code()==200)
                {
                    try {
                        s = response.body().string();
                        JSONObject jsonObject = new JSONObject(s);
                        String msg = jsonObject.getString("message");

                        alertfamilySubmit(msg);
                        getActivity().onBackPressed();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();

            }
        });


        //  SharedPrefManager.getInstans(getActivity()).getUsername();

        // Toast.makeText(getActivity(), SharedPrefManager.getInstans(getActivity()).getUsername(), Toast.LENGTH_SHORT).show();
    }
    public void alertfamilySubmit(String title)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.logo_black)
                .setTitle(title)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


    public void getgernalinfromation() {

        // Toast.makeText(getContext(), SharedPrefManager.getInstans(getActivity()).getdatakyc_id()+"", Toast.LENGTH_SHORT).show();
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().getgernalinfromation(SharedPrefManager.getInstans(getActivity()).getdatakyc_id());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //  Log.d("datadata", response.toString());
                // Toast.makeText(getActivity(), response.code()+"", Toast.LENGTH_SHORT).show();

                String s = null;


                if (response.code() == 200) {
                    try {

                        s = response.body().string();

                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("records");
                        for (int i = 0; i <= jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            // String name,mobile_no,mobile_code,nationality,lindline_code,lindline_no,passport_number,email_address,p_address,p_postalcode,R_address,R_postalcode;

                            GI_customer_query_name_et.setText(jsonObject1.getString("fullname"));

                            GK__customer_query_mobileetno.setText(jsonObject1.getString("mobile"));
                            Gk_country_tv.setText(jsonObject1.getString("nationality"));

                            GK_customer_laindline_et.setText(jsonObject1.getString("landline"));
                            Gk_customer_passport_et.setText(jsonObject1.getString("passport"));
                            Gk_customer_emailadress_et.setText(jsonObject1.getString("email"));
                            GK_customer_permanentadress_et.setText(jsonObject1.getString("permanent_address"));
                            Gk_customer_permanentadress_postal_et.setText(jsonObject1.getString("pa_pincode"));
                            Gk_customer_resiencyadress_et.setText(jsonObject1.getString("residency_address"));
                            Gk_customer_resiencyadress_postal_et.setText(jsonObject1.getString("ra_pincode"));


                        }
                        //String msg = jsonObject.getString("message");
                        //Toast.makeText(getActivity(), s , Toast.LENGTH_SHORT).show();


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                } else {


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                //progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();


            }
        });

    }

}