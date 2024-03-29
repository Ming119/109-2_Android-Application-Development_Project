/**
 * No more changes will be made before presentation
 * By 108590050
 *
 * Scheduled changes
 * TODO: Show the original Item info so that user can easily know what they're going to change
 *
 */

package edu.ntut.finalproject.activities;

import android.os.Bundle;
import android.view.MenuItem;
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
import edu.ntut.finalproject.util;

public class EditItemActivity extends AppCompatActivity {

    private int id;

    private EditText title;
    private EditText desc;
    private EditText price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edititem);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra(util.ID, 0);

        title = findViewById(R.id.edit_item_new_title);
        desc  = findViewById(R.id.edit_item_new_desc);
        price = findViewById(R.id.edit_item_new_price);

        Button editbtn = findViewById(R.id.edit_item_edit);
        editbtn.setOnClickListener(v -> {
            Item item = new Item();

            try {
                String t = title.getText().toString();
                String d = desc.getText().toString();
                String p = price.getText().toString();

                if (t.isEmpty()) {
                    Toast.makeText(EditItemActivity.this, R.string.nullTitle, Toast.LENGTH_LONG).show();
                    return;
                }

                if (p.isEmpty()) {
                    Toast.makeText(EditItemActivity.this, R.string.nullPrice, Toast.LENGTH_LONG).show();
                    return;
                }

                if (item.updateItem(id, t, d, Integer.parseInt(p))) {
                    Toast.makeText(EditItemActivity.this, R.string.itemEdit, Toast.LENGTH_SHORT).show();

                    finish();
                } else
                    Toast.makeText(EditItemActivity.this, R.string.editFail, Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button soldbtn = findViewById(R.id.edit_item_sold);
        soldbtn.setOnClickListener(v -> {
            Item item = new Item();

            try {
                if (item.itemSold(id)) {
                    Toast.makeText(EditItemActivity.this, R.string.sold, Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(EditItemActivity.this, R.string.error, Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        Button deletebtn = findViewById(R.id.edit_item_delete);
        deletebtn.setOnClickListener(v -> {
            Item item = new Item();
            try {
                if (item.deleteItem(id)) {
                    Toast.makeText(EditItemActivity.this, R.string.deleteSuccess, Toast.LENGTH_SHORT).show();
                    setResult(1);
                    finish();
                } else
                    Toast.makeText(EditItemActivity.this, R.string.deleteFail, Toast.LENGTH_LONG).show();

            } catch (IOException e) {
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
