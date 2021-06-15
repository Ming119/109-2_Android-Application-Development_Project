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

    private ArrayList<Chat> messageArray;

    public ChatAdapter(Context context, String uid) {
        this.context = context;

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
            Chat chat = messageArray.get(getAdapterPosition());

            Intent message = new Intent(context, MessageActivity.class);
            message.putExtra(util.FROM, chat.getFromUID());
            message.putExtra(util.TO, chat.getToUID());

            context.startActivity(message);
        }

        public void bindTo(@NonNull Chat chat) {
            toUID.setText(chat.getToUID());
            lastMesg.setText(chat.getLastMesg());
        }
    }
}
