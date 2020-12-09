package com.razinggroups.presentation.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseActivity;
import com.razinggroups.presentation.ui.CustomerQuery.CustomerQueryFragment;
import com.razinggroups.presentation.ui.brandCompany.BrandCompanyActivity;
import com.razinggroups.presentation.ui.dashboard.DashBoardFragment;
import com.razinggroups.presentation.ui.employee.EmployeeFragment;
import com.razinggroups.presentation.ui.employeeTask.EmployeeTaskFragment;
import com.razinggroups.presentation.ui.holiday.HolidayMainFragment;
import com.razinggroups.presentation.ui.leaves.LeaveFragmnet;
import com.razinggroups.presentation.ui.login.LoginActivity;
import com.razinggroups.presentation.ui.myTask.MyTaskFragment;
import com.razinggroups.presentation.ui.vendor.VendorActivity;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends BaseActivity<MainViewModel> implements MainNavigator, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    @Named("MainActivity")
    ViewModelProvider.Factory viewModelFactory;

    MainViewModel mainViewModel;
    Toolbar toolbar;

    ImageView toggleIv;
    TextView headerTitle;
    LinearLayout dashBoardLayout, personalTaskLayout, employeeTaskLayout, holidayLayout;
    TextView dashBoardTv, personalTaskTv, employeeTaskTv, holidayTv;
    ImageView dashBoardIv, personalTaskIv, employeeTaskIv, holidayIv;


    ImageView addIv;

    String userType = "";
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((MainApplication) getApplicationContext()).getComponent().inject(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setVisibility(View.GONE);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViews();

        userType = getIntent().getStringExtra("userType");
        userName = getIntent().getStringExtra("userName");


        if (userType.equalsIgnoreCase("MasterAdmin")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer_admin);

        } else if (userType.equalsIgnoreCase("Admin")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main2_drawer);

        }
        openDashBoardFragment();

    }

    private void initViews() {
        toggleIv = findViewById(R.id.activity_main_header_home_iv);
        headerTitle = findViewById(R.id.activity_main_header_title);
        addIv = findViewById(R.id.activity_main_footer_add_icon);


        dashBoardLayout = findViewById(R.id.activity_main_footer_dashboard);
        dashBoardIv = findViewById(R.id.activity_main_footer_dashboard_iv);
        dashBoardTv = findViewById(R.id.activity_main_footer_dashboard_tv);

        personalTaskLayout = findViewById(R.id.activity_main_footer_personal_task);
        personalTaskIv = findViewById(R.id.activity_main_footer_personal_task_iv);
        personalTaskTv = findViewById(R.id.activity_main_footer_personal_task_tv);

        employeeTaskLayout = findViewById(R.id.activity_main_footer_employee_task);
        employeeTaskIv = findViewById(R.id.activity_main_footer_employee_task_iv);
        employeeTaskTv = findViewById(R.id.activity_main_footer_employee_task_tv);

        holidayLayout = findViewById(R.id.activity_main_footer_holiday_list);
        holidayIv = findViewById(R.id.activity_main_footer_holiday_list_iv);
        holidayTv = findViewById(R.id.activity_main_footer_holiday_list_tv);


        employeeTaskLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeTaskFragment("all");
            }
        });
        dashBoardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashBoardFragment();
            }
        });
        personalTaskLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonalTaskFragment();
            }
        });
        holidayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHolidayFragment();
            }
        });


        toggleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
        addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateTaskHomeActivity();
            }
        });
    }

    private void footerVisibilityManager(String string) {
        switch (string) {
            case "holiday":
                dashBoardTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                personalTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                employeeTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                holidayTv.setTextColor(ContextCompat.getColor(this, R.color.colorRed));

                dashBoardIv.setImageResource(R.drawable.dashboard_grey);
                personalTaskIv.setImageResource(R.drawable.personal_task_grey);
                employeeTaskIv.setImageResource(R.drawable.employee_tasks_grey);
                holidayIv.setImageResource(R.drawable.holiday_list_red);
                break;
            case "DashBoard":

                dashBoardTv.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                personalTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                employeeTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                holidayTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));

                dashBoardIv.setImageResource(R.drawable.dashboard_red);
                personalTaskIv.setImageResource(R.drawable.personal_task_grey);
                employeeTaskIv.setImageResource(R.drawable.employee_tasks_grey);
                holidayIv.setImageResource(R.drawable.holiday_list_grey);
                break;
            case "EmployeeTask":

                dashBoardTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                personalTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                employeeTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                holidayTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));

                dashBoardIv.setImageResource(R.drawable.dashboard_grey);
                personalTaskIv.setImageResource(R.drawable.personal_task_grey);
                employeeTaskIv.setImageResource(R.drawable.employee_tasks_red);
                holidayIv.setImageResource(R.drawable.holiday_list_grey);
                break;
            case "PersonalTask":
                dashBoardTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                personalTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                employeeTaskTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
                holidayTv.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));

                dashBoardIv.setImageResource(R.drawable.dashboard_grey);
                personalTaskIv.setImageResource(R.drawable.personal_task_red);
                employeeTaskIv.setImageResource(R.drawable.employee_tasks_grey);
                holidayIv.setImageResource(R.drawable.holiday_list_grey);
                break;
        }

    }

    private void openCreateTaskHomeActivity() {
        Intent i = new Intent(this, CreateTaskHomeActivity.class);
        i.putExtra("userType", userType);
        startActivity(i);
    }

    private void openCustomerQueryFragment()
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CustomerQueryFragment employeeFragment = new CustomerQueryFragment();
        transaction.replace(R.id.activity_main_frame, employeeFragment);
        transaction.commit();
        headerTitle.setText("Customer Query");

    }

    private void openDashBoardFragment() {
        footerVisibilityManager("DashBoard");
        emptyFragmentStack();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userType", userType);
        bundle.putString("userName", userName);
        dashBoardFragment.setArguments(bundle);
        transaction.replace(R.id.activity_main_frame, dashBoardFragment);
        transaction.commit();
        headerTitle.setText("DASHBOARD");
    }

    public void openEmployeeTaskFragment(String string) {
        footerVisibilityManager("EmployeeTask");
        emptyFragmentStack();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        EmployeeTaskFragment employeeTaskFragment = new EmployeeTaskFragment();

        Bundle bundle = new Bundle();
        bundle.putString("taskType", string);
        employeeTaskFragment.setArguments(bundle);

        transaction.replace(R.id.activity_main_frame, employeeTaskFragment);
        transaction.commit();
        headerTitle.setText("EMPLOYEE TASKS");
    }


    private void openHolidayFragment() {
        footerVisibilityManager("holiday");
        emptyFragmentStack();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HolidayMainFragment holidayMainFragment = new HolidayMainFragment();
        transaction.replace(R.id.activity_main_frame, holidayMainFragment);
        transaction.commit();
        headerTitle.setText("HOLIDAYS");
    }

    public void openPersonalTaskFragment() {
        footerVisibilityManager("PersonalTask");
        emptyFragmentStack();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MyTaskFragment myTaskFragment = new MyTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userType", userType);
        myTaskFragment.setArguments(bundle);
        transaction.replace(R.id.activity_main_frame, myTaskFragment);
        transaction.commit();
        headerTitle.setText("MY TASKS");
    }

    public void setHeaderTitle(String str) {
        headerTitle.setText(str);
    }

    public void openEmployeesFragment() {
        footerVisibilityManager("DashBoard");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        EmployeeFragment employeeFragment = new EmployeeFragment();
        transaction.replace(R.id.activity_main_frame, employeeFragment);
        transaction.addToBackStack(null);

        Bundle bundle = new Bundle();
        bundle.putString("type", "all");
        employeeFragment.setArguments(bundle);


        transaction.commit();
        headerTitle.setText("EMPLOYEES");
    }


    public void setTitle(String str) {

        headerTitle.setText(str);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    public void openDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_task) {
            openPersonalTaskFragment();
        } else if (id == R.id.brand_company) {
            openBrandCompany();
        } else if (id == R.id.view_edit_employees) {
            emptyFragmentStack();
            openEmployeesFragment();
        } else if (id == R.id.employees_task) {
            openEmployeeTaskFragment("all");
        }else if (id == R.id.customerquery) {
            //Toast.makeText(this, "customerquery", Toast.LENGTH_SHORT).show();
            openCustomerQueryFragment();


            /*openLeaveFragment();*/
        } else if (id == R.id.leave_information) {
            openLeaveFragment();
        } else if (id == R.id.holidays_list) {
            openHolidayFragment();
        } else if (id == R.id.vendor) {
            openVendor();
        } else if (id == R.id.signOut) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to sign out?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void openVendor() {
        Intent i = new Intent(this, VendorActivity.class);
        startActivity(i);
    }

    private void openBrandCompany() {
        Intent i = new Intent(this, BrandCompanyActivity.class);
        startActivity(i);
    }

    private void openLeaveFragment() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        LeaveFragmnet employeeFragment = new LeaveFragmnet();
        transaction.replace(R.id.activity_main_frame, employeeFragment);
        transaction.commit();
        headerTitle.setText("Leave");
    }

    public void emptyFragmentStack() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }

    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }


}
