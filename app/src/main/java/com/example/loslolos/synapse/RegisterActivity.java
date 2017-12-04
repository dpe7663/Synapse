//import android.support.v7.app.AppCompatActivity;
package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //initialize variables that will reference the elements in activity_register.xml
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextUsername;
    EditText editTextAbout;

    AutoCompleteTextView autoFOI1;
    AutoCompleteTextView autoFOI2;
    AutoCompleteTextView autoFOI3;
    AutoCompleteTextView autoFOI4;
    AutoCompleteTextView autoFOI5;

    Spinner spinnerMajors;

    //Initialize String variables that will eventually be entered into Firebase when a user registers
    String email;
    String password;
    String firstname;
    String lastname;
    String username;
    String about;
    String major;
    String firstFOI;
    String secondFOI;
    String thirdFOI;
    String fourthFOI;
    String fifthFOI;

    ProgressBar progressBar;

    //reference for Firebase authorization
    private FirebaseAuth mAuth;

    //reference for the Firebase Database
    DatabaseReference refDatabase;

    //default minimum password length in Firebase is 6 characters, set as a final variable
    final int MIN_PASSWORD_LENGTH = 6;

    //Automatically created method for activity_register.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //set variables equal to references for the EditText/AutoCompleteTextView fields and ProgressBar
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);

        editTextAbout = (EditText) findViewById(R.id.editTextAbout);

        //FOI is abbreviated for Field of Interest
        autoFOI1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteFOI1);
        autoFOI2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteFOI2);
        autoFOI3 = (AutoCompleteTextView) findViewById(R.id.autoCompleteFOI3);
        autoFOI4 = (AutoCompleteTextView) findViewById(R.id.autoCompleteFOI4);
        autoFOI5 = (AutoCompleteTextView) findViewById(R.id.autoCompleteFOI5);

        //A Spinner is like a drop down menu. Used later to select a Major
        spinnerMajors = (Spinner) findViewById(R.id.spinnerMajors);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //set variable equal to an instance of the Firebase Authorization
        mAuth = FirebaseAuth.getInstance();

        //make a reference to the Firebase Database so that we can pull values from it
        //to populate the spinners for the majors, and fields of interest
        refDatabase = FirebaseDatabase.getInstance().getReference();

        //give onClickListeners to buttonRegister and textViewLogin
        //see the bottom of this file to see the onCLick method
        findViewById(R.id.buttonRegister).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

        //Take the Database reference and add an event listener to the "Majors" child.
        //This ValueListener() allows the Majors Spinner (drop down menu) to populate
        //with the table of majors in Firebase.
        refDatabase.child("Majors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> majors = new ArrayList<String>();

                //This Enhanced For Loop basically says, "For each DataSnapshot data in dataSnapShot,
                //add the values of the children titled "Name" to the majors list
                for (DataSnapshot majorSnapshot : dataSnapshot.getChildren()) {
                    String majorName = majorSnapshot.child("Name").getValue(String.class);
                    majors.add(majorName);
                }

                ArrayAdapter<String> majorsAdapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, majors);
                majorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMajors.setAdapter(majorsAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Take the Database reference and add an event listener to the "Fields of Interest" child.
        //This ValueListener() allows the Field of Interest AutoCompleteTextViews to populate
        //with the table of interests in Firebase.
        refDatabase.child("Fields of Interest").addValueEventListener(new ValueEventListener() {
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

                ArrayAdapter<String> interestsAdapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, interests);
                interestsAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

                //These next lines add the list of interests from Firebase into each variable
                autoFOI1.setAdapter(interestsAdapter);
                autoFOI2.setAdapter(interestsAdapter);
                autoFOI3.setAdapter(interestsAdapter);
                autoFOI4.setAdapter(interestsAdapter);
                autoFOI5.setAdapter(interestsAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //registerUser() method handles all of the options for a user registering for Synapse
    //with their Email and Password
    private void registerUser() {

        //set variables equal to what the user enters in for their Email and Password
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        firstname = editTextFirstName.getText().toString().trim();
        lastname = editTextLastName.getText().toString().trim();
        username = editTextUsername.getText().toString().trim();

        about = editTextAbout.getText().toString().trim();

        major = spinnerMajors.getSelectedItem().toString().trim();

        firstFOI = autoFOI1.getText().toString().trim();
        secondFOI = autoFOI2.getText().toString().trim();
        thirdFOI = autoFOI3.getText().toString().trim();
        fourthFOI = autoFOI4.getText().toString().trim();
        fifthFOI = autoFOI5.getText().toString().trim();

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

                            //Initialize a String variable called user_id, which stores the current user's
                            //unique ID from email authentication
                            String user_id = mAuth.getCurrentUser().getUid();

                            /*Initialize a DatabaseReference variable called current_user_db, which
                            stores the place where the user's account info will go to in Firebase
                            when the user registers. It finds the "Users" child in the database,
                            and stores the user_id as a child inside of it*/
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            //Make a HashMap called newPost which will store the values that the
                            //user enters for their first name, last name, and username
                            Map newPost = new HashMap();
                            newPost.put("First Name", firstname);
                            newPost.put("Last Name", lastname);
                            newPost.put("Username", username);
                            newPost.put("About", about);
                            newPost.put("Email", email);
                            newPost.put("Password", password);
                            newPost.put("Major", major);
                            newPost.put("First Field of Interest", firstFOI);
                            newPost.put("Second Field of Interest", secondFOI);
                            newPost.put("Third Field of Interest", thirdFOI);
                            newPost.put("Fourth Field of Interest", fourthFOI);
                            newPost.put("Fifth Field of Interest", fifthFOI);

                            /*Take the current_user_db DatabaseReference variable and set it's
                            values equal to the newPost HashMap. This line stores the first name,
                            last name, and username inside of the user_id child in Firebase*/
                            current_user_db.setValue(newPost);

                            //clears all open activities on top of stack, which would be signup/login activity.
                            //important because if user presses Back button, it will send them back to signup/login activity screens
                            finish();

                            //user is successfully registered and logged in
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                            //start UserProfileActivity
                            startActivity(new Intent(RegisterActivity.this, UserProfileActivity.class));

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