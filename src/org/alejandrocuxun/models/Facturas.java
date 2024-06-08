/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.models;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author aleja
 */
public class Facturas {
    int facturaId;
    Date fecha;
    Time hora;
    String clienteId;
    String empleadoId;
    double total;
    
    public Facturas(){
        
    }

    public Facturas(int facturaId, Date fecha, Time hora, String clienteId, String empleadoId, double total) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.total = total;
    }
    
    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Facturas{" + "facturaId=" + facturaId + ", fecha=" + fecha + ", hora=" + hora + ", clienteId=" + clienteId + ", empleadoId=" + empleadoId + ", total=" + total + '}';
    }
    
    
}
