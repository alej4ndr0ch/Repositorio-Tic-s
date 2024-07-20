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
@Table(name = "Clientes")
@Entity
public class Clientes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clienteId;
    private String nombre;
    private String apellido;
    private int direccionId;

    public Clientes() {
    }

    public Clientes(String nombre, String apellido, int direccionId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccionId = direccionId;
    }

    public Clientes(int clienteId, String nombre, String apellido, int direccionId) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccionId = direccionId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    @Override
    public String toString() {
        return "Clientes{" + "clienteId=" + clienteId + ", nombre=" + nombre + ", apellido=" + apellido + ", direccionId=" + direccionId + '}';
    }
}
