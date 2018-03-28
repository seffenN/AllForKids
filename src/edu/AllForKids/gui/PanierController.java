/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import com.jfoenix.controls.JFXButton;
import edu.AllForKids.entities.Produits;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class PanierController implements Initializable {

    @FXML
    private Label nomprod;
    @FXML
    private Label prix;
    @FXML
    private Button bttAjout;
     private List<Produits> lst_P ;
      public static List<ArrayList> panier;
      public static int nb_produits_panier;
      Produits p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableMap<Produits,Integer> prod;
         prix.setText(String.valueOf(ListeProduitsController.Prix));
        nomprod.setText(String.valueOf(ListeProduitsController.nomProd));
        
       
         
        // TODO
    }    

   
    
     
      public int MAJ_Nbr_Produits(Produits p){
        int  pos=-1;
        for(int i=0;i<panier.size();i++){
              String id=""+p.getId();
              //
                 if(panier.get(i).get(0).toString().equalsIgnoreCase(id)){
                    pos=i;
                }
                 
            
        }
        return pos;
          
    }
      public void afficher(){
          for(int i=0;i<panier.size();i++){
              System.out.println(panier.get(i));
          }
          
      }

    @FXML
    private void paniiiier(ActionEvent event) {
           panier=new ArrayList<>();
                 nb_produits_panier= panier.size();
                 
                 
                 
                }
                }
                 // System.out.println(panier.listIterator());
            
            
        
    
    

        
  
    
    
    

    

        
    
    

