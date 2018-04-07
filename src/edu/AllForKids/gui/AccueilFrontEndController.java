/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.gui.LoginController;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
public class AccueilFrontEndController implements Initializable {

    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;
    @FXML
    private Button BtnActualite;
    @FXML
    private Button BtnStore;
    @FXML
    private Button BtnBabySitter;
    @FXML
    private Button BtnEvenent;
    @FXML
    private Button BtnEspace;
    @FXML
    private Button BtnPediatre;
    @FXML
    private ScrollPane AncoMain;

    /**
     * Initializes the controller class.
     */
    Stage primaryStage;
    AnchorPane GestionUser, ActualiteClient, GestionStore, GestionEvenement, GestionEspace, GestionBabySitter, GestionPediatre;
    @FXML
    private AnchorPane secPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void ProfileEdit(MouseEvent event) {
    }

    @FXML
    private void Lougout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        loader.setController(new LoginController());

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Connexion");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void setNode(Node node) {

        AncoMain.setContent(node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void GetAcceuil(ActionEvent event) {

        setNode(ActualiteClient);

    }

    @FXML
    private void GetStore(ActionEvent event) throws IOException {
        //setNode(GestionStore);
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("AfficheProd.fxml"));
        secPane.getChildren().add(newLoadedPane);

    }

    @FXML
    private void GetBabySitter(ActionEvent event) {
        setNode(GestionBabySitter);

    }

    @FXML
    private void GetEvenement(ActionEvent event) {

        setNode(GestionEvenement);
    }

    @FXML
    private void GetEspace(ActionEvent event) {
        setNode(GestionEspace);

    }

    @FXML
    private void GetPediatre(ActionEvent event) {
        setNode(GestionPediatre);

    }

}
