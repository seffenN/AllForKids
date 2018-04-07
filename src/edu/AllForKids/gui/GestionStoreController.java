/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import static edu.AllForKids.gui.AfficherProduitsController.p;
import static edu.AllForKids.gui.AjoutProdController.copier;
import static edu.AllForKids.gui.ModifierprodController.copier;
import edu.AllForKids.services.CrudStore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class GestionStoreController implements Initializable {

    @FXML
    private ImageView AfficheIMG;
    @FXML
    private TextField txtproduit;
    @FXML
    private TextField FilePath;
    @FXML
    private Button imagebtt;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtquantite;
    @FXML
    private ComboBox<String> txtcategorie;
    @FXML
    private CheckBox txtdisponible;
    @FXML
    private Button bttajout;
    @FXML
    private Button supprimer;
    @FXML
    private Button reset;
    @FXML
    private TextField Chercher;
    @FXML
    private Button btnchercher;

    @FXML
    private Button filtre;

    @FXML
    private WebView WebView;
    String path1 = "";
    final Stage stage = new Stage();
    private FileChooser fileChooser = new FileChooser();
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

    CrudStore produitService = new CrudStore();
    // public static Produits p = null;
    List<Produits> arrayList;

    @FXML
    private TableColumn<Produits, String> cat;

    @FXML
    private TableColumn<Produits, Integer> id;
    @FXML
    private Button modifierprod;
    @FXML
    private CheckBox Cultiver;
    @FXML
    private CheckBox Distraire;
    @FXML
    private CheckBox Cinema;
    @FXML
    private CheckBox Rando;
    @FXML
    private CheckBox autre;
   public static  Produits p;
    int idprod=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> categories = FXCollections.observableArrayList("Vétements", "Jouets");
        txtcategorie.setItems(categories);
        txtcategorie.setValue("Vétements");
        arrayList = new ArrayList<>();
        arrayList = produitService.selectProduit();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        UpdateList();
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Produits SelectedEvenet = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                p = SelectedEvenet;

            }
        });
    }

    public void UpdateList() {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomProd.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        Quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        cat.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        Dispo.setCellValueFactory(new PropertyValueFactory<>("Disponible"));

        // Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

    @FXML
    private void AjoutProduit(ActionEvent event) throws IOException, SQLException {
        int quantite = Integer.parseInt(txtquantite.getText());
        String nom = txtproduit.getText();

       Produits p = new Produits();
        p.setNom(txtproduit.getText());
        float prix = Float.parseFloat(txtprix.getText());
        p.setQuantite(quantite);
        p.setPrix(prix);
        p.setCategorie(txtcategorie.getValue());
        p.setEtat("En attente");
        p.setDiponibilité(txtdisponible.isSelected());
        //copier(pfile, pDir);
        p.setImage(path1);
        produitService.insererProduit(p);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setContentText("Produit Ajouté !");
        alert1.showAndWait();
        arrayList = produitService.selectProduit();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        /* Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/
    }

    @FXML
    private void ImageChoose(ActionEvent event) {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            if (file.getName().contains(".jpg")) {
                //System.out.println("khtart fichier");

                System.out.println(UUID.randomUUID().toString().concat(".jpg"));

                String uuid = UUID.randomUUID().toString().concat(".jpg");

                path1 = file.getAbsolutePath();
                System.out.println(path1);
                //String pathxx1 = file.toURI().toURL().toString();

                //Image image = new Image(pathxx1);
                File file1 = new File(path1);

                // Image image1 = new Image(file1.toURI().toString());
                //imagebtt.setImage(image1);
                File source = new File(path1);

                File dest = new File("C:\\wamp64\\www\\PI4\\web\\bundles\\images\\" + uuid);
                path1 = uuid;
                copier(source, dest);
            }

        }
    }

    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            p = table.getItems().get(table.getSelectionModel().getSelectedIndex());

            //id.setText(String.valueOf(p.getId()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation suppression");
            alert.setHeaderText("Confirmer ");
            alert.setContentText("Vous-etes sur de supprimer l'evenement " + p.getNom() + " ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //((Node)event.getSource()).getScene().getWindow().hide();
                produitService.DeleteProduit(p);
                arrayList = produitService.selectProduit();
                ObservableList observableList = FXCollections.observableArrayList(arrayList);
                table.setItems(observableList);
                /*  Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Suppression Réussite réussite !");
           alert1.showAndWait();*/

            } else {
                JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit");
            }
        }

    }

    @FXML
    private void ModifierProduit(ActionEvent event) throws Exception {
            
       if (table.getSelectionModel().getSelectedItem()!=null)
            {
                 p=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                //stem.out.println(p.getId());
          
          /*  Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmer la modification");
            confirmation.setHeaderText("Confirmer");
            confirmation.setContentText("Vous-vous modifier l'evenement " +p.getNom()+ " ?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                if ("Enregistrer".equals(modifierprod.getText())) {
                   
                    p=new Produits();
       // int id=Integer.parseInt(txtid.getText());
      
                    //System.out.println(p.getId());
        p.setNom(txtproduit.getText());
        float prix=Float.parseFloat(txtprix.getText());
           int quantite = Integer.parseInt(txtquantite.getText());
        p.setQuantite(quantite);
       // copier(pfile, pDir);
        p.setImage(path1);
        //p.setEtat(txtetat.getText());
        p.setPrix(prix);
        p.setCategorie(txtcategorie.getValue());
        p.setDiponibilité(txtdisponible.isSelected());
          p.setId(p.getId());
                    System.out.println(p.getId());
        produitService.ModifierProduit(p);
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
           alert1.setTitle("I have a great message for you!");
           alert1.setHeaderText(null);
           alert1.setContentText("Modification réussite !");
            arrayList = produitService.selectProduit();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        modifierprod.setText("Modifier");
                }
            }
             if ("Modifier".equals(modifierprod.getText())) {
                afficherProdAmodifier();
                //AddEvent.setVisible(false);
               modifierprod.setText("Enregistrer");
            }
                 
                
        }*/
    //}
            
            /* System.out.println(p.getId());
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = new Stage();
            CrudStore myTool = new CrudStore();
            Produits p = new Produits();
            //int id=Integer.parseInt(txtid.getText());
            int quantite = Integer.parseInt(txtquantite.getText());
            p.setNom(txtproduit.getText());
            float prix = Float.parseFloat(txtprix.getText());
            p.setQuantite(quantite);

            p.setImage(path1);
            //p.setEtat(txtetat.getText());
            p.setPrix(prix);
            p.setCategorie(txtcategorie.getValue());
            p.setDiponibilité(txtdisponible.isSelected());
            myTool.ModifierProduit(p);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("I have a great message for you!");
            alert1.setHeaderText(null);
            alert1.setContentText("Modification réussite !");

        }else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit");*/
        }
    }
    
        
    
         public void afficherProdAmodifier(){
   
            txtproduit.setText(p.getNom());
            txtprix.setText("" + p.getPrix());
            txtquantite.setText(""+p.getQuantite());
            txtdisponible.setText(""+p.isDisponible());
            txtcategorie.setValue(p.getCategorie());
            FilePath.setText(p.getImage());
}
    

    @FXML
    private void LieuChanged2(ActionEvent event) {
    }

    @FXML
    private void vider(ActionEvent event) {
    }

    @FXML
    private void Miseajour(ActionEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void Filtre(ActionEvent event) {
    }

    @FXML
    private void SlectTable(MouseEvent event) {
    }

}

    
