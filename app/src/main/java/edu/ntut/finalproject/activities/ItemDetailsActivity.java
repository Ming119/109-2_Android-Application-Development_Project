package edu.ntut.finalproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.util;

public class ItemDetailsActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences(util.sharePrefName, MODE_PRIVATE);

        id = getIntent().getIntExtra(util.ID, 0);

        ImageView image    = findViewById(R.id.Item_image);
        TextView  title    = findViewById(R.id.Item_title);
        TextView  desc     = findViewById(R.id.Item_desc);
        TextView  price    = findViewById(R.id.Item_price);
        Button    contact  = findViewById(R.id.Item_contact);

        image.setImageResource(R.drawable.ntut);
        title.setText(getIntent().getStringExtra(util.TITLE));
        desc.setText(getIntent().getStringExtra(util.DESC));
        price.setText("$ " + getIntent().getStringExtra(util.PRICE));

        if (getIntent().getStringExtra(util.SELLER).equals(sharedPreferences.getString(util.UID, null))) {
            contact.setText(R.string.edit_item);
        }

        contact.setOnClickListener(v -> {
            if (contact.getText().equals(util.EDIT_ITEM)) {
                Intent editItemActivity = new Intent(this, EditItemActivity.class);
                editItemActivity.putExtra(util.ID, id);
                startActivity(editItemActivity);

            } else if (sharedPreferences.getString(util.UID, null) == null) {
                Toast.makeText(this, R.string.loginFirst, Toast.LENGTH_LONG).show();

                // TODO: goto LOGIN page if possible

            } else {
                // TODO:

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
