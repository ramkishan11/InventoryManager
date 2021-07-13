package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.inventory.Adapter.RecyclerViewAdapterCart;
import com.example.inventory.Adapter.RecyclerViewAdapterProducts;
import com.example.inventory.Model.Cart;
import com.example.inventory.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class
CartActivity extends AppCompatActivity {
    List<Cart> cartList;
    private RecyclerViewAdapterCart cartAdapter;
    private RecyclerView r;
    private TextView totalCostTextview;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
//        Intent i=getIntent();
//        String productName=i.getStringExtra("productName");
//        String productQuantity=i.getStringExtra("productQuantity");
//        String productCost=i.getStringExtra("productCost");
//        cartList=new ArrayList<Cart>();
//        Cart c=new Cart(productName,Integer.valueOf(productQuantity),Integer.valueOf(productCost));
//        cartList.add(c);

        r=findViewById(R.id.recyclerViewCart);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter=new RecyclerViewAdapterCart(CartActivity.this);
        r.setAdapter(cartAdapter);

        totalCostTextview=findViewById(R.id.totalcost);
        totalCostTextview.setText(String.valueOf(DashboardFragment.totalCost));

        Button b=findViewById(R.id.sellButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Cart c:RecyclerViewAdapterProducts.cartList)
                {
                    String id=c.getProductId();
                   // Log.d("ramkishan","Id      "+id);
                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        DashboardFragment.totalCost=0;
    }
}