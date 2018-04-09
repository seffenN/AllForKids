/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import edu.AllForKids.entities.Question;
import edu.AllForKids.entities.Quiz;
import edu.AllForKids.entities.Reponse;
import static edu.AllForKids.gui.GestionLessonController.infoBox;
import static edu.AllForKids.gui.GestionLessonController.rightBox;
import edu.AllForKids.services.CrudLesson;
import edu.AllForKids.services.CrudQuiz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.Label;

import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class AjouterQuizController implements Initializable {

    @FXML
    private TextField f_quiz_nom;
    @FXML
    private ComboBox<String> F_theme_quiz;
    @FXML
    private ComboBox<String> F_cat_quiz;
    @FXML
    private TextArea F_desc_quiz;
    @FXML
    private TextField F_time_quiz;
    @FXML
    private ImageView quizphoto;
    @FXML
    private Button btn_parc_im;
  
    @FXML
    private Button supprimer;
    @FXML
    private Button reset;
    @FXML
    private TextField Chercher;
    @FXML
    private TableColumn<Reponse, String> lib_rep;
    @FXML
    private TableView<Question> FormTokens;
    @FXML
    private TableColumn<Reponse, Integer> verif;
    @FXML
    private TableColumn<Reponse, Integer> pts;
    @FXML
    private TableColumn<Question, String> lib_quest;
    @FXML
    private TableColumn<Question, Integer> id_quest;
    @FXML
    private TableColumn<Reponse, Integer> id_rep_quest;
    @FXML
    private TableView<Reponse> tablereponses;
    private HashMap<Question, ArrayList<Reponse>> hashquest;
    private HashMap<Quiz, HashMap<Question, ArrayList<Reponse>>> hashquiz;
    @FXML
    private TextField newquestion;
    static int num = 0;
   
    int file = 0;
    File pDir;
    File pfile;
    String lien;
    int c;
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
    private TextField F_total_quiz;
    @FXML
    private Button EditQuiz;
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
    private TextField f_id;
    @FXML
    private Button btn_add_quest;
    @FXML
    private Button remove_question;
    @FXML
    private Button btn_add_quiz;
    @FXML
    private Button load;
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                /**/
                idquiz.setCellValueFactory(new PropertyValueFactory("id"));
                nomquiz.setCellValueFactory(new PropertyValueFactory("nom_quiz"));
                themequiz.setCellValueFactory(new PropertyValueFactory("theme"));
                categorieagequiz.setCellValueFactory(new PropertyValueFactory("categorie_age"));
                timequiz5.setCellValueFactory(new PropertyValueFactory("time"));
                
                
                
                
                
                try {
                    tablequiz.getItems().addAll(getQuizs());
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultListQuizController.class.getName()).log(Level.SEVERE, null, ex);
                };
                
                
                
                /**/
                tablereponses.setEditable(true);
                ObservableList<String> listcat = FXCollections.observableArrayList("4-6", "7-9", "10-12", "13-15");
                ObservableList<String> listtheme = FXCollections.observableArrayList("Mathématique", "Science", "Culture Générale", "Langue");
                F_cat_quiz.setItems(listcat);
                F_theme_quiz.setItems(listtheme);
                lib_quest.setCellValueFactory(new PropertyValueFactory<Question, String>("libelle"));
                id_quest.setCellValueFactory(new PropertyValueFactory<Question, Integer>("id"));
                // FormTokens.setItems(getQuestions());
                
                lib_rep.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                lib_rep.setCellFactory(TextFieldTableCell.forTableColumn());
                
                id_rep_quest.setCellValueFactory(new PropertyValueFactory<>("id_quest_id"));
                
                verif.setCellValueFactory(new PropertyValueFactory<>("verif"));
                //verif.setCellFactory(TextFieldTableCell.forTableColumn());
                
                pts.setCellValueFactory(new PropertyValueFactory<>("point"));
                //     tablereponses.setItems(getReponses());
                FormTokens.setRowFactory(tv -> {
                    TableRow<Question> row = new TableRow<>();
                    row.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2 && (!row.isEmpty())) {
                            try {
                                Question rowData = FormTokens.getSelectionModel().getSelectedItem();
                                ShowQuestionDialog(rowData);
                                System.err.println(rowData);
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterQuizController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    return row;
                });
                file = 0;
                c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
                pDir = new File("src/edu/AllForKids/images/Quiz" + c + ".jpg");
                lien = "images/Quiz" + c + ".jpg";
                btn_add_quiz.disableProperty().bind(f_quiz_nom.textProperty().isEqualTo("")
                        .or(F_time_quiz.textProperty().isEqualTo("")).or(F_theme_quiz.getSelectionModel().selectedItemProperty().isNull())
                        .or(F_desc_quiz.textProperty().isEqualTo("")).or(F_cat_quiz.getSelectionModel().selectedItemProperty().isNull())
                );
                
                
                Chercher.textProperty().addListener(new InvalidationListener() {
                    ObservableList<Quiz> list=getQuizs();
                    @Override
                    public void invalidated(javafx.beans.Observable observable) {
                        
                        if(Chercher.textProperty().get().isEmpty()) {
                            
                            
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
                                
                                if(cellValue.contains(Chercher.textProperty().get().toLowerCase())) {
                                    
                                    tableItems.add(list.get(i));
                                    
                                    break;
                                    
                                }
                                
                            }
                            
                            
                        }
                        
                        
                        tablequiz.setItems(tableItems);
                        
                        
                        
                    }
                });
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(AjouterQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
      

        }
       
        public ObservableList<Quiz> getQuizs() throws SQLException {
        ObservableList<Quiz> Quize = FXCollections.observableArrayList();
        CrudQuiz crudQ=new CrudQuiz();
        
       Quize.addAll(crudQ.ListeQuiz());
        return Quize;
    }

    @FXML
    private void UplaodImg(ActionEvent event) throws MalformedURLException {
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
            quizphoto.setImage(image);    }
    }
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
    private void AjouterQuiz(ActionEvent event) {
        if(f_id.getText().equals(""))
        {
        
        if(F_time_quiz.getText().matches("\\d*"))
        {
            if(!FormTokens.getItems().isEmpty())
            {
                 if(!tablereponses.getItems().isEmpty())
            {
           hashquest = new HashMap<>();
        hashquiz = new HashMap<>();
        
        for (Question que : FormTokens.getItems()) {
            int id = que.getId();
            ArrayList<Reponse> listr = new ArrayList<>();
            for (Reponse re : tablereponses.getItems()) {

                if (re.getId_quest_id() == id) {
                   
                    listr.add(re);
                }

            }
            hashquest.put(que, listr);
        }
         copier(pfile, pDir);
        Quiz quiz = new Quiz(f_quiz_nom.getText(), F_theme_quiz.getValue(), F_cat_quiz.getValue(), Integer.parseInt(F_time_quiz.getText()), F_desc_quiz.getText(), lien, gettotal(), 1, hashquest);
        CrudQuiz crudquiz = new CrudQuiz();
        try {
            crudquiz.AjouerQuiz(quiz, hashquest);
            rightBox("Quiz Ajouté Avec succés", "Succés", "");
            System.out.println("ok");
            reset();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        else
        {
            infoBox("Faux Au mois insérer une Réponse", "Erreur", "Erreur");
        }
        }
        else
        {
            infoBox("Faux Au mois insérer une Question", "Erreur", "Erreur");
        }
        }
        else
        {
            infoBox("Veuillez Vérifier la durée du quiz", "Erreur", "Erreur");
        }
        
    }
        if(!f_id.getText().equals(""))
        {  
             if(F_time_quiz.getText().matches("\\d*"))
        {
            if(!FormTokens.getItems().isEmpty())
            {
                 if(!tablereponses.getItems().isEmpty())
            {
                    hashquest = new HashMap<>();
        hashquiz = new HashMap<>();
        for (Question que : FormTokens.getItems()) {
            int id = que.getId();
            ArrayList<Reponse> listr = new ArrayList<>();
            for (Reponse re : tablereponses.getItems()) {
                if (re.getId_quest_id() == id) {
                    listr.add(re);
                }

            }
            hashquest.put(que, listr);
        }
        copier(pfile, pDir);
        Quiz quiz = new Quiz(f_quiz_nom.getText(), F_theme_quiz.getValue(), F_cat_quiz.getValue(), Integer.parseInt(F_time_quiz.getText()), F_desc_quiz.getText(), lien, Integer.parseInt(F_total_quiz.getText()), 1, hashquest);
        CrudQuiz crudquiz = new CrudQuiz();
        try {
            
            crudquiz.ModifierQuiz(quiz, hashquest,Integer.parseInt(f_id.getText()));
            rightBox("Quiz modifié avec succés", "succés", "");
            reset();
            f_id.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(AjouterQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            infoBox("Faux Au mois insérer une Réponse", "Erreur", "Erreur");
        }
        }
        else
        {
            infoBox("Faux Au mois insérer une Question", "Erreur", "Erreur");
        }}
        
        else
        {
            infoBox("Veuillez Vérifier la durée du quiz", "Erreur", "Erreur");
        }}
    }
        public int gettotal() {
        int total = 0;

        for (Reponse re : tablereponses.getItems()) {

            total +=  re.getPoint();

        }
        return total;
    }



    @FXML
    private void vider(ActionEvent event) {
        reset();
    }

    @FXML
    private void Miseajour(ActionEvent event) throws SQLException {
        ObservableList<Quiz> liste=FXCollections.observableArrayList();
            CrudQuiz cq=new CrudQuiz();
            liste.setAll(cq.ListeQuiz());
                
          tablequiz.setItems(liste);

    }



    @FXML
      private void changelibrep(CellEditEvent event) {
        Reponse reponsesel = tablereponses.getSelectionModel().getSelectedItem();
        reponsesel.setLibelle(event.getNewValue().toString());
    }
        public ObservableList<Reponse> getReponses() {
        ObservableList<Reponse> reponses = FXCollections.observableArrayList();
        /* reponses.addAll(new Reponse(1, "reponse1", 1, 10), new Reponse(1, "reponse2", 0, 10),
                new Reponse(2, "reponse1", 1, 20), new Reponse(2, "reponse2", 0, 5));*/
        return reponses;
    }
    @FXML
    private void AjouterQuestion(ActionEvent event) {
             boolean test=false;
        if(num>0)
        {
            for(Reponse r:tablereponses.getItems())
            {
                if(r.getId_quest_id()==num)
                    test=true;
            }
        }
        else
        {test=true;}
        if(test){
        Question neQuestion = new Question(num + 1, newquestion.getText());
        num = num + 1;
        FormTokens.getItems().add(neQuestion);
        }
        else
        {
             infoBox("Erreur", "Erreur", "Veuillez inserere une réponse au question precedant!!");
        }
    }
        public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
             public static void rightBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }


    @FXML
    private void RemoveQuestion(ActionEvent event) {
               Question QUESTRE = FormTokens.getSelectionModel().getSelectedItem();
        FormTokens.getItems().remove(QUESTRE);
        tablereponses.getItems().removeIf(s -> s.getId_quest_id() == QUESTRE.getId());
    }
      private void ShowQuestionDialog(Question rowData) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("AjouterQuiz.fxml"));
        final Stage dialog = new Stage();
        dialog.setTitle("Nouvelle réponse");
        // dialog.initOwner(root.get);
        dialog.initModality(Modality.WINDOW_MODAL);
       // dialog.initStyle(StageStyle.UTILITY);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.show();
        /*  dialog.setX(50 +50);
    dialog.setY(10);*/

        // create a grid for the data entry.
        GridPane grid = new GridPane();
        final TextField Libellerep = new TextField();
        final CheckBox verif = new CheckBox("");

        final TextField point = new TextField();

        grid.addRow(0, new Label("Reponse"), Libellerep);
        grid.addRow(1, new Label("Correcte"), verif);
        grid.addRow(2, new Label("Points"), point);

        grid.setHgap(10);
        grid.setVgap(10);
        GridPane.setHgrow(Libellerep, Priority.ALWAYS);
        GridPane.setHgrow(verif, Priority.ALWAYS);
        GridPane.setHgrow(point, Priority.ALWAYS);
        // create action buttons for the dialog.
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        Button cancel = new Button("Cancel");
        cancel.setCancelButton(true);
        dialog.show();

        // only enable the ok button when there has been some text entered.
        ok.disableProperty().bind(Libellerep.textProperty().isEqualTo("").or(point.textProperty().isEqualTo("")));

        // add action handlers for the dialog buttons.
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
if(point.getText().matches("\\d*"))
{
                System.err.println("v" + verif.selectedProperty().getValue());
                if (verif.selectedProperty().getValue() == true) {
                    Reponse r = new Reponse(rowData.getId(), Libellerep.getText(), 1, Integer.parseInt(point.getText()));
                    tablereponses.getItems().add(r);
                } else {
                    Reponse r = new Reponse(rowData.getId(), Libellerep.getText(), 0, Integer.parseInt(point.getText()));
                    tablereponses.getItems().add(r);
                }
                 dialog.close();
                }
else
{
    infoBox("Veuillez Vérifier le nombre du point", "Erreur", "Erreur");
}
// Integer.parseInt(verif.getText()), Integer.parseInt(point.getText())

               
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialog.close();
            }
        });

        // layout the dialog.
        HBox buttons = HBoxBuilder.create().spacing(10).children(ok, cancel).alignment(Pos.CENTER_RIGHT).build();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid, buttons);
        layout.setPadding(new Insets(5));
        dialog.setScene(new Scene(layout));
        dialog.show();
    }

    private void Addimage(ActionEvent event) throws MalformedURLException {
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
            quizphoto.setImage(image);    }
    }

    @FXML
    private void ModifierQuiz(ActionEvent event) throws SQLException, MalformedURLException, FileNotFoundException {
        F_total_quiz.setVisible(true);
        int idquizmod=tablequiz.getSelectionModel().getSelectedItem().getId();
                   CrudQuiz cr=new CrudQuiz();
         HashMap<Quiz, HashMap<Question, ArrayList<Reponse>>> hash;
         hash=cr.getAllQuiz(idquizmod);
         // TODO
         for(Map.Entry<Quiz, HashMap<Question, ArrayList<Reponse>>> e :hash.entrySet())
         {
             f_id.setText(String.valueOf(e.getKey().getId()));
             f_quiz_nom.setText(e.getKey().getNom_quiz());
             F_desc_quiz.setText(e.getKey().getDescription());
             //System.out.println("C:/Users/khaoula/Documents/NetBeansProjects/AllForKidsDesk7/src/edu/AllForKids/"+e.getKey().getImage());
        // File f = new File("src/edu/AllForKids/images/"+e.getKey().getImage());
        //Image     image = new Image(getClass().getResourceAsStream("src/edu/AllForKids/"+e.getKey().getImage()));
        Image image = new Image(new FileInputStream("C:/Users/Narjes/Documents/NetBeansProjects/AllForkids/src/edu/AllForKids/"+e.getKey().getImage()));
       
             quizphoto.setImage(image);
             
             F_theme_quiz.setValue(e.getKey().getTheme());
             F_cat_quiz.setValue(e.getKey().getCategorie_age());
             F_time_quiz.setText(String.valueOf(e.getKey().getTime()));
             F_total_quiz.setText(String.valueOf(e.getKey().getTotal()));
             HashMap<Question, ArrayList<Reponse>> hashing=e.getValue();
             ObservableList <Question> obque = FXCollections.observableArrayList();
             ObservableList <Reponse> obre = FXCollections.observableArrayList();
             int i=0;
             for(Map.Entry<Question, ArrayList<Reponse>> h :hashing.entrySet())
             {
                 i++;
                 System.out.println(h.getKey());
                 //int i=hashing.size();
                 System.out.println(hashing.size());
                 
                 obque.add(new Question(i, h.getKey().getLibelle()));
                 num=i;
                 
                 for(Reponse r:h.getValue())
                 {
                     obre.add(new Reponse(i, r.getLibelle(), r.getVerif(), r.getPoint()));
                 }
                 tablereponses.setItems(obre);
             }

             FormTokens.setItems(obque);
         } 
    
    
      FormTokens.setRowFactory( tv -> {
   TableRow<Question> row = new TableRow<>();
   row.setOnMouseClicked(ev -> {
        if (ev.getClickCount() == 2 && (! row.isEmpty()) ) {
            try {
                Question rowData = FormTokens.getSelectionModel().getSelectedItem();
                ShowQuestionDialog(rowData);
                System.err.println(rowData);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    });
    return row ;
});
    }

    @FXML
    private void checktheme(ActionEvent event) throws SQLException {
       ArrayList <String> list = new ArrayList<>();
        ArrayList <String> cat = new ArrayList<>();
        CrudQuiz crudQuiz=new CrudQuiz();
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
             
             ObservableList<Quiz> liste=FXCollections.observableArrayList();
             liste.addAll(crudQuiz.FilterQuizTheme(list,cat));
                tablequiz.setItems(liste);
            
      
    }
    
    public void reset()
    {
        f_id.setId("");
         f_quiz_nom.setText("");
             F_desc_quiz.setText("");
             F_theme_quiz.setValue("");
             F_cat_quiz.setValue("");
             F_time_quiz.setText("");
             F_total_quiz.setText("");
             F_total_quiz.setVisible(false);
               FormTokens.getItems().removeAll(FormTokens.getItems());
            tablereponses.getItems().removeAll(tablereponses.getItems());
              quizphoto.setImage(null);
              num=0;
              load.fire();
 
    }

    @FXML
    private void DeletEven(ActionEvent event) {
    }
       

}
