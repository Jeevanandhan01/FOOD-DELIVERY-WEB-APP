package com.jeeva.models;

public class Order {
    private int id;
    private String food_name;
    private String restaurant_name;
    private float total;
    private String status;
    private String rest_address;
    private String user_contact;
    private String rest_contact;
    private String customer_name;

    public Order( int id,String food_name,String restaurant_name,  float total, String status, String rest_address, String user_contact, String rest_contact,String customer_name) {
        this.id = id;
        this.food_name = food_name;
        this.restaurant_name = restaurant_name;
        this.total = total;
        this.status = status;
        this.rest_address = rest_address;
        this.user_contact = user_contact;
        this.rest_contact = rest_contact;
        this.customer_name = customer_name;
    }

    public int getId() {
        return id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getRest_address() {
        return rest_address;
    }

    public void setRest_address(String rest_address) {
        this.rest_address = rest_address;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(String user_contact) {
        this.user_contact = user_contact;
    }

    public String getRest_contact() {
        return rest_contact;
    }

    public void setRest_contact(String rest_contact) {
        this.rest_contact = rest_contact;
    }

    public void setRestaurant_name() {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }
}
