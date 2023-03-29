package com.example.healthguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExercisesActivity extends AppCompatActivity {
    String userName , email , password , age ,weight , length;


    int[] newArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        Intent intent = getIntent();
        userName=intent.getStringExtra("username");
        email=intent.getStringExtra("email");
        password=intent.getStringExtra("password");
        age=intent.getStringExtra("age");
        weight=intent.getStringExtra("weight");
        length=intent.getStringExtra("length");





        newArray = new int[] {

                R.id.bow_pose,R.id.bridge_pose,R.id.chair_pose,R.id.child_pose,R.id.cobbler_pose,R.id.cow_pose,R.id.playji_pose,R.id.pauseji_pose,R.id.crunches_pose,
                R.id.situp_pose,R.id.rotation_pose,R.id.twist_pose,R.id.windmill_pose,R.id.legup_pose,

        };


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Exercises);




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
                        return true;



                }
                return false;
            }
        });

    }
    public void Imagebuttonclicked(View view) {

        for (int i=0; i< newArray.length;i++){

            if (view.getId() == newArray[i]) {

                int value = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent = new Intent(ExercisesActivity.this, TimerActivity.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);

            }
        }

    }

}