package com.razinggroups.presentation.ui.createTask;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.model.employeeTask.CreateEmployeeTaskRequest;
import com.razinggroups.domain.utils.Const;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

public class CreateTaskActivity extends BaseActivity<CreateTaskViewModel> implements CreateTaskNavigator {

    @Inject
    @Named("CreateTaskActivity")
    ViewModelProvider.Factory viewModelFactory;
    CreateTaskViewModel viewModel;


    ImageView backBtn;

    EditText taskName, taskDetail;
    EditText endDateEt;
    LinearLayout endDateLayout;
    Button btn;

    ProgressBar progressBar;

    RelativeLayout layout;

    Calendar myCalendar = Calendar.getInstance();

    Spinner brandSpinner, employeeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_create_task);

        ((MainApplication) getApplicationContext()).getComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateTaskViewModel.class);
        viewModel.setNavigator(this);

        layout = findViewById(R.id.activity_create_task_layout);
        backBtn = findViewById(R.id.activity_create_task_cross_btn);
        taskName = findViewById(R.id.activity_create_task_task_name);
        taskDetail = findViewById(R.id.activity_create_task_task_detail);
        endDateEt = findViewById(R.id.activity_create_task_end_date_et);
        endDateLayout = findViewById(R.id.activity_create_task_end_date);
        btn = findViewById(R.id.activity_create_task_assign_btn);
        progressBar = findViewById(R.id.activity_create_task_progress_bar);
        brandSpinner = findViewById(R.id.activity_create_task_spinner_brand);
        employeeSpinner = findViewById(R.id.activity_create_task_spinner_employee);

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
                hideKeyboard(CreateTaskActivity.this);
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
                    Toast.makeText(getApplicationContext(), "Please Enter Brand to Assign", Toast.LENGTH_SHORT).show();
                } else if (endDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Deadline to Assign", Toast.LENGTH_SHORT).show();
                } else if (taskName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Task Name to Assign", Toast.LENGTH_SHORT).show();
                } else if (taskDetail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Task Detail to Assign", Toast.LENGTH_SHORT).show();
                } else if (employeeSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Select Employee to Assign", Toast.LENGTH_SHORT).show();
                } else {
                    com.razinggroups.domain.model.employeeTask.CreateEmployeeTaskRequest createEmployeeTaskRequest = new CreateEmployeeTaskRequest(
                            ((ItemData) employeeSpinner.getSelectedItem()).getId(),
                            "n/a",
                            taskDetail.getText().toString(),
                            endDateEt.getText().toString(),
                            taskName.getText().toString()
                    );
                    progressBar.setVisibility(View.VISIBLE);
                    viewModel.createTask(createEmployeeTaskRequest);
                }

            }
        });


        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    employeeSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        layout.setVisibility(View.GONE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, 0);
