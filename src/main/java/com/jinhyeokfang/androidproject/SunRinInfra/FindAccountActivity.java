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

public class FindAccountActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_account);

        final Button findButton = (Button) findViewById(R.id.find_btn);
        final TextView idText = (TextView) findViewById(R.id.id_tv);
        final TextView nameText = (TextView) findViewById(R.id.name_tv);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Client.retrofitService.findAccount(idText.getText().toString(), nameText.getText().toString()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    switch(response.code()) {
                        case 200:
                            finish();
                            Intent changePwIntent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                            changePwIntent.putExtra("id", idText.getText().toString());
                            startActivity(changePwIntent);
                            break;
                        case 405:
                            Toast.makeText(getApplicationContext(), "인증 실패 : 아이디나 이름이 올바르지 않습니다", Toast.LENGTH_LONG).show();
                            break;
                        case 500:
                            Toast.makeText(getApplicationContext(), "인증 실패 : 서버 오류", Toast.LENGTH_LONG).show();

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
