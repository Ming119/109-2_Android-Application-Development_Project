package edu.ntut.finalproject.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.adapters.MessageAdapter;
import edu.ntut.finalproject.models.Message;
import edu.ntut.finalproject.util;

public class MessageActivity extends AppCompatActivity {

    private String from;
    private String to;
    private ScrollView scrollView;
    private ArrayList<Message> messageArray;

    private EditText editText;
    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        from = getIntent().getStringExtra(util.FROM);
        to   = getIntent().getStringExtra(util.TO);

        editText = findViewById(R.id.editText_message);

        scrollView = findViewById(R.id.message_scrollView);
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));

        Message messaage = new Message(from);
        try {
            messageArray = messaage.getMessages(to);
            messageAdapter = new MessageAdapter(this, from, to, messageArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.message_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        Button send = findViewById(R.id.send_message);
        send.setOnClickListener(v -> {
            String s = editText.getText().toString();

            Message m = new Message();
            try {
                if (m.newMessage(from, to, s)) {
                    Toast.makeText(MessageActivity.this, R.string.sent, Toast.LENGTH_LONG).show();
                    Message message = new Message(from);
                    messageArray = message.getMessages(to);
                    messageAdapter.setMessageArray(messageArray);
                    messageAdapter.notifyDataSetChanged();

                } else
                    Toast.makeText(MessageActivity.this, R.string.error, Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
