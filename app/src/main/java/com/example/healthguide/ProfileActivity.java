package com.example.healthguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    TextView  profileEmail, profileUsername,profilePassword;
    TextView  titleUserName;
    TextView weightNo,lengthNo,ageNo;
    Button editProfile,removeProfile;
    FirebaseDatabase database;
    DatabaseReference reference;
    String userName , email , password , age ,weight , length;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        userName=intent.getStringExtra("username");
        email=intent.getStringExtra("email");
        password=intent.getStringExtra("password");
        age=intent.getStringExtra("age");
        weight=intent.getStringExtra("weight");
        length=intent.getStringExtra("length");





        profileUsername =  findViewById(R.id.profileUsername);
        profileEmail = findViewById(R.id.profileEmail);
        profilePassword =  findViewById(R.id.profilePassword);
        titleUserName = findViewById(R.id.titleUserName);
        weightNo = findViewById(R.id.weightNo);
        lengthNo = findViewById(R.id.lengthNo);
        ageNo =  findViewById(R.id.ageNo);
        editProfile = findViewById(R.id.editButton);
        removeProfile = findViewById(R.id.removeButton);

        showUserData();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });

        removeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");


                String username = profileUsername.getText().toString();
                String email = profileEmail.getText().toString();
                String password = profilePassword.getText().toString();
                String age = ageNo.getText().toString();
                String weight = weightNo.getText().toString();
                String length = lengthNo.getText().toString();


                HelperClass helperClass = new HelperClass(username,  email, password,age,  weight , length);
                reference.child(username).removeValue();

                Toast.makeText(ProfileActivity.this, "You have successfully deleted the account!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, SignupActivity.class);
                startActivity(intent);


            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);






        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.calc:
                        Intent intentCalc=new Intent(getApplicationContext(),CalcActivity.class);
                        intentCalc.putExtra("username",userName);
                        intentCalc.putExtra("email",email);
                        intentCalc.putExtra("password",password);
                        intentCalc.putExtra("age",age);
                        intentCalc.putExtra("weight",weight);
                        intentCalc.putExtra("length",length);
                        startActivity(intentCalc);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        return true;
                    case R.id.Exercises:
                        Intent intentExercises=new Intent(getApplicationContext(), ExercisesActivity.class);
                        intentExercises.putExtra("username",userName);
                        intentExercises.putExtra("email",email);
                        intentExercises.putExtra("password",password);
                        intentExercises.putExtra("age",age);
                        intentExercises.putExtra("weight",weight);
                        intentExercises.putExtra("length",length);
                        startActivity(intentExercises);
                        overridePendingTransition(0,0);
                        return true;



                }
                return false;
            }
        });

    }
    public void showUserData(){



        titleUserName.setText(userName);
        profileEmail.setText(email);
        profileUsername.setText(userName);
        profilePassword.setText(password);
        weightNo.setText(weight);
        lengthNo.setText(length);
        ageNo.setText(age);


    }

    public void passUserData() {
        String userUsername = profileUsername.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    String weightFromDB = snapshot.child(userUsername).child("weight").getValue(String.class);
                    String lengthFromDB = snapshot.child(userUsername).child("length").getValue(String.class);
                    String ageFromDB = snapshot.child(userUsername).child("age").getValue(String.class);


                    Intent intent = new Intent(com.example.healthguide.ProfileActivity.this, EditProfileActivity.class);

                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("password", passwordFromDB);
                    intent.putExtra("weight", weightFromDB);
                    intent.putExtra("length", lengthFromDB);
                    intent.putExtra("age", ageFromDB);


                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }}
