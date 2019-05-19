package com.example.nazmussakib.firebaseapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import RoomDatabae.user;
import RoomDatabae.userDatabase;

public class SignUp extends AppCompatActivity {

    private TextInputLayout userName,userEmail,userPassword;
    private Button regButton,alreadyHaveAnAccount;
    private Toolbar toolbar;

    private String name,email,password;

    private static userDatabase users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbar = findViewById(R.id.toolbar);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userPassword= findViewById(R.id.userPassword);
        regButton = findViewById(R.id.regButton);
        alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAccount);
        toolbar.setTitle("Create Account");
        setSupportActionBar(toolbar);

        users = Room.databaseBuilder(this,userDatabase.class,"userdb").allowMainThreadQueries().build();

        try {
            regButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validition(userName) && validition(userEmail) && validition(userPassword)) {
                        signUp();
                        startActivity(new Intent(SignUp.this,MainActivity.class));

                    }
                }
            });

            alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SignUp.this,Login.class));
                }
            });

        }
        catch (Exception e)
        {
            Log.e(getApplicationContext().toString(),e.toString());
        }

    }


    private boolean validition(TextInputLayout editText)
    {
        if(editText.getEditText().getText().toString().length()>0)
        {
            return true;
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;

    }

    private void signUp()
    {
        name = userName.getEditText().getText().toString().trim();
        email = userEmail.getEditText().getText().toString().trim();
        password = userPassword.getEditText().getText().toString().trim();

        user user = new user();

        user.setUserName(name);
        user.setUserEmail(email);
        user.setPassword(password);


        users.userDao().addUser(user);

        print(name+" added Successfully");

        userName.getEditText().setText("");
        userEmail.getEditText().setText("");
        userPassword.getEditText().setText("");


    }

    private void print(Object o)
    {
        Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
    }



}
