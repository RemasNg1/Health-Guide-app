package com.example.healthguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalcActivity extends AppCompatActivity {
    private EditText EditTextWeight , EditTextLength , EditTextAge;
    private Button calculate;
    private TextView result;
    private float op1,op2,op3,op4;
    String userName , email , password , age ,weight , length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Intent intent = getIntent();
        userName=intent.getStringExtra("username");
        email=intent.getStringExtra("email");
        password=intent.getStringExtra("password");
        age=intent.getStringExtra("age");
        weight=intent.getStringExtra("weight");
        length=intent.getStringExtra("length");


        EditTextWeight = (EditText) findViewById(R.id.witedtxt);
        EditTextLength = (EditText) findViewById(R.id.hiedtxt);
        EditTextAge = (EditText) findViewById(R.id.agedtxt);
        calculate = (Button) findViewById(R.id.calbtn);
        result = (TextView) findViewById(R.id.rslt);

        EditTextWeight.setText(weight);
        EditTextLength.setText(length);
        EditTextAge.setText(age);

        calculate.setOnClickListener(new View.OnClickListener() {
                                         @Override

                                         public void onClick(View v) {
                                             op1 = Float.parseFloat(EditTextWeight.getText().toString());
                                             op2 = Float.parseFloat(EditTextLength.getText().toString());
                                             op3 = Float.parseFloat(EditTextAge.getText().toString());
                                             op4 = ((op2 * (float) 6.25)+(op1*(float)10)-(op3*(float)5)+(float)5);
                                             result.setText(Float.toString(op4));


                                         }
                                     }
        );








        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calc);








        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.calc:
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
}



