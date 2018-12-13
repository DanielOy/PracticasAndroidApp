package com.example.danny.apprepositorio;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danny.apprepositorio.entidades.Aportaciones;
import com.example.danny.apprepositorio.entidades.Foro;
import com.example.danny.apprepositorio.utilidades.UtilidadesAportaciones;
import com.example.danny.apprepositorio.utilidades.UtilidadesForo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ForoTopicActivity extends AppCompatActivity {

    TextView titulo, autor, descripcion;
    ImageView imagen;
    Button ad;
    String filead, t,a,d,i;
    MultiAutoCompleteTextView multi;

    ListView listViewAportaciones;
    ArrayList<String> listaInformacion;
    ArrayList<Aportaciones> listaAportaciones;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_topic);

        filead = "";

        titulo = (TextView)findViewById(R.id.txtTitulo);
        autor = (TextView)findViewById(R.id.txtAutor);
        descripcion = (TextView)findViewById(R.id.txtDescripcion);
        imagen = (ImageView)findViewById(R.id.imgLenguaje);
        ad = (Button)findViewById(R.id.btnAduntarCodigo);
        multi = (MultiAutoCompleteTextView)findViewById(R.id.multiComentario);

        listViewAportaciones = (ListView)findViewById(R.id.listComentarios);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
             t =(String) b.get("Titulo");
             a =(String) b.get("Autor");
             d =(String) b.get("Descripcion");
             i =(String) b.get("Plataforma");
             id=b.getInt("Id");
            titulo.setText(t);
            autor.setText(a);
            descripcion.setText(d);
            switch (i){
                case "Java":
                    Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.java_b);
                    imagen.setImageBitmap(bImage);
                    break;
                case "C#":
                    Bitmap bImage2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.cplus_b);
                    imagen.setImageBitmap(bImage2);
                    break;
                case "Python":
                    Bitmap bImage3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.python_b);
                    imagen.setImageBitmap(bImage3);
                    break;
                case "JS":
                    Bitmap bImage4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.javascript_b);
                    imagen.setImageBitmap(bImage4);
                    break;
                case "Android":
                    Bitmap bImage5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.android_b);
                    imagen.setImageBitmap(bImage5);
                    break;
                case "Escritorio":
                    Bitmap bImage6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.escritorio_b);
                    imagen.setImageBitmap(bImage6);
                    break;
                case "Web":
                    Bitmap bImage7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.web_b);
                    imagen.setImageBitmap(bImage7);
                    break;
                default:
                    imagen.setImageResource(R.drawable.logo2);
                    break;
            }
            consultarlist();
            //Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
        }

    }

    public void onclickFile(View view) {
        Intent file = new Intent(getApplicationContext(),UploadActivity.class);
        startActivityForResult(file,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==Activity.RESULT_OK){
                String result = data.getStringExtra("datos");
                filead = result;
                Drawable draw = getDrawable(R.drawable.ic_action_file);
                //ad.setCompoundDrawables(draw,null,null,null);
                ad.setBackground(draw);
                Toast.makeText(this, "Archivo adjuntado", Toast.LENGTH_SHORT).show();
            }if(resultCode==Activity.RESULT_CANCELED){
                Toast.makeText(this, "Archivo no adjuntado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSend(View view) {
        try {
            registrarForo();
            Toast.makeText(this, "Comentario Agregado", Toast.LENGTH_SHORT).show();

            Intent vent = new Intent(getApplicationContext(),ForoTopicActivity.class);
            vent.putExtra("Autor",a);
            vent.putExtra("Titulo",t);
            vent.putExtra("Descripcion",d);
            vent.putExtra("Plataforma",i);
            vent.putExtra("Id",id);
            startActivity(vent);
            finish();
        }catch (Exception ex){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    private void registrarForo() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_app",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        Date curent = Calendar.getInstance().getTime();

        ContentValues values = new ContentValues();
        values.put(UtilidadesAportaciones.CAMPO_ID,id);
        values.put(UtilidadesAportaciones.CAMPO_USUARIO,"user");
        values.put(UtilidadesAportaciones.CAMPO_CODIGO,filead);
        values.put(UtilidadesAportaciones.CAMPO_PLATAFORMA,i);
        values.put(UtilidadesAportaciones.CAMPO_DESCRIPCION,multi.getText().toString());
        values.put(UtilidadesAportaciones.CAMPO_FECHA, curent.toString());

        Long idResultante = db.insert(UtilidadesAportaciones.TABLA_APORTACIONES,UtilidadesAportaciones.CAMPO_ID,values);

        Toast.makeText(this, "ID registro:"+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void consultarListaForo() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_app",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Aportaciones aportaciones =null;
        listaAportaciones=new ArrayList<Aportaciones>();
        //select * from usuarios
        String[] parametros = new String[]{(id+"")};
        Cursor cursor = db.rawQuery("SELECT * FROM "+UtilidadesAportaciones.TABLA_APORTACIONES+" WHERE "+UtilidadesAportaciones.CAMPO_ID+
                "=?",parametros);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            aportaciones = new Aportaciones();
            aportaciones.setId(cursor.getInt(0));
            aportaciones.setUsuario(cursor.getString(1));
            aportaciones.setCodigo(cursor.getString(2));
            aportaciones.setPlataforma(cursor.getString(3));
            aportaciones.setDescripcion(cursor.getString(4));
            aportaciones.setFecha(cursor.getString(5));

            listaAportaciones.add(aportaciones);
        }

        obtenerLista();
    }

    public void consultarlist(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);

        try{
            consultarListaForo();

            ArrayAdapter adaptador = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listaInformacion);
            listViewAportaciones.setAdapter(adaptador);
            //Comentario
            listViewAportaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String informacion = "";//"id: "+listaForo.get(position).getId()+"\n";
                    //informacion+="Titulo: "+listaForo.get(position).getTitulo()+"\n";
                    //informacion+="Descripcion: "+listaForo.get(position).getDescripcion();
                    //String informacion = "Manten precionado para ver detalles";
                    //Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_SHORT).show();
                }
            });
            /*listViewForo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getActivity(),ForoTopicActivity.class);
                    i.putExtra("Autor",listaForo.get(position).getAutor());
                    i.putExtra("Titulo",listaForo.get(position).getTitulo());
                    i.putExtra("Descripcion",listaForo.get(position).getDescripcion());
                    i.putExtra("Plataforma",listaForo.get(position).getLenguaje());
                    i.putExtra("Id",listaForo.get(position).getId());
                    startActivity(i);
                    return false;
                }
            });*/
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error we", Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0;i<listaAportaciones.size();i++){
            listaInformacion.add(listaAportaciones.get(i).getUsuario() +" - "+
                    listaAportaciones.get(i).getDescripcion());
        }
    }

}
