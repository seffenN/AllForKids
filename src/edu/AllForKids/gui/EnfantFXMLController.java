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
import edu.AllForKids.entities.Enfant;

import edu.AllForKids.services.CrudEnfant;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class EnfantFXMLController implements Initializable {
      @FXML
    private DatePicker date_naissance;
     @FXML
    private JFXTextField tf_prenom;
     @FXML
    private TableView<Enfant> table_event;

    @FXML
    private TableColumn<Enfant, String> c_nom;

    @FXML
    private TableColumn<Enfant, String> c_date;
    @FXML
    private TableColumn<Enfant, String> c_prenom;
    
    @FXML
    private JFXButton bt_modfiier;

    @FXML
    private JFXButton bt_supprimer;

    @FXML
    private JFXButton bt_ajouter;

    @FXML
    private Button bt_retour;
    ObservableList<Enfant> data = FXCollections.observableArrayList();
      ObservableList<Enfant> pdftoextract = FXCollections.observableArrayList();
@FXML
    private JFXDrawer drawer;
    
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXTextField tf_nom_enfant;
   

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
            System.out.println("ti heh");
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
//            try {
//            VBox box = FXMLLoader.load(getClass().getResource("/edu/AllForKids/GUI/EspaceSanteMenu.fxml"));
//             drawer.setSidePane(box);
//             
//        
//        
//        } catch (IOException ex) {
//           // Logger.getLogger(DrawerMenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
           
           
           
           
           
            CrudEnfant enfantService = new CrudEnfant();
            c_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            c_date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
            c_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            data.addAll(enfantService.listofkids());
            System.out.println(data);
            table_event.setItems(data);
            table_event.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
                tf_nom_enfant.clear();
                tf_prenom.clear();
               
                tf_nom_enfant.setText(table_event.getSelectionModel().getSelectedItem().getNom());
                tf_prenom.setText(table_event.getSelectionModel().getSelectedItem().getPrenom());
                date_naissance.setPromptText(table_event.getSelectionModel().getSelectedItem().getDateNaissance());
                
                 
                 
                 
            });
    }    
  @FXML
    void retourAction(ActionEvent event) throws IOException {
        /* Parent root = FXMLLoader.load(getClass().getResource("/edu/AllForKids/GUI/vaccins.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
     
         Scene scene = new Scene(root);
 

        stage.setScene(scene);
    
        stage.setFullScreen(false);
        stage.resizableProperty().set(false);
        stage.show();*/
    }
  
    @FXML
    void addEnfant(ActionEvent event) {
             String nom, prenom;
        String dnaissance;
        
        CrudEnfant crenfant = new CrudEnfant();
        
         if(tf_nom_enfant.getText().equalsIgnoreCase("")||tf_prenom.getText().equalsIgnoreCase("")||date_naissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equalsIgnoreCase("")||date_naissance.getValue()==null)
       {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur lors de l'ajout d'un enfant");
         alert.setContentText("Vérifiez vos infomartions!");
         alert.showAndWait();
       }
         else if(date_naissance.getValue()==null)
         {
               Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur");
         alert.setContentText("la date ne doit pas être null!");
         alert.showAndWait();
         }
         
         
       else
       { nom=tf_nom_enfant.getText();
      prenom=tf_prenom.getText();
       dnaissance = date_naissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));    
       
        Enfant  en = new Enfant();
        en.setNom(nom);
        en.setDateNaissance(dnaissance);
        en.setPrenom(prenom);
        en.setIdParent(1);
        crenfant.ajouterEnfant(en);
         
        
        
        ObservableList<Enfant> tmp = FXCollections.observableArrayList();
        tmp.addAll(crenfant.listofkids());
        table_event.setItems(tmp);
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succés");
        alert.setHeaderText("Enfant ajouter avec succés");
        alert.showAndWait();
       }
       
       }

    @FXML
    private void updateEnfant(ActionEvent event) {
        
        System.out.println("nom"+tf_nom_enfant.getText()+"prenom"+tf_prenom.getText()+"date"+date_naissance.getPromptText());
        if(tf_nom_enfant.getText().equalsIgnoreCase("")||tf_prenom.getText().equalsIgnoreCase("")||date_naissance.getPromptText().equalsIgnoreCase(""))
       {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur lors de la modification");
         alert.setContentText("Vérifiez vos infomartions!");
         alert.showAndWait();
       }
        
       
        else
        {CrudEnfant cr = new CrudEnfant();
        Enfant e = new Enfant();
       System.out.println(table_event.getSelectionModel().getSelectedItem().getId());
        e.setId(table_event.getSelectionModel().getSelectedItem().getId());
        e.setNom(tf_nom_enfant.getText());
        e.setDateNaissance(date_naissance.getPromptText());
        e.setPrenom(tf_prenom.getText());
        e.setIdParent(table_event.getSelectionModel().getSelectedItem().getIdParent());
        cr.modifierEnfant(e);
        ObservableList<Enfant> tmp = FXCollections.observableArrayList();
        tmp.addAll(cr.listofkids());
        table_event.setItems(tmp);
        }}
    

    @FXML
    private void deleteEnfant(ActionEvent event) {
        
         CrudEnfant cr = new CrudEnfant();
              
            cr.supprimerEnfant(table_event.getSelectionModel().getSelectedItem().getId());
            
            ObservableList<Enfant> tmp = FXCollections.observableArrayList();
            tmp.addAll(cr.listofkids());
            table_event.setItems(tmp);
            tf_nom_enfant.clear();
            tf_prenom.clear();
            
            date_naissance.setPromptText("");
    }

    }    
    

