package com.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Adapter for RecyclerView

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
        ((ViewHolder)holder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int index) {
                Intent intent = new Intent(context,ChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("contact",contact);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       public ImageView photo;
       public TextView txtname,txtnum;
       private ItemClickListener itemClickListener;


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public ViewHolder(View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            txtname = itemView.findViewById(R.id.name);
            txtnum = itemView.findViewById(R.id.num);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }


}
