package com.example.edukaan.activities;

import static com.example.edukaan.utils.Constants.ORDER_HISTORY_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.edukaan.R;
import com.example.edukaan.adapters.OrderHistoryAdapter;
import com.example.edukaan.databinding.ActivityMainBinding;
import com.example.edukaan.databinding.ActivityOrderHistoryBinding;
import com.example.edukaan.model.Category;
import com.example.edukaan.model.OrderHistory;
import com.example.edukaan.model.Product;
import com.example.edukaan.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection;

public class Order_History_Activity extends AppCompatActivity {

    private RecyclerView orderRecycler;

    private OrderHistoryAdapter orderAdapter;

    FirebaseAuth mauth;

    private ArrayList<OrderHistory> orderList;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        mauth = FirebaseAuth.getInstance();

        orderList = new ArrayList<>();
        orderRecycler = findViewById(R.id.orderHistoryRecycler);
        orderAdapter = new OrderHistoryAdapter(this, orderList);
        progressBar = findViewById(R.id.idPB);

        getOrderHistory();
    }

    private void getOrderHistory() {
        String email=mauth.getCurrentUser().getEmail();
        final String token = "e6e061838856bf47e1de730719fb2609";
        RequestQueue queue = Volley.newRequestQueue(Order_History_Activity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ORDER_HISTORY_URL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                orderRecycler.setVisibility(View.VISIBLE);
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject responseObj = response.getJSONObject(i);
                            String code=responseObj.getString("code");
                            String buyer=responseObj.getString("buyer");
                            String status=responseObj.getString("payment_status");
                            int total_fees=responseObj.getInt("total_fees");
                            int tax=responseObj.getInt("tax");
                            String userid=responseObj.getString("userId");
                            if(userid!=null && userid.equalsIgnoreCase(email))
                            {
                                orderList.add(new OrderHistory(code,buyer,status,userid,total_fees,tax));
                                buildRecyclerView();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(Order_History_Activity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Order_History_Activity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("token", token);
                return headers;
            }
        };

        queue.add(jsonArrayRequest);
    }


    private void buildRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        orderRecycler.setHasFixedSize(true);
        orderRecycler.setLayoutManager(manager);
        orderRecycler.setAdapter(orderAdapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}