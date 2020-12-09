package com.razinggroups.presentation.ui.brandCompany;

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
import com.razinggroups.presentation.ui.brandCompany.BrandManipulateScreen.BrandManipulateActivity;
import com.razinggroups.presentation.ui.brandCompany.CompanyManipulateScreen.CompanyManipulateActivity;
import com.razinggroups.presentation.ui.brandCompany.ListScreen.CompanyListFragment;

public class BrandCompanyActivity extends AppCompatActivity {

    RelativeLayout addCompany, addBrand, viewCompany, viewBrand;
    LinearLayout innerLayout;

    ImageView backIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_brand_company);

        addBrand = findViewById(R.id.activity_brand_company_add_brand_layout);
        addCompany = findViewById(R.id.activity_brand_company_add_company_layout);
        viewBrand = findViewById(R.id.activity_brand_company_view_brand_layout);
        viewCompany = findViewById(R.id.activity_brand_company_view_company_layout);
        innerLayout = findViewById(R.id.activity_brand_company_frame_inner);
        backIv = findViewById(R.id.activity_brand_company_header_home_iv);

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
        } else {
            innerLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();


        addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BrandCompanyActivity.this, CompanyManipulateActivity.class);
                i.putExtra("mode", "add");
                startActivity(i);
            }
        });
        addBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BrandCompanyActivity.this, BrandManipulateActivity.class);
                i.putExtra("mode", "add");
                startActivity(i);
            }
        });
        viewCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerLayout.setVisibility(View.GONE);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                CompanyListFragment companyListFragment = new CompanyListFragment();
                transaction.replace(R.id.activity_brand_company_frame, companyListFragment);

                transaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("listType", "company");
                companyListFragment.setArguments(bundle);

                transaction.commit();
            }
        });
        viewBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerLayout.setVisibility(View.GONE);

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                CompanyListFragment companyListFragment = new CompanyListFragment();
                transaction.replace(R.id.activity_brand_company_frame, companyListFragment);

                transaction.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("listType", "brand");
                companyListFragment.setArguments(bundle);

                transaction.commit();
            }
        });

    }
}
