package edu.ntut.finalproject.models;

import android.net.Uri;

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
    private static final String DESC = "desc";
    private static final String PRICE = "price";
    private static final String SOLD = "sold";
    private static final String GET = "GET";
    private static final String POST = "POST";

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;

    public Database() { }

    /**
     * User Login Function
     * @param uid
     * @return JSON like String
     * @throws IOException
     */
    public String Login(String uid)  throws IOException {
        String url = BASE_URL + "GetUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(UID, uid)
                .build();

        URL requestURL = new URL(builtURI.toString());

        urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod(POST);
        urlConnection.connect();

        reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
            builder.append(line + "\n");

        if (builder.length() == 0) return null;

        return builder.toString();
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
        String url = BASE_URL + "CreateUser?";
        Uri builtURI = Uri.parse(url).buildUpon()
                .appendQueryParameter(UID, uid)
                .appendQueryParameter(NAME, name)
                .appendQueryParameter(PW, pw)
                .build();

        URL requestURL = new URL(builtURI.toString());

        urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod(POST);
        urlConnection.connect();
        if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK)
            return true;

        return false;
    }

}
