/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.models;

import java.sql.Date;

/**
 *
 * @author aleja
 */
public class Promociones {
    int promocionId;
    int precioPromocion;
    String descripcionPromocion;
    Date fechaInicio;
    Date fechaFinalizacion;
    String productoId;

    public Promociones() {
    }

    public Promociones(int promocionId, int precioPromocion, String descripcionPromocion, Date fechaInicio, Date fechaFinalizacion, String productoId) {
        this.promocionId = promocionId;
        this.precioPromocion = precioPromocion;
        this.descripcionPromocion = descripcionPromocion;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.productoId = productoId;
    }

    public Promociones(int promocionId, String descripcionPromocion, int precioPromocion, int fechaInicio, int fechaFinalizacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(int promocionId) {
        this.promocionId = promocionId;
    }

    public int getPrecioPromocion() {
        return precioPromocion;
    }

    public void setPrecioPromocion(int precioPromocion) {
        this.precioPromocion = precioPromocion;
    }

    public String getDescripcionPromocion() {
        return descripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        this.descripcionPromocion = descripcionPromocion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "Promociones{" + "promocionId=" + promocionId + ", precioPromocion=" + precioPromocion + ", descripcionPromocion=" + descripcionPromocion + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion + ", productoId=" + productoId + '}';
    }
    
    
}
