/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Pediatre;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Narjes
 */
public class CrudPediatre {
    
     Connection connexion = MyConnexion.getInstance().getConnection();
    public List<Pediatre> getPediatres(){
    
     try {
            String query = "SELECT * FROM `user` WHERE `roles`='a:1:{i:0;s:13:\"ROLE_PEDIATRE\";}'" ;
            ArrayList Resultat = new ArrayList<Pediatre>();
            Statement stm= connexion.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next())
            {
                Pediatre p = new Pediatre(rs.getString("adresse"), rs.getString("ville"), rs.getInt("id"), rs.getString("username"));
            Resultat.add(p);
            }
            System.out.println("operation effectuee");
            return Resultat;
        } catch (SQLException ex) {
            System.out.println("Echec");
            return null;
        } 
    }
    
}
