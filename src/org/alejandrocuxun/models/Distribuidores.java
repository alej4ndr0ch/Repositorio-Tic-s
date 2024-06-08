/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.models;

/**
 *
 * @author aleja
 */
public class Distribuidores {
    private int distribuidorId;
    private String nombreDistribuidor;
    private String direccionDistribuidor;
    private String nitDistribuidor;
    private int telefono;
    private String web;

    public Distribuidores() {
    }

    public Distribuidores(int distribuidorId, String nombreDistribuidor, String direccionDistribuidor, String nitDistribuidor, int telefono, String web) {
        this.distribuidorId = distribuidorId;
        this.nombreDistribuidor = nombreDistribuidor;
        this.direccionDistribuidor = direccionDistribuidor;
        this.nitDistribuidor = nitDistribuidor;
        this.telefono = telefono;
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

    public void setDireccionDistribuidor(String descripcionDistribuidor) {
        this.direccionDistribuidor = descripcionDistribuidor;
    }

    public String getNitDistribuidor() {
        return nitDistribuidor;
    }

    public void setNitDistribuidor(String nitDistribuidor) {
        this.nitDistribuidor = nitDistribuidor;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Distribuidores{" + "distribuidorId=" + distribuidorId + ", nombreDistribuidor=" + nombreDistribuidor + ", descripcionDistribuidor=" + direccionDistribuidor + ", nitDistribuidor=" + nitDistribuidor + ", telefono=" + telefono + ", web=" + web + '}';
    }
}
