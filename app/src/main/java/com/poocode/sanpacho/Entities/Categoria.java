package com.poocode.sanpacho.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Categoria {

    @SerializedName("idCategoriat")
    private int idCategoriat;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("estado")
    private int estado;

    @SerializedName("children")
    private ArrayList<MasterItem> hijos;

    public int getIdCategoriat() {
        return idCategoriat;
    }

    public void setIdCategoriat(int idCategoriat) {
        this.idCategoriat = idCategoriat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ArrayList<MasterItem> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<MasterItem> hijos) {
        this.hijos = hijos;
    }

}
