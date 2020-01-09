package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.EventActivity;
import com.app.alcheringa2020.events.EventHorizontalAdapter;
import com.app.alcheringa2020.events.model.ItemModel;
import com.app.alcheringa2020.schedule.model.EventModel;

import java.util.ArrayList;


/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class ScheduleChildAdapter extends RecyclerView.Adapter<ScheduleChildAdapter.ViewHolder> {
    String TAG = ScheduleChildAdapter.class.getSimpleName();
    ArrayList<EventModel> eventModelArrayList;
    Context mContext;

    public ScheduleChildAdapter(Context context, ArrayList<EventModel> eventModelArrayList) {
        this.eventModelArrayList = eventModelArrayList;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventTxt,venueTxt,timeTxt;
        public ViewHolder(View v) {
            super(v);
            eventTxt = (TextView) v.findViewById(R.id.eventTxt);
            venueTxt = (TextView) v.findViewById(R.id.venueTxt);
            timeTxt = (TextView) v.findViewById(R.id.timeTxt);

        }
    }
    public void removeItem(int position) {
        eventModelArrayList.remove(position);
        notifyItemRemoved(position);
    }
    public ArrayList<EventModel> getData() {
        return eventModelArrayList;
    }
    @Override
    public ScheduleChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_schedule_child, parent, false);
        return new ScheduleChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScheduleChildAdapter.ViewHolder holder,final int position) {
        EventModel eventModel = eventModelArrayList.get(position);
        holder.eventTxt.setText(eventModel.getEvent_name());
        holder.venueTxt.setText(eventModel.getVenue());
        holder.timeTxt.setText(eventModel.getStart_time()+"-"+eventModel.getEnd_time());

    }

    @Override
    public int getItemCount() {
        return eventModelArrayList.size();
    }
}