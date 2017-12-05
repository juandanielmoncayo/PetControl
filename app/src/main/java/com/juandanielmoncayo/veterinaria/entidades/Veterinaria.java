package com.juandanielmoncayo.veterinaria.entidades;

import java.io.Serializable;

/**
 * Created by USUARIO on 01/12/2017.
 */

public class Veterinaria implements Serializable {

    private Integer idVeterinaria;
    private String nombreVeterinaria;
    private String telefono;
    private String barrio;
    private Double latitud;
    private Double longitud;
    public Veterinaria(){

    }

    public Veterinaria(Integer idVeterinaria, String nombreVeterinaria, String telefono, String barrio, Double latitud, Double longitud) {
        this.idVeterinaria = idVeterinaria;
        this.nombreVeterinaria = nombreVeterinaria;
        this.telefono = telefono;
        this.barrio = barrio;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(Integer idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public String getNombreVeterinaria() {
        return nombreVeterinaria;
    }

    public void setNombreVeterinaria(String nombreVeterinaria) {
        this.nombreVeterinaria = nombreVeterinaria;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
