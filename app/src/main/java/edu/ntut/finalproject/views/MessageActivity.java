package edu.ntut.finalproject.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.MessageAdapter;
import edu.ntut.finalproject.models.Message;

public class MessageActivity extends AppCompatActivity {

    private String from;
    private String to;

    private Button send;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        from = getIntent().getStringExtra("FROM");
        to   = getIntent().getStringExtra("TO");

        send = findViewById(R.id.send_message);
        editText = findViewById(R.id.editText_message);

        RecyclerView recyclerView = findViewById(R.id.message_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MessageAdapter(this, from, to));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();

                Message m = new Message();
                try {
                    if (m.newMessage(from, to, s)) {

                    } else {
                        Toast.makeText(MessageActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
