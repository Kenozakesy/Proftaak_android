package com.example.gebruiker.androiddrawerview.Classes;

import com.example.gebruiker.androiddrawerview.Enums.Gender;
import com.example.gebruiker.androiddrawerview.Enums.Hobby;

/**
 * Created by Gebruiker on 9-6-2017.
 */

public class StaticAccount {

    //fields
    private static String name;
    private static String password;

    private static Gender gender;
    private static int age;
    private static Hobby hobby;
    //private static ;

    //properties
    public static String GetName() {return name;}
    public static String GetPassword() {return password;}
    public static Gender GetGender() {return gender;}
    public static int GetAge() {return age;}

    //methods
    public static void Login(String name, String password)
    {
        //haal hier dingen uit database op
    }
}
