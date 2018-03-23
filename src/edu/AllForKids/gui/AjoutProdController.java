/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
       File pDir;

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
     int c;
     String lien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> categories=FXCollections.observableArrayList("Vétements","Jouets");
         txtcategorie.setItems(categories);
         txtcategorie.setValue("Vétements");
          c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
         pDir = new File("src/Media/" + c + ".jpg");
          lien= "Media/" + c + ".jpg";
         
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
        copier(pfile, pDir);
        p.setImage(lien);
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
         pfile=fc.showOpenDialog(null);
         if(pfile!=null)
         {
              String p=pfile.getPath();
              path=pfile.getAbsolutePath();
              
              
             
         }
    
        
    }
    public static boolean copier(File source, File dest) { 
    try (InputStream sourceFile = new java.io.FileInputStream(source);  
            OutputStream destinationFile = new FileOutputStream(dest)) { 
        // Lecture par segment de 0.5Mo  
        byte buffer[] = new byte[512 * 1024]; 
        int nbLecture; 
        while ((nbLecture = sourceFile.read(buffer)) != -1){ 
            destinationFile.write(buffer, 0, nbLecture); 
        } 
    } catch (IOException e){ 
        e.printStackTrace(); 
        return false; // Erreur 
    } 
    return true; // Résultat OK   
}
     @FXML
    private void Annuler(ActionEvent event) throws IOException {
           ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AjoutProd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
      
    }
}
