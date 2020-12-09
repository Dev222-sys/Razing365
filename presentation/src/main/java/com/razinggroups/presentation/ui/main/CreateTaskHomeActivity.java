package com.razinggroups.presentation.ui.main;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.createTask.CreateTaskActivity;
import com.razinggroups.presentation.ui.myTask.createMyTask.CreateMyTaskFragment;
import com.razinggroups.presentation.ui.vendor.createVendorTask.CreateVendorTaskActivity;

public class CreateTaskHomeActivity extends AppCompatActivity {

    Button employeeTaskBtn, vendorTaskBtn, personalTaskBtn;
    ImageView backIv;
    TextView headerTv;
    String userType = "";
    LinearLayout innerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_task_home);


        headerTv = findViewById(R.id.activity_create_task_home_header_tv);
        backIv = findViewById(R.id.activity_create_task_home_cross_btn);


        employeeTaskBtn = findViewById(R.id.activity_create_task_home_employee_btn);
        vendorTaskBtn = findViewById(R.id.activity_create_task_home_vendor_btn);
        personalTaskBtn = findViewById(R.id.activity_create_task_home_personal_btn);
        innerLayout = findViewById(R.id.activity_create_task_home_inner_layout);

        userType = getIntent().getStringExtra("userType");

        employeeTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateTaskHomeActivity.this, CreateTaskActivity.class);
                startActivity(i);
            }
        });

        vendorTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateTaskHomeActivity.this, CreateVendorTaskActivity.class);
                startActivity(i);
            }
        });
        personalTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutVisibility(false);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                CreateMyTaskFragment createMyTaskFragment = new CreateMyTaskFragment();
                transaction.replace(R.id.activity_create_task_home_frame, createMyTaskFragment);
                transaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("userType", userType);
                createMyTaskFragment.setArguments(bundle);
                transaction.commit();
            }
        });
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setLayoutVisibility(boolean visibility) {
        if (visibility) {
            innerLayout.setVisibility(View.VISIBLE);
            headerTv.setText("CHOOSE TASK");

        } else {
            innerLayout.setVisibility(View.GONE);
            headerTv.setText("CREATE TASK");
        }
    }
}
