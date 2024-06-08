/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.dto.CargosDTO;
import org.alejandrocuxun.models.Cargos;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormCargosController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    
    
    @FXML
    TextField tfCargoId, tfNombreCargo;
    
    @FXML
    Button btnGuardar, btnCancelar;
    
    @FXML
    TextArea taDescripcionCargo;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreCargo.getText().equals("") && !taDescripcionCargo.getText().equals("")){
                    agregarCargos();
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                    stage.menuCargosView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(600);
                    tfNombreCargo.requestFocus();
                }  
                
            }else if(op == 2){
                if(!tfNombreCargo.getText().equals("") && !taDescripcionCargo.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarCargos();
                        SuperKinalAlert.getInstance().mostrarAlertaInformacion(500);
                        CargosDTO.getCargosDTO().setCargos(null);
                        stage.menuCargosView();
                    }else{
                        stage.menuCargosView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(600);
                    tfNombreCargo.requestFocus();
                    
                }
            }
        }else if(event.getSource() == btnCancelar) {
                        stage.menuCargosView();
                        CargosDTO.getCargosDTO().setCargos(null);
        }
        
    }
                
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CargosDTO.getCargosDTO().getCargos() !=null){
            cargarDatos(CargosDTO.getCargosDTO().getCargos());
        }
    }

    public void cargarDatos(Cargos cargos){
        tfCargoId.setText(Integer.toString(cargos.getCargoId()));
        tfNombreCargo.setText(cargos.getNombreCargo());
        taDescripcionCargo.setText(cargos.getDescripcionCargo());   
        
    
    }
    
    public void agregarCargos(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarCargos(?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreCargo.getText());
            statement.setString(2, taDescripcionCargo.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }else if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        op = 1;
    }
    
     public void editarCargos(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "Call sp_EditarCargos(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCargoId.getText()));
            statement.setString(2, tfNombreCargo.getText());
            statement.setString(3, taDescripcionCargo.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public void setOp(int op) {
        this.op = op;
    }
}
