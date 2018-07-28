package com.techfreaks.starbucks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {


    String getOrderURL = "http://192.168.1.7:8080/getOrder/";
    String cancelOrderURL = "http://192.168.1.7:8080/cancelOrder/";

    //String url = "http://10.0.2.2:8080/placeOrder";

    RequestQueue requestQueue;
    AlertDialog.Builder builder;
    Button cancelOrderBtn;
    TextView totalPrice;

    int itemNumber = 0;
    ArrayList < String > itemNames;
    ArrayList < String > itemPrices;
    RelativeLayout parentLayout;
    ArrayList < String > params;
    String orderNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        orderNumber = i.getStringExtra("orderNumber");
        getOrderURL += orderNumber;
        cancelOrderURL+=orderNumber;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        requestQueue = Volley.newRequestQueue(this);
        builder = new AlertDialog.Builder(this);
        cancelOrderBtn = (Button) findViewById(R.id.cancelOrder);
        totalPrice=(TextView)findViewById(R.id.totalPrice);

        itemNames = new ArrayList < String > ();
        itemPrices = new ArrayList < String > ();
        params = new ArrayList < String > ();
        parentLayout = (RelativeLayout) findViewById(R.id.ordersView);

        //Get Items List from REST API.
        getOrderListAndPrices();
        System.out.println("List Items Received are" + itemNames.size() + "Prices received are" + itemPrices.size());

        try {

            cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Cancelling Order: " +orderNumber);
                    CancelOrder(orderNumber);
                    AlertDialog alert = builder.create();
                    alert.setTitle("Order cancelled");
                    alert.show();

                    finish();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void getOrderListAndPrices() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, getOrderURL, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject res) {

                        try {
                            // Check the length of the JSON response (to see if there is any card)
                            JSONObject resFormatted = new JSONObject(res.toString());
                            JSONArray response = resFormatted.getJSONArray("orderedItems");
                            if (response.length() > 0) {
                                // If there is a card then get the balance and display it on the screen
                                System.out.println("RESt API Response is" + response);
                                itemNumber = response.length();
                                for (int a = 0; a < response.length(); a++) {
                                    itemNames.add(response.getJSONObject(a).get("itemName").toString());
                                    itemPrices.add(response.getJSONObject(a).get("itemPrice").toString());
                                    System.out.println("Item Added");
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    if (a == 0)
                                        params.topMargin = 280;
                                    else if (a == 1)
                                        params.topMargin = 280 + 150;
                                    else params.topMargin = 280 + (a * 150);


                                    LinearLayout.LayoutParams paramsPrice = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    if (a == 0)
                                        paramsPrice.topMargin = 280;
                                    else if (a == 1)
                                        paramsPrice.topMargin = 280 + 150;
                                    else paramsPrice.topMargin = 280 + (a * 150);
                                    paramsPrice.leftMargin = 800;

                                    TextView cb = new TextView(getApplicationContext());
                                    cb.setText(itemNames.get(a));
                                    cb.setTextColor(Color.rgb(128, 0, 0));
                                    cb.setTextSize(25);
                                    cb.setWidth(700);
                                    final String t2 = cb.getText().toString();


                                    TextView valueTV = new TextView(getApplicationContext());
                                    valueTV.setText(itemPrices.get(a));
                                    valueTV.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.FILL_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));
                                    valueTV.setTextColor(Color.rgb(128, 0, 0));
                                    valueTV.setTextSize(25);
                                    valueTV.setWidth(200);

                                    //((LinearLayout) linearLayout).addView(valueTV);
                                    parentLayout.addView(cb, params);
                                    parentLayout.addView(valueTV, paramsPrice);
                                }

                                //Set the Total Price here.
                                totalPrice.setText(res.get("totalPrice").toString());
                            } else {
                                // The user didn't have any repos.
                                builder.setMessage("No response found from REST API");
                            }

                        } catch (JSONException error) {
                            System.out.println("JSOn Exception" + error.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        requestQueue.add(jsonObjectRequest);

    }
    boolean cancelStatus=false;

    public void CancelOrder(String orderNumber)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, cancelOrderURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println("Response is"+response);
                        if(response.equals("true")){cancelStatus=true;
                            if(cancelStatus)
                                System.out.println("cancelStatus is"+cancelStatus);
                            else{

                                //Show Some Internal Server Error.
                                System.out.println("cancelStatus is"+cancelStatus);

                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error is"+error.getMessage());

            }
        }){

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestQueue.add(stringRequest);






    }

}