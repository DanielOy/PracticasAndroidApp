package com.example.danny.apprepositorio;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.danny.apprepositorio.utilidades.Utilidades;

import java.util.Random;

public class RegistroActivity extends AppCompatActivity {

    Button cancelar, aceptar;
    EditText matricula, email, pass,name;
    Spinner sem;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cancelar=(Button)findViewById(R.id.btnCancelar);
        aceptar=(Button)findViewById(R.id.btnAceptar);

        matricula=(EditText)findViewById(R.id.edtMatricula);
        email=(EditText)findViewById(R.id.edtCorreo);
        pass=(EditText)findViewById(R.id.edtContrasenia);
        name=(EditText)findViewById(R.id.edtUser);
        sem=(Spinner)findViewById(R.id.spSemestre);
        aSwitch=(Switch)findViewById(R.id.swCondiciones);
    }

    public void Cancelar(View view){
        onBackPressed();
        finish();
    }
    public void onClickInsertar(View v){
        try{
            if(!(matricula.getText().length()>0)) throw new Exception("Sin Matricula");
            if(!(email.getText().length()>0)) throw new Exception("Sin Email");
            if(!(pass.getText().length()>0)) throw new Exception("No se definio una contraseña");
            if(!(name.getText().length()>0)) throw new Exception("No se definio un nombre de usuario");
            if(!(aSwitch.isChecked())) throw new Exception("Favor de primero leer y aceptar los terminos y condiciones.");
        registrarUsuario();
            Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            finish();
        //Cancelar(v);
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void registrarUsuario() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_app",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        Random r = new Random();
        int i = r.nextInt(100);
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID,(i+""));
        values.put(Utilidades.CAMPO_MATRICULA,matricula.getText().toString());
        values.put(Utilidades.CAMPO_CORREO,email.getText().toString());
        values.put(Utilidades.CAMPO_CONTRASENIA,pass.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,name.getText().toString());
        values.put(Utilidades.CAMPO_SEMESTRE,sem.getSelectedItem().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIOS,Utilidades.CAMPO_ID,values);

        //Toast.makeText(this, "ID registro:"+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
}
