package com.razinggroups.presentation.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.razinggroups.data.network.RetrofitClient;
import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgetpassword extends AppCompatActivity {
    TextInputEditText forgetemail_ed;
    Button sendmail;
    ProgressBar progress_circular;
    String Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        forgetemail_ed = findViewById(R.id.forgetemail_ed);
        sendmail = findViewById(R.id.sendmail);
        progress_circular = findViewById(R.id.progress_circular);

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetemail();
            }
        });
    }

    public void forgetemail() {
        Email = forgetemail_ed.getText().toString().trim();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {

            Sneaker.with(Forgetpassword.this)
                    .setTitle("Enter Email Address")
                    .setMessage("")
                    .sneakError();

        } else {

            progress_circular.setVisibility(View.VISIBLE);

            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi().forgetpassword(Email);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String s;
                  //  Toast.makeText(Forgetpassword.this, response.code() + "", Toast.LENGTH_SHORT).show();
                    if (response.code() == 200) {
                        progress_circular.setVisibility(View.GONE);
                        try {
                            s = response.body().string();

                            JSONObject jsonObject = new JSONObject(s);
                            String msg = jsonObject.getString("message");
                            if (msg.equals("Success")) {

                                alertEmail(msg);
                            }else {
                                alertEmailError(msg) ;
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }


                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progress_circular.setVisibility(View.GONE);


                }
            });
        }

    }

    public void alertEmail(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.logo_black)
                .setTitle("Success")

                .setMessage(title)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent in=new Intent(Forgetpassword.this,LoginActivity.class);
                        startActivity(in);
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public void alertEmailError(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.logo_black)
                .setTitle("Error")

                .setMessage(title)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


}