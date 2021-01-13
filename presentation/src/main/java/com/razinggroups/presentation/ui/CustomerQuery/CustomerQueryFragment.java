package com.razinggroups.presentation.ui.CustomerQuery;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.CustomerQuery.FetchAllCustomerQuerryResponse;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.employee.EditEmployee.EditEmployeeViewModel;
import com.razinggroups.presentation.ui.myTask.createMyTask.ItemData;
import com.razinggroups.presentation.ui.myTask.createMyTask.SpinnerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerQueryFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    /*@Inject
    @Named("LeaveFragmnet")*/

    private ViewFlipper viewFlipper;

    ViewModelProvider.Factory viewModelFactory;

    CustomerQueryViewModel viewModel;

    ProgressBar progressBar;
    TextView errorTv;
    RecyclerView recyclerView;

    Spinner  as_single_spinner, as_mentioned_spinner, customer_employe_type_sp, fragment_uk_visa_spinner,
            fragment_european_visa_spinner,
            fragment_usa_visa_spinner,
            fragment_intersted_type_spinner,
            fragment_Social_media_spinner,
            conacted_spinner,
            subscibe_spinner;
    EditText full_name, mobile_no, nationality, landline_no, passport_no, email, p_address, r_address;

    Button gernal_submit_btn;
    Customer customer = new Customer();
    TextView lead_type_tv;
    LinearLayout applicant_or_not, uk_visa_spinner_ly, european_visa_ly, usa_visa_ly;
    EditText customer_work_org_name_et, customer_uk_year_et, customer_Reason_for_refusal_et,
            customer_migrate_et;




