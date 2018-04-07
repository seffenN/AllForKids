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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AccueilBackEndController implements Initializable {

    @FXML
    private Label LblUserName;
    @FXML
    private ImageView UserPicture;
    @FXML
    private AnchorPane ContenuPane;

    /**
     * Initializes the controller class.
     */
    
        AnchorPane GestionMembre,GestionArticle,GestionEvent,GestionEspace,GestionOffre,GestionEval,ChatBotHelp,GestionReclamation,GestionEditProfile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

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
    private void GetGestionUser(ActionEvent event) {
    }

    @FXML
    private void GetGestionStore(ActionEvent event) throws IOException {
        //setNode(GestionArticle);
       // Ges.setText("Gestionnaire des Articles");
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("GestionStore.fxml"));
        ContenuPane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void GetGestionEvenement(ActionEvent event) {
            setNode(GestionArticle);

    }

    @FXML
    private void GetGestionEspace(ActionEvent event) {
        setNode(GestionEspace);
    }

    @FXML
    private void GetGestionBabySitter(ActionEvent event) {
    }

    @FXML
    private void GetGestionPediatre(ActionEvent event) {
    }

    @FXML
    private void EditProfile(MouseEvent event) {
    }

    @FXML
    private void disconnect(MouseEvent event) {
    }
    
}
