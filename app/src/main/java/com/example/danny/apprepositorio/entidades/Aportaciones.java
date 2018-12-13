package com.example.danny.apprepositorio.entidades;

public class Aportaciones {

    private int id;
    private String usuario;
    private String titulo;
    private String codigo;
    private String plataforma;
    private String descripcion;
    private String fecha;

    public Aportaciones(int id, String usuario, String titulo, String codigo, String plataforma, String descripcion, String fecha) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.codigo = codigo;
        this.plataforma = plataforma;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Aportaciones() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
