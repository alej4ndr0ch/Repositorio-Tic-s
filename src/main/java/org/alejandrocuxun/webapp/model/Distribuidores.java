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
@Table(name = "Distribuidores")
@Entity
public class Distribuidores {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int distribuidorId;
    private String nombreDistribuidor;
    private String direccionDistribuidor;
    private String nitDistribuidor;
    private String telefonoDistribuidor;
    private String web;

    public Distribuidores() {
    }

    public Distribuidores(String nombreDistribuidor, String direccionDistribuidor, String nitDistribuidor, String telefonoDistribuidor, String web) {
        this.nombreDistribuidor = nombreDistribuidor;
        this.direccionDistribuidor = direccionDistribuidor;
        this.nitDistribuidor = nitDistribuidor;
        this.telefonoDistribuidor = telefonoDistribuidor;
        this.web = web;
    }

    public Distribuidores(int distribuidorId, String nombreDistribuidor, String direccionDistribuidor, String nitDistribuidor, String telefonoDistribuidor, String web) {
        this.distribuidorId = distribuidorId;
        this.nombreDistribuidor = nombreDistribuidor;
        this.direccionDistribuidor = direccionDistribuidor;
        this.nitDistribuidor = nitDistribuidor;
        this.telefonoDistribuidor = telefonoDistribuidor;
        this.web = web;
    }

    public int getDistribuidorId() {
        return distribuidorId;
    }

    public void setDistribuidorId(int distribuidorId) {
        this.distribuidorId = distribuidorId;
    }

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public String getDireccionDistribuidor() {
        return direccionDistribuidor;
    }

    public void setDireccionDistribuidor(String direccionDistribuidor) {
        this.direccionDistribuidor = direccionDistribuidor;
    }

    public String getNitDistribuidor() {
        return nitDistribuidor;
    }

    public void setNitDistribuidor(String nitDistribuidor) {
        this.nitDistribuidor = nitDistribuidor;
    }

    public String getTelefonoDistribuidor() {
        return telefonoDistribuidor;
    }

    public void setTelefonoDistribuidor(String telefonoDistribuidor) {
        this.telefonoDistribuidor = telefonoDistribuidor;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Distribuidores{" + "distribuidorId=" + distribuidorId + ", nombreDistribuidor=" + nombreDistribuidor + ", direccionDistribuidor=" + direccionDistribuidor + ", nitDistribuidor=" + nitDistribuidor + ", telefonoDistribuidor=" + telefonoDistribuidor + ", web=" + web + '}';
    }
}
