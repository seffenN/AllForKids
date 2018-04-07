/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudUser;
import java.io.File;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

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
     int file = 0;
    File pDir;
    File pfile;

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
        u.setEnabled(1);
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
         femme.setSelected(false);
        homme.setSelected(true);
        tfmail.clear();
        tfpass.clear();
        Nom.clear();
        //RepeterPass.clear();
    }

    @FXML
    private void Choisir(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image..");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            //ch.setText("image sélectionnée");
            file = 1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            img.setImage(image);
        }
    }
    
}
