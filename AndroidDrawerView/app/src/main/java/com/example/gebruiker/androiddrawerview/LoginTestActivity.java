package com.example.gebruiker.androiddrawerview;

import android.accounts.Account;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gebruiker.androiddrawerview.Classes.StaticAccount;

public class LoginTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);
    }

    public void Login(View e)
    {
        EditText EDname = (EditText)findViewById(R.id.tbName);
        EditText EDpassword = (EditText)findViewById(R.id.tbPassword);

        String name = EDname.getText().toString();
        String password = EDpassword.getText().toString();

        StaticAccount.Login(name, password);
    }

}
