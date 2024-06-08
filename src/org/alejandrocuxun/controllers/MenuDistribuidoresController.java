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
import org.alejandrocuxun.dto.DistribuidoresDTO;
import org.alejandrocuxun.models.Distribuidores;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class MenuDistribuidoresController implements Initializable {
    Main stage;
    int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblDistribuidores;
    
    @FXML
    TableColumn colDistribuidorId, colNombreDistribuidor, colDireccionDistribuidor, colNitDistribuidor, ColTelefono, colWeb;
    
    @FXML
    Button btnRegresar, btnAgregar, btnEditar, btnEliminar, btnBuscar;
    
    @FXML
    TextField tfDistribuidorId;
    
     @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formDistribuidoresView(1);
        }else if(event.getSource() == btnEditar){
            DistribuidoresDTO.getDistribuidorDTO().setDistribuidores((Distribuidores)tblDistribuidores.getSelectionModel().getSelectedItem());
            stage.formDistribuidoresView(2);
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarDistribuidor(((Distribuidores)tblDistribuidores.getSelectionModel().getSelectedItem()).getDistribuidorId());
                cargarDatos(); 
            }
        }else if(event.getSource() == btnBuscar){
            tblDistribuidores.getItems().clear();
            if(tfDistribuidorId.getText().equals("")){
                cargarDatos();
            }else{
                op = 3;
                cargarDatos();
            }
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

     public void cargarDatos(){
        if(op == 3){
            tblDistribuidores.getItems().add(buscarDistribuidor());
            op = 0;
        }else{
            tblDistribuidores.setItems(listarDistribuidor());
        }
        colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer>("distribuidorId"));
        colNombreDistribuidor.setCellValueFactory(new PropertyValueFactory<Distribuidores, String>("nombreDistribuidor"));
        colDireccionDistribuidor.setCellValueFactory(new PropertyValueFactory<Distribuidores, String>("direccionDistribuidor"));
        colNitDistribuidor.setCellValueFactory(new PropertyValueFactory<Distribuidores, String>("NitDistribuidor"));
        ColTelefono.setCellValueFactory(new PropertyValueFactory<Distribuidores, String>("telefonoDistribuidor"));
        colWeb.setCellValueFactory(new PropertyValueFactory<Distribuidores, String>("web"));
    }
    
    public ObservableList<Distribuidores> listarDistribuidor(){
        ArrayList<Distribuidores> Distribuidores = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarDistribuidores()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("NitDistribuidor");
                int telefono = resultSet.getInt("telefono");
                String web = resultSet.getString("web");
                
                Distribuidores.add(new Distribuidores(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefono, web));
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
        return FXCollections.observableList(Distribuidores);
    }
    
    public void eliminarDistribuidor(int disId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EliminarDistribuidores(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, disId);
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
    
    public Distribuidores buscarDistribuidor(){
        Distribuidores distribuidores = null;
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_BuscarDistribuidores(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfDistribuidorId.getText()));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("NitDistribuidor");
                int telefono = resultSet.getInt("telefonoDistribuidor");
                String web = resultSet.getString("web");
                
                distribuidores = new Distribuidores(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefono, web);
            }
            
        }catch(Exception e){
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
        return distribuidores;
    }
    public void setStage(Main stage) {
        this.stage = stage;
    }

    public Main getStage() {
        return stage;
    }
}
