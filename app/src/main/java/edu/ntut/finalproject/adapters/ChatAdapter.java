package edu.ntut.finalproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Chat;
import edu.ntut.finalproject.activities.MessageActivity;
import edu.ntut.finalproject.util;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final Context context;

    private final String uid;
    private ArrayList<Chat> chatArray;

    public ChatAdapter(Context context, ArrayList<Chat> messageArray, String uid) {
        this.context   = context;
        this.chatArray = messageArray;
        this.uid       = uid;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        if (chatArray == null) return;
        holder.bindTo(chatArray.get(position));
    }

    @Override
    public int getItemCount() {
        if (chatArray == null) return 0;
        return chatArray.size();
    }

    public void setMessageArray(ArrayList<Chat> messageArray) {
        this.chatArray = messageArray;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView toUID;
        private final TextView lastMesg;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            toUID = itemView.findViewById(R.id.tab_message_item_UID);
            lastMesg = itemView.findViewById(R.id.tab_message_item_lastmesg);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Chat chat = chatArray.get(getAdapterPosition());

            Intent message = new Intent(context, MessageActivity.class);
            if (chat.getToUID().equals(uid)) {
                message.putExtra(util.FROM, uid);
                message.putExtra(util.TO, chat.getFromUID());
            } else {
                message.putExtra(util.FROM, uid);
                message.putExtra(util.TO, chat.getToUID());
            }

            context.startActivity(message);
        }

        public void bindTo(@NonNull Chat chat) {
            if (chat.getToUID().equals(uid))
                toUID.setText(chat.getFromUID());
            else
                toUID.setText(chat.getToUID());

            lastMesg.setText(chat.getLastMesg());
        }
    }
}
