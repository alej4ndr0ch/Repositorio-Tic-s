/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.dto;

import org.alejandrocuxun.models.Empleados;

/**
 *
 * @author aleja
 */
public class EmpleadosDTO {
    private static EmpleadosDTO instance;
    private Empleados empleados;
    
    private EmpleadosDTO(){
    
    }
    
    public static EmpleadosDTO getEmpleadoDTO(){
        if(instance == null){
            instance = new EmpleadosDTO();
        }
        
        return instance;
    }

    public Empleados getEmpleado() {
        return empleados;
    }

    public void setEmpleado(Empleados empleados) {
        this.empleados = empleados;
    }
}
