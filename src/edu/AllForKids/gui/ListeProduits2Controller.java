/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;

import edu.AllForKids.services.CrudStore;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Enumeration;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class ListeProduits2Controller implements Initializable {

    @FXML
    private TableView<Produits> table;
    @FXML
    private ImageView image;
    @FXML
    private TableColumn<Produits, String> nomProd;
    @FXML
    private TableColumn<Produits, Float> prix;
    @FXML
    private TableColumn<Produits, String> type;
    List<Produits> arrayList;
    CrudStore produitService = new CrudStore();
   public static Produits p;
   
    public static List<ArrayList> panier = new ArrayList<>();
    @FXML
    private Button pann;
    @FXML
    private Button caddie;
    @FXML
    private Label nbpanier;
    int nbProdPanier=0;
    @FXML
    private Circle nbPanier;
    @FXML
    private ImageView sac;
    @FXML
    private Button ajoutaupan;
    @FXML
    private ImageView cadd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arrayList = new ArrayList<>();
        arrayList = produitService.selectProduit();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProd.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        //Quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        // image.setCellValueFactory(new PropertyValueFactory<>("image"));
        //Dispo.setCellValueFactory(new PropertyValueFactory<>("Disponible"));
        type.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        //Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       

    }

    @FXML
    private void imageAffiche(MouseEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            
            File file = new File("C:\\wamp64\\www\\PI4\\web\\bundles\\images\\" + table.getItems().get(table.getSelectionModel().getFocusedIndex()).getImage());
       
         
            Image i = new Image(file.toURI().toString());
            image.setImage(i);
        }
    }

    @FXML
    private void AjoutPanier(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            p = table.getItems().get(table.getSelectionModel().getSelectedIndex());
            ArrayList<Object> nb_pdt = new ArrayList<>();
            nb_pdt.add(0, p.getId());
            System.out.println(nb_pdt);
            nb_pdt.add(1, 1);
            System.out.println(nb_pdt);
            panier.add(nb_pdt);
             nbProdPanier=nbProdPanier+1;
                   nbpanier.setText(""+nbProdPanier);
            Stage stage = new Stage();
         FXMLLoader loader=new FXMLLoader(getClass().getResource("Quantite.fxml"));
        Parent root=loader.load();
        //QuantiteController dpc=loader.getController();
         TranslateTransition t=new TranslateTransition();
        t.setDuration(Duration.seconds(4));
        t.setNode(sac);
        t.setToX(524);
        t.setToY(19);
        t.play();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            

        }
    }

    @FXML
    private void consulter(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
