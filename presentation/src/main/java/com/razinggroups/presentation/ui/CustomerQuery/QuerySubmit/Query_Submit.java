package com.razinggroups.presentation.ui.CustomerQuery.QuerySubmit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
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

import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.CustomerQueryViewModel;

public class Query_Submit extends Fragment  implements AdapterView.OnItemSelectedListener {


    private ViewFlipper viewFlipper;

    ViewModelProvider.Factory viewModelFactory;

    CustomerQueryViewModel viewModel;

    ProgressBar progressBar;
    TextView errorTv;
    RecyclerView recyclerView;

    Spinner applyingforfamily,as_single_spinner, as_mentioned_spinner, customer_employe_type_sp, fragment_uk_visa_spinner,
            fragment_european_visa_spinner,
            fragment_usa_visa_spinner,
            fragment_intersted_type_spinner,
            fragment_Social_media_spinner,
            conacted_spinner,
            subscibe_spinner,
            passportcopy;
    EditText full_name, mobile_no, nationality, landline_no, passport_no, email, p_address, r_address;

    String full_namee, mobile_noo, nationalityy, landline_noo, passport_noo, emaill, p_addresss, r_addresss;

    Button gernal_submit_btn;
    Customer customer = new Customer();
    TextView lead_type_tv;
    LinearLayout applicant_or_not, uk_visa_spinner_ly, european_visa_ly, usa_visa_ly;

    EditText customer_work_org_name_et, customer_uk_year_et, customer_Reason_for_refusal_et,
            customer_migrate_et;
    FloatingActionButton submitgernalform,
            submitSingleapplicantform,
            queryempolyetype,
            query_visa,
            socialmedia,
            applyforfamily_btn;

    EditText family_name,family_passport,family_age,family_passport_copy;
    String family_namee,family_passportt,family_agee,family_passport_copyy;



    public Query_Submit() {
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

        View view = inflater.inflate(R.layout.activity_customer_query_submit, container, false);
        initViews(view);


        String username = SharedPrefManager.getInstans(getActivity()).getUsername();

        //leaveViewModel.fetchData();
        return view;
    }


    private void initViews(View view) {
       // progressBar = view.findViewById(R.id.CustomerQueryFragment_progress_bar);

        viewFlipper = view.findViewById(R.id.viewflipper_registration);
        viewFlipper.setDisplayedChild(0);

        submitgernalform=view.findViewById(R.id.submitgernalform);
        submitSingleapplicantform=view.findViewById(R.id.submitSingleapplicantform);
        applyforfamily_btn=view.findViewById(R.id.applyforfamily_btn);

        queryempolyetype=view.findViewById(R.id.queryempolyetype);
        query_visa=view.findViewById(R.id.query_visa);
        socialmedia=view.findViewById(R.id.socialmedia);


        applyingforfamily=view.findViewById(R.id.applyingforfamily);

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
        passportcopy=view.findViewById(R.id.passportcopy);



        full_name = (EditText) view.findViewById(R.id.fragment_customer_query_name_et);
        mobile_no = (EditText) view.findViewById(R.id.fragment_customer_query_mobileetno);
        nationality = (EditText) view.findViewById(R.id.customer_nationality_et);
        landline_no = (EditText) view.findViewById(R.id.customer_laindline_et);
        passport_no = (EditText) view.findViewById(R.id.customer_passport_et);
        email = (EditText) view.findViewById(R.id.customer_emailadress_et);
        p_address = (EditText) view.findViewById(R.id.customer_permanentadress_et);
        r_address = (EditText) view.findViewById(R.id.customer_resiencyadress_et);




        family_name=view.findViewById(R.id.familycustomer_query_name_et);
        family_passport=view.findViewById(R.id.familycustomer_passport_et);
        family_age=view.findViewById(R.id.familycustomer_age_et);




        spinnerdata();




       // spinnerdata();
/*
        if (gernal_information_spinner.getSelectedItemPosition()==1)
        {

            Toast.makeText(getActivity(), "Please Select Lead Type", Toast.LENGTH_SHORT).show();

//            progressBar.setVisibility(View.INVISIBLE);

        }*/
        //statusData.add(new ItemData("Ex Employee", "2"));
        submitgernalform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gernalformdata();

        // viewFlipper.setDisplayedChild(1);

            }
        });

        submitSingleapplicantform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(2);

                //Toast.makeText(getActivity(), "hey", Toast.LENGTH_SHORT).show();
            }
        });

        queryempolyetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(3);

                //Toast.makeText(getActivity(), "hey", Toast.LENGTH_SHORT).show();
            }
        });


        query_visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     viewFlipper.setDisplayedChild(4);

