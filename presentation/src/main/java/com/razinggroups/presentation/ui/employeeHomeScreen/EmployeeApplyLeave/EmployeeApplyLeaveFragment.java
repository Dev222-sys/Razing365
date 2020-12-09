package com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeApplyLeave;

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
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.domain.model.leave.CreateLeaveRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

public class EmployeeApplyLeaveFragment extends BaseFragment<EmployeeApplyLeaveViewModel> implements EmployeeApplyLeaveNavigator {
    @Inject
    @Named("EmployeeApplyLeaveFragment")
    ViewModelProvider.Factory viewModelFactory;

    EmployeeApplyLeaveViewModel viewModel;
    long empId;


    ProgressBar progressBar;

    TextView titleTv, descTv;
    EditText startDateEt, endDateEt;
    LinearLayout startDateLayout, endDateLayout;
    Button btn;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeeApplyLeaveViewModel.class);
        viewModel.setNavigator(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_apply_leave, container, false);
        progressBar = view.findViewById(R.id.fragment_employee_apply_leave_pb);
        titleTv = view.findViewById(R.id.fragment_employee_apply_leave_title);
        descTv = view.findViewById(R.id.fragment_employee_apply_leave_desc);
        startDateEt = view.findViewById(R.id.fragment_employee_apply_leave_start_date_et);
        endDateEt = view.findViewById(R.id.fragment_employee_apply_leave_end_date_et);
        startDateLayout = view.findViewById(R.id.fragment_employee_apply_leave_start_date);
        endDateLayout = view.findViewById(R.id.fragment_employee_apply_leave_end_date);
        btn = view.findViewById(R.id.fragment_employee_apply_leave_btn);


        empId = Long.parseLong(getArguments().getString("employeeId"));

        progressBar.setVisibility(View.GONE);


        DatePickerDialog.OnDateSetListener datepickerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                hideKeyboard(getActivity());
                startDateEt.setText(sdf.format(myCalendar.getTime()));
            }

        };

        DatePickerDialog.OnDateSetListener datepickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                hideKeyboard(getActivity());
                endDateEt.setText(sdf.format(myCalendar.getTime()));
            }

        };

        startDateEt.setInputType(InputType.TYPE_NULL);
        endDateEt.setInputType(InputType.TYPE_NULL);
        startDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerStart);
            }
        });
        startDateEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    openDatePickerDialog(datepickerStart);

            }
        });
        startDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(datepickerStart);

            }
        });
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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Start Date to Assign", Toast.LENGTH_SHORT).show();
                } else if (endDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter End Date to Assign", Toast.LENGTH_SHORT).show();
                } else if (titleTv.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter title to Assign", Toast.LENGTH_SHORT).show();
                } else if (descTv.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter leave description to Assign", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    com.razinggroups.domain.model.leave.CreateLeaveRequest createLeaveRequest = new CreateLeaveRequest("" + empId, titleTv.getText().toString(), startDateEt.getText().toString(),
                            endDateEt.getText().toString(), descTv.getText().toString());
                    viewModel.postData(createLeaveRequest);
                }
            }
        });
        return view;
    }

    void openDatePickerDialog(DatePickerDialog.OnDateSetListener datepicker) {
        hideKeyboard(getActivity());
        new DatePickerDialog(getContext(), datepicker, myCalendar
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
    public EmployeeApplyLeaveViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onDataPosted(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                clearAllViews();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void clearAllViews() {
        titleTv.setText("");
        descTv.setText("");
        startDateEt.setText("");
        endDateEt.setText("");
    }
}

