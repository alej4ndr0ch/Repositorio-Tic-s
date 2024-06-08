/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.controllers;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.dto.PromocionesDTO;
import org.alejandrocuxun.models.Productos;
import org.alejandrocuxun.models.Promociones;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormPromocionesController implements Initializable {
    Main stage;
    int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
   @FXML
    Button btnRegresar, btnGuardar;
   
   @FXML
   TextField tfPromocionId, tfPrecioPromocion, tfDescripcionPromocion, tfFechaInicio, tfFechaFinalizacion;
   
   @FXML
   ComboBox cmbProductoId;
   
   @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            PromocionesDTO.getPromocionDTO().setPromocion(null);
            stage.menuPromocionesView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfPrecioPromocion.getText().equals("") && !tfFechaInicio.getText().equals("") && !tfFechaFinalizacion.getText().equals("")){
                    agregarPromocion();
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                    stage.menuPromocionesView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfPrecioPromocion.getText().equals("")){
                        tfPrecioPromocion.requestFocus();
                    }else if(tfFechaInicio.getText().equals("")){
                        tfFechaInicio.requestFocus();
                    }else if(tfFechaFinalizacion.getText().equals("")){
                        tfFechaFinalizacion.requestFocus();
                    }
                }
                
               
            }else if(op == 2){
                if(!tfPrecioPromocion.getText().equals("") && !tfFechaInicio.getText().equals("") && !tfFechaFinalizacion.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                    editarPromocion();
                        PromocionesDTO.getPromocionDTO().setPromocion(null);
                        SuperKinalAlert.getInstance().mostrarAlertaInformacion(500);
                        stage.menuPromocionesView();
                    }else{
                        stage.menuPromocionesView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfPrecioPromocion.getText().equals("")){
                        tfPrecioPromocion.requestFocus();
                    }else if(tfFechaInicio.getText().equals("")){
                        tfFechaInicio.requestFocus();
                    }else if(tfFechaFinalizacion.getText().equals("")){
                        tfFechaFinalizacion.requestFocus();
                    }
                }
                
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(PromocionesDTO.getPromocionDTO().getPromocion() != null){
            cargarDatos(PromocionesDTO.getPromocionDTO().getPromocion());
        }
        cmbProductoId.setItems(listarProductos());
        
    }
    
    public void cargarDatos(Promociones Promocion){
        tfPromocionId.setText(Integer.toString(Promocion.getPromocionId()));
        tfPrecioPromocion.setText(Double.toString(Promocion.getPrecioPromocion()));
        tfDescripcionPromocion.setText(Promocion.getDescripcionPromocion());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        tfFechaInicio.setText(formatoFecha.format(Promocion.getFechaInicio()));
        tfFechaFinalizacion.setText(formatoFecha.format(Promocion.getFechaFinalizacion()));
    }
    
    public void agregarPromocion(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_AgregarPromociones(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfPrecioPromocion.getText());
            statement.setString(2, tfDescripcionPromocion.getText());
            statement.setString(3, tfFechaInicio.getText());
            statement.setString(4, tfFechaFinalizacion.getText());
            statement.setInt(5,((Productos)cmbProductoId.getSelectionModel().getSelectedItem()).getProductoId());
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
    
    public void editarPromocion(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarPromociones(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfPromocionId.getText()));
            statement.setString(2, tfPrecioPromocion.getText());
            statement.setString(3, tfDescripcionPromocion.getText());
            statement.setString(4, tfFechaInicio.getText());
            statement.setString(5, tfFechaFinalizacion.getText());
            statement.setInt(6,((Productos)cmbProductoId.getSelectionModel().getSelectedItem()).getProductoId());
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
    
    public ObservableList<Productos> listarProductos(){
        ArrayList<Productos> productos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int productoId = resultSet.getInt("productoId");
                String nombreProducto = resultSet.getString("nombreProducto");
                String descripcionProducto = resultSet.getString("descripcionProducto");
                int cantidadProducto = resultSet.getInt("cantidadStock");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagenProducto = resultSet.getBlob("imagenProducto");
                String distribuidorId = resultSet.getString("distribuidor");
                String categoriaProductoId = resultSet.getString("categoria");
            
                productos.add(new Productos(productoId, nombreProducto, descripcionProducto, cantidadProducto, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductoId));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                
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
        
        
        return FXCollections.observableList(productos);
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
