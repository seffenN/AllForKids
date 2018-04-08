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
        con = MyConnexion.getInstance().getConnection();

    }

    public void ajouter_utilisateur(User u) {

        try {

            String query = "INSERT INTO user (username, email,password, roles,  Age,  Sexe, adresse,nom_image) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRoles());
            pst.setInt(5, u.getAge());

            pst.setString(6, u.getSexe());

            pst.setString(7, u.getAdresse());
                        pst.setString(8, u.getNom_image());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
   

   
    public User FindByEmail(String email) {
        try {
            String requete = "SELECT * FROM user where email='" + email + "'";
           Statement ste = con.createStatement();
           ResultSet rs = ste.executeQuery(requete);
            
            while (rs.next()) {
                
                User m = new User(rs.getInt("id"), 
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("sexe"),
                        rs.getString("roles"),
                        rs.getString("password"));
                return m;
            }
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    public  User Authentification(String email , String password) throws SQLException{
        String requete=" SELECT * FROM user where email='"+email+"' AND password='"+password+"'" ;
       Statement ste=con.createStatement() ;
       ResultSet rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
   User m = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),rs.getInt("enabled"),
           rs.getString("password"), rs.getString("roles"),rs.getString("nom_image"));

                
        return m ;
        }
        return null ;
    
    }
}
