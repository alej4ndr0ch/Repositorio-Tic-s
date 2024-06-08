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
import org.alejandrocuxun.dto.CategoriaProductoDTO;
import org.alejandrocuxun.models.CategoriaProducto;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormCategoriaProductoController implements Initializable {
    private Main stage;
    private int op;
    
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    
   @FXML
    Button btnRegresar, btnGuardar;
   
   @FXML
   TextField tfCategoriaProductoId, tfNombreCategoriaProducto, tfDescripcionCategoriaProducto;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            CategoriaProductoDTO.getCategoriaPDTO().setCategoriaP(null);
            stage.menuCategoriaProductoView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreCategoriaProducto.getText().equals("") && !tfDescripcionCategoriaProducto.getText().equals("")){
                    agregarCategoriaP();
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                    stage.menuCategoriaProductoView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfNombreCategoriaProducto.getText().equals("")){
                        tfNombreCategoriaProducto.requestFocus();
                    }else if(tfDescripcionCategoriaProducto.getText().equals("")){
                        tfDescripcionCategoriaProducto.requestFocus();
                    }
                }               
            }else if(op == 2){
                if(!tfNombreCategoriaProducto.getText().equals("") && !tfDescripcionCategoriaProducto.getText().equals("")){
                    editarCategoriaP();
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        CategoriaProductoDTO.getCategoriaPDTO().setCategoriaP(null);
                        SuperKinalAlert.getInstance().mostrarAlertaInformacion(500);
                        stage.menuCategoriaProductoView();
                    }else{
                        stage.menuCategoriaProductoView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfNombreCategoriaProducto.getText().equals("")){
                        tfNombreCategoriaProducto.requestFocus();
                    }else if(tfDescripcionCategoriaProducto.getText().equals("")){
                        tfDescripcionCategoriaProducto.requestFocus();
                    }
                }
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       if(CategoriaProductoDTO.getCategoriaPDTO().getCategoriaP() != null){
            cargarDatos(CategoriaProductoDTO.getCategoriaPDTO().getCategoriaP());
        }
    }
    
    public void cargarDatos(CategoriaProducto categoriaProducto){
        tfCategoriaProductoId.setText(Integer.toString(categoriaProducto.getCategoriaProductoId()));
        tfNombreCategoriaProducto.setText(categoriaProducto.getNombreCategoria());
        tfDescripcionCategoriaProducto.setText(categoriaProducto.getDescripcionCategoria());

    }
    
    public void agregarCategoriaP(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_AgregarCategoriaProductos(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreCategoriaProducto.getText());
            statement.setString(2, tfDescripcionCategoriaProducto.getText());
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
    
    public void editarCategoriaP(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarCategoriaProductos(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCategoriaProductoId.getText()));
            statement.setString(2, tfNombreCategoriaProducto.getText());
            statement.setString(3, tfDescripcionCategoriaProducto.getText());
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
    
    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void setOp(int op) {
        this.op = op;
    }
}
