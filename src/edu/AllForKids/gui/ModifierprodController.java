/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import com.sun.xml.internal.txw2.TxwException;
import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class ModifierprodController implements Initializable {

    @FXML
    private TextField txtquantite;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtdispo;
    @FXML
    private TextField txtetat;
    @FXML
    private TextField txtcategorie;
    @FXML
    private TextField txtimage;
    @FXML
    private Button ModifierAction;
    //int c = Integer.valueOf(AfficherProduitsController.p.getId());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        txtid.setText(String.valueOf(AfficherProduitsController.p.getId()));
        txtnom.setText(AfficherProduitsController.p.getNom());
        String prix=""+AfficherProduitsController.p.getPrix();
        txtprix.setText(prix);
        String quantite=""+AfficherProduitsController.p.getQuantite();
       txtquantite.setText(quantite);
        txtimage.setText(AfficherProduitsController.p.getImage());
        txtcategorie.setText(AfficherProduitsController.p.getCategorie());
       String dispo=""+AfficherProduitsController.p.isDisponible();
        txtdispo.setText(dispo);
       
        txtetat.setText(AfficherProduitsController.p.getEtat());
      
        //Image i=new Image("http://localhost/"+AfficherProduitsController.p.getImage());
       
        
        // TODO
    }    

    @FXML
    private void Modifer(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage=new Stage();
             CrudStore myTool=new CrudStore();
             
          
        Produits p=new Produits();
        int id=Integer.parseInt(txtid.getText());
       p.setId(id);
        p.setNom(txtnom.getText());
        float prix=Float.parseFloat(txtprix.getText());
        int quantite=Integer.parseInt(txtquantite.getText());
        p.setQuantite(quantite);
        p.setImage(txtimage.getText());
        p.setEtat(txtetat.getText());
        p.setPrix(prix);
        p.setCategorie(txtcategorie.getText());
        
        p.setDiponibilité(txtdispo.isEditable());
        myTool.ModifierProduit(p);
         Alert alert1 = new Alert(AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Modification réussite !");
           alert1.showAndWait();
      Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }

    }

    
    
}
