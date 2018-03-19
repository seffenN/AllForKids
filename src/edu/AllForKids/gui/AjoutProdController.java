/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class AjoutProdController implements Initializable {
     File pfile;
     int file=0;
     String path;

    @FXML
    private Button bttajout;
    @FXML
    private TextField txtproduit;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtquantite;
    @FXML
    private CheckBox txtdisponible;
    @FXML
    private ComboBox<String> txtcategorie;
    @FXML
    private Button imagebtt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> categories=FXCollections.observableArrayList("Vétements","Jouets");
         txtcategorie.setItems(categories);
         txtcategorie.setValue("Vétements");
         
        // TODO
    }    

    @FXML
    private void AjoutProduit(ActionEvent event) throws SQLException {
        CrudStore myTool=new CrudStore();
        Produits p=new Produits();
        p.setNom(txtproduit.getText());
        float prix=Float.parseFloat(txtprix.getText());
        int quantite=Integer.parseInt(txtquantite.getText());
        p.setQuantite(quantite);
        p.setPrix(prix);
        p.setCategorie(txtcategorie.getValue());
        p.setEtat("En attente");
        p.setDiponibilité(txtdisponible.isSelected());
        p.setImage(path);
        myTool.insererProduit(p);
        JOptionPane.showMessageDialog(null,"Produit ajouté");
        
    }

    @FXML
    private void ImageChoose(ActionEvent event) throws MalformedURLException {
         FileChooser fc = new FileChooser();
         fc.setTitle("Open Resource File");
         fc.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("JPG", "*.jpg"),
         new FileChooser.ExtensionFilter("PNG", "*.png")
         );
         File selectedFile=fc.showOpenDialog(null);
         if(selectedFile!=null)
         {
              String p=selectedFile.getPath();
              path=selectedFile.getAbsolutePath();
              
              
             
         }
    
        
    }
    
}
