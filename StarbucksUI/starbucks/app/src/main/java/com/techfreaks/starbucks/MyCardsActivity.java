package com.techfreaks.starbucks;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCardsActivity extends AppCompatActivity {

    String url = "http://10.0.2.2:8080/mycards/active";
    RequestQueue requestQueue;
    AlertDialog.Builder builder;
    TextView cardBalanceText, cardIDText, currentTime;
    Button addCardButton, allCardsButton;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cards);



//      Get the latest time the REST API call is made
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        String formattedDate = df.format(c.getTime());
        currentTime = (TextView) findViewById(R.id.currentTimeText);
        currentTime.setText(formattedDate);
        cardBalanceText = (TextView) findViewById(R.id.cardBalanceText);
        cardIDText = (TextView) findViewById(R.id.cardNumberText);

//      Set the request queue to make REST API call and alert dailog to show error message
        requestQueue = Volley.newRequestQueue(this);
        builder = new AlertDialog.Builder(this);
        getActiveCard();

//        Get the button fro mthe screen
        addCardButton = (Button) findViewById(R.id.addCardButton);
        allCardsButton = (Button) findViewById(R.id.allCardsButton);


//        Set addcard button listener to navigate to AddCardActivity screen
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCardsActivity.this, AddCardActivity.class);
                startActivity(intent);
            }
        });

        allCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCardsActivity.this, AllCardsActivity.class);
                startActivity(intent);
            }
        });

    }


    private void getActiveCard(){
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Check the length of the JSON response (to see if there is any card)

                        if (response.length() > 0 ) {
                            // If there is a card then get the balance and display it on the screen
                                try {
                                    // For each repo, add a new line to our repo list
                                    String cardID = "Card #"+ response.get("cardID").toString();
                                    String cardBalance = "$"+ response.get("cardBalance").toString();
                                    cardBalanceText.setText(cardBalance);
                                    cardIDText.setText(cardID);

                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    builder.setMessage("Invalid JSON Object.");
                                    Log.e("Volley", "Invalid JSON Object.");
                                    AlertDialog alert = builder.create();
                                    alert.setTitle("Starbucks REST API response");
                                    alert.show();
                                }

                        } else {
                            // The user didn't have any repos.
                            builder.setMessage("No response found from REST API");
                            AlertDialog alert = builder.create();
                            alert.setTitle("Alert");
                            alert.show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our repo list.
                        builder.setMessage("Error while calling REST API");
                        Log.e("Volley", error.toString());
                        AlertDialog alert = builder.create();
                        alert.setTitle("Alert");
                        alert.show();
                    }
                }
        );
        requestQueue.add(arrReq);
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