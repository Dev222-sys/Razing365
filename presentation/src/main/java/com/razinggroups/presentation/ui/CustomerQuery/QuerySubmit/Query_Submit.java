package com.razinggroups.presentation.ui.CustomerQuery.QuerySubmit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import com.hbb20.CountryCodePicker;
import com.irozon.sneaker.Sneaker;
import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.presentation.R;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Query_Submit extends Fragment  implements AdapterView.OnItemSelectedListener{


    private ViewFlipper viewFlipper;

    ProgressDialog progressDialog;


    Spinner applyingforfamily,as_single_spinner, as_mentioned_spinner, customer_employe_type_sp, fragment_uk_visa_spinner,
            fragment_european_visa_spinner,
            fragment_usa_visa_spinner,
            fragment_intersted_type_spinner,
            fragment_Social_media_spinner,
            conacted_spinner,
            subscibe_spinner,
            passportcopy,
            realtion_ship,
            hear_about;


    String apply_family="null",apply_applicant="null",main_Applicent="null", employe_type="null",intersted_type="null",uk_visa="null",european_visa="null",usa_visa="null",hear_aboutt="null",social_media="null",conacted="null",subscibe="null";
    EditText full_name, mobile_no, nationality, landline_no, passport_no, email, p_address, r_address,p_address_postal_code,r_address_postal_code;

    String full_namee, mobile_noo, country_code,landline_noo, passport_noo, emaill, p_addresss, r_addresss,p_address_postal_codee,r_address_postal_codee,country_landline_code_ett;

    Button gernal_submit_btn;

    TextView lead_type_tv;
    LinearLayout applicant_or_not, uk_visa_spinner_ly, european_visa_ly, usa_visa_ly;

    EditText customer_work_org_name_et, customer_uk_year_et, customer_Reason_for_refusal_et,
            customer_migrate_et,customer_budget_et,customer_european_year_et,customer_european_for_refusal_et,customer_usa_year_et,customer_usa_for_refusal_et,print_advertisement_et;
    String  customer_work_org,uk_year,uk_reason,european_year,european_reason,usa_year,usa_reason ,customer_budget,customer_migrate;
    FloatingActionButton submitgernalform,
            submitSingleapplicantform,
            queryempolyetype,
            query_visa,
            socialmedia,
            applyforfamily_btn,
            back_arrowimage;

    EditText family_name,family_passport,family_age,family_passport_copy,country_landline_code_et;
    String  passport="null" , realtion="null",family_namee,family_passportt,family_agee,country_nationlilty;

    CountryCodePicker cuntry_nationality,country_no_code;

    CountryCurrencyButton customer_budget_currency_picker;
    TextView country_tv,country_no_code_tv,customer_budget_tv,tv_hint;

    LinearLayout socialmedia_ll ,print_advertisement_ll,channel_partners_ll,friend_collagues_ll;




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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        viewFlipper = view.findViewById(R.id.viewflipper_registration);

        try {


            if (SharedPrefManager.getInstans(getActivity()).getkyc_id().isEmpty()
            ) {
                viewFlipper.setDisplayedChild(0);

              //  Toast.makeText(getActivity(), "Empty", Toast.LENGTH_SHORT).show();

            }else if (SharedPrefManager.getInstans(getActivity()).getkyc_id().equals("1"))
            {
                viewFlipper.setDisplayedChild(0);

            }

            else {
                viewFlipper.setDisplayedChild(1);

            }

        }catch (Exception e)
        {

        }
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
        customer_migrate_et=view.findViewById(R.id.customer_migrate_et);
        customer_budget_et=view.findViewById(R.id.customer_budget_et);
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
        realtion_ship=view.findViewById(R.id.realtion_ship);
        hear_about=view.findViewById(R.id.hear_about);



        full_name = (EditText) view.findViewById(R.id.fragment_customer_query_name_et);
        mobile_no = (EditText) view.findViewById(R.id.fragment_customer_query_mobileetno);

        landline_no = (EditText) view.findViewById(R.id.customer_laindline_et);
        passport_no = (EditText) view.findViewById(R.id.customer_passport_et);
        email = (EditText) view.findViewById(R.id.customer_emailadress_et);
        p_address = (EditText) view.findViewById(R.id.customer_permanentadress_et);
        p_address_postal_code= view.findViewById(R.id.customer_permanentadress_postal_et);
        r_address = (EditText) view.findViewById(R.id.customer_resiencyadress_et);
        r_address_postal_code = (EditText) view.findViewById(R.id.customer_resiencyadress_postal_et);
        customer_european_year_et=view.findViewById(R.id.customer_european_year_et);
        customer_european_for_refusal_et=view.findViewById(R.id.customer_european_year_et);
        customer_usa_year_et=view.findViewById(R.id.customer_usa_year_et);
        customer_usa_for_refusal_et=view.findViewById(R.id.customer_usa_for_refusal_et);
        family_name=view.findViewById(R.id.familycustomer_query_name_et);
        family_passport=view.findViewById(R.id.familycustomer_passport_et);
        family_age=view.findViewById(R.id.familycustomer_age_et);
        print_advertisement_et=view.findViewById(R.id.print_advertisement_et);
        //cuntry_nationality code picker
        cuntry_nationality=view.findViewById(R.id.cuntry_nationality);
        country_tv=view.findViewById(R.id.country_tv);
        country_no_code=view.findViewById(R.id.country_no_code);
      //  country_landline_code=view.findViewById(R.id.country_landline_code);

        country_no_code_tv=view.findViewById(R.id.country_no_code_tv);
        country_landline_code_et=view.findViewById(R.id.country_landline_code_et);
        customer_budget_tv=view.findViewById(R.id.customer_budget_tv);
        customer_budget_currency_picker=view.findViewById(R.id.customer_budget_currency_picker);
        back_arrowimage=view.findViewById(R.id.back_arrowimage);
        tv_hint=view.findViewById(R.id.tv_hint);

        //Liner Layout
        socialmedia_ll=view.findViewById(R.id.socialmedia_ll);
        print_advertisement_ll=view.findViewById(R.id.print_advertisement_ll);
      //  channel_partners_ll=view.findViewById(R.id.channel_partners_ll);
       // friend_collagues_ll=view.findViewById(R.id.friend_collagues_ll);
        spinnerdata();


        back_arrowimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewFlipper.setDisplayedChild(1);


            }
        });
        customer_budget_currency_picker.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(getActivity(), "please select Country", Toast.LENGTH_SHORT).show();
                } else {
                    customer_budget_tv.setText(country.getCurrency().getSymbol());
/*
                    Toast.makeText(getActivity(),
                            String.format("name: %s\ncurrencySymbol: %s", country.getName(), country.getCurrency().getSymbol())
                            , Toast.LENGTH_SHORT).show();*/
                }
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });

        cuntry_nationality.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country_tv.setText(cuntry_nationality.getSelectedCountryName());

                //Toast.makeText(getContext(), "Updated " + cuntry_nationality.getSelectedCountryName(), Toast.LENGTH_SHORT).show();
            }
        });

        country_no_code.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country_no_code_tv.setText(country_no_code.getSelectedCountryCodeWithPlus());

                //Toast.makeText(getContext(), "Updated " + cuntry_nationality.getSelectedCountryName(), Toast.LENGTH_SHORT).show();
            }
        });
      /*  country_landline_code.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {

                //country_landline_code_tv.setText( country_landline_code.getSelectedCountryCodeWithPlus());

                //Toast.makeText(getContext(), "Updated " + cuntry_nationality.getSelectedCountryName(), Toast.LENGTH_SHORT).show();
            }
        });
*/










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
                if (apply_applicant.equals("null")){



                    Sneaker.with(getActivity())
                            .setTitle("Please Select Applicant !")
                            .setMessage("")
                            .sneakError();

                }


                else if(apply_family.equals("null"))
                {

                    Sneaker.with(getActivity())
                            .setTitle("Please Select Family details !")
                            .setMessage("")
                            .sneakError();


                }
                else {

                    viewFlipper.setDisplayedChild(2);

                }



                //Toast.makeText(getActivity(), "hey", Toast.LENGTH_SHORT).show();
            }
        });

        queryempolyetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                customer_work_org=customer_work_org_name_et.getText().toString();
                uk_year=customer_uk_year_et.getText().toString();
                uk_reason=customer_Reason_for_refusal_et.getText().toString();
                european_year=customer_european_year_et.getText().toString();
                european_reason=customer_european_for_refusal_et.getText().toString();
                usa_year=customer_usa_year_et.getText().toString();
                usa_reason=customer_usa_for_refusal_et.getText().toString();




                if (employe_type.equals("null"))
                {
                    Sneaker.with(getActivity())
                            .setTitle("Please Select business owner or a salaried employee type !")
                            .setMessage("")
                            .sneakError();

                }else if(customer_work_org.isEmpty())
                {
                    Sneaker.with(getActivity())
                            .setTitle("Enter Your business or Organization !")
                            .setMessage("")
                            .sneakError();

                } else if (uk_visa.equals("null"))
                {

                    Sneaker.with(getActivity())
                            .setTitle("Please Select UK Visa  !")
                            .setMessage("")
                            .sneakError();


                }else if (european_visa.equals("null"))
                {
                    Sneaker.with(getActivity())
                            .setTitle("Please Select European Visa !")
                            .setMessage("")
                            .sneakError();


                }else if(usa_visa.equals("null"))

                {
                    Sneaker.with(getActivity())
                            .setTitle("Please Select USA Visa !")
                            .setMessage("")
                            .sneakError();



                }
                else {
                   // Toast.makeText(getActivity(), employe_type+intersted_type+"", Toast.LENGTH_SHORT).show();

                    viewFlipper.setDisplayedChild(3);
                }

                //Toast.makeText(getActivity(), "hey", Toast.LENGTH_SHORT).show();
            }
        });


        query_visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 customer_migrate=customer_migrate_et.getText().toString();
                 customer_budget=customer_budget_et.getText().toString();


                 if (intersted_type.equals("null"))

                {


                    Sneaker.with(getActivity())
                            .setTitle("please Select Are you interested in residency or in a second passport !")
                            .setMessage("")
                            .sneakError();



                }else if (customer_migrate.isEmpty())
                {

                    Sneaker.with(getActivity())
                            .setTitle("Enter your migrate details  !")
                            .setMessage("")
                            .sneakError();




                }else if(customer_budget.isEmpty())
                {
                    Sneaker.with(getActivity())
                            .setTitle("Enter Your Budget OR Select Currency Type  !")
                            .setMessage("")
                            .sneakError();




                }
                 else
                 {

                     viewFlipper.setDisplayedChild(4);
                 }






