package com.example.danny.apprepositorio.utilidades;

public class UtilidadesForo
{

    public static final String TABLA_FORO="foro";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_TITULO="titulo";
    public static final String CAMPO_LENGUAJE= "lenguaje";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_AUTOR="autor";
    public static final String CAMPO_FECHA="fecha";


    public static final String CREAR_TABLA_FORO = "CREATE TABLE "+
            ""+TABLA_FORO+" ("+CAMPO_ID+" "+
            " TEXT, "+CAMPO_TITULO+" TEXT, "+CAMPO_LENGUAJE+" TEXT, "+
            CAMPO_DESCRIPCION+" TEXT, "+CAMPO_AUTOR+" TEXT, "+
            CAMPO_FECHA+" TEXT)";
}
