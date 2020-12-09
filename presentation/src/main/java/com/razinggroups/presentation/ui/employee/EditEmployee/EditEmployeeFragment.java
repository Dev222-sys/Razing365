package com.razinggroups.presentation.ui.employee.EditEmployee;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.myTask.createMyTask.ItemData;
import com.razinggroups.presentation.ui.myTask.createMyTask.SpinnerAdapter;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.domain.utils.Const;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

public class EditEmployeeFragment extends BaseFragment<EditEmployeeViewModel> implements EditEmployeeNavigator {

    @Inject
    @Named("EditEmployeeFragment")
    ViewModelProvider.Factory viewModelFactory;

    EditEmployeeViewModel viewModel;

    String empId = "";

    ProgressBar progressBar;
    com.razinggroups.domain.model.employee.EmployeeDetail record = new com.razinggroups.domain.model.employee.EmployeeDetail();

    EditText nameEt, designationEt, officelandlineEt, offcialEmailId, personalEmailId, mobile, location, officeAddress, residenceAddress, salary, emergencyContactName, EmergencyContactNo, dateOfBirth;
    EditText dateOfJoining, aadharNo, panNo;
    LinearLayout dobLayout, dojlayout;

    Button submitBtn, deleteBtn;
    Calendar myCalendar = Calendar.getInstance();

    Spinner statusSpinner, brandSpinner;
    List<ItemData> spinnerDataList = new ArrayList<>();


