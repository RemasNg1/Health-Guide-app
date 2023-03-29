package com.example.healthguide;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class HelperClass implements DatabaseReference.CompletionListener {

    String username , email, password,age, weight, length;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }


    public HelperClass(String username, String email, String password,String age, String weight, String length) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.length = length;
    }

    public HelperClass() {
    }

    @Override
    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

    }
}