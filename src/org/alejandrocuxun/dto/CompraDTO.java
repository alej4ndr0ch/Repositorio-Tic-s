/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.dto;

import org.alejandrocuxun.models.Compras;

/**
 *
 * @author aleja
 */
public class CompraDTO {
    private static CompraDTO instance;
    private Compras compras;
    
    private CompraDTO(){
    
    }
    
    public static CompraDTO getCompraDTO(){
        if(instance == null){
            instance = new CompraDTO();
        }
        
        return instance;
    }

    public Compras getCompra() {
        return compras;
    }

    public void setCompra(Compras compras) {
        this.compras = compras;
    }
}
