package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.FamliyView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.presentation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Family_Detail extends Fragment  implements AdapterView.OnItemSelectedListener{


    EditText familydeatils_name_et, familydeatils_passport_et, familydeatils_age_et;
    Spinner familydeatils_passportcopy, familydeatils_realtion_ship;
    LinearLayout family_detailsQueryLl;
    String name, age, family_passport, passportcopy = "null", realtion_ship = "null";

    String id;
    Button Submit, Delete;

    public Family_Detail() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_family__detail, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        familydeatils_name_et = view.findViewById(R.id.familydeatils_name_et);
        familydeatils_passport_et = view.findViewById(R.id.familydeatils_passport_et);
        familydeatils_age_et = view.findViewById(R.id.familydeatils_age_et);
        familydeatils_passportcopy = view.findViewById(R.id.familydeatils_passportcopy);
        familydeatils_realtion_ship = view.findViewById(R.id.familydeatils_realtion_ship);
        family_detailsQueryLl = view.findViewById(R.id.family_detailsQueryLl);

        Submit = view.findViewById(R.id.generalFamilyQueryupdate);
        Delete = view.findViewById(R.id.generalFamilyQueryDelete);
         id = getArguments().getString("id");
        String usertype = SharedPrefManager.getInstans(getActivity()).getLogintype();
        // Toast.makeText(getContext(), usertype+"", Toast.LENGTH_SHORT).show();
        if (usertype.trim().equalsIgnoreCase("MasterAdmin")) {

            //  Toast.makeText(getContext(), "masterAdmin", Toast.LENGTH_SHORT).show();
            family_detailsQueryLl.setVisibility(View.VISIBLE);

        }
        Spinnerdata();
        getgernalinfromation();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatefamilyformdata();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletefamilyformdata();

            }
        });


    }

    public void Spinnerdata() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.PassportCopy));

        familydeatils_passportcopy.setAdapter(adapter);
        familydeatils_passportcopy.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.realtionship_item));

        familydeatils_realtion_ship.setAdapter(adapter2);
        familydeatils_realtion_ship.setOnItemSelectedListener(this);
    }

    public void getgernalinfromation() {

       // Toast.makeText(getContext(), id + "", Toast.LENGTH_SHORT).show();
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().familyparticulardetils(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //  Log.d("datadata", response.toString());
            //    Toast.makeText(getActivity(), response.code() + "", Toast.LENGTH_SHORT).show();

                String s = null;


                if (response.code() == 200) {
                    try {

                        s = response.body().string();

                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("records");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            familydeatils_name_et.setText(jsonObject1.getString("full_name"));
                            familydeatils_passport_et.setText(jsonObject1.getString("passport_no"));
                            familydeatils_age_et.setText(jsonObject1.getString("age"));

                            if (jsonObject1.getString("relation").equals("Father")) {
                                familydeatils_realtion_ship.setSelection(1);
                                realtion_ship = jsonObject1.getString("relation");
                            } else if (jsonObject1.getString("relation").equals("Mother")) {
                                familydeatils_realtion_ship.setSelection(2);
                                realtion_ship = jsonObject1.getString("relation");

                            } else if (jsonObject1.getString("relation").equals("Son")) {
                                familydeatils_realtion_ship.setSelection(3);
                                realtion_ship = jsonObject1.getString("relation");

                            } else if (jsonObject1.getString("relation").equals("Daughter")) {
                                familydeatils_realtion_ship.setSelection(4);
                                realtion_ship = jsonObject1.getString("relation");

                            } else if (jsonObject1.getString("relation").equals("Husband")) {
                                familydeatils_realtion_ship.setSelection(5);
                                realtion_ship = jsonObject1.getString("relation");

                            } else if (jsonObject1.getString("relation").equals("Wife")) {
                                familydeatils_realtion_ship.setSelection(6);
                                realtion_ship = jsonObject1.getString("relation");

                            } else if (jsonObject1.getString("relation").equals("Brother")) {
                                familydeatils_realtion_ship.setSelection(7);
                                realtion_ship = jsonObject1.getString("relation");

                            } else if (jsonObject1.getString("relation").equals("Sister")) {
                                familydeatils_realtion_ship.setSelection(8);
                                realtion_ship = jsonObject1.getString("relation");

                            }

                            if (jsonObject1.getString("passport_copy").equals("Received")) {
                                familydeatils_passportcopy.setSelection(1);
                                passportcopy = jsonObject1.getString("passport_copy");
                            } else if (jsonObject1.getString("passport_copy").equals("Not Received")) {
                                familydeatils_passportcopy.setSelection(2);
                                passportcopy = jsonObject1.getString("passport_copy");

                            }

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

    public void updatefamilyformdata() {
        realtion_ship();
        passportcopy();

        name = familydeatils_name_et.getText().toString();
        family_passport = familydeatils_passport_et.getText().toString();
        age = familydeatils_age_et.getText().toString();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().updateQueryFamily(id,realtion_ship,name,family_passport,age,passportcopy);
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
    public void deletefamilyformdata() {

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().deleteQueryFamily(id);
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




    private void realtion_ship() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.realtionship_item));

        familydeatils_realtion_ship.setAdapter(adapter);
        familydeatils_realtion_ship.setOnItemSelectedListener(this);
        familydeatils_realtion_ship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {


                } else {


                    realtion_ship = familydeatils_realtion_ship.getSelectedItem().toString();
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

        familydeatils_passportcopy.setAdapter(adapter);
        familydeatils_passportcopy.setOnItemSelectedListener(this);
        familydeatils_passportcopy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {

                    passportcopy = familydeatils_passportcopy.getSelectedItem().toString();


                } else if (position == 2) {

                    passportcopy = familydeatils_passportcopy.getSelectedItem().toString();

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


        if (spinner.getId() == R.id.familydeatils_realtion_ship)
        {
            realtion_ship = adapterView.getItemAtPosition(i).toString();
         //   Toast.makeText(getContext(), realtion_ship+"", Toast.LENGTH_SHORT).show();
        } else if (spinner.getId() == R.id.familydeatils_passportcopy) {
            passportcopy = adapterView.getItemAtPosition(i).toString();
         //   Toast.makeText(getContext(), passportcopy+"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, School_type+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}