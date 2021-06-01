package edu.ntut.finalproject.models;

public class Item {

    private int     id;
    private String  title;
    private String  description;
    private int     price;
    private String  uid;
    private boolean sold;

    public Item() {
        id          = 0;
        title       = "";
        description = "";
        price       = 0;
        uid         = "";
        sold        = false;
    }

    public Item(int id, String title, String desc, int price, String uid, boolean sold) {
        this.id          = id;
        this.title       = title;
        this.description = desc;
        this.price       = price;
        this.uid         = uid;
        this.sold        = sold;
    }

    public int getID() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public String getUid() { return uid; }
    public boolean isSold() { return sold; }

    public void setID(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(int price) { this.price = price; }
    public void setUid(String uid) { this.uid = uid; }
    public void setSold(boolean sold) { this.sold = sold; }
    
}
