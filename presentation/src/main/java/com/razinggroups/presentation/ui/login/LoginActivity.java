package com.razinggroups.presentation.ui.login;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.Credentials;
import com.razinggroups.domain.model.Login;
import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseActivity;
import com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeHomeActivity;
import com.razinggroups.presentation.ui.main.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;

public class LoginActivity extends BaseActivity<LoginViewModel> implements LoginNavigator {

    @Inject
    @Named("LoginActivity")
    ViewModelProvider.Factory viewModelFactory;
    LoginViewModel loginViewModel;
    EditText userNameEt, passwordEt;
    Button loginBtn;
    FrameLayout progressBarLayout;
    CheckBox checkBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((MainApplication) getApplicationContext()).getComponent().inject(this);
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        loginViewModel.setNavigator(this);

        userNameEt = findViewById(R.id.login_activity_et_username);
        passwordEt = findViewById(R.id.login_activity_et_password);
        loginBtn = findViewById(R.id.login_activity_login_btn);
        progressBarLayout = findViewById(R.id.login_activity_progress_bar_layout);
        progressBarLayout.setVisibility(View.GONE);
        checkBox = findViewById(R.id.checkBox);


        loginViewModel.getCred();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userNameEt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter UserName to Continue !!", Toast.LENGTH_SHORT).show();
                } else if (passwordEt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Password to Continue !!", Toast.LENGTH_SHORT).show();
                } else {
                    hideKeyboard(LoginActivity.this);
                    progressBarLayout.setVisibility(View.VISIBLE);
                    loginViewModel.login(userNameEt.getText().toString(), passwordEt.getText().toString());
                    }
            }
        });
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
    public LoginViewModel getViewModel() {
        return loginViewModel;
    }


    @Override
    public void onLoginSuccess(Login login) {

        progressBarLayout.setVisibility(View.GONE);
        if (checkBox.isChecked()) {
            loginViewModel.setCred(userNameEt.getText().toString(), passwordEt.getText().toString());
        } else {
            loginViewModel.setCred("", "");

        }

        if (login.getMsg().equalsIgnoreCase("Valid User")) {
            if (login.getType().trim().equalsIgnoreCase("MasterAdmin")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userType", "MasterAdmin");
                intent.putExtra("userName", login.getName());

                SharedPrefManager.getInstans(getApplicationContext()).userLogin(login.getEmpId(),login.getName(),login.getType());
                startActivity(intent);
                finish();
            } else if (login.getType().equalsIgnoreCase("Employee")) {
                Intent intent = new Intent(LoginActivity.this, EmployeeHomeActivity.class);
                intent.putExtra("employeeId", login.getEmpId());
                intent.putExtra("userName", login.getName());

                intent.putExtra("useremail", login.getEmail());

                SharedPrefManager.getInstans(getApplicationContext()).userLogin(login.getEmpId(),login.getName(),login.getType());

                startActivity(intent);
                finish();
            } else if (login.getType().equalsIgnoreCase("Admin")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userType", "Admin");
                intent.putExtra("userName", login.getName());

                SharedPrefManager.getInstans(getApplicationContext()).userLogin(login.getEmpId(),login.getName(),login.getType());
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "String Changed from Back end" + login.getMsg(), Toast.LENGTH_SHORT).show();
            }


        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("userType", "HR");
            intent.putExtra("userName", "HR");

            startActivity(intent);
            finish();
            Toast.makeText(this, "not valid user " + login.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String toString) {
        progressBarLayout.setVisibility(View.GONE);
        Toast.makeText(this, "Error in response from back end " + toString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCredLoaded(Credentials credentials) {
        if (!credentials.getName().isEmpty()) {
            userNameEt.setText(credentials.getName().trim());
            passwordEt.setText(credentials.getPassword().trim());
            checkBox.setChecked(true);
        }
    }
}
