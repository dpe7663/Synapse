package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UniversityInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_information);

        /* Creates a "Create Button" */
        Button createButton = (Button) findViewById(R.id.createProfileButton);

        /* Creates action listener for "Create Button" */
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreateProfile = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(intentCreateProfile);
            }
        });
    }
}
