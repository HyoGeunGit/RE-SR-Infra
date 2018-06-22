package com.jinhyeokfang.androidproject.SunRinInfra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QnAFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qna, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView QnaRecyclerView = (RecyclerView) view.findViewById(R.id.qnaRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        QnaRecyclerView.setLayoutManager(layoutManager);

        final QnARecyclerViewAdapter QnAAdapter = new QnARecyclerViewAdapter(new ArrayList<QnA>());
        QnaRecyclerView.setAdapter(QnAAdapter);

        Client.retrofitService.loadQNA().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONArray qnaItems = null;
                try {
                    qnaItems = jsonObject.getJSONArray("result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < qnaItems.length(); i++) {
                    JSONObject data = null;
                    try {
                        data = qnaItems.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        QnAAdapter.addItem(QnAAdapter.getItemCount(), new QnA(data.getString("writer"),data.getString("content"),data.getString("date")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        final FloatingActionButton addButton = view.findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QnAAdapter.addItem(QnAAdapter.getItemCount(), new QnA("testest", "test", "test"));
            }
        });


    }
}
