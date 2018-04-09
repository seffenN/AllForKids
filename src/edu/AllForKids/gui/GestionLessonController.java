/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Lesson;
import edu.AllForKids.services.CrudLesson;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;
import org.apache.pdfbox.pdmodel.*;
import javafx.application.HostServices;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class GestionLessonController implements Initializable {

    @FXML
    private TextField f_lesson_nom;
    @FXML
    private ComboBox<String> F_theme_lesson;
    @FXML
    private ComboBox<String> F_cat_lesson;
    @FXML
    private Button btn_parc_im;
    @FXML
    private TextField F_total_quiz;
    @FXML
    private Button btn_ajt_quiz;
    @FXML
    private Button supprimer;
    @FXML
    private Button reset;
    @FXML
    private TextField f_id;
    @FXML
    private TextField Chercher;
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
    private Button EditLesson;
     int file = 0;
    File pDir;
File pfile;
    String lien;
int c;
//private VBox test2;
    @FXML
    private VBox vbox;
    private Pagination pagination;
    private HBox ancor;
    private TitledPane r;
    private static int iddocument;
    private static boolean cheking;
    private static String oldlien;
    HostServices hostServices;
    @FXML
    private Label labdoc;
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
                file = 0;
                c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
                pDir = new File("src/edu/AllForKids/documents/lesson" + c + ".pdf");
                lien = "documents/lesson" + c + ".pdf";
     CrudLesson cr=new CrudLesson();
     if(cr.getAllLessons().size()>0)
     {
                pagination = new Pagination(cr.getAllLessons().size()/itemsPerPage(), 0);
              
       
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
      btn_ajt_quiz.disableProperty().bind(f_lesson_nom.textProperty().isEqualTo("")
                       .or(F_theme_lesson.getSelectionModel().selectedItemProperty().isNull())
                     .or(F_cat_lesson.getSelectionModel().selectedItemProperty().isNull())
                );
    }    
   public int itemsPerPage() {
        return 2;
    }
    @FXML
    private void UplaodImg(ActionEvent event) throws MalformedURLException {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un Lesson (format pdf )..");
  FileChooser.ExtensionFilter extFilter = 
                        new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(extFilter);     // new FileChooser.ExtensionFilter("DOC", "*.doc")
       
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            //ch.setText("image sélectionnée");
            file = 1;
            File image = new File(pfile.toURI().toURL().toExternalForm());
            labdoc.setText(image.getAbsolutePath());
             }
    }
        private void Addimage(ActionEvent event) throws MalformedURLException {
               FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image..");
        fileChooser.getExtensionFilters().addAll(
             new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                 new FileChooser.ExtensionFilter("DOC", "*.doc")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            //ch.setText("image sélectionnée");
            file = 1;
            File image = new File(pfile.toURI().toURL().toExternalForm());
          //  LessonDocument.setImage(image);    }
    }}
        public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }


    @FXML
    private void AjouterLesson(ActionEvent event) throws SQLException {
        CrudLesson crudLesson=new CrudLesson();
        if(valid.equals("ajout"))
        {
            if(!lien.equals(""))
            {
        copier(pfile, pDir);
        crudLesson.AjouterLesson(new Lesson(f_lesson_nom.getText(), lien, F_cat_lesson.getSelectionModel().getSelectedItem(),  F_theme_lesson.getSelectionModel().getSelectedItem()));
        rightBox("Doccument insérer avec succés", "Succés", "");
        load.fire();
            }
            else
            {
                infoBox("Veuillez sélectionner une piéce joint", "Erreur", "");
                 load.fire();
            }
        }
        if(valid.equals("update"))
        {
            if(!lien.startsWith("documents"))
            {
                System.out.println(lien+" "+oldlien);
             copier(pfile, pDir);
             crudLesson.updateLesson(new Lesson(f_lesson_nom.getText(), lien, F_cat_lesson.getSelectionModel().getSelectedItem(), F_theme_lesson.getSelectionModel().getSelectedItem()), iddocument);
            rightBox("Document modifié avec succés", "succés", "");
            }
            else
            {
                crudLesson.updateLesson(new Lesson(f_lesson_nom.getText(), oldlien, F_cat_lesson.getSelectionModel().getSelectedItem(), F_theme_lesson.getSelectionModel().getSelectedItem()), iddocument);
            rightBox("Document modifié avec succés", "succés", "");
            }
            }
            
    }
            public static void rightBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
        
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
        CrudLesson cl=new CrudLesson();
               valid="update";
   if(iddocument!=0)
   {
        Lesson l=cl.getLesson(iddocument);
        f_lesson_nom.setText(l.getNomLesson());
        F_cat_lesson.setValue(l.getCategorieAge());
        F_theme_lesson.setValue(l.getTheme());
        labdoc.setText(l.getBrochure());
        oldlien=l.getBrochure();
    }
    else
   {
       infoBox("Veuillez sélectionner un document pour le modifier", "Erreur", "");
   }
    }

    @FXML
    private void DeleteLesson(ActionEvent event) {
          CrudLesson cmv=new CrudLesson();
         if(iddocument!=0)
   {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Vous étes sur de vouloir supprimer ce document ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    cmv.DeleteLesson(iddocument);
    rightBox("Vidéo supprimé avec succés", "", "");
    load.fire();
   
   
}}
         else
         {infoBox("Veuillez sélectionner un document pour modifier", "Erreur", "");
             
         }
    }
    

    @FXML
    private void vider(ActionEvent event) {
        f_lesson_nom.setText("");
        F_cat_lesson.setValue("");
        F_theme_lesson.setValue("");
        iddocument=0;
        oldlien="";
        labdoc.setText("");
         vbox.getChildrenUnmodifiable().removeAll(vbox.getChildren());
        
    }

    @FXML
    private void Miseajour(ActionEvent event) {
       ;
        CrudLesson cr=new CrudLesson();
         if(cr.getAllLessons().size()>0)
     {
                pagination = new Pagination(cr.getAllLessons().size()/itemsPerPage(), 0);
              
       
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
        
    }

    @FXML
    private void checktheme(ActionEvent event) throws SQLException {
        
         ArrayList <String> list = new ArrayList<>();
        ArrayList <String> cat = new ArrayList<>();
        CrudLesson crudlesson=new CrudLesson();
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
             
             ArrayList<Lesson> liste=crudlesson.FilterLessonTheme(list,cat);
             
                   vbox.getChildren().remove(pagination);
                      int var =crudlesson.FilterLessonTheme(list,cat).size();
                pagination = new Pagination(var/itemsPerPage(), 0);
                pagination.setStyle("-fx-border-color:blue;");
     
        pagination.setPageFactory(new Callback<Integer, Node>() {
 
            @Override
             public Node call(Integer pageIndex) {
                 int val = 0;
                try {
                    val = crudlesson.FilterLessonTheme(list,cat).size();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
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
                public VBox createPage(int pageIndex) {   
                      iddocument=0;
           CrudLesson cr=new CrudLesson();
        HashMap<Integer,Lesson> number=new HashMap<>();
        int k=0;
       
            for(Lesson q:cr.getAllLessons())
            {
               
                number.put(k, q);
                 k++;
                
            }
      
        for(Map.Entry<Integer,Lesson> h :number.entrySet())
            {
                System.out.println(h.getKey()+" "+h.getValue().toString());
            }
      
        VBox box = new VBox(5);
       
        int page = pageIndex * itemsPerPage();
       for (int i = page; i < page + itemsPerPage(); i++) {
        
      
         
                 ancor=new HBox();
               
       Lesson val = number.get(i);
      
                  
            
          Label l1=new Label("Titre :"+val.getNomLesson()+"\n");
            Label l2=new Label("Théme :"+val.getTheme()+"\n");
                Label l3=new Label("Catégorie d''age :"+val.getCategorieAge()+"\n");
                    
             
               HBox test2=new HBox();
                  CheckBox check=new CheckBox();
                  check.setAlignment(Pos.CENTER_RIGHT);
                  check.setId(String.valueOf(val.getId()));
                  check.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                         cheking=check.isSelected();
                         iddocument=Integer.parseInt(check.getId());
                     }
                 });
                
                  Button boutton=new  Button("ouvrir pdf");
                  boutton.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                      //  File file = new File("src/edu/AllForKids/documents/Lesson268509.pdf");
File file = new File("src/edu/AllForKids/"+val.brochure);
HostServices host=GestionQuiz.host;
                         
     host.showDocument(file.getAbsolutePath());

                     }
                 });
                  Label espace=new Label("                                                                                                                   ");
                    test2.getChildren().addAll(check,espace,boutton);
                
                          
                          
                          
                          
              //   test2.getChildren().addAll(check,boutton);
                  
         
                r=new TitledPane(l1.getText()+l2.getText()+l3.getText(), test2);
                
              
                box.getChildren().add(r);
            
      
    }  return box;
    }
                       public VBox createPagebytheme(int pageIndex,ArrayList<Lesson> media) { 
      iddocument=0;
        vbox.getChildren().removeAll(r.getChildrenUnmodifiable());
           CrudLesson cr=new CrudLesson();
        HashMap<Integer,Lesson> number=new HashMap<>();
        int k=0;
     
            for(Lesson m:media)
            {
               
                number.put(k, m);
                 k++;
                
            }
       
      
        VBox box = new VBox(5);
       
        int page = pageIndex * itemsPerPage();
       for (int i = page; i < page + itemsPerPage(); i++) {
        
      
         
                 ancor=new HBox();
               
       Lesson val = number.get(i);
      
                  
            
          Label l1=new Label("Titre :"+val.getNomLesson()+"\n");
            Label l2=new Label("Théme :"+val.getTheme()+"\n");
                Label l3=new Label("Catégorie d''age :"+val.getCategorieAge()+"\n");
                    
             
               HBox test2=new HBox();
                  CheckBox check=new CheckBox();
                  check.setAlignment(Pos.CENTER_RIGHT);
                  check.setId(String.valueOf(val.getId()));
                  check.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                         cheking=check.isSelected();
                         iddocument=Integer.parseInt(check.getId());
                     }
                 });
                
                  Button boutton=new  Button("ouvrir pdf");
                  boutton.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                      //  File file = new File("src/edu/AllForKids/documents/Lesson268509.pdf");
File file = new File("src/edu/AllForKids/"+val.brochure);
HostServices host=GestionQuiz.host;
                         
     host.showDocument(file.getAbsolutePath());

                     }
                 });
                  Label espace=new Label("                                                                                                                   ");
                    test2.getChildren().addAll(check,espace,boutton);
                
                          
                          
                          
                          
              //   test2.getChildren().addAll(check,boutton);
                  
         
                r=new TitledPane(l1.getText()+l2.getText()+l3.getText(), test2);
                
              
                box.getChildren().add(r);
            
      
    }  return box;
    }
}
