/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.services.CrudQuiz;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class PieMostPlayed extends Application { 
  
    @Override 
    public void start(Stage primaryStage) throws SQLException { 
        // Création du graphique. 
        final PieChart chart = new PieChart(); 
        chart.setTitle("les Quiz les plus joués"); 
        CrudQuiz cr=new CrudQuiz();
        chart.getData().setAll(new PieChart.Data("Mathématique", cr.getPourcentagePlayed("Mathématique")), new PieChart.Data("Science", cr.getPourcentagePlayed("Science")),  
                new PieChart.Data("Langue", cr.getPourcentagePlayed("Langue")), new PieChart.Data("Culture Générale", cr.getPourcentagePlayed("Culture Générale")) 
        ); 
        // Montage de l'IU. 
        final StackPane root = new StackPane(); 
        root.getChildren().add(chart); 
        final Scene scene = new Scene(root, 300, 250); 
        primaryStage.setTitle("Test de PieChart"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 
  
    public static void main(String[] args) { 
        launch(args); 
    } 
}