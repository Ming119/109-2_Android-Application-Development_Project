package edu.ntut.finalproject.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.AddBookToSell;
import edu.ntut.finalproject.controllers.ProgressBarAnimations;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    private SharedPreferences mPreferences;
    final String filename = "NTUTBOOKAHOLIC";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_app_loading);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progressBar);

        progressAnimation();
        progressBar.setMax(100);
        progressBar.setScaleX(2f);
        progressBar.setScaleY(2f);

        //shared preferences
        mPreferences = getSharedPreferences(filename, MODE_PRIVATE);


        //condition connected or not
        if (mPreferences.contains("id")) {
            Intent intent = new Intent(MainActivity.this, AddBookToSell.class);
            startActivity(intent);
        }


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

    public void progressAnimation() {
        ProgressBarAnimations anim = new ProgressBarAnimations(this, progressBar, textView, 0f, 100f);
        anim.setDuration(3500);
        progressBar.setAnimation(anim);
    }

}