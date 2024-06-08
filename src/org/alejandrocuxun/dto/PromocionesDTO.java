/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.dto;

import org.alejandrocuxun.models.Promociones;

/**
 *
 * @author aleja
 */
public class PromocionesDTO {
    private static PromocionesDTO instance;
    private Promociones promociones;
    
    private PromocionesDTO(){
    
    }
    
    public static PromocionesDTO getPromocionDTO(){
        if(instance == null){
            instance = new PromocionesDTO();
        }
        
        return instance;
    }

    public Promociones getPromocion() {
        return promociones;
    }

    public void setPromocion(Promociones promociones) {
        this.promociones = promociones;
    }
}
