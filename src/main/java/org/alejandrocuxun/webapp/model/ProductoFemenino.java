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
import java.sql.Blob;

/**
 *
 * @author aleja
 */
@Table(name = "ProductosFemeninos")
@Entity
public class ProductoFemenino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int productoMasculinoId;
    private String nombreProducto;
    private double precioCompra;
    private String descripcionProducto;
    private int cantidadStock;
    private double precioVentaUnitario;
    private Blob imagenProducto;
    private int distribuidorId;
    private int categoriaproductoId;

    public ProductoFemenino() {
    }

    public ProductoFemenino(int productoMasculinoId, String nombreProducto, double precioCompra, String descripcionProducto, int cantidadStock, double precioVentaUnitario, Blob imagenProducto, int distribuidorId, int categoriaproductoId) {
        this.productoMasculinoId = productoMasculinoId;
        this.nombreProducto = nombreProducto;
        this.precioCompra = precioCompra;
        this.descripcionProducto = descripcionProducto;
        this.cantidadStock = cantidadStock;
        this.precioVentaUnitario = precioVentaUnitario;
        this.imagenProducto = imagenProducto;
        this.distribuidorId = distribuidorId;
        this.categoriaproductoId = categoriaproductoId;
    }

    public ProductoFemenino(String nombreProducto, double precioCompra, String descripcionProducto, int cantidadStock, double precioVentaUnitario, Blob imagenProducto, int distribuidorId, int categoriaproductoId) {
        this.nombreProducto = nombreProducto;
        this.precioCompra = precioCompra;
        this.descripcionProducto = descripcionProducto;
        this.cantidadStock = cantidadStock;
        this.precioVentaUnitario = precioVentaUnitario;
        this.imagenProducto = imagenProducto;
        this.distribuidorId = distribuidorId;
        this.categoriaproductoId = categoriaproductoId;
    }

    public int getProductoMasculinoId() {
        return productoMasculinoId;
    }

    public void setProductoMasculinoId(int productoMasculinoId) {
        this.productoMasculinoId = productoMasculinoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecioVentaUnitario() {
        return precioVentaUnitario;
    }

    public void setPrecioVentaUnitario(double precioVentaUnitario) {
        this.precioVentaUnitario = precioVentaUnitario;
    }

    public Blob getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(Blob imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public int getDistribuidorId() {
        return distribuidorId;
    }

    public void setDistribuidorId(int distribuidorId) {
        this.distribuidorId = distribuidorId;
    }

    public int getCategoriaproductoId() {
        return categoriaproductoId;
    }

    public void setCategoriaproductoId(int categoriaproductoId) {
        this.categoriaproductoId = categoriaproductoId;
    }

    @Override
    public String toString() {
        return "ProductosFemeninos{" + "productoMasculinoId=" + productoMasculinoId + ", nombreProducto=" + nombreProducto + ", precioCompra=" + precioCompra + ", descripcionProducto=" + descripcionProducto + ", cantidadStock=" + cantidadStock + ", precioVentaUnitario=" + precioVentaUnitario + ", imagenProducto=" + imagenProducto + ", distribuidorId=" + distribuidorId + ", categoriaproductoId=" + categoriaproductoId + '}';
    }
}
