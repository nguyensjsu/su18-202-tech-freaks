package com.techfreaks.starbucks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.getMenu().findItem(R.id.navigation_settings).setChecked(true);
        setNavlistener(mBottomNav);
    }


    private void setNavlistener(BottomNavigationView mBottomNav){
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_mycards: {
                        Intent intent = new Intent(SettingsActivity.this, MyCardsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_menu: {
                        // Need to change the activity to menu acitivty here
                        Intent intent = new Intent(SettingsActivity.this, AddCardActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_payment: {
                        // Need to change the activity to menu acitivty here
                        Intent intent = new Intent(SettingsActivity.this, AllCardsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_user: {

                        break;
                    }
                    case R.id.navigation_settings: {
                        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                return true;
            }
        });
    }

    @SuppressLint("InflateParams")
    private void setupActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = null;
            if (inflator != null) {
                v = inflator.inflate(R.layout.custom_actionbar, null);
            }
            ImageButton refresh = v.findViewById(R.id.refreshImageButton);
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            actionBar.setCustomView(v);
        }
    }
}
