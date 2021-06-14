package edu.ntut.finalproject.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import edu.ntut.finalproject.R;

public class ItemDetailsActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("edu.ntut.finalproject.loginStatus", MODE_PRIVATE);

        id = getIntent().getIntExtra("ID", 0);

        ImageView image    = findViewById(R.id.Item_image);
        TextView  title    = findViewById(R.id.Item_title);
        TextView  desc     = findViewById(R.id.Item_desc);
        TextView  price    = findViewById(R.id.Item_price);
        Button    contact  = findViewById(R.id.Item_contact);

        image.setImageResource(R.drawable.was);
        title.setText(getIntent().getStringExtra("TITLE"));
        desc.setText(getIntent().getStringExtra("DESC"));
        price.setText("$ " + getIntent().getStringExtra("PRICE"));

        if (getIntent().getStringExtra("SELLER").equals(sharedPreferences.getString("UID", null))) {
            contact.setText("Edit Item");
        }

        contact.setOnClickListener(v -> {
            if (contact.getText().equals("Edit Item")) {
                Intent editItemActivity = new Intent(this, EditItemActivity.class);
                editItemActivity.putExtra("ID", id);
                startActivity(editItemActivity);

            } else if (sharedPreferences.getString("UID", null) == null) {
                //Intent intent = new Intent(this, .class);
                //startActivity(intent);
            } else {

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
