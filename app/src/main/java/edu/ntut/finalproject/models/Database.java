/*
 * By 108590050
 */

package edu.ntut.finalproject.models;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Database {
    private static final String BASE_URL = "https://140.124.184.193:8080/androidfinal/";
    private static final String UID = "uid";
    private static final String NAME = "name";
    private static final String PW = "password";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESC = "description";
    private static final String PRICE = "price";
    private static final String SOLD = "sold";
    private static final String GET = "GET";
    private static final String POST = "POST";

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;

    public Database() { }

    private HttpURLConnection connect(@NonNull Uri uri, String method) throws IOException{
        URL requestURL = new URL(uri.toString());

        urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod(method);
        urlConnection.connect();

        return urlConnection;
    }

    @Nullable
    private String JSON2String(@NonNull InputStream json) throws IOException{
        reader = new BufferedReader(new InputStreamReader(json));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
            builder.append(line + "\n");

        if (builder.length() == 0) return null;

        return builder.toString();
    }

    /**
     * Create a new user to the database
     * @param uid
     * @param name
     * @param pw
     * @return true if user create success
     * @return false if user create failed
     * @throws IOException
     */
    public boolean createUser(String uid, String name, String pw) throws IOException {
        String url = BASE_URL + "CreateUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(UID, uid)
                .appendQueryParameter(NAME, name)
                .appendQueryParameter(PW, pw)
                .build();

        if (connect(builtURI, POST).getResponseCode() == HttpsURLConnection.HTTP_OK)
            return true;

        return false;
    }

    /**
     * Get a user from the database by uid
     * @param uid
     * @return JSON like String
     * @throws IOException
     */
    public String getUser(String uid)  throws IOException {
        String url = BASE_URL + "GetUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(UID, uid)
                .build();

        String res = JSON2String(connect(builtURI, GET).getInputStream());
        if (res == null) return null;
        return res;
    }

    /**
     * Update the name and password of a user from the database by uid
     * @param uid
     * @param name
     * @param pw
     * @return true if user update success
     * @return false if user update failed
     * @throws IOException
     */
    public boolean updateUser(String uid, String name, String pw) throws IOException {
        String url = BASE_URL + "UpdateUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(UID, uid)
                .appendQueryParameter(NAME, name)
                .appendQueryParameter(PW, pw)
                .build();

        if (connect(builtURI, POST).getResponseCode() == HttpsURLConnection.HTTP_OK)
            return true;

        return false;
    }

    /**
     * Create a new item to the database
     * @param title
     * @param desc
     * @param price
     * @param uid
     * @return true if item create success
     * @return false if item create failed
     * @throws IOException
     */
    public boolean createItem(String title, String desc, int price, String uid) throws IOException {
        String url = BASE_URL + "CreateItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(TITLE, title)
                .appendQueryParameter(DESC, desc)
                .appendQueryParameter(PRICE, String.valueOf(price))
                .appendQueryParameter(UID, uid)
                .build();

        if (connect(builtURI, POST).getResponseCode() == HttpsURLConnection.HTTP_OK)
            return true;

        return false;
    }

    /**
     * Get all items from the database
     * @return JSON like String
     * @throws IOException
     */
    public String getItems() throws IOException {
        String url = BASE_URL + "GetItem";
        Uri builtURI = Uri.parse(url).buildUpon().build();

        String res = JSON2String(connect(builtURI, GET).getInputStream());
        if (res == null) return null;
        return res;
    }

    /**
     * Get an item from the database by id
     * @param id
     * @return JSON like String
     * @throws IOException
     */
    public String getItem(int id) throws IOException {
        String url = BASE_URL + "GetItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(ID, String.valueOf(id))
                .build();

        String res = JSON2String(connect(builtURI, GET).getInputStream());
        if (res == null) return null;
        return res;
    }

    /**
     * Update the title, description and price of an item from the database by id
     * @param id
     * @param title
     * @param desc
     * @param price
     * @return true if update success
     * @retrun false if update failed
     * @throws IOException
     */
    public boolean updateItem(int id, String title, String desc, int price) throws IOException {
        String url = BASE_URL + "UpdateItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(ID, String.valueOf(id))
                .appendQueryParameter(TITLE, title)
                .appendQueryParameter(DESC, desc)
                .appendQueryParameter(PRICE, String.valueOf(price))
                .build();

        if (connect(builtURI, POST).getResponseCode() == HttpsURLConnection.HTTP_OK)
            return true;

        return false;
    }

    /**
     * Update the sold status of an item from the database by id
     * @param id
     * @return true if update success
     * @return false if update failed
     * @throws IOException
     */
    public boolean updateItem(int id) throws IOException {
        String url = BASE_URL + "SellItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(ID, String.valueOf(id))
                .build();

        if (connect(builtURI, POST).getResponseCode() == HttpsURLConnection.HTTP_OK)
            return true;

        return false;
    }

    /**
     * Delete an item from the datqbase by id
     * @param id
     * @return true if delete success
     * @return false if delete failed
     * @throws IOException
     */
    public boolean deleteItem(int id) throws IOException {
        String url = BASE_URL + "DeleteItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(ID, String.valueOf(id))
                .build();

        if (connect(builtURI, POST).getResponseCode() == HttpsURLConnection.HTTP_OK)
            return true;

        return false;
    }
}
