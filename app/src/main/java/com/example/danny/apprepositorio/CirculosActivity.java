package com.example.danny.apprepositorio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danny.apprepositorio.utilidades.UtilidadesCirculos;

public class CirculosActivity extends AppCompatActivity implements CrearCirculosFragment.OnFragmentInteractionListener,
        InicioCirculosFragment.OnFragmentInteractionListener{

    ConexionSQLiteHelper conn;
    CrearCirculosFragment crearCirculosFragment;
    InicioCirculosFragment inicioCirculosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulos);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);
        Toast.makeText(this, "Conexion realziada", Toast.LENGTH_SHORT).show();

        crearCirculosFragment = new CrearCirculosFragment();
        inicioCirculosFragment = new InicioCirculosFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorfragmentscirculos,inicioCirculosFragment).commit();
    }

        public void onClick(View view){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (view.getId()){
                case R.id.btnCreate:
                    transaction.replace(R.id.contenedorfragmentscirculos,crearCirculosFragment);
                    break;
                case R.id.btnStart:
                    transaction.replace(R.id.contenedorfragmentscirculos,inicioCirculosFragment);
                    break;
                case R.id.btnBuscarC:
                    break;
            }
            transaction.commit();
        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
