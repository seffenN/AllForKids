/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class QuantiteController implements Initializable {

    @FXML
    private TextField nbrArticle;
    @FXML
    private Button AjouterArtcile;
    Produits p;
    @FXML
    private Label id;

    public static int qt;
    CrudStore prodService=new CrudStore();
    @FXML
    private AnchorPane monFrame;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void AjoutArticle(ActionEvent event) throws SQLException {
        if(nbrArticle.getText().compareTo("") == 0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention");
                alert.setContentText("Vous devez saisir le nombre d'article!");
                alert.showAndWait();
        }else if(Integer.parseInt(nbrArticle.getText()) <0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention");
                alert.setContentText("Nombre d'article ne peut pas étre négatif !");
                alert.showAndWait();
        } else if(Integer.parseInt(nbrArticle.getText())==0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention");
                alert.setContentText("Nombre d'article ne peut pas étre null !");
                alert.showAndWait();
               
         
        }else if(prodService.Stock(AfficheProdController.id, Integer.parseInt(nbrArticle.getText()))){
             System.out.println("ok");
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention");
                alert.setContentText("Quantite en stock insuffisante !");
                alert.showAndWait();
        }else{
        
        id.setText(""+AfficheProdController.id);
            System.out.println(AfficheProdController.id);
        nbrArticle.setText(nbrArticle.getText());
        qt = Integer.parseInt(nbrArticle.getText());
        //System.out.println(id.getText());
        
       ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    }

    public TextField getNbrArticle() {
        return nbrArticle;
    }

    public void setNbrArticle(int nbrArticle) {
        
        this.nbrArticle.setText(""+nbrArticle);
    }

    public Label getId() {
        return id;
    }

    public void setId(int id) {
        
        this.id.setText(""+id);
    }
    
}
