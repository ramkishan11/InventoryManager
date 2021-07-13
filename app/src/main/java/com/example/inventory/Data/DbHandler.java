package com.example.inventory.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.inventory.Model.Products;
import com.example.inventory.Params.Params;
import com.example.inventory.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper{
    public DbHandler(Context context) {
        super(context, Params.DBNAME, null, Params.DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String createProductTable="CREATE TABLE " + Params.PRODUCTSTABLE +" ( productid INTEGER PRIMARY KEY ,  name TEXT , quantity INTEGER  , cost INTEGER  )";
        String createCustomerTable="CREATE TABLE "+Params.CUSTOMERTABLE +" ( customerid INTEGER PRIMARY KEY , name TEXT )";
        String createSoldToTable="CREATE TABLE " + Params.SOLDTOTABLE+" ( orderid INTEGER PRIMARY KEY , date TEXT , productid INTEGER , customerid INTEGER , quantitysold INTEGER )";

        Log.d("ramkishan","Query running");
        sqLiteDatabase.execSQL(createProductTable);
        sqLiteDatabase.execSQL(createCustomerTable);
        sqLiteDatabase.execSQL(createSoldToTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //Products
    public void addProduct(Products p)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("productid",p.getProductid());
        cv.put("name",p.getProductName());
        cv.put("quantity",p.getProductQuantity());
        cv.put("cost",p.getProductCost());

        db.insert(Params.PRODUCTSTABLE,null,cv);
        Log.d("ramkishan","Successfully inserted");
        db.close();

    }
    public List<Products> getAllProducts()
    {
        List<Products> listProducts=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+Params.PRODUCTSTABLE;
        Cursor c=db.rawQuery(select,null);
        if(c.moveToFirst())
        {
            do {
                Products p=new Products();
                p.setProductid(c.getString(0));
                p.setProductName(c.getString(1));
                p.setProductQuantity(Integer.parseInt(c.getString(2)));
                p.setProductCost(Integer.parseInt(c.getString(3)));
                listProducts.add(p);
            }while(c.moveToNext());
        }
        return listProducts;
    }

    public void deleteContact (String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.PRODUCTSTABLE,"productid=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateQuantity(String id,int quantity)
    {

    }
}
