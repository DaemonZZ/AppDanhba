package com.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter {
    private List<Call_Log> list;
    private Context context;


    public LogAdapter(List<Call_Log> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.log_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Call_Log log =list.get(position);
        ((ViewHolder)holder).txtNum.setText(log.getNumber());
        ((ViewHolder)holder).txtDur.setText(log.getDuration());
        ((ViewHolder)holder).txtTime.setText(log.getTime());
        ((ViewHolder)holder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int index) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+list.get(index).getNumber()));
                context.startActivity(callIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNum,txtDur,txtTime;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        public ViewHolder(View itemView) {
            super(itemView);

            txtNum = itemView.findViewById(R.id.txtNumber);
            txtDur = itemView.findViewById(R.id.txtDuration);
            txtTime = itemView.findViewById(R.id.txtTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
