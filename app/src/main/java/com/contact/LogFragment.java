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
    ArrayList<Call_Log> list = new ArrayList<>();
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
        context = this.getContext();
        rv = view.findViewById(R.id.log_rv);
        getCallDetails();
        return view;
    }
    private  void getCallDetails() {
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int num = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(num);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/mm/yyyy");
            String time = dateFormat.format(callDayTime);

            String callDuration = cursor.getString(duration);
            int dur = Integer.parseInt(callDuration);
            int min = dur / 60;
            int sec = dur % 60;

            list.add(new Call_Log(phNumber,"["+min+":"+sec+"]",time));

        }
        cursor.close();
        adapter = new LogAdapter(list,context);
        rv.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        rv.setLayoutManager(lm);
    }
}