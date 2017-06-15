package com.example.gebruiker.androiddrawerview;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Gebruiker on 15-6-2017.
 */

public class TestClass {

    public static FirebaseUser User;

    public static void GetEmail()
    {
        Log.d("something: ", User.getEmail());
    }
}
