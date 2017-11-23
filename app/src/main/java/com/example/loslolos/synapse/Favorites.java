package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        /*Creates the Image Buttons according to their corresponding actions*/
        ImageButton profileImageButton = (ImageButton) findViewById(R.id.imageButtonProfile2);
        ImageButton researchImageButton = (ImageButton) findViewById(R.id.imageButtonResearch2);
        ImageButton searchImageButton = (ImageButton) findViewById(R.id.imageButtonSearch2);
        ImageButton favoritesImageButton = (ImageButton) findViewById(R.id.imageButtonFavorite2);
        ImageButton settingsImageButton = (ImageButton) findViewById(R.id.imageButtonSettings2);

        /*Creates action listeners for each single image button created above in order to obtain
        each class according to their corresponding actions*/
        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(profileIntent);
            }
        });

        researchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent researchIntent = new Intent(getApplicationContext(), ResearchBackground.class);
                startActivity(researchIntent);
            }
        });

        searchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), Search.class);
                startActivity(searchIntent);
            }
        });

        favoritesImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favoritesIntent = new Intent(getApplicationContext(), Favorites.class);
                startActivity(favoritesIntent);
            }
        });

        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), Settings.class);
                startActivity(settingsIntent);
            }
        });
    }
}
