/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.systems;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.alejandrocuxun.controllers.FormAsignarEmpleadoController;
import org.alejandrocuxun.controllers.FormCargosController;
import org.alejandrocuxun.controllers.FormCategoriaProductoController;
import org.alejandrocuxun.controllers.FormClienteController;
import org.alejandrocuxun.controllers.FormComprasController;
import org.alejandrocuxun.controllers.FormDetalleCompraController;
import org.alejandrocuxun.controllers.FormDetalleFacturaController;
import org.alejandrocuxun.controllers.FormDistribuidoresController;
import org.alejandrocuxun.controllers.FormEmpleadosController;
import org.alejandrocuxun.controllers.FormFacturasController;
import org.alejandrocuxun.controllers.FormProductosController;
import org.alejandrocuxun.controllers.FormPromocionesController;
import org.alejandrocuxun.controllers.FormUsuarioController;
import org.alejandrocuxun.controllers.LoginController;
import org.alejandrocuxun.controllers.MenuCargosController;
import org.alejandrocuxun.controllers.MenuCategoriaProductoController;
import org.alejandrocuxun.controllers.MenuClientesController;
import org.alejandrocuxun.controllers.MenuComprasController;
import org.alejandrocuxun.controllers.MenuDistribuidoresController;
import org.alejandrocuxun.controllers.MenuEmpleadosController;
import org.alejandrocuxun.controllers.MenuFacturasController;
import org.alejandrocuxun.controllers.MenuPrincipalController;
import org.alejandrocuxun.controllers.MenuProductosController;
import org.alejandrocuxun.controllers.MenuPromocionesController;
import org.alejandrocuxun.controllers.MenuTicketSoporteController;

/**
 *
 * @author informatica
 */
public class Main extends Application {
    private final String URLVIEW = "/org/alejandrocuxun/views/";
    private Stage stage;
    private Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Super Kinal App");
        loginView();
        stage.show();
    }
    
    public Initializable switchScene(String fxmlName, int width, int height) throws Exception{
        Initializable resultado;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        System.out.println("Loading FXML from: " + URLVIEW + fxmlName);
  
        scene = new Scene((AnchorPane) loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
             
        resultado = (Initializable)loader.getController();
        
        return resultado;
    }
    
    public void menuPrincipalView(){
        try{
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 850, 702);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuClienteView(){
         try{
            MenuClientesController menuClientesView = (MenuClientesController)switchScene("MenuClientesView.fxml", 1200, 750);
            menuClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formClienteView(int op){
         try{
            FormClienteController formClienteView = (FormClienteController)switchScene("FormClienteView.fxml", 500, 750);
            formClienteView.setOp(op);
            formClienteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuTicketSoporteView(){
        try{
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml", 1200,750);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuEmpleadosView(){
        try{
            MenuEmpleadosController menuEmpleadosView = (MenuEmpleadosController)switchScene("MenuEmpleadosView.fxml", 1200,750);
            menuEmpleadosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuFacturasView(){
        try{
            MenuFacturasController menuFacturasView = (MenuFacturasController)switchScene("MenuFacturasView.fxml", 1200,750);
            menuFacturasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuComprasView(){
        try{
            MenuComprasController menuComprasView = (MenuComprasController)switchScene("MenuComprasView.fxml", 1200,750);
            menuComprasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuCategoriaProductoView(){
        try{
            MenuCategoriaProductoController menuCategoriaProductoView = (MenuCategoriaProductoController)switchScene("MenuCategoriaProductoView.fxml", 1200,750);
            menuCategoriaProductoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuDistribuidoresView(){
        try{
            MenuDistribuidoresController menuDistribuidoresView = (MenuDistribuidoresController)switchScene("MenuDistribuidoresView.fxml", 1200,750);
            menuDistribuidoresView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuPromocionesView(){
        try{
            MenuPromocionesController menuPromocionesView = (MenuPromocionesController)switchScene("MenuPromocionesView.fxml", 1200,750);
            menuPromocionesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuProductosView(){
        try{
            MenuProductosController menuProductosView = (MenuProductosController)switchScene("MenuProductosView.fxml", 1300,900);
            menuProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCategoriaProductoView(int op){
        try{
            FormCategoriaProductoController formCategoriaProductoView = (FormCategoriaProductoController)switchScene("FormCategoriaProductoView.fxml", 500, 750);
            formCategoriaProductoView.setOp(op);
            formCategoriaProductoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formComprasView(int op){
        try{
            FormComprasController formComprasView = (FormComprasController)switchScene("FormComprasView.fxml", 500, 750);
            formComprasView.setOp(op);
            formComprasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleCompraView(int op){
        try{
            FormDetalleCompraController formDetalleCompraView = (FormDetalleCompraController)switchScene("FormDetalleCompraView.fxml", 500, 750);
            formDetalleCompraView.setOp(op);
            formDetalleCompraView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleFacturaView(int op){
        try{
            FormDetalleFacturaController formDetalleFacturaView = (FormDetalleFacturaController)switchScene("FormDetalleFacturaView.fxml", 500, 750);
            formDetalleFacturaView.setOp(op);
            formDetalleFacturaView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDistribuidoresView(int op){
        try{
            FormDistribuidoresController formDistribuidoresView = (FormDistribuidoresController)switchScene("FormDistribuidoresView.fxml", 500, 750);
            formDistribuidoresView.setOp(op);
            formDistribuidoresView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formEmpleadosView(int op){
        try{
            FormEmpleadosController formEmpleadosView = (FormEmpleadosController)switchScene("FormEmpleadosView.fxml", 500, 750);
            formEmpleadosView.setOp(op);
            formEmpleadosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formFacturasView(int op){
        try{
            FormFacturasController formFacturasView = (FormFacturasController)switchScene("FormFacturasView.fxml", 500, 750);
            formFacturasView.setOp(op);
            formFacturasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formProductosView(int op){
        try{
            FormProductosController formProductosView = (FormProductosController)switchScene("FormProductosView.fxml", 500, 750);
            formProductosView.setOp(op);
            formProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formPromocionesView(int op){
        try{
            FormPromocionesController formPromocionesView = (FormPromocionesController)switchScene("FormPromocionesView.fxml", 500, 750);
            formPromocionesView.setOp(op);
            formPromocionesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuCargosView(){
        try{
            MenuCargosController menuCargosView = (MenuCargosController)switchScene("MenuCargosView.fxml", 1200,750);
            menuCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCargosView(int op){
        try{
            FormCargosController formCargosView = (FormCargosController)switchScene("FormCargosView.fxml", 500, 750);
            formCargosView.setOp(op);
            formCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formAsignarEmpleadoView(int op){
        try{
            FormAsignarEmpleadoController formAsignarEmpleadoView = (FormAsignarEmpleadoController)switchScene("FormAsignarEmpleadoView.fxml", 500, 750);
            formAsignarEmpleadoView.setOp(op);
            formAsignarEmpleadoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void loginView(){
        try{
            LoginController loginView = (LoginController) switchScene("LoginView.fxml", 500, 750);
            loginView.setStage(this);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void formUsuarioView(){
        try{
            FormUsuarioController formUsuarioView = (FormUsuarioController) switchScene ("FormUsuarioView.fxml", 500, 750);
            formUsuarioView.setStage(this);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
