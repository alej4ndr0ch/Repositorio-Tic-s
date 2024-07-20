/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.alejandrocuxun.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author aleja
 */
@Table(name = "Direcciones")
@Entity
public class Direcciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int direccionId;
    private String calle;
    private String avenida;
    private String numeroCasa;
    private int ciudadId;

    public Direcciones() {
    }

    public Direcciones(String calle, String avenida, String numeroCasa, int ciudadId) {
        this.calle = calle;
        this.avenida = avenida;
        this.numeroCasa = numeroCasa;
        this.ciudadId = ciudadId;
    }

    public Direcciones(int direccionId, String calle, String avenida, String numeroCasa, int ciudadId) {
        this.direccionId = direccionId;
        this.calle = calle;
        this.avenida = avenida;
        this.numeroCasa = numeroCasa;
        this.ciudadId = ciudadId;
    }

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public int getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(int ciudadId) {
        this.ciudadId = ciudadId;
    }

    @Override
    public String toString() {
        return "Direcciones{" + "direccionId=" + direccionId + ", calle=" + calle + ", avenida=" + avenida + ", numeroCasa=" + numeroCasa + ", ciudadId=" + ciudadId + '}';
    }
}