/*

    @Override
    public CustomerQueryViewModel getViewModel() {

        return viewModel;
    }
*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CustomerQueryViewModel.class);
        viewModel.setNavigator(this);*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_query, container, false);
        initViews(view);


        String username = SharedPrefManager.getInstans(getActivity()).getUsername();

        //leaveViewModel.fetchData();
        return view;
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.CustomerQueryFragment_progress_bar);


        as_single_spinner = view.findViewById(R.id.fragment_as_single_spinner);
        applicant_or_not = view.findViewById(R.id.applicant_or_not);
        as_mentioned_spinner = view.findViewById(R.id.as_mentioned_spinner);
        customer_employe_type_sp = view.findViewById(R.id.customer_employe_type_sp);
        customer_work_org_name_et = view.findViewById(R.id.customer_work_org_name_et);
        fragment_uk_visa_spinner = view.findViewById(R.id.fragment_uk_visa_spinner);
        uk_visa_spinner_ly = view.findViewById(R.id.uk_visa_spinner_ly);
        customer_uk_year_et = view.findViewById(R.id.customer_uk_year_et);
        customer_Reason_for_refusal_et = view.findViewById(R.id.customer_Reason_for_refusal_et);
        fragment_european_visa_spinner = view.findViewById(R.id.fragment_european_visa_spinner);
        european_visa_ly = view.findViewById(R.id.european_visa_ly);


        fragment_usa_visa_spinner = view.findViewById(R.id.fragment_usa_visa_spinner);
        usa_visa_ly = view.findViewById(R.id.usa_visa_ly);
        fragment_intersted_type_spinner = view.findViewById(R.id.fragment_intersted_type_spinner);
        fragment_Social_media_spinner = view.findViewById(R.id.fragment_Social_media_spinner);
        conacted_spinner = view.findViewById(R.id.conacted_spinner);
        subscibe_spinner=view.findViewById(R.id.subscibe_spinner);



        full_name = (EditText) view.findViewById(R.id.fragment_customer_query_name_et);
        mobile_no = (EditText) view.findViewById(R.id.fragment_customer_query_mobileetno);
        nationality = (EditText) view.findViewById(R.id.customer_nationality_et);
        landline_no = (EditText) view.findViewById(R.id.customer_laindline_et);
        passport_no = (EditText) view.findViewById(R.id.customer_passport_et);
        email = (EditText) view.findViewById(R.id.customer_emailadress_et);
        p_address = (EditText) view.findViewById(R.id.customer_permanentadress_et);
        r_address = (EditText) view.findViewById(R.id.customer_resiencyadress_et);
        gernal_submit_btn=view.findViewById(R.id.gernal_submit_btn);



        spinnerdata();
/*
        if (gernal_information_spinner.getSelectedItemPosition()==1)
        {

            Toast.makeText(getActivity(), "Please Select Lead Type", Toast.LENGTH_SHORT).show();

//            progressBar.setVisibility(View.INVISIBLE);

        }*/
        //statusData.add(new ItemData("Ex Employee", "2"));

    }

    public void spinnerdata() {

        single_or_more();
        Applicant_same();
        bussines_salaeried();
        fragment_uk_visa_spinner();
        fragment_european_visa_spinner();
        fragment_usa_visa_spinner();
        fragment_intersted_type_spinner();
        conacted_spinner();
        subscibe_spinner();
        fragment_Social_media_spinner();
    }




    private void single_or_more() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        as_single_spinner.setAdapter(adapter);
        as_single_spinner.setOnItemSelectedListener(this);
        as_single_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    applicant_or_not.setVisibility(View.VISIBLE);
                } else {
                    applicant_or_not.setVisibility(View.GONE);
                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }

    private void Applicant_same()
    {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        as_mentioned_spinner.setAdapter(adapter);
        as_mentioned_spinner.setOnItemSelectedListener(this);
        as_mentioned_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
                } else {

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }

    private void bussines_salaeried() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.business_salaried));

        customer_employe_type_sp.setAdapter(adapter);
        customer_employe_type_sp.setOnItemSelectedListener(this);
        customer_employe_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
                } else {

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }

    private void fragment_uk_visa_spinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        fragment_uk_visa_spinner.setAdapter(adapter);
        fragment_uk_visa_spinner.setOnItemSelectedListener(this);
        fragment_uk_visa_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    uk_visa_spinner_ly.setVisibility(View.VISIBLE);
                } else {

                    uk_visa_spinner_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }


    private void fragment_european_visa_spinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        fragment_european_visa_spinner.setAdapter(adapter);
        fragment_european_visa_spinner.setOnItemSelectedListener(this);
        fragment_european_visa_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    european_visa_ly.setVisibility(View.VISIBLE);
                } else {

                    european_visa_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }

    private void fragment_usa_visa_spinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        fragment_usa_visa_spinner.setAdapter(adapter);
        fragment_usa_visa_spinner.setOnItemSelectedListener(this);
        fragment_usa_visa_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    usa_visa_ly.setVisibility(View.VISIBLE);
                } else {

                    usa_visa_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }

    private void fragment_intersted_type_spinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.residency_passport));

        fragment_intersted_type_spinner.setAdapter(adapter);
        fragment_intersted_type_spinner.setOnItemSelectedListener(this);
        fragment_intersted_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else {


                    //usa_visa_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }


    private void conacted_spinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.conacted_spinner));

        conacted_spinner.setAdapter(adapter);
        conacted_spinner.setOnItemSelectedListener(this);
        conacted_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else {


                    //usa_visa_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }
    private void subscibe_spinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        subscibe_spinner.setAdapter(adapter);
        subscibe_spinner.setOnItemSelectedListener(this);
        subscibe_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else {


                    //usa_visa_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }


    private void fragment_Social_media_spinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.social_media));


        fragment_Social_media_spinner.setAdapter(adapter);
        fragment_Social_media_spinner.setOnItemSelectedListener(this);
        fragment_Social_media_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else {


                    //usa_visa_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }


    private void referenceSpinner() {

        List<ItemData> statusData = new ArrayList<>();
        statusData.add(new ItemData("Select Reference ", "0"));
        statusData.add(new ItemData("Friends", "1"));
        statusData.add(new ItemData("Social Media", "2"));
        statusData.add(new ItemData("Channel  Partner", "3"));


        //statusData.add(new ItemData("Ex Employee", "2"));

        //  reference.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, statusData));
    }

    private void loadStatusSpinner() {
        List<ItemData> statusData = new ArrayList<>();
        statusData.add(new ItemData("Select Profession", "0"));
        statusData.add(new ItemData("Bussniess", "1"));

        //statusData.add(new ItemData("Ex Employee", "2"));

        // profession.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, statusData));
    }


   /* public void SubmitQuery() {


            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi().submitquery(SharedPrefManager.getInstans(getActivity()).getUsername(),
                            ((ItemData) lead_type_spinner.getSelectedItem()).getName(),
                            et_name.getText().toString(),
                            et_company_email.getText().toString()
                            ,et_mobile_no.getText().toString(),
                            et_nationality.getText().toString()
                            ,et_landline_no.getText().toString(),
                            ((ItemData) reference.getSelectedItem()).getName(),

                            ((ItemData) profession.getSelectedItem()).getName(),
                            et_address.getText().toString(),
                            et_passport_no.getText().toString(),
                            et_enquiry.getText().toString() );

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    String s = null;
                    if (response.code()==200)
                    {
                        try {

                            s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            String msg = jsonObject.getString("message");

//                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            onCreatealert(msg);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }




                    }
                    else
                    {

                        progressBar.setVisibility(View.INVISIBLE);



                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();


                }
            });

        }*/


    public void onCreatealert(String message) {
        progressBar.setVisibility(View.INVISIBLE);
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

    public void Gernal_Information() {






    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



/*
    @Override
    public void onError(String toString) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(toString);
    }*/
/*

    @Override
    public void onDataLoaded(FetchAllLeavesResponse fetchAllLeavesResponse) {
        progressBar.setVisibility(View.GONE);
        if (fetchAllLeavesResponse.getCount().equalsIgnoreCase("0")) {
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText("No Data To Display");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new LeaveListAdapter(fetchAllLeavesResponse.getRecords(), this));
        }

    }

    @Override
    public void onUpdateResponse(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                leaveViewModel.fetchData();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

*/

/*    @Override
    public void onBtnClick(String taskId, String status) {
        leaveViewModel.updateLeave(new UpdateLeave(taskId, status));
        progressBar.setVisibility(View.VISIBLE);
    }*/
}
