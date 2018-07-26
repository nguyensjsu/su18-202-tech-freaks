package com.techfreaks.starbucks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONException;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


public class AddCardActivity extends AppCompatActivity {

    Button cancelBtn, insertBtn;
    EditText cardID, cardCode;
    CheckBox activeStatus;
    AlertDialog.Builder builder;
    Boolean activeCard = false;
    RequestQueue requestQueue;
    private String url = "http://10.0.2.2:8080/addcard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);

        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        setNavlistener(mBottomNav);

        cancelBtn = (Button) findViewById(R.id.cancelButton);
        insertBtn = (Button) findViewById(R.id.confrimButton);
        activeStatus = (CheckBox) findViewById(R.id.activeCheckBox);
        cardID = (EditText) findViewById(R.id.cardIdEditText);
        cardCode = (EditText) findViewById(R.id.cardCodeEditText);

        requestQueue = Volley.newRequestQueue(this);
        builder = new AlertDialog.Builder(this);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(activeStatus.isChecked()){
                     activeCard = true;
                 }
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("cardID", cardID.getText().toString());
                params.put("cardCode", cardCode.getText().toString());
                params.put("activeStatus", String.valueOf(activeCard));
                params.put("cardUserID", "1");
                params.put("cardBalance", "20.00");

                if(cardID.getText().toString() == "" || cardID.getText().toString().length() > 9 ){
                    builder.setMessage("Please enter 9 digit numeric ID");
                    AlertDialog alert = builder.create();
                    alert.setTitle("Invalid Card ID");
                    alert.show();
                }


                if(cardCode.getText().toString() == "" || cardCode.getText().toString().length() > 3 ){
                    builder.setMessage("Please enter 3 digit code.");
                    AlertDialog alert = builder.create();
                    alert.setTitle("Invalid Card Code");
                    alert.show();
                }

                JsonObjectRequest request_json = new JsonObjectRequest(url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //Process os success response
                                System.out.println(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.toString());
                    }
                });

                // add the request object to the queue to be executed
                System.out.println(request_json);
                requestQueue.add(request_json);
                builder.setMessage("New Card Inserted");
                AlertDialog alert = builder.create();
                alert.setTitle("Congrats!!!");
                alert.show();

                cardID.setText(null);
                cardCode.setText(null);
                finish();
            }
        });
     }


    private void setNavlistener(BottomNavigationView mBottomNav){
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_mycards: {
                        Intent intent = new Intent(AddCardActivity.this, MyCardsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_menu: {
                        // Need to change the activity to menu acitivty here
                        Intent intent = new Intent(AddCardActivity.this, AddCardActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_payment: {
                        // Need to change the activity to menu acitivty here
                        Intent intent = new Intent(AddCardActivity.this, AllCardsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_user: {

                        break;
                    }
                    case R.id.navigation_settings: {
                        Intent intent = new Intent(AddCardActivity.this, SettingsActivity.class);
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
