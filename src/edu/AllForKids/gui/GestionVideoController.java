/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.MediaVideo;
import edu.AllForKids.entities.Quiz;
import edu.AllForKids.services.CrudMediaVideo;
import edu.AllForKids.services.CrudQuiz;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class GestionVideoController implements Initializable {

    @FXML
    private TextField f_titre;
    @FXML
    private TextField f_url;
    @FXML
    private ComboBox<String> F_theme_lesson;
    @FXML
    private ComboBox<String> F_cat_lesson;
    @FXML
    private TextArea f_description;
    @FXML
    private Button btn_ajt_video;
    @FXML
    private Button EditVideo;
    @FXML
    private Button supprimer;
    @FXML
    private Button reset;
    @FXML
    private CheckBox math;
    @FXML
    private CheckBox science;
    @FXML
    private CheckBox culture;
    @FXML
    private CheckBox langue;
    @FXML
    private CheckBox cat1;
    @FXML
    private CheckBox cat2;
    @FXML
    private CheckBox cat3;
    @FXML
    private CheckBox cat4;
    @FXML
    private VBox vbox;
    private Pagination pagination;
    private HBox ancor;
    private TitledPane r;
    WebEngine engine;
     private WebView web;
     static boolean checking=false;

     static int idvideo;
     static String valid="ajout";
    @FXML
    private Button load;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listcat = FXCollections.observableArrayList("4-6", "7-9", "10-12", "13-15");
                ObservableList<String> listtheme = FXCollections.observableArrayList("Mathématique", "Science", "Culture Générale", "Langue");
                F_cat_lesson.setItems(listcat);
                F_theme_lesson.setItems(listtheme);
                CrudMediaVideo cr=new CrudMediaVideo();
        
            pagination = new Pagination(cr.getAllVideo().size()/itemsPerPage(), 0);
              
       
        pagination.setStyle("-fx-border-color:blue;");
        
        pagination.setPageFactory(new Callback<Integer, Node>() {
 
            @Override
             public Node call(Integer pageIndex) {
               return createPage(pageIndex); 
            }
        });
         
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        vbox.getChildren().addAll(pagination);
   btn_ajt_video.disableProperty().bind(f_titre.textProperty().isEqualTo("")
                        .or(f_url.textProperty().isEqualTo("")).or(F_theme_lesson.getSelectionModel().selectedItemProperty().isNull())
                        .or(f_description.textProperty().isEqualTo("")).or(F_theme_lesson.getSelectionModel().selectedItemProperty().isNull())
                );
      
    }    

    @FXML
    private void AjouterLesson(ActionEvent event) throws SQLException {
        String url=f_url.getText().substring(f_url.getText().indexOf("v=")+2, f_url.getText().length());
        
        if(f_url.getText().startsWith("https://www.youtube.com/watch?v") )
        {
         CrudMediaVideo media=new CrudMediaVideo();
        if(valid.equals("ajout"))
        {
       
        
        System.out.println(url);
        media.AjouterVideo(new MediaVideo("youtube", f_titre.getText(),url , F_theme_lesson.getSelectionModel().getSelectedItem(),  F_cat_lesson.getSelectionModel().getSelectedItem(), f_description.getText()));
        rightBox("Vidéo Ajouté avec succés", "", "");
        load.fire();
        reset();
        }
        if(valid.equals("update"))
        {
              if(checking==true)
        {
              
            media.updateLesson(new MediaVideo("youtube", f_titre.getText(),url , F_theme_lesson.getSelectionModel().getSelectedItem(),  F_cat_lesson.getSelectionModel().getSelectedItem(), f_description.getText()), idvideo);
            rightBox("Vidéo Modifié avec succés!!!!", "", "");
            reset();
            load.fire();
        }
        else
        {
            infoBox("Veuillez saisir un vidéo pour le dmodifié", "Erreur", "");
        }
        }}
        else
        {
            infoBox("Veuillez saisir un format d''url valide !!", "Erreur", "");
        }
    }
  
    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @FXML
    private void ModifierLesson(ActionEvent event) throws SQLException {
        valid="update";
   if(idvideo!=0)
   {
        CrudMediaVideo crudMediaVideo=new CrudMediaVideo();
           MediaVideo m=  crudMediaVideo.getVideo(idvideo);
        f_titre.setText(m.getTitre());
        f_url.setText("https://www.youtube.com/embed/"+m.getIdentif());
        F_cat_lesson.setValue(m.getCategorieAge());
        F_theme_lesson.setValue(m.getTheme());
        f_description.setText(m.getDescription());
        
   }
   else
   {infoBox("Veuillez sélectionner un vidéo pour modifier", "Erreur", "");
       
   }
      
    }

    @FXML
    private void DeleteLesson(ActionEvent event) {
        CrudMediaVideo cmv=new CrudMediaVideo();
         if(idvideo!=0)
   {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Vous étes sur de vouloir supprimer ce vidéo ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    cmv.DeleteVideo(idvideo);
    rightBox("Vidéo supprimé avec succés", "", "");
    load.fire();
   
   
}}
         else
         {infoBox("Veuillez sélectionner un vidéo pour modifier", "Erreur", "");
             
         }
    }

    @FXML
    private void vider(ActionEvent event) {
       reset();
    }
        public void reset()
    {
      f_titre.setText("");
         f_url.setText("");
         f_description.setText("");
         F_cat_lesson.setValue("");
         F_theme_lesson.setValue("");
 
    }

    @FXML
    private void Miseajour(ActionEvent event) {
        
         vbox.getChildren().remove(pagination);
          CrudMediaVideo cr=new CrudMediaVideo();
          pagination = new Pagination(cr.getAllVideo().size()/itemsPerPage(), 0);
              
       
        pagination.setStyle("-fx-border-color:blue;");
        
        pagination.setPageFactory(new Callback<Integer, Node>() {
 
            @Override
             public Node call(Integer pageIndex) {
               return createPage(pageIndex); 
            }
        });
         
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        vbox.getChildren().addAll(pagination);
    }

    @FXML
    private void checktheme(ActionEvent event) throws SQLException {
        
         ArrayList <String> list = new ArrayList<>();
        ArrayList <String> cat = new ArrayList<>();
        CrudMediaVideo crudQuiz=new CrudMediaVideo();
        if(math.isSelected())
           list.add("Mathématique");
            if(science.isSelected())
           list.add("Science");
             if(culture.isSelected())
           list.add("Culture Générale");
             if(langue.isSelected())
           list.add("Langue");
              if(cat1.isSelected())
           cat.add("4-6");
            if(cat2.isSelected())
           cat.add("7-9");
             if(cat3.isSelected())
           cat.add("10-12");
             if(cat4.isSelected())
           cat.add("13-15");
             
             ArrayList<MediaVideo> liste=crudQuiz.FilterQuizTheme(list,cat);
             
                   vbox.getChildren().remove(pagination);
                      int var =crudQuiz.FilterQuizTheme(list,cat).size();
                pagination = new Pagination(var/itemsPerPage(), 0);
                pagination.setStyle("-fx-border-color:blue;");
     
        pagination.setPageFactory(new Callback<Integer, Node>() {
 
            @Override
             public Node call(Integer pageIndex) {
                 int val = 0;
                try {
                    val = crudQuiz.FilterQuizTheme(list,cat).size();
                } catch (SQLException ex) {
                    Logger.getLogger(DisplayListQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                 if(val>0)           
               return createPagebytheme(pageIndex,liste); 
               else
                     return null;
             
            }
        });
 
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        vbox.getChildren().addAll(pagination);
    
            
    }
       public int itemsPerPage2() {
        return 2;
    }
       public VBox createPagebytheme(int pageIndex,ArrayList<MediaVideo> media) { 
      idvideo=0;
        vbox.getChildren().removeAll(r.getChildrenUnmodifiable());
           CrudQuiz cr=new CrudQuiz();
        HashMap<Integer,MediaVideo> number=new HashMap<>();
        int k=0;
     
            for(MediaVideo m:media)
            {
               
                number.put(k, m);
                 k++;
                
            }
       
      
        VBox box = new VBox(5);
       
        int page = pageIndex * itemsPerPage2();
       for (int i = page; i < page + itemsPerPage2(); i++) {
        
      
                 ancor=new HBox();
               
       MediaVideo val = number.get(i);
       web=new WebView();
                  engine=web.getEngine();
             engine.load("https://www.youtube.com/embed/"+val.getIdentif());
          Label l1=new Label("Titre :"+val.getTitre()+"\n");
            Label l2=new Label("Théme :"+val.getTheme()+"\n");
                Label l3=new Label("Catégorie d''age :"+val.getCategorieAge()+"\n");
                    Label l4=new Label("Description :"+val.getCategorieAge());
                HBox test=new HBox();
                test.getChildren().addAll(l1,l2,l3,l4);
                  VBox test2=new VBox();
                  CheckBox check=new CheckBox();
                  check.setAlignment(Pos.CENTER_RIGHT);
                  check.setId(String.valueOf(val.getId()));
                  check.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                         checking=check.isSelected();
                         idvideo=Integer.parseInt(check.getId());
                     }
                 });
                    
                
                          
                          
                          
                          
                  test2.getChildren().addAll(check,web);
                  
         
                r=new TitledPane(l1.getText()+l2.getText()+l3.getText()+l4.getText(), test2);
             
              
                
              
                box.getChildren().add(r);
            
      
    }  return box;
    }
      
    
            public static void rightBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
              public int itemsPerPage() {
        return 2;
    }
                  public VBox createPage(int pageIndex) {   
                      idvideo=0;
           CrudMediaVideo cr=new CrudMediaVideo();
        HashMap<Integer,MediaVideo> number=new HashMap<>();
        int k=0;
       
            for(MediaVideo q:cr.getAllVideo())
            {
               
                number.put(k, q);
                 k++;
                
            }
      
        for(Map.Entry<Integer,MediaVideo> h :number.entrySet())
            {
                System.out.println(h.getKey()+" "+h.getValue().toString());
            }
      
        VBox box = new VBox(5);
       
        int page = pageIndex * itemsPerPage();
       for (int i = page; i < page + itemsPerPage(); i++) {
        
      
         
                 ancor=new HBox();
               
       MediaVideo val = number.get(i);
       web=new WebView();
                  engine=web.getEngine();
             engine.load("https://www.youtube.com/embed/"+val.getIdentif());
          Label l1=new Label("Titre :"+val.getTitre()+"\n");
            Label l2=new Label("Théme :"+val.getTheme()+"\n");
                Label l3=new Label("Catégorie d''age :"+val.getCategorieAge()+"\n");
                    Label l4=new Label("Description :"+val.getCategorieAge());
                HBox test=new HBox();
                test.getChildren().addAll(l1,l2,l3,l4);
                  VBox test2=new VBox();
                  CheckBox check=new CheckBox();
                  check.setAlignment(Pos.CENTER_RIGHT);
                  check.setId(String.valueOf(val.getId()));
                  check.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                         checking=check.isSelected();
                         idvideo=Integer.parseInt(check.getId());
                     }
                 });
                    
                
                          
                          
                          
                          
                  test2.getChildren().addAll(check,web);
                  
         
                r=new TitledPane(l1.getText()+l2.getText()+l3.getText()+l4.getText(), test2);
                
              
                box.getChildren().add(r);
            
      
    }  return box;
    }
}
