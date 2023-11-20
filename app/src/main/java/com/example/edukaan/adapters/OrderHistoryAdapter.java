package com.example.edukaan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edukaan.R;
import com.example.edukaan.model.OrderHistory;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>{

    private ArrayList<OrderHistory> orderHistoryList;
    private Context context;

    public OrderHistoryAdapter(Context context, ArrayList<OrderHistory> orderHistoryList) {
        this.context = context;
        this.orderHistoryList = orderHistoryList;
    }

    @NonNull
    @Override
    public OrderHistoryAdapter.OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new OrderHistoryViewHolder(view);}

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.OrderHistoryViewHolder holder, int position) {
        OrderHistory modal = orderHistoryList.get(position);
        holder.Order_Code.setText("code: "+modal.getCode());
        holder.Order_Tax.setText("Tax: "+modal.getTax());
        holder.Order_Status.setText("Status: Paid");
        holder.Order_BuyerName.setText("Buyer: "+modal.getBuyer());
        holder.Order_Price.setText("Total: " + modal.getTotal_fees());
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView Order_Code, Order_BuyerName, Order_Status,Order_Tax,Order_Price;
        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            Order_Code=itemView.findViewById(R.id.order_code);
            Order_BuyerName=itemView.findViewById(R.id.order_buyerName);
            Order_Price=itemView.findViewById(R.id.order_price);
            Order_Status=itemView.findViewById(R.id.order_status);
            Order_Tax=itemView.findViewById(R.id.order_tax);
        }
    }
}
