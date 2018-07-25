package com.techfreaks.starbucks;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;;
import android.content.Context;
import android.view.Window;


public class MyCardsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cards);
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
            actionBar.setCustomView(v);
        }
    }
}