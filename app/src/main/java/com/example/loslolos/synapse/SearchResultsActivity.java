package com.example.loslolos.synapse;

import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResultsActivity extends AppCompatActivity {

    //Initialize variables that reference elements in activity_search_results.xml
    TextView textViewFOI;
    //TextView textViewFOI1;

    ArrayList<String> usernames = new ArrayList<>();
    //ArrayAdapter adapter;

    //fieldofinterest will reference the autoFOI variable from SearchActivity.java
    //and display it at the top of the page
    String fieldofinterest;

    ListView searchResultsListView;

    //DatabaseReference reference;

    //Automatically created method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //fieldofinterest is set equal to the autoFOI variable from the SearchActivity class
        //and is set as a String
        fieldofinterest = SearchActivity.autoFOI.getText().toString().trim();

        searchResultsListView = (ListView) findViewById(R.id.searchResultsListView);

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usernames);
        //searchResultsListView.setAdapter(adapter);

        //Set the TextView at the top of the page to equal the selected Field of Interest for searching
        textViewFOI = (TextView) findViewById(R.id.textViewFOI);
        textViewFOI.setText(fieldofinterest);
/*
        //textViewFOI1 = (TextView) findViewById(R.id.textViewFOI1);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        reference.orderByChild("First Field of Interest").equalTo(fieldofinterest).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getValue();
            }

            //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

/*
        //Take the Database reference and add an event listener to the "Majors" child.
        //This ValueListener() allows the Majors Spinner (drop down menu) to populate
        //with the table of majors in Firebase.
        reference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> majors = new ArrayList<String>();

                //This Enhanced For Loop basically says, "For each DataSnapshot data in dataSnapShot,
                //add the values of the children titled "Name" to the majors list
                for (DataSnapshot majorSnapshot : dataSnapshot.getChildren()) {
                    String majorName = majorSnapshot.child("Username").getValue(String.class);
                    majors.add(majorName);
                }

                ArrayAdapter<String> majorsAdapter = new ArrayAdapter<String>(SearchResultsActivity.this, android.R.layout.simple_spinner_item, majors);
                majorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                searchResultsListView.setAdapter(majorsAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
/*
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                usernames.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

*/

/*
        Query query = reference.child("Users").orderByChild("First Field of Interest").equalTo(fieldofinterest);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    //add username
                }

                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    if (singleSnapshot.exists()) {

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        //textViewFOI1.setText((CharSequence) query);





/*
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("Users");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("name").getValue(String.class);
                    Log.d("TAG", name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);
*/


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
