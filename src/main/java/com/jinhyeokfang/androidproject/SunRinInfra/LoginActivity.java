package com.jinhyeokfang.androidproject.SunRinInfra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView logo = (ImageView) findViewById(R.id.logo1);
        final Button loginButton = (Button) findViewById(R.id.login_btn);
        final TextView signUpButton = (TextView) findViewById(R.id.signup_go);
        final TextView idText = (TextView) findViewById(R.id.id_tv);
        final TextView pwText = (TextView) findViewById(R.id.pw_tv);
        final TextView findText = (TextView) findViewById(R.id.find_tv);

        logo.bringToFront();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client.retrofitService.logIn(idText.getText().toString(), pwText.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        switch(response.code()) {
                            case 200:
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                break;
                            case 405:
                                Toast.makeText(getApplicationContext(), "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show();
                                break;
                            case 500:
                                Toast.makeText(getApplicationContext(), "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });

        findText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FindAccountActivity.class));
            }
        });
    }
}
