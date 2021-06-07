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

    private int rcid;
    private String fromUID;
    private String toUID;
    private String message;

    public Chat() {
        this.rcid    = 0;
        this.fromUID = null;
        this.toUID   = null;
        this.message = null;
    }

    public Chat(int rcid) {
        this.rcid = rcid;
    }

    public Chat(int rcid, String from, String to, String mesg) {
        this.rcid    = rcid;
        this.fromUID = from;
        this.toUID   = to;
        this.message = mesg;
    }

    public int getRcid() { return rcid; }
    public String getFromUID() {return fromUID; }
    public String getToUID() { return toUID; }
    public String getMessage() { return message; }

    public void setRcid(int rcid) { this.rcid = rcid; }
    public void setFromUID(String fromUID) { this.fromUID = fromUID; }
    public void setToUID(String toUID) { this.toUID = toUID; }
    public void setMessage(String message) { this.message = message; }

    public ArrayList<Chat> getChats(String uid) throws Exception {
        String JSONString = db.getChats(uid);
        if (JSONString == null) return null;

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray chatArray = jsonObject.getJSONArray("chatRecord");

        ArrayList chats = new ArrayList<Item>();
        if (chatArray == null) return null;

        for (int i = 0; i < chatArray.length(); i++) {
            JSONObject chat = chatArray.getJSONObject(i);

            int     cid  = Integer.parseInt(chat.getString("rcid"));
            String  fuid = chat.getString("fromUID");
            String  tuid = chat.getString("toUID");
            String  cmsg = chat.getString("message");

            chats.add(new Chat(cid, fuid, tuid, cmsg));
        }

        return chats;
    }

    public boolean newChat(String from, String to, String mesg) throws IOException {
        return db.createChat(from, to, mesg);
    }

    public boolean deleteChat(int rcid) throws IOException {
        return db.deleteChat(rcid);
    }
}