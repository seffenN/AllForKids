/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Quiz;
import edu.AllForKids.entities.Question;
import edu.AllForKids.entities.Reponse;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khaoula
 */
public class CrudQuiz {

       Connection cnx = MyConnexion.getInstance().getConnection();

    public void AjouerQuiz(Quiz quiz, Map<Question, ArrayList<Reponse>> map) throws SQLException {

        String sql = "INSERT INTO quiz (nom_quiz, theme,categorie_age,time,description,image,total) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement statement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, quiz.getNom_quiz());
        statement.setString(2, quiz.getTheme());
        statement.setString(3, quiz.getCategorie_age());
        statement.setInt(4, quiz.getTime());
        statement.setString(5, quiz.getDescription());
        statement.setString(6, quiz.getImage());
        statement.setInt(7, quiz.getTotal());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            for (Map.Entry<Question, ArrayList<Reponse>> e : map.entrySet()) {
                String sql2 = "INSERT INTO question (id_quiz_id, libelle) VALUES (?,?)";

                PreparedStatement statement2 = cnx.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                statement2.setInt(1, auto_id);
                statement2.setString(2, e.getKey().getLibelle());

                int rowsInsertedQuest = statement2.executeUpdate();
                if (rowsInsertedQuest > 0) {
                    for (Reponse r : e.getValue()) {
                        ResultSet rsques = statement2.getGeneratedKeys();
                        rsques.next();
                        int auto_id_que = rsques.getInt(1);
                        String sql3 = "INSERT INTO reponse (id_quest_id, libelle,verif,point) VALUES (?,?,?,?)";

                        PreparedStatement statement3 = cnx.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                        statement3.setInt(1, auto_id_que);
                        statement3.setString(2, r.getLibelle());
                        statement3.setInt(3, r.getVerif());
                        statement3.setInt(4, r.getPoint());

                        int rowsInsertedRep = statement3.executeUpdate();
                        System.out.println(rowsInsertedRep);

                    }
                }
            }

        }
    }

    public void ModifierQuiz(Quiz quiz, Map<Question, ArrayList<Reponse>> map, int id) throws SQLException {

        //nom_quiz, theme,categorie_age,time,description,image,total
        String sql = "UPDATE quiz SET nom_quiz=?, theme=?,categorie_age=?,time=?,description=?,image=?,total=? where id=?";

        PreparedStatement statement;
       
            statement = cnx.prepareStatement(sql);

            statement.setString(1, quiz.getNom_quiz());
            statement.setString(2, quiz.getTheme());
            statement.setString(3, quiz.getCategorie_age());
            statement.setInt(4, quiz.getTime());
            statement.setString(5, quiz.getDescription());
            statement.setString(6, quiz.getImage());
            statement.setInt(7, quiz.getTotal());
            statement.setInt(8, id);

        
        String sqldelete = "DELETE FROM question WHERE id_quiz_id=?";
            
            PreparedStatement statementdel = cnx.prepareStatement(sqldelete);
            statementdel.setInt(1, id);
      int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            for (Map.Entry<Question, ArrayList<Reponse>> e : map.entrySet()) {
                String sql2 = "INSERT INTO question (id_quiz_id, libelle) VALUES (?,?)";

                PreparedStatement statement2 = cnx.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                statement2.setInt(1, auto_id);
                statement2.setString(2, e.getKey().getLibelle());

                int rowsInsertedQuest = statement2.executeUpdate();
                if (rowsInsertedQuest > 0) {
                    for (Reponse r : e.getValue()) {
                        ResultSet rsques = statement2.getGeneratedKeys();
                        rsques.next();
                        int auto_id_que = rsques.getInt(1);
                        String sql3 = "INSERT INTO reponse (id_quest_id, libelle,verif,point) VALUES (?,?,?,?)";

                        PreparedStatement statement3 = cnx.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                        statement3.setInt(1, auto_id_que);
                        statement3.setString(2, r.getLibelle());
                        statement3.setInt(3, r.getVerif());
                        statement3.setInt(4, r.getPoint());

                        int rowsInsertedRep = statement3.executeUpdate();
                        System.out.println(rowsInsertedRep);

                    }
                }
            }

        }
    }

}
