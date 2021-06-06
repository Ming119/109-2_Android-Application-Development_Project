package edu.ntut.finalproject.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.User;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText studentID;
    private EditText name;
    private EditText password;
    private EditText confirmPasword;
    private Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("edu.ntut.finalproject.loginStatus", MODE_PRIVATE);

        studentID = findViewById(R.id.register_studentID);
        name      = findViewById(R.id.register_name);
        password  = findViewById(R.id.register_password);
        confirmPasword = findViewById(R.id.register_confirm_password);

        register = findViewById(R.id.btn_register);
        register.setOnClickListener(view -> {
            String rid = studentID.getText().toString();
            String rnm = name.getText().toString();
            String rpw = password.getText().toString();
            String cpw = confirmPasword.getText().toString();

            if (rid.equals("")) {
                Toast.makeText(RegisterActivity.this, "Student ID cannot be empty", Toast.LENGTH_LONG).show();
                return;
            }

            if (rid.length() != 9) {
                Toast.makeText(RegisterActivity.this, "Student ID format error", Toast.LENGTH_LONG).show();
                return;
            }

            if (rnm.equals("")) {
                Toast.makeText(RegisterActivity.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                return;
            }

            if (rpw.equals("")) {
                Toast.makeText(RegisterActivity.this, "Password cannot be empty", Toast.LENGTH_LONG).show();
                return;
            }

            if (!rpw.equals(cpw)) {
                Toast.makeText(RegisterActivity.this, "Passwords did not match", Toast.LENGTH_LONG).show();
                return;
            }

            boolean isSuccess = false;
            User user = new User();

            try {
                isSuccess = user.Register(studentID.getText().toString(), name.getText().toString(), password.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (isSuccess) {
                Toast.makeText(RegisterActivity.this, "Register Success.", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                preferencesEditor.putString("UID", user.getUid());
                preferencesEditor.putString("USERNAME", user.getName());
                preferencesEditor.apply();

                Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(main);
            }
            else
                Toast.makeText(RegisterActivity.this, "Register Failed.", Toast.LENGTH_LONG).show();
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void gotoLogin(View view) {
        finish();
    }

}
