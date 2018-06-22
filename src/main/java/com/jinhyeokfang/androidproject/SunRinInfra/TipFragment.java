package com.jinhyeokfang.androidproject.SunRinInfra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import java.util.ArrayList;

public class TipFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tip, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView TipRecyclerView = (RecyclerView) view.findViewById(R.id.tipRecyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        TipRecyclerView.setLayoutManager(layoutManager);

        ArrayList<Tip> dataList = new ArrayList<>();
        dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));dataList.add(new Tip("title", "description"));

        final TipRecyclerViewAdapter TipAdapter = new TipRecyclerViewAdapter(dataList);
        TipRecyclerView.setAdapter(TipAdapter);




        final ViewPager tipViewPager = (ViewPager) view.findViewById(R.id.tipViewPager);


        //set viewpager adapter
        TipViewPagerAdapter pagerAdapter = new TipViewPagerAdapter(getActivity().getSupportFragmentManager());
        tipViewPager.setAdapter(pagerAdapter);

    }
}
