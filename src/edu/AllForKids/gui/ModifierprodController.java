/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import com.sun.xml.internal.txw2.TxwException;
import edu.AllForKids.entities.Produits;
import static edu.AllForKids.gui.AjoutProdController.copier;
import edu.AllForKids.services.CrudStore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
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
    private CheckBox txtdispo;
    @FXML
    private TextField txtetat;
    @FXML
    private ComboBox<String> txtcategorie;
    @FXML
    private TextField txtimage;
    @FXML
    private Button ModifierAction;
    int c;
    String lien;
    File pfile;
     int file=0;
     String path;
       File pDir;
    @FXML
    private Button bttimage;
    @FXML
    private Label label_erreur;
    //int c = Integer.valueOf(AfficherProduitsController.p.getId());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> categories = FXCollections.observableArrayList("Vétements", "Jouets");
        txtcategorie.setItems(categories);
       // txtcategorie.setValue("Vétements");
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
         pDir = new File("src/Media/" + c + ".jpg");
          lien= "Media/" + c + ".jpg";
     
        txtid.setText(String.valueOf(AfficherProduitsController.p.getId()));
        txtnom.setText(AfficherProduitsController.p.getNom());
        String prix=""+AfficherProduitsController.p.getPrix();
        txtprix.setText(prix);
        String quantite=""+AfficherProduitsController.p.getQuantite();
       txtquantite.setText(quantite);
        txtimage.setText(AfficherProduitsController.p.getImage());
        txtcategorie.setValue(AfficherProduitsController.p.getCategorie());
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
              int quantite = Integer.parseInt(txtquantite.getText());
        String nom =txtnom.getText();
        label_erreur.setText("");
        if ((txtnom.getText().equals("")) && (txtprix.getText().compareTo("") == 0) && (txtquantite.getText().compareTo("") == 0)) {
            label_erreur.setText("un ou plusieurs champs sont vides");

            } else 

                if (quantite <0) {
                    label_erreur.setText("nega");
                } else {
          
        Produits p=new Produits();
        int id=Integer.parseInt(txtid.getText());
       p.setId(id);
        p.setNom(txtnom.getText());
        float prix=Float.parseFloat(txtprix.getText());
        p.setQuantite(quantite);
        copier(pfile, pDir);
        p.setImage(lien);
        p.setEtat(txtetat.getText());
        p.setPrix(prix);
        p.setCategorie(txtcategorie.getValue());
        p.setDiponibilité(txtdispo.isSelected());
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
                }
        
    }else {
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           }

    }

    @FXML
    private void Choose(ActionEvent event) {
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
            Parent root = FXMLLoader.load(getClass().getResource("ModifierProd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
      
    }

    
    
}
