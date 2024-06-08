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
import java.sql.Time;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.dto.EmpleadosDTO;
import org.alejandrocuxun.models.Empleados;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class MenuEmpleadosController implements Initializable {
    Main stage;
    int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TextField tfEmpledoId, tfNombreEmpledo, tfApellidoEmpledo, tfHoraEntrada, tfHoraSalida, tfSueldo;
    
    @FXML
    ComboBox cmbCargo;
    
    @FXML
    TableView tblEmpleados;
    
    @FXML
    TableColumn colEmpleadoId, colNombreEmpleado, colApellidoEmpleado, colHoraEntrada, colHoraSalida, colCargoId, colEncargadoId, colSueldo;
    
    @FXML
    Button btnAgregar, btnEliminar, btnRegresar, btnEditar, btnBuscar, btnAsignar;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formEmpleadosView(1);
        }else if(event.getSource() == btnEditar){
            EmpleadosDTO.getEmpleadoDTO().setEmpleado((Empleados)tblEmpleados.getSelectionModel().getSelectedItem());
            stage.formEmpleadosView(2);
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarEmpleado(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getEmpleadoId());
                cargarDatos();
            }
        }else if (event.getSource() == btnBuscar){
            tblEmpleados.getItems().clear();
            if(tfEmpledoId.getText().equals("")){
                cargarDatos();
            
            }else{
                op = 3;
                cargarDatos();
            }
        }else if(event.getSource() == btnAsignar){
            EmpleadosDTO.getEmpleadoDTO().setEmpleado((Empleados)tblEmpleados.getSelectionModel().getSelectedItem());
            stage.formAsignarEmpleadoView(op);
        }
    
    }
    
    public void cargarDatos(){
        if(op == 3){
            tblEmpleados.getItems().add(buscarEmpleado());
            op = 0;
            
        }else{
            tblEmpleados.setItems(listarEmpleados()); 

            colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("empleadoId"));
            colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("nombreE"));
            colApellidoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("apellidoE"));
            colSueldo.setCellValueFactory(new PropertyValueFactory<Empleados, Double>("sueldo"));
            colHoraEntrada.setCellValueFactory(new PropertyValueFactory<Empleados, Time>("horaEntrada"));
            colHoraSalida.setCellValueFactory(new PropertyValueFactory<Empleados, Time>("horaSalida"));
            colCargoId.setCellValueFactory(new PropertyValueFactory<Empleados, String>("cargo"));
            colEncargadoId.setCellValueFactory(new PropertyValueFactory<Empleados, String>("encargado"));
        }
        
        
    }
    
    public ObservableList<Empleados> listarEmpleados(){
        ArrayList<Empleados> empleados = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                Time horaEntrada = resultSet.getTime("horaentrada");
                Time horaSalida = resultSet.getTime("horaSalida");
                String cargoId = resultSet.getString("cargo");
                String encargadoId = resultSet.getString("nombreEncargado");
                double sueldo = resultSet.getDouble("sueldo");
            
                empleados.add(new Empleados(empleadoId, nombreEmpleado, apellidoEmpleado,horaEntrada, horaSalida, cargoId, encargadoId, sueldo));
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
        
        
        return FXCollections.observableList(empleados);
    }
    
    public void eliminarEmpleado(int empId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EliminarEmpleados(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,empId);
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
    
    public Empleados buscarEmpleado(){
        Empleados empleado = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_BuscarEmpleados(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfEmpledoId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                Time horaentrada = resultSet.getTime("horaentrada");
                Time horaSalida = resultSet.getTime("horaSalida");
                String cargo = resultSet.getString("cargo");
                double sueldo = resultSet.getDouble("sueldo");
                String encargado = resultSet.getString("nombreEncargado");
            
                empleado = new Empleados(empleadoId, nombreEmpleado, apellidoEmpleado, horaentrada, horaSalida, cargo, encargado, sueldo);

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
        return empleado;
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
