/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.dto;

import org.alejandrocuxun.models.CategoriaProducto;

/**
 *
 * @author aleja
 */
public class CategoriaProductoDTO {
    private static CategoriaProductoDTO instance;
    private CategoriaProducto categoriaProducto;
    
    private CategoriaProductoDTO(){
    
    }
    
    public static CategoriaProductoDTO getCategoriaPDTO(){
        if(instance == null){
            instance = new CategoriaProductoDTO();
        }
        
        return instance;
    }

    public CategoriaProducto getCategoriaP() {
        return categoriaProducto;
    }

    public void setCategoriaP(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }  
}
