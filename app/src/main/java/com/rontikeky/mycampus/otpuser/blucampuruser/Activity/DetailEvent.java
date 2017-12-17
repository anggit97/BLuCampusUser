package com.rontikeky.mycampus.otpuser.blucampuruser.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.rontikeky.mycampus.otpuser.blucampuruser.Config.Constant;
import com.rontikeky.mycampus.otpuser.blucampuruser.Config.PrefHandler;
import com.rontikeky.mycampus.otpuser.blucampuruser.R;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.DaftarEventResponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.DetailEventResponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpuser.blucampuruser.RestApi.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEvent extends AppCompatActivity {

    ImageView  ivEvent;
    TextView judul, isi, tanggal, jam, tempat, contact_person, available, biaya, urlImage,tanggal_valid;
    Button btnDaftar;

    String idEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        judul = (TextView)findViewById(R.id.judulDetail);
        isi = (TextView)findViewById(R.id.isiDetail);
        tanggal = (TextView)findViewById(R.id.tanggalDetail);
        tanggal_valid = (TextView)findViewById(R.id.tanggalDetailValid);
        jam = (TextView)findViewById(R.id.jamDetail);
        ivEvent = (ImageView)findViewById(R.id.imgSeminarDetail);
        tempat = (TextView)findViewById(R.id.tempat);
        contact_person = (TextView)findViewById(R.id.contact_person);
        biaya = (TextView)findViewById(R.id.biaya);
        available = (TextView)findViewById(R.id.available);
        urlImage = (TextView)findViewById(R.id.urlImage);
        btnDaftar = (Button)findViewById(R.id.btnDaftar);

        PrefHandler.init(DetailEvent.this);

        Intent result= getIntent();
        Bundle extrasResult = result.getExtras();

        if(extrasResult!=null)
        {
            idEvent = extrasResult.getString(Constant.ID_EVENT_KEY);
        }


        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDaftarEvent(PrefHandler.getId(),idEvent);
            }
        });

        getDetailEvent(idEvent);
    }

    private void getDetailEvent(String idEvent){

        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        Call<DetailEventResponse> call    =   client.getDetailEvent(idEvent);

        call.enqueue(new Callback<DetailEventResponse>() {
            @Override
            public void onResponse(Call<DetailEventResponse> call, Response<DetailEventResponse> response) {
                if (response.isSuccessful()){
                    Log.d("DEBUG 1",new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                    Glide.with(DetailEvent.this).load("http://blucampus.anggitprayogo.com/"+response.body().getAcara().getFotoAcara()).placeholder(R.drawable.guest).error(R.drawable.guest).into(ivEvent);
                    tanggal.setText(response.body().getAcara().getTanggalAcara());
                    tanggal_valid.setText(response.body().getAcara().getTanggalAcara());
                    judul.setText(response.body().getAcara().getJudulAcara());
                    isi.setText(response.body().getAcara().getIsiAcara());
                    tempat.setText(response.body().getAcara().getTempatAcara());
                    contact_person.setText(response.body().getAcara().getContactPersonAcara());
                    available.setText(response.body().getAcara().getJumlahPeserta());
                    biaya.setText("GRATIS");
                    jam.setText(response.body().getAcara().getJamAcara());

                }else{
                    Log.d("DEBUG 2","Gagal");
                }
            }

            @Override
            public void onFailure(Call<DetailEventResponse> call, Throwable t) {
                Log.d("DEBUG 3",t.toString());
            }
        });
    }

    private void doDaftarEvent(String idUser, String idEvent){
        BlucampusClient client  =   ServiceGenerator.createService(BlucampusClient.class);
        Call<DaftarEventResponse>   call    =   client.doDaftarEvent(idEvent,idUser);

        call.enqueue(new Callback<DaftarEventResponse>() {
            @Override
            public void onResponse(Call<DaftarEventResponse> call, Response<DaftarEventResponse> response) {
                if (response.isSuccessful()){
                    Log.d("DEBUG 4",new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                    Toast.makeText(DetailEvent.this,"Berhasil Daftar Event", Toast.LENGTH_LONG).show();
                    btnDaftar.setText("SUDAH TERDAFTAR");
                    btnDaftar.setBackgroundColor(Color.parseColor("#f1c40f"));

                }else{
                    Log.d("DEBUG 5","Gagal");
                }
            }

            @Override
            public void onFailure(Call<DaftarEventResponse> call, Throwable t) {
                Log.d("DEBUG 6",t.toString());
            }
        });
    }
}
