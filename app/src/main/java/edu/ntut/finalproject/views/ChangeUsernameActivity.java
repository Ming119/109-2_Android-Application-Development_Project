package edu.ntut.finalproject.views;

import android.content.SharedPreferences;
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
import edu.ntut.finalproject.models.User;

public class ChangeUsernameActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText newUsername;
    private EditText password;
    private EditText confirmPW;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("edu.ntut.finalproject.loginStatus", MODE_PRIVATE);

        newUsername = findViewById(R.id.change_username_newusername);
        password    = findViewById(R.id.change_username_password);
        confirmPW   = findViewById(R.id.change_username_confirm_password);

        Button summit = findViewById(R.id.btn_change_username);
        summit.setOnClickListener(view -> {
            String newName = newUsername.getText().toString();
            String pw      = password.getText().toString();
            String cpw     = confirmPW.getText().toString();

            if (newName.equals("")) {
                Toast.makeText(ChangeUsernameActivity.this, "Username cannot be empty", Toast.LENGTH_LONG).show();
                return;
            }

            if (pw.equals("")) {
                Toast.makeText(ChangeUsernameActivity.this, "Password cannot be empty", Toast.LENGTH_LONG).show();
                return;
            }

            if (!pw.equals(cpw)) {
                Toast.makeText(ChangeUsernameActivity.this, "Passwords did not match", Toast.LENGTH_LONG).show();
                return;
            }

            User user = new User(sharedPreferences.getString("UID", null));
            try {
                if(user.EditProfile(newName, pw)) {
                    Toast.makeText(ChangeUsernameActivity.this, "Username Changed!", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                    preferencesEditor.putString("UID", user.getUid());
                    preferencesEditor.putString("USERNAME", user.getName());
                    preferencesEditor.apply();

                    finish();
                } else {
                    Toast.makeText(ChangeUsernameActivity.this, "Change failed!", Toast.LENGTH_LONG).show();
                }
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
