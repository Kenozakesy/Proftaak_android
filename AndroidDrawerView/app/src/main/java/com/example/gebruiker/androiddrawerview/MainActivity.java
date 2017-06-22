package com.example.gebruiker.androiddrawerview;

import android.content.Intent;
import android.net.Uri;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.*;

import static android.R.id.toggle;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference userDatabaseReference;
    //

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button =(Button) findViewById(R.id.buttonKlantenservice);
        button.setBackgroundColor(getResources().getColor(R.color.buttonColor));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

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


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
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

        DatabaseReference userDatabaseReference = database.getReference().child("Account").child(currentUser.getUid());
        userDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                HashMap<String, Object> hashMap = new HashMap();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    hashMap.put(ds.getKey(), ds.getValue());
                }
                updateGUI(hashMap);
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
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

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    public void updateGUI(HashMap map) {
        int leeftijd = safeLongToInt((long)map.get("Leeftijd"));
        TableRow trKlant = (TableRow) findViewById(R.id.trKlant);
        trKlant.setVisibility(View.GONE);


        if (map.get("Geslacht").equals("Vrouw")) {

            if (leeftijd > 15 && leeftijd < 25) {

            } else if (leeftijd > 25 && leeftijd < 60) {
                ImageView imgHeader = (ImageView) findViewById(R.id.imageHeader);
                ImageView imgMainPicture = (ImageView) findViewById(R.id.imageMainPicture);
                ImageView imgBanner = (ImageView) findViewById(R.id.imageBanner);
                ImageView imgProduct1 = (ImageView) findViewById(R.id.imageProduct1);
                ImageView imgProduct2 = (ImageView) findViewById(R.id.imageProduct2);
                ImageView imgProduct3 = (ImageView) findViewById(R.id.imageProduct3);
                ImageView imgProduct4 = (ImageView) findViewById(R.id.imageProduct4);
                ImageView imgProduct5 = (ImageView) findViewById(R.id.imageProduct5);

                imgHeader.setImageResource(R.drawable.dame38_header);
                imgMainPicture.setImageResource(R.drawable.dame38_mainpicture);
                imgBanner.setImageResource(R.drawable.dame38_banner);
                imgProduct1.setImageResource(R.drawable.dame38_product1);
                imgProduct2.setImageResource(R.drawable.dame38_product2);
                imgProduct3.setImageResource(R.drawable.dame38_product3);
                imgProduct4.setImageResource(R.drawable.dame38_product4);
                imgProduct5.setImageResource(R.drawable.dame38_product5);

            } else {

            }


        } else {
            if (leeftijd > 15 && leeftijd < 25) {
                ImageView imgHeader = (ImageView) findViewById(R.id.imageHeader);
                ImageView imgMainPicture = (ImageView) findViewById(R.id.imageMainPicture);
                ImageView imgBanner = (ImageView) findViewById(R.id.imageBanner);
                ImageView imgProduct1 = (ImageView) findViewById(R.id.imageProduct1);
                ImageView imgProduct2 = (ImageView) findViewById(R.id.imageProduct2);
                ImageView imgProduct3 = (ImageView) findViewById(R.id.imageProduct3);
                ImageView imgProduct4 = (ImageView) findViewById(R.id.imageProduct4);
                ImageView imgProduct5 = (ImageView) findViewById(R.id.imageProduct5);

                imgHeader.setImageResource(R.drawable.student21_header);
                imgMainPicture.setImageResource(R.drawable.student21_mainpicture);
                imgBanner.setImageResource(R.drawable.student21_banner);
                imgProduct1.setImageResource(R.drawable.student21_product1);
                imgProduct2.setImageResource(R.drawable.student21_product2);
                imgProduct3.setImageResource(R.drawable.student21_product3);
                imgProduct4.setImageResource(R.drawable.student21_product4);
                imgProduct5.setImageResource(R.drawable.student21_product5);

            } else if (leeftijd > 25 && leeftijd < 60) {
                ImageView imgHeader = (ImageView) findViewById(R.id.imageHeader);
                ImageView imgMainPicture = (ImageView) findViewById(R.id.imageMainPicture);
                ImageView imgBanner = (ImageView) findViewById(R.id.imageBanner);
                ImageView imgProduct1 = (ImageView) findViewById(R.id.imageProduct1);
                ImageView imgProduct2 = (ImageView) findViewById(R.id.imageProduct2);
                ImageView imgProduct3 = (ImageView) findViewById(R.id.imageProduct3);
                ImageView imgProduct4 = (ImageView) findViewById(R.id.imageProduct4);
                ImageView imgProduct5 = (ImageView) findViewById(R.id.imageProduct5);

                imgHeader.setImageResource(R.drawable.zakenman54_header);
                imgMainPicture.setImageResource(R.drawable.zakenman54_mainpicture);
                imgBanner.setImageResource(R.drawable.zakenman54_banner);
                imgProduct1.setImageResource(R.drawable.zakenman54_product1);
                imgProduct2.setImageResource(R.drawable.zakenman54_product2);
                imgProduct3.setImageResource(R.drawable.zakenman54_product3);
                imgProduct4.setImageResource(R.drawable.zakenman54_product4);
                imgProduct5.setImageResource(R.drawable.zakenman54_product5);

            } else {

            }
        }
    }


}
