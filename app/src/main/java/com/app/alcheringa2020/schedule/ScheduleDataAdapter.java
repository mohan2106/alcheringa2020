package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.EventDataAdapter;
import com.app.alcheringa2020.events.EventHorizontalAdapter;
import com.app.alcheringa2020.events.model.ProgrammeModel;
import com.app.alcheringa2020.external.SwipeToDeleteCallback;
import com.app.alcheringa2020.schedule.model.ScheduleModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class ScheduleDataAdapter extends RecyclerView.Adapter<ScheduleDataAdapter.ViewHolder> {
    String TAG = ScheduleDataAdapter.class.getSimpleName();

    ArrayList<ScheduleModel> scheduleModelArrayList;
    Context mContext;

    public ScheduleDataAdapter(Context context, ArrayList<ScheduleModel> scheduleModelArrayList) {
        this.scheduleModelArrayList = scheduleModelArrayList;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_text;
        RecyclerView rv_child;
        int position_data;

        public ViewHolder(View v) {
            super(v);
            title_text = (TextView) v.findViewById(R.id.text_title);
            rv_child = (RecyclerView) v.findViewById(R.id.event_data);

        }

    }

    @Override
    public ScheduleDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_schedule, parent, false);
        return new ScheduleDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleDataAdapter.ViewHolder holder, int position) {
        holder.position_data = position;
        ScheduleModel scheduleModel = scheduleModelArrayList.get(position);
        holder.title_text.setText(scheduleModelArrayList.get(position).getDay());
        ScheduleChildAdapter scheduleChildAdapter = new ScheduleChildAdapter(mContext, scheduleModel.getEventModelArrayList());
        holder.rv_child.setLayoutManager(new LinearLayoutManager(mContext));
        holder.rv_child.setAdapter(scheduleChildAdapter);
        enableSwipeToDeleteAndUndo(scheduleChildAdapter,holder);
    }

    private void enableSwipeToDeleteAndUndo(final ScheduleChildAdapter mAdapter, ViewHolder holder) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(mContext) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final String item = mAdapter.getData().get(position).getEvent_name();

                mAdapter.removeItem(position);


                /*Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();*/

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView( holder.rv_child);
    }

    @Override
    public int getItemCount() {

        return scheduleModelArrayList.size();
    }
}