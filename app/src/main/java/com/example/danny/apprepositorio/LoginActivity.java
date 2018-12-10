package com.example.danny.apprepositorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //comentario
    }
    public void Registarse (View view){
        Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
        startActivity(i);
    }
}
