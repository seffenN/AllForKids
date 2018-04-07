/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;
import static edu.AllForKids.gui.AfficherProduitsController.p;
import edu.AllForKids.services.CrudUser;
import java.io.IOException;
import java.net.URL;
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
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    @FXML
    private void seConnecter(ActionEvent event) throws IOException {
          tfmail.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white");
        tfpass.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white");

        if (tfmail.getText().equals("") || tfpass.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez inserer votre email et votre mot de pass");
            a.showAndWait();

            if (tfmail.getText().equals("")) {
                tfmail.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
            }
            if (tfpass.getText().equals("")) {
                tfpass.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
            }
        } else {
            String email = tfmail.getText();
            String pass = tfpass.getText();
            CrudUser crudutilisateur = new CrudUser();
            User u = crudutilisateur.Verif_Connexion(email, pass);
           
            if (u == null) {
               Alert a = new Alert(Alert.AlertType.WARNING);
                 //Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
           // Scene scene = new Scene(root);
           // Stage stage=new Stage();
           // stage.setScene(scene);
           // stage.show();
               a.setContentText("mdp ou email incorrect");
                a.showAndWait();
            } else if (u.getEnabled() != 1) {
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setContentText("Compte n'est pas confirmé ou bien deactivé par l'admin");
                b.showAndWait();
            }
            /*else {
                CurrentUser = u;
                if (u.getRoles().equals("admin")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilBackEnd.fxml"));
                    loader.setController(new AccueilBackEndController(primaryStage));

                    AnchorPane root = loader.load();
                    AccueilBackEndController dashboard = loader.getController();

                    dashboard.setLabelUserName(u.getUsername(), u.getId());
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("Accueil");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    primaryStage.setMaximized(true);

                } else if (u.getRoles().equals("parent")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilFrontEnd.fxml"));
                    loader.setController(new AcceuilFrontEndController(primaryStage));

                    BorderPane root = loader.load();
                    AcceuilFrontEndController frontaceuil = loader.getController();

                    frontaceuil.setLabelUserName(u.getUsername(), u.getId());
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("Accueil");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    primaryStage.setMaximized(true);

                }
            }
        }*/
        
    }
    }  
}
