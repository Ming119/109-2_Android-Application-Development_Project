
package edu.ntut.finalproject.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private final Context context;

    private ArrayList<Chat> messageArray;
    private String uid;

    public ChatAdapter(Context context, String uid) {
        this.context = context;
        this.uid = uid;

        Chat chat = new Chat();
        try {
            this.messageArray = chat.getChats(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        if (messageArray == null) return;
        holder.bindTo(messageArray.get(position));
    }

    @Override
    public int getItemCount() {
        if (messageArray == null) return 0;
        return messageArray.size();
    }
}
