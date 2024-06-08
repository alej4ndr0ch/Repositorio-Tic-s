/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.dto;

import org.alejandrocuxun.models.Facturas;

/**
 *
 * @author aleja
 */
public class FacturasDTO {
    private static FacturasDTO instance;
    private Facturas facturas;
    
    private FacturasDTO(){
    
    }
    
    public static FacturasDTO getFacturaDTO(){
        if(instance == null){
            instance = new FacturasDTO();
        }
        
        return instance;
    }

    public Facturas getFactura() {
        return facturas;
    }

    public void setFactura(Facturas facturas) {
        this.facturas = facturas;
    }
}
