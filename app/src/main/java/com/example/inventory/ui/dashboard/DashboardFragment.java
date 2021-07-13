package com.example.inventory.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.Adapter.RecyclerViewAdapterProducts;
import com.example.inventory.Addproduct;
import com.example.inventory.CartActivity;
import com.example.inventory.Data.DbHandler;
import com.example.inventory.Model.Cart;
import com.example.inventory.Model.Products;
import com.example.inventory.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class
DashboardFragment extends Fragment {
    private RecyclerView recyclerViewProducts;
    private RecyclerViewAdapterProducts recyclerViewAdapterProducts;
    private List<Products> productsArrayList;
    private ArrayAdapter<String> arrayAdapterProducts;
    private DashboardViewModel dashboardViewModel;
    public static int totalCost=0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        FloatingActionButton  b=root.findViewById(R.id.floatingActionButton);
        FloatingActionButton  c=root.findViewById(R.id.cartButton);
        recyclerViewProducts=root.findViewById(R.id.recylerViewProducts);

        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity().getApplicationContext(), Addproduct.class);
                getActivity().startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               for(Cart c:RecyclerViewAdapterProducts.cartList)
               {
                   totalCost+=c.getProductCost();
                   Log.d("ramkishan","\nTotal cost  "+totalCost);
               }
                Intent i=new Intent(getActivity().getApplicationContext(), CartActivity.class);
                getActivity().startActivity(i);
            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        DbHandler db=new DbHandler(getContext());
        productsArrayList=new ArrayList<>();
        //db.deleteContact("1");
        List<Products> listProducts=db.getAllProducts();
        for(Products pr:listProducts)
        {

            productsArrayList.add(pr);
        }
//        for(Products pr:productsArrayList)
//        {
//            Log.d("ramkishan","id  "+pr.getProductid()+"\n name  "+pr.getProductName()+" \n quantity   "+pr.getProductQuantity()+"\n cost    "+pr.getProductCost() );
//        }
        recyclerViewAdapterProducts=new RecyclerViewAdapterProducts(getContext(),productsArrayList);
        recyclerViewProducts.setAdapter(recyclerViewAdapterProducts);
    }
}