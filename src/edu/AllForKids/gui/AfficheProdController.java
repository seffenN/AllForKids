/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import static edu.AllForKids.gui.LoginController.nb_produits_panier;

import edu.AllForKids.services.CrudStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeMap;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
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
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class AfficheProdController implements Initializable {

    CrudStore prodService = new CrudStore();
    List<Produits> arrayList;

    @FXML
    private ScrollPane LisTProd;
    // public static Map<Produits,Integer> paniers;
    //int nbProdPanier=0;
    // public static int nbrArtcile;
    public static int id;
    public static int stock;
    // public static Produits p;
    // Produits p=null;
// public static List<ArrayList> panier = new ArrayList<>();
    @FXML
    private Circle nbPanier;
    @FXML
    private Label nbpanier;
    @FXML
    private ImageView cadd;
    @FXML
    private Button bttVoir;
    @FXML
    private TextField cat;

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
                Label idHidden = new Label("" + p.getId());
                idHidden.setVisible(false);
                HBox Hbtn = new HBox(10);
                Button button = new Button("Ajout au Panier");
                button.setStyle("-fx-background-color:  #2471A3");
                button.setTextFill(Color.web("#FBFCFC"));
                // button.setAccessibleText("" + E.getId_even());
                button.onActionProperty().set((event) -> {

                    // try {
                    if (p.getQuantite() < 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur ");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Rupture du stock!");
                        alert.showAndWait();
                    } else {
                          List<Integer> lst_qte = new ArrayList<>();
                        for (int j = 0; j < p.getQuantite(); j++) {
                            lst_qte.add(j + 1);
                        }
                        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, lst_qte);
                        dialog.setTitle("Choice Dialog");
                        dialog.setHeaderText("Look, a Choice Dialog");
                        dialog.setContentText("Choisissez nbre de produit:");
                         Optional<Integer> result = dialog.showAndWait();
                         if(result.isPresent()){
                             int qte=result.get();
                             System.out.println(qte);
                              ArrayList<Object> nb_pdt = new ArrayList<>();
                        nb_pdt.add(0,p.getId());
                        nb_pdt.add(1, qte);
                       // System.out.println(nb_pdt);
                        //nb_pdt.add(1, 1);
                       // System.out.println(nb_pdt);
                        LoginController.panier.add(nb_pdt);
                        LoginController.nb_produits_panier = nb_produits_panier + 1;
                        nbpanier.setText("" + nb_produits_panier);
                        id = p.getId();
                             
                         }
                        // try {
                       
                       
                        // Stage stage = new Stage

                        /* Parent roots = FXMLLoader.load(getClass().getResource("Quantite.fxml"));
                               Scene scene = new Scene(roots);
                               stage.setScene(scene);
                               stage.show();
                          /* } catch (IOException ex) {
                               Logger.getLogger(AfficheProdController.class.getName()).log(Level.SEVERE, null, ex);
                           }*/
                    }

                    bttVoir.setVisible(true);
                    /* } catch (IOException ex) {
                        Logger.getLogger(AfficheProdController.class.getName()).log(Level.SEVERE, null, ex);
                    }*/

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
    private void consulterPanier(MouseEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {

    }

}
