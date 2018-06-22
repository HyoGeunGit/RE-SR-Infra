package com.jinhyeokfang.androidproject.SunRinInfra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jinhyeokfang.androidproject.sunrininfra.R;

public class TimeTableAdapter extends BaseAdapter {
    private Context context;
    private String[] timeTables;

    public TimeTableAdapter (Context context, String timetables[]) {
        this.context = context;
        timeTables = timetables;

    }

    @Override
    public int getCount() {
        return timeTables.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.item_time, null);


            TextView timeItemView = (TextView) gridView.findViewById(R.id.timeItem);

            timeItemView.setText(timeTables[position / 5 + position % 5 * 7]);
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
