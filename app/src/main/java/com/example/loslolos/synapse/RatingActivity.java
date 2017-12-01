package com.example.loslolos.synapse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

                /*Creates the Image Buttons according to their corresponding actions*/
        ImageButton profileImageButton = (ImageButton) findViewById(R.id.imageButtonProfile3);
        ImageButton researchImageButton = (ImageButton) findViewById(R.id.imageButtonResearch3);
        ImageButton searchImageButton = (ImageButton) findViewById(R.id.imageButtonSearch3);
        ImageButton favoritesImageButton = (ImageButton) findViewById(R.id.imageButtonFavorite3);
        ImageButton settingsImageButton = (ImageButton) findViewById(R.id.imageButtonSettings3);

        /*Creates action listeners for each single image button created above in order to obtain
        each class according to their corresponding actions*/
        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        researchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent researchIntent = new Intent(getApplicationContext(), ResearchBackgroundActivity.class);
                startActivity(researchIntent);
            }
        });

        searchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        favoritesImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favoritesIntent = new Intent(getApplicationContext(), FavoritesActivity.class);
                startActivity(favoritesIntent);
            }
        });

        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
    }
}
