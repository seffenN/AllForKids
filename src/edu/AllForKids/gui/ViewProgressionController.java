/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.services.CrudQuiz;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class ViewProgressionController implements Initializable {

    @FXML
    private Pane panestat;
    @FXML
    private ComboBox<Integer> annee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Integer> liste=new ArrayList<>();
        for(int i=1980;i<2040;i++)
        {
           liste.add(i);
        }
             ObservableList<Integer> listanne=FXCollections.observableArrayList(liste);
             annee.setItems(listanne);
  
        // TODO
    }    

    @FXML
    private void Getdata(ActionEvent event) {
               final List<BarChart.Series> seriesList = new LinkedList<>(); 
   final String[] categoriesNames = {"Janvier", "Février", "Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"}; 
    final int[] categories = {1,2,3,4,5,6,7,8,9,10,11,12}; 
        final String[] seriesNames = {"Mathématique", "Science", "Langue", "Culture Générale"}; 
      final double[][] allValues = new double[4][12] ;
         
     
       CrudQuiz crudQuiz=new CrudQuiz();
       int n=0;
       try
       {
        for(int i=0;i<allValues.length;i++)
        {
             for(int j=0;j<allValues[i].length;j++) 
          {
            
              System.out.println(seriesNames[i]+" "+categories[j]);
              if(Double.isNaN(crudQuiz.StatScore1(seriesNames[i], categories[j], 2018, 1)))
              {
                allValues[i][j]=0;  
              }
                  else
              {
              allValues[i][j]=crudQuiz.StatScore1(seriesNames[i], categories[j], 2018, 1);
              System.out.println(allValues[i][j]);
          }}
       }
       }catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
        final double minY = 0; 
        //-Double.MAX_VALUE
        double maxY = 100; 
        for (int seriesIndex = 0; seriesIndex < seriesNames.length; seriesIndex++) { 
            final BarChart.Series series = new BarChart.Series<>(); 
            series.setName(seriesNames[seriesIndex]); 
            final double[] values = allValues[seriesIndex]; 
            for (int categoryIndex = 0; categoryIndex < categoriesNames.length; categoryIndex++) { 
                final double value = values[categoryIndex]; 
                final String category = categoriesNames[categoryIndex]; 
                maxY = Math.max(maxY, value); 
                final BarChart.Data data = new BarChart.Data(category, value); 
                series.getData().add(data); 
            } 
            seriesList.add(series); 
        } 
        // Création du graphique. 
        final CategoryAxis xAxis = new CategoryAxis(); 
        xAxis.getCategories().setAll(categoriesNames); 
        xAxis.setLabel("Période"); 
        final NumberAxis yAxis = new NumberAxis(minY, maxY, 50); 
        yAxis.setLabel("Volumes"); 
        final BarChart chart = new BarChart(xAxis, yAxis); 
        chart.setTitle("Exportations"); 
        chart.getData().setAll(seriesList); 
        panestat.getChildren().add(chart);
        // TODO
    }
    
}
