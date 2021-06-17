package edu.ntut.finalproject.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import edu.ntut.finalproject.R;
import edu.ntut.finalproject.adapters.TabAdapter;
import edu.ntut.finalproject.util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(util.sharePrefName, MODE_PRIVATE);
        String uid = sharedPreferences.getString(util.UID, null);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new TabAdapter(this, uid));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.ic_home);
                    break;

                case 1:
                    tab.setIcon(R.drawable.ic_search);
                    break;

                case 2:
                    tab.setIcon(R.drawable.ic_add);
                    break;

                case 3:
                    tab.setIcon(R.drawable.ic_notify);
                    break;

                case 4:
                    tab.setIcon(R.drawable.ic_settting);
                    break;

                default: break;
        }}).attach();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
