/**
 * No more changes will be made before presentation
 * By 108590050
 *
 * Scheduled changes
 * TODO: Jump to the Login page, if possible, while User is not logged in and clicking the `Context Seller` Button.
 * TODO: Jump to the Message page, if possible, after clicking the `Context Seller` Button.
 *
 */

package edu.ntut.finalproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.Item;
import edu.ntut.finalproject.models.Message;
import edu.ntut.finalproject.util;

public class ItemDetailsActivity extends AppCompatActivity {

    private TextView  title;
    private TextView  desc;
    private TextView  price;

    private int id;
    private String from;
    private String to;

    private String sTitle;
    private String sDesc;
    private String sPrice;

    ActivityResultLauncher<Intent> editItemActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == 1)
                    finish();
            });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences(util.sharePrefName, MODE_PRIVATE);
        from = sharedPreferences.getString(util.UID, null);
        to   = getIntent().getStringExtra(util.SELLER);
        id   = getIntent().getIntExtra(util.ID, 0);
        sTitle = getIntent().getStringExtra(util.TITLE);
        sDesc  = getIntent().getStringExtra(util.DESC);
        sPrice = "$ " + getIntent().getStringExtra(util.PRICE);


        ImageView image    = findViewById(R.id.Item_image);
        title    = findViewById(R.id.Item_title);
        desc     = findViewById(R.id.Item_desc);
        price    = findViewById(R.id.Item_price);
        Button    contact  = findViewById(R.id.Item_contact);

        image.setImageResource(R.drawable.ntut);
        title.setText(sTitle);
        desc.setText(sDesc);
        price.setText(sPrice);

        if (to.equals(from)) {
            contact.setText(R.string.edit_item);
        }

        contact.setOnClickListener(v -> {
            if (contact.getText().equals(util.EDIT_ITEM)) {
                Intent editItemActivity = new Intent(this, EditItemActivity.class);
                editItemActivity.putExtra(util.ID, id);
                editItemActivityResultLauncher.launch(editItemActivity);


            } else if (from == null) {
                 // temporarily implemented with Toast
                Toast.makeText(this, R.string.loginFirst, Toast.LENGTH_LONG).show();

                // TODO: goto LOGIN tab if possible

            } else {
                // temporarily implemented with sending item info automatically
                String mesg = sTitle + "    " + sDesc + "    " + sPrice;
                Message message = new Message();
                try {
                    if (message.newMessage(from, to, mesg)) {
                        Toast.makeText(this, R.string.contectSellerSuccess, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // TODO: goto MESSAGE tab if possible
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

    @Override
    protected void onResume() {
        Item item = new Item();
        try {
            item = item.getItem(id);
            sTitle = item.getTitle();
            sDesc  = item.getDescription();
            sPrice = "$ " + item.getPrice();

            title.setText(sTitle);
            desc.setText(sDesc);
            price.setText(sPrice);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onResume();
    }

}
