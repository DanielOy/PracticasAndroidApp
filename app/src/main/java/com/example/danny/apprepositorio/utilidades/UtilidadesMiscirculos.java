package com.example.danny.apprepositorio.utilidades;

public class UtilidadesMiscirculos {
    public static final String TABLA_MISCIRCULOS="miscirculos";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_USER="user";
    public static final String CAMPO_IDUSER="iduser";

    public static final String CREAR_TABLA_MISCIRCULOS = "CREATE TABLE "+
            ""+TABLA_MISCIRCULOS+" ("+CAMPO_ID+" "+
            " TEXT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_DESCRIPCION+" TEXT, "+
            CAMPO_USER+" TEXT, "+CAMPO_IDUSER+" TEXT)";
}
