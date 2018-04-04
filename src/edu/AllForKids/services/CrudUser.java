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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     public User Verif_Connexion(String email, String motpasse) {
        User u =null;
        try {
            System.out.println("Recupération...");
            Statement stm =con.createStatement();
            String sql="select * from user where email='"+email+"'AND password='"+motpasse+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
           u= new User(rs.getString("email"),rs.getString("password"));
            System.out.println("Recuperation avec succes");  
            }
        }
           
           //stm.executeQuery(sql);
         catch (SQLException ex) {
        }
        return u;      }
}
