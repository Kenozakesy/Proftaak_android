package com.example.gebruiker.androiddrawerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth =firebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
