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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danny.apprepositorio.entidades.Circulos;
import com.example.danny.apprepositorio.utilidades.UtilidadesCirculos;

import java.util.ArrayList;

public class CirculosActivity extends AppCompatActivity implements
        CrearCirculosFragment.OnFragmentInteractionListener,
        InicioCirculosFragment.OnFragmentInteractionListener,
        ListaCirculosFragment.OnFragmentInteractionListener,
        MisCirculosFragment.OnFragmentInteractionListener
{

    ConexionSQLiteHelper conn;
    ListView listViewCirculos;
    ArrayList<String> circulos;
    ArrayList<Circulos> listCirculos;

    CrearCirculosFragment crearCirculosFragment;
    InicioCirculosFragment inicioCirculosFragment;
    ListaCirculosFragment listaCirculosFragment;
    MisCirculosFragment misCirculosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulos);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);
        //Toast.makeText(this, "Conexion realziada", Toast.LENGTH_SHORT).show();

        crearCirculosFragment = new CrearCirculosFragment();
        inicioCirculosFragment = new InicioCirculosFragment();
        listaCirculosFragment = new ListaCirculosFragment();
        misCirculosFragment = new MisCirculosFragment();



        getSupportFragmentManager().beginTransaction().add(R.id.contenedorfragmentscirculos,misCirculosFragment).commit();

        listViewCirculos = (ListView)findViewById(R.id.gruposCirculos);

        consultarListaForo();

        try {
            ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,circulos);
            listViewCirculos.setAdapter(adaptador);
        }catch (Exception e ){
            //Toast.makeText(this, "Error: " +e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Comentario
        try{
        listViewCirculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "";
                informacion+="Nombre: "+listCirculos.get(position).getNombrecirculo()+"\n";
                informacion+="Categoria: "+listCirculos.get(position).getCategoria() + "\n";
                informacion+= "Descripcion" + listCirculos.get(position).getDescrip();

                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_SHORT).show();
            }
        });
        } catch (Exception ex){
           // Toast.makeText(this, "Error: " +ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void consultarListaForo() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Circulos foro =null;
        listCirculos=new ArrayList<Circulos>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+UtilidadesCirculos.TABLA_CIRCULOS,null);

        while (cursor.moveToNext()){
            foro = new Circulos();
            foro.setId(cursor.getInt(0));
            foro.setNombrecirculo(cursor.getString(1));
            foro.setCategoria(cursor.getString(2));
            foro.setDescrip(cursor.getString(3));

            listCirculos.add(foro);
        }

        obtenerLista();
    }

    private void obtenerLista() {
        circulos = new ArrayList<String>();
        for (int i=0;i<listCirculos.size();i++){
            circulos.add(listCirculos.get(i).getId() +" - "+
                    listCirculos.get(i).getNombrecirculo()+"---"+
                    listCirculos.get(i).getCategoria() + "---"+
                    listCirculos.get(i).getNombrecirculo());

        }
    }

        public void onClick(View view){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (view.getId()){
                case R.id.btnCreate:
                    transaction.replace(R.id.contenedorfragmentscirculos,crearCirculosFragment);
                    break;
                case R.id.btnStart:
                    transaction.replace(R.id.contenedorfragmentscirculos,misCirculosFragment);
                    break;
                case R.id.btnBuscarC:
                    transaction.replace(R.id.contenedorfragmentscirculos,listaCirculosFragment);
                    break;
            }
            transaction.commit();
        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
