package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        /*Creates the buttons for each button located at the botton of the class*/
        Button profileButton = (Button) findViewById(R.id.profileButton);
        Button researchButton = (Button) findViewById(R.id.researchButton);
        Button search2Button = (Button) findViewById(R.id.search2Button);
        Button favoritesButton = (Button) findViewById(R.id.favoritesButton);
        Button settingsButton = (Button) findViewById(R.id.settingsButton);

        /*Creates action listeners for each button defined above*/
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(profileIntent);
            }
        });

        researchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent researchIntent = new Intent(getApplicationContext(), ResearchBackground.class);
                startActivity(researchIntent);
            }
        });

        search2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), Search.class);
                startActivity(searchIntent);
            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favoritesIntent = new Intent(getApplication(), Favorites.class);
                startActivity(favoritesIntent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), Settings.class);
                startActivity(settingsIntent);
            }
        });
    }
}
