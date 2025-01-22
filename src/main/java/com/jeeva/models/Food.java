package com.jeeva.models;

public class Food {
    private int uid;
    private int fid;
    private String name;
    private float price;
    private int rid;
    private boolean avail;
    private String rname;
    private String location;
    public Food(int uid,int fid, String name, float price, int rid, boolean avail, String rname, String location) {
        this.uid = uid;
        this.fid = fid;
        this.name = name;
        this.price = price;
        this.rid = rid;
        this.avail = avail;
        this.rname = rname;
        this.location = location;
    }
    public int getFid() {
        return fid;
    }

    public String getLocation()
    {
        return location;
    }
    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public boolean isAvail() {
        return avail;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    public String getRname() {
        return rname;
    }

    public int getUid() {
        return uid;
    }

}
