/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.models;

import java.sql.Blob;

/**
 *
 * @author aleja
 */
public class Productos {
    int productoId;
    String nombreProducto;
    String descripcionProducto;
    int cantidadProducto;
    double precioVentaUnitaria;
    double precioVentaMayor;
    double precioCompra;
    Blob imagenProducto;
    String distribuidorId;
    String categoriaProductoId;

    public Productos() {
    }

    public Productos(int productoId, String nombreProducto, String descripcionProducto, int cantidadProducto, double precioVentaUnitaria, double precioVentaMayor, double precioCompra, Blob imagenProducto, String distribuidorId, String categoriaProductoId) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioVentaUnitaria = precioVentaUnitaria;
        this.precioVentaMayor = precioVentaMayor;
        this.precioCompra = precioCompra;
        this.imagenProducto = imagenProducto;
        this.distribuidorId = distribuidorId;
        this.categoriaProductoId = categoriaProductoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getPrecioVentaUnitaria() {
        return precioVentaUnitaria;
    }

    public void setPrecioVentaUnitaria(double precioVentaUnitaria) {
        this.precioVentaUnitaria = precioVentaUnitaria;
    }

    public double getPrecioVentaMayor() {
        return precioVentaMayor;
    }

    public void setPrecioVentaMayor(double precioVentaMayor) {
        this.precioVentaMayor = precioVentaMayor;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public Blob getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(Blob imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getDistribuidorId() {
        return distribuidorId;
    }

    public void setDistribuidorId(String distribuidorId) {
        this.distribuidorId = distribuidorId;
    }

    public String getCategoriaProductoId() {
        return categoriaProductoId;
    }

    public void setCategoriaProductoId(String categoriaProductoId) {
        this.categoriaProductoId = categoriaProductoId;
    }

    @Override
    public String toString() {
        return "Productos{" + "nombreProducto=" + nombreProducto + ", descripcionProducto=" + descripcionProducto + ", cantidadProducto=" + cantidadProducto + ", precioVentaUnitaria=" + precioVentaUnitaria + ", precioVentaMayor=" + precioVentaMayor + ", precioCompra=" + precioCompra + ", distribuidorId=" + distribuidorId + ", categoriaProductoId=" + categoriaProductoId + '}';
    }
    
    
}
