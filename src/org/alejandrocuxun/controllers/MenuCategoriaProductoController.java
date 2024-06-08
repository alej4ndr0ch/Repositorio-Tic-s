/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.controllers;

import java.net.URL;
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
import org.alejandrocuxun.dto.CategoriaProductoDTO;
import org.alejandrocuxun.models.CategoriaProducto;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class MenuCategoriaProductoController implements Initializable {
    private Main stage;
    private int op;
    
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet;
    
   @FXML
    Button btnAgregar, btnEditar, btnEliminar, btnVaciar, btnBuscar, btnRegresar;
   @FXML
   TextField tfCategoriaProductosId, tfNombreCategoria, tfDescripcionCategoria;
   @FXML
   TableView tblCategoriaProductos;
   @FXML
   TableColumn colCategoriaProductosId, colNombreCategoria, colDescripcionCategoria;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formCategoriaProductoView(1);
        }else if(event.getSource() == btnEditar){
            CategoriaProductoDTO.getCategoriaPDTO().setCategoriaP((CategoriaProducto)tblCategoriaProductos.getSelectionModel().getSelectedItem());
            stage.formCategoriaProductoView(2);
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarCategoriaProducto(((CategoriaProducto)tblCategoriaProductos.getSelectionModel().getSelectedItem()).getCategoriaProductoId());
                cargarDatos();
            }
        }else if (event.getSource() == btnBuscar){
            tblCategoriaProductos.getItems().clear();
            if(tfCategoriaProductosId.getText().equals("")){
                cargarDatos();
            
            }else{
                op = 3;
                cargarDatos();
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }   
    
    public void cargarDatos(){
        if(op == 3){
            tblCategoriaProductos.getItems().add(buscarCategoriaP());
            op = 0;
        }else{
            tblCategoriaProductos.setItems(listarCategoriasProducto()); 
        }
        colCategoriaProductosId.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, Integer>("categoriaproductosId"));
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("nombreCategoria"));
        colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("descripcionCategoria"));
    }
    
    public ObservableList<CategoriaProducto> listarCategoriasProducto(){
        ArrayList<CategoriaProducto> categoriaProducto = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarCategoriaProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int categoriaProductoID = resultSet.getInt("categoriaproductosId");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
                categoriaProducto.add(new CategoriaProducto(categoriaProductoID, nombreCategoria, descripcionCategoria));
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
        return FXCollections.observableList(categoriaProducto);
    }
    
    public void eliminarCategoriaProducto(int catProdId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EliminarCategoriaProductos(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,catProdId);
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
    
    public CategoriaProducto buscarCategoriaP(){
        CategoriaProducto categoriaProducto = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_BuscarCategoriaProductos(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfCategoriaProductosId.getText()));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int categoriaPId = resultSet.getInt("categoriaproductosId");
                String nombreCategoriaProducto = resultSet.getString("nombreCategoria");
                String descripcionCategoriaProducto = resultSet.getString("descripcionCategoria");
                categoriaProducto = new CategoriaProducto(categoriaPId, nombreCategoriaProducto, descripcionCategoriaProducto);
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
        return categoriaProducto;
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
