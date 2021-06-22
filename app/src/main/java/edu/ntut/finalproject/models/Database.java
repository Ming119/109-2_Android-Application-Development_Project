/**
 * No more changes will be made before presentation
 * By 108590050
 *
 * Final Version
 *
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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import edu.ntut.finalproject.util;

public class Database {

    private HttpURLConnection urlConnection = null;
    private InputStream    response = null;

    public Database() { }

    /**
     * Private method: connect to the database
     * @param uri       Uri
     * @param method    String POST/GET
     */
    private void connect(@NonNull Uri uri, String method) {

        /**
         * TODO: MultiThreading
         *      `AsyncTask` was deprecated in Android API 30.
         *      Suggesting use `ExecutorService` instead.
         *      `Thread` is not suggested.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            try  {
                URL requestURL = new URL(uri.toString());

                urlConnection = (HttpURLConnection) requestURL.openConnection();
                urlConnection.setRequestMethod(method);
                urlConnection.connect();

                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                    response = urlConnection.getInputStream();

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private method: convert JSON to STRING
     * @param json InputStream
     * @return  JSON like String
     * @throws IOException null JSON
     */
    @Nullable
    private String JSON2String(@NonNull InputStream json) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(json));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
            builder.append(line);

        if (builder.length() == 0) return null;

        return builder.toString();
    }

    /**
     * Create a new user to the database
     * @param uid   String
     * @param name  String
     * @param pw    String
     * @return true if user create success, false if user create failed
     * @throws IOException Connection Error
     */
    public boolean createUser(String uid, String name, String pw) throws IOException {

        String url = util.BASE_URL + "CreateUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lUID, uid)
                .appendQueryParameter(util.NAME, name)
                .appendQueryParameter(util.PW, pw)
                .build();

        connect(builtURI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * Get a user from the database by uid
     * @param uid String
     * @return JSON like String
     * @throws IOException Connection Error
     */
    public String getUser(String uid)  throws IOException {
        String url = util.BASE_URL + "GetUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lUID, uid)
                .build();

        connect(builtURI, util.GET);

        while (response == null)
            connect(builtURI, util.GET);

        if (response != null)
            return JSON2String(response);

        return null;
    }

    /**
     * Update the name and password of a user from the database by uid
     * @param uid   String
     * @param name  String
     * @param pw    String
     * @return true if user update success, false if user update failed
     * @throws IOException Connection Error
     */
    public boolean updateUser(String uid, String name, String pw) throws IOException {
        String url = util.BASE_URL + "UpdateUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lUID, uid)
                .appendQueryParameter(util.NAME, name)
                .appendQueryParameter(util.PW, pw)
                .build();

        connect(builtURI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * Create a new item to the database
     * @param title     String
     * @param desc      Stromg
     * @param price     omt
     * @param uid       String
     * @return true if item create success, false if item create failed
     * @throws IOException Connection Error
     */
    public boolean createItem(String title, String desc, int price, String uid) throws IOException {
        String url = util.BASE_URL + "CreateItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lTITLE, title)
                .appendQueryParameter(util.lDESC, desc)
                .appendQueryParameter(util.lPRICE, String.valueOf(price))
                .appendQueryParameter(util.lUID, uid)
                .build();

        connect(builtURI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * Get all items from the database
     * @return JSON like String
     * @throws IOException Connection Error
     */
    public String getItems() throws IOException {
        String url = util.BASE_URL + "GetItem";
        Uri builtURI = Uri.parse(url).buildUpon().build();

        connect(builtURI, util.GET);
        while (response == null)
            connect(builtURI, util.GET);

        if (response != null)
            return JSON2String(response);

        return null;
    }

    /**
     * Get an item from the database by id
     * @param id    int
     * @return JSON like String
     * @throws IOException Connection Error
     */
    public String getItem(int id) throws IOException {
        String url = util.BASE_URL + "GetItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lID, String.valueOf(id))
                .build();

        connect(builtURI, util.GET);
        while (response == null)
            connect(builtURI, util.GET);

        if (response != null)
            return JSON2String(response);

        return null;
    }

    /**
     * Update the title, description and price of an item from the database by id
     * @param id        int
     * @param title     String
     * @param desc      String
     * @param price     int
     * @return true if update success, false if update failed
     * @throws IOException Connection Error
     */
    public boolean updateItem(int id, String title, String desc, int price) throws IOException {
        String url = util.BASE_URL + "UpdateItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lID, String.valueOf(id))
                .appendQueryParameter(util.lTITLE, title)
                .appendQueryParameter(util.lDESC, desc)
                .appendQueryParameter(util.lPRICE, String.valueOf(price))
                .build();

        connect(builtURI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * Update the sold status of an item from the database by id
     * @param id int
     * @return true if update success, false if update failed
     * @throws IOException Connection Error
     */
    public boolean updateItem(int id) throws IOException {
        String url = util.BASE_URL + "UpdateItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lID, String.valueOf(id))
                .build();

        connect(builtURI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * Delete an item from the datqbase by id
     * @param id int
     * @return true if delete success, false if delete failed
     * @throws IOException Connection Error
     */
    public boolean deleteItem(int id) throws IOException {
        String url = util.BASE_URL + "DeleteItem?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lID, String.valueOf(id))
                .build();

        connect(builtURI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * @param from      String
     * @param to        String
     * @param message   String
     * @return true if chat record create success, false if item create failed
     * @throws IOException Connection Error
     */
    public boolean createChat(String from, String to, String message) throws IOException {
        String url = util.BASE_URL + "CreateChatRecord?";
        Uri builtBRI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.FROMUID, from)
                .appendQueryParameter(util.TOUID, to)
                .appendQueryParameter(util.MSG, message)
                .build();

        connect(builtBRI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * @param from  String
     * @param to    String
     * @return JSON like String
     * @throws IOException Connection Error
     */
    public String getChats(String from, String to) throws IOException {
        String url = util.BASE_URL + "GetChatRecord?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.FROMUID, from)
                .appendQueryParameter(util.TOUID, to)
                .build();

        connect(builtURI, util.GET);

        while (response == null)
            connect(builtURI, util.GET);

        if (response != null)
            return JSON2String(response);

        return null;
    }

    /**
     * @param rcid int
     * @return true if delete success, false if delete failed
     * @throws IOException Connection Error
     */
    public boolean deleteChat(int rcid) throws IOException {
        String url = util.BASE_URL + "DeleteChatRecord?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.CRID, String.valueOf(rcid))
                .build();

        connect(builtURI, util.POST);

        return urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK;
    }

    /**
     * @param uid   String
     * @return  String: the last chat
     * @throws IOException Connection Error
     */
    public String getLastChat(String uid) throws IOException {
        String url = util.BASE_URL + "GetLastChat?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(util.lUID, uid)
                .build();

        connect(builtURI, util.GET);

        if (response != null) {
            return JSON2String(response);
        }

        return null;
    }
}
