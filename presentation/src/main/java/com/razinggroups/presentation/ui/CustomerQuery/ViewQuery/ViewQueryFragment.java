package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.myTask.createMyTask.ItemData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewQueryFragment extends Fragment {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView textView;
    ArrayList<Customer> customerList;
    Customer customer;
    EditText search_input;

    CharSequence search = "";

    public ViewQueryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_query, container, false);
        initViews(view);
        readQuery();
        return view;
        //return inflater.inflate(R.layout.fragment_view_query, container, false);
    }


    private void initViews(View view) {
        progressBar = view.findViewById(R.id.fragment_view_query_progress_bar);
        recyclerView = view.findViewById(R.id.fragment_query_list_rv);
        textView = view.findViewById(R.id.fragment_query_error_tv);
        textView.setText(" Loading...");
        search_input = view.findViewById(R.id.search_input);

        customerList = new ArrayList<Customer>();


    }

    public void readQuery() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().readQuery();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                if (response.code() == 200) {
                    try {

                        s = response.body().string();
                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("records");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            customer = new Customer();

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            customer.setId(jsonObject1.getString("id"));

                            customer.setAddress(jsonObject1.getString("address"));
                            customer.setName(jsonObject1.getString("fullname"));
                            customer.setCompany_email(jsonObject1.getString("companymail"));
                            customer.setMobile(jsonObject1.getString("mobile"));
                            customer.setLandline(jsonObject1.getString("landline_phone"));
                            customer.setPassport_no(jsonObject1.getString("passport"));
                            customer.setEnquiry_details(jsonObject1.getString("enquirymessage"));
                            customerList.add(customer);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            //  recyclerView.setAdapter(adapter);
                            ItemClickListener itemClickListener = new ItemClickListener() {
                                @Override
                                public void onClick(View view, int position, boolean isLongClick) {


                                    //     String number=  customerList.get(position).getLead_type() ;
                                    //  deleteQuery(customerList.get(position).getLead_type());
                                    deleteAlert(customerList.get(position).getId());


                                    //      Toast.makeText(getActivity(),    number+"", Toast.LENGTH_SHORT).show();
                                }
                            };
                            QueryListAdapter queryListAdapter = new QueryListAdapter(customerList, itemClickListener);
                            recyclerView.setAdapter(queryListAdapter);
                            recyclerView.setHasFixedSize(true);

                            /*------------------Searching Filter---------------------
                             */
                            search_input.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    queryListAdapter.getFilter().filter(s);
                                    search = s;
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });


                            progressBar.setVisibility(View.GONE);
                            textView.setVisibility(View.GONE);
                        }
                        //String msg = jsonObject.getString("message");
                        //Toast.makeText(getActivity(), s , Toast.LENGTH_SHORT).show();


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                } else {

                    progressBar.setVisibility(View.GONE);

                    textView.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                //progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();


            }
        });

    }

    public void deleteQuery(String id) {
        //Toast.makeText(getActivity(), id+"", Toast.LENGTH_SHORT).show();


        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .deleteQuery(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String s = null;

                //Toast.makeText(getActivity(), response.code(), Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    try {

                        s = response.body().string();
                        JSONObject jsonObject = new JSONObject(s);
                        String msg = jsonObject.getString("message");

//                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                        onCreatealert(msg);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                } else {


                    //   Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();
                    onCreatealert("server Error");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


                Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();


            }
        });

    }

    public void onCreatealert(String message) {

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

    public void deleteAlert(String idd) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setCancelable(false);
        dialog.setTitle("Alert !!!");
        dialog.setMessage("Are you sure you want to delete this Query?");
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                deleteQuery(idd);
                //Toast.makeText(getActivity(), idd+"deleted", Toast.LENGTH_SHORT).show();
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".

                        //      Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();

                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }


}