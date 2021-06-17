/**
 * No more changes will be made before presentation
 * By 108590050
 *
 * Scheduled changes
 * TODO: Show the original Item info so that user can easily know what they're going to change
 *
 */

package edu.ntut.finalproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.User;
import edu.ntut.finalproject.util;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText studentID;
    private EditText name;
    private EditText password;
    private EditText confirmPasword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences(util.sharePrefName, MODE_PRIVATE);

        studentID = findViewById(R.id.register_studentID);
        name      = findViewById(R.id.register_name);
        password  = findViewById(R.id.register_password);
        confirmPasword = findViewById(R.id.register_confirm_password);

        Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(view -> {
            String rid = studentID.getText().toString();
            String rnm = name.getText().toString();
            String rpw = password.getText().toString();
            String cpw = confirmPasword.getText().toString();

            if (rid.isEmpty()) {
                Toast.makeText(RegisterActivity.this, R.string.nullUID, Toast.LENGTH_LONG).show();
                return;
            }

            if (rid.length() != 9) {
                Toast.makeText(RegisterActivity.this, R.string.UIDformat, Toast.LENGTH_LONG).show();
                return;
            }

            if (rnm.isEmpty()) {
                Toast.makeText(RegisterActivity.this, R.string.nullUsername, Toast.LENGTH_LONG).show();
                return;
            }

            if (rpw.isEmpty()) {
                Toast.makeText(RegisterActivity.this, R.string.nullPW, Toast.LENGTH_LONG).show();
                return;
            }

            if (!rpw.equals(cpw)) {
                Toast.makeText(RegisterActivity.this, R.string.pwNotMatch, Toast.LENGTH_LONG).show();
                return;
            }


            User user = new User();
            try {
                if (user.Register(studentID.getText().toString(), name.getText().toString(), password.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, R.string.registerSuccess, Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                    preferencesEditor.putString(util.UID, user.getUid());
                    preferencesEditor.putString(util.USERNAME, user.getName());
                    preferencesEditor.apply();

                    Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(main);
                }
                else
                    Toast.makeText(RegisterActivity.this, R.string.registerFail, Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        TextView gotoLogin = findViewById(R.id.text_gotoLogin);
        gotoLogin.setOnClickListener(v -> finish());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
