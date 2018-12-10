package com.example.danny.apprepositorio.utilidades;

public class Utilidades
{
    //constantes campos tabla usuarios

    public static final String TABLA_USUARIOS="users";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_MATRICULA="matricula";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_CONTRASENIA="contrasenia";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_SEMESTRE="semestre";

    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE "+
            ""+TABLA_USUARIOS+" ("+CAMPO_ID+" "+
            "INTEGER, "+CAMPO_MATRICULA+" INTEGER, "+CAMPO_CORREO +
            " TEXT, "+CAMPO_CONTRASENIA+" TEXT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_SEMESTRE+" TEXT)";
}
