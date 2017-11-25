package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Creates The LoginActivity and Register Buttons */
        Button loginButton = (Button) findViewById(R.id.loginBtn);
        Button registerButton = (Button) findViewById(R.id.registerBtn);

        /* Creates action listener for the LoginActivity button */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent (getApplicationContext(), UserProfileActivity.class);
                startActivity(loginIntent);
            }
        });

        /* Creates action listener for the Register Button */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}
