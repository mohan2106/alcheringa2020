package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.events.EventDataAdapter;
import com.app.alcheringa2020.events.EventsDataModel;
import com.app.alcheringa2020.events.EventsFragment;
import com.app.alcheringa2020.events.model.ProgrammeModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 31-Dec-19.
 */
public class ScheduleFragment extends BaseFragment {
    private static String TAG = ScheduleFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static ScheduleFragment fragment;
    RecyclerView schedule_recycler;
    ArrayList<ScheduleModel> scheduleModelArrayList;
    ScheduleDataAdapter scheduleDataAdapter;

    public ScheduleFragment() {
        //blank Constructor
    }

    public static ScheduleFragment newInstance(Context context1) {
        fragment = new ScheduleFragment();
        context = context1;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_container = container;
        context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.recycler_layout, container, false);
        schedule_recycler = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        schedule_recycler.setLayoutManager(mLayoutManager);
        schedule_recycler.setItemAnimator(new DefaultItemAnimator());
        initData();
        return view;
    }
    private void initData() {
        try {
            scheduleModelArrayList  = ScheduleDataModel.scheduleModelArrayList(context);
            scheduleDataAdapter = new ScheduleDataAdapter(context, scheduleModelArrayList);
            schedule_recycler.setAdapter(scheduleDataAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
