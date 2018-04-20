package com.example.android.tourguideapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LandmarkActivity extends AppCompatActivity {

    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark);

        // Providing up navigation for this activity (for a theme without ActionBar)
        // REMEMBER to also update Manifest
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(getResources().getColor(R.color.distance_gray));

        // Using custom drawable for a visual polish
        Drawable icon = ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_left_black_24dp);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(icon);

        //RECEIVING DATA VIA INTENTS
        int image = getIntent().getIntExtra("LandmarkImageResource_FromIntent", 0);

        int name = getIntent().getIntExtra("LandmarkName_FromIntent", 0);
        int description = getIntent().getIntExtra("LandmarkDescription_FromIntent", 0);
        int phone = getIntent().getIntExtra("LandmarkPhone_FromIntent", 0);
        int website = getIntent().getIntExtra("LandmarkWebsite_FromIntent", 0);

        latitude = getIntent().getDoubleExtra("LandmarkLatitude_FromIntent", 0.00);
        longitude = getIntent().getDoubleExtra("LandmarkLongitude_FromIntent", 0.00);

        int backgroundColor = getIntent().getIntExtra("Background_FromIntent", 0);

        //SETTING DATA TO VIEWS
        LinearLayout mainLayout = findViewById(R.id.main_layout);
        mainLayout.setBackgroundColor(backgroundColor);
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageResource(image);

        TextView t1 = findViewById(R.id.name_text_view);
        t1.setText(name);

        TextView t2 = findViewById(R.id.description_text_view);
        t2.setText(description);

        TextView t3 = findViewById(R.id.phone_text_view);
        t3.setText(getString(R.string.phone_code) + phone);

        TextView t4 = findViewById(R.id.website_text_view);
        t4.setText(website);
    }

    // Google Maps Intent
    // Launch turn-by-turn navigation

    public void getDirections(View v) {

        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    // Update back button logic to avoid Resources$NotFoundException:
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

}
