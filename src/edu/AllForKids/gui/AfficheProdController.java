/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;

import edu.AllForKids.services.CrudStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class AfficheProdController implements Initializable {

    CrudStore prodService = new CrudStore();
    List<Produits> arrayList;
    @FXML
    private TextField Titre;
    @FXML
    private Button ChercherTitre;
    @FXML
    private CheckBox Cultiver;
    @FXML
    private CheckBox Distraire;
    @FXML
    private ScrollPane LisTProd;
    int nbProdPanier=0;
    public static int nbrArtcile;
    public static int id=0;
    public static int stock;
   // public static Produits p;
    // Produits p=null;
 public static List<ArrayList> panier = new ArrayList<>();
    @FXML
    private Circle nbPanier;
    @FXML
    private Label nbpanier;
    @FXML
    private ImageView cadd;
    @FXML
    private Button bttVoir;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     bttVoir.setVisible(false);
        try {
            LisTProd.setContent(ListofProducts());
            
            
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AfficheProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Node ListofProducts() throws SQLException {

        VBox root0 = new VBox(10);
        HBox H0 = new HBox(6);
        VBox V2 = new VBox(10);
        // ObservableList<Evenement> OB = FXCollections.observableArrayList();
        //OB = CE.displayAllEvenement();
        // arrayList = new ArrayList<>();
        // arrayList = prodService.selectProduit();
        ObservableList<Produits> OL = FXCollections.observableArrayList();
        OL = prodService.TousLesProduits();

        for (int i = 0; i < OL.size(); i++) {

            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setStyle("-fx-Border-color:  #2471A3");
           // root.setStyle("-fx-background-color: #F0FFFF");
            root.setPadding(new Insets(5, 5, 5, 5));
            try {
              Produits p = OL.get(i);
              
                File file = new File("C:\\wamp64\\www\\PI4\\web\\bundles\\images\\" + p.getImage());
                Image image = new Image(file.toURI().toString());
                System.out.println("ok");
                ImageView IMG = new ImageView(image);
                IMG.fitHeightProperty().set(200);
                IMG.fitWidthProperty().set(200);
                IMG.preserveRatioProperty().set(true);
                Separator sep = new Separator(Orientation.VERTICAL);
                VBox root2 = new VBox(6);
                root2.prefWidthProperty().set(1000);
                root2.prefHeightProperty().set(200);
                root2.setPadding(new Insets(4, 4, 4, 4));
                Label Titre = new Label(p.getNom());
                Titre.setFont(new Font("Arial", 30));
                Titre.setTextFill(Color.web("#17202A"));
                //*******description
                Label Description = new Label("" + p.getPrix());
                Description.backgroundProperty().set(Background.EMPTY);
                Description.setTextFill(Color.web("#17202A"));
                VBox root3 = new VBox(3);
                Separator sep2 = new Separator(Orientation.HORIZONTAL);
                
                Label Tarif = new Label("Prix : " + p.getPrix());
                Tarif.setTextFill(Color.web("#17202A"));
                Label categorie = new Label("categorie :" + p.getCategorie());
                categorie.setTextFill(Color.web("#17202A"));
                  Label idHidden = new Label( ""+p.getId());
                  idHidden.setVisible(false);
                HBox Hbtn = new HBox(10);
                Button button = new Button("Ajout au Panier");
                button.setStyle("-fx-background-color:  #2471A3");
                button.setTextFill(Color.web("#FBFCFC"));
                // button.setAccessibleText("" + E.getId_even());
                button.onActionProperty().set((event) -> {
                    try {
                        ArrayList<Object> nb_pdt = new ArrayList<>();
                        nb_pdt.add(0, p.getId());
                        System.out.println(nb_pdt);
                        nb_pdt.add(1, 1);
                        System.out.println(nb_pdt);
                        panier.add(nb_pdt);
                        nbProdPanier=nbProdPanier+1;
                         nbpanier.setText(""+nbProdPanier);
                         id=p.getId();
                         Stage stage = new Stage();
       Parent roots = FXMLLoader.load(getClass().getResource("Quantite.fxml"));
        Scene scene = new Scene(roots);
        stage.setScene(scene);
        stage.show();
        bttVoir.setVisible(true);
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(AfficheProdController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

                root.getChildren().addAll(IMG, sep, root2, Description);
                root3.getChildren().addAll(categorie, Tarif);
                Hbtn.getChildren().addAll(button);

                root2.getChildren().addAll(Titre, root3, Hbtn);

            } catch (Exception e) {
                System.out.println("exeption du try affiche all event");
            }

            root0.getChildren().add(root);
            root0.setPrefSize(720, 200);
            root0.setPadding(new Insets(0, 0, 0, 0));

        }
        return root0;

    }

    @FXML
    private void voirPanier(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Chercher(ActionEvent event) {
    }

    @FXML
    private void consulterPanier(MouseEvent event) {
    }

}
