package com.razinggroups.presentation.ui.vendor.createVendorTask;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseActivity;
import com.razinggroups.presentation.ui.myTask.createMyTask.ItemData;
import com.razinggroups.presentation.ui.myTask.createMyTask.SpinnerAdapter;
import com.razinggroups.domain.model.vendor.FetchAllVendorBrandRecordResponse;
import com.razinggroups.domain.model.vendor.FetchAllVendorBrandResponse;
import com.razinggroups.domain.model.vendor.FetchVendorServiceRecordResponse;
import com.razinggroups.domain.model.vendor.FetchVendorServiceResponse;
import com.razinggroups.domain.model.vendorTask.CreateVendorTask;
import com.razinggroups.domain.utils.Const;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

public class CreateVendorTaskActivity extends BaseActivity<CreateVendorTaskViewModel> implements CreateVendorTaskNavigator {

    @Inject
    @Named("CreateVendorTaskActivity")
    ViewModelProvider.Factory viewModelFactory;

    CreateVendorTaskViewModel viewModel;


    ImageView backBtn;
    EditText taskDetail;
    EditText endDateEt;
    LinearLayout endDateLayout;
    Button btn;
    RelativeLayout layout;


    ProgressBar progressBar;
    Calendar myCalendar = Calendar.getInstance();

    Spinner brandSpinner, serviceSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplicationContext().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateVendorTaskViewModel.class);
        viewModel.setNavigator(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_create_vendor_task);
        initViews();
        viewModel.fetchData();
    }

    private void initViews() {

        layout = findViewById(R.id.activity_create_task_vendor_layout);
        backBtn = findViewById(R.id.activitycreate_vendor_task_cross_btn);
        taskDetail = findViewById(R.id.activity_create_task_vendor_task_detail);
        endDateEt = findViewById(R.id.activity_create_task_vendor_end_date_et);
        endDateLayout = findViewById(R.id.activity_create_task_vendor_end_date);
        btn = findViewById(R.id.activity_create_task_vendor_assign_btn);
        progressBar = findViewById(R.id.activity_create_vendor_task_progress_bar);
        brandSpinner = findViewById(R.id.activity_create_task_vendor_spinner_brand);
        serviceSpinner = findViewById(R.id.activity_create_task_vendor_spinner_service);

        layout.setVisibility(View.GONE);


        DatePickerDialog.OnDateSetListener datepickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = Const.dateFormat; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                hideKeyboard(CreateVendorTaskActivity.this);
                endDateEt.setText(sdf.format(myCalendar.getTime()));
            }
        };

        endDateEt.setInputType(InputType.TYPE_NULL);
        endDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerEnd);
            }
        });
        endDateEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    openDatePickerDialog(datepickerEnd);
            }
        });
        endDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerEnd);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brandSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select Brand to Assign", Toast.LENGTH_SHORT).show();
                } else if (serviceSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select service to Assign", Toast.LENGTH_SHORT).show();
                } else if (taskDetail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter task detail to Assign", Toast.LENGTH_SHORT).show();
                } else {
                    com.razinggroups.domain.model.vendorTask.CreateVendorTask createVendorTask = new CreateVendorTask(
                            ((ItemData) brandSpinner.getSelectedItem()).getName(),
                            ((ItemData) serviceSpinner.getSelectedItem()).getName(),
                            endDateEt.getText().toString(),
                            taskDetail.getText().toString(),
                            "n/a"
                    );
                    progressBar.setVisibility(View.VISIBLE);
                    viewModel.createTask(createVendorTask);
                }

            }
        });
        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String brandid = ((ItemData) brandSpinner.getSelectedItem()).getId();
                    progressBar.setVisibility(View.VISIBLE);
                    viewModel.fetchVendorService(Long.parseLong(brandid));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void openDatePickerDialog(DatePickerDialog.OnDateSetListener datepickerEnd) {
        hideKeyboard(this);
        new DatePickerDialog(this, datepickerEnd, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public CreateVendorTaskViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void showDailog(String message) {
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
    public void onBrandsLoaded(FetchAllVendorBrandResponse fetchAllVendorBrandResponse) {
        layout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        List<ItemData> list = new ArrayList<>();
        list.add(new ItemData("Select Brand", "0"));
        for (FetchAllVendorBrandRecordResponse recordResponse : fetchAllVendorBrandResponse.getRecords()) {
            ItemData itemData = new ItemData(recordResponse.getVendorBrand(), recordResponse.getId());
            list.add(itemData);
        }
        brandSpinner.setAdapter(new SpinnerAdapter(this, R.id.txt, list));
    }

    @Override
    public void onServiceLoaded(FetchVendorServiceResponse fetchVendorServiceResponse) {
        progressBar.setVisibility(View.GONE);
        List<ItemData> list = new ArrayList<>();
        list.add(new ItemData("Select Service", "0"));
        for (FetchVendorServiceRecordResponse recordResponse : fetchVendorServiceResponse.getRecords()) {
            ItemData itemData = new ItemData(recordResponse.getService(), "1");
            list.add(itemData);
        }
        serviceSpinner.setAdapter(new SpinnerAdapter(this, R.id.txt, list));
    }
}
