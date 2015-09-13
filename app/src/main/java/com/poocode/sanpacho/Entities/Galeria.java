package com.poocode.sanpacho.Entities;

import com.google.gson.annotations.SerializedName;

public class Galeria {

    @SerializedName("idgaleria")
    private int idGaleria;

    @SerializedName("rutaimagen")
    private String imagen;

    @SerializedName("estado")
    private int estado;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(int idGaleria) {
        this.idGaleria = idGaleria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
