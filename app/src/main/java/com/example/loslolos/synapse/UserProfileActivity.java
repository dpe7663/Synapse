package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserProfileActivity extends AppCompatActivity {

    //Initialize variables that will be used to reference the TextView fields in the UserProfileActivity
    TextView textViewUsername;
    TextView textViewMajor;
    //TextView textViewConcentration;
    TextView textViewEmail;
    TextView textViewFOI1;
    TextView textViewFOI2;
    TextView textViewFOI3;
    TextView textViewFOI4;
    TextView textViewFOI5;

    //String variables used to store a user's registration info from Firebase.
    //FOI is an abbreviation for the user's Fields of Interest that they selected during registration.
    String username;
    String major;
    String email;
    String FOI1;
    String FOI2;
    String FOI3;
    String FOI4;
    String FOI5;

    //String uid is used to store a user's unique ID
    String uid;

    //DatabaseReference synapseDatabase is a reference for the Firebase Database
    DatabaseReference synapseDatabase;

    //FirebaseUser user gets the currently logged in user
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //user variable is set to the currently logged in user
        //uid is set to the user's unique ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        //Variables made to reference the TextViews from activity_user_profile.xml
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        textViewMajor = (TextView) findViewById(R.id.textViewMajor);
        //textViewConcentration;
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewFOI1 = (TextView) findViewById(R.id.textViewFOI1);
        textViewFOI2 = (TextView) findViewById(R.id.textViewFOI2);
        textViewFOI3 = (TextView) findViewById(R.id.textViewFOI3);
        textViewFOI4 = (TextView) findViewById(R.id.textViewFOI4);
        textViewFOI5 = (TextView) findViewById(R.id.textViewFOI5);

        //synapseDatabase is set to reference the Firebase Database
        synapseDatabase = FirebaseDatabase.getInstance().getReference();

        //Add a ValueEventListener to synapseDatabase. This will be used to pull the logged
        //in user's registration info from Firebase and populate it on his/her profile page
        synapseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Take each variable, set it equal to the value
                //that's found in the appropriate child in Firebase.
                //In the username example: the variables find the "Users" child, then finds the "uid" child,
                //then the "Username" child.
                username = dataSnapshot.child("Users").child(uid).child("Username").getValue(String.class);
                major = dataSnapshot.child("Users").child(uid).child("Major").getValue(String.class);
                email = dataSnapshot.child("Users").child(uid).child("Email").getValue(String.class);
                FOI1 = dataSnapshot.child("Users").child(uid).child("First Field of Interest").getValue(String.class);
                FOI2 = dataSnapshot.child("Users").child(uid).child("Second Field of Interest").getValue(String.class);
                FOI3 = dataSnapshot.child("Users").child(uid).child("Third Field of Interest").getValue(String.class);
                FOI4 = dataSnapshot.child("Users").child(uid).child("Fourth Field of Interest").getValue(String.class);
                FOI5 = dataSnapshot.child("Users").child(uid).child("Fifth Field of Interest").getValue(String.class);

                //Set the referenced TextView variables equal to what we just pulled in from Firebase
                textViewUsername.setText(username);
                textViewMajor.setText(major);
                textViewEmail.setText(email);
                textViewFOI1.setText(FOI1);
                textViewFOI2.setText(FOI2);
                textViewFOI3.setText(FOI3);
                textViewFOI4.setText(FOI4);
                textViewFOI5.setText(FOI5);

            }

            //If there's a problem, a notification pops up
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "Uh oh! Something bad happened. Sorry.", Toast.LENGTH_SHORT).show();

            }
        });

        //Creates a TextView to display the about information
        TextView textViewAbout = (TextView) findViewById(R.id.textViewAbout);

        //Creates a new file with the directory of the public file to be read
        File file = new File(getExternalFilesDir(null), "about.txt");

        /*Reads the file with a BufferedReader and a FileReader in order to read
        them line by line and append them accordingly with their line break as
        user typed his information.
         */
        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while((line = fileReader.readLine()) != null)
            {
                strBuilder.append(line);
                strBuilder.append("\n");
            }
            fileReader.close();
            strBuilder.trimToSize();
            String contentsOfFile = strBuilder.toString();
            textViewAbout.setText(contentsOfFile);
        }
        catch (IOException e){
        }

        /*Creates a new PreviousResearchButton*/
        Button prevResearchButton = (Button) findViewById(R.id.buttonPrevR);

        /*Creates the Image Buttons according to their corresponding actions*/
        ImageButton profileImageButton = (ImageButton) findViewById(R.id.imageButtonProfile);
        ImageButton researchImageButton = (ImageButton) findViewById(R.id.imageButtonResearch);
        ImageButton searchImageButton = (ImageButton) findViewById(R.id.imageButtonSearch);
        ImageButton favoritesImageButton = (ImageButton) findViewById(R.id.imageButtonFavorite);
        ImageButton settingsImageButton = (ImageButton) findViewById(R.id.imageButtonSettings);

        /*Creates an action listener for previousResearchButton*/
        prevResearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent researchIntent = new Intent(getApplicationContext(), ResearchBackgroundActivity.class);
                startActivity(researchIntent);
            }
        });

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
                Intent favoritesIntent = new Intent(getApplication(), FavoritesActivity.class);
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
