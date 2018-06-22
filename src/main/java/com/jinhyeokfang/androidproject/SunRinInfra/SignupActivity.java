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

public class SignupActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageView logo = (ImageView) findViewById(R.id.logo1);
        final Button signupButton = (Button) findViewById(R.id.sign_btn);
        final TextView idText = (TextView) findViewById(R.id.id_tv);
        final TextView pwText = (TextView) findViewById(R.id.pw_tv);
        final TextView nameText = (TextView) findViewById(R.id.name_tv);

        logo.bringToFront();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client.retrofitService.logUp(nameText.getText().toString(), idText.getText().toString(), pwText.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        switch(response.code()) {
                            case 200:
                                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                finish();
                                break;
                            case 405:
                                Toast.makeText(getApplicationContext(), "회원가입 실패 : 사용할 수 없는 아이디, 비밀번호입니다.", Toast.LENGTH_LONG).show();
                                break;
                            case 500:
                                Toast.makeText(getApplicationContext(), "회원가입 실패 : 서버 오류", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });
    }
}
