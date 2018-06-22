package com.jinhyeokfang.androidproject.SunRinInfra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final TextView pwText = (TextView) findViewById(R.id.pw_tv);
        final Button changeButton = (Button) findViewById(R.id.change_btn);

        Intent intentFromFind = getIntent();
        final String id = intentFromFind.getExtras().getString("id");

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client.retrofitService.changePassword(id, pwText.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        switch(response.code()) {
                            case 200:
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                break;
                            case 405:
                                Toast.makeText(getApplicationContext(), "재설정 실패 : 사용할 수 없는 비밀번호입니다.", Toast.LENGTH_LONG).show();
                                break;
                            case 500:
                                Toast.makeText(getApplicationContext(), "재설 실패 : 서버 오류", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }
}
