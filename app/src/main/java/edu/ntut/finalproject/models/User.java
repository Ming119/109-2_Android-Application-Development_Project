/*
 * By 108590050
 */

package edu.ntut.finalproject.models;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class User {
    private static Database db = new Database();

    private String uid;
    private String name;

    public User() {
        uid  = null;
        name = null;
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
     * User Login
     * @param uid
     * @param pw
     * @return true if login success
     * @return false if login failed
     * @throws IOException
     * @throws JSONException
     */
    public boolean Login(String uid, String pw) throws IOException, JSONException {
        String JSONString = db.getUser(uid);

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray usersArray = jsonObject.getJSONArray("user");

        if (usersArray == null) return false;
        else {
            JSONObject user = usersArray.getJSONObject(0);
            String password = user.getString("password");

            if (password.equals(pw)) {
                this.uid  = user.getString("uid");
                this.name = user.getString("name");

                return true;
            }
        }

        return false;
    }

    /**
     * User Registration
     * @param uid
     * @param name
     * @param pw
     * @return true if registration success
     * @return false if registration failed
     * @throws IOException
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
     * @param uid
     * @param name
     * @param pw
     * @return true if edit success
     * @return false if edit failed
     * @throws IOException
     */
    public boolean EditProfile(String uid, String name, String pw) throws IOException {
        return db.updateUser(uid, name, pw);
    }
}
