/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.dto;

import org.alejandrocuxun.models.Distribuidores;

/**
 *
 * @author aleja
 */
public class DistribuidoresDTO {
    private static DistribuidoresDTO instance;
    private Distribuidores distribuidores;
    
    private DistribuidoresDTO() {
        
    }
    
    public static DistribuidoresDTO getDistribuidorDTO(){
        if(instance == null) {
            instance = new DistribuidoresDTO();
        }
        return instance;
    }

    public Distribuidores getDistribuidores() {
        return distribuidores;
    }

    public void setDistribuidores(Distribuidores distribuidores) {
        this.distribuidores = distribuidores;
    }
}
