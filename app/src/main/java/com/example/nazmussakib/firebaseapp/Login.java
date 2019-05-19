package com.example.nazmussakib.firebaseapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import RoomDatabae.user;
import RoomDatabae.userDatabase;
import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {

    private TextInputLayout userEmail,userPassword;
    private Button loginButton,haveNotAccount;
    private Toolbar toolbar;
    private String email,password;

    private static userDatabase users;

    public static boolean userLogedIn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar2);
        userEmail = findViewById(R.id.userEmailLogin);
        userPassword= findViewById(R.id.userPasswordLogin);
        loginButton = findViewById(R.id.logButton);
        haveNotAccount = findViewById(R.id.haventAccount);
        users = Room.databaseBuilder(this,userDatabase.class,"userdb").allowMainThreadQueries().build();
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validition(userEmail) && validition(userPassword)) {
                    if(login())
                    {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toasty.error(Login.this,"Incorrect Email or Password",Toasty.LENGTH_SHORT).show();
                    }
                }
            }
        });

        haveNotAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });

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

    private boolean login()
    {
        email = userEmail.getEditText().getText().toString().trim();
        password = userPassword.getEditText().getText().toString().trim();

        user user;
        user = users.userDao().getUserInfo(email, password);

        if (user.getUserEmail().equals(email)
                && user.getPassword().equals(password)) {
             userLogedIn = true;

            print(user.getUserName() + " Login Successfully\n your ID: " + user.getUid());
            userEmail.getEditText().setText("");
            userPassword.getEditText().setText("");
            return true;
        }
        return false;


    }

    private void print(Object o)
    {
        Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
    }


}
