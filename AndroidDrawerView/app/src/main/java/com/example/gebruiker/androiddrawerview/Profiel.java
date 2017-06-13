package com.example.gebruiker.androiddrawerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Profiel extends AppCompatActivity {

    private Button buttonLoguot;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiel);

        buttonLoguot = (Button) findViewById(R.id.buttonSignup);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void logout(View v)
    {
        //signOut();
        Intent logoutIntent = new Intent(this, MainActivity.class);
        startActivity(logoutIntent);
    }
    private void signOut(){
        firebaseAuth.signOut();
    }
}
