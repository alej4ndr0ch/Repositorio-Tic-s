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
import javafx.scene.control.TextField;
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
public class FormAsignarEmpleadoController implements Initializable {
    Main stage;
    
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
   @FXML
    Button btnRegresar,btnGuardar;
   
   @FXML
   TextField tfEmpleadoId;
   
   @FXML
   ComboBox cmbEncargados;
   
   @FXML
   private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnRegresar){
            EmpleadosDTO.getEmpleadoDTO().setEmpleado(null);
            stage.menuEmpleadosView();
        }else if(event.getSource() == btnGuardar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                asignarEncargado();
                EmpleadosDTO.getEmpleadoDTO().setEmpleado(null);
                SuperKinalAlert.getInstance().mostrarAlertaInformacion(500);
                stage.menuEmpleadosView();
            }
        }
   }
                
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(EmpleadosDTO.getEmpleadoDTO().getEmpleado() != null){
            cargarDatos(EmpleadosDTO.getEmpleadoDTO().getEmpleado());
        }
        cmbEncargados.setItems(listarEmpleados());
    }
    
    public void cargarDatos(Empleados empleado){
        tfEmpleadoId.setText(Integer.toString(empleado.getEmpleadoId()));
    }
    
    public void asignarEncargado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_AsignarEncargado(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            statement.setInt(2,((Empleados)cmbEncargados.getSelectionModel().getSelectedItem()).getEmpleadoId());
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
                String cargoId = resultSet.getString("cargoId");
                String encargadoId = resultSet.getString("EncargadoId");
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

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public void setOp(int op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
