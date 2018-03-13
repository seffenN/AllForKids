/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import Entity.Enfant;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author IRONSIDE
 */
public class CrudEnfant {
     Connection connexion = MyConnexion.getInstance().getConnection();
   
    
    
    
    // Ajout Enfant
    public void ajouterEnfant(Enfant p) {
        try {
            System.out.println(p.toString());
                   
            String query = "INSERT INTO `enfant`(`nom`, `prenom`, `datenaissance`, `idParent`) VALUES" +""
                    + "( '"+p.getNom()+"','"+p.getPrenom()+"',"+p.getDateNaissance()+",'"+p.getIdParent()+" )";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }
    
    
    //Modification enfant
    public void modifierVaccin(Enfant p) {
        try {
            System.out.println(p.toString());
                   
            String query = "UPDATE `enfant` SET `nom`='"+p.getNom()+"',`prenom`='"+p.getPrenom()+"',`datenaissance`='"+p.getDateNaissance()+"',`idParent`='"+p.getIdParent()+"'"
                    +  "WHERE `id`="+p.getId()+"";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
        }
    }
    
    
    
    //Suppression Enfant
    public void supprimerEnfant(int id) {
         try {
            String query = "DELETE FROM `enfant` "
                   + "WHERE id="+id+"";
           
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de suppression");
        }
    }
    
    
    
    
}
