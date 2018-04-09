/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class DrawerBoxController implements Initializable {

    
    @FXML
    private JFXButton btn_acceuil;

    
    
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //btn_acceuil.setBackground();
    }    
    
    
    @FXML
    void vaccinAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VaccinFXML.fxml"));
 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.setFullScreen(false);
         stage.resizableProperty().set(false);
         stage.show();
    }
     @FXML
    void rendezAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RdvFXML.fxml"));
 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.setFullScreen(false);
         stage.resizableProperty().set(false);
         stage.show();
    }
     @FXML
    void enfantAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EnfantFXML.fxml"));
 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.setFullScreen(false);
         stage.resizableProperty().set(false);
         stage.show();
    }
     void pediatreAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VaccinFXML.fxml"));
 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.setFullScreen(false);
         stage.resizableProperty().set(false);
         stage.show();
    }
     void logOut(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/edu/AllForKids/GUI/VaccinFXML.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
     
         Scene scene = new Scene(root);
 

        stage.setScene(scene);
    
        stage.setFullScreen(false);
        stage.resizableProperty().set(false);
        stage.show();
    }

    @FXML
    private void menuAction(ActionEvent event) {
    } 
    
}
