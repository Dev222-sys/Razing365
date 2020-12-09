package com.razinggroups.presentation.ui.vendor;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.vendor.createVendorTask.CreateVendorTaskActivity;
import com.razinggroups.presentation.ui.vendor.manipulateVendor.ManipulateVendorActivity;
import com.razinggroups.presentation.ui.vendor.vendorList.VendorListFragment;
import com.razinggroups.presentation.ui.vendor.vendorTaskList.VendorTaskListFragment;

public class VendorActivity extends AppCompatActivity {

    RelativeLayout addVendor, addTask, viewVendor, viewTask;
    LinearLayout innerLayout;

    ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_vendor);

        addVendor = findViewById(R.id.activity_vendor_add_vendor_layout);
        addTask = findViewById(R.id.activity_vendor_add_task_layout);
        viewVendor = findViewById(R.id.activity_vendor_view_vendor_layout);
        viewTask = findViewById(R.id.activity_vendor_view_tasks_layout);

        innerLayout = findViewById(R.id.activity_vendor_frame_inner);
        backIv = findViewById(R.id.activity_vendor_header_home_iv);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        addVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VendorActivity.this, ManipulateVendorActivity.class);
                i.putExtra("mode", "add");
                startActivity(i);
            }
        });
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VendorActivity.this, CreateVendorTaskActivity.class);
//                i.putExtra("mode", "add");
                startActivity(i);

            }
        });
        viewVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerLayout.setVisibility(View.GONE);

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                VendorListFragment companyListFragment = new VendorListFragment();
                transaction.replace(R.id.activity_vendor_frame, companyListFragment);

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerLayout.setVisibility(View.GONE);

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                VendorTaskListFragment companyListFragment = new VendorTaskListFragment();
                transaction.replace(R.id.activity_vendor_frame, companyListFragment);

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



    }
    public void setLayoutVisibility(boolean visibility) {
        if (visibility) {
            innerLayout.setVisibility(View.VISIBLE);
        } else {
            innerLayout.setVisibility(View.GONE);
        }
    }
}
