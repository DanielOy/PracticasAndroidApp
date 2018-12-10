package com.example.danny.apprepositorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);
        Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
    }
    public void Registarse (View view){
        Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
        startActivity(i);
    }
    public void Entrar (View view){
        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);
    }
}
