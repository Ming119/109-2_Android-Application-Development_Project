package edu.ntut.finalproject.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;

import java.io.IOException;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.TabAdapter;
import edu.ntut.finalproject.models.User;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;

    EditText studentID;
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        studentID = findViewById(R.id.login_studentID);
        password  = findViewById(R.id.login_passowrd);

        viewPager.setAdapter(new TabAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            }
        }).attach();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * .
     * @param view
     */
    public void gotoRegister(View view) {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }

    /**
     * .
     * @param view
     */
    public void Login(View view) {
        User user = new User();
        try {
            user.Login(studentID.getContext().toString(), password.getContext().toString());
        } catch (IOException | JSONException e) {
            Log.e("Message", e.getMessage());
        }
    }

}