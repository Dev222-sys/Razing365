package com.razinggroups.presentation.ui.vendor.manipulateVendor;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
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
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.vendor.FetchSingleVendorResposne;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class ManipulateVendorActivity extends BaseActivity<ManipulateVendorViewModel> implements ManipulateVendorNavigator {

    @Inject
    @Named("ManipulateVendorActivity")
    ViewModelProvider.Factory viewModelFactory;

    ManipulateVendorViewModel viewModel;


    TextView title;
    ProgressBar progressBar;
    Button submitBtn, deleteBtn;
    ImageView crossBtn;
    EditText firstNameEt, middleNameEt, lastNameEt, servicesEt, mobile, street, country, city, postalCode, emailAdd, gstEt;


    String mode = "";
    String vendorId = "";

    Spinner brandSpinner;
    List<ItemData> spinnerDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ManipulateVendorViewModel.class);
        viewModel.setNavigator(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_manipulate_vendor);

        initViews();

        mode = getIntent().getStringExtra("mode");
        if (getIntent().getStringExtra("vendorId") != null) {
            vendorId = getIntent().getStringExtra("vendorId");
        }

        initViews();
        viewModel.fetchBrands();
    }

    private void initViews() {

        title = findViewById(R.id.activity_manipulate_vendor_title);
        progressBar = findViewById(R.id.activity_manipulate_vendor_progress_bar);

        brandSpinner = findViewById(R.id.activity_manipulate_vendor_brand_name_spinner);
        firstNameEt = findViewById(R.id.activity_manipulate_vendor_first_name_et);
        middleNameEt = findViewById(R.id.activity_manipulate_vendor_middle_name_et);
        lastNameEt = findViewById(R.id.activity_manipulate_vendor_last_name_et);
        servicesEt = findViewById(R.id.activity_manipulate_vendor_services_et);
        mobile = findViewById(R.id.activity_manipulate_vendor_mobile_et);
        street = findViewById(R.id.activity_manipulate_vendor_street_et);
        country = findViewById(R.id.activity_manipulate_vendor_country_et);
        city = findViewById(R.id.activity_manipulate_vendor_city_et);
        postalCode = findViewById(R.id.activity_manipulate_vendor_postal_et);
        emailAdd = findViewById(R.id.activity_manipulate_vendor_email_et);
        gstEt = findViewById(R.id.activity_manipulate_vendor_gst_et);

        crossBtn = findViewById(R.id.activity_manipulate_vendor_cross_btn);
        submitBtn = findViewById(R.id.activity_manipulate_vendor_submit_btn);
        deleteBtn = findViewById(R.id.activity_manipulate_vendor_delete_btn);


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
                viewModel.delete(vendorId);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (brandSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select brand to continue", Toast.LENGTH_SHORT).show();
                } else if (firstNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter first name to continue", Toast.LENGTH_SHORT).show();
                } else if (emailAdd.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter email address to continue", Toast.LENGTH_SHORT).show();
                } else if (mobile.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter mobile no to continue", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    if (mode.equalsIgnoreCase("add"))
                        viewModel.manipulate(createRequestWithoutId(), "create");
                    else if (mode.equalsIgnoreCase("edit"))
                        viewModel.manipulate(createRequestWithId(), "update");
                }

            }
        });
    }

    @Override
    public void onBrandLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {


        spinnerDataList.add(new ItemData("Select Brand", "0"));
        for (FetchAllBrandDetailsRecordResponse recordResponse : fetchAllBrandDetailsResponse.getRecords()) {
            ItemData itemData = new ItemData(recordResponse.getBrandName(), recordResponse.getId());
            spinnerDataList.add(itemData);
        }
        brandSpinner.setAdapter(new SpinnerAdapter(this, R.id.txt, spinnerDataList));


        if (mode.equalsIgnoreCase("add")) {
            title.setText("ADD VENDOR");
            deleteBtn.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } else if (mode.equalsIgnoreCase("edit")) {
            title.setText("EDIT VENDOR");
            viewModel.fetchVendor(Long.parseLong(vendorId));
        }

    }

    private com.razinggroups.domain.model.vendor.FetchSingleVendorResposne createRequestWithId() {
        return new com.razinggroups.domain.model.vendor.FetchSingleVendorResposne(
                vendorId,
                firstNameEt.getText().toString(),
                middleNameEt.getText().toString(),
                lastNameEt.getText().toString(),
                emailAdd.getText().toString(),
                mobile.getText().toString(),
                ((ItemData) brandSpinner.getSelectedItem()).getName(),
                "",
                servicesEt.getText().toString(),
                gstEt.getText().toString(),
                street.getText().toString(),
                city.getText().toString(),
                postalCode.getText().toString(),
                country.getText().toString()
        );

    }

    private com.razinggroups.domain.model.vendor.FetchSingleVendorResposne createRequestWithoutId() {
        return new com.razinggroups.domain.model.vendor.FetchSingleVendorResposne(
                firstNameEt.getText().toString(),
                middleNameEt.getText().toString(),
                lastNameEt.getText().toString(),
                emailAdd.getText().toString(),
                mobile.getText().toString(),
                ((ItemData) brandSpinner.getSelectedItem()).getName(),
                "",
                servicesEt.getText().toString(),
                gstEt.getText().toString(),
                street.getText().toString(),
                city.getText().toString(),
                postalCode.getText().toString(),
                country.getText().toString()
        );


    }

    @Override
    public ManipulateVendorViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onVendorLoaded(FetchSingleVendorResposne fetchSingleVendorResposne) {
        progressBar.setVisibility(View.GONE);
        firstNameEt.setText(fetchSingleVendorResposne.getFirstName());
        middleNameEt.setText(fetchSingleVendorResposne.getMiddleName());
        lastNameEt.setText(fetchSingleVendorResposne.getLastName());
        emailAdd.setText(fetchSingleVendorResposne.getEmail());
        mobile.setText(fetchSingleVendorResposne.getContact());

        for (int i = 0; i < spinnerDataList.size(); i++) {
            if (spinnerDataList.get(i).getName() != null)
                if (spinnerDataList.get(i).getName().equalsIgnoreCase(fetchSingleVendorResposne.getVendorBrand())) {
                    brandSpinner.setSelection(i + 1);
                } else {
                    brandSpinner.setSelection(0);
                }
        }


        servicesEt.setText(fetchSingleVendorResposne.getVendorService());
        gstEt.setText(fetchSingleVendorResposne.getGstin());
        street.setText(fetchSingleVendorResposne.getStreet());
        city.setText(fetchSingleVendorResposne.getStreet());
        postalCode.setText(fetchSingleVendorResposne.getPostalCode());
        country.setText(fetchSingleVendorResposne.getCountry());

    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
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
    public void showDialog(String message) {
        progressBar.setVisibility(View.GONE);
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
}
