package com.example.healthguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    EditText ediEmail, ediUsername, ediPassword,ediWeight,ediLength,ediAge;
    Button saveButton;
    String  emailUser, usernameUser, passwordUser,weightUser,lengthUser,ageUser;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        ediUsername = findViewById(R.id.editUsername);
        ediEmail = findViewById(R.id.editEmail);
        ediPassword = findViewById(R.id.editPassword);
        ediWeight=findViewById(R.id.edWeight) ;
        ediLength=findViewById(R.id.edLength) ;
        ediAge=findViewById(R.id.edAge) ;
        saveButton = findViewById(R.id.saveButton);

        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateUsername() | !validatePassword()){

                } else {


                    if (isEmailChanged() | isPasswordChanged() | isWeightChanged() | isLengthChanged() | isAgeChanged()) {
                        Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                    }
                    checkUser();
                }


            }
        });

    }


    public Boolean validateUsername(){
        String val = ediUsername.getText().toString();
        if (val.isEmpty()){
            ediUsername.setError("Username cannot be empty");
            return false;
        } else {
            ediUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = ediPassword.getText().toString();
        if (val.isEmpty()){
            ediPassword.setError("Password cannot be empty");
            return false;
        } else {
            ediPassword.setError(null);
            return true;
        }
    }

    public boolean isEmailChanged(){
        if (!emailUser.equals(ediEmail.getText().toString())){
            reference.child(usernameUser).child("email").setValue(ediEmail.getText().toString());
            emailUser = ediEmail.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isPasswordChanged(){
        if (!passwordUser.equals(ediPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(ediPassword.getText().toString());
            passwordUser = ediPassword.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isWeightChanged(){
        if (!weightUser.equals(ediWeight.getText().toString())){
            reference.child(usernameUser).child("weight").setValue(ediWeight.getText().toString());
            weightUser = ediWeight.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isLengthChanged(){
        if (!lengthUser.equals(ediLength.getText().toString())){
            reference.child(usernameUser).child("length").setValue(ediLength.getText().toString());
            lengthUser = ediLength.getText().toString();
            return true;
        } else{
            return false;
        }
    }


    public boolean isAgeChanged(){
        if (!ageUser.equals(ediAge.getText().toString())){
            reference.child(usernameUser).child("age").setValue(ediAge.getText().toString());
            ageUser = ediAge.getText().toString();
            return true;
        } else{
            return false;
        }
    }



    public void showData(){
        Intent intent = getIntent();

        usernameUser = intent.getStringExtra("username");
        emailUser = intent.getStringExtra("email");
        passwordUser = intent.getStringExtra("password");
        weightUser = intent.getStringExtra("weight");
        lengthUser = intent.getStringExtra("length");
        ageUser = intent.getStringExtra("age");



        ediUsername.setText(usernameUser);
        ediEmail.setText(emailUser);
        ediPassword.setText(passwordUser);
        ediWeight.setText(weightUser);
        ediLength.setText(lengthUser);
        ediAge.setText(ageUser);


    } // end showData


    public void checkUser(){
        String userUsername = ediUsername.getText().toString().trim();
        String userPassword = ediPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    ediUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        ediUsername.setError(null);

                        //Pass the data using intent

                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String weightFromDB = snapshot.child(userUsername).child("weight").getValue(String.class);
                        String lengthFromDB = snapshot.child(userUsername).child("length").getValue(String.class);
                        String ageFromDB = snapshot.child(userUsername).child("age").getValue(String.class);


                        Intent intent = new Intent(EditProfileActivity.this,ProfileActivity.class);

                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("weight", weightFromDB);
                        intent.putExtra("length", lengthFromDB);
                        intent.putExtra("age", ageFromDB);

                        startActivity(intent);
                    }
                } else {
                    ediUsername.setError("Cannot change username!");
                    ediUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}