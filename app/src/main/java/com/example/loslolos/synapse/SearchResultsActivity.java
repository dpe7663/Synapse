package com.example.loslolos.synapse;

import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {

    //Initialize variables that reference elements in activity_search_results.xml
    TextView textViewFOI;

    //fieldofinterest will reference the autoFOI variable from SearchActivity.java
    //and display it at the top of the page
    String fieldofinterest;

    //Automatically created method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //fieldofinterest is set equal to the autoFOI variable from the SearchActivity class
        //and is set as a String
        fieldofinterest = SearchActivity.autoFOI.getText().toString().trim();

        //Set the TextView at the top of the page to equal the selected Field of Interest for searching
        textViewFOI = (TextView) findViewById(R.id.textViewFOI);
        textViewFOI.setText(fieldofinterest);


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
                Intent profileIntent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        researchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent researchIntent = new Intent(getApplicationContext(), ResearchBackgroundActivity.class);
                startActivity(researchIntent);
            }
        });

        search2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favoritesIntent = new Intent(getApplication(), FavoritesActivity.class);
                startActivity(favoritesIntent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
    }
}
