package com.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChiTietActivity extends AppCompatActivity {
    RecyclerView rv ;
    ImageView img;
    Button btnCall,btnMes;
    TextView txtName;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_chi_tiet);
        rv = findViewById(R.id.ct_rv);
        img = findViewById(R.id.ct_img);
        btnCall = findViewById(R.id.btn_call);
        btnMes = findViewById(R.id.btn_mes);
        txtName = findViewById(R.id.ct_name);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Contact c = bundle.getParcelable("contact");

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+c.getNumber()));
                context.startActivity(callIntent);
            }
        });
        btnMes.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));

        img.setImageBitmap(c.getPhoto());
        if(c.getName().equals("")){
            txtName.setText(c.getNumber());
        }
        else {
            txtName.setText(c.getName());
        }
        //Lay chuoi so
        String rs ="";
        for(int i=0;i<c.getNumber().length();i++){
            String id = Character.toString(c.getNumber().charAt(i));
            if(id.matches("\\b\\d\\b")) rs+=id;
        }
        ArrayList<Call_Log> list = readLog(rs);

        ChiTietAdapter adapter = new ChiTietAdapter(list,this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);

    }

    private ArrayList<Call_Log> readLog(String num) {
        ArrayList<Call_Log> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                String phNumber = cursor.getString(number);
                if(phNumber.equals(num)){
                    String callDate = cursor.getString(date); // tra ve kieu long
                    Date callDayTime = new Date(Long.valueOf(callDate)); //convert
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy"); //format
                    String time = dateFormat.format(callDayTime); //toString
                    int loai = cursor.getInt(type);

                    String callDuration = cursor.getString(duration); // giay
                    int dur = Integer.parseInt(callDuration);
                    int min = dur / 60;
                    int sec = dur % 60;

                    list.add(new Call_Log(phNumber,"["+min+" phút "+sec+" giây]",time,loai));
                }


            }while (cursor.moveToNext());
        }

        cursor.close();
        return list;

    }
}