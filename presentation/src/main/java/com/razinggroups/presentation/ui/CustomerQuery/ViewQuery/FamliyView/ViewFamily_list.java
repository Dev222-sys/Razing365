package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.FamliyView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.razinggroups.domain.model.CustomerQuery.FamilyDetails;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.ItemClickListener;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewFamily_list extends Fragment {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView textView;
    ArrayList<FamilyDetails>familylist;
    FamilyDetails familyDetails;
    EditText search_input;

    CharSequence search = "";

    FamilyListAdapter familyListAdapter;
    public ViewFamily_list() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_view_family_list, container, false);
        initViews(view);

        return view;
    }




        private void initViews(View view) {
            progressBar = view.findViewById(R.id.familyview_query_progress_bar);
            recyclerView = view.findViewById(R.id.viewfamily_query_list_rv);
            textView = view.findViewById(R.id.familyquery_error_tv);
            textView.setText(" Loading...");
            search_input = view.findViewById(R.id.familysearch_input);
            familylist = new ArrayList<FamilyDetails>();

            readQuery();
           // Toast.makeText(getActivity(),    SharedPrefManager.getInstans(getActivity()).getkyc_id()+"", Toast.LENGTH_SHORT).show();


        }



    public void readQuery() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);


        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().familydetils(SharedPrefManager.getInstans(getActivity()).getdatakyc_id());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //  Log.d("datadata", response.toString());
             //   Toast.makeText(getActivity(), response.code()+"", Toast.LENGTH_SHORT).show();


                String s = null;
                if (response.code() == 200) {
                    try {

                        s = response.body().string();
                        //Toast.makeText(getActivity(), s+"", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(s);

                        String count=jsonObject.getString("count");
                        if (count.equals("0"))
                        {

                            onCreatealert("No family details found ");

                        }else {
                        JSONArray jsonArray = jsonObject.getJSONArray("family_records");
                       for (int i = 0; i <= jsonArray.length(); i++) {

                           familyDetails=new FamilyDetails();

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            familyDetails.setId(jsonObject1.getString("id"));

                            familyDetails.setFull_name(jsonObject1.getString("full_name"));
                           familyDetails.setPassport_no(jsonObject1.getString("passport_no"));
                           familyDetails.setAge(jsonObject1.getString("age"));
                           familyDetails.setUpdate_at(jsonObject1.getString("create_at"));

                         //  Log.d("familydata",jsonObject1.getString("age")+jsonObject1.getString("update_at"));

                           familylist.add(familyDetails);

                        //String msg = jsonObject.getString("message");
                        //Toast.makeText(getActivity(), s , Toast.LENGTH_SHORT).show();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        //  recyclerView.setAdapter(adapter);
                        ItemClickListener itemClickListener= new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {


                               // SharedPrefManager.getInstans(getActivity()).datakyc_id(customerList.get(position).getId());
                                //Toast.makeText(getContext(), familylist.get(position).getId()+"", Toast.LENGTH_SHORT).show();

                                //  deleteQuery(customerList.get(position).getLead_type());
                                //   deleteAlert(customerList.get(position).getId());

                              //  Toast.makeText(getActivity(),    familylist.get(position).getId()+"", Toast.LENGTH_SHORT).show();
                                String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();

                                if (usertype.trim().equalsIgnoreCase("MasterAdmin")) {
                                    FragmentManager manager = getFragmentManager();
                                    FragmentTransaction transaction = manager.beginTransaction();
                                    Family_Detail topicQueryFragment = new Family_Detail();
                                    Bundle args = new Bundle();
                                    args.putString("id", familylist.get(position).getId());
                                    topicQueryFragment.setArguments(args);

                                    // activity_main_frame
                                    transaction.replace(R.id.activity_main_frame, topicQueryFragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();


//Inflate the fragment
                                }
                                else
                                {

                                    FragmentManager manager = getFragmentManager();
                                    FragmentTransaction transaction = manager.beginTransaction();
                                    Family_Detail topicQueryFragment = new Family_Detail();
                                    Bundle args = new Bundle();
                                    args.putString("id", familylist.get(position).getId());
                                    topicQueryFragment.setArguments(args);

                                    // activity_main_frame
                                    transaction.replace(R.id.activity_employee_home_frame_layout, topicQueryFragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();

                                }



                            }
                        };
                         familyListAdapter = new FamilyListAdapter(familylist, itemClickListener);
                        recyclerView.setAdapter(familyListAdapter);
                        recyclerView.setHasFixedSize(true);


                           search_input.addTextChangedListener(new TextWatcher() {
                               @Override
                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                               }

                               @Override
                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                   familyListAdapter.getFilter().filter(s);
                                   search = s;
                               }

                               @Override
                               public void afterTextChanged(Editable s) {

                               }
                           });


                           progressBar.setVisibility(View.GONE);
                           textView.setVisibility(View.GONE);
                       }



                        /*------------------Searching Filter---------------------
                         */

                       }
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

    public void onCreatealert(String message) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                getActivity().onBackPressed();
            }
        });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }
}