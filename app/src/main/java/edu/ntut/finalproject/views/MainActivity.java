package edu.ntut.finalproject.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.controllers.TabAdapter;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    private SharedPreferences mPreferences;
    final String filename = "NTUTBOOKAHOLIC";

    // Main-page carousel view (slide) , import external implementation 'com.synnapps:carouselview:0.1.5'
    CarouselView carouselview;
    private int[] CarouselImages = new int[] {R.drawable.whole,R.drawable.was};
    private String[] CarouselImageTitle = new String[] {"Market","WAS"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselview = (CarouselView) findViewById(R.id.carouselView);
        carouselview.setPageCount(CarouselImages.length);

        //Set the carousel image (show and position)
        carouselview.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(CarouselImages[position]);
            }
        });

        //Click on the carousel image with toast text
        carouselview.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,CarouselImageTitle[position],Toast.LENGTH_SHORT).show();
            }
        });




//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        progressBar = findViewById(R.id.progressBar);
//
//        progressAnimation();
//        progressBar.setMax(100);
//        progressBar.setScaleX(2f);
//        progressBar.setScaleY(2f);
//
//        //shared preferences
//        mPreferences = getSharedPreferences(filename, MODE_PRIVATE);


        //condition connected or not
//        if (mPreferences.contains("id")) {
//            Intent intent = new Intent(MainActivity.this, AddBookToSell.class);
//            startActivity(intent);
//        }


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

}