package com.razinggroups.presentation.ui.employeeHomeScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.CustomerQueryFragment;
import com.razinggroups.presentation.ui.CustomerQuery.CustomerSection.CustomerQueryMainFragment;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeTaskDetailList.EmployeeTaskDetailListFragment;
import com.razinggroups.presentation.ui.login.LoginActivity;
import com.razinggroups.presentation.ui.myTask.MyTaskFragment;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
*/

public class EmployeeHomeActivity extends AppCompatActivity {


    TextView titleTv, signOut;
    BottomNavigationView bottomNavigationView;
    private String selectedFragment = "";
    private String empId, userName,useremail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_employee_home);

        empId = getIntent().getStringExtra("employeeId");
        userName = getIntent().getStringExtra("userName");


        titleTv = findViewById(R.id.activity_main_header_title);
        signOut = findViewById(R.id.activity_employee_home_sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signOutMethod();
            }
        });

        bottomNavigationView = findViewById(R.id.activity_employee_home_bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_task_detail: {
                        if (!selectedFragment.equalsIgnoreCase("EmployeeTaskDetailFragment")) {
                            openEmployeeTaskDetailFragment();
                        }
                        break;
                    }
                    case R.id.action_leave_information: {
                        if (!selectedFragment.equalsIgnoreCase("EmployeeLeaveMainFragment")) {
                            emptyFragmentStack();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            EmployeeLeaveMainFragment employeeLeaveMainFragment = new EmployeeLeaveMainFragment();
                            transaction.replace(R.id.activity_employee_home_frame_layout, employeeLeaveMainFragment);

                            Bundle bundle = new Bundle();
                            bundle.putString("employeeId", empId);
                            employeeLeaveMainFragment.setArguments(bundle);
                            selectedFragment = "EmployeeLeaveMainFragment";
                            transaction.commit();
                            titleTv.setText("Leave Information");

                        }
                        break;
                    }
                    case R.id.action_my_tasks: {
                        if (!selectedFragment.equalsIgnoreCase("MyTaskFragment")) {
                            emptyFragmentStack();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            MyTaskFragment myTaskFragment = new MyTaskFragment();
                            transaction.replace(R.id.activity_employee_home_frame_layout, myTaskFragment);

                            Bundle bundle = new Bundle();
                            bundle.putString("userType", "EmployeeMyTask");
                            myTaskFragment.setArguments(bundle);
                            selectedFragment = "MyTaskFragment";
                            transaction.commit();
                            titleTv.setText("My Tasks");
                        }
                        break;
                    }
                    case R.id.action_customer_querry: {
                        if (!selectedFragment.equalsIgnoreCase("MyTaskFragment")) {
                            emptyFragmentStack();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            CustomerQueryMainFragment customerQueryMainFragment = new CustomerQueryMainFragment();
                            transaction.replace(R.id.activity_employee_home_frame_layout, customerQueryMainFragment);

                            Bundle bundle = new Bundle();
                            bundle.putString("key", "1");
                            customerQueryMainFragment.setArguments(bundle);
                            selectedFragment = "CustomerQueryMainFragment";
                            transaction.commit();

                            titleTv.setText("Customer Query");

                            //Toast.makeText(EmployeeHomeActivity.this, "Customer Query", Toast.LENGTH_SHORT).show();


                        }
                        break;
                    }
                }

                return true;
            }
        });
        openEmployeeTaskDetailFragment();
    }

    private void signOutMethod() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to sign out?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
                /*logout();*/
                performLogout();


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

    void openEmployeeTaskDetailFragment() {
        emptyFragmentStack();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        EmployeeTaskDetailListFragment employeeTaskDetailListFragment = new EmployeeTaskDetailListFragment();
        transaction.replace(R.id.activity_employee_home_frame_layout, employeeTaskDetailListFragment);

        Bundle bundle = new Bundle();
        bundle.putString("employeeId", empId);
        bundle.putString("userName", userName);
        employeeTaskDetailListFragment.setArguments(bundle);
        transaction.commit();
        selectedFragment = "EmployeeTaskDetailFragment";
        titleTv.setText("Task Details");
    }

    public void emptyFragmentStack() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }



  public void performLogout() {

      useremail = getIntent().getStringExtra("useremail").trim();
//      Toast.makeText(this, useremail+"", Toast.LENGTH_SHORT).show();


        RequestQueue queue = Volley.newRequestQueue(EmployeeHomeActivity.this);

        String Url = "https://365.raizinggroup.com/api_crm/login/logout.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


             Toast.makeText(EmployeeHomeActivity.this,  "Logout Successfully !", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EmployeeHomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();



            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EmployeeHomeActivity.this, error.toString()+"", Toast.LENGTH_SHORT).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uemail",useremail);
                return params;
            }
        };

        queue.add(stringRequest);
    }

  }

