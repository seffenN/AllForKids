/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Question;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import edu.AllForKids.entities.Quiz;
import edu.AllForKids.entities.Reponse;
import edu.AllForKids.services.CrudQuiz;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;

import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class DisplayListQuizController implements Initializable {

    @FXML
    private ImageView imagebackground;
    @FXML
    private AnchorPane Parent;
    private Pagination pagination;
    @FXML
    private VBox vbox;
    public static int hello=0;
    @FXML
    private Label labtheme;
    @FXML
    private ComboBox<String> thecombo;
TitledPane r;

public static int quizDisplay;
HBox ancor;
MediaPlayer musicplayer; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try
      {
        
       String bip = "Whitney.mp3";
Media hit = new Media(new File(bip).toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(hit);
mediaPlayer.play();

      }catch(Exception e)
      {
          System.out.println(e.getMessage());
      }
        

            ObservableList<String> listtheme = FXCollections.observableArrayList("Mathématique", "Science", "Culture Générale", "Langue");
            thecombo.setItems(listtheme);
            CrudQuiz cr=new CrudQuiz();
         
          
           
            try {
                pagination = new Pagination(cr.ListeQuiz().size()/itemsPerPage(), 0);
              
            } catch (SQLException ex) {
                Logger.getLogger(DisplayListQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    //    vbox.getChildren().removeAll(vbox.getChildren());
    vbox.getChildren().remove(pagination);
         CrudQuiz cr=new CrudQuiz();
                   try {
                      // System.out.println("rrrr"+);
                       int var =cr.ListeQuizbytheme(thecombo.getSelectionModel().getSelectedItem()).size();
                pagination = new Pagination(var/itemsPerPage(), 0);
            } catch (SQLException ex) {
                       System.err.println(ex.getMessage());
            }
        pagination.setStyle("-fx-border-color:blue;");
     
        pagination.setPageFactory(new Callback<Integer, Node>() {
 
            @Override
             public Node call(Integer pageIndex) {
                 int val = 0;
                try {
                    val = cr.ListeQuizbytheme(thecombo.getSelectionModel().getSelectedItem()).size();
                } catch (SQLException ex) {
                    Logger.getLogger(DisplayListQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                 if(val>0)           
               return createPagebytheme(pageIndex,thecombo.getSelectionModel().getSelectedItem()); 
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
    
    
     
      
           
           
         
       
   
    
    
    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent) {
                addAllDescendents((Parent) node, nodes);
                ((Parent) node).getChildrenUnmodifiable().remove(node);
            }
        }
    }


    
  public int itemsPerPage() {
        return 3;
    }
    public int itemsPerPage2() {
        return 2;
    }
    public VBox createPage(int pageIndex) {   
           CrudQuiz cr=new CrudQuiz();
        HashMap<Integer,Quiz> number=new HashMap<>();
        int k=0;
        try {
            for(Quiz q:cr.ListeQuiz())
            {
               
                number.put(k, q);
                 k++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayListQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Map.Entry<Integer,Quiz> h :number.entrySet())
            {
                System.out.println(h.getKey()+" "+h.getValue().toString());
            }
      
        VBox box = new VBox(5);
       
        int page = pageIndex * itemsPerPage();
       for (int i = page; i < page + itemsPerPage(); i++) {
        
      
         
                 ancor=new HBox();
               
       Quiz val = number.get(i);
       
                Label labeldesc=new Label(val.getDescription());
                Label labetheme=new Label(val.getTheme());
                Button button=new Button("Jouer");
                button.setId(String.valueOf(val.getId()));
                button.setAlignment(Pos.BASELINE_LEFT);
               
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        
                        System.out.println(button.getId());
                          try {
                     quizDisplay=Integer.parseInt(button.getId());
                        
                        Parent ModifService_parent = FXMLLoader.load(getClass().getResource("PlayQuiz.fxml"));
                        Scene ModifServic_scene = new Scene(ModifService_parent);
                        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        app_stage.hide();
                        app_stage.setScene(ModifServic_scene);
                        app_stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(DisplayListQuizController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
                });
                
                ancor.getChildren().addAll(labeldesc,labetheme,button);
         
                r=new TitledPane(val.getNom_quiz(), ancor);
                
               
                box.getChildren().add(r);
            
      
    }  return box;
    }
    
    public VBox createPagebytheme(int pageIndex,String theme) { 
        System.out.println(theme);
        vbox.getChildren().removeAll(r.getChildrenUnmodifiable());
           CrudQuiz cr=new CrudQuiz();
        HashMap<Integer,Quiz> number=new HashMap<>();
        int k=0;
        try {
            for(Quiz q:cr.ListeQuizbytheme(theme))
            {
               
                number.put(k, q);
                 k++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayListQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
        VBox box = new VBox(5);
       
        int page = pageIndex * itemsPerPage2();
       for (int i = page; i < page + itemsPerPage2(); i++) {
        
      
         
                 ancor=new HBox();
               System.out.println(i);
       Quiz val = number.get(i);
           System.out.println(val.toString());
       
                Label labeldesc=new Label(val.getDescription());
                Label labetheme=new Label(val.getTheme());
                Button button=new Button("Jouer");
                button.setId(String.valueOf(val.getId()));
                button.setAlignment(Pos.BASELINE_LEFT);
               
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        
                        System.out.println(button.getId());
                          try {
                     quizDisplay=Integer.parseInt(button.getId());
                        
                        Parent ModifService_parent = FXMLLoader.load(getClass().getResource("PlayQuiz.fxml"));
                        Scene ModifServic_scene = new Scene(ModifService_parent);
                        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        app_stage.hide();
                        app_stage.setScene(ModifServic_scene);
                        app_stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(DisplayListQuizController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
                });
                
                ancor.getChildren().addAll(labeldesc,labetheme,button);
         
                r=new TitledPane(val.getNom_quiz(), ancor);
                
               
                  box.getChildren().add(r);
            
      
    }  return box;
    }

  
}
