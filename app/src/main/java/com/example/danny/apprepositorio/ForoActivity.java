package com.example.danny.apprepositorio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danny.apprepositorio.entidades.Foro;
import com.example.danny.apprepositorio.utilidades.UtilidadesForo;

import java.util.ArrayList;

public class ForoActivity extends AppCompatActivity
        implements ForoAportacionesFragment.OnFragmentInteractionListener,
        ForoPrincipalFragment.OnFragmentInteractionListener
{

    ListView listViewForo;
    ArrayList<String> listaInformacion;
    ArrayList<Foro> listaForo;
    ConexionSQLiteHelper conn;

    ForoPrincipalFragment fragmentPrincipal;
    ForoAportacionesFragment fragmetAportaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        fragmentPrincipal=new ForoPrincipalFragment();
        fragmetAportaciones = new ForoAportacionesFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorrFragment,fragmentPrincipal).commit();

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);
        listViewForo = (ListView)findViewById(R.id.entradasForo);

        consultarListaForo();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewForo.setAdapter(adaptador);
        //Comentario
        listViewForo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "id: "+listaForo.get(position).getId()+"\n";
                informacion+="Titulo: "+listaForo.get(position).getTitulo()+"\n";
                informacion+="Autor: "+listaForo.get(position).getAutor();

                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void consultarListaForo() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Foro foro =null;
        listaForo=new ArrayList<Foro>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+UtilidadesForo.TABLA_FORO,null);

        while (cursor.moveToNext()){
            foro = new Foro();
            foro.setId(cursor.getInt(0));
            foro.setTitulo(cursor.getString(1));
            foro.setAutor(cursor.getString(2));

            listaForo.add(foro);
        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0;i<listaForo.size();i++){
            listaInformacion.add(listaForo.get(i).getId() +" - "+
                    listaForo.get(i).getTitulo());
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onclickForo(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId())
        {
            case R.id.btnPrincipal:
                transaction.replace(R.id.contenedorrFragment,fragmentPrincipal);
                break;
            case R.id.btnAportaciones:
                transaction.replace(R.id.contenedorrFragment,fragmetAportaciones);
                break;
        }
        transaction.commit();
    }

    public void onclickAdd(View view) {
        Intent add = new Intent(getApplicationContext(),AddForoActivity.class);
        startActivity(add);
    }
}
