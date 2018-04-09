/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;
import edu.AllForKids.entities.Quiz;
import edu.AllForKids.services.CrudQuiz;
import com.sun.prism.impl.Disposer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class ConsultListQuizController implements Initializable {

    @FXML
    private TableView<Quiz> tablequiz;
    @FXML
    private TableColumn<Quiz, Integer> idquiz;
    @FXML
    private TableColumn<Quiz, String> nomquiz;
    @FXML
    private TableColumn<Quiz, String> themequiz;
    @FXML
    private TableColumn<Quiz, String> categorieagequiz;
    @FXML
    private TableColumn<Quiz, Integer> timequiz5;
    @FXML
    private TextField searchfield;
    public static int idquizmod=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            idquiz.setCellValueFactory(new PropertyValueFactory("id"));
            nomquiz.setCellValueFactory(new PropertyValueFactory("nom_quiz"));
            themequiz.setCellValueFactory(new PropertyValueFactory("theme"));
            categorieagequiz.setCellValueFactory(new PropertyValueFactory("categorie_age"));
            timequiz5.setCellValueFactory(new PropertyValueFactory("time"));
            TableColumn col_action = new TableColumn<>("Suppresion");
            TableColumn col_update = new TableColumn<>("Modification");
            tablequiz.getColumns().add(col_update);
            tablequiz.getColumns().add(col_action);
            /**/
            col_update.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>,
                            ObservableValue<Boolean>>() {
                                
                                @Override
                                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                                    return new SimpleBooleanProperty(p.getValue() != null);
                                }
                            });
            
            
            //Adding the Button to the cell
            col_update.setCellFactory(
                    new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                        
                        @Override
                        public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                            return new ButtonCellUpdate();
                        }
                        
                    });
            
            /**/
            col_action.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>,
                            ObservableValue<Boolean>>() {
                                
                                @Override
                                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                                    return new SimpleBooleanProperty(p.getValue() != null);
                                }
                            });
            
            
            //Adding the Button to the cell
            col_action.setCellFactory(
                    new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                        
                        @Override
                        public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                            return new ButtonCell();
                        }
                        
                    });
            
            
            
            try {
                tablequiz.getItems().addAll(getQuizs());
            } catch (SQLException ex) {
                Logger.getLogger(ConsultListQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
            searchfield.textProperty().addListener(new InvalidationListener() {
                ObservableList<Quiz> list=getQuizs();
                @Override
                public void invalidated(javafx.beans.Observable observable) {
                    
                    if(searchfield.textProperty().get().isEmpty()) {
                        
                        
                        tablequiz.setItems(list);
                        
                        return;
                        
                        
                    }
                    
                    ObservableList<Quiz> tableItems = FXCollections.observableArrayList();
                    
                    ObservableList<TableColumn<Quiz, ?>> cols = tablequiz.getColumns();
                    
                    for(int i=0; i<list.size(); i++) {
                        
                        
                        
                        for(int j=0; j<cols.size(); j++) {
                            
                            TableColumn col = cols.get(j);
                            
                            String cellValue = col.getCellData(tablequiz.getItems().get(i)).toString();
                            
                            cellValue = cellValue.toLowerCase();
                            
                            if(cellValue.contains(searchfield.textProperty().get().toLowerCase())) {
                                
                                tableItems.add(list.get(i));
                                
                                break;
                                
                            }
                            
                        }
                        
                        
                    }
                    
                    
                    tablequiz.setItems(tableItems);
                    
                    
                    
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultListQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


            

    
        
        
    public ObservableList<Quiz> getQuizs() throws SQLException {
        ObservableList<Quiz> Quize = FXCollections.observableArrayList();
        CrudQuiz crudQ=new CrudQuiz();
        
       Quize.addAll(crudQ.ListeQuiz());
        return Quize;
    }
        //Define the button cell
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Delete");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	CrudQuiz cr=new CrudQuiz();
                        System.out.println(tablequiz.getItems().get(ButtonCell.this.getIndex()));
                        Alert alert = new Alert(AlertType.CONFIRMATION, "vous etes sur de vouloir supprimer " + "" + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
        cr.SupprimerQuiz(tablequiz.getItems().get(ButtonCell.this.getIndex()).getId());
                	//remove selected item from the table list
                	tablequiz.getItems().remove(tablequiz.getItems().get(ButtonCell.this.getIndex()));
    //do stuff
}
                    
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
super.updateItem(t, empty);
if(!empty){
setGraphic(cellButton);
}
else{
setGraphic(null);
}
}
    }
    
    private class ButtonCellUpdate extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Update");
        
        ButtonCellUpdate(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                   
                	
                    try {
                        idquizmod=tablequiz.getItems().get(ButtonCellUpdate.this.getIndex()).getId();
                        System.out.println(tablequiz.getItems().get(ButtonCellUpdate.this.getIndex()));
                        Parent ModifService_parent = FXMLLoader.load(getClass().getResource("UpdateQuiz.fxml"));
                        Scene ModifServic_scene = new Scene(ModifService_parent);
                        Stage app_stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                        app_stage.hide();
                        app_stage.setScene(ModifServic_scene);
                        app_stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ConsultListQuizController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
super.updateItem(t, empty);
if(!empty){
setGraphic(cellButton);
}
else{
setGraphic(null);
}
}
    }
}
 
