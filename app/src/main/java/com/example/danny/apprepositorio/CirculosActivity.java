package com.example.danny.apprepositorio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danny.apprepositorio.utilidades.UtilidadesCirculos;

public class CirculosActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulos);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);
        Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
    }


}
