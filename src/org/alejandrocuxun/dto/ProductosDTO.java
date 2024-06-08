/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.dto;

import org.alejandrocuxun.models.Productos;

/**
 *
 * @author aleja
 */
public class ProductosDTO {
    private static ProductosDTO instance;
    private Productos productos;
    
    private ProductosDTO(){
    
    }
    
    public static ProductosDTO getProductoDTO(){
        if(instance == null){
            instance = new ProductosDTO();
        }
        
        return instance;
    }

    public Productos getProducto() {
        return productos;
    }

    public void setProducto(Productos productos) {
        this.productos = productos;
    }
}
