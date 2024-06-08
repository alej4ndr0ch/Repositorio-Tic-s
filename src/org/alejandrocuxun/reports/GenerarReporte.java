/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alejandrocuxun.reports;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.alejandrocuxun.dao.Conexion;
import win.zqxu.jrviewer.JRViewerFX;

/**
 *
 * @author aleja
 */
public class GenerarReporte {
    private static GenerarReporte instance;
    
    private static Connection conexion = null;

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private GenerarReporte(){
        
    }
    
    public static GenerarReporte getInstance(int factId){
        if(instance == null){
            instance = new GenerarReporte();
        }
        return instance;
    }
    
    public void generarFactura(int factId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("factId", factId);
            
            Stage reportStage = new Stage();
            
            JasperPrint reporte = JasperFillManager.fillReport(
                    GenerarReporte.class.getResourceAsStream("/org/alejandrocuxun/reports/Factura.jasper"), 
                    parametros, conexion);
            
            JRViewerFX reportView = new JRViewerFX(reporte);
            
            Pane root = new Pane();
            root.getChildren().add(reportView);
            
            reportView.setPrefSize(1000, 800);
            
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Factura");
            reportStage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void generarCliente(int cliId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("cliId", cliId);
            
            Stage reportStage = new Stage();
            
            JasperPrint reporte = JasperFillManager.fillReport(
                    GenerarReporte.class.getResourceAsStream("/org/alejandrocuxun/reports/Cliente.jasper"), 
                    parametros, conexion);
            
            JRViewerFX reportView = new JRViewerFX(reporte);
            
            Pane root = new Pane();
            root.getChildren().add(reportView);
            
            reportView.setPrefSize(1000, 800);
            
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Cliente");
            reportStage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
