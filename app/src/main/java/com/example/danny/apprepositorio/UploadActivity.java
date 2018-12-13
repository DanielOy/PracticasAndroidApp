package com.example.danny.apprepositorio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class UploadActivity extends AppCompatActivity {

    EditText edtURL, edtNombre;
    private ProgressBar progress;
    public int mProgressStatus = 0;
    private Handler miHandler;
    private Runnable runnable;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        edtURL = (EditText)findViewById(R.id.edtURL);
        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtNombre.setEnabled(false);
        progress = (ProgressBar)findViewById(R.id.progressBarLoad);
        progress.setVisibility(View.INVISIBLE);
    }

    private static final int READ_REQUEST_CODE=42;
    public void demo(View view)
    {
        performFileSearch();
    }
    public void performFileSearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent,READ_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case READ_REQUEST_CODE:
                    Uri miPath=data.getData();
                    File codigo = new File(miPath.toString());
                    edtURL.setText(codigo.getName());
                    edtNombre.setText(codigo.getName());
                    edtNombre.setEnabled(true);
                    break;
            }
        }
    }

    public void onCancelarClick(View view) {
        onBackPressed();
        finish();
    }


    public void onClickUpload(View view) {
        try{
            progress.setVisibility(View.VISIBLE);
            //progress.showContextMenu();

            miHandler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    progress.setVisibility(View.VISIBLE);
                    timer.cancel();
                }
            };

            timer  = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    miHandler.post(runnable);
                }
            },10000,1000);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("datos",edtNombre.getText().toString());
            setResult(Activity.RESULT_OK,returnIntent);
            progress.setVisibility(View.VISIBLE);
            finish();
            
        }catch (Exception ex){
            Toast.makeText(this, "Error al subir", Toast.LENGTH_SHORT).show();
        }
    }
}
