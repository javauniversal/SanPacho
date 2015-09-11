package com.poocode.sanpacho.Entities;

import com.google.gson.annotations.SerializedName;

public class MasterItem {

    @SerializedName("idhotelesrestaurante")
    private int idhotelesrestaurante;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("correo")
    private String correo;

    @SerializedName("celular")
    private String celular;

    @SerializedName("dueno")
    private String dueno;

    @SerializedName("servicios")
    private String servicios;

    @SerializedName("estado")
    private int estado;

    @SerializedName("imagen")
    private String imagen;

    @SerializedName("idcategoria")
    private int idcategoria;

    private static MasterItem masterItemStatic;

    public int getIdhotelesrestaurante() {
        return idhotelesrestaurante;
    }

    public void setIdhotelesrestaurante(int idhotelesrestaurante) {
        this.idhotelesrestaurante = idhotelesrestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public static MasterItem getMasterItemStatic() {
        return masterItemStatic;
    }

    public static void setMasterItemStatic(MasterItem masterItemStatic) {
        MasterItem.masterItemStatic = masterItemStatic;
    }

}
