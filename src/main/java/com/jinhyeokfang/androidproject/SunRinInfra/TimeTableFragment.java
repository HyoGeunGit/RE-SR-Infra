package com.jinhyeokfang.androidproject.SunRinInfra;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TimeTableFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timetable, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Client.retrofitService.loadTimeTable("1", "5").enqueue(new Callback<ResponseBody>() {
            final Context context = getActivity();
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String[] numbers = new String[34];
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray timeItems = jsonObject.getJSONArray("result");
                    for (int i=0; i<34; i++) {
                        numbers[i] = timeItems.getString(i);
                     }
                    GridView timeTableView = (GridView) view.findViewById(R.id.timeTable);
                    TimeTableAdapter adapter = new TimeTableAdapter(context, numbers);
                    timeTableView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        Spinner gradeSpinner = (Spinner) view.findViewById(R.id.gradeSpinner);
        List<String> gradeList = new ArrayList<String>(Arrays.asList("1", "2", "3"));
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gradeList);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(gradeAdapter);

        Spinner classNumberSpinner = (Spinner) view.findViewById(R.id.classNumberSpinner);
        List<String> classNumberList = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
        ArrayAdapter<String> classNumberAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, classNumberList);
        classNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classNumberSpinner.setAdapter(classNumberAdapter);
    }
}
