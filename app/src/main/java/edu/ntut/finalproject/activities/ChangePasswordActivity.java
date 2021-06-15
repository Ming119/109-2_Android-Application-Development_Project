package edu.ntut.finalproject.activities;

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
import edu.ntut.finalproject.util;

public class ChangePasswordActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText newPassword;
    private EditText confirmNewPW;
    private EditText oldPassword;
    private EditText confirmOldPW;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(util.sharePrefName, MODE_PRIVATE);

        newPassword  = findViewById(R.id.change_password_newpassword);
        confirmNewPW = findViewById(R.id.change_password_confirm_newpassword);
        oldPassword  = findViewById(R.id.change_password_old_password);
        confirmOldPW = findViewById(R.id.change_password_confirm_password);

        Button summit = findViewById(R.id.btn_change_password);
        summit.setOnClickListener(view -> {
            String npw  = newPassword.getText().toString();
            String cnpw = confirmNewPW.getText().toString();
            String opw  = oldPassword.getText().toString();
            String copw = confirmOldPW.getText().toString();

            if (npw.isEmpty()) {
                Toast.makeText(ChangePasswordActivity.this, R.string.nullPW, Toast.LENGTH_LONG).show();
                return;
            }

            if (!npw.equals(cnpw)) {
                Toast.makeText(ChangePasswordActivity.this, R.string.pwNotMatch, Toast.LENGTH_LONG).show();
                return;
            }

            if (opw.isEmpty()) {
                Toast.makeText(ChangePasswordActivity.this, R.string.nullPW, Toast.LENGTH_LONG).show();
                return;
            }

            if (!opw.equals(copw)) {
                Toast.makeText(ChangePasswordActivity.this, R.string.pwNotMatch, Toast.LENGTH_LONG).show();
                return;
            }

            User user = new User(sharedPreferences.getString(util.UID, null));
            try {
                if(user.EditProfile(sharedPreferences.getString(util.USERNAME, null), npw)) {
                    Toast.makeText(ChangePasswordActivity.this, R.string.pwChange, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, R.string.changeFail, Toast.LENGTH_LONG).show();
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