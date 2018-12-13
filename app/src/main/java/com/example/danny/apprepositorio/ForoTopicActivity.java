package com.example.danny.apprepositorio;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ForoTopicActivity extends AppCompatActivity {

    TextView titulo, autor, descripcion;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_topic);

        titulo = (TextView)findViewById(R.id.txtTitulo);
        autor = (TextView)findViewById(R.id.txtAutor);
        descripcion = (TextView)findViewById(R.id.txtDescripcion);
        imagen = (ImageView)findViewById(R.id.imgLenguaje);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            String t =(String) b.get("Titulo");
            String a =(String) b.get("Autor");
            String d =(String) b.get("Descripcion");
            String i =(String) b.get("Plataforma");
            titulo.setText(t);
            autor.setText(a);
            descripcion.setText(d);
            Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
        }

    }

    public void onclickFile(View view) {
        Intent file = new Intent(getApplicationContext(),UploadActivity.class);
        startActivity(file);
    }
}
