package com.example.loslolos.synapse;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    //Initialize variables that will reference the elements in activity_search.xml
    public static AutoCompleteTextView autoFOI;

    //fieldofinterest will hold the user's selection for the Field of Interest that he/she
    //wants to search
    public static String fieldofinterest;

    //variable to reference Firebase so we can pull values from it
    DatabaseReference refDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //set the variables equal to the elements in activity_search.xml
        autoFOI = (AutoCompleteTextView) findViewById(R.id.autoCompleteFOI);

        /*Creates the button for the regular SearchActivity Button*/
        Button searchButton = (Button) findViewById(R.id.buttonSearch);

        //synapseDatabase is stores a reference of the Firebase database
        refDatabase = FirebaseDatabase.getInstance().getReference();

        //add a value event listener to synapseDatabase. This will allow us to populate
        //the Field of Interest field with values from Firebase
        refDatabase.child("Fields of+ Interest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Initialize a List to hold the table of interests from Firebase
                final List<String> interests = new ArrayList<String>();

                //This Enhanced For Loop basically says, "For each DataSnapshot data in dataSnapShot,
                //add the values of the children titled "Name" to the interests list
                for (DataSnapshot interestSnapshot : dataSnapshot.getChildren()) {
                    String interestName = interestSnapshot.child("Name").getValue(String.class);
                    interests.add(interestName);
                }

                ArrayAdapter<String> interestsAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_dropdown_item_1line, interests);
                interestsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //These next lines add the list of interests from Firebase into each variable
                autoFOI.setAdapter(interestsAdapter);

                //set String variable fieldofinterest equal to what the user selected for the Field of Interest
                fieldofinterest = autoFOI.getText().toString().trim();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*Creates action listener for the regular search button*/
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), SearchResultsActivity.class);
                startActivity(searchIntent);
            }
        });

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
