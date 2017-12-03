package com.example.loslolos.synapse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

public class EditProfile extends AppCompatActivity {
    EditText editTextAbout;
    String about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        /**/
        editTextAbout = (EditText) findViewById(R.id.editTextAboutEdit);

        /*Creates an update profile button*/
        Button updateProfileBtn = (Button) findViewById(R.id.updateProfileButton);

        /*Creates an onClick Listener for the Update Profile Button*/
        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets the string from the about editText and stores it in the about variable
                about = editTextAbout.getText().toString();

                /*Creates a new file named about.txt making it public for the user and other
                apps to see it but able to edit it*/
                File file = new File(getExternalFilesDir(null), "about.txt");

                /*Exception thrown to verify the file is getting created correctly and in the
                right path where it should go. It is also available for later use in other activities.
                 */
                try {
                    /*Verifies if File exists in the system, to delete the file*/
                    if(file.exists()){
                        file.delete();
                    }
                    FileOutputStream outputStream = new FileOutputStream(file, true);
                    outputStream.write(about.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
            }
        });
    }
}
