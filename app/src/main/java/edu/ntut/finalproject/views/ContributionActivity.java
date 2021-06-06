package edu.ntut.finalproject.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import edu.ntut.finalproject.R;

public class ContributionActivity extends AppCompatActivity {

    TextView projectLink;
    TextView contributor1Link;
    TextView contributor2Link;
    TextView contributor3Link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        projectLink = findViewById(R.id.text_link);
        projectLink.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://github.com/Ming119/109-2_Android-Application-Development_Project");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        });

        contributor1Link = findViewById(R.id.contributor1);
        contributor1Link.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://github.com/FuChiKei");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        });

        contributor2Link = findViewById(R.id.contributor2);
        contributor2Link.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://github.com/Ming119");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        });

        contributor3Link = findViewById(R.id.contributor3);
        contributor3Link.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://github.com/HuangShengYao108590061");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
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
