/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class AfficherProduitsController implements Initializable {

    @FXML
    private TableView<Produits> table;
    @FXML
    private TableColumn<Produits, String> NomProd;
    @FXML
    private TableColumn<Produits, Float> Prix;
    @FXML
    private TableColumn<Produits, String> image;
    @FXML
    private TableColumn<Produits, Integer> Quantite;
    @FXML
    private TableColumn<Produits, Boolean> Dispo;
    @FXML
    private TableColumn<Produits, String> Etat;
    @FXML
    private TableColumn<Produits, Integer> id;
    @FXML
    private TableColumn<Produits, String> cat;
    CrudStore produitService=new CrudStore();
    public static Produits p=new Produits();
    
    @FXML
    private Button bttModifer;
    List<Produits> arrayList;
    @FXML
    private Button bttajout;
    @FXML
    private Button bttsupp;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arrayList=new ArrayList<>();
        arrayList=produitService.selectProduit();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomProd.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        Quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        Dispo.setCellValueFactory(new PropertyValueFactory<>("Disponible"));
        cat.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       
          }

    @FXML
    private void ModifierProduit(ActionEvent event) throws IOException {
         if (table.getSelectionModel().getSelectedItem()!=null)
            {
            ((Node)event.getSource()).getScene().getWindow().hide();
            p=table.getItems().get(table.getSelectionModel().getSelectedIndex());
            //id.setText(String.valueOf(p.getId()));
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ModifierProd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner un produit");
            }
    }

    @FXML
    private void AjouterProduits(ActionEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AjoutProd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    @FXML
    private void supprimerProduit(ActionEvent event) throws IOException {
         if (table.getSelectionModel().getSelectedItem()!=null)
            {
           
            //id.setText(String.valueOf(p.getId()));
           Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){ 
             ((Node)event.getSource()).getScene().getWindow().hide();
            p=table.getItems().get(table.getSelectionModel().getSelectedIndex());
            produitService.DeleteProduit(p);
            Alert alert1 = new Alert(AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Suppression Réussite réussite !");
           alert1.showAndWait();
           Stage stage=new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
             ((Node)event.getSource()).getScene().getWindow().hide();
        }
            }
            else 
            {
                 JOptionPane.showMessageDialog(null,"Veuillez selectionner un produit");
            }
    }

   
      
     
    }    
    

