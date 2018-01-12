package com.rontikeky.mycampus.otpuser.blucampuruser.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.rontikeky.mycampus.otpuser.blucampuruser.Config.Constant;
import com.rontikeky.mycampus.otpuser.blucampuruser.Config.PrefHandler;
import com.rontikeky.mycampus.otpuser.blucampuruser.R;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.MyEventDetailReponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.Response.MyEventReponse;
import com.rontikeky.mycampus.otpuser.blucampuruser.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpuser.blucampuruser.RestApi.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMyEventActivity extends AppCompatActivity {

    ImageView ivEvent,ivQRCode;
    TextView judul, isi, tanggal, jam, tempat, contact_person, available, biaya, urlImage,tanggal_valid;
    Button btnDaftar;

    String idEvent;

    MultiFormatWriter multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_my_event);


        //Inisialisasi
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
        ivQRCode    = (ImageView)findViewById(R.id.btnQrCode);


        PrefHandler.init(DetailMyEventActivity.this);

        Intent result= getIntent();
        Bundle extrasResult = result.getExtras();

        multi = new MultiFormatWriter();

        if(extrasResult!=null)
        {
            idEvent = extrasResult.getString(Constant.ID_EVENT_KEY);
        }

        btnDaftar.setText("Anda Sudah terdaftar");
        btnDaftar.setBackgroundColor(Color.parseColor("#f1c40f"));
        btnDaftar.setEnabled(false);

        getDetailEvent(idEvent,PrefHandler.getId());
    }

    private void getDetailEvent(String idEvent, String idUser) {
        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        Call<MyEventDetailReponse>    call    =   client.getDetailMyEvent(idEvent,idUser);

        call.enqueue(new Callback<MyEventDetailReponse>() {
            @Override
            public void onResponse(Call<MyEventDetailReponse> call, Response<MyEventDetailReponse> response) {
                if (response.isSuccessful()){
                    Log.d("DEBUG 1",new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                    Glide.with(DetailMyEventActivity.this).load(response.body().getFotoAcara()).placeholder(R.drawable.guest).error(R.drawable.guest).into(ivEvent);
                    tanggal.setText(response.body().getTanggalAcara());
                    tanggal_valid.setText(response.body().getTanggalAcara());
                    judul.setText(response.body().getJudulAcara());
                    isi.setText(response.body().getIsiAcara());
                    tempat.setText(response.body().getTempatAcara());
                    contact_person.setText(response.body().getContactPersonAcara());
                    available.setText(response.body().getJumlahPeserta());
                    biaya.setText("GRATIS");
                    jam.setText(response.body().getJamAcara());

                    generateQrCode(response.body().getKode().getKode());
                }else{
                    Log.d("DEBUG 2","Gagal");
                }
            }

            @Override
            public void onFailure(Call<MyEventDetailReponse> call, Throwable t) {
                Log.d("DEBUG 3",t.toString());
            }
        });
    }

    private void generateQrCode(String kode){
        try{
            BitMatrix bitMatrix = multi.encode(kode, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder encode = new BarcodeEncoder();
            Bitmap bitmap = encode.createBitmap(bitMatrix);
            ivQRCode.setImageBitmap(bitmap);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
