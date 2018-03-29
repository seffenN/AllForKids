/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
    @FXML
    private ComboBox<String> role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> roles = FXCollections.observableArrayList("Parent", "BabySitter","Pediatre");
        role.setItems(roles);
        role.setValue("Vous etes");
        
    }    

    @FXML
    private void btnconnexion(ActionEvent event) {
    }

    @FXML
    private void Register(ActionEvent event) {
        CrudUser userService=new CrudUser();
        User u=new User();
        u.setNom(Nom.getText());
        u.setEmail(Email.getText());
        u.setMdp(Pass.getText());
        u.setRole(role.getValue());
        if(homme.isSelected()){
            u.setSexe(homme.getText());
        }
        else if(femme.isSelected()){
            u.setSexe(femme.getText());
        }
        userService.ajouter_utilisateur(u);
        
    }

    @FXML
    private void Reset(ActionEvent event) {
    }

    @FXML
    private void Choisir(ActionEvent event) {
    }
    
}