//                Toast.makeText(getActivity(), "hey", Toast.LENGTH_SHORT).show();
            }
        });
        socialmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onCreatealert("Query Was Generated successfully");
            }
        });


        applyforfamily_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            family_details();
            }
        });


    }


    private void family_details() {
        family_namee=family_name.getText().toString();
        family_passportt=family_passport.getText().toString();
        family_agee=family_age.getText().toString();
        if (family_namee.isEmpty())
        {
            family_name.setError("please Enter Name");
        }else if(family_passportt.isEmpty())
        {
            family_passport.setError("Please Enter Passport");

        }else if (family_agee.isEmpty())
        {
            family_age.setError("Please Enter Age");
        }else {
            Toast.makeText(getActivity(), "Submit", Toast.LENGTH_SHORT).show();
            alertfamilySubmit();
        }







    }

    public  void gernalformdata()
    {

        full_namee=full_name.getText().toString();
        mobile_noo=mobile_no.getText().toString();
        nationalityy=nationality.getText().toString();
        landline_noo=landline_no.getText().toString();
        passport_noo=passport_no.getText().toString();
        emaill=email.getText().toString();

        p_addresss=p_address.getText().toString();
        r_addresss=r_address.getText().toString();



        if (full_namee.isEmpty())
        {
            full_name.setError("Please Enter Name");
            full_name.findFocus();
        }
        else if(mobile_noo.isEmpty())
        {
            mobile_no.setError("Please enter mobile no");
            mobile_no.findFocus();
        }

        else if(nationalityy.isEmpty())
        {
            nationality.setError("Please enter Nationality");
            nationality.findFocus();
        }
        else if(landline_noo.isEmpty())
        {
            landline_no.setError("Please enter Landline no");
            landline_no.findFocus();
        }
        else if(passport_noo.isEmpty())
        {
            passport_no.setError("Please enter Passport no");
            passport_no.findFocus();
        }
        else if(emaill.isEmpty())
        {
            email.setError("Please enter Email Address");
            email.findFocus();
        }

        else if(p_addresss.isEmpty())
        {
            p_address.setError("Please enter permanent  Addres");
            p_address.findFocus();
        }
        else if(r_addresss.isEmpty())
        {
            r_address.setError("Please enter Resiency Address");
            r_address.findFocus();
        }
        else {

            viewFlipper.setDisplayedChild(1);

            Toast.makeText(getActivity(), "Submit", Toast.LENGTH_SHORT).show();
        }

    }
    public void alertfamilySubmit()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Family Details Was Submitted")
                .setMessage("Are you  Want To Add  More Family Details?")
                .setCancelable(false)
                .setPositiveButton("Add More", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //Toast.makeText(getActivity(), "Add more", Toast.LENGTH_SHORT).show();
                       // dialog.cancel();
                        viewFlipper.setDisplayedChild(5);

                        family_name.getText().clear();
                        family_age.getText().clear();
                        family_passport.getText().clear();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        viewFlipper.setDisplayedChild(1);

                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void spinnerdata() {
        applyingforfamily();
        passportcopy();
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


    private void applyingforfamily() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        applyingforfamily.setAdapter(adapter);
        applyingforfamily.setOnItemSelectedListener(this);
        applyingforfamily.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {

                    String text = as_single_spinner.getSelectedItem().toString();
                    Toast.makeText(getActivity(), text+"", Toast.LENGTH_SHORT).show();

                    viewFlipper.setDisplayedChild(5);

                } else {

                    String text = as_single_spinner.getSelectedItem().toString();
                    Toast.makeText(getActivity(), text+"", Toast.LENGTH_SHORT).show();
                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }



    private void passportcopy() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.PassportCopy));

        passportcopy.setAdapter(adapter);
        passportcopy.setOnItemSelectedListener(this);
        passportcopy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {

                    String text = passportcopy.getSelectedItem().toString();
                    Toast.makeText(getActivity(), text+"", Toast.LENGTH_SHORT).show();


                } else {

                    String text = as_single_spinner.getSelectedItem().toString();
                    Toast.makeText(getActivity(), text+"", Toast.LENGTH_SHORT).show();
                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }
    private void single_or_more() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        as_single_spinner.setAdapter(adapter);
        as_single_spinner.setOnItemSelectedListener(this);
        as_single_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {

                    String text = as_single_spinner.getSelectedItem().toString();
                    Toast.makeText(getActivity(), text+"", Toast.LENGTH_SHORT).show();

                    applicant_or_not.setVisibility(View.VISIBLE);
                } else {
                    applicant_or_not.setVisibility(View.GONE);
                    String text = as_single_spinner.getSelectedItem().toString();
                    Toast.makeText(getActivity(), text+"", Toast.LENGTH_SHORT).show();
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
}