package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Creates The Login and Register Buttons */
        Button loginButton = (Button) findViewById(R.id.loginBtn);
        Button registerButton = (Button) findViewById(R.id.registerBtn);

        /* Creates action listener for the Login button */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent (getApplicationContext(), UserProfile.class);
                startActivity(loginIntent);
            }
        });

        /* Creates action listener for the Register Button */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), AccountInformation.class);
                startActivity(registerIntent);
            }
        });
    }
}
