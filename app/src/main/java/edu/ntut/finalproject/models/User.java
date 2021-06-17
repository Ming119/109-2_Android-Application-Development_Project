/*
 * By 108590050
 */

package edu.ntut.finalproject.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.ntut.finalproject.util;

public class User {

    private static final Database db = new Database();

    private String uid;
    private String name;

    public User() {
        uid  = null;
        name = null;
    }

    public User(String uid) {
        this.uid = uid;

        String JSONString;
        try {
            JSONString = db.getUser(uid);

            JSONObject jsonObject = new JSONObject(JSONString);
            JSONArray usersArray = jsonObject.getJSONArray(util.USER);
            JSONObject user = usersArray.getJSONObject(0);
            this.name  = user.getString(util.NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUid() { return uid; }
    public String getName() { return name; }

    public void setUid(String uid) { this.uid = uid; }
    public void setName(String name) { this.name = name; }

    /**
     * User Login
     * @param uid   String
     * @param pw    String
     * @return true if login success, false if login failed
     * @throws IOException      Connection Error
     * @throws JSONException    Ref to DataBase JSON2String method
     */
    public boolean Login(String uid, String pw) throws IOException, JSONException {
        String JSONString = db.getUser(uid);

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray usersArray = jsonObject.getJSONArray(util.USER);

        JSONObject user = usersArray.getJSONObject(0);
        String password = user.getString(util.PW);

        if (password.equals(pw)) {
            this.uid  = user.getString(util.lUID);
            this.name = user.getString(util.NAME);

            return true;
        }

        return false;
    }

    /**
     * User Registration
     * @param uid   String
     * @param name  String
     * @param pw    String
     * @return true if registration success, false if registration failed
     * @throws IOException  Connection Error
     */
    public boolean Register(String uid, String name, String pw) throws IOException {
        if (db.createUser(uid, name, pw)) {
            this.uid  = uid;
            this.name = name;

            return true;
        }
        return false;
    }

    /**
     * Edit User Profile
     * @param name  String
     * @param pw    String
     * @return true if edit success, false if edit failed
     * @throws IOException  Connection Error
     */
    public boolean EditProfile(String name, String pw) throws IOException {
        if (db.updateUser(uid, name, pw)) {
            this.name = name;
            return true;
        }
        return false;
    }
}
