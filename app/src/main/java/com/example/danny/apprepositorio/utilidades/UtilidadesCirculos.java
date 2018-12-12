package com.example.danny.apprepositorio.utilidades;

public class UtilidadesCirculos {
    public static final String TABLA_CIRCULOS="circulos";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_CATEGORIA="categoria";

    public static final String CREAR_TABLA_CIRCULOS = "CREATE TABLE "+
            ""+TABLA_CIRCULOS+" ("+CAMPO_ID+" "+
            " TEXT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_DESCRIPCION+" TEXT, "+CAMPO_CATEGORIA+" TEXT)";
}
