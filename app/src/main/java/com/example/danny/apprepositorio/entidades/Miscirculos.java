package com.example.danny.apprepositorio.entidades;

public class Miscirculos {

    private Integer id;
    private String nombrecirculo;
    private String descrip;
    private String user;
    private String iduser;

    public Miscirculos(Integer id, String nombrecirculo, String descrip, String user, String iduser) {
        this.id = id;
        this.nombrecirculo = nombrecirculo;
        this.descrip = descrip;
        this.user = user;
        this.iduser = iduser;
    }

    public Miscirculos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrecirculo() {
        return nombrecirculo;
    }

    public void setNombrecirculo(String nombrecirculo) {
        this.nombrecirculo = nombrecirculo;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
}
