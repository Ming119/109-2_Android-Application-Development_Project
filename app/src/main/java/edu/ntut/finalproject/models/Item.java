/*
 * By 108590050
 */

package edu.ntut.finalproject.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Item {
    private static final Database db = new Database();

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

    /**
     * Get all item
     * @return An ArrayList of all Item
     * @throws IOException Connection Error
     * @throws JSONException Connection Error
     */
    public ArrayList<Item> getItems() throws IOException, JSONException {
        String JSONString = db.getItems();
        if (JSONString == null) return null;

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray itemsArray = jsonObject.getJSONArray("items");
        if (itemsArray == null) return null;

        ArrayList items = new ArrayList<Item>();
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.getJSONObject(i);

            int     iid    = Integer.parseInt(item.getString("id"));
            String  ititle = item.getString("title");
            String  idesc  = item.getString("description");
            int     iprice = Integer.parseInt(item.getString("price"));
            String  iuid   = item.getString("uid");
            boolean isold  = Boolean.parseBoolean(item.getString("sold"));

            items.add(new Item(iid, ititle, idesc, iprice, iuid, isold));
        }

        return items;
    }

    /**
     * Get an item by id
     * @param id int
     * @return An Item
     * @throws IOException Connection Error
     * @throws JSONException Connection Error
     */
    public Item getItem(int id) throws IOException, JSONException {
        String JSONString = db.getItem(id);

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray itemsArray = jsonObject.getJSONArray("item");

        JSONObject item = itemsArray.getJSONObject(0);
        int     iid    = Integer.parseInt(item.getString("id"));
        String  ititle = item.getString("title");
        String  idesc  = item.getString("description");
        int     iprice = Integer.parseInt(item.getString("price"));
        String  iuid   = item.getString("uid");
        boolean isold  = Boolean.parseBoolean(item.getString("sold"));

        return new Item(iid, ititle, idesc, iprice, iuid, isold);
    }

    /**
     * Create a new item
     * @param title String
     * @param desc  String
     * @param price int
     * @param uid   String
     * @return true if create success, false if create failed
     * @throws IOException Connection Error
     */
    public boolean newItem(String title, String desc, int price, String uid) throws IOException {
        return db.createItem(title, desc, price, uid);
    }

    /**
     * Update the title, description and price of an item by id
     * @param id    int
     * @param title String
     * @param desc  String
     * @param price int
     * @return true if update success, false if update failed
     * @throws IOException Connection Error
     */
    public boolean updateItem(int id, String title, String desc, int price) throws IOException {
        return db.updateItem(id, title, desc, price);
    }

    /**
     * Change the status of sold of an item by id
     * @param id int
     * @return true if change success, false if change failed
     * @throws IOException Connection Error
     */
    public boolean itemSold(int id) throws IOException {
        return db.updateItem(id);
    }

    /**
     * Delete an item by id
     * @param id int
     * @return true if delete success,  false if delete failed
     * @throws IOException Connection Error
     */
    public boolean deleteItem(int id) throws IOException {
        return db.deleteItem(id);
    }
}
