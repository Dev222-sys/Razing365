package com.razinggroups.presentation.ui.brandCompany.BrandManipulateScreen;

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
import com.razinggroups.domain.model.brand.CreateBrandRequest;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class BrandManipulateActivity extends BaseActivity<BrandManipulateViewModel> implements BrandManipulateNavigator {

    @Inject
    @Named("BrandManipulateActivity")
    ViewModelProvider.Factory viewModelFactory;

    BrandManipulateViewModel viewModel;

    TextView title;
    ProgressBar progressBar;
    Button submitBtn, deleteBtn;
    ImageView crossBtn;
    EditText brandEt, divisonNameEt, divisonEmailEt, divisionContactEt, mobile, street, country, city, postalCode, emailAdd;


    String mode = "";
    String brandId = "";


    List<ItemData> companyDataList = new ArrayList<>();
    Spinner companySpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BrandManipulateViewModel.class);
        viewModel.setNavigator(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_brand_manipulate);

        mode = getIntent().getStringExtra("mode");
        if (getIntent().getStringExtra("brandId") != null) {
            brandId = getIntent().getStringExtra("brandId");
        }

        initViews();
        viewModel.fetchCompany();


    }

    private void initViews() {


        title = findViewById(R.id.activity_brand_manipulate_title);
        progressBar = findViewById(R.id.activity_brand_manipulate_progress_bar);

        companySpinner = findViewById(R.id.activity_manipulate_company_name_spinner);
        brandEt = findViewById(R.id.activity_brand_manipulate_brand_et);
        divisonNameEt = findViewById(R.id.activity_brand_manipulate_division_name_et);
        divisonEmailEt = findViewById(R.id.activity_brand_manipulate_division_email_et);
        divisionContactEt = findViewById(R.id.activity_brand_manipulate_division_contact_et);
        mobile = findViewById(R.id.activity_brand_manipulate_brand_mobile_et);
        street = findViewById(R.id.activity_brand_manipulate_street_et);
        country = findViewById(R.id.activity_brand_manipulate_country_et);
        city = findViewById(R.id.activity_brand_manipulate_city_et);
        postalCode = findViewById(R.id.activity_brand_manipulate_postal_et);
        emailAdd = findViewById(R.id.activity_brand_manipulate_brand_email_et);

        crossBtn = findViewById(R.id.activity_brand_manipulate_cross_btn);
        submitBtn = findViewById(R.id.activity_brand_manipulate_submit_btn);
        deleteBtn = findViewById(R.id.activity_brand_manipulate_delete_btn);


        crossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.delete(brandId);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (companySpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Select company to Continue", Toast.LENGTH_SHORT).show();
                }
//                else if (mobile.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please Enter mobile no to Continue", Toast.LENGTH_SHORT).show();
//                }
                else if (emailAdd.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter email address to Continue", Toast.LENGTH_SHORT).show();
                } else if (brandEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter brand name to Continue", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    if (mode.equalsIgnoreCase("add")) {
                        viewModel.manipulate(createRequestWithoutId(), "create");
                    } else if (mode.equalsIgnoreCase("edit")) {
                        viewModel.manipulate(createRequestWithId(), "update");
                    }
                }
            }
        });

    }

    private com.razinggroups.domain.model.brand.CreateBrandRequest createRequestWithId() {
        return new com.razinggroups.domain.model.brand.CreateBrandRequest(
                brandId,
                brandEt.getText().toString(),
                ((ItemData) companySpinner.getSelectedItem()).getName(),
                divisonNameEt.getText().toString(),
                divisonEmailEt.getText().toString(),
                divisionContactEt.getText().toString(),
                emailAdd.getText().toString(),
                mobile.getText().toString(),
                street.getText().toString(),
                city.getText().toString(),
                postalCode.getText().toString(),
                country.getText().toString()
        );

    }

    private com.razinggroups.domain.model.brand.CreateBrandRequest createRequestWithoutId() {
        return new CreateBrandRequest(
                brandEt.getText().toString(),
                ((ItemData) companySpinner.getSelectedItem()).getName(),
                divisonNameEt.getText().toString(),
                divisonEmailEt.getText().toString(),
                divisionContactEt.getText().toString(),
                emailAdd.getText().toString(),
                mobile.getText().toString(),
                street.getText().toString(),
                city.getText().toString(),
                postalCode.getText().toString(),
                country.getText().toString()
        );
    }


    @Override
    public BrandManipulateViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onDataloaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {

        progressBar.setVisibility(View.GONE);
        com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse recordResponse = new com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse();
        for (FetchAllBrandDetailsRecordResponse recordResponse1 : fetchAllBrandDetailsResponse.getRecords()) {
            if (recordResponse1.getId().equalsIgnoreCase(brandId)) {
                recordResponse = recordResponse1;
            }
        }
        companySpinner.setSelection(0);
        for (int i = 0; i < companyDataList.size(); i++) {
            if (companyDataList.get(i).getName().equalsIgnoreCase(recordResponse.getBrandCompany())) {
                companySpinner.setSelection(i);
            }
        }

        brandEt.setText(recordResponse.getBrandName());
        divisonNameEt.setText(recordResponse.getBrandDivision());
        divisonEmailEt.setText(recordResponse.getDivisionMail());
        divisionContactEt.setText(recordResponse.getDivisionMobile());
        mobile.setText(recordResponse.getBrandMobile());
        country.setText(recordResponse.getBrandCountry());
        street.setText(recordResponse.getBrandStreet());
        city.setText(recordResponse.getBrandCity());
        postalCode.setText(recordResponse.getBrandCode());
        emailAdd.setText(recordResponse.getBrandEmail());

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
    public void onCompanyloaded(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse) {


        companyDataList.add(new ItemData("Select Company", "0"));
        for (FetchAllCompanyDetailsRecordResponse recordResponse : fetchAllCompanyDetailsResponse.getRecords()) {
            ItemData itemData = new ItemData(recordResponse.getCompanyName(), recordResponse.getId());
            companyDataList.add(itemData);
        }
        companySpinner.setAdapter(new SpinnerAdapter(this, R.id.txt, companyDataList));


        if (mode.equalsIgnoreCase("add")) {
            title.setText("ADD BRAND");
            deleteBtn.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } else if (mode.equalsIgnoreCase("edit")) {
            title.setText("EDIT BRAND");
            viewModel.fetchData();
        }
    }
}
