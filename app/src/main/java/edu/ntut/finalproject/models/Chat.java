/*
 * By 108590050
 */

package edu.ntut.finalproject.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.ntut.finalproject.util;

public class Chat {

    private static final Database db = new Database();

    private String fromUID;
    private String toUID;
    private String lastMesg;

    public Chat() {
        this.fromUID  = "";
        this.toUID    = "";
        this.lastMesg = "";
    }

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

    /**
     * Get all chats from the database
     * @param uid String
     * @return  String like JSON array
     * @throws Exception Connection Error
     */
    public ArrayList<Chat> getChats(String uid) throws Exception {
        String JSONString = db.getLastChat(uid);
        if (JSONString == null) return null;

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray chatArray = jsonObject.getJSONArray(util.CHAT);

        ArrayList<Chat> chats = new ArrayList<>();

        for (int i = 0; i < chatArray.length(); i++) {
            JSONObject chat = chatArray.getJSONObject(i);

            String  fuid = chat.getString(util.FROMUID);
            String  tuid = chat.getString(util.TOUID);
            String  cmsg = chat.getString(util.MSG);

            chats.add(new Chat(fuid, tuid, cmsg));
        }

        return chats;
    }
}
