package com.example.danny.apprepositorio;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.danny.apprepositorio.utilidades.Utilidades;
import com.example.danny.apprepositorio.utilidades.UtilidadesForo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AddForoActivity extends AppCompatActivity {

    EditText titulo, descripcion;
    Spinner lenguajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_foro);
        titulo = (EditText)findViewById(R.id.edtTitulo);
        descripcion =(EditText)findViewById(R.id.edtDescripcion);
        lenguajes = (Spinner) findViewById(R.id.spPlataforma);
    }

    public void onClickAceptar(View view) {
        try {
            registrarForo();
            //Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(getApplicationContext(),ForoActivity.class);
            startActivity(in);
            finish();
        }catch (Exception ex){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void registrarForo() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_app",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        Random r = new Random();
        int i = r.nextInt();

        Date curent = Calendar.getInstance().getTime();

        ContentValues values = new ContentValues();
        values.put(UtilidadesForo.CAMPO_ID,(i));
        values.put(UtilidadesForo.CAMPO_TITULO,titulo.getText().toString());
        values.put(UtilidadesForo.CAMPO_LENGUAJE,lenguajes.getSelectedItem().toString());
        values.put(UtilidadesForo.CAMPO_DESCRIPCION,descripcion.getText().toString());
        values.put(UtilidadesForo.CAMPO_AUTOR,"user");
        values.put(UtilidadesForo.CAMPO_FECHA, curent.toString());

        Long idResultante = db.insert(UtilidadesForo.TABLA_FORO,UtilidadesForo.CAMPO_ID,values);

        Toast.makeText(this, "ID registro:"+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void onClickCancelar(View view) {
        Toast.makeText(this, "Agregar entrada cancelado", Toast.LENGTH_SHORT).show();
        onBackPressed();
        finish();
    }
}
