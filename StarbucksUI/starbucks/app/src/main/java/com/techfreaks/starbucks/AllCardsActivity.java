package com.techfreaks.starbucks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllCardsActivity extends AppCompatActivity {

    private String url = "http://10.0.2.2:8080/mycards/all";
    ListView  allCardsListView;
    Context context;
    RequestQueue requestQueue;
    ArrayList<JSONObject> allCardsArrayList;
    ArrayList<JSONObject> cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cards);
        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        setNavlistener(mBottomNav);
        context = this;
        allCardsListView = (ListView) findViewById(R.id.allCardsListView);
        requestQueue = Volley.newRequestQueue(this);
        this.getArrayListFromJSON(url);
    }


    private void getArrayListFromJSON(String url){
        cardsList = new ArrayList<JSONObject>();
        final String username;
        JsonArrayRequest request_json = new JsonArrayRequest (url, new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Process os success response
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                cardsList.add(response.getJSONObject(i));
                                System.out.println(response.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ListAdapter adapter = new ListAdapter( context , R.layout.allcards_list_layout, R.id.cardIdText , cardsList);
                        allCardsListView.setAdapter(adapter);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.toString());
            }
        }
        );
        // add the request object to the queue to be executed
        requestQueue.add(request_json);
    }

    private void setNavlistener(BottomNavigationView mBottomNav){
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_mycards: {
                        Intent intent = new Intent(AllCardsActivity.this, MyCardsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_menu: {
                        // Need to change the activity to menu acitivty here
                        Intent intent = new Intent(AllCardsActivity.this, AddCardActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_payment: {
                        // Need to change the activity to menu acitivty here
                        Intent intent = new Intent(AllCardsActivity.this, AllCardsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_user: {

                        break;
                    }
                    case R.id.navigation_settings: {
                        Intent intent = new Intent(AllCardsActivity.this, SettingsActivity.class);
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
