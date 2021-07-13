package com.example.inventory.Model;

public class Cart {
   private String productName;
   private int productQuantity;
   private int productCost;
    private String productId;
    public Cart(String productName, int productQuantity, int productCost,String productId) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productCost = productCost;
        this.productId=productId;
    }
    public Cart()
    {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }
}
