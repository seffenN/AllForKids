/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class ListeProduitsController implements Initializable {

    @FXML
    private ListView<Produits> listprod;
    public static String nomProd;
    public static int Prix;
    public static int quantite;
    public static String cat;
    public static String etat;
    public static String image;
    public static boolean dispo;
    public static int id;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Produits> oblist = FXCollections.observableArrayList();
        CrudStore prod = new CrudStore();
        //List<Produits> panier;
        List<Produits> listedem = prod.selectProduit();
        oblist.addAll(listedem);
        listprod.getItems().addAll(oblist);
        listprod.setCellFactory(new Callback<ListView<Produits>, ListCell<Produits>>() {

            @Override
            public ListCell<Produits> call(ListView<Produits> arg0) {
                return new ListCell<Produits>() {

                    @Override
                    protected void updateItem(Produits item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {

                            GridPane gridPane = new GridPane();
                            Label NomProduit = new Label();
                            Label Prix = new Label();
                            ImageView ImageProd = new ImageView();
                            AnchorPane content = new AnchorPane();
                            ImageProd.setFitWidth(75);
                            ImageProd.setPreserveRatio(true);
                            GridPane.setConstraints(ImageProd, 0, 0, 1, 3);
                            GridPane.setValignment(ImageProd, VPos.TOP);
                            // 
                            NomProduit.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
                            GridPane.setConstraints(NomProduit, 1, 0);
                            //GridPane.setConstraints(AjoutAuPanier, 3, 0);
                            Prix.setStyle("-fx-opacity: 0.75;");
                            GridPane.setConstraints(Prix, 1, 1);
                            GridPane.setColumnSpan(Prix, Integer.MAX_VALUE);
                            gridPane.getChildren().setAll(ImageProd, NomProduit, Prix);
                            AnchorPane.setTopAnchor(gridPane, 0d);
                            AnchorPane.setLeftAnchor(gridPane, 0d);
                            AnchorPane.setBottomAnchor(gridPane, 0d);
                            AnchorPane.setRightAnchor(gridPane, 0d);
                            content.getChildren().add(gridPane);
                            NomProduit.setText(item.getNom());
                            String prix = "" + item.getPrix() + "DT";
                            Prix.setText(prix);
                            //AjoutAuPanier.setText("Ajouter au panier"); 
                            Image i = new Image(item.getImage());
                            ImageProd.setImage(i);
                            //descriptionLabel.setText(String.format(", %d places, %d portes", item.getSeats(), item.getDoors())); 
                            //colorRect.setFill(item.getColor()); 
                            setText(null);
                            setGraphic(content);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                        }
                    }

                };
            }

        });
         listprod.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produits>() {
            @Override
            public void changed(ObservableValue<? extends Produits> observable, Produits oldValue, Produits newValue) {
                 try {
                      id=newValue.getId();
               nomProd=newValue.getNom();
                Prix=(int) newValue.getPrix();
                quantite=newValue.getQuantite();
                cat=newValue.getCategorie();
                etat=newValue.getEtat();
                image=newValue.getImage();
                dispo=newValue.isDisponible();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Panier.fxml"));
                    Parent root;
              
                    root = (Parent) fxmlLoader.load();
               
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                     } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                }
                
	}
         
                
            
         });
         }
    
        // TODO
    }


