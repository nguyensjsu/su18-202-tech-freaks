package com.techfreaks.starbucks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuActivity extends AppCompatActivity {

    String placeOrderURL = "http://192.168.1.7:8080/placeOrder";

    RequestQueue requestQueue;
    AlertDialog.Builder builder;
    CheckBox itemOne, itemTwo, itemThree, itemFour, itemFive;
    TextView priceOne, priceTwo, priceThree, priceFour, priceFive;
    Button orderBtn;

    int itemNumber=0;
    ArrayList<String> itemNames;
    ArrayList<String> itemPrices;
    RelativeLayout parentLayout;
    ArrayList<String> params;
    String t1;
    String cardId;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        requestQueue = Volley.newRequestQueue(this);
        builder = new AlertDialog.Builder(this);
        orderBtn = (Button) findViewById(R.id.placeOrderBtn);

        Intent intent = getIntent();
        cardId = intent.getStringExtra("cardId");
        itemNames=new ArrayList<String>();
        itemPrices=new ArrayList<String>();
        params=new ArrayList<String>();
        parentLayout = (RelativeLayout) findViewById(R.id.itemsView);

        //Get Items List from REST API.
        getItemsListAndPrices();
        System.out.println("List Items Received are"+itemNames.size()+"Prices received are"+itemPrices.size());

        try {

            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    findCheckBoxStatus(getApplicationContext(),parentLayout);
                    System.out.println("Status of All Checked Items is : "+params);


                    //Post the Selected Items to Web Service.
                    JSONObject request = new JSONObject();
                    // String cardId="111111111";
                    String itemListContent=android.text.TextUtils.join(",", params);

                    try {
                        request.put("cardId",cardId);
                        request.put("itemList",itemListContent);
                        System.out.println("Request Body is"+request.toString());
                        MakePost(request);

                    } catch (JSONException e) {
                        System.out.println(e.getMessage());

                        e.printStackTrace();

                    }





                    builder.setMessage("Order Placed Successfully");
                    AlertDialog alert = builder.create();

                    finish();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveToOrderDetails(String orderNumber)
    {
        Intent orderDetailsIntent=new Intent(getApplicationContext(),OrderDetailsActivity.class);
        orderDetailsIntent.putExtra("orderNumber", orderNumber);
        startActivity(orderDetailsIntent);


    }

    public void findCheckBoxStatus(Context context, View v){
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    //you can recursively call this method
                    findCheckBoxStatus(context, child);
                }
            } else if (v instanceof CheckBox)
            {
                System.out.println("CheckBox Found");
                //do whatever you want ...
                CheckBox cg = (CheckBox) v;
                System.out.println("CheckBox text is"+cg.getText());

                if(cg.isChecked()){
                    params.add(cg.getText().toString());
                }else{
                    if(params.contains(cg.getText().toString()))
                        params.remove(cg.getText().toString());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String mJSONURLString="http://192.168.1.7:8080/getMenuItems";

    private void getItemsListAndPrices(){
        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, mJSONURLString,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of the JSON response (to see if there is any card)

                        if (response.length() > 0 ) {
                            // If there is a card then get the balance and display it on the screen
                            System.out.println("RESt API Response is"+response);
                            itemNumber=response.length();
                            for(int a=0;a<response.length();a++)
                            {
                                try {
                                    itemNames.add(response.getJSONObject(a).get("itemName").toString());
                                    itemPrices.add(response.getJSONObject(a).get("itemPrice").toString());
                                    System.out.println("Item Added");


                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    if(a==0)
                                        params.topMargin = 280;
                                    else if(a==1)
                                        params.topMargin = 280+150;
                                    else params.topMargin = 280 +(a*150);



                                    LinearLayout.LayoutParams paramsPrice = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    if(a==0)
                                        paramsPrice.topMargin = 280;
                                    else if(a==1)
                                        paramsPrice.topMargin = 280+150;
                                    else paramsPrice.topMargin = 280 +(a*150);
                                    paramsPrice.leftMargin=800;

                                    CheckBox cb = new CheckBox(getApplicationContext());
                                    cb.setText(itemNames.get(a));
                                    cb.setTextColor(Color.rgb(128,0,0));
                                    cb.setTextSize(25);
                                    cb.setWidth(700);
                                    final String t2=cb.getText().toString();


                                    //cb.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                                    //cb.setWidth(WRAP.C);


                                    TextView valueTV = new TextView(getApplicationContext());
                                    valueTV.setText(itemPrices.get(a));
                                    valueTV.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.FILL_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));
                                    valueTV.setTextColor(Color.rgb(128,0,0));
                                    valueTV.setTextSize(25);
                                    valueTV.setWidth(200);

                                    //((LinearLayout) linearLayout).addView(valueTV);
                                    parentLayout.addView(cb, params);
                                    parentLayout.addView(valueTV, paramsPrice);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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

    boolean orderStatus=false;
    public void MakePost(final JSONObject jsonobj)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, placeOrderURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println("Response is"+response);
                        JSONObject jsonO=new JSONObject();
                        try {
                            jsonO=new JSONObject(response);
                            System.out.println("Order Number from Response is"+jsonO.get("orderNumber"));
                            String orderNumber=jsonO.get("orderNumber").toString();


                            if(orderNumber.equals("0")){
                                moveToOrderDetails("33");
                            }else{
                                moveToOrderDetails(orderNumber);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error is"+error.getMessage());

            }
        }){


            @Override
            public byte[] getBody() {
                System.out.println("Request Body Set is"+jsonobj.toString());

                return jsonobj.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestQueue.add(stringRequest);

    }


}



