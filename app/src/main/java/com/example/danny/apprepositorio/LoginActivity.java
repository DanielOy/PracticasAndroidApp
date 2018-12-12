package com.example.danny.apprepositorio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danny.apprepositorio.utilidades.Utilidades;

public class LoginActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);
        //Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        user = (EditText)findViewById(R.id.edtUser);
        pass = (EditText)findViewById(R.id.edtPass);
    }
    public void Registarse (View view){
        Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
        startActivity(i);
        finish();
    }
    public void Entrar (View view){
        consultar();
    }

    public void consultar(){
        try {
            if((user.getText().toString().length()>0)&&pass.getText().toString().length()>0)
            {
                SQLiteDatabase db = conn.getReadableDatabase();
                String[] parametros = {user.getText().toString(),pass.getText().toString()};
                String[] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_CONTRASENIA};
                String x, y;
                Cursor cursor = db.query(Utilidades.TABLA_USUARIOS, campos, Utilidades.CAMPO_NOMBRE + "=? AND " + Utilidades.CAMPO_CONTRASENIA+"=?", parametros, null, null, null);
                cursor.moveToFirst();
                x=cursor.getString(0);
                y=cursor.getString(1);
                cursor.close();
                if (x.equals(user.getText().toString())&&y.equals(pass.getText().toString()))
                {
                    //Abrimos la ventana
                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }else
            {
                Toast.makeText(this, "Favor de llenar los campos requeridos.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "El usuario no existe.", Toast.LENGTH_SHORT).show();
        }
    }
}