//        }
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.GET_ACCOUNTS}, 1);
//        }
        viewModel.fetchBrand();
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
    public CreateTaskViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Some Error occured. Try again after some time !!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
    public void onCreateResponse(String message) {
        progressBar.setVisibility(View.INVISIBLE);
//        addEvent();
//        addEvent2();
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
    public void onEmployeesLoaded(EmployeeList employeeList) {
        progressBar.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);

        employeeSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (brandSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Select Brand First ", Toast.LENGTH_SHORT).show();
                    return true;
                } else {

                    List<ItemData> list = new ArrayList<>();
                    list.add(new ItemData("Select Employee", "0"));
                    String brandId = ((ItemData) brandSpinner.getSelectedItem()).getId();

                    for (EmployeeDetail recordResponse : employeeList.getEmployeeDetailList()) {
                        if (recordResponse.getBrandId().equalsIgnoreCase(brandId)) {
                            ItemData itemData = new ItemData(recordResponse.getName(), recordResponse.getId());
                            list.add(itemData);
                        }
                    }
                    employeeSpinner.setAdapter(new SpinnerAdapter(CreateTaskActivity.this, R.id.txt, list));
                    return false;
                }
            }
        });


    }

    @Override
    public void onBrandLoaded(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
        viewModel.fetchEmployees();
        List<ItemData> list = new ArrayList<>();
        list.add(new ItemData("Select Brand", "0"));
        for (FetchAllBrandDetailsRecordResponse recordResponse : fetchAllBrandDetailsResponse.getRecords()) {
            ItemData itemData = new ItemData(recordResponse.getBrandName(), recordResponse.getId());
            list.add(itemData);
        }
        brandSpinner.setAdapter(new SpinnerAdapter(this, R.id.txt, list));


    }


    public void addEvent() {

        String calenderEmaillAddress = "";

//        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
//
//        Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
//        for (Account account : accounts) {
//            if (emailPattern.matcher(account.name).matches()) {
//                calenderEmaillAddress = account.name;
//
//            }
//        }

        String accountName = "";

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.GET_ACCOUNTS}, 1);
        } else {
            Account account = getAccount(AccountManager.get(getApplicationContext()));
            if (account != null)
                accountName = account.name;
        }


        Log.e("raman", "email add" + accountName);

        String finalCalenderEmaillAddress = accountName;
        Dexter.withActivity(CreateTaskActivity.this)
                .withPermission(Manifest.permission.WRITE_CALENDAR)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        try {

                            int calenderId = -1;
//                            String calenderEmaillAddress = "ramanbindal411@gmail.com";

                            String[] projection = new String[]{
                                    CalendarContract.Calendars._ID,
                                    CalendarContract.Calendars.ACCOUNT_NAME};
                            ContentResolver cr = getApplicationContext().getContentResolver();

                            Cursor cursor = cr.query(Uri.parse("content://com.android.calendar/calendars"), projection,
                                    CalendarContract.Calendars.ACCOUNT_NAME + "=? and (" +
                                            CalendarContract.Calendars.NAME + "=? or " +
                                            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME + "=?)",
                                    new String[]{finalCalenderEmaillAddress, finalCalenderEmaillAddress,
                                            finalCalenderEmaillAddress}, null);

                            if (cursor.moveToFirst()) {

                                if (cursor.getString(1).equals(finalCalenderEmaillAddress)) {

                                    calenderId = cursor.getInt(0);
                                }
                            }

                            String givenDateString = endDateEt.getText().toString();
                            long timeInMilliseconds = 0;
                            SimpleDateFormat sdf = new SimpleDateFormat(Const.dateFormat);
                            try {
                                Date mDate = sdf.parse(givenDateString);
                                timeInMilliseconds = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            long start2 = timeInMilliseconds;
                            long end2 = timeInMilliseconds + (12 * 60 * 60 * 1000);   // 2011-02-12 13h00

                            String title = taskName.getText().toString();

                            ContentValues cvEvent = new ContentValues();
                            cvEvent.put("calendar_id", calenderId);
                            cvEvent.put(CalendarContract.Events.TITLE, title);

                            cvEvent.put(CalendarContract.Events.DESCRIPTION, String.valueOf(start2));
//                            cvEvent.put(CalendarContract.Events.EVENT_LOCATION, "Bhatar,Surat");
                            cvEvent.put("dtstart", start2);
                            cvEvent.put("hasAlarm", 1);
                            cvEvent.put("dtend", end2);

                            cvEvent.put("eventTimezone", TimeZone.getDefault().getID());


                            Uri uri = getContentResolver().insert(Uri.parse("content://com.android.calendar/events"), cvEvent);


                            // get the event ID that is the last element in the Uri

                            long eventID = Long.parseLong(uri.getLastPathSegment());


                            ContentValues values = new ContentValues();

                            values.put(CalendarContract.Reminders.MINUTES, 2);
                            values.put(CalendarContract.Reminders.EVENT_ID, eventID);
                            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALARM);
                            if (ActivityCompat.checkSelfPermission(CreateTaskActivity.this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
                            //Uri uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();

    }

    public void addEvent2() {


        String givenDateString = endDateEt.getText().toString();
        long timeInMilliseconds = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(Const.dateFormat);
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

    public static Account getAccount(AccountManager accountManager) {
//        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account[] accounts = accountManager.getAccounts();
        Account account;

        Log.e("raman", "accounts lenght- " + accounts.length);
        Log.e("raman", "accounts- " + accounts.toString());

        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

}
