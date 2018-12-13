package com.example.danny.apprepositorio.entidades;

public class Foro
{
    public int id;
    public String titulo;
    public String lenguaje;
    public String autor;
    public String fecha;
    public String descripcion;

    public Foro(int id, String titulo, String lenguaje, String autor, String fecha, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.lenguaje = lenguaje;
        this.autor = autor;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Foro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
