package com.jeeva.models;

public class Restaurant {
    private int r_id;
    private String r_name;

    public Restaurant(int r_id, String r_name) {
        this.r_id = r_id;
        this.r_name = r_name;
    }
    public int getR_id() {
        return r_id;
    }
    public String getR_name() {
        return r_name;
    }
}
