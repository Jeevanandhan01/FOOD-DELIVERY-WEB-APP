package com.jeeva.models;
public class Cart {
    private int uid;
    private int fid;
    private String foodname;
    private int quantity;
    private float price;
    private float total;
    private String restaurantname;
    private String restaurantaddress;

    public Cart(int uid,int fid,String foodname, int quantity, float price,float total, String restaurantname, String restaurantaddress) {
        this.uid = uid;
        this.fid = fid;
        this.foodname = foodname;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.restaurantname = restaurantname;
        this.restaurantaddress = restaurantaddress;

    }
    public String getFoodname() {
        return foodname;
    }

    public float getTotal() {
        return total;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getUid() {
        return uid;
    }

    public int getFid() {
        return fid;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getRestaurantaddress() {
        return restaurantaddress;
    }

    public void setRestaurantaddress(String restaurantaddress) {
        this.restaurantaddress = restaurantaddress;
    }



}
