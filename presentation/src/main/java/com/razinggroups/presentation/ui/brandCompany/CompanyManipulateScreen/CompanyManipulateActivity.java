package com.razinggroups.presentation.ui.brandCompany.CompanyManipulateScreen;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseActivity;
import com.razinggroups.presentation.ui.myTask.createMyTask.ItemData;
import com.razinggroups.presentation.ui.myTask.createMyTask.SpinnerAdapter;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.company.CreateCompanyRequest;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class CompanyManipulateActivity extends BaseActivity<CompanyManipulateViewModel> implements CompanyManipulateNavigator {

    @Inject
    @Named("CompanyManipulateActivity")
    ViewModelProvider.Factory viewModelFactory;

    CompanyManipulateViewModel viewModel;

    TextView title;
    ProgressBar progressBar;
    Button submitBtn, deleteBtn;
    ImageView crossBtn;
    EditText companyEt, gstNumberEt, tanNumberId, billingAddress, mobile, headOfficeAddress, street, country, city, postalCode, websiteLink, emailAdd, billingAddressLocation;


    String mode = "";
    String companyId = "";

    Spinner gstLocationSpinner;
    List<ItemData> spinnerDataList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CompanyManipulateViewModel.class);
        viewModel.setNavigator(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_company_manipulate);


        initViews();


        mode = getIntent().getStringExtra("mode");
        if (getIntent().getStringExtra("companyId") != null) {
            companyId = getIntent().getStringExtra("companyId");
        }

        initViews();
        initSpinner();
        if (mode.equalsIgnoreCase("add")) {
            title.setText("ADD COMPANY");
            deleteBtn.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } else if (mode.equalsIgnoreCase("edit")) {
            title.setText("EDIT COMPANY");
            viewModel.fetchData();
        }
    }

    private void initSpinner() {

        spinnerDataList.add(new ItemData("Select Gst Location", "0"));

        List<String> gstLocations = new ArrayList<>();
        gstLocations.add("Andhra Pradesh");
        gstLocations.add("Arunachal Pradesh");
        gstLocations.add("Assam");
        gstLocations.add("Bihar");
        gstLocations.add("Chhattisgarh");
        gstLocations.add("Goa");
        gstLocations.add("Gujarat");
        gstLocations.add("Haryana");
        gstLocations.add("Himachal Pradesh");
        gstLocations.add("Jammu & Kashmir");
        gstLocations.add("Jharkhand");
        gstLocations.add("Karnataka");
        gstLocations.add("Kerala");
        gstLocations.add("Madhya Pradesh");
        gstLocations.add("Maharashtra");
        gstLocations.add("Manipur");
        gstLocations.add("Meghalaya");
        gstLocations.add("Mizoram");
        gstLocations.add("Nagaland");
        gstLocations.add("Odisha");
        gstLocations.add("Punjab");
        gstLocations.add("Rajasthan");
        gstLocations.add("Sikkim");
        gstLocations.add("Tamil Nadu");
        gstLocations.add("Tripura");
        gstLocations.add("Uttarakhand");
        gstLocations.add("Uttar Pradesh");
        gstLocations.add("West Bengal");
        gstLocations.add("Andaman & Nicobar");
        gstLocations.add("Chandigarh");
        gstLocations.add("Dadra and Nagar Haveli");
        gstLocations.add("Daman & Diu");
        gstLocations.add("Delhi");
        gstLocations.add("Lakshadweep");
        gstLocations.add("Puducherry");


        for (int i = 0; i < gstLocations.size(); i++) {
            ItemData itemData = new ItemData(gstLocations.get(i), i + 1 + "");
            spinnerDataList.add(itemData);
        }

        gstLocationSpinner.setAdapter(new SpinnerAdapter(this, R.id.txt, spinnerDataList));
    }

    private void initViews() {

        title = findViewById(R.id.activity_company_manipulate_title);
        progressBar = findViewById(R.id.activity_company_manipulate_progress_bar);

        companyEt = findViewById(R.id.activity_company_manipulate_company_et);
        gstNumberEt = findViewById(R.id.activity_company_manipulate_gst_number_et);
        gstLocationSpinner = findViewById(R.id.activity_company_manipulate_gst_location_spinner);
        tanNumberId = findViewById(R.id.activity_company_manipulate_tan_number_et);
        billingAddress = findViewById(R.id.activity_company_manipulate_billing_address_et);
        headOfficeAddress = findViewById(R.id.activity_company_manipulate_head_address_et);
        mobile = findViewById(R.id.activity_company_manipulate_mobile_et);
        street = findViewById(R.id.activity_company_manipulate_street_et);
        country = findViewById(R.id.activity_company_manipulate_country_et);
        city = findViewById(R.id.activity_company_manipulate_city_et);
        postalCode = findViewById(R.id.activity_company_manipulate_postal_et);
        websiteLink = findViewById(R.id.activity_company_manipulate_link_et);
        emailAdd = findViewById(R.id.activity_company_manipulate_email_address_et);
        billingAddressLocation = findViewById(R.id.activity_company_manipulate_billing_address_location_et);

        crossBtn = findViewById(R.id.activity_company_manipulate_cross_btn);
        submitBtn = findViewById(R.id.activity_company_manipulate_submit_btn);
        deleteBtn = findViewById(R.id.activity_company_manipulate_delete_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(companyEt.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter company name to Continue", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    if (mode.equalsIgnoreCase("add"))
                        viewModel.manipulate(createRequestWithoutId(), "create");
                    else if (mode.equalsIgnoreCase("edit"))
                        viewModel.manipulate(createRequestWithId(), "update");
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.delete(companyId);
            }
        });
        crossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private com.razinggroups.domain.model.company.CreateCompanyRequest createRequestWithoutId() {
        return new com.razinggroups.domain.model.company.CreateCompanyRequest(

                companyEt.getText().toString(),
                gstNumberEt.getText().toString(),
                ((ItemData) gstLocationSpinner.getSelectedItem()).getName(),
                "",
                "",
                tanNumberId.getText().toString(),
                "",
                billingAddress.getText().toString(),
                billingAddressLocation.getText().toString(),
                emailAdd.getText().toString(),
                mobile.getText().toString(),
                headOfficeAddress.getText().toString(),
                street.getText().toString(),
                city.getText().toString(),
                postalCode.getText().toString(),
                country.getText().toString(),
                websiteLink.getText().toString()
        );

    }

    private com.razinggroups.domain.model.company.CreateCompanyRequest createRequestWithId() {

        return new CreateCompanyRequest(
                companyId,
                companyEt.getText().toString(),
                gstNumberEt.getText().toString(),
                ((ItemData) gstLocationSpinner.getSelectedItem()).getName(),
                "",
                "",
                tanNumberId.getText().toString(),
                "",
                billingAddress.getText().toString(),
                billingAddressLocation.getText().toString(),
                emailAdd.getText().toString(),
                mobile.getText().toString(),
                headOfficeAddress.getText().toString(),
                street.getText().toString(),
                city.getText().toString(),
                postalCode.getText().toString(),
                country.getText().toString(),
                websiteLink.getText().toString()
        );
    }


    @Override
    public CompanyManipulateViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onUpdate(Message message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message.getMessage()).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onDataloaded(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse) {

        com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse recordResponse = new com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse();
        for (FetchAllCompanyDetailsRecordResponse recordResponse1 : fetchAllCompanyDetailsResponse.getRecords()) {
            if (companyId.equalsIgnoreCase(recordResponse1.getId())) {
                recordResponse = recordResponse1;
            }
        }

        progressBar.setVisibility(View.GONE);

        companyEt.setText(recordResponse.getCompanyName());
        gstNumberEt.setText(recordResponse.getGstNumber());
        tanNumberId.setText(recordResponse.getTanNumber());
        billingAddress.setText(recordResponse.getBillingAddress());
        headOfficeAddress.setText(recordResponse.getHeadOfficeAddress());
        mobile.setText(recordResponse.getMobile());
        street.setText(recordResponse.getStreet());
        country.setText(recordResponse.getCountry());
        city.setText(recordResponse.getCity());
        postalCode.setText(recordResponse.getPostalCode());
        websiteLink.setText(recordResponse.getWebsiteLink());
        emailAdd.setText(recordResponse.getEmailAddress());
        billingAddressLocation.setText(recordResponse.getBillingAddressLocation());

        gstLocationSpinner.setSelection(0);
        for (int i = 0; i < spinnerDataList.size(); i++) {
            if (spinnerDataList.get(i).getName().equalsIgnoreCase(recordResponse.getGstLocation())) {
                gstLocationSpinner.setSelection(i);
            }
        }

    }
}
