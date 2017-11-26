package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //initialize variables for the elements in activity_login.xml

    FirebaseAuth mAuth;
    EditText editTextEmail;
    EditText editTextPassword;
    ProgressBar progressBar;

    //create final variable for the minimum character length for a password
    final int MIN_PASS_LENGTH = 6;

    //onCreate method is automatically created when LoginActivity is made
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mAuth variable stores an instance of the Firebase Authorization
        mAuth = FirebaseAuth.getInstance();

        //editTextEmail and editTextPassword variables are
        //stored as references for the Edit Text fields
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        //progressBar variable is set to reference the Progress Bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        /* Creates The LoginActivity and Register Buttons */
        //Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        //Button buttonRegister = (Button) findViewById(R.id.buttonRegister);

        findViewById(R.id.buttonSignIn).setOnClickListener(this);
        findViewById(R.id.buttonRegister).setOnClickListener(this);


        /* Creates action listener for the Login button */
        /*
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent (getApplicationContext(), UserProfileActivity.class);
                startActivity(loginIntent);
            }
        });

        /* Creates action listener for the Register Button */
        /*
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        */
    }

    //userLogin method manages all of the options when the user enters
    //in their email and password, and presses the Login button
    private void userLogin() {

        //variables for storing what the user entered for email and password
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //if email is empty...
        //if (TextUtils.isEmpty(email)) {
        if (email.isEmpty()) {
            //Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        //if the email is not a valid email...
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        //if password is empty...
        //if (TextUtils.isEmpty(password)) {
        if (password.isEmpty()) {
            //Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        //if password is less than 6...
        if (password.length() < MIN_PASS_LENGTH) {
            editTextPassword.setError("Minimum length of password should be 6");
        }

        //if validations are ok
        //first show a progress bar
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //progressBar visibility is set to GONE, so it disappears
                progressBar.setVisibility(View.GONE);

                //if login is successful...
                if (task.isSuccessful()) {

                    //finish LoginActivity. This prevents user from going back to login screen
                    finish();

                    //start AccountInformationActivity
                    Intent intent = new Intent(LoginActivity.this, AccountInformationActivity.class);

                    //clears all open activities on top of stack, which would be Login/Register activity.
                    //important because if user presses Back button, it will send them back to Login/Register activity screens
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    //start the activity
                    startActivity(intent);
                }

                //else...prints an error exception message
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

/*
    //Made an Override method called onStart.
    //When LoginActivity starts, this method checks to
    //see if the current user is already logged in
    @Override
    protected void onStart() {
        super.onStart();

        //if current user is not null (meaning already logged in)...
        if (mAuth.getCurrentUser() != null) {
            //finish the activity, and start AccountInformationActivity
            finish();
            startActivity(new Intent(this, AccountInformationActivity.class));
        }
    }
*/

    //onClick method manages onClick methods for buttonLogin and buttonRegister
    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            //when buttonSignIn is pressed, call userLogin() method
            case R.id.buttonSignIn:
                userLogin();

                break;

            //when buttonRegister is pressed, LoginActivity is finished
            //and RegisterActivity starts
            case R.id.buttonRegister:
                finish();
                startActivity(new Intent(this, RegisterActivity.class));

                break;


        }

    }
}
