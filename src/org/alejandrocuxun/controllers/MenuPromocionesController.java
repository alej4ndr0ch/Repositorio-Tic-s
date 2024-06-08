/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.controllers;

import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.dto.PromocionesDTO;
import org.alejandrocuxun.models.Promociones;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class MenuPromocionesController implements Initializable {
    Main stage;
    int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblPromociones;
    
    @FXML
    TableColumn colPromocionId, colPrecioPromocion, colDescripcionPromocion, colFechaInicio, colFechaFinalizacion, colProductoId;
    
    @FXML
    Button btnRegresar, btnAgregar, btnEditar, btnEliminar, btnBuscar;
    
    @FXML
    TextField tfPromocionId;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formPromocionesView(1);
        }else if(event.getSource() == btnEditar){
            PromocionesDTO.getPromocionDTO().setPromocion((Promociones)tblPromociones.getSelectionModel().getSelectedItem());
            stage.formPromocionesView(2);
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarPromociones(((Promociones)tblPromociones.getSelectionModel().getSelectedItem()).getPromocionId());
                cargarDatos();
            }
        }else if (event.getSource() == btnBuscar){
            tblPromociones.getItems().clear();
            if(tfPromocionId.getText().equals("")){
                cargarDatos();
            
            }else{
                op = 3;
                cargarDatos();
            }
        }
    }
    
    public void cargarDatos(){
        if(op == 3){
            tblPromociones.getItems().add(buscarPromocion());
            op = 0;
        }else{
            tblPromociones.setItems(listarPromociones()); 
        }
            colPromocionId.setCellValueFactory(new PropertyValueFactory<Promociones, Integer>("promocionId"));
            colPrecioPromocion.setCellValueFactory(new PropertyValueFactory<Promociones, Double>("precioProm"));
            colDescripcionPromocion.setCellValueFactory(new PropertyValueFactory<Promociones, String>("descripcionProm"));
            colFechaInicio.setCellValueFactory(new PropertyValueFactory<Promociones, Date>("fechaInicio"));
            colFechaFinalizacion.setCellValueFactory(new PropertyValueFactory<Promociones, Date>("fechaFin"));
            colProductoId.setCellValueFactory(new PropertyValueFactory<Promociones, String>("productoId"));;
        
    }
    
    public ObservableList<Promociones> listarPromociones(){
        ArrayList<Promociones> promociones = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarPromociones()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int promocionId = resultSet.getInt("promocionId");
                int precioPromocion = resultSet.getInt("precioPromocion");
                String descripcionPromocion = resultSet.getString("descripcionPromocion");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFinalizacion = resultSet.getDate("fechaFinalizacion");
                String productoId = resultSet.getString("Producto");
            
                promociones.add(new Promociones(promocionId, precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId));
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
        
        
        return FXCollections.observableList(promociones);
    }
    
    public void eliminarPromociones(int promId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EliminarPromociones(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,promId);
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
    
    public Promociones buscarPromocion(){
        Promociones promociones = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_BuscarPromociones(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfPromocionId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int promocionId = resultSet.getInt("promocionId");
                int precioPromocion = resultSet.getInt("precioPromocion");
                String descripcionPromocion = resultSet.getString("descripcionPromocion");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFinalizacion = resultSet.getDate("fechaFinalizacion");
                String productoId = resultSet.getString("Producto");
            
                promociones = new Promociones(promocionId, precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId);
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
        return promociones;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
