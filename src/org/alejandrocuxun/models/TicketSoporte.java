/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.models;

/**
 *
 * @author informatica
 */
public class TicketSoporte {
    int ticketSoporteId;
    String descripcionTicket;
    String estatus;
    int clienteId;
    String cliente;
    int facturaId;
    
    public TicketSoporte(){
    }

    public TicketSoporte(int ticketSoporteId, String descripcionTicket, String estatus, String cliente, int facturaId) {
        this.ticketSoporteId = ticketSoporteId;
        this.descripcionTicket = descripcionTicket;
        this.estatus = estatus;
        this.cliente = cliente;
        this.facturaId = facturaId;
    }

    public int getTicketSoporteId() {
        return ticketSoporteId;
    }

    public void setTicketSoporteId(int ticketSoporteId) {
        this.ticketSoporteId = ticketSoporteId;
    }

    public String getDescripcion() {
        return descripcionTicket;
    }

    public void setDescripcion(String descripcionTicket) {
        this.descripcionTicket = descripcionTicket;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    @Override
    public String toString() {
        return "TicketSoporte{" + "ticketSoporteId=" + ticketSoporteId + ", descripcion=" + descripcionTicket + ", estatus=" + estatus + ", clienteId=" + clienteId + ", cliente=" + cliente + ", facturaId=" + facturaId + '}';
    }
}
