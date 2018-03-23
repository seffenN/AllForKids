/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Narjes
 */
public class AjoutMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Parent root;
        try {
              //root = FXMLLoader.load(getClass().getResource("AjoutProd.fxml"));
              root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
         } catch (IOException ex) {
            Logger.getLogger(AjoutMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
        
        
       
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
