package com.rontikeky.mycampus.otpuser.blucampuruser.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rontikeky.mycampus.otpuser.blucampuruser.Config.PrefHandler;
import com.rontikeky.mycampus.otpuser.blucampuruser.R;


public class SplashActivity extends AppCompatActivity {

    private static int mSplashInterval  =   2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                PrefHandler.init(SplashActivity.this);

                if (PrefHandler.getId() == ""){
                    Log.d("DEBUG 1", "TIDAK ADA");
                    Intent toLoginActivity   =   new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(toLoginActivity);
                }else{
                    Log.d("DEBUG 2", "ADA");
                    Intent toMainActivity   =   new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(toMainActivity);
                }


                SplashActivity.this.finish();
            }

        },mSplashInterval);
    }
}
