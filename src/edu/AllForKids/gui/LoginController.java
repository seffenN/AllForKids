/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;

import edu.AllForKids.services.CrudUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane Pane;
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
   public static User CurrentUser;
    public static String pseudo;
    CrudUser crudutilisateur = new CrudUser();
     public static List<ArrayList> panier;
    public static int nb_produits_panier; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {

        //id.setText(String.valueOf(p.getId()));
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void seConnecter(ActionEvent event) throws IOException, SQLException {
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
             panier=new ArrayList<>();
        nb_produits_panier= panier.size();
                            System.out.println("user n'est pas null *****"+u);

            if (u == null) {
                System.out.println("iln'yapas dns base");
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Email ou mot de passe incorrect");
                a.showAndWait();
                System.out.println("mailfaut");

            }else {
                CurrentUser = u;
                
                if(!u.getRoles().equals("Parent")){
                System.out.println("parent");
                
               
             ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AccueilFrontEnd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
                }
            
         
                else if (!u.getRoles().equals("Admin")){
                 
        ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AccueilFrontEnd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
                }
                else if (!u.getRoles().equals("Pediatre")){
                                        System.out.println("    ped");
((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AccueilBackEnd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 

                }
                else if (!u.getRoles().equals("BabySitter")){
                ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AccueilBackEnd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 

                }

                
            }
        }
      
    }
}
