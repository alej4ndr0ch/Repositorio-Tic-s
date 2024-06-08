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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.alejandrocuxun.dao.Conexion;
import org.alejandrocuxun.models.Empleados;
import org.alejandrocuxun.models.NivelAcceso;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.PasswordUtils;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormUsuarioController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    
    @FXML
    TextField tfUser, tfPassword;
    @FXML
    ComboBox cmbEmpleado, cmbNivelAcceso;
    @FXML
    Button btnRegistrar, btnEmpleado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbEmpleado.setItems(listarEmpleados());
        cmbNivelAcceso.setItems(listarNivelesAcceso());
    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegistrar){
            agregarUsuario();
            stage.loginView();
        }else if(event.getSource() == btnEmpleado) {
            
            stage.formEmpleadosView(1);
        }
    }
    
    public ObservableList<NivelAcceso> listarNivelesAcceso() {
        ArrayList<NivelAcceso> nivelesAcceso = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarNivelAcceso()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                int nivelAccesoId = resultSet.getInt("nivelAccesoId");
                String nivelAcceso = resultSet.getString("nivelAcceso");
                
                nivelesAcceso.add(new NivelAcceso(nivelAccesoId, nivelAcceso));
            }
            
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null) {
                    statement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
                if(conexion != null) {
                    conexion.close();
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableArrayList(nivelesAcceso);
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
            
                empleados.add(new Empleados(empleadoId, nombreEmpleado, apellidoEmpleado, horaEntrada, horaSalida, cargoId, encargadoId, sueldo));
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
    
    public void agregarUsuario(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarUsuario(?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfUser.getText());
            statement.setString(2, PasswordUtils.getInstance().encryptedPassword(tfPassword.getText()));
            statement.setInt(3, ((NivelAcceso)cmbNivelAcceso.getSelectionModel().getSelectedItem()).getNivelAccesoId());
            statement.setInt(4,((Empleados)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.execute();
        }catch(SQLException e) {
            System.out.println(e.getMessage()); 
        }finally{
            try{
                if(statement != null) {
                    statement.close();
                }
                if(conexion != null) {
                    conexion.close();
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void setStage(Main stage) {
        this.stage = stage;
    }
}
