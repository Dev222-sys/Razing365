package com.razinggroups.presentation.ui.myTask.createMyTask;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.Toast;

import com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.myTask.CreateMyTaskRequest;
import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.presentation.ui.main.CreateTaskHomeActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

import static com.razinggroups.domain.utils.Const.dateFormat;

public class CreateMyTaskFragment extends BaseFragment<CreateMyTaskViewModel> implements CreateMyTaskNavigator {

    @Inject
    @Named("CreateMyTaskFragment")
    ViewModelProvider.Factory viewModelFactory;

    CreateMyTaskViewModel viewModel;


    private String userType = "";

    EditText taskName, taskDetail;
    EditText endDateEt;
    LinearLayout endDateLayout;
    Button btn;

    ProgressBar progressBar;

    Spinner spinner;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateMyTaskViewModel.class);
        viewModel.setNavigator(this);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALENDAR}, 0);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.GET_ACCOUNTS}, 1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_my_task, container, false);

        taskName = view.findViewById(R.id.fragment_create_task_task_name);
        taskDetail = view.findViewById(R.id.fragment_create_task_task_detail);
        endDateEt = view.findViewById(R.id.fragment_create_task_end_date_et);
        endDateLayout = view.findViewById(R.id.fragment_create_task_end_date);
        btn = view.findViewById(R.id.fragment_create_task_assign_btn);
        progressBar = view.findViewById(R.id.fragment_create_task_pb);
        spinner = view.findViewById(R.id.fragment_create_task_spinner_employee);


        userType = getArguments().getString("userType");

        DatePickerDialog.OnDateSetListener datepickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = dateFormat; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                hideKeyboard(getActivity());
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

        progressBar.setVisibility(View.VISIBLE);


        if (userType.equalsIgnoreCase("EmployeeMyTask")) {
            progressBar.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
        } else {
            viewModel.fetchBrand();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getContext(), "Please Enter Brand to Assign", Toast.LENGTH_SHORT).show();
                } else if (endDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Deadline to Assign", Toast.LENGTH_SHORT).show();
                } else if (taskName.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Task Name to Assign", Toast.LENGTH_SHORT).show();
                } else if (taskDetail.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Task Detail to Assign", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    CreateMyTaskRequest createMyTaskRequest = new CreateMyTaskRequest(
                            taskName.getText().toString(),
                            endDateEt.getText().toString(),
                            "n/a",
                            taskDetail.getText().toString());

                    if (userType.equalsIgnoreCase("EmployeeMyTask")) {
                        createMyTaskRequest.setBrand("");
                    } else {
                        createMyTaskRequest.setBrand(((ItemData) spinner.getSelectedItem()).getName());
                    }

//                    Toast.makeText(getContext(), ""+((ItemData) spinner.getSelectedItem()).getName(), Toast.LENGTH_SHORT).show();
                    viewModel.create(createMyTaskRequest, userType);

                }

            }
        });

        return view;
    }

    private void openDatePickerDialog(DatePickerDialog.OnDateSetListener datepickerEnd) {
        hideKeyboard(getActivity());
        new DatePickerDialog(getContext(), datepickerEnd, myCalendar
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
    public void onCreateResponse(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        addEvent2();

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
        progressBar.setVisibility(View.GONE);
        List<ItemData> list = new ArrayList<>();
        list.add(new ItemData("Select Brand", "0"));
        for (FetchAllBrandDetailsRecordResponse recordResponse : fetchAllBrandDetailsResponse.getRecords()) {
            ItemData itemData = new ItemData(recordResponse.getBrandName(), recordResponse.getId());
            list.add(itemData);
        }
        spinner.setAdapter(new SpinnerAdapter(getActivity(), R.id.txt, list));
    }

    @Override
    public void onErrorInBrand(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Some Error occured. Try again after some time !!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
    public CreateMyTaskViewModel getViewModel() {
        return viewModel;
    }


    public void addEvent2() {


        String givenDateString = endDateEt.getText().toString();
        long timeInMilliseconds = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long start2 = timeInMilliseconds;
        long end2 = timeInMilliseconds + (12 * 60 * 60 * 1000);   // 2011-02-12 13h00

        String title = taskName.getText().toString();


//        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", start2);
        intent.putExtra("endTime", end2);
        intent.putExtra("rrule", "FREQ=YEARLY");
//        intent.putExtra("allDay", false);
//        intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            CreateTaskHomeActivity createTaskHomeActivity = (CreateTaskHomeActivity) getActivity();
            createTaskHomeActivity.setLayoutVisibility(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
