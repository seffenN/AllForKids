/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.AllForKids.entities.Enfant;
import edu.AllForKids.entities.Pediatre;
import edu.AllForKids.entities.Rdv;
import edu.AllForKids.entities.User;
import edu.AllForKids.entities.Vaccin;
import edu.AllForKids.services.CrudEnfant;
import edu.AllForKids.services.CrudPediatre;
import edu.AllForKids.services.CrudRdv;
import edu.AllForKids.services.CrudVaccin;
//import edu.AllForKids.utils.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Narjes
 */
public class RdvFXMLController implements Initializable {
    @FXML
    private TableView<Pediatre> table_pediatre;
     @FXML
    private TableColumn<Pediatre, String> c_nom;

    @FXML
    private TableColumn<Pediatre, String> c_adresse;
    @FXML
    private TableColumn<Pediatre, String> c_ville;
     ObservableList<Pediatre> data = FXCollections.observableArrayList();
     ObservableList<Pediatre> r_data = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox c_enfantlist ;
    final ObservableList options = FXCollections.observableArrayList();
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Button bt_reserver;
    @FXML
    private DatePicker date_rdv;
    @FXML
    private JFXComboBox c_vaccinlist;
    @FXML
    private JFXTimePicker tp_picker;
    @FXML
    private  JFXTextField c_recherche;
    
    
    public static Enfant en;
    public static Vaccin vac;
    @FXML
    private Label lb_pediatre;
    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;

    /**
     * Initializes the controller class.
     */
    public int getAge(String s) {
            System.out.println("ssssssss "+s);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try{
             date = formatter.parse(s);
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
// Date dd = new Date(SimpleDateFormat.parse(s));
        //   dd=SimpleDateFormat.parse(s);
//s.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Calendar curr = Calendar.getInstance();
  Calendar birth = Calendar.getInstance();
   System.out.println("dddddddddd "+date);
  birth.setTime(date);
  int yeardiff = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
  int monthdiff=curr.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
           System.out.println("year"+yeardiff+"month"+monthdiff);
  curr.add(Calendar.YEAR,-yeardiff);
  if(birth.equals(curr))
  {yeardiff = 0;}
 
  return (yeardiff*12)+monthdiff;

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      System.out.println("bonjour");
        try {
            VBox box = FXMLLoader.load(getClass().getResource("DrawerBox.fxml"));
             drawer.setSidePane(box);
                for(Node node : box.getChildren())
             { 
             if(node.getAccessibleText()!=null)
                 {   
             node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                      
                    switch(node.getAccessibleText())
                     {  
                         case "Vaccin":
                         
                         
                         break;
                         case "Rendez-vous":
                         break;
                         case "Enfant":
                         break;
                         case "Pediatre":
                         break;
                         case "Quitter":
                         break;
                             
                     }
                  
                 
               });
                 }
        }
          HamburgerBackArrowBasicTransition burgerTask2 =new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e)->{
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }
            else {
                drawer.open();
            }
                    
        });
        
        } catch (IOException ex) {
           // Logger.getLogger(DrawerMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        c_vaccinlist.setVisible(false);
        table_pediatre.setVisible(false);
        date_rdv.setVisible(false);
        tp_picker.setVisible(false);
        lb_pediatre.setVisible(false);
        bt_reserver.setVisible(false);
        c_recherche.setVisible(false);
        
        
        CrudEnfant crenfant =new CrudEnfant();
        User u = new User(1);
        List<Enfant> le = new ArrayList<Enfant>();
        System.out.println("je suis lzaaaaa"+ le.size());
        le.addAll(crenfant.listEnfantByParentId(u));
        for (int i = 0;i < le.size(); i++) {
            
        
        
        c_enfantlist.getItems().add(le.get(i).getNom());
        }
    c_enfantlist.valueProperty().addListener(new ChangeListener<String>() {
      

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                c_vaccinlist.setVisible(true);
                
                Enfant e = new Enfant();
               System.out.println("element selectionné"+c_enfantlist.getSelectionModel().getSelectedItem().toString());
                for (int i = 0;i < le.size(); i++) {
                   if(le.get(i).getNom()== c_enfantlist.getSelectionModel().getSelectedItem().toString())
                       e=le.get(i);
               }
                en=e;
                 System.out.println("agggggge " + e.getNom());
                int a = getAge(e.getDateNaissance());
                System.out.println("agggggge "+a);
               
                
                
                CrudVaccin cv =new CrudVaccin();
       //User u = new User(1);
        List<Vaccin> lv = new ArrayList<Vaccin>();
        System.out.println("je suis lzaaaaa"+ le.size());
        lv.addAll(cv.listofvaccinsbyage(a));
         c_vaccinlist.getItems().clear();
        for (int j = 0;j < lv.size(); j++) {
            
        
        
        c_vaccinlist.getItems().add(lv.get(j).getNomVaccin());
        }
            c_vaccinlist.valueProperty().addListener(new ChangeListener<String>() {

                   @Override
                   public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  table_pediatre.setVisible(true);
                  c_recherche.setVisible(true);
                       for (int i = 0;i < lv.size() ; i++) {
                   if(lv.get(i).getNomVaccin()== c_vaccinlist.getSelectionModel().getSelectedItem().toString())
                       vac=lv.get(i);
               }
                   
                   }
                
            });
        
        
            
            CrudPediatre pediatreService = new CrudPediatre();
            c_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            c_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            c_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            data.addAll(pediatreService.getPediatres());
            System.out.println(data);
            table_pediatre.setItems(data);
               }
  });
        //c_enfantlist.getItems().addAll(crenfant.listEnfantByParentId(CurrentUser.getId()));
        table_pediatre.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        date_rdv.setVisible(true);
       // tp_picker.setVisible(true);
        
    }
});

        
        c_recherche.textProperty().addListener((observable, oldValue, newValue) -> {

           table_pediatre.getItems().clear();
            List<Pediatre> rlp = new ArrayList<Pediatre>();
        CrudPediatre ps = new CrudPediatre();
        rlp.addAll(ps.getPediatres());
        List<Pediatre> nrlp = new ArrayList<Pediatre>();
        nrlp.clear();
            System.out.println("taille de rlp"+rlp.size());
            System.out.println("taille nrlp"+nrlp.size());
       for (int i= 0 ; i < rlp.size();i++){
           
           if(rlp.get(i).getAdresse().contains(newValue)||rlp.get(i).getUsername().contains(newValue)||rlp.get(i).getVille().contains(newValue))
                {  nrlp.add(rlp.get(i));
          
          // table_pediatre.setItems(null);
       }}
       
            for (int i = 0; i < nrlp.size(); i++) {
           
                 System.out.println("list after search"+nrlp.get(i).toString());
            }
           
     //  r_data.clear();
       c_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            c_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            c_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
     // table_pediatre.setItems(null);
      
          // table_pediatre.setItems(null);
       r_data.addAll(nrlp);
     //  table_pediatre.
            table_pediatre.setItems(r_data);
            nrlp.clear();
            rlp.clear();
            data.clear();
            
            System.out.println("taille de rlp"+rlp.size());
            System.out.println("taille nrlp"+nrlp.size());
});
        
   date_rdv.valueProperty().addListener((ov, oldValue, newValue) -> {
           tp_picker.setVisible(true);
        });
    tp_picker.valueProperty().addListener((ov, oldValue, newValue) -> {
           bt_reserver.setVisible(true);
        });
        
