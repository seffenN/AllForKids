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
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;


import edu.AllForKids.entities.Vaccin;
import edu.AllForKids.services.CrudEnfant;

import edu.AllForKids.services.CrudVaccin;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class VaccinFXMLController implements Initializable {
      @FXML
    private TableView<Vaccin> table_event;
    @FXML
    private TableColumn<?, ?> c_nom;
    @FXML
    private TableColumn<?, ?> c_age;
    @FXML
    private TableColumn<?, ?> c_maladies;
    @FXML
    private TableColumn<?, ?> c_description;
    @FXML
    private JFXTextField tf_age;
    @FXML
    private JFXTextField tf_maladies;
    @FXML
    private JFXTextField tf_description;
    @FXML
    private JFXButton bt_modfiier;
    @FXML
    private JFXButton bt_supprimer;
    @FXML
    private JFXButton bt_ajouter;
    @FXML
    private Button bt_retour;
    ObservableList<Vaccin> data = FXCollections.observableArrayList();
      ObservableList<Vaccin> pdftoextract = FXCollections.observableArrayList();
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField tf_nom;
    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            VBox box = FXMLLoader.load(getClass().getResource("DrawerBox.fxml"));
             drawer.setSidePane(box);
                for(Node node : box.getChildren())
             { 
             if(node.getAccessibleText()!=null)
                 {   
             node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                      
                    switch(node.getAccessibleText())
                     {  
                         case "Vaccin":
                         
                         
                         break;
                         case "Rendez-vous":
                         break;
                         case "Enfant":
                         break;
                         case "Pediatre":
                         break;
                         case "Quitter":
                         break;
                             
                     }
                  
                 
               });
                 }
        }
          HamburgerBackArrowBasicTransition burgerTask2 =new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e)->{
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }
            else {
                drawer.open();
            }
                    
        });
        
        } catch (IOException ex) {
           // Logger.getLogger(DrawerMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CrudVaccin vaccinService = new CrudVaccin();
            c_nom.setCellValueFactory(new PropertyValueFactory<>("nomVaccin"));
            c_age.setCellValueFactory(new PropertyValueFactory<>("age"));
            c_maladies.setCellValueFactory(new PropertyValueFactory<>("maladies"));
            c_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            data.addAll(vaccinService.listofvaccins());
            System.out.println(data);
            table_event.setItems(data);
            table_event.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
                tf_nom.clear();
                tf_age.clear();
                tf_maladies.clear();
                tf_description.clear();
               
                tf_nom.setText(table_event.getSelectionModel().getSelectedItem().getNomVaccin());
                tf_age.setText(table_event.getSelectionModel().getSelectedItem().getAge());
                tf_maladies.setText(table_event.getSelectionModel().getSelectedItem().getMaladies());
                tf_description.setText(table_event.getSelectionModel().getSelectedItem().getDescription());
                 
                 
                 
            });
        // TODO
    }

    @FXML
    private void retourAction(ActionEvent event) {
        
        
    }

    @FXML
    private void updateVaccin(ActionEvent event) {
        
        if(tf_nom.getText().equalsIgnoreCase("")||tf_age.getText().equalsIgnoreCase("")||tf_maladies.getText().equalsIgnoreCase("")||tf_description.getText().equalsIgnoreCase(""))
       {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur lors de l'ajout d'un enfant");
         alert.setContentText("Vérifiez vos infomartions!");
         alert.showAndWait();
       }
        else
        {CrudVaccin cr = new CrudVaccin();
        Vaccin e = new Vaccin();
       System.out.println(table_event.getSelectionModel().getSelectedItem().getId());
        e.setId(table_event.getSelectionModel().getSelectedItem().getId());
        e.setNomVaccin(tf_nom.getText());
        e.setAge(tf_age.getText());
        e.setMaladies(tf_maladies.getText());
        e.setDescription(tf_description.getText());
        
        cr.modifierVaccin(e);
        ObservableList<Vaccin> tmp = FXCollections.observableArrayList();
        tmp.addAll(cr.listofvaccins());
        table_event.setItems(tmp);
        }
    }
        
    

    @FXML
    private void deleteVaccin(ActionEvent event) {
        
        CrudVaccin cr = new CrudVaccin();
              
            cr.supprimerVaccin(table_event.getSelectionModel().getSelectedItem().getId());
            
            ObservableList<Vaccin> tmp = FXCollections.observableArrayList();
            tmp.addAll(cr.listofvaccins());
            table_event.setItems(tmp);
            tf_nom.clear();
            tf_age.clear();
            tf_maladies.clear();
            tf_description.clear();
            
            
    }

    @FXML
    private void addVaccin(ActionEvent event) {
    
        
        String nom, age, mld, desc;
        
        
        CrudVaccin crvaccin = new CrudVaccin();
        
         if(tf_nom.getText().equalsIgnoreCase("")||tf_age.getText().equalsIgnoreCase("")||tf_maladies.getText().equalsIgnoreCase("")||tf_description.getText().equalsIgnoreCase(""))
       {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur lors de l'ajout d'un enfant");
         alert.setContentText("Vérifiez vos infomartions!");
         alert.showAndWait();
       }
         
                 
       else
       { nom=tf_nom.getText();
         age=tf_age.getText();
         mld = tf_maladies.getText();  
         desc = tf_description.getText();
       
        Vaccin  en = new Vaccin();
        en.setNomVaccin(nom);
        en.setAge(age);
        en.setMaladies(mld);
        en.setDescription(desc);
        crvaccin.ajouterVaccin(en);
         
        
        
        ObservableList<Vaccin> tmp = FXCollections.observableArrayList();
        tmp.addAll(crvaccin.listofvaccins());
        table_event.setItems(tmp);
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succés");
        alert.setHeaderText("Vaccin ajouter avec succés");
        alert.showAndWait();
       }
       
        
    }    

    @FXML
    private void ProfileEdit(MouseEvent event) {
    }

    @FXML
    private void Lougout(MouseEvent event) {
    }
    
}
