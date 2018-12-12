package com.example.danny.apprepositorio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.net.URI;

public class UploadActivity extends AppCompatActivity {

    EditText edtURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        edtURL = (EditText)findViewById(R.id.edtURL);
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
                    break;
            }
        }
    }
}
