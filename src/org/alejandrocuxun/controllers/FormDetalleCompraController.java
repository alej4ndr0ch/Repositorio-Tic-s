/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.controllers;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.models.Compras;
import org.alejandrocuxun.models.Productos;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormDetalleCompraController implements Initializable {
    Main stage;
    int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TextField tfDetalleCompraId, tfCantidad;
    @FXML
    ComboBox cmbProductoId, cmbCompraId;
    @FXML
    TableView tblDetalleCompra;
    @FXML
    TableColumn colDetalleCompraId, colCantidad, colProductoId, colCompraId;
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            stage.menuComprasView();
        }else if (event.getSource() == btnGuardar) {
            if (op == 1) {
                agregarDetalleCompra();
                SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                stage.menuComprasView();
            }
        }
    }
    
    
    public void agregarDetalleCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_AgregarDetalleCompras(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfCantidad.getText());
            statement.setInt(2,((Productos)cmbProductoId.getSelectionModel().getSelectedItem()).getProductoId());
            statement.setInt(3,((Compras)cmbCompraId.getSelectionModel().getSelectedItem()).getCompraId());
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCompraId.setItems(listarCompras());
        cmbProductoId.setItems(listarProductos());
        
    }
    
    public ObservableList<Productos> listarProductos(){
        ArrayList<Productos> productos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarDetalleCompras()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int productoId = resultSet.getInt("productoId");
                String nombreProducto = resultSet.getString("nombreProducto");
                String descripcionProducto = resultSet.getString("descripcionProducto");
                int cantidadProducto = resultSet.getInt("cantidadProducto");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagenProducto = resultSet.getBlob("imagenProducto");
                String distribuidorId = resultSet.getString("distribuidorId");
                String categoriaProductoId = resultSet.getString("categoriaId");
            
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
    
    public ObservableList<Compras> listarCompras(){
        ArrayList<Compras> compras = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarCompras()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int compraId = resultSet.getInt("compraId");
                Date fechaCompra = resultSet.getDate("fechaCompra");
                Double totalCompra = resultSet.getDouble("totalCompra");
            
                compras.add(new Compras(compraId, fechaCompra, totalCompra));
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
        
        
        return FXCollections.observableList(compras);
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
