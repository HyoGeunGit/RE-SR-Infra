package com.jinhyeokfang.androidproject.SunRinInfra;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import java.util.ArrayList;
import java.util.List;

public class TipRecyclerViewAdapter extends RecyclerView.Adapter<TipRecyclerViewAdapter.ViewHolder> {
    private final List<Tip> mDataList;

    //constructor
    public TipRecyclerViewAdapter(ArrayList<Tip> dataList) {
        mDataList = dataList;
    }

    //create view holder, inflate layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tip, parent, false);
        return new ViewHolder(view);
    }

    //set data
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tip item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
    }

    public void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.getItemCount());
    }

    public void addItem(int position, Tip item) {
        mDataList.add(position, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, this.getItemCount());
    }

    //return data size
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public ViewHolder (View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tip_title);
            description = itemView.findViewById(R.id.tip_description);
        }
    }
}
