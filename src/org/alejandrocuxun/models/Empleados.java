/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.models;

import java.sql.Time;

/**
 *
 * @author aleja
 */
public class Empleados {
    int empleadoId;
    String nombreEmpleado;
    String apellidoEmpleado;
    Time horaEntrada;
    Time horaSalida;
    String cargoId;
    String encargadoId;
    double sueldo;

    public Empleados() {
    }

    public Empleados(int empleadoId, String nombreEmpleado, String apellidoEmpleado, Time horaEntrada, Time horaSalida, String cargoId, String encargadoId, double sueldo) {
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.cargoId = cargoId;
        this.encargadoId = encargadoId;
        this.sueldo = sueldo;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(String encargadoId) {
        this.encargadoId = encargadoId;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Empleados{" + "empleadoId=" + empleadoId + ", nombreEmpleado=" + nombreEmpleado + ", apellidoEmpleado=" + apellidoEmpleado + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", cargo=" + cargoId + ", encargado=" + encargadoId + ", sueldo=" + sueldo + '}';
    }
}
