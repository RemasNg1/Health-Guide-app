package com.example.healthguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView name;
    String username;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userName , email , password , age ,weight , length;

        reference= FirebaseDatabase.getInstance().getReference();


       // name= findViewById(R.id.UserName);

        showData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        Intent intent = getIntent();
        userName=intent.getStringExtra("username");
        email=intent.getStringExtra("email");
        password=intent.getStringExtra("password");
        age=intent.getStringExtra("age");
        weight=intent.getStringExtra("weight");
        length=intent.getStringExtra("length");












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
                        Intent intentProfile=new Intent(getApplicationContext(),ProfileActivity.class);
                        intentProfile.putExtra("username",userName);
                        intentProfile.putExtra("email",email);
                        intentProfile.putExtra("password",password);
                        intentProfile.putExtra("age",age);
                        intentProfile.putExtra("weight",weight);
                        intentProfile.putExtra("length",length);
                        startActivity(intentProfile);
                        overridePendingTransition(0,0);
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
    public void showData (){
        Intent intent = getIntent();
        name=(TextView) findViewById(R.id.UserName);
         username=intent.getStringExtra("username");
        name.setText(username);

    }

}
