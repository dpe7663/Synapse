package com.example.loslolos.synapse;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

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
