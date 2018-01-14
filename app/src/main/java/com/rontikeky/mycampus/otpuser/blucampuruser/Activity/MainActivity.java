package com.rontikeky.mycampus.otpuser.blucampuruser.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rontikeky.mycampus.otpuser.blucampuruser.Config.PrefHandler;
import com.rontikeky.mycampus.otpuser.blucampuruser.Fragment.EventFragment;
import com.rontikeky.mycampus.otpuser.blucampuruser.Fragment.MyEventFragment;
import com.rontikeky.mycampus.otpuser.blucampuruser.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FragmentManager fragmentManager;
    Fragment fragment;

    TextView tvEmailHeader, tvNameHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi
        Toolbar toolbar =   (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("BLuCampus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        PrefHandler.init(this);

        //DRAWER TOGGLE
        DrawerLayout drawerLayout   =   (DrawerLayout)findViewById(R.id.drawer_layout);
        //final ActionBarDrawerToggle toggle  =   new ActionBarDrawerToggle(this,drawerLayout,R.string.navigatio_drawer_open,R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigatio_drawer_open, R.string.navigation_drawer_close)
        {

            public void onDrawerClosed(View view)
            {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView)
            {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //Drawer
        NavigationView navigationView   =   (NavigationView)findViewById(R.id.nav_view);
        View    view    =   navigationView.getHeaderView(0);
        Menu menu    =   navigationView.getMenu();
//        MenuItem    target  =   menu.findItem(R.id.)

        tvEmailHeader   =   (TextView)view.findViewById(R.id.emailHeader);
        tvNameHeader    =   (TextView)view.findViewById(R.id.nameHeader);

        tvEmailHeader.setText(PrefHandler.getEmailKey());
        tvNameHeader.setText(PrefHandler.getNameKey());

        navigationView.setCheckedItem(R.id.nav_events);

        //Listerner untuk Navigation drawer item
        navigationView.setNavigationItemSelectedListener(this);

        //Set Default Fragment yang ditampilkan
        fragmentManager =   getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container,new EventFragment()).commit();

    }

    //ONCLICK ITEM NAVIGATION DRAWER
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavigationView  navigationView  = (NavigationView)findViewById(R.id.nav_view);
        Menu    menu    =   navigationView.getMenu();
        int id  =   item.getItemId();

        fragment    =   null;

        if (id  ==  R.id.nav_events){
            fragment    =   new EventFragment();
        }else if(id ==  R.id.nav_registered_event){
            fragment    =   new MyEventFragment();
        }else if(id ==  R.id.nav_logout){
            PrefHandler.setLogout();
            Intent toLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
            toLoginActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(toLoginActivity);
            Toast.makeText(MainActivity.this, "Anda berhasil logout!",Toast.LENGTH_SHORT).show();
        }

        if (fragment    !=  null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        }

        DrawerLayout    drawerLayout    =   (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}
