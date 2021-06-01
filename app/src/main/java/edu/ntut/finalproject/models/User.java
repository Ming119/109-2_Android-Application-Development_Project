package edu.ntut.finalproject.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class User {

    private String uid;
    private String name;

    public User() {
        uid  = "";
        name = "";
    }

    public User(String uid, String name) {
        this.uid  = uid;
        this.name = name;
    }

    public String getUid() { return uid; }
    public String getName() { return name; }

    public void setUid(String uid) { this.uid = uid; }
    public void setName(String name) { this.name = name; }

    /**
     * User Login Function
     * @param uid
     * @param pw
     * @return true if login success
     * @return false if login failed
     * @throws IOException
     * @throws JSONException
     */
    public boolean Login(String uid, String pw) throws IOException, JSONException {
        Database db = new Database();

        String JSONString = db.Login(uid);

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray usersArray = jsonObject.getJSONArray("user");

        if (usersArray == null) return false;
        else {
            JSONObject user = usersArray.getJSONObject(0);
            String password = user.getString("password");

            if (password == pw) return true;
        }

        return false;
    }

    /**
     * User Registration Function
     * @param uid
     * @param name
     * @param pw
     * @return true if registration success
     * @return false if registration failed
     * @throws IOException
     */
    public boolean Register(String uid, String name, String pw) throws IOException {
        Database db = new Database();
        return  db.Register(uid, name, pw);
    }
}
