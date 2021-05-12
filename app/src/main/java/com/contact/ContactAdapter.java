package com.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter {
    private List<Contact> list;
    private Context context;

    public ContactAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.contact_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contact contact =list.get(position);
        ((ViewHolder)holder).photo.setImageBitmap(contact.getPhoto());
        ((ViewHolder)holder).txtname.setText(contact.getName());
        ((ViewHolder)holder).txtnum.setText(contact.getNumber());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
       public ImageView photo;
       public TextView txtname,txtnum;


        public ViewHolder(View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            txtname = itemView.findViewById(R.id.name);
            txtnum = itemView.findViewById(R.id.num);
        }
    }
}
