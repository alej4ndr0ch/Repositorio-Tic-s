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
import org.alejandrocuxun.models.Cargos;
import org.alejandrocuxun.models.Empleados;
import org.alejandrocuxun.systems.Main;
import org.alejandrocuxun.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class FormEmpleadosController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
   @FXML
    Button btnRegresar,btnGuardar;
   
   @FXML
   TextField tfEmpleadoId, tfNombreEmpleado, tfApellidoEmpleado, tfHoraEntrada, tfHoraSalida, tfSueldo;
   
   @FXML
   ComboBox cmbCargos, cmbEncargados;
   
   @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            EmpleadosDTO.getEmpleadoDTO().setEmpleado(null);
            stage.menuEmpleadosView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreEmpleado.getText().equals("") && !tfApellidoEmpleado.getText().equals("") && !tfSueldo.getText().equals("") && !tfHoraEntrada.getText().equals("") && !tfHoraSalida.getText().equals("")){
                    agregarEmpleado();
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                    stage.menuEmpleadosView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfNombreEmpleado.getText().equals("")){
                        tfNombreEmpleado.requestFocus();
                    }else if(tfApellidoEmpleado.getText().equals("")){
                        tfApellidoEmpleado.requestFocus();
                    }else if(tfSueldo.getText().equals("")){
                        tfSueldo.requestFocus();
                    }else if(tfHoraEntrada.getText().equals("")){
                        tfHoraEntrada.requestFocus();
                    }else if(tfHoraSalida.getText().equals("")){
                        tfHoraSalida.requestFocus();
                    }
                }
                
               
            }else if(op == 2){
                if(!tfNombreEmpleado.getText().equals("") && !tfApellidoEmpleado.getText().equals("") && !tfSueldo.getText().equals("") && !tfHoraEntrada.getText().equals("") && !tfHoraSalida.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarEmpleado();
                        EmpleadosDTO.getEmpleadoDTO().setEmpleado(null);
                        SuperKinalAlert.getInstance().mostrarAlertaInformacion(500);
                        stage.menuEmpleadosView();
                    }else{
                        stage.menuEmpleadosView();
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(33);
                    if(tfNombreEmpleado.getText().equals("")){
                        tfNombreEmpleado.requestFocus();
                    }else if(tfApellidoEmpleado.getText().equals("")){
                        tfApellidoEmpleado.requestFocus();
                    }else if(tfSueldo.getText().equals("")){
                        tfSueldo.requestFocus();
                    }else if(tfHoraEntrada.getText().equals("")){
                        tfHoraEntrada.requestFocus();
                    }else if(tfHoraSalida.getText().equals("")){
                        tfHoraSalida.requestFocus();
                    }
                }
                
            }
        }
    }
   
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(EmpleadosDTO.getEmpleadoDTO().getEmpleado() != null){
            cargarDatos(EmpleadosDTO.getEmpleadoDTO().getEmpleado());
        }
        cmbCargos.setItems(listarCargos());
        
    }

    public void cargarDatos(Empleados empleado){
        tfEmpleadoId.setText(Integer.toString(empleado.getEmpleadoId()));
        tfNombreEmpleado.setText(empleado.getNombreEmpleado());
        tfApellidoEmpleado.setText(empleado.getApellidoEmpleado());
        tfHoraEntrada.setText(empleado.getHoraEntrada().toString());
        tfHoraSalida.setText(empleado.getHoraSalida().toString());
        tfSueldo.setText(Double.toString(empleado.getSueldo()));
    }
    
    public void agregarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_AgregarEmpleados(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreEmpleado.getText());
            statement.setString(2, tfApellidoEmpleado.getText());
            statement.setString(3, tfHoraEntrada.getText());
            statement.setString(4, tfHoraSalida.getText());
            statement.setString(5, tfSueldo.getText());
            statement.setInt(6,((Cargos)cmbCargos.getSelectionModel().getSelectedItem()).getCargoId());
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
    
    public ObservableList<Cargos> listarCargos(){
        ArrayList<Cargos> cargos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarCargos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int cargoId = resultSet.getInt("cargoId");
                String nombreCargo = resultSet.getString("nombreCargo");
                String descripcionCargo = resultSet.getString("descripcionCargo");
            
                cargos.add(new Cargos(cargoId, nombreCargo, descripcionCargo));
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
        
        
        return FXCollections.observableList(cargos);
    }
    
    public void editarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarEmpleados(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreEmpleado.getText());
            statement.setString(2, tfApellidoEmpleado.getText());
            statement.setString(3, tfHoraEntrada.getText());
            statement.setString(4, tfHoraSalida.getText());
            statement.setString(5, tfSueldo.getText());
            statement.setInt(6,((Cargos)cmbCargos.getSelectionModel().getSelectedItem()).getCargoId());
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
                Time horaEntrada = resultSet.getTime("horaEntrada");
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
