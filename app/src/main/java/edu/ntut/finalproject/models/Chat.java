/*
 * By 108590050
 */

package edu.ntut.finalproject.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Chat {

    private static final Database db = new Database();

    private String fromUID;
    private String toUID;
    private String lastMesg;

    public Chat() { }

    public Chat(String from, String to, String mesg) {
        this.fromUID  = from;
        this.toUID    = to;
        this.lastMesg = mesg;
    }

    public String getFromUID() { return fromUID; }
    public String getToUID() { return toUID; }
    public String getLastMesg() { return lastMesg; }

    public void setFromUID(String fromUID) { this.fromUID = fromUID; }
    public void setToUID(String toUID) { this.toUID = toUID; }
    public void setLastMesg(String lastMesg) { this.lastMesg = lastMesg; }

    public ArrayList<Chat> getChats(String uid) throws Exception {
        String JSONString = db.getChats(uid);
        if (JSONString == null) return null;

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray chatArray = jsonObject.getJSONArray("chat");

        ArrayList<Chat> chats = new ArrayList<>();
        if (chatArray == null) return null;

        for (int i = 0; i < chatArray.length(); i++) {
            JSONObject chat = chatArray.getJSONObject(i);

            String  fuid = chat.getString("fromUID");
            String  tuid = chat.getString("toUID");
            String  cmsg = chat.getString("message");

            chats.add(new Chat(fuid, tuid, cmsg));
        }

        return chats;
    }
}
