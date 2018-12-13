package com.example.danny.apprepositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.danny.apprepositorio.utilidades.Utilidades;
import com.example.danny.apprepositorio.utilidades.UtilidadesAportaciones;
import com.example.danny.apprepositorio.utilidades.UtilidadesCirculos;
import com.example.danny.apprepositorio.utilidades.UtilidadesForo;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    @Override
    public void onCreate(SQLiteDatabase db) {
        //EJECUTAMOS LA CONSUTLA AL CREAR LA BASE DE DATOS DE FORMA AUTOMATICA
        //EL METODO ONCREATE
        db.execSQL(Utilidades.CREAR_TABLA_USUARIOS);
        db.execSQL(UtilidadesCirculos.CREAR_TABLA_CIRCULOS);
        db.execSQL(UtilidadesForo.CREAR_TABLA_FORO);
        db.execSQL(UtilidadesAportaciones.CREAR_TABLA_APORTACIONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //si existe la base de datos, la borramos

        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS "+UtilidadesCirculos.TABLA_CIRCULOS);
        db.execSQL("DROP TABLE IF EXISTS "+UtilidadesForo.TABLA_FORO);
        db.execSQL("DROP TABLE IF EXISTS "+UtilidadesAportaciones.TABLA_APORTACIONES);
        onCreate(db);
    }

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
