package com.techfreaks.starbucks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class ListAdapter extends ArrayAdapter<JSONObject>{

    private int vg;
    private ArrayList<JSONObject> list;
    private Context context;
    private String url = "http://10.0.2.2:8080/delete/";
    private String cardID;
    private RequestQueue requestQueue;
    private TextView cardId, cardBalance;
    private Button deleteCard;
    private View itemView;
    AlertDialog.Builder builder;

    public ListAdapter(Context context, int vg, int id, ArrayList<JSONObject> list){
        super(context,vg, id,list);
        this.context=context;
        this.vg=vg;
        this.list=list;
        requestQueue =  Volley.newRequestQueue(this.context);
        builder = new AlertDialog.Builder(this.context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(vg, parent, false);
        cardId = (TextView)itemView.findViewById(R.id.cardIdText);
        cardBalance=(TextView)itemView.findViewById(R.id.cardBalanceTextView);
        deleteCard=(Button) itemView.findViewById(R.id.deleteCard);

        try {
            cardID = list.get(position).getString("cardID");
            if (cardId != null) {
                String ID = "ID: " +cardID;
                cardId.setText(ID);
            }
            String balance = "$" + String.format("%.2f",  Double.parseDouble(list.get(position).getString("cardBalance")));
            cardBalance.setText(balance);
            deleteCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    url = url + cardID;
                    JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.DELETE, url, new JSONObject(),
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
                    builder.setMessage("The card has been deleted successfully!");
                    requestQueue.add(request_json);
                    AlertDialog alert = builder.create();
                    alert.setTitle("DELETE REST API call");
                    alert.show();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemView;
    }
}
