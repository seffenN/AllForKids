/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Question;
import edu.AllForKids.entities.Quiz;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author khaoula
 */
public class CrudQuestion {
   Connection cnx = MyConnexion.getInstance().getConnection();
   
    public void insertData(Question question) throws SQLException
    {
        
        String sql2 = "INSERT INTO question (id_quiz_id, libelle) VALUES (?,?)";

PreparedStatement statement2 = cnx.prepareStatement(sql2);
statement2.setInt(1, question.getId_quiz_id());
statement2.setString(2, question.getLibelle());

int rowsInserted = statement2.executeUpdate();
if (rowsInserted > 0) {
    System.out.println("A new queston was inserted successfully!");
}
    }
}
