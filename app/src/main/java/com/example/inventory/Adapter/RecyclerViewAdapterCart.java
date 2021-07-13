package com.example.inventory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.Model.Cart;
import com.example.inventory.Model.Products;
import com.example.inventory.R;

import java.util.List;

public class RecyclerViewAdapterCart extends RecyclerView.Adapter<RecyclerViewAdapterCart.CartViewHolder> {
    private Context context;


    public RecyclerViewAdapterCart(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewcart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart c=RecyclerViewAdapterProducts.cartList.get(position);
        holder.productName.setText(c.getProductName());
        holder.productQuantity.setText(String.valueOf(c.getProductQuantity()));
        holder.productCost.setText((String.valueOf(c.getProductCost())));
    }

    @Override
    public int getItemCount() {
        return RecyclerViewAdapterProducts.cartList.size();
    }
    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView productName;
        public TextView productQuantity;
        public TextView productCost;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.cartname);
            productQuantity=itemView.findViewById(R.id.cartquantity);
            productCost=itemView.findViewById(R.id.cartcost);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
