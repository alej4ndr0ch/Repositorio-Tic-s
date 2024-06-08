/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.dto.ProductosDTO;
import org.alejandrocuxun.models.CategoriaProducto;
import org.alejandrocuxun.models.Distribuidores;
import org.alejandrocuxun.models.Productos;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormProductosController implements Initializable {
    Main stage;
    int op;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    private List<File> files = null;
    
    @FXML
    Button btnRegresar, btnGuardar;
   
    @FXML
    TextField tfProductoId, tfNombreProducto, tfDescripcionProducto, tfCantidadProducto, tfPrecioVentaUnitaria, tfPrecioVentaMayor, tfPrecioCompra;

    @FXML
    ComboBox cmbDistribuidorId, cmbCategoriaProductoId;
    
    @FXML
    ImageView imgCargar, imgMostrar;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            ProductosDTO.getProductoDTO().setProducto(null);
            stage.menuProductosView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreProducto.getText().equals("") && !tfCantidadProducto.getText().equals("") && !tfPrecioVentaUnitaria.getText().equals("") && !tfPrecioVentaMayor.getText().equals("") && !tfPrecioCompra.getText().equals("")){
                    agregarProducto();
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                    stage.menuProductosView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfNombreProducto.getText().equals("")){
                        tfNombreProducto.requestFocus();
                    }else if(tfCantidadProducto.getText().equals("")){
                        tfCantidadProducto.requestFocus();
                    }else if(tfPrecioVentaUnitaria.getText().equals("")){
                        tfPrecioVentaUnitaria.requestFocus();
                    }else if(tfPrecioVentaMayor.getText().equals("")){
                        tfPrecioVentaMayor.requestFocus();
                    }else if(tfPrecioCompra.getText().equals("")){
                        tfPrecioCompra.requestFocus();
                    }
                }
                
               
            }else if(op == 2){
                if(!tfNombreProducto.getText().equals("") && !tfCantidadProducto.getText().equals("") && !tfPrecioVentaUnitaria.getText().equals("") && !tfPrecioVentaMayor.getText().equals("") && !tfPrecioCompra.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarProducto();
                        ProductosDTO.getProductoDTO().setProducto(null);
                        SuperKinalAlert.getInstance().mostrarAlertaInformacion(500);
                        stage.menuProductosView();
                    }else{
                        stage.menuProductosView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfNombreProducto.getText().equals("")){
                        tfNombreProducto.requestFocus();
                    }else if(tfCantidadProducto.getText().equals("")){
                        tfCantidadProducto.requestFocus();
                    }else if(tfPrecioVentaUnitaria.getText().equals("")){
                        tfPrecioVentaUnitaria.requestFocus();
                    }else if(tfPrecioVentaMayor.getText().equals("")){
                        tfPrecioVentaMayor.requestFocus();
                    }else if(tfPrecioCompra.getText().equals("")){
                        tfPrecioCompra.requestFocus();
                    }
                }
                
            }
        }
    }
    
    @FXML
    public void handleOnDrop(DragEvent event){
        try{
            files = event.getDragboard().getFiles();
            FileInputStream file = new FileInputStream(files.get(0));
            Image image = new Image(file);
            imgCargar.setImage(image);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleOnDrag(DragEvent event){
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    
    public void agregarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_AgregarProductos(?,?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreProducto.getText());
            statement.setString(2, tfDescripcionProducto.getText());
            statement.setString(3, tfCantidadProducto.getText());
            statement.setString(4, tfPrecioVentaUnitaria.getText());
            statement.setString(5, tfPrecioVentaMayor.getText());
            statement.setString(6, tfPrecioCompra.getText());

            if (files != null && !files.isEmpty() && files.get(0) != null) {
                InputStream img = new FileInputStream(files.get(0));
                statement.setBinaryStream(7, img);
            } else {
                statement.setBinaryStream(7, null); 
            }
            statement.setInt(8,((Distribuidores)cmbDistribuidorId.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(9,((CategoriaProducto)cmbCategoriaProductoId.getSelectionModel().getSelectedItem()).getCategoriaProductoId());
            statement.execute();
        }catch(Exception e){
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
    
    public void editarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarProductos(?,?,?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfProductoId.getText()));
            statement.setString(2, tfNombreProducto.getText());
            statement.setString(3, tfDescripcionProducto.getText());
            statement.setString(4, tfCantidadProducto.getText());
            statement.setString(5, tfPrecioVentaUnitaria.getText());
            statement.setString(6, tfPrecioVentaMayor.getText());
            statement.setString(7, tfPrecioCompra.getText());
            if (files != null && !files.isEmpty() && files.get(0) != null) {
                InputStream img = new FileInputStream(files.get(0));
                statement.setBinaryStream(8, img);
            } else {
                statement.setBinaryStream(8, null); 
            }
            statement.setInt(9,((Distribuidores)cmbDistribuidorId.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(10,((CategoriaProducto)cmbCategoriaProductoId.getSelectionModel().getSelectedItem()).getCategoriaProductoId());
            statement.execute();
        }catch(Exception e){
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
        if(ProductosDTO.getProductoDTO().getProducto() != null){
            cargarDatos(ProductosDTO.getProductoDTO().getProducto());
        }
        cmbDistribuidorId.setItems(listarDistribuidores());
        cmbCategoriaProductoId.setItems(listarCategoriasP());
        
    }
    
    public void cargarDatos(Productos producto) {
        try {
            tfProductoId.setText(Integer.toString(producto.getProductoId()));
            tfNombreProducto.setText(producto.getNombreProducto());
            tfDescripcionProducto.setText(producto.getDescripcionProducto());
            tfCantidadProducto.setText(Integer.toString(producto.getCantidadProducto()));
            tfPrecioVentaUnitaria.setText(Double.toString(producto.getPrecioVentaUnitaria()));
            tfPrecioVentaMayor.setText(Double.toString(producto.getPrecioVentaMayor()));
            tfPrecioCompra.setText(Double.toString(producto.getPrecioCompra()));

            if (producto.getImagenProducto() != null) {
                InputStream file = producto.getImagenProducto().getBinaryStream();
                Image image = new Image(file);
                imgMostrar.setImage(image);
            } else {
                imgMostrar.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public ObservableList<Distribuidores> listarDistribuidores(){
        ArrayList<Distribuidores> distribuidores = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarDistribuidores()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("nitDistribuidor");
                int telefono = resultSet.getInt("telefono");
                String web = resultSet.getString("web");
            
                distribuidores.add(new Distribuidores(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefono, web));
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
        
        
        return FXCollections.observableList(distribuidores);
    }
    
    public ObservableList<CategoriaProducto> listarCategoriasP(){
        ArrayList<CategoriaProducto> categoriasP = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarCategoriasProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int categoriaProductoID = resultSet.getInt("categoriaproductosId");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
            
                categoriasP.add(new CategoriaProducto(categoriaProductoID, nombreCategoria, descripcionCategoria));
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
        
        
        return FXCollections.observableList(categoriasP);
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
