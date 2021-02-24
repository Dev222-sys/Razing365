    package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.data.network.RetrofitClient;

import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.utils.Const;
import com.razinggroups.presentation.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

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
    TextView search_input_2;


    LinearLayout date_Calender;

    CharSequence search = "";
    Calendar myCalendar = Calendar.getInstance();

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

    }


    private void initViews(View view) {
        progressBar = view.findViewById(R.id.fragment_view_query_progress_bar);
        recyclerView = view.findViewById(R.id.fragment_query_list_rv);
        textView = view.findViewById(R.id.fragment_query_error_tv);
        textView.setText(" Loading...");
        search_input = view.findViewById(R.id.search_input);
        search_input_2= view.findViewById(R.id.search_input_2);
        date_Calender= view.findViewById(R.id.date_Calender);
        customerList = new ArrayList<Customer>();

        DatePickerDialog.OnDateSetListener datepickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = Const.dateFormat; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                hideKeyboard(getActivity());
                search_input_2.setText(sdf.format(myCalendar.getTime()));
              //  Toast.makeText(getContext(), sdf.format(myCalendar.getTime())+"", Toast.LENGTH_SHORT).show();
               // dateOfJoining.setText(sdf.format(myCalendar.getTime()));
            }

        };

        search_input_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog(datepickerEnd);
            }
        });
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


                //  Log.d("datadata", response.toString());


                String s = null;
                if (response.code() == 200) {
                    try {

                        s = response.body().string();

                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("records");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            customer = new Customer();

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            customer.setId(jsonObject1.getString("kyc_id"));

                            customer.setAddress(jsonObject1.getString("permanent_address"));
                            customer.setName(jsonObject1.getString("fullname"));
                            customer.setCompany_email(jsonObject1.getString("email"));
                            customer.setMobile(jsonObject1.getString("mobile"));
                            customer.setLandline(jsonObject1.getString("landline"));
                            customer.setPassport_no(jsonObject1.getString("passport"));
                            customer.setCreate_at(jsonObject1.getString("create_at"));
                            customerList.add(customer);
                            //create_at
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            //  recyclerView.setAdapter(adapter);
                            ItemClickListener itemClickListener = new ItemClickListener() {
                                @Override
                                public void onClick(View view, int position, boolean isLongClick) {


                                    String number = customerList.get(position).getLead_type();
                                    SharedPrefManager.getInstans(getActivity()).datakyc_id(customerList.get(position).getId());
                                    //  Toast.makeText(getContext(), SharedPrefManager.getInstans(getActivity()).getkyc_id()+"", Toast.LENGTH_SHORT).show();

                                    //  deleteQuery(customerList.get(position).getLead_type());
                                    //   deleteAlert(customerList.get(position).getId());

                                    // Toast.makeText(getActivity(),    number+"", Toast.LENGTH_SHORT).show();
                                    String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();

                                    if (usertype.trim().equalsIgnoreCase("MasterAdmin")) {
                                        FragmentManager manager = getFragmentManager();
                                        FragmentTransaction transaction = manager.beginTransaction();
                                        TopicQueryFragment topicQueryFragment = new TopicQueryFragment();
                                        // activity_main_frame
                                        transaction.replace(R.id.activity_main_frame, topicQueryFragment);
                                        transaction.addToBackStack(null);
                                        transaction.commit();

                                    } else {
                                        FragmentManager manager = getFragmentManager();
                                        FragmentTransaction transaction = manager.beginTransaction();
                                        TopicQueryFragment topicQueryFragment = new TopicQueryFragment();
                                        // activity_main_frame
                                        transaction.replace(R.id.activity_employee_home_frame_layout, topicQueryFragment);
                                        transaction.addToBackStack(null);
                                        transaction.commit();

                                    }


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
                            search_input_2.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    queryListAdapter.getFilter().filter(s);
                                    search = s;
                                  //  search_input_2.setText(" ");
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






    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    void openDatePickerDialog(DatePickerDialog.OnDateSetListener datepicker) {
        hideKeyboard(getActivity());
        new DatePickerDialog(getContext(), datepicker, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}