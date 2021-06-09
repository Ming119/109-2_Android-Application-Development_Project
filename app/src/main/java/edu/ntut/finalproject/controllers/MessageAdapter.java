/*
package edu.ntut.finalproject.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Chat;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ItemViewHolder> {

    private final Context context;

    private String uid;
    private ArrayList<Chat> chatArray;

    public MessageAdapter(Context context, String uid) {
        this.context = context;
        this.uid     = uid;

        Chat chat = new Chat();
        try {
            this.chatArray = chat.getChats(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.message_tab_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (chatArray == null) return;
        holder.bindTo(chatArray.get(position));
    }

    @Override
    public int getItemCount() {
        return chatArray.size();
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView message;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message_tab_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindTo(@NonNull Chat chat) {
            message.setText(chat.getMessage());
        }
    }
}
*/
