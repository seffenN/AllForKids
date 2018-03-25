/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class ListeProduitsController implements Initializable {

    @FXML
    private ListView<Produits> listprod;
    CrudStore produitsServices=new CrudStore();
    ArrayList<Produits> listE=(ArrayList<Produits>) produitsServices.selectProduit();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<Produits> items =FXCollections.observableArrayList(listE) ;
        listprod.setItems(items);
         listprod.setCellFactory(new Callback<ListView<Produits>, ListCell<Produits>>() {
              @Override
              public ListCell<Produits> call(ListView<Produits> param) {
                return new ProduitsAffichage();
                 }
              });
    }
}
