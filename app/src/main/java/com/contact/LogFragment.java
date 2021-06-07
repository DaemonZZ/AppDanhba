package com.contact;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LogFragment extends Fragment {
    Context context;

    RecyclerView rv;
    LogAdapter adapter;

    public LogFragment() {
    }

    public static LogFragment newInstance(String param1, String param2) {
        LogFragment fragment = new LogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_log, container, false);
        context = this.getContext(); //context?
        rv = view.findViewById(R.id.log_rv);

        //get data
        ArrayList<Call_Log> list = getCallDetails();

        adapter = new LogAdapter(list,context);
        rv.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        rv.setLayoutManager(lm);

        return view;
    }
    private  ArrayList<Call_Log> getCallDetails() {
        ArrayList<Call_Log> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int num = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(num);

            String callDate = cursor.getString(date); // tra ve kieu long
            Date callDayTime = new Date(Long.valueOf(callDate)); //convert
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MMM/yyyy"); //format
            String time = dateFormat.format(callDayTime); //toString

            String callDuration = cursor.getString(duration); // giay
            int dur = Integer.parseInt(callDuration);
            int min = dur / 60;
            int sec = dur % 60;

            list.add(new Call_Log(phNumber,"["+min+":"+sec+"]",time,1));

        }
        cursor.close();
        return list;

    }
}