    @Override
    public EditEmployeeViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EditEmployeeViewModel.class);
        viewModel.setNavigator(this);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_employee, container, false);

        empId = getArguments().getString("employeeId");
        initViews(view);
        viewModel.fetchBrand();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.updateEmployee(createRequest());
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteEmployee(empId);
            }
        });

        DatePickerDialog.OnDateSetListener datepickerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = Const.dateFormat; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                hideKeyboard(getActivity());
                dateOfBirth.setText(sdf.format(myCalendar.getTime()));
            }

        };

        DatePickerDialog.OnDateSetListener datepickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = Const.dateFormat; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                hideKeyboard(getActivity());
                dateOfJoining.setText(sdf.format(myCalendar.getTime()));
            }

        };
        dateOfBirth.setInputType(InputType.TYPE_NULL);
        dateOfJoining.setInputType(InputType.TYPE_NULL);
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerStart);
            }
        });
        dateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    openDatePickerDialog(datepickerStart);

            }
        });
        dobLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerStart);

            }
        });
        dateOfJoining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerEnd);
            }
        });
        dateOfJoining.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    openDatePickerDialog(datepickerEnd);
            }
        });

        dojlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerEnd);
            }
        });


        return view;
    }

    private com.razinggroups.domain.model.employee.EmployeeDetail createRequest() {
        return new com.razinggroups.domain.model.employee.EmployeeDetail(
                empId,
                location.getText().toString(),
                nameEt.getText().toString(),
                designationEt.getText().toString(),
                officelandlineEt.getText().toString(),
                offcialEmailId.getText().toString(),
                personalEmailId.getText().toString(),
                mobile.getText().toString(),
                record.getDol(),
                record.getAppointmentLetter(),
                officeAddress.getText().toString(),
                residenceAddress.getText().toString(),
                salary.getText().toString(),
                emergencyContactName.getText().toString(),
                EmergencyContactNo.getText().toString(),
                dateOfBirth.getText().toString(),
                dateOfJoining.getText().toString(),
                aadharNo.getText().toString(),
                record.getAadharCard(),
                panNo.getText().toString(),
                record.getPanCard(),
                record.getEducation(),
                record.getProfileImg(),
                ((ItemData) statusSpinner.getSelectedItem()).getId(),
                ((ItemData) brandSpinner.getSelectedItem()).getName(),
                record.getBrandId()
        );

    }

    private void initViews(View view) {

        progressBar = view.findViewById(R.id.fragment_employee_detail_progress_bar);
        nameEt = view.findViewById(R.id.fragment_employee_detail_name_et);
        designationEt = view.findViewById(R.id.fragment_employee_detail_designationet);
        officelandlineEt = view.findViewById(R.id.fragment_employee_detail_officelandet);
        offcialEmailId = view.findViewById(R.id.fragment_employee_detail_officialemailidet);
        personalEmailId = view.findViewById(R.id.fragment_employee_detail_personalemailidet);
        mobile = view.findViewById(R.id.fragment_employee_detail_mobileet);
        location = view.findViewById(R.id.fragment_employee_detail_Locationet);
        officeAddress = view.findViewById(R.id.fragment_employee_detail_officeAddresset);
        residenceAddress = view.findViewById(R.id.fragment_employee_detail_residenceAddresset);
        salary = view.findViewById(R.id.fragment_employee_detail_salaryet);
        emergencyContactName = view.findViewById(R.id.fragment_employee_detail_emergencycontactet);
        EmergencyContactNo = view.findViewById(R.id.fragment_employee_detail_emergencyconatctet);
        dateOfBirth = view.findViewById(R.id.fragment_employee_detail_dobet);
        dateOfJoining = view.findViewById(R.id.fragment_employee_detail_dojet);
        aadharNo = view.findViewById(R.id.fragment_aadharet);
        panNo = view.findViewById(R.id.fragment_pan_no_et);
        statusSpinner = view.findViewById(R.id.fragment_status_spinner);
        brandSpinner = view.findViewById(R.id.fragment_Brand_spinner);
        dobLayout = view.findViewById(R.id.fragment_employee_detail_dob_layout);
        dojlayout = view.findViewById(R.id.fragment_employee_detail_doj_layout);
        submitBtn = view.findViewById(R.id.fragment_employee_detail_submit_btn);
        deleteBtn = view.findViewById(R.id.fragment_employee_detail_delete_btn);


    }

    void openDatePickerDialog(DatePickerDialog.OnDateSetListener datepicker) {
        hideKeyboard(getActivity());
        new DatePickerDialog(getContext(), datepicker, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onError(String message) {
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

    @Override
    public void onDataLoaded(EmployeeDetail employeeDetail) {

        progressBar.setVisibility(View.GONE);
        record = employeeDetail;

        nameEt.setText(record.getName());
        designationEt.setText(record.getDesignation());
        officelandlineEt.setText(record.getLandline());
        offcialEmailId.setText(record.getOfficialMail());
        personalEmailId.setText(record.getPersonalMail());
        mobile.setText(record.getMobile());
        location.setText(record.getLocation());
        officeAddress.setText(record.getOfficeAddress());
        residenceAddress.setText(record.getResidenceAddress());
        salary.setText(record.getSalary());
        emergencyContactName.setText(record.getEmergencyName());
        EmergencyContactNo.setText(record.getEmergencyNo());
        dateOfBirth.setText(record.getDob());
        dateOfJoining.setText(record.getDoj());
        aadharNo.setText(record.getAadharNo());
        panNo.setText(record.getPanNo());


        brandSpinner.setSelection(0);
        for (int i = 0; i < spinnerDataList.size(); i++) {
            if (spinnerDataList.get(i).getName().equalsIgnoreCase(employeeDetail.getBrandName())) {
                brandSpinner.setSelection(i);
            }
        }


        if (record.getEmpStatus().equalsIgnoreCase("1")) {
            statusSpinner.setSelection(1);
        } else if (record.getEmpStatus().equalsIgnoreCase("2")) {
            statusSpinner.setSelection(2);
        }
    }

    @Override
    public void onUpdate(String message) {
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

    @Override
    public void onBrandLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
        loadStatusSpinner();
        spinnerDataList.add(new ItemData("Select Brand", "0"));
        for (FetchAllBrandDetailsRecordResponse recordResponse : fetchAllBrandDetailsResponse.getRecords()) {
            ItemData itemData = new ItemData(recordResponse.getBrandName(), recordResponse.getId());
            spinnerDataList.add(itemData);
        }
        brandSpinner.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, spinnerDataList));
        viewModel.fetchData(Long.parseLong(empId));


    }

    private void loadStatusSpinner() {
        List<ItemData> statusData = new ArrayList<>();
        statusData.add(new ItemData("Select Status", "0"));
        statusData.add(new ItemData("Active Employee", "1"));
        statusData.add(new ItemData("Ex Employee", "2"));

        statusSpinner.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, statusData));
    }
}
