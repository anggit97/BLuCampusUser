package com.rontikeky.mycampus.otpuser.blucampuruser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rontikeky.mycampus.otpuser.blucampuruser.Activity.DetailEvent;
import com.rontikeky.mycampus.otpuser.blucampuruser.Activity.DetailMyEventActivity;
import com.rontikeky.mycampus.otpuser.blucampuruser.Config.Constant;
import com.rontikeky.mycampus.otpuser.blucampuruser.R;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.EventResponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.MyEventReponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anggit on 16/12/2017.
 */

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.MyEventHolder>{

    List<MyEventReponse.Content> eventResponses  =   new ArrayList<>();
    Context context;

    public MyEventAdapter(List<MyEventReponse.Content> eventResponses, Context context) {
        this.eventResponses =   eventResponses;
        this.context        =   context;
    }

    public class MyEventHolder extends RecyclerView.ViewHolder {

        TextView tvJudul, tvTanggal, tvLokasi, tvIdEvent;
        ImageView ivGambar;

        public MyEventHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toDetailPresenceEvent   =   new Intent(context, DetailMyEventActivity.class);
                    toDetailPresenceEvent.putExtra(Constant.ID_EVENT_KEY,tvIdEvent.getText().toString());
                    view.getContext().startActivity(toDetailPresenceEvent);
                }
            });

            tvJudul     =   (TextView)itemView.findViewById(R.id.tvJudulEvent);
            tvTanggal   =   (TextView)itemView.findViewById(R.id.tvTanggalAcara);
            tvLokasi    =   (TextView)itemView.findViewById(R.id.tvTempatAcara);
            tvIdEvent   =   (TextView)itemView.findViewById(R.id.tvIdEventEO);
            ivGambar    =   (ImageView)itemView.findViewById(R.id.ivEvent);
        }
    }

    @Override
    public MyEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View    view    = LayoutInflater.from(context).inflate(R.layout.single_myevent,parent,false);

        return new MyEventHolder(view);
    }

    @Override
    public void onBindViewHolder(MyEventHolder holder, int position) {
        MyEventReponse.Content model   =   eventResponses.get(position);


        try {
            holder.tvJudul.setText(model.getJudulAcara());
            holder.tvTanggal.setText(model.getTanggalAcara());
            holder.tvLokasi.setText(model.getTempatAcara());
            holder.tvIdEvent.setText(String.valueOf(model.getId()));
            Glide.with(context).load("http://blucampus.anggitprayogo.com/"+model.getFotoAcara()).placeholder(R.drawable.guest).error(R.drawable.guest).into(holder.ivGambar);
        } catch (Exception e) {
            Log.d("DEBUG ID", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        if (eventResponses == null) return 0;

        return eventResponses.size();
    }


}
