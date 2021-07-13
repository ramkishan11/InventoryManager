package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory.Adapter.RecyclerViewAdapterProducts;
import com.example.inventory.Data.DbHandler;
import com.example.inventory.Model.Products;

import java.util.ArrayList;
import java.util.List;

public class Addproduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        EditText pid=findViewById(R.id.productid);
        EditText pname=findViewById(R.id.productname);
        EditText pcost=findViewById(R.id.productcost);
        EditText pquantity=findViewById(R.id.productquantity);
        Button b=findViewById(R.id.submitproduct);

        DbHandler db=new DbHandler(Addproduct.this);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String productid;
                    String productname;
                    int productcost;
                    int productquantity;
                    productid=pid.getText().toString();
                    productname=pname.getText().toString();


                    if(productid.equals("") || productname.equals("") || pcost.getText().toString().equals("") || pquantity.getText().toString().equals(""))
                    {
                        Toast.makeText(Addproduct.this,"ENTER ALL THE FIELDS",Toast.LENGTH_LONG).show();
                    }
                    else {
                        productcost = Integer.parseInt(pcost.getText().toString());
                        productquantity = Integer.parseInt(pquantity.getText().toString());

                        Products p = new Products(productid, productname, productcost, productquantity);
                        db.addProduct(p);
                        List<Products> listProducts=db.getAllProducts();
                        for(Products pr:listProducts)
                        {
                           Log.d("ramkishan","id \n"+pr.getProductid()+"name \n"+pr.getProductName()+"quantity \n"+pr.getProductQuantity()+"cost \n"+pr.getProductCost() );

                        }



                        finish();
                    }
                }
            });




    }
}