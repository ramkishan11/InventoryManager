package com.example.inventory.Model;

public class Products {
   private String productid;
   private String productname;
   private int productquantity;
   private int productcost;

    public Products(String productid, String name, int quantity, int cost) {
        this.productid = productid;
        this.productname = name;
        this.productquantity = quantity;
        this.productcost = cost;
    }

    public Products() {
    }

    public String getProductid() {
        return productid;
    }

    public String getProductName() {
        return productname;
    }

    public int getProductQuantity() {
        return productquantity;
    }

    public int getProductCost() {
        return productcost;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setProductName(String name) {
        this.productname = name;
    }

    public void setProductQuantity(int quantity) {
        this.productquantity = quantity;
    }

    public void setProductCost(int cost) {
        this.productcost = cost;
    }
}
