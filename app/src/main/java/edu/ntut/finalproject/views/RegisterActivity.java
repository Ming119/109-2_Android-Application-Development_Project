package edu.ntut.finalproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText studentID;
    private EditText name;
    private EditText password;
    private EditText confirmPasword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        studentID = findViewById(R.id.register_studentID);
        name      = findViewById(R.id.register_name);
        password  = findViewById(R.id.register_password);
        confirmPasword = findViewById(R.id.register_confirm_password);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void gotoLogin(View view) {
        finish();
    }

    public void Register(View view) {
        if (password != confirmPasword)
        {
            Toast.makeText(this, "Passwords did not match.", Toast.LENGTH_SHORT);
            return;
        }

        boolean isSuccess = false;
        User user = new User();

        try {
            isSuccess = user.Register(studentID.getContext().toString(), name.getContext().toString(), password.getContext().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isSuccess)
            Toast.makeText(this, "Register Success.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Register Failed.", Toast.LENGTH_SHORT).show();
    }
}
