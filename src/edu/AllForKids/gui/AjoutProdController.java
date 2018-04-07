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
import java.util.UUID;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    int file = 0;
    String path;
    File pDir;
    private FileChooser fileChooser = new FileChooser();

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
    @FXML
    private Button bttAnnuler;
    @FXML
    private Label label_erreur;
    String path1 = "";
    final Stage stage = new Stage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> categories = FXCollections.observableArrayList("Vétements", "Jouets");
        txtcategorie.setItems(categories);
        txtcategorie.setValue("Vétements");
      /*  c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/" + c + ".jpg");
        lien = "Media/" + c + ".jpg";*/

        // TODO
    }

    @FXML
    private void AjoutProduit(ActionEvent event) throws SQLException, IOException {
        int quantite = Integer.parseInt(txtquantite.getText());
        String nom = txtproduit.getText();
        label_erreur.setText("");
        if ((txtproduit.getText().equals("")) && (txtprix.getText().compareTo("") == 0) && (txtquantite.getText().compareTo("") == 0)) {
            label_erreur.setText("un ou plusieurs champs sont vides");

        } else if (quantite < 0) {
            label_erreur.setText("nega");
        } else {
            CrudStore myTool = new CrudStore();
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
            myTool.insererProduit(p);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Produit Ajouté !");
            alert1.showAndWait();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }

    @FXML
    private void ImageChoose(ActionEvent event) throws MalformedURLException {
        /* FileChooser fc = new FileChooser();
            fc.setTitle("Open Resource File");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            pfile = fc.showOpenDialog(null);
            if (pfile != null) {
                //String p = pfile.getPath();
                 pfile.getAbsolutePath();

            }*/
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            if (file.getName().contains(".jpg")) {
                System.out.println("khtart fichier");

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
                path1=uuid;
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
    private void Annuler(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutProd.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
