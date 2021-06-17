package edu.ntut.finalproject.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.util;

public class Message {
    private static final Database db = new Database();

    private int rcid;
    private String fromUID;
    private String toUID;
    private String message;

    public Message() {
        this.rcid    = 0;
        this.fromUID = null;
        this.toUID   = null;
        this.message = null;
    }

    public Message(String from) {
        this.rcid    = 0;
        this.fromUID = from;
        this.toUID   = null;
        this.message = null;
    }

    public Message(int rcid, String from, String to, String mesg) {
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

    /**
     * Get all chats from the database
     * @param toUID String
     * @return  String like JSON array
     * @throws Exception Connection Error
     */
    public ArrayList<Message> getMessages(String toUID) throws Exception {
        String JSONString = db.getChats(this.fromUID, toUID);
        if (JSONString == null) return null;

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONArray chatArray = jsonObject.getJSONArray(util.CHATs);

        ArrayList<Message> chats = new ArrayList<>();
        if (chatArray == null) return null;

        for (int i = 0; i < chatArray.length(); i++) {
            JSONObject chat = chatArray.getJSONObject(i);

            int     cid  = Integer.parseInt(chat.getString(util.RCID));
            String  fuid = chat.getString(util.FROMUID);
            String  tuid = chat.getString(util.TOUID);
            String  cmsg = chat.getString(util.MSG);

            chats.add(new Message(cid, fuid, tuid, cmsg));
        }

        return chats;
    }

    /**
     * Add a new message to the database
     * @param from  String
     * @param to    String
     * @param mesg  String
     * @return  true if success, false if failed.
     * @throws IOException Connection Error
     */
    public boolean newMessage(String from, String to, String mesg) throws IOException {
        return db.createChat(from, to, mesg);
    }

    /**
     * Delete a message from the database by rcid
     * @param rcid  int
     * @return  true if success, false if failed.
     * @throws IOException Connection Error
     */
    public boolean deleteMessage(int rcid) throws IOException {
        return db.deleteChat(rcid);
    }
}
