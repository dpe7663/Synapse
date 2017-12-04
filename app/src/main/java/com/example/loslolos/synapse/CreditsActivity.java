package com.example.loslolos.synapse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        /*Creates the arrow buton to return to the settings page*/
        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnButton);

        /*Creates an on click listener for the return button*/
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
            }
        });
    }
}
