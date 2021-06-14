package edu.ntut.finalproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Item;

public class EditItemActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;
    private EditText price;
    private Button   editbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edititem);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.edit_item_new_title);
        desc  = findViewById(R.id.edit_item_new_desc);
        price = findViewById(R.id.edit_item_new_price);
        editbtn = findViewById(R.id.edit_item_edit);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void EditItem(View view) {
        Item item = new Item();
        try {
            String t = title.getText().toString();
            String d = desc.getText().toString();
            String p = price.getText().toString();

            if (t.equals("")) {
                Toast.makeText(this, "Title cannot be empty.", Toast.LENGTH_LONG).show();
                return;
            }

            if (p.equals("")) {
                Toast.makeText(this, "Price cannot be empty.", Toast.LENGTH_LONG).show();
                return;
            }

            if (item.updateItem(getIntent().getIntExtra("ID", 0), t, d, Integer.parseInt(p))) {
                Toast.makeText(EditItemActivity.this, "Edit success!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditItemActivity.this, "Edit failed!", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
