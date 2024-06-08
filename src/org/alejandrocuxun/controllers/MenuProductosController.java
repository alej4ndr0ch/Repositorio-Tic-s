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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.dto.ProductosDTO;
import org.alejandrocuxun.models.Productos;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class MenuProductosController implements Initializable {
    Main stage;
    int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblProductos;
    
    @FXML
    TableColumn colProductoId, colNombreProducto, colDescripcionProducto, colCantidadProducto, colPrecioVentaUnitaria, colPrecioVentaMayor, colPrecioCompra, colImagenProducto, colDistribuidorId, colCategoriaProductoId;
    
    @FXML
    Button btnRegresar, btnAgregar, btnEditar, btnEliminar, btnBuscar;
    
    @FXML
    TextField tfProductoId;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formProductosView(1);
        }else if(event.getSource() == btnEditar){
            ProductosDTO.getProductoDTO().setProducto((Productos)tblProductos.getSelectionModel().getSelectedItem());
            stage.formProductosView(2);
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarProducto(((Productos)tblProductos.getSelectionModel().getSelectedItem()).getProductoId());
                cargarDatos();
            }
        }else if (event.getSource() == btnBuscar){
            tblProductos.getItems().clear();
            if(tfProductoId.getText().equals("")){
                cargarDatos();
            
            }else{
                op = 3;
                cargarDatos();
            }
        }
    }
    
    public void cargarDatos(){
        if(op == 3){
            tblProductos.getItems().add(buscarProducto());
            op = 0;
        }else{
            tblProductos.setItems(listarProductos()); 
        }
            colProductoId.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("productoId"));
            colNombreProducto.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreProducto"));
            colDescripcionProducto.setCellValueFactory(new PropertyValueFactory<Productos, String>("descripcionProducto"));
            colCantidadProducto.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("cantidadStock"));
            colPrecioVentaUnitaria.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioVentaUnitario"));
            colPrecioVentaMayor.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioVentaMayor"));
            colPrecioCompra.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioCompra"));
            colImagenProducto.setCellValueFactory(new PropertyValueFactory<Productos, Blob>("imagenProducto"));
            colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Productos, String>("distribuidor"));
            colCategoriaProductoId.setCellValueFactory(new PropertyValueFactory<Productos, String>("CategoriaP"));
        
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
                int cantidadProducto = resultSet.getInt("cantidadProducto");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagenProducto = resultSet.getBlob("imagenProducto");
                String distribuidorId = resultSet.getString("distribuidor");
                String categoriaproductosId = resultSet.getString("categoria");
            
                productos.add(new Productos(productoId, nombreProducto, descripcionProducto, cantidadProducto, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId,categoriaproductosId));
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
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void eliminarProducto(int proId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EliminarProductos(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,proId);
            statement.execute();
            
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
    }
    
    public Productos buscarProducto(){
        Productos producto = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_BuscarProductos(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfProductoId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                 int productoId = resultSet.getInt("productoId");
                String nombreProducto = resultSet.getString("nombreProducto");
                String descripcionProducto = resultSet.getString("descripcionProducto");
                int cantidadStock = resultSet.getInt("cantidadStock");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagenProducto = resultSet.getBlob("imagenProducto");
                String distribuidor = resultSet.getString("distribuidor");
                String categoria = resultSet.getString("categoria");
            
                producto = new Productos(productoId, nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidor, categoria);

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
        return producto;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
