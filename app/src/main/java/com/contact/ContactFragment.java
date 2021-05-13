package com.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

public class ContactFragment extends Fragment {
    Context context;

    RecyclerView rv;
    ContactAdapter adapter;
    public ContactFragment() {
    }

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_contact,container,false);
        context = this.getContext();
        rv = view.findViewById(R.id.contact_rv);

        //get data
        ArrayList<Contact> list = readContact();

        adapter = new ContactAdapter(list,context);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);

        return view;
    }

    public ArrayList<Contact> readContact(){
        ArrayList<Contact> list = new ArrayList<>();
        String[] pr = {
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        ContentResolver resolver =this.getContext().getContentResolver();
        Cursor cur = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,pr,null,null,null);

        int photoIndex = cur.getColumnIndex(pr[0]);
        int nameIndex = cur.getColumnIndex(pr[1]);
        int numIndex = cur.getColumnIndex(pr[2]);

        cur.moveToFirst();
        do{
            String photoUri = cur.getString(photoIndex);
            String name = cur.getString(nameIndex);
            String num = cur.getString(numIndex);
            Bitmap photo;

            if(photoUri!=null){
                 photo= getPhoto(photoUri);
            }
            else {
                photo= BitmapFactory.decodeResource(context.getResources(), R.drawable.yasuo); //Hình mặc định
            }

            list.add(new Contact(photo,name,num));
        } while (cur.moveToNext());
        cur.close();

        return list;
    }

    private Bitmap getPhoto(String photoUri) {
        if(photoUri!=null){
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(photoUri));
                return bm;
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return null;
    }


}