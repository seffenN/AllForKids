/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import Entity.Vaccin;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author IRONSIDE
 */
public class CrudVaccin {
    
     Connection connexion = MyConnexion.getInstance().getConnection();
    // Ajout Vaccin
    public void ajouterVaccin(Vaccin p) {
        try {
            System.out.println(p.toString());
                   
            String query = "INSERT INTO `vaccin`(`nom`, `age`, `matricule`, `description`) VALUES" +""
                    + "( '"+p.getNomVaccin()+"','"+p.getAge()+"',"+p.getMaladies()+",'"+p.getDescription()+" )";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }
    
    
    //Modification
    public void modifierVaccin(Vaccin p) {
        try {
            System.out.println(p.toString());
                   
            String query = "UPDATE `vaccin` SET `nom`='"+p.getNomVaccin()+"',`age`='"+p.getAge()+"',`matricule`='"+p.getMaladies()+"',`description`='"+p.getDescription()+"'"
                    +  "WHERE `id`="+p.getId()+"";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }
    
    
    
    //Suppression Vaccin
    public void supprimerVaccin(int id) {
         try {
            String query = "DELETE FROM `vaccin` "
                   + "WHERE id="+id+"";
           
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de suppression");
        }
    }
    
}
