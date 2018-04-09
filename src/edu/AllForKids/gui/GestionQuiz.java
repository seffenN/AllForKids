/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.HostServices;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

import javafx.stage.Stage;

/**
 *
 * @author khaoula
 */
public class GestionQuiz extends Application {
    private static HostServices hostServices ;
    public static HostServices host;

   

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AcceuilFrontEnd.fxml"));
            Scene scene = new Scene(root);
         
            primaryStage.setScene(scene);
            /* primaryStage.setMaximized(true);
            primaryStage.setFullScreen(true);*/
          Screen screen = Screen.getPrimary();
          Rectangle2D bounds = screen.getVisualBounds();
            host=getHostServices();

primaryStage.setX(bounds.getMinX());
primaryStage.setY(bounds.getMinY());
primaryStage.setWidth(bounds.getWidth());
primaryStage.setHeight(bounds.getHeight());
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

 
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
