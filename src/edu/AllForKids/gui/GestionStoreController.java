/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.AllForKids.entities.Produits;

import static edu.AllForKids.gui.AjoutProdController.copier;

import edu.AllForKids.services.CrudStore;
import edu.AllForKids.utils.MyConnexion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.image.Image;
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
    public static Produits pr;
    int idprod = 0;
    @FXML
    private Button bttpdf;
    @FXML
    private ImageView imageprod;
    Produits p;
    File file1;
    File source;
    File dest;

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

        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomProd.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        Quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        cat.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        //image.setCellValueFactory(new PropertyValueFactory<>("image"));
        Dispo.setCellValueFactory(new PropertyValueFactory<>("Disponible"));

        // Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

    @FXML
    private void AjoutProduit(ActionEvent event) throws IOException, SQLException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmer l'ajout");
        confirmation.setHeaderText("Confirmer");
        confirmation.setContentText("Voulez-vous ajouter ce Produit ?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.get() == ButtonType.OK) {
            if ((txtproduit.getText().equals("")) && (txtprix.getText().compareTo("") == 0) && (txtquantite.getText().compareTo("") == 0)) {
                System.out.println("ok");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention");
                alert.setContentText("Champs vide !");
                alert.showAndWait();

            } else if (Integer.parseInt(txtquantite.getText()) < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention");
                alert.setContentText("quantité ne peut pas etre negatif !");
                alert.showAndWait();
            } else if (Float.parseFloat(txtprix.getText()) < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention");
                alert.setContentText("Prix ne peut pas etre negatif !");
                alert.showAndWait();
            } else {

                Produits p = new Produits();
                p.setNom(txtproduit.getText());
                String nom = txtproduit.getText();
                float prix = Float.parseFloat(txtprix.getText());
                int quantite = Integer.parseInt(txtquantite.getText());
                p.setQuantite(quantite);
                p.setPrix(prix);
                p.setCategorie(txtcategorie.getValue());
                p.setEtat("En attente");
                p.setDiponibilité(txtdisponible.isSelected());
                copier(source, dest);
                //copier(pfile, pDir);
                p.setImage(path1);
                File file = new File("C:\\wamp64\\www\\PI4\\web\\bundles\\images\\" + p.getImage());
                Image i = new Image(file.toURI().toString());
                imageprod.setImage(i);
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
        } else {
            System.out.println("erreur");
        }

    }

    @FXML
    private void ImageChoose(ActionEvent event) {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            if (file.getName().contains(".jpg")) {
                System.out.println(UUID.randomUUID().toString().concat(".jpg"));
                String uuid = UUID.randomUUID().toString().concat(".jpg");
                path1 = file.getAbsolutePath();
                System.out.println(path1);
                file1 = new File(path1);
                source = new File(path1);
                dest = new File("C:\\wamp64\\www\\PI4\\web\\bundles\\images\\" + uuid);
                path1 = uuid;
                Image i = new Image(file.toURI().toString());
                imageprod.setImage(i);

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
            alert.setContentText("Vous-etes sur de supprimer le produit " + p.getNom() + " ?");

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

        if (table.getSelectionModel().getSelectedItem() != null) {

            p = table.getItems().get(table.getSelectionModel().getSelectedIndex());
            //stem.out.println(p.getId());
            int id = p.getId();
            System.out.println(id);
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmer la modification");
            confirmation.setHeaderText("Confirmer");
            confirmation.setContentText("Vous-vous modifier l'evenement " + p.getNom() + " ?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                if ("Enregistrer".equals(modifierprod.getText())) {
                    if (Integer.parseInt(txtquantite.getText()) < 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("quantité ne peut pas etre negatif !");
                        alert.showAndWait();
                    } else if (Float.parseFloat(txtprix.getText()) < 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Prix ne peut pas etre negatif !");
                        alert.showAndWait();

                    } else {

                        pr = new Produits();
                        float prix = Float.parseFloat(txtprix.getText());
                        int quantite = Integer.parseInt(txtquantite.getText());
                        pr.setNom(txtproduit.getText());
                        pr.setQuantite(quantite);
                        // copier(pfile, pDir);
                        copier(source, dest);
                        pr.setImage(path1);
                        File file = new File("C:\\wamp64\\www\\PI4\\web\\bundles\\images\\" + p.getImage());
                        System.out.println(p.getImage());
                        Image i = new Image(file.toURI().toString());
                        imageprod.setImage(i);
                        //p.setEtat(txtetat.getText());
                        pr.setPrix(prix);
                        pr.setCategorie(txtcategorie.getValue());
                        pr.setDiponibilité(txtdisponible.isSelected());
                        pr.setEtat("en attente");
                        pr.setId(idprod);
                        System.out.println(p.getId());

                        produitService.ModifierProduits(pr, p.getId());

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
            }

            if ("Modifier".equals(modifierprod.getText())) {
                afficherProdAmodifier();
                //AddEvent.setVisible(false);
                modifierprod.setText("Enregistrer");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit");
        }
    }

    public void afficherProdAmodifier() {

        txtproduit.setText(p.getNom());
        txtprix.setText("" + p.getPrix());
        txtquantite.setText("" + p.getQuantite());
        txtdisponible.setText("" + p.isDisponible());
        txtcategorie.setValue(p.getCategorie());
        File file = new File("C:\\wamp64\\www\\PI4\\web\\bundles\\images\\" + p.getImage());

        Image i = new Image(file.toURI().toString());
        imageprod.setImage(i);
        //FilePath.setText(p.getImage());
    }

    @FXML
    private void LieuChanged2(ActionEvent event) {
    }

    @FXML
    private void vider(ActionEvent event) throws IOException {
        p = null;
        txtproduit.setText("");
        txtprix.setText("");
//        FilePath.setText("");
        txtquantite.setText("");
        txtdisponible.setText("");

        txtcategorie.setPromptText("Categorie");
        imageprod.imageProperty().set(null);
        modifierprod.setText("Modifier");
        bttajout.setVisible(true);
    }

    @FXML
    private void pdf(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
        String requete = "SELECT * FROM vehicules";

        Connection cnx = MyConnexion.getInstance().getConnection();
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("Liste des produits.pdf"));

        doc.open();
        PdfPTable table = new PdfPTable(4);

        PdfPCell table_cell;

        table.addCell("Nom Produit");

        table.addCell("Prix");

        table.addCell("Stock");

        table.addCell("Categorie");

    }

    public boolean validerNomProd() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txtproduit.getText());
        if (m.find() && m.group().equals(txtproduit.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation du nom du produit");
            alert.setHeaderText(null);
            alert.setContentText("Merci d'entrer une nom de produit valide");
            alert.showAndWait();
            return false;
        }
    }

}
