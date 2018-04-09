/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AccueilBackEndController implements Initializable {

    @FXML
    private AnchorPane ContenuPane;

    /**
     * Initializes the controller class.
     */
    
        AnchorPane GGestionUser,GestionStore,GestionEvenement,GestionEspace,GestionBabySitter,GestionPediatre;
    @FXML
    private Label NomGestionnaire;
    @FXML
    private Label LblUserName;
    @FXML
    private ImageView UserPicture;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {    
             //GestionUser = FXMLLoader.load(getClass().getResource(".fxml"));
           //  GestionStore = FXMLLoader.load(getClass().getResource(".fxml"));
             GestionStore =FXMLLoader.load(getClass().getResource("GestionStore.fxml"));
            GestionEspace = FXMLLoader.load(getClass().getResource("AjouterQuiz.fxml"));
            // GestionBabySitter = FXMLLoader.load(getClass().getResource(".fxml"));
            // GestionPediatre = FXMLLoader.load(getClass().getResource(".fxml"));
             // NomGestionnaire.setText("Gestionnaire des users");
              
             //Image image = new Image("file:"+ LoginController.CurrentUser.getNom_image());
//              System.out.println(LoginController.CurrentUser.getNom_image());
             // UserPicture.setImage(image);
              
        } catch (IOException ex) {
             System.out.println("controller.AccueilController.initialize() "+ex);
        }    }    
        // TODO
       

    private void setNode(Node node) {
        ContenuPane.getChildren().clear();
        ContenuPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    

    @FXML
    private void GetGestionStore(ActionEvent event) throws IOException {
      setNode(GestionStore);
       // Ges.setText("Gestionnaire des Articles");
        //Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionStore.fxml"));
        //ContenuPane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void GetGestionEvenement(ActionEvent event) {
            //setNode(GestionArticle);

    }

    @FXML
    private void GetGestionEspace(ActionEvent event) {
        setNode(GestionEspace);
    }

    @FXML
    private void GetGestionUser(ActionEvent event) {
    }

    @FXML
    private void GetGestionBabySitter(ActionEvent event) {
    }

    @FXML
    private void GetGestionPediatre(ActionEvent event) {
    }


    @FXML
    private void disconnect(MouseEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    
}
