package com.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
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
    ArrayList<Contact> list = new ArrayList<>();
    RecyclerView rv;
    ContactAdapter adapter;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    public ContactFragment() {
    }

    // TODO: Rename and change types and number of parameters
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

        readContact();
        return view;
    }

    public void readContact(){
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
            Bitmap photo = getPhoto(photoUri);

            list.add(new Contact(photo,name,num));
        } while (cur.moveToNext());
        cur.close();

        adapter = new ContactAdapter(list,context);

        LinearLayoutManager lm = new LinearLayoutManager(context);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);
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