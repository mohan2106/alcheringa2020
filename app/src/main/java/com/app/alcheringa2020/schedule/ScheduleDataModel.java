package com.app.alcheringa2020.schedule;

import android.content.Context;

import com.app.alcheringa2020.external.CommonFunctions;
import com.app.alcheringa2020.events.model.ItemModel;
import com.app.alcheringa2020.events.model.JudgeModel;
import com.app.alcheringa2020.events.model.ProgrammeModel;
import com.app.alcheringa2020.events.model.RuleModel;
import com.app.alcheringa2020.schedule.model.EventModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class ScheduleDataModel {
    public static ArrayList<ScheduleModel> scheduleModelArrayList(Context context) {
        ArrayList<ScheduleModel> scheduleModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = CommonFunctions.loadAssetsJsonObj("schedule.json", context);
            JSONArray scheduleJsonArray = jsonObject.getJSONArray("schedule");
            for (int i = 0; i < scheduleJsonArray.length(); i++) {
                JSONObject sceduleJsonObject = scheduleJsonArray.getJSONObject(i);
                int id = sceduleJsonObject.getInt("id");
                String day = sceduleJsonObject.getString("day");
                String date = sceduleJsonObject.getString("date");
                ArrayList<EventModel> eventModelArrayList = new ArrayList<>();
                JSONArray eventArray = sceduleJsonObject.getJSONArray("event");
                for (int j = 0; j < eventArray.length(); j++) {
                    JSONObject eventObject = eventArray.getJSONObject(j);
                    int eventId = eventObject.getInt("id");
                    String event_name = eventObject.getString("event_name");
                    String venue = eventObject.getString("venue");
                    String start_time = eventObject.getString("start_time");
                    String end_time = eventObject.getString("end_time");

                    eventModelArrayList.add(new EventModel(eventId, event_name, venue, start_time, end_time));

                }
                scheduleModelArrayList.add(new ScheduleModel(id, day, date, eventModelArrayList));

            }
            return scheduleModelArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return scheduleModelArrayList;
    }
}
