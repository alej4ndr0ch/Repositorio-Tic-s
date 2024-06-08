/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    package org.alejandrocuxun.controllers;

    import java.net.URL;
    import java.util.ResourceBundle;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.MenuItem;
    import org.alejandrocuxun.systems.Main;

    /**
     *
     * @author informatica
     */
    public class MenuPrincipalController implements Initializable {
        private Main stage;

        @FXML
        MenuItem btnMenuClientes, btnTicketSoporte, btnCategoriaProducto, btnCompras, btnDetalleCompra, btnDetalleFactura, btnDistribuidores, btnEmpleados, btnFacturas, btnProductos, btnPromociones;

        @FXML
        public void handleButtonAction(ActionEvent event)throws Exception{
            if(event.getSource() == btnMenuClientes){
                stage.menuClienteView();
            }else if(event.getSource() == btnTicketSoporte){
                stage.menuTicketSoporteView();
            }else if(event.getSource() == btnCategoriaProducto){
                stage.menuCategoriaProductoView();
            }else if(event.getSource() == btnCompras){
                stage.menuComprasView();
            }else if(event.getSource() == btnDistribuidores){
                stage.menuDistribuidoresView();
            }else if(event.getSource() == btnEmpleados){
                stage.menuEmpleadosView();
            }else if(event.getSource() == btnFacturas){
                stage.menuFacturasView();
            }else if(event.getSource() == btnProductos){
                stage.menuProductosView();
            }else if(event.getSource() == btnPromociones){
                stage.menuPromocionesView();
            }
        }

        public void setStage(Main stage) {
            this.stage = stage;
        }

        public Main getStage() {
            return stage;
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }
    }
