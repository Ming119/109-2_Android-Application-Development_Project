package edu.ntut.finalproject.controllers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Chat;

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

    }

    public void bindTo(@NonNull Chat chat) {
        toUID.setText(chat.getToUID());
        lastMesg.setText(chat.getLastMesg());
    }
}
