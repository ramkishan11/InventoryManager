package com.example.inventory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.CartActivity;
import com.example.inventory.MainActivity;
import com.example.inventory.Model.Cart;
import com.example.inventory.Model.Products;
import com.example.inventory.R;
import com.example.inventory.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;

public class RecyclerViewAdapterProducts extends RecyclerView.Adapter<RecyclerViewAdapterProducts.ProductsViewHolder> {
    private Context context;
    private List<Products> productsList;
    public static List<Cart> cartList=new ArrayList<Cart>();
    public RecyclerViewAdapterProducts(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }
    //oncreate view holder asks which method is to be displayed repeatedly
    //get the single card as viewholder object.
    @NonNull
    @Override
    public RecyclerViewAdapterProducts.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewproducts,parent,false);
        return new ProductsViewHolder(view);
    }
    //this method specifies what happens after we get the view holder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterProducts.ProductsViewHolder holder, int position) {

        Products productt=productsList.get(position);
        Log.d("ramkishan","Productslist "+productt.getProductName());
           holder.prname.setText(productt.getProductName());
           holder.prcost.setText(String.valueOf(productt.getProductCost()));
           holder.prinstock.setText(String.valueOf(productt.getProductQuantity()));
    }
    //how many object
    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView prname;
        public TextView prcost;
        public TextView prinstock;
        public Button prmovetocart;
        public ImageButton quantityup;
        public ImageButton quantitydown;
        public TextView prquantity;
        public Button prbuy;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            prname=itemView.findViewById(R.id.prname);
            prcost=itemView.findViewById(R.id.prcost);
            prinstock=itemView.findViewById(R.id.prquantityinstock);
            prmovetocart=itemView.findViewById(R.id.prmovetocart);
            quantityup=itemView.findViewById(R.id.quantityup);
            quantitydown=itemView.findViewById(R.id.quantitydown);
            prquantity=itemView.findViewById(R.id.prquantity);
            prbuy=itemView.findViewById(R.id.prbuy);

            prmovetocart.setOnClickListener(this);
            quantityup.setOnClickListener(this);
            quantitydown.setOnClickListener(this);
            prbuy.setOnClickListener(this);
            
        }

        @Override
        public void onClick(View view) {
            if(view.getId()==quantityup.getId())
            {
                //Log.d("ramkishan","Quantity up");
                int quant=Integer.valueOf(prquantity.getText().toString())+1;
                prquantity.setText(String.valueOf(quant));
            }
            else if(view.getId()==quantitydown.getId())
            {
                //Log.d("ramkishan","Quantity down");
                int quant=Integer.valueOf(prquantity.getText().toString())-1;
                if(quant<0)
                {

                }
                else {
                    prquantity.setText(String.valueOf(quant));
                }
            }
            //!!!!!prcost has instock!!!!! !!!!!instock has cost!!!!!
            int position=this.getAdapterPosition();
            Products p=productsList.get(position);
            String id=p.getProductid();
            String name=p.getProductName();
            int quantity=Integer.parseInt(prquantity.getText().toString());
            int cost=Integer.parseInt(prinstock.getText().toString())*quantity;
            int instock=p.getProductCost();


            if(view.getId()==prmovetocart.getId())
            {
                Log.d("ramkishan","instock   "+instock);
                Log.d("ramkishan","quantity    "+quantity);
                if(quantity>instock)
                {
                    Toast.makeText(context,"Not in stock!!!!",Toast.LENGTH_LONG).show();
                }
                else {
                    Cart c = new Cart(name, quantity, cost, id);
                    cartList.add(c);
                }
                //Log.d("ramkishan","\nc prname    "+c.getProductName()+"\n c prquantity  " +c.getProductQuantity()+"\n  prcost   "+c.getProductCost());
            }
        }
    }
}
