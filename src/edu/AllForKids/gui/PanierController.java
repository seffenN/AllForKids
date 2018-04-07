/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import com.jfoenix.controls.JFXButton;
import edu.AllForKids.entities.Commande;
import edu.AllForKids.entities.Produits;
import edu.AllForKids.entities.ligne_commandes;
import static edu.AllForKids.gui.ListeProduits2Controller.p;
import edu.AllForKids.services.CrudCommande;
import edu.AllForKids.services.CrudStore;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javax.management.Query.value;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class PanierController implements Initializable {

    //private List<Produits> lst_P ;
    // public static List<ArrayList> panier;
    //public static int nb_produits_panier;
    @FXML
    private TableView<Produits> table;
    @FXML
    private TableColumn<Produits, String> nomprod;
    @FXML
    private TableColumn<Produits, Float> prix;
    @FXML
    private TableColumn<Produits, String> type;
    private TableColumn<Produits, Integer> quantite;
    @FXML
    private Button commander;
    List<Commande> lst_com;
    CrudCommande serviceCommande = new CrudCommande();
    CrudStore servP = new CrudStore();
    Produits p;
    private TextField nbrArtcile;
    AfficheProdController qc = new AfficheProdController();
            int id=AfficheProdController.id;
    @FXML
    private Label totalPrix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableMap<Produits,Integer> prod;
        List<Produits> lst_P = new ArrayList<>();
        for (int i = 0; i < AfficheProdController.panier.size(); i++) {
            lst_P.add(servP.findById((Integer) AfficheProdController.panier.get(i).get(0)));
        }
        ObservableList observableList = FXCollections.observableArrayList(lst_P);
        table.setItems(observableList);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomprod.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      //  quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));

       // quantite.setEditable(true);

        // image.setCellValueFactory(new PropertyValueFactory<>("image"));
        //Dispo.setCellValueFactory(new PropertyValueFactory<>("Disponible"));
        type.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        //quantite.setCellValueFactory(TextFieldTableCell.forTableColumn());
        //Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    // TODO
    @FXML
    private void commander(ActionEvent event) throws ParseException, SQLException {
        lst_com = new ArrayList<>();

        ligne_commandes lc = new ligne_commandes();

        Commande cmd = new Commande();
        cmd.setIdClient(2);
        DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        cmd.setDateCommande(date_format.parse(date_format.format(date)));
        serviceCommande.AjouterCommande(cmd);
        lst_com.add(cmd);
        for (int i = 0; i < table.getItems().size(); i++) // if (table.getSelectionModel().getSelectedItem()!=null)
        {
            Commande c = serviceCommande.ConsulterListe_Commandes();
            lc.setId_commande(c.getIdCommande());
            System.out.println(cmd.getIdCommande());
       
            p = table.getItems().get(i);
            //int quantite = p.getQuantite();
            QuantiteController qc = new QuantiteController();
            
            System.out.println(id);
            serviceCommande.DecrementerStock(p.getId(),qc.qt);
            lc.setId_produit(p.getId());
            lc.setPrix_commande(p.getPrix());
            lc.setQuantite(qc.qt);
            serviceCommande.AjouterLigne_Commande(lc);
            // }

        }
    }
}

// System.out.println(panier.listIterator());

