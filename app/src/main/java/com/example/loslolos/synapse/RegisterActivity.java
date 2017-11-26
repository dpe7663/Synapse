package com.example.loslolos.synapse;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //initialize variables from the elements in activity_register.xml
    EditText editTextEmail;
    EditText editTextPassword;

    ProgressBar progressBar;

    //reference for Firebase authorization
    private FirebaseAuth mAuth;

    //default minimum password length in Firebase is 6 characters, set as a final variable
    final int MIN_PASSWORD_LENGTH = 6;

    //Automatically created method for activity_register.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //set variables equal to references for the EditText fields and ProgressBar
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //set variable equal to an instance of the Firebase Authorization
        mAuth = FirebaseAuth.getInstance();

        //give onClickListeners to buttonRegister and textViewLogin
        //see the bottom of this file to see the onCLick method
        findViewById(R.id.buttonRegister).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    //registerUser() method handles all of the options for a user registering for Synapse
    //with their Email and Password
    private void registerUser() {

        //set variables equal to what the user enters in for their Email and Password
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //if email is empty...
        //error message comes up
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        //if the email is not a valid email...
        //error message comes up
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        //if password is empty...
        //error message comes up
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        //if password is less than 6 characters...
        //error message comes up
        if (password.length() < MIN_PASSWORD_LENGTH) {
            editTextPassword.setError("Minimum length of password should be 6");
        }

        //progressBar is set to visible once user presses the register button
        progressBar.setVisibility(View.VISIBLE);

        //Added an On Complete Listener when the user has entered an email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //progressBar is set to Gone, it goes away
                        progressBar.setVisibility(View.GONE);

                        //if user is registered successfully...
                        if (task.isSuccessful()) {

                            finish();

                            //user is successfully registered and logged in
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                            //start AccountInformationActivity activity
                            startActivity(new Intent(RegisterActivity.this, AccountInformationActivity.class));

                            //clears all open activities on top of stack, which would be signup/login activity.
                            //important because if user presses Back button, it will send them back to signup/login activity screens
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        }

                        //if user is not registered successfully...
                        else {
                            //if email is already registered...
                            //pop up comes up saying so
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_SHORT).show();
                            }

                            //Otherwise...it pops up saying to try again
                            else {
                                //Toast.makeText(getApplicationContext(), "Could not register, please try again", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }

    //onClick method for the Register Button and the TextView
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //case for pressing buttonRegister. If the button is pressed, the
            //registerUser() method is called
            case R.id.buttonRegister:
                registerUser();
                break;

            //case for pressing textViewLogin. If this is pressed,
            //it takes the user back to the LoginActivity page
            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
