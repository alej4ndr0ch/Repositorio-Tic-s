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
public class DetalleFactura {
    int detalleFacturaId;
    String facturaId;
    String productoId;
    
    public void DetalleFactura(){
        
    }

    public DetalleFactura(int detalleFacturaId, String facturaId, String productoId) {
        this.detalleFacturaId = detalleFacturaId;
        this.facturaId = facturaId;
        this.productoId = productoId;
    }

    public int getDetalleFacturaId() {
        return detalleFacturaId;
    }

    public void setDetalleFacturaId(int detalleFacturaId) {
        this.detalleFacturaId = detalleFacturaId;
    }

    public String getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(String facturaId) {
        this.facturaId = facturaId;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "detalleFacturaId=" + detalleFacturaId + ", facturaId=" + facturaId + ", productoId=" + productoId + '}';
    }
    
    
}
