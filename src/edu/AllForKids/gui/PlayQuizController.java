/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import edu.AllForKids.entities.Question;
import edu.AllForKids.entities.Quiz;
import edu.AllForKids.entities.Reponse;
import edu.AllForKids.entities.Score;

import edu.AllForKids.services.CrudQuiz;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class PlayQuizController implements Initializable {

    @FXML
    private AnchorPane ParentAnchor;
    @FXML
    private ImageView Backimage;
    @FXML
    private VBox vbox;
    private HBox ancor;
    private HBox ancor2;
    Label Question;
    @FXML
    private Label timer;
    private final Integer starttime=15;
 private Integer seconds= starttime;
 private static boolean valid=false;
 private static int Score=0;
 private Button bouton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          timer.setFont(Font.font(30));
  timer.setTextFill(Color.RED);
     doTime();
        HashMap<Quiz, HashMap<Question, ArrayList<Reponse>>> list;
        try {
            CrudQuiz cr = new CrudQuiz();
            list = cr.getAllQuiz(DisplayListQuizController.quizDisplay);

            for (Map.Entry<Quiz, HashMap<Question, ArrayList<Reponse>>> e : list.entrySet()) {
                HashMap<Question, ArrayList<Reponse>> hashing = e.getValue();
                int i = 0;
                for (Map.Entry<Question, ArrayList<Reponse>> h : hashing.entrySet()) {
                    i++;
                    ancor = new HBox();
                    ancor.setId("hbox" + i);
                    Label Question = new Label("Question " + i + " : ");
                    Question.setId("question" + i);
                    Label labellib = new Label(h.getKey().getLibelle());
                    labellib.setId("libquestion" + i);
                    ancor.getChildren().addAll(Question, labellib);
                    vbox.getChildren().addAll(ancor);
                    vbox.setSpacing(10);

                    int j = 0;
                    for (Reponse r : h.getValue()) {
                        j++;
                        ancor2 = new HBox();
                      Label space =new Label("          ");
                        Label Choix = new Label("Choix " + j + " : ");
                        
                        Label choice = new Label(r.getLibelle());
                      
                        
                        CheckBox check = new CheckBox();
                        check.setId("c" + String.valueOf(i) + '-' + String.valueOf(j));
                        CheckBox checking = new CheckBox();
                        checking.setId("ch" + String.valueOf(i) + '-' + String.valueOf(j));
                        check.setVisible(false);
                        if (r.getVerif() == 1) {
                            check.setSelected(true);
                        } else {
                            check.setSelected(false);
                        }
                        TextField p = new TextField(String.valueOf(r.getPoint()));
                       p.setVisible(false);
                        ancor2.getChildren().addAll(space,Choix, choice, checking, check, p);
                        vbox.getChildren().addAll(ancor2);
                    }
                }
            }
            bouton = new Button("valider");

            vbox.getChildren().addAll(bouton);
            bouton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                   FetchAndScore();
                    redirect(e);
                }
            });
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(PlayQuizController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    private void doTime() {
       
  Timeline time= new Timeline();
  
  
  KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

   @Override
   public void handle(ActionEvent event) {
    
    
    seconds--;
    timer.setText(" "+seconds.toString());
    if(seconds<=0){
       
        
        time.stop();
       bouton.fire();
      System.out.println("time is up !!!");
    }
    
    
   }
   
   
  });
  
  time.setCycleCount(Timeline.INDEFINITE);
  time.getKeyFrames().add(frame);
  if(time!=null){
   time.stop();
  }
  time.play();
  
  
 }

    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }
   public void redirect(ActionEvent e)
    {
   
     
        try {
            Parent ModifService_parent = FXMLLoader.load(getClass().getResource("AccueilFrontEnd.fxml"));
            Scene ModifServic_scene = new Scene(ModifService_parent);
            Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(ModifServic_scene);
            app_stage.toBack();
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PlayQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent) {
                addAllDescendents((Parent) node, nodes);
            }
        }
    }
public void FetchAndScore()
{   ArrayList<Test> arrayList = new ArrayList<Test>();
                    int i = 0;
                    boolean val1 = false;
                    boolean val2 = false;
                    int val3 = 0;
                    for (Node n : getAllNodes(vbox)) {

                        if (n instanceof CheckBox) {
                            i++;


                            System.out.println(i);
                            if(i==1)
                            val1 = (((CheckBox) n).isSelected());
                             if(i==2)
                            val2 = (((CheckBox) n).isSelected());

                        }

                        if (n instanceof TextField) {
                            i++;
                            System.out.println(i);
                            String ch = (((TextField) n).getText());

                            val3 = Integer.parseInt(ch);
                            if(i==3)
                            {
                            Test t1 = new Test(val1, val2, val3);
                            arrayList.add(t1);
                            }

                            i = 0;
                        }
                    }
                   
                    System.out.println(arrayList);
                  
                    for(Test t : arrayList)
                    {
                        if(t.c1==t.c2)
                            Score+=t.point;
                        
                    }
                    System.out.println("Score "+Score);
CrudQuiz quiz=new CrudQuiz();
              try {
                  quiz.CalculScore(new Score(Score,DisplayListQuizController.quizDisplay ,1));
                  infoBox("vous avez recu "+Score, "score", "");
                  
              } catch (SQLException ex) {
                  Logger.getLogger(PlayQuizController.class.getName()).log(Level.SEVERE, null, ex);
              }
    
}
 public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}


class Test {

    boolean c1;
    boolean c2;
    int point;

    Test() {

    }

    public boolean isC1() {
        return c1;
    }

    public boolean isC2() {
        return c2;
    }

    public int getPoint() {
        return point;
    }

    public void setC1(boolean c1) {
        this.c1 = c1;
    }

    public void setC2(boolean c2) {
        this.c2 = c2;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Test{" + "c1=" + c1 + ", c2=" + c2 + ", point=" + point + '}';
    }

    public Test(boolean c1, boolean c2, int point) {
        this.c1 = c1;
        this.c2 = c2;
        this.point = point;
    }

}