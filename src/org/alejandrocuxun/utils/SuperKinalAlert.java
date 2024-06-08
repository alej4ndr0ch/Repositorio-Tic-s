/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author informatica
 */
public class SuperKinalAlert {
    private static SuperKinalAlert instance;
    
    private SuperKinalAlert(){
        
    }
    
    public static SuperKinalAlert getInstance(){
        if(instance == null){
            instance = new SuperKinalAlert();
        }
        return instance;
    }
    
    public void mostrarAlertaInformacion(int code){
        if(code == 400){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmacion Registro");
            alert.setHeaderText("Confirmacion Registro");
            alert.setContentText("¡Registro realizado con exito!");
            alert.showAndWait();
        }else if(code == 500){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición Registro");
            alert.setContentText("¡Edición realizado con exito!");
            alert.showAndWait();
        } else if(code == 600){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Pendientes");
            alert.setHeaderText("Campos Pendientes");
            alert.setContentText("¡Algunos Campos necesarios para el registro estan vacíos!");
            alert.showAndWait();
        }
    }
    
    public Optional<ButtonType> mostrarAlertaConfirmacion(int code){
        Optional<ButtonType> action = null;
        
        if(code == 404){//Codigo 404 sirve para confirmar la eliminacion de registro
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminación Registro");
            alert.setHeaderText("Eliminación Registro");
            alert.setContentText("¿Desea confirmar la eliminación del registro?");
            action = alert.showAndWait();
        }else if(code == 505){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición Registro");
            alert.setContentText("¿Desea confirmar la edición del registro?");
            action = alert.showAndWait();
        }
        return action;
    }
    
     public void alertaSaludo(String usuario) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bienvenido");
        alert.setHeaderText("Bienvenido " + usuario);
        alert.showAndWait();
    }
}
