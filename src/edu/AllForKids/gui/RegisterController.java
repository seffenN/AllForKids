/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudUser;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfmail;
    @FXML
    private PasswordField tfpass;
    @FXML
    private Button btnconnexion;
    @FXML
    private Text BtnForgetPassword;
    @FXML
    private Button btnsignup;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Pass;
    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private ImageView img;
    @FXML
    private Button Reset;
    @FXML
    private Button Choose;
    private ComboBox<String> role;
     int file = 0;
    File pDir;
    File pfile;
       User CurrentUser;
        Stage primarystage;
String path1="";
    File file1;
    File source;
    File dest;
    private FileChooser fileChooser =new FileChooser();
    @FXML
    private PasswordField RepeterPass;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ComboBox<String> Role;
    @FXML
    private ImageView bttimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       homme.setSelected(true);
        ObservableList Combo = FXCollections.observableArrayList("Parent", "BabySitter", "Pediatre", "Prestataire");
        Role.setItems(Combo);
        
    }    

    @FXML
    private void btnconnexion(ActionEvent event) throws IOException, SQLException {
         tfmail.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white");
        tfpass.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white");

        if (tfmail.getText().equals("") || tfpass.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez inserer votre email et votre mot de pass");
            a.showAndWait();

            if (tfmail.getText().equals("")) {
                tfmail.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
            Alert b = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez inserer votre email ");
            a.showAndWait();
            }
            if (tfpass.getText().equals("")) {
                tfpass.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
            Alert c = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez inserer  et votre mot de pass");
            a.showAndWait();
            }
        } else {
            String email = tfmail.getText();
            String pass = tfpass.getText();
            CrudUser crudutilisateur = new CrudUser();
            User u = crudutilisateur.Authentification(email, pass);
                            System.out.println("user n'est pas null *****"+u);

            if (u == null) {
                System.out.println("iln'yapas dns base");
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Email ou mot de passe incorrect");
                a.showAndWait();
                System.out.println("mailfaut");

            } else {
                CurrentUser = u;
                if(!u.getRoles().equals("Parent")){
                System.out.println("parent");
                
              primarystage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AccueilFrontEnd.fxml"));
        Scene scene = new Scene(root);
        primarystage.setScene(scene);
        primarystage.show();
                }
                else if (!u.getRoles().equals("Admin")){
                 primarystage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AccueilBackEnd.fxml"));
        Scene scene = new Scene(root);
        primarystage.setScene(scene);
        primarystage.show();
                }
                else if (!u.getRoles().equals("Pediatre")){
                                        System.out.println("    ped");

                primarystage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AccueilBackEnd.fxml"));
        Scene scene = new Scene(root);
        primarystage.setScene(scene);
        primarystage.show();
                }
                else if (!u.getRoles().equals("BabySitter")){
                 primarystage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AccueilBackEnd.fxml"));
        Scene scene = new Scene(root);
        primarystage.setScene(scene);
        primarystage.show();
                }

                
            }
        }
    }

    @FXML
    private void Register(ActionEvent event) {
         if (!Nom.getText().equals("")) {
            Nom.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Nom.setPromptText("Inserez votre nom ici !!!");
        }
        if (!Email.getText().equals("")) {
            Email.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Email.setPromptText("Inserez votre mail ici !!!");
        }
        
        if (!Pass.getText().equals("")) {
            Pass.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Pass.setPromptText("Inserez votre password ici !!!");
        }
        if (!RepeterPass.getText().equals("")) {
            RepeterPass.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            RepeterPass.setPromptText("Repeter votre password ici !!!");
            
        }
        
        if (Nom.getText().equals("") || Email.getText().equals("") || Pass.getText().equals("")
                || RepeterPass.getText().equals("") || Role.getSelectionModel().getSelectedIndex() == -1) {

            // (img.getProperties().isEmpty())
            System.out.println("1");
            
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez remplir les champs manquants");
            a.showAndWait();
            if (Nom.getText().equals("")) {
                Nom.setStyle("-fx-border-color : red ;");
            }
            
            if (Role.getSelectionModel().getSelectedIndex() == -1) {
                Role.setStyle("-fx-border-color : red ;");
            }
            if (Email.getText().equals("")) {
                Email.setStyle("-fx-border-color : red ;");
            }
            if (Pass.getText().equals("")) {
                Pass.setStyle("-fx-border-color : red ;");
            }
            if (RepeterPass.getText().equals("")) {
                RepeterPass.setStyle("-fx-border-color : red ;");
            }
            
        } else {
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText().toLowerCase();
            CrudUser crudutilisateur = new CrudUser();
            if (crudutilisateur.FindByEmail(Email.getText()) == null) {
                
                copier(source, dest);
                User u = new User(Nom.getText(), Email.getText(), Pass.getText(),path1,
                        Role.getSelectionModel().getSelectedItem(), toogleGroupValue);
               
                    
                u.setNom_image(path1);
                System.out.println(u.getNom_image());
             
                //img.imageProperty().set(Choose.getAccessibleText());
                //System.out.println("******"+Choose.getAccessibleText());
                if (!RepeterPass.getText().equals(Pass.getText())) {
                    
                    do {
                        RepeterPass.setStyle("-fx-border-color : red ;");
                        JOptionPane.showMessageDialog(null, " le password répété est erroné!!!");
                    } while (RepeterPass.getText().equals(Pass.getText()));
                    
                } else {
                    
                    crudutilisateur.ajouter_utilisateur(u);
                    u.setEnabled(1);
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("nouveau compte est créé");
                    a.showAndWait();
                }
                
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("adresse email existe deja connectez vous");
                a.showAndWait();
            }
        }
       
        
    }

    @FXML
    private void Reset(ActionEvent event) {
         femme.setSelected(false);
        homme.setSelected(true);
        tfmail.clear();
        tfpass.clear();
        Nom.clear();
        //RepeterPass.clear();
    }

    @FXML
    private void Choisir(ActionEvent event) throws MalformedURLException {
          File file = fileChooser.showOpenDialog(primarystage);
        if (file != null) {
            if (file.getName().contains(".jpg")) {
                System.out.println("khtart fichier");

                System.out.println(UUID.randomUUID().toString().concat(".jpg"));

                String uuid = UUID.randomUUID().toString().concat(".jpg");

                path1 = file.getAbsolutePath();
                System.out.println(path1);
                //String pathxx1 = file.toURI().toURL().toString();

                //Image image = new Image(pathxx1);
                file1 = new File(path1);

                // Image image1 = new Image(file1.toURI().toString());
                //imagebtt.setImage(image1);
                 source = new File(path1);

                dest = new File("C:\\wamp64\\www\\PI4\\web\\images\\" + uuid);
                path1 = uuid;  Image i = new Image(file.toURI().toString());
                bttimage.setImage(i);
                
               
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
        
    
}
