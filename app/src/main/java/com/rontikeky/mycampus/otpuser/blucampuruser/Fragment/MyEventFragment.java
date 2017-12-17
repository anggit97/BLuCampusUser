package com.rontikeky.mycampus.otpuser.blucampuruser.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.rontikeky.mycampus.otpuser.blucampuruser.Adapter.EventAdapter;
import com.rontikeky.mycampus.otpuser.blucampuruser.Adapter.MyEventAdapter;
import com.rontikeky.mycampus.otpuser.blucampuruser.Config.FontHandler;
import com.rontikeky.mycampus.otpuser.blucampuruser.Config.PrefHandler;
import com.rontikeky.mycampus.otpuser.blucampuruser.R;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.EventResponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.MyEventReponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpuser.blucampuruser.RestApi.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anggit on 16/12/2017.
 */

public class MyEventFragment extends Fragment{

    //SINGLETON
    FontHandler fontHandler;
    Typeface fontMedium, fontBold;

    //LIST
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    MyEventAdapter mEventAdpater;

    //MODEL
    List<MyEventReponse.Content> eventResponses  = new ArrayList<>();

    public MyEventFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view   =   inflater.inflate(R.layout.fragment_myevent,container,false);

        //Inisialisasi
        mRecyclerView   =   (RecyclerView)view.findViewById(R.id.rvEvent);

        //Orientation Layout Recyclerview
        mLinearLayoutManager    =   new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mEventAdpater   =   new MyEventAdapter(eventResponses,getActivity());

        mRecyclerView.setAdapter(mEventAdpater);


        //Buat objek FontHandler
        fontHandler =   new FontHandler(getActivity());
        //Mendapatkan nilai font dari Class FontHandler
        fontMedium  =   fontHandler.getFont();
        fontBold    =   fontHandler.getFontBold();

        PrefHandler.init(getActivity());

        getMyEvents(PrefHandler.getId());

        return view;
    }

    private void getMyEvents(String idUser) {
        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        Call<MyEventReponse> call  =   client.getMyEvents(idUser);

        call.enqueue(new Callback<MyEventReponse>() {
            @Override
            public void onResponse(Call<MyEventReponse> call, Response<MyEventReponse> response) {
                if (response.isSuccessful()){
                    Log.d("DEBUG 1", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                    int i   =   0;
                    while (i < response.body().getContent().size()){
                        MyEventReponse.Content  data    =   new MyEventReponse.Content(response.body().content.get(i).getId(),response.body().content.get(i).getIdUser(),response.body().content.get(i).getJudulAcara(),response.body().content.get(i).getIsiAcara(),
                                response.body().content.get(i).getTempatAcara(),response.body().content.get(i).getContactPersonAcara(),response.body().content.get(i).getJumlahPeserta(),response.body().content.get(i).getTanggalAcara(),
                                response.body().content.get(i).getJamAcara(),response.body().content.get(i).getFotoAcara(),response.body().content.get(i).getStatusAcara());

                        eventResponses.add(data);
                        i++;
                    }

                    Log.d("DEBUG 5", String.valueOf(eventResponses.size()));

                    mEventAdpater.notifyDataSetChanged();
                }else{
                    Log.d("DEBUG 2", "GAGAL");
                }
            }

            @Override
            public void onFailure(Call<MyEventReponse> call, Throwable t) {
                Log.d("DEBUG 4", t.toString());
            }
        });
    }
}