//                Toast.makeText(getActivity(), "hey", Toast.LENGTH_SHORT).show();
            }
        });
        socialmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (social_media.equals("null"))
                {
                    social_media=print_advertisement_et.getText().toString().trim();


                }



                if (hear_aboutt.equals("null"))
                {


                    Sneaker.with(getActivity())
                            .setTitle("Please Select  Where did you hear about us? ")
                            .setMessage("")
                            .sneakError();


                }else if (conacted.equals("null"))
                {
                          Sneaker.with(getActivity())
                            .setTitle("Please Select  How would you like to be contacted ? ")
                            .setMessage("")
                            .sneakError();



                }else if (subscibe.equals("null"))
                {
                    Sneaker.with(getActivity())
                            .setTitle("Please Select  Would you like to subscribe for our emails and any relevant information ? or any updates  ? ")
                            .setMessage("")
                            .sneakError();



                }else {



                     progressDialog.show();

                    //SharedPrefManager.getInstans(getActivity()).getUsername();

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi().submitQueryFinal(SharedPrefManager.getInstans(getActivity()).getkyc_id(),apply_applicant,main_Applicent,apply_family,employe_type,customer_work_org,
                                    uk_visa,uk_year,uk_reason,european_visa,european_year,european_reason,usa_visa,usa_year,usa_reason,intersted_type,customer_migrate,
                                    customer_budget,hear_aboutt,social_media,conacted,subscibe);
                                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                           // Toast.makeText(getActivity(), response.toString()+"", Toast.LENGTH_SHORT).show();
                            String s = null;
                            if (response.code()==200)
                            {
                                try {
                                    s = response.body().string();
                                    JSONObject jsonObject = new JSONObject(s);
                                    String msg = jsonObject.getString("message");
                                    progressDialog.dismiss();
                                    //  Toast.makeText(getActivity(), SharedPrefManager.getInstans(getActivity()).getkyc_id(), Toast.LENGTH_SHORT).show();
                                    onCreatealert(msg);
                                    SharedPrefManager.getInstans(getActivity()).kyc_id("1");

                                    getActivity().onBackPressed();
                                } catch (IOException | JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                progressDialog.dismiss();

                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });


                    //  SharedPrefManager.getInstans(getActivity()).getUsername();

                    // Toast.makeText(getActivity(), SharedPrefManager.getInstans(getActivity()).getUsername(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();


                   // onCreatealert("Query Was Generated successfully");
                }


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


        if (realtion.equals("null"))
        {
            Sneaker.with(getActivity())
                    .setTitle("Please select Relationship of family member ")
                    .setMessage("")
                    .sneakError();
        }
        else  if (family_namee.isEmpty())
        {
            Sneaker.with(getActivity())
                    .setTitle("please Enter Name ")
                    .setMessage("")
                    .sneakError();
        }else if(family_passportt.isEmpty())
        {

            Sneaker.with(getActivity())
                    .setTitle("Please Enter Passport")
                    .setMessage("")
                    .sneakError();

        }else if (family_agee.isEmpty())
        {
            Sneaker.with(getActivity())
                    .setTitle("Please Enter Age")
                    .setMessage("")
                    .sneakError();

        }else if (passport.equals("null"))
        {
            Sneaker.with(getActivity())
                    .setTitle("Please Select Passport Copy")
                    .setMessage("")
                    .sneakError();

        }



        else {


            progressDialog.show();


            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi().submitQueryFamily(SharedPrefManager.getInstans(getActivity()).getkyc_id(),realtion,family_namee,family_passportt,family_agee,passport);

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


                            progressDialog.dismiss();

                            alertfamilySubmit(msg);



                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();

                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            });


            //  SharedPrefManager.getInstans(getActivity()).getUsername();

            // Toast.makeText(getActivity(), SharedPrefManager.getInstans(getActivity()).getUsername(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }







    }

    public  void gernalformdata()
    {

        full_namee=full_name.getText().toString();
        mobile_noo=mobile_no.getText().toString();

        landline_noo=landline_no.getText().toString();
        passport_noo=passport_no.getText().toString();
        emaill=email.getText().toString();
        country_nationlilty=country_tv.getText().toString();
        p_addresss=p_address.getText().toString();
        r_addresss=r_address.getText().toString();
        p_address_postal_codee=p_address_postal_code.getText().toString();
        r_address_postal_codee=r_address_postal_code.getText().toString();
        country_landline_code_ett=country_landline_code_et.getText().toString();
        country_code=country_no_code_tv.getText().toString();



        if (full_namee.isEmpty())
        {
            //progressDialog.show();

            Sneaker.with(getActivity())
                    .setTitle("Please Enter full name as passport !")
                    .setMessage("")
                    .sneakError();


        }
        else if(mobile_noo.isEmpty())
        {


            Sneaker.with(getActivity())
                    .setTitle("Please enter mobile no.!")
                    .setMessage("")
                    .sneakError();


        }

        else if(country_nationlilty.isEmpty())
        {
            Sneaker.with(getActivity())
                    .setTitle("Please Select Nationality !")
                    .setMessage("")
                    .sneakError();


        }
        else if(landline_noo.isEmpty())
        {
            Sneaker.with(getActivity())
                    .setTitle("Please enter Landline no.!")
                    .setMessage("")
                    .sneakError();

        }
        else if(passport_noo.isEmpty())
        {

            Sneaker.with(getActivity())
                    .setTitle("Please enter Passport no.!")
                    .setMessage("")
                    .sneakError();

        }
        else if(emaill.isEmpty())
        {

            Sneaker.with(getActivity())
                    .setTitle("Please enter Email Address !")
                    .setMessage("")
                    .sneakError();

        }

        else if(p_addresss.isEmpty())
        {

            Sneaker.with(getActivity())
                    .setTitle("Please enter permanent  Address !")
                    .setMessage("")
                    .sneakError();

        }
        else if(r_addresss.isEmpty())
        {

            Sneaker.with(getActivity())
                    .setTitle("Please enter Resiency Address")
                    .setMessage("")
                    .sneakError();
        }
        else {


            progressDialog.show();


            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi().submitQueryGenerate( SharedPrefManager.getInstans(getActivity()).getUsername(),full_namee,emaill,country_code+mobile_noo,
                            country_landline_code_et.getText().toString()+ landline_noo,passport_noo,country_nationlilty,p_addresss,p_address_postal_codee,r_addresss,r_address_postal_codee);
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
                            String kyc_id = jsonObject.getString("kyc_id");
                            progressDialog.dismiss();
                            SharedPrefManager.getInstans(getActivity()).kyc_id(kyc_id);
                          //  Toast.makeText(getActivity(), SharedPrefManager.getInstans(getActivity()).getkyc_id(), Toast.LENGTH_SHORT).show();
                            onCreatealert(msg);
                          viewFlipper.setDisplayedChild(1);

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();

                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            });


          //  SharedPrefManager.getInstans(getActivity()).getUsername();

           // Toast.makeText(getActivity(), SharedPrefManager.getInstans(getActivity()).getUsername(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

    }
    public void alertfamilySubmit(String title)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.logo_black)
                .setTitle("Do you  want to add  another family details ?")

                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //Toast.makeText(getActivity(), "Add more", Toast.LENGTH_SHORT).show();
                       // dialog.cancel();
                      viewFlipper.setDisplayedChild(5);

                        family_name.getText().clear();
                        family_age.getText().clear();
                        family_passport.getText().clear();
                        realtion_ship();
                        passportcopy();

                        /*realtion_ship.setSelected(true);
                        passportcopy.setSelected(true);
*/

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        viewFlipper.setDisplayedChild(2);

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
                //getActivity().onBackPressed();
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
        realtion_ship();
        hear_about();
    }


    private void applyingforfamily() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        applyingforfamily.setAdapter(adapter);
        applyingforfamily.setOnItemSelectedListener(this);
        applyingforfamily.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {

                    apply_family= applyingforfamily.getSelectedItem().toString();


                    viewFlipper.setDisplayedChild(5);

                } else if(position == 2)
                {



                    apply_family= applyingforfamily.getSelectedItem().toString();

                    //viewFlipper.setDisplayedChild(2);
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

                  passport = passportcopy.getSelectedItem().toString();
                    Toast.makeText(getActivity(), passport+"", Toast.LENGTH_SHORT).show();


                } else if (position == 2) {

                    passport = passportcopy.getSelectedItem().toString();
                    Toast.makeText(getActivity(), passport+"", Toast.LENGTH_SHORT).show();

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }
    private void realtion_ship() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.realtionship_item));

        realtion_ship.setAdapter(adapter);
        realtion_ship.setOnItemSelectedListener(this);
        realtion_ship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {



                } else {



                     realtion = realtion_ship.getSelectedItem().toString();
                    //Toast.makeText(getActivity(), realtion+"", Toast.LENGTH_SHORT).show();
                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }



    private void hear_about() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.hear_about));

        hear_about.setAdapter(adapter);
        hear_about.setOnItemSelectedListener(this);
        hear_about.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                //Toast.makeText(getActivity(), hear_about.getSelectedItem().toString()+"", Toast.LENGTH_SHORT).show();

                socialmedia_ll.setVisibility(View.GONE);
                print_advertisement_ll.setVisibility(View.GONE);
               // channel_partners_ll.setVisibility(View.GONE);
             //   friend_collagues_ll.setVisibility(View.GONE);

                if (position == 0) {




                } else if (position==1){

                    hear_aboutt=hear_about.getSelectedItem().toString();
                    socialmedia_ll.setVisibility(View.VISIBLE);

                }else if (position==2)
                {
                    print_advertisement_ll.setVisibility(View.VISIBLE);
                    tv_hint.setText("Print Advertisement");
                    print_advertisement_et.setHint("Where?");
                    hear_aboutt=hear_about.getSelectedItem().toString();
                }

                else if (position==3)
                {
                    print_advertisement_ll.setVisibility(View.VISIBLE);
                    tv_hint.setText("Channel Partners");
                    print_advertisement_et.setHint("Who? Which Organization?");
                    hear_aboutt=hear_about.getSelectedItem().toString();


                }
                else if (position==4)
                {

                    print_advertisement_ll.setVisibility(View.VISIBLE);
                    tv_hint.setText("Friends OR Colleagues");
                    print_advertisement_et.setHint("Who?");
                    hear_aboutt=hear_about.getSelectedItem().toString();
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
                    apply_applicant=as_single_spinner.getSelectedItem().toString();
                    applicant_or_not.setVisibility(View.VISIBLE);
                   // Toast.makeText(getActivity(), apply_applicant+"", Toast.LENGTH_SHORT).show();
                } else if (position==2){
                    apply_applicant=as_single_spinner.getSelectedItem().toString();
                    applicant_or_not.setVisibility(View.GONE);

                    //Toast.makeText(getActivity(), apply_applicant+"", Toast.LENGTH_SHORT).show();

                    }
                else {

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
                    main_Applicent=as_mentioned_spinner.getSelectedItem().toString();
                   // Toast.makeText(getContext(), main_Applicent+"", Toast.LENGTH_SHORT).show();
                 //   apply_applicant=as_mentioned_spinner.getSelectedItem().toString();

                } else if (position==2){
             //       apply_applicant=as_mentioned_spinner.getSelectedItem().toString();
                    main_Applicent=as_mentioned_spinner.getSelectedItem().toString();
                  //  Toast.makeText(getContext(), main_Applicent+"", Toast.LENGTH_SHORT).show();

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
                    employe_type=customer_employe_type_sp.getSelectedItem().toString();

                } else if (position==2){
                    employe_type=customer_employe_type_sp.getSelectedItem().toString();

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
                    uk_visa=fragment_uk_visa_spinner.getSelectedItem().toString();

                } else if (position==2)
                {
                    uk_visa=fragment_uk_visa_spinner.getSelectedItem().toString();
                    uk_visa_spinner_ly.setVisibility(View.GONE);
                }else
                {

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
                   european_visa=fragment_european_visa_spinner.getSelectedItem().toString();
                }else if
                (position==2)
                {
                    european_visa=fragment_european_visa_spinner.getSelectedItem().toString();
                    european_visa_ly.setVisibility(View.GONE);
                }
                else {

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
                    usa_visa=fragment_usa_visa_spinner.getSelectedItem().toString();

                } else if (position==2) {
                    usa_visa=fragment_usa_visa_spinner.getSelectedItem().toString();

                    usa_visa_ly.setVisibility(View.GONE);
                }
                else {


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
                    intersted_type=fragment_intersted_type_spinner.getSelectedItem().toString();
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else if (position==2){

                    intersted_type=fragment_intersted_type_spinner.getSelectedItem().toString();

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
                    conacted=conacted_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else if (position==2){

                    conacted=conacted_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

                }else if(position==3)

                {

                    conacted=conacted_spinner.getSelectedItem().toString();

                }else if(position==4)

                {

                    conacted=conacted_spinner.getSelectedItem().toString();

                }else if(position==5)

                {

                    conacted=conacted_spinner.getSelectedItem().toString();
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
                    subscibe=subscibe_spinner.getSelectedItem().toString();
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else if (position==2){
                    subscibe=subscibe_spinner.getSelectedItem().toString();


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
                    social_media=fragment_Social_media_spinner.getSelectedItem().toString();
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else if (position==2){

                    social_media=fragment_Social_media_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

                }
                else if (position==3){

                    social_media=fragment_Social_media_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

                }
                else if (position==4){

                    social_media=fragment_Social_media_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

                }
                else if (position==5){

                    social_media=fragment_Social_media_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

                }
                else if (position==6){

                    social_media=fragment_Social_media_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

                }
                else if (position==7){

                    social_media=fragment_Social_media_spinner.getSelectedItem().toString();

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