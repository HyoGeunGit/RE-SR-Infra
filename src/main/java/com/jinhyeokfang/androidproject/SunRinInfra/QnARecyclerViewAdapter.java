package com.jinhyeokfang.androidproject.SunRinInfra;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import java.util.List;

public class QnARecyclerViewAdapter extends RecyclerView.Adapter<QnARecyclerViewAdapter.ViewHolder> {
    private final List<QnA> mDataList;

    //constructor
    public QnARecyclerViewAdapter(List<QnA> dataList) {
        mDataList = dataList;
    }

    //create view holder, inflate layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_qna, parent, false);
        return new ViewHolder(view);
    }

    //set data
    @Override
    public void onBindViewHolder(@NonNull QnARecyclerViewAdapter.ViewHolder holder, int position) {
        QnA item = mDataList.get(position);
        holder.writer.setText(item.getWriter());
        holder.content.setText(item.getContent());
        holder.date.setText(item.getDate());
    }

    public void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.getItemCount());
    }

    public void addItem(int position, QnA item) {
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
        TextView writer;
        TextView content;
        TextView date;

        public ViewHolder (View itemView) {
            super(itemView);
            writer = itemView.findViewById(R.id.writerTextView);
            content = itemView.findViewById(R.id.contentTextView);
            date = itemView.findViewById(R.id.dateTextView);
        }
    }
}
