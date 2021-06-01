package edu.ntut.finalproject.models;

import android.net.Uri;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
     * @param user
     * @param pw
     * @return true if login success
     * @return false if login failed
     */
    public boolean Login(@NonNull User user, String pw) {
        String uid = user.getUid();

        try {
            String url = BASE_URL + "GetUser?";
            Uri builtURI = Uri.parse(url).buildUpon()
                    .appendQueryParameter(UID, uid)
                    .build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod(POST);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");

            }
            if (builder.length() == 0) {
                return false;
            }
            String JSONString = builder.toString();

            JSONObject jsonObject = new JSONObject(JSONString);
            JSONArray usersArray = jsonObject.getJSONArray("user");

            String upw   = null;

            JSONObject uuser = usersArray.getJSONObject(0);
            upw   = uuser.getString(PW);

            if (upw == pw)
                return true;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    /**
     * User Registration Function
     * @param user
     * @param pw
     * @return true if registration success
     * @return false if registration failed
     */
    public boolean Register(@NonNull User user, String pw) {
        String uid = user.getUid();
        String name = user.getName();

        try {
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

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return false;
    }

}
