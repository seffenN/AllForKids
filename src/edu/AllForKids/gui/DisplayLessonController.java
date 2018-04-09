/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Lesson;
import edu.AllForKids.services.CrudLesson;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
public class DisplayLessonController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> listtheme = FXCollections.observableArrayList("Mathématique", "Science", "Culture Générale", "Langue");
        thecombo.setItems(listtheme);
        CrudLesson cr = new CrudLesson();
        if (cr.getAllLessons().size() > 0) {
            pagination = new Pagination(cr.getAllLessons().size() / itemsPerPage(), 0);

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
    private void getListQuiz(ActionEvent event) throws SQLException {
        vbox.getChildren().remove(pagination);
        CrudLesson crudQuiz = new CrudLesson();
        ArrayList<String> the = new ArrayList<>();
        the.add(thecombo.getSelectionModel().getSelectedItem());
        ArrayList<String> cat = new ArrayList<>();
        ArrayList<Lesson> liste = crudQuiz.FilterLessonTheme(the, cat);

        int var = liste.size();
        pagination = new Pagination(var / itemsPerPage(), 0);

        pagination.setStyle("-fx-border-color:blue;");

        pagination.setPageFactory(new Callback<Integer, Node>() {

            @Override
            public Node call(Integer pageIndex) {

                int val = liste.size();

                if (val > 0) {
                    return createPagebytheme(pageIndex, liste);
                } else {
                    return null;
                }

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

        CrudLesson cr = new CrudLesson();
        HashMap<Integer, Lesson> number = new HashMap<>();
        int k = 0;

        for (Lesson q : cr.getAllLessons()) {

            number.put(k, q);
            k++;

        }

        for (Map.Entry<Integer, Lesson> h : number.entrySet()) {
            System.out.println(h.getKey() + " " + h.getValue().toString());
        }

        VBox box = new VBox(5);

        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {

            ancor = new HBox();

            Lesson val = number.get(i);

            Label l1 = new Label("Titre :" + val.getNomLesson() + "\n");
            Label l2 = new Label("Théme :" + val.getTheme() + "\n");
            Label l3 = new Label("Catégorie d''age :" + val.getCategorieAge() + "\n");

            HBox test2 = new HBox();

            Button boutton = new Button("ouvrir pdf");
            boutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //  File file = new File("src/edu/AllForKids/documents/Lesson268509.pdf");
                    File file = new File("src/edu/AllForKids/" + val.brochure);
                    HostServices host = GestionQuiz.host;

                    host.showDocument(file.getAbsolutePath());

                }
            });
            Label espace = new Label("                                                                                                                   ");
            test2.getChildren().addAll(espace, boutton);

            //   test2.getChildren().addAll(check,boutton);
            r = new TitledPane(l1.getText() + l2.getText() + l3.getText(), test2);

            box.getChildren().add(r);

        }
        return box;
    }

    public VBox createPagebytheme(int pageIndex, ArrayList<Lesson> media) {

        vbox.getChildren().removeAll(r.getChildrenUnmodifiable());
        CrudLesson cr = new CrudLesson();
        HashMap<Integer, Lesson> number = new HashMap<>();
        int k = 0;

        for (Lesson m : media) {

            number.put(k, m);
            k++;

        }

        VBox box = new VBox(5);

        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {

            ancor = new HBox();

            Lesson val = number.get(i);

            Label l1 = new Label("Titre :" + val.getNomLesson() + "\n");
            Label l2 = new Label("Théme :" + val.getTheme() + "\n");
            Label l3 = new Label("Catégorie d''age :" + val.getCategorieAge() + "\n");

            HBox test2 = new HBox();

            Button boutton = new Button("ouvrir pdf");
            boutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //  File file = new File("src/edu/AllForKids/documents/Lesson268509.pdf");
                    File file = new File("src/edu/AllForKids/" + val.brochure);
                    HostServices host = GestionQuiz.host;

                    host.showDocument(file.getAbsolutePath());

                }
            });
            Label espace = new Label("                                                                                                                   ");
            test2.getChildren().addAll(espace, boutton);

            //   test2.getChildren().addAll(check,boutton);
            r = new TitledPane(l1.getText() + l2.getText() + l3.getText(), test2);

            box.getChildren().add(r);

        }
        return box;
    }
}
