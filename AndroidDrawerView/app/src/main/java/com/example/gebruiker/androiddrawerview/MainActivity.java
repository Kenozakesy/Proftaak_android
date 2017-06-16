package com.example.gebruiker.androiddrawerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference userDatabaseReference;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.nav_header_main, null);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
         Button loginButton = (Button) view.findViewById(R.id.buttonInlog);
        loginButton.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();

        if (getUser()) {
            database = FirebaseDatabase.getInstance();
            addDatabaseEvent();
        }


//        updateUI();

    }

    //    public void onStart(){
//        super.onStart();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
    private boolean getUser() {
        currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) return true;
        else return false;
    }

    private void addDatabaseEvent() {

        DatabaseReference userDatabaseReference = database.getReference("projectbol-86135").child("Account").child(currentUser.getUid());
        userDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                HashMap<String, Object> hashMap = new HashMap();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    hashMap.put(ds.getKey(), ds.getValue());
                    Log.d("val", ds.getKey() + " is: " + (String) ds.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("er", "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View v) {
        Intent loginItent = new Intent(this, LoginActivity.class);
        startActivity(loginItent);
    }

    public void logout(View v) {
        signOut();
        Intent logoutIntent = new Intent(this, MainActivity.class);
        startActivity(logoutIntent);
    }

    private void signOut() {
        firebaseAuth.signOut();
    }
}
