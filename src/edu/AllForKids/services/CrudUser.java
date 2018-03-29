/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.User;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Narjes
 */
public class CrudUser {
Connection con;
    public CrudUser() {
          con=MyConnexion.getInstance().getConnection();
        
    }
     public void ajouter_utilisateur(User u) {

        try {

            String query = "INSERT INTO user (username, email,password, roles,Sexe) VALUES(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getMdp());
            pst.setString(4, u.getSexe());
            pst.setObject(5, u.getRole());
            //pst.setInt(5, u.getAge());

           // pst.setString(6, u.getSexe());

            //pst.setString(7, u.getAdresse());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
}
