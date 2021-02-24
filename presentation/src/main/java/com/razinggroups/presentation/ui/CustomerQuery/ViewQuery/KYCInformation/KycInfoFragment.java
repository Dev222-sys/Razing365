package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.KYCInformation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.CustomerSection.CustomerQueryMainFragment;
import com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.ViewQueryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KycInfoFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    LinearLayout KycQueryLl, socialmedia_ll, print_advertisement_ll, usa_visa_ly, european_visa_ly, uk_visa_spinner_ly;

    Button Kyc_submitQuery, Kyc_DeleteQuery;



    EditText Kyc_work_org_name_et, kyc_uk_year_et, Kyc_Reason_for_refusal_et, kyc_european_year_et, kyc_european_for_refusal_et, kyc_usa_year_et, kyc_usa_for_refusal_et,
            kyc_migrate_et, kyc_budget_et, kyc_print_advertisement_et;




    Spinner kyc_as_single_spinner, kyc_employe_type_sp, Kyc_uk_visa_spinner, kyc_european_visa_spinner, kyc_usa_visa_spinner,
            kyc_intersted_type_spinner, kyc_hear_about_spiner, kyc_Social_media_spinner, kyc_conacted_spinner, kyc_subscibe_spinner;

    String apply_applicant = "null", intersted_type = "null", employe_type = "null", uk_visa = "null", european_visa = "null", usa_visa = "null", hear_aboutt = "null", platform = "null", conacted = "null", subscibe = "null";
    TextView textviewhint;

    public KycInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kyc_info, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        kyc_as_single_spinner = view.findViewById(R.id.kyc_as_single_spinner);
        kyc_employe_type_sp = view.findViewById(R.id.kyc_employe_type_sp);
        Kyc_uk_visa_spinner = view.findViewById(R.id.Kyc_uk_visa_spinner);
        kyc_european_visa_spinner = view.findViewById(R.id.kyc_european_visa_spinner);
        kyc_usa_visa_spinner = view.findViewById(R.id.kyc_usa_visa_spinner);
        kyc_intersted_type_spinner = view.findViewById(R.id.kyc_intersted_type_spinner);
        kyc_hear_about_spiner = view.findViewById(R.id.kyc_hear_about_spiner);
        kyc_Social_media_spinner = view.findViewById(R.id.kyc_Social_media_spinner);
        kyc_conacted_spinner = view.findViewById(R.id.kyc_conacted_spinner);
        kyc_subscibe_spinner = view.findViewById(R.id.kyc_subscibe_spinner);
        textviewhint=view.findViewById(R.id.textviewhint);

        Kyc_work_org_name_et = view.findViewById(R.id.Kyc_work_org_name_et);
        kyc_uk_year_et = view.findViewById(R.id.kyc_uk_year_et);
        Kyc_Reason_for_refusal_et = view.findViewById(R.id.Kyc_Reason_for_refusal_et);
        kyc_european_year_et = view.findViewById(R.id.kyc_european_year_et);
        kyc_european_for_refusal_et = view.findViewById(R.id.kyc_european_for_refusal_et);
        kyc_usa_year_et = view.findViewById(R.id.kyc_usa_year_et);
        kyc_usa_for_refusal_et = view.findViewById(R.id.kyc_usa_for_refusal_et);
        kyc_migrate_et = view.findViewById(R.id.kyc_migrate_et);
        kyc_budget_et = view.findViewById(R.id.kyc_budget_et);
        kyc_print_advertisement_et = view.findViewById(R.id.kyc_print_advertisement_et);

        String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();
        KycQueryLl = view.findViewById(R.id.KycQueryLl);
        socialmedia_ll = view.findViewById(R.id.socialmedia_ll);
        print_advertisement_ll = view.findViewById(R.id.print_advertisement_ll);
         usa_visa_ly = view.findViewById(R.id.usa_visa_ly);
        european_visa_ly = view.findViewById(R.id.european_visa_ly);
        uk_visa_spinner_ly = view.findViewById(R.id.uk_visa_spinner_ly);
        Kyc_submitQuery = view.findViewById(R.id.Kyc_submitQuery);
        Kyc_DeleteQuery = view.findViewById(R.id.Kyc_DeleteQuery);


        getgernalinfromation();
        Spinnerdata();
        // Toast.makeText(getContext(), usertype+"", Toast.LENGTH_SHORT).show();
        if (usertype.trim().equalsIgnoreCase("MasterAdmin")) {

            KycQueryLl.setVisibility(View.VISIBLE);
            // Toast.makeText(getContext(), "masterAdmin", Toast.LENGTH_SHORT).show();

        }
        Kyc_submitQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateKycInfoFragment();
            }
        });
        Kyc_DeleteQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), Kyc_DeleteQuery + "", Toast.LENGTH_SHORT).show();
                deletedata();
            }
        });
    }

    public void getgernalinfromation() {

        //Toast.makeText(getContext(), SharedPrefManager.getInstans(getActivity()).getdatakyc_id()+"", Toast.LENGTH_SHORT).show();
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().getgernalinfromation(SharedPrefManager.getInstans(getActivity()).getdatakyc_id());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //  Log.d("datadata", response.toString());
                //  Toast.makeText(getActivity(), response.code()+"", Toast.LENGTH_SHORT).show();

                String s = null;


                if (response.code() == 200) {
                    try {

                        s = response.body().string();

                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("records");
                        for (int i = 0; i <= jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            // String name,mobile_no,mobile_code,nationality,lindline_code,lindline_no,passport_number,email_address,p_address,p_postalcode,R_address,R_postalcode;

                            Kyc_work_org_name_et.setText(jsonObject1.getString("name_of_org"));
                            kyc_uk_year_et.setText(jsonObject1.getString("uk_year"));
                            Kyc_Reason_for_refusal_et.setText(jsonObject1.getString("uk_reasion"));
                            kyc_european_year_et.setText(jsonObject1.getString("european_visa_year"));
                            kyc_european_for_refusal_et.setText(jsonObject1.getString("european_visa_reasion"));
                            kyc_usa_year_et.setText(jsonObject1.getString("usa_year"));
                            kyc_usa_for_refusal_et.setText(jsonObject1.getString("usa_reasion"));
                            kyc_migrate_et.setText(jsonObject1.getString("migrate_plan"));
                            kyc_budget_et.setText(jsonObject1.getString("investment_budget"));
                            platform = jsonObject1.getString("platform");
                            jsonObject1.getString("employeement_type");

                            if (jsonObject1.getString("single_applicant").equals("Yes")) {
                                kyc_as_single_spinner.setSelection(1);
                                apply_applicant = "Yes";
                            } else if (jsonObject1.getString("single_applicant").equals("No")) {

                                kyc_as_single_spinner.setSelection(2);
                                apply_applicant = "No";
                            }

                            if (jsonObject1.getString("employeement_type").equals("Business Owner")) {
                                kyc_employe_type_sp.setSelection(1);
                                employe_type = jsonObject1.getString("employeement_type");

                            } else if (jsonObject1.getString("employeement_type").equals("Salaried Employee")) {
                                employe_type = jsonObject1.getString("employeement_type");

                                kyc_employe_type_sp.setSelection(2);
                            }

                            if (jsonObject1.getString("reference_through").equals("Social Media")) {
                                hear_aboutt = jsonObject1.getString("reference_through");
                                kyc_hear_about_spiner.setSelection(1);
                                socialmedia_ll.setVisibility(View.VISIBLE);
                                if (jsonObject1.getString("platform").equals("Facebook")) {
                                    kyc_Social_media_spinner.setSelection(1);

                                } else if (jsonObject1.getString("platform").equals("Instagram")) {
                                    kyc_Social_media_spinner.setSelection(2);
                                } else if (jsonObject1.getString("platform").equals("LinkedIn")) {
                                    kyc_Social_media_spinner.setSelection(3);
                                } else if (jsonObject1.getString("platform").equals("Snapchat")) {
                                    kyc_Social_media_spinner.setSelection(4);
                                } else if (jsonObject1.getString("platform").equals("YouTube")) {
                                    kyc_Social_media_spinner.setSelection(5);
                                } else if (jsonObject1.getString("platform").equals("WhatsApp")) {
                                    kyc_Social_media_spinner.setSelection(6);
                                }


                            } else if (jsonObject1.getString("reference_through").equals("Print Advertisement")) {
                                hear_aboutt = jsonObject1.getString("reference_through");
                                kyc_hear_about_spiner.setSelection(2);
                                print_advertisement_ll.setVisibility(View.VISIBLE);
                                kyc_print_advertisement_et.setText(platform);
                            } else if (jsonObject1.getString("reference_through").equals("Channel Partners")) {
                                kyc_hear_about_spiner.setSelection(3);
                                hear_aboutt = jsonObject1.getString("reference_through");
                                textviewhint.setText("Channel Partners");
                                print_advertisement_ll.setVisibility(View.VISIBLE);
                                kyc_print_advertisement_et.setText(platform);

                            } else if (jsonObject1.getString("reference_through").equals("Friends or Colleagues")) {
                                kyc_hear_about_spiner.setSelection(4);
                                hear_aboutt = jsonObject1.getString("reference_through");
                                textviewhint.setText("Friends or Colleagues");
                                print_advertisement_ll.setVisibility(View.VISIBLE);
                                kyc_print_advertisement_et.setText(platform);

                            }


                            if (jsonObject1.getString("usa_visa_status").equals("Yes")) {
                                usa_visa_ly.setVisibility(View.VISIBLE);
                                kyc_usa_visa_spinner.setSelection(1);
                                usa_visa = jsonObject1.getString("usa_visa_status");
                            } else if (jsonObject1.getString("usa_visa_status").equals("No")) {
                                usa_visa = jsonObject1.getString("usa_visa_status");
                                kyc_usa_visa_spinner.setSelection(2);
                            }

                            if (jsonObject1.getString("european_visa_status").equals("Yes")) {
                                european_visa_ly.setVisibility(View.VISIBLE);
                                kyc_european_visa_spinner.setSelection(1);
                                european_visa = jsonObject1.getString("european_visa_status");
                            } else if (jsonObject1.getString("european_visa_status").equals("No")) {

                                kyc_european_visa_spinner.setSelection(2);

                                european_visa = jsonObject1.getString("european_visa_status");

                            }


                            if (jsonObject1.getString("uk_visa_status").equals("Yes")) {
                                uk_visa_spinner_ly.setVisibility(View.VISIBLE);
                                uk_visa = jsonObject1.getString("uk_visa_status");
                                Kyc_uk_visa_spinner.setSelection(1);
                            } else if (jsonObject1.getString("uk_visa_status").equals("No")) {
                                uk_visa = jsonObject1.getString("uk_visa_status");
                                Kyc_uk_visa_spinner.setSelection(2);
                            }


                            if (jsonObject1.getString("subscribe_email").equals("Yes")) {
                                kyc_subscibe_spinner.setSelection(1);
                                subscibe = jsonObject1.getString("subscribe_email");
                            } else if (jsonObject1.getString("subscribe_email").equals("No")) {
                                kyc_subscibe_spinner.setSelection(2);
                                subscibe = jsonObject1.getString("subscribe_email");
                            }


                            /// connected
                            if (jsonObject1.getString("contact_through").equals("Email")) {
                                kyc_conacted_spinner.setSelection(1);
                                conacted = jsonObject1.getString("contact_through");

                            } else if (jsonObject1.getString("contact_through").equals("SMS")) {
                                kyc_conacted_spinner.setSelection(2);

                                conacted = jsonObject1.getString("contact_through");

                            } else if (jsonObject1.getString("contact_through").equals("Call")) {
                                kyc_conacted_spinner.setSelection(3);
                                conacted = jsonObject1.getString("contact_through");


                            } else if (jsonObject1.getString("contact_through").equals("Video Call")) {
                                kyc_conacted_spinner.setSelection(4);
                                conacted = jsonObject1.getString("contact_through");


                            } else if (jsonObject1.getString("contact_through").equals("WhatsApp")) {
                                kyc_conacted_spinner.setSelection(5);
                                conacted = jsonObject1.getString("contact_through");


                            }
                            if (jsonObject1.getString("interested_type").equals("Residency")) {
                                kyc_intersted_type_spinner.setSelection(1);
                                intersted_type = jsonObject1.getString("interested_type");

                            } else if (jsonObject1.getString("interested_type").equals("Second Passport")) {
                                kyc_intersted_type_spinner.setSelection(2);
                                intersted_type = jsonObject1.getString("interested_type");

                            }

                            /*
                            kyc_print_advertisement_et.setText(jsonObject1.getString(""));
                            kyc_channel_partners_et.setText(jsonObject1.getString(""));
                            kyc_friend_collagues_et.setText(jsonObject1.getString(""));*/


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

    public void updateKycInfoFragment() {

      //  spinnerdata();

     //   Log.d("spniner", apply_applicant + intersted_type + employe_type + uk_visa + european_visa + usa_visa + hear_aboutt + platform + conacted + subscibe + "onClick: ");


        if (hear_aboutt.equals("Print Advertisement"))
        {
            platform=kyc_print_advertisement_et.getText().toString().trim();
        }else if (hear_aboutt.equals("Channel Partners"))
        {
            platform=kyc_print_advertisement_et.getText().toString().trim();
        }else if (hear_aboutt.equals("Friends or Colleagues"))
        {
            platform=kyc_print_advertisement_et.getText().toString().trim();
        }
        String orgname, uk_year, uk_reason, european_year, european_reason, usa_year, usa_reason, migrate, budget, friend_collagues, channel_partners, print_advertisement;
        orgname = Kyc_work_org_name_et.getText().toString();
        uk_year = kyc_uk_year_et.getText().toString();
        uk_reason = Kyc_Reason_for_refusal_et.getText().toString();
        european_year = kyc_european_year_et.getText().toString();
        european_reason = kyc_european_for_refusal_et.getText().toString();
        usa_year = kyc_usa_year_et.getText().toString();
        usa_reason = kyc_usa_for_refusal_et.getText().toString();
        migrate = kyc_migrate_et.getText().toString();
        budget = kyc_budget_et.getText().toString();
   /*

        platform = kyc_friend_collagues_et.getText().toString();
        platform = kyc_channel_partners_et.getText().toString();
        platform = kyc_print_advertisement_et.getText().toString();
*/
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().update_kyc_infromation(SharedPrefManager.getInstans(getActivity()).getdatakyc_id(), apply_applicant, " ", " ", employe_type,
                        orgname, uk_visa, uk_year, uk_reason, european_visa, european_year, european_reason, usa_visa, usa_year, usa_reason, intersted_type, migrate, budget, hear_aboutt, platform, conacted, subscibe);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;

                // Toast.makeText(getActivity(), response.code()+"", Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    try {
                        s = response.body().string();
                        JSONObject jsonObject = new JSONObject(s);
                        String msg = jsonObject.getString("message");

                        alertfamilySubmit(msg);
                        getActivity().onBackPressed();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {

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

    public void alertfamilySubmit(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.logo_black)
                .setTitle(title)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void spinnerdata() {
        single_or_more();
        bussines_salaeried();
        fragment_uk_visa_spinner();
        fragment_european_visa_spinner();
        fragment_usa_visa_spinner();
        fragment_intersted_type_spinner();
        hear_about();
        conacted_spinner();
        subscibe_spinner();
    }

    private void single_or_more() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        kyc_as_single_spinner.setAdapter(adapter);
        kyc_as_single_spinner.setOnItemSelectedListener(this);
        kyc_as_single_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    apply_applicant = kyc_as_single_spinner.getSelectedItem().toString();
                } else if (position == 2) {
                    apply_applicant = kyc_as_single_spinner.getSelectedItem().toString();


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

        kyc_employe_type_sp.setAdapter(adapter);
        kyc_employe_type_sp.setOnItemSelectedListener(this);
        kyc_employe_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (position == 1) {
                    employe_type = kyc_employe_type_sp.getSelectedItem().toString();

                } else if (position == 2) {
                    employe_type = kyc_employe_type_sp.getSelectedItem().toString();

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

        Kyc_uk_visa_spinner.setAdapter(adapter);
        Kyc_uk_visa_spinner.setOnItemSelectedListener(this);
        Kyc_uk_visa_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    uk_visa_spinner_ly.setVisibility(View.VISIBLE);
                    uk_visa = Kyc_uk_visa_spinner.getSelectedItem().toString();

                } else if (position == 2) {
                    uk_visa = Kyc_uk_visa_spinner.getSelectedItem().toString();
                    uk_visa_spinner_ly.setVisibility(View.GONE);
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

        kyc_european_visa_spinner.setAdapter(adapter);
        kyc_european_visa_spinner.setOnItemSelectedListener(this);
        kyc_european_visa_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    european_visa_ly.setVisibility(View.VISIBLE);
                    european_visa = kyc_european_visa_spinner.getSelectedItem().toString();
                } else if
                (position == 2) {
                    european_visa = kyc_european_visa_spinner.getSelectedItem().toString();
                    european_visa_ly.setVisibility(View.GONE);
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

        kyc_usa_visa_spinner.setAdapter(adapter);
        kyc_usa_visa_spinner.setOnItemSelectedListener(this);
        kyc_usa_visa_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    usa_visa_ly.setVisibility(View.VISIBLE);
                    usa_visa = kyc_usa_visa_spinner.getSelectedItem().toString();

                } else if (position == 2) {
                    usa_visa = kyc_usa_visa_spinner.getSelectedItem().toString();

                    usa_visa_ly.setVisibility(View.GONE);
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

        kyc_intersted_type_spinner.setAdapter(adapter);
        kyc_intersted_type_spinner.setOnItemSelectedListener(this);
        kyc_intersted_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    intersted_type = kyc_intersted_type_spinner.getSelectedItem().toString();
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else if (position == 2) {

                    intersted_type = kyc_intersted_type_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

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

        kyc_hear_about_spiner.setAdapter(adapter);
        kyc_hear_about_spiner.setOnItemSelectedListener(this);
        kyc_hear_about_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                //Toast.makeText(getActivity(), hear_about.getSelectedItem().toString()+"", Toast.LENGTH_SHORT).show();

                socialmedia_ll.setVisibility(View.GONE);
                print_advertisement_ll.setVisibility(View.GONE);


                if (position == 0) {


                } else if (position == 1) {
                    hear_aboutt = kyc_hear_about_spiner.getSelectedItem().toString();
                    socialmedia_ll.setVisibility(View.VISIBLE);

                } else if (position == 2) {
                    print_advertisement_ll.setVisibility(View.VISIBLE);
                    hear_aboutt = kyc_hear_about_spiner.getSelectedItem().toString();
                } else if (position == 3) {

                    print_advertisement_ll.setVisibility(View.VISIBLE);
                    textviewhint.setText("Channel Partners");
                    kyc_print_advertisement_et.setHint("Who? Which Organization?");
                    hear_aboutt = kyc_hear_about_spiner.getSelectedItem().toString();
                } else if (position == 4) {

                    print_advertisement_ll.setVisibility(View.VISIBLE);
                    textviewhint.setText("Friends or Colleagues");
                    kyc_print_advertisement_et.setHint("Who?");

                    hear_aboutt = kyc_hear_about_spiner.getSelectedItem().toString();
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

        kyc_conacted_spinner.setAdapter(adapter);
        kyc_conacted_spinner.setOnItemSelectedListener(this);
        kyc_conacted_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    conacted = kyc_conacted_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else if (position == 2) {

                    conacted = kyc_conacted_spinner.getSelectedItem().toString();

                    //usa_visa_ly.setVisibility(View.GONE);

                } else if (position == 3) {

                    conacted = kyc_conacted_spinner.getSelectedItem().toString();

                } else if (position == 4) {

                    conacted = kyc_conacted_spinner.getSelectedItem().toString();

                } else if (position == 5) {

                    conacted = kyc_conacted_spinner.getSelectedItem().toString();
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

        kyc_subscibe_spinner.setAdapter(adapter);
        kyc_subscibe_spinner.setOnItemSelectedListener(this);
        kyc_subscibe_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    subscibe = kyc_subscibe_spinner.getSelectedItem().toString();
                    //usa_visa_ly.setVisibility(View.VISIBLE);
                } else if (position == 2) {
                    subscibe = kyc_subscibe_spinner.getSelectedItem().toString();


                    //usa_visa_ly.setVisibility(View.GONE);

                }


            }

            public void onNothingSelected(AdapterView<?> parentView) {
                //  selectedyear = 0;
                //return;
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        Spinner spinner = (Spinner) adapterView;

        if (spinner.getId() == R.id.kyc_as_single_spinner)
        {
            apply_applicant = adapterView.getItemAtPosition(i).toString();
            // Toast.makeText(getContext(), apply_applicant+"", Toast.LENGTH_SHORT).show();
        } else if (spinner.getId() == R.id.kyc_employe_type_sp) {
            employe_type = adapterView.getItemAtPosition(i).toString();
           // Toast.makeText(getContext(), employe_type+"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.Kyc_uk_visa_spinner) {
           // Toast.makeText(getContext(), uk_visa+"", Toast.LENGTH_SHORT).show();

            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();

            if (i == 1) {
                uk_visa_spinner_ly.setVisibility(View.VISIBLE);
                uk_visa = adapterView.getItemAtPosition(i).toString();

            } else if (i == 2) {
                uk_visa_spinner_ly.setVisibility(View.GONE);
                uk_visa = adapterView.getItemAtPosition(i).toString();

            } else {

                uk_visa_spinner_ly.setVisibility(View.GONE);

            }
        }


        else if (spinner.getId() == R.id.kyc_european_visa_spinner) {
           // european_visa = adapterView.getItemAtPosition(i).toString();
          //  Toast.makeText(getContext(), european_visa+"", Toast.LENGTH_SHORT).show();

            if (i == 1) {
                european_visa_ly.setVisibility(View.VISIBLE);
                european_visa = adapterView.getItemAtPosition(i).toString();
            } else if
            (i == 2) {
                european_visa = adapterView.getItemAtPosition(i).toString();
                european_visa_ly.setVisibility(View.GONE);
            } else {

                european_visa_ly.setVisibility(View.GONE);

            }

            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.kyc_usa_visa_spinner) {
            usa_visa = adapterView.getItemAtPosition(i).toString();
           // Toast.makeText(getContext(), usa_visa+"", Toast.LENGTH_SHORT).show();


            if (i == 1) {
                usa_visa_ly.setVisibility(View.VISIBLE);
                usa_visa =adapterView.getItemAtPosition(i).toString();

            } else if (i == 2) {
                usa_visa =adapterView.getItemAtPosition(i).toString();

                usa_visa_ly.setVisibility(View.GONE);
            } else {


                usa_visa_ly.setVisibility(View.GONE);

            }

            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }


        else if (spinner.getId() == R.id.kyc_intersted_type_spinner) {
            intersted_type = adapterView.getItemAtPosition(i).toString();
           // Toast.makeText(getContext(), intersted_type+"", Toast.LENGTH_SHORT).show();

            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.kyc_hear_about_spiner) {
          //  Toast.makeText(getContext(), hear_aboutt+"", Toast.LENGTH_SHORT).show();


            socialmedia_ll.setVisibility(View.GONE);
            print_advertisement_ll.setVisibility(View.GONE);

            if (i == 0) {


            } else if (i == 1) {
                hear_aboutt = adapterView.getItemAtPosition(i).toString();
                socialmedia_ll.setVisibility(View.VISIBLE);

            } else if (i == 2) {
                print_advertisement_ll.setVisibility(View.VISIBLE);
                hear_aboutt = adapterView.getItemAtPosition(i).toString();

            } else if (i == 3) {

                print_advertisement_ll.setVisibility(View.VISIBLE);
                textviewhint.setText("Channel Partners");
                kyc_print_advertisement_et.setHint("Who? Which Organization?");
                hear_aboutt = adapterView.getItemAtPosition(i).toString();

            } else if (i == 4) {

                print_advertisement_ll.setVisibility(View.VISIBLE);
                textviewhint.setText("Friends or Colleagues");
                kyc_print_advertisement_et.setHint("Who?");

                hear_aboutt = adapterView.getItemAtPosition(i).toString();

            }


            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.kyc_conacted_spinner) {

          //  Toast.makeText(getContext(), conacted+"", Toast.LENGTH_SHORT).show();
            if (i == 1) {
                conacted = adapterView.getItemAtPosition(i).toString();

                //usa_visa_ly.setVisibility(View.VISIBLE);
            } else if (i == 2) {

                conacted = adapterView.getItemAtPosition(i).toString();

                //usa_visa_ly.setVisibility(View.GONE);

            } else if (i == 3) {

                conacted = adapterView.getItemAtPosition(i).toString();

            } else if (i == 4) {

                conacted = adapterView.getItemAtPosition(i).toString();

            } else if (i == 5) {

                conacted = adapterView.getItemAtPosition(i).toString();
            }

            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }

        else if (spinner.getId() == R.id.kyc_subscibe_spinner) {
            if (i == 1) {
                subscibe = adapterView.getItemAtPosition(i).toString();
                //usa_visa_ly.setVisibility(View.VISIBLE);
            } else if (i == 2) {
                subscibe = adapterView.getItemAtPosition(i).toString();


                //usa_visa_ly.setVisibility(View.GONE);

            }
            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.kyc_Social_media_spinner) {
            if (i == 0) {
                //usa_visa_ly.setVisibility(View.VISIBLE);
            } else{

                platform = adapterView.getItemAtPosition(i).toString();


                //usa_visa_ly.setVisibility(View.GONE);

            }
            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                if (response.code() == 200) {
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

                        } else {
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
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Server ", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public  void Spinnerdata()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        kyc_as_single_spinner.setAdapter(adapter);
        kyc_as_single_spinner.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.business_salaried));
        kyc_employe_type_sp.setAdapter(adapter2);
        kyc_employe_type_sp.setOnItemSelectedListener(this);



        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));
        Kyc_uk_visa_spinner.setAdapter(adapter3);
        Kyc_uk_visa_spinner.setOnItemSelectedListener(this);



        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));

        kyc_european_visa_spinner.setAdapter(adapter4);
        kyc_european_visa_spinner.setOnItemSelectedListener(this);



        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));
        kyc_usa_visa_spinner.setAdapter(adapter5);
        kyc_usa_visa_spinner.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.residency_passport));
        kyc_intersted_type_spinner.setAdapter(adapter6);
        kyc_intersted_type_spinner.setOnItemSelectedListener(this);



        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.hear_about));
        kyc_hear_about_spiner.setAdapter(adapter7);
        kyc_hear_about_spiner.setOnItemSelectedListener(this);



        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.conacted_spinner));
        kyc_conacted_spinner.setAdapter(adapter8);
        kyc_conacted_spinner.setOnItemSelectedListener(this);




        ArrayAdapter<String> adapter9= new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.yes_no));
        kyc_subscibe_spinner.setAdapter(adapter9);
        kyc_subscibe_spinner.setOnItemSelectedListener(this);




        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.social_media));

        kyc_Social_media_spinner.setAdapter(adapter10);
        kyc_Social_media_spinner.setOnItemSelectedListener(this);
    }


}