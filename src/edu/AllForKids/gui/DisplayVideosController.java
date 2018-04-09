/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.MediaVideo;
import static edu.AllForKids.gui.GestionVideoController.idvideo;
import edu.AllForKids.services.CrudMediaVideo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
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
public class DisplayVideosController implements Initializable {

    @FXML
    private AnchorPane Parent;
    @FXML
    private ImageView imagebackground;
    @FXML
    private VBox vbox;
    @FXML
    private Label labtheme;
    @FXML
    private ComboBox<String> thecombo;
    private Pagination pagination;
    private HBox ancor;
    private TitledPane r;
    WebEngine engine;
    private WebView web;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listtheme = FXCollections.observableArrayList("Mathématique", "Science", "Culture Générale", "Langue");
        thecombo.setItems(listtheme);
        CrudMediaVideo cr = new CrudMediaVideo();

        pagination = new Pagination(cr.getAllVideo().size() / itemsPerPage(), 0);

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
    private void getListQuiz(ActionEvent event) throws SQLException {
               vbox.getChildren().remove(pagination);
        CrudMediaVideo crudQuiz=new CrudMediaVideo();
        ArrayList<String> the=new ArrayList<>();
        the.add(thecombo.getSelectionModel().getSelectedItem());
      ArrayList<String> cat=new ArrayList<>();
     ArrayList<MediaVideo> liste=crudQuiz.FilterQuizTheme(the,cat);
    
                 
                      // System.out.println("rrrr"+);
                       int var =liste.size();
                pagination = new Pagination(var/itemsPerPage(), 0);
           
        pagination.setStyle("-fx-border-color:blue;");
     
        pagination.setPageFactory(new Callback<Integer, Node>() {
 
            @Override
             public Node call(Integer pageIndex) {
                
                  int  val = liste.size();
                
               
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

    public int itemsPerPage() {
        return 2;
    }

    public VBox createPage(int pageIndex) {
        idvideo = 0;
        CrudMediaVideo cr = new CrudMediaVideo();
        HashMap<Integer, MediaVideo> number = new HashMap<>();
        int k = 0;

        for (MediaVideo q : cr.getAllVideo()) {

            number.put(k, q);
            k++;

        }

        for (Map.Entry<Integer, MediaVideo> h : number.entrySet()) {
            System.out.println(h.getKey() + " " + h.getValue().toString());
        }

        VBox box = new VBox(5);

        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {

            ancor = new HBox();

            MediaVideo val = number.get(i);
            web = new WebView();
            engine = web.getEngine();
            engine.load("https://www.youtube.com/embed/" + val.getIdentif());
            Label l1 = new Label("Titre :" + val.getTitre() + "\n");
            Label l2 = new Label("Théme :" + val.getTheme() + "\n");
            Label l3 = new Label("Catégorie d''age :" + val.getCategorieAge() + "\n");
            Label l4 = new Label("Description :" + val.getCategorieAge());
            HBox test = new HBox();
            test.getChildren().addAll(l1, l2, l3, l4);
            VBox test2 = new VBox();

            test2.getChildren().addAll(web);

            r = new TitledPane(l1.getText() + l2.getText() + l3.getText() + l4.getText(), test2);

            box.getChildren().add(r);

        }
        return box;
    }
    
     public VBox createPagebytheme(int pageIndex,ArrayList<MediaVideo> media) { 
       vbox.getChildren().removeAll(r.getChildrenUnmodifiable());
          
        HashMap<Integer,MediaVideo> number=new HashMap<>();
        int k=0;
     
            for(MediaVideo m:media)
            {
               
                number.put(k, m);
                 k++;
                
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
                  
                
                          
                          
                          
                          
                  test2.getChildren().addAll(web);
                  
         
                r=new TitledPane(l1.getText()+l2.getText()+l3.getText()+l4.getText(), test2);
             
              
                
              
                box.getChildren().add(r);
            
      
    }  return box;
     }
}
