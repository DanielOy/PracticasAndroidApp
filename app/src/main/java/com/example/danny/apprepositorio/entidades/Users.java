package com.example.danny.apprepositorio.entidades;
public class Users
{   //Declaracion de miembros de clase
    private Integer id;
    private Integer matricula;
    private String correo;
    private String contrasenia;
    private String nombre;
    private String semestre;

    public Users(Integer id, Integer matricula, String correo, String contrasenia, String nombre, String semestre) {
        this.id = id;
        this.matricula = matricula;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.semestre = semestre;
    }

    public Users() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}