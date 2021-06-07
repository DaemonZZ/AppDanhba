package com.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChiTietAdapter extends RecyclerView.Adapter {
    private Context context ;
    private ArrayList<Call_Log> list;

    public ChiTietAdapter(ArrayList<Call_Log> list, Context context){
        this.list = list;
        this.context = context;
    }

    public ChiTietAdapter() {
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.ct_rv_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Call_Log log = list.get(position);
        String type="";
        switch (log.getType()){
            case 1:
                type = "Gọi tới";
                break;
            case 2:
                type = "Gọi đi";
                break;
            case 3:
                type="Gọi nhỡ";
                break;
            case 4:
                type="Voice mail";
                break;
            default:
                type="Khác";
                break;
        }
        ((ViewHolder)holder).txtName.setText(log.getTime());
        ((ViewHolder)holder).txtType.setText(type);
        ((ViewHolder)holder).txtTime.setText("Thời gian gọi");
        ((ViewHolder)holder).txtDur.setText(log.getDuration());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener itemClickListener;
        public TextView txtName,txtTime,txtType,txtDur;


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.ct_it_name);
            txtType = itemView.findViewById(R.id.ct_it_type);
            txtTime = itemView.findViewById(R.id.ct_it_time);
            txtDur = itemView.findViewById(R.id.ct_it_dur);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
