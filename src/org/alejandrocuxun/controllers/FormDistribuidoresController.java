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
import javafx.scene.control.TextField;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.dto.DistribuidoresDTO;
import org.alejandrocuxun.models.Distribuidores;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormDistribuidoresController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    
    
    @FXML
    TextField tfDistribuidorId, tfNombreDistribuidor, tfDireccionDistribuidor, tfNitDistribuidor, tfTelefono, tfWeb;
    
    @FXML
    Button btnGuardar, btnCancelar;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreDistribuidor.getText().equals("") && !tfDireccionDistribuidor.getText().equals("") && !tfNitDistribuidor.getText().equals("")){
                    agregarDistribuidor();
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                    stage.menuDistribuidoresView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(600);
                    tfNombreDistribuidor.requestFocus();
                }  
                
            }else if(op == 2){
                if(!tfNombreDistribuidor.getText().equals("") && !tfDireccionDistribuidor.getText().equals("") && !tfNitDistribuidor.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarDistribuidor();
                        SuperKinalAlert.getInstance().mostrarAlertaInformacion(500);
                        DistribuidoresDTO.getDistribuidorDTO().setDistribuidores(null);
                        stage.menuDistribuidoresView();
                    }else{
                        stage.menuDistribuidoresView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(600);
                    tfNombreDistribuidor.requestFocus();
                    
                }
            }
        }else if(event.getSource() == btnCancelar) {
                        stage.menuDistribuidoresView();
                        DistribuidoresDTO.getDistribuidorDTO().setDistribuidores(null);
        }
        
    }
                
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(DistribuidoresDTO.getDistribuidorDTO().getDistribuidores() !=null){
            cargarDatos(DistribuidoresDTO.getDistribuidorDTO().getDistribuidores());
        }
    }

    public void cargarDatos(Distribuidores distribuidores){
        tfDistribuidorId.setText(Integer.toString(distribuidores.getDistribuidorId()));
        tfNombreDistribuidor.setText(distribuidores.getNombreDistribuidor());
        tfDireccionDistribuidor.setText(distribuidores.getDireccionDistribuidor());
        tfNitDistribuidor.setText(distribuidores.getNitDistribuidor());
        tfTelefono.setText(Integer.toString(distribuidores.getTelefono()));
        tfWeb.setText(distribuidores.getWeb());    
        
    
    }
    
    public void agregarDistribuidor(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarDistribuidores(?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreDistribuidor.getText());
            statement.setString(2, tfDireccionDistribuidor.getText());
            statement.setString(3, tfNitDistribuidor.getText());
            statement.setString(4, tfTelefono.getText());
            statement.setString(5, tfWeb.getText());
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
    
     public void editarDistribuidor(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "Call sp_EditarDistribuidores(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfDistribuidorId.getText()));
            statement.setString(2, tfNombreDistribuidor.getText());
            statement.setString(3, tfDireccionDistribuidor.getText());
            statement.setString(4, tfNitDistribuidor.getText());
            statement.setString(5, tfTelefono.getText());
            statement.setString(6, tfWeb.getText());
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
