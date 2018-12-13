package com.example.danny.apprepositorio.utilidades;

public class UtilidadesAportaciones
{
    //constantes campos tabla de aportaciones

    public static final String TABLA_APORTACIONES="aportes";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_USUARIO="usuario";
    public static final String CAMPO_CODIGO="codigo";
    public static final String CAMPO_PLATAFORMA="plataforma";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_FECHA="fecha";

    public static final String CREAR_TABLA_APORTACIONES = "CREATE TABLE "+
            ""+TABLA_APORTACIONES+" ("+CAMPO_ID+" "+
            "INTEGER , "+CAMPO_USUARIO+" TEXT, "+CAMPO_CODIGO +
            " TEXT, "+CAMPO_PLATAFORMA+" TEXT, "+CAMPO_DESCRIPCION+" TEXT, "+CAMPO_FECHA+" TEXT)";
}