//        CrudVaccin vaccinService = new CrudVaccin();
//            c_nom.setCellValueFactory(new PropertyValueFactory<>("nomVaccin"));
//            c_age.setCellValueFactory(new PropertyValueFactory<>("age"));
//            c_maladies.setCellValueFactory(new PropertyValueFactory<>("maladies"));
//            data.addAll(vaccinService.listofvaccins());
//            System.out.println(data);
//            table_vaccin.setItems(data);
//            CrudEnfant enfantService =new CrudEnfant();
//            
//            options.addAll(enfantService.listofknom());
//            c_enfantlist.setItems(options);
//            
    }    
    
     @FXML
    private void addRdv(ActionEvent event) {
         Rdv r = new Rdv();
        Date d= new Date();
       
        Calendar drdv = Calendar.getInstance();
        drdv.set(date_rdv.getValue().getYear(), date_rdv.getValue().getMonthValue(),date_rdv.getValue().getDayOfMonth(), tp_picker.getValue().getHour(), tp_picker.getValue().getMinute());
         System.out.println("la date selectionné"+drdv.get(Calendar.YEAR));
         r.setDate(""+date_rdv.getValue().getYear()+"-"+date_rdv.getValue().getMonthValue()+"-"+date_rdv.getValue().getDayOfMonth()
         +" "+tp_picker.getValue().getHour()+":"+tp_picker.getValue().getMinute()+":00");
         r.setIdEnfant(en.getId());
         r.setIdPediatre(table_pediatre.getSelectionModel().getSelectedItem().getId());
         r.setIdVaccin(vac.getId());
         CrudRdv crdv = new CrudRdv();
         crdv.ajouterRdv(r);
         
    }

    @FXML
    private void ProfileEdit(MouseEvent event) {
    }

    @FXML
    private void Lougout(MouseEvent event) {
    }
    
    
}
