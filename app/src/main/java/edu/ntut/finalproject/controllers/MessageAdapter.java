package edu.ntut.finalproject.controllers;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Message;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private final Context context;

    private String fromUID;
    private String toUID;
    private ArrayList<Message> messageArray;

    public MessageAdapter(Context context, String from, String to) {
        this.context = context;
        this.fromUID = from;
        this.toUID   = to;

        Message message = new Message(fromUID);
        try {
            this.messageArray = message.getChats(toUID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (messageArray == null) return;
        holder.bindTo(messageArray.get(position));
    }

    @Override
    public int getItemCount() {
        if (messageArray == null) return 0;
        return messageArray.size();
    }

    protected class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView message;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message_b);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindTo(@NonNull Message mesg) {
            Log.d("message", mesg.getMessage());

            message.setText(mesg.getMessage());

            if (mesg.getFromUID().equals(fromUID)) {
                message.setGravity(Gravity.RIGHT);
            }

            if (mesg.getFromUID().equals(toUID)) {
                message.setGravity(Gravity.LEFT);
            }
        }
    }
}